package net.justfind.util;

import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月29日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class IndexWriterUtil {


    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig indexCpnfig = new IndexWriterConfig(Configuration.LOCAL_VERSION, Configuration.ANALYZER);
            indexWriter = new IndexWriter(Configuration.getDirectory(), indexCpnfig);
            //注册事件，应用关闭时释放资源;addShutdownHook可以添加JVM停止时要处理事件。
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                public void run() {
//                    closeIndexWrite(indexWriter);
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public static void closeIndexWrite(IndexWriter indexWriter) {
        if (indexWriter != null) {
            try {
                indexWriter.close();
                System.out.println("indexWriter is close");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
