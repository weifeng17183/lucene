package net.justfind.util;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

/**
 * <br>
 * <b>功能：</b>高亮处理工具类<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月22日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class HighLightUtil {
    public static String doHighLight(Query query, String fieldName, String fieldValue) {
        String result = "";
        try {
            //设置高亮的格式
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            //指定高亮的查询条件
            Scorer scorer = new QueryScorer(query);
            Highlighter highlighter = new Highlighter(formatter, scorer);
            //指定高亮后的长度，需要SimpleFragmenter类型参数，这里指定长度为参数size（结尾为标点符号或不够长的词一般不显示出来,所以一般会少几个字）
            highlighter.setTextFragmenter(new SimpleFragmenter());
            //设置对哪个字段进行高亮操作，返回高亮后的结果
            result = highlighter.getBestFragment(Configuration.ANALYZER, fieldName, fieldValue);
            //把高亮后的值重新赋给字段
            if (result == null) {
                result = fieldValue;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
