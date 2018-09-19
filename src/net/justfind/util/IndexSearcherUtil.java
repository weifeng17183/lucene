package net.justfind.util;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月29日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class IndexSearcherUtil {
    public static void closeIndexSearcher(IndexSearcher indexSearcher) {
        if (indexSearcher != null) {
            try {
                indexSearcher.close();
                System.out.println("indexSearcher is close");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        try {
            indexSearcher = new IndexSearcher(IndexReader.open(Configuration.getDirectory()));
            //注册事件，应用关闭时释放资源;addShutdownHook可以添加JVM停止时要处理事件。
            //            Runtime.getRuntime().addShutdownHook(new Thread() {
            //                public void run() {
            //                    closeIndexSearcher(indexSearcher);
            //                }
            //            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexSearcher;
    }
}
