package net.justfind.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * <br>
 * <b>功能：</b>lucene配置类<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月21日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class Configuration {
    // lucene版本
    public static final Version LOCAL_VERSION = Version.LUCENE_34;
    //使用IKAnalyzer分词器,当参数为true时，表示使用最大词长分词，false表示使用细粒度分词
    public static final Analyzer ANALYZER = new PaodingAnalyzer();
    // 索引库
    public static Directory DIRECTORY;
    // 索引库目录
    public static String PATH = "";
    // 配置文件
    private static Properties config = new Properties();
    
    public static Directory  getDirectory(){
        try {
            InputStream in  = Object.class.getResourceAsStream("/paoding-dic-home.properties");
            config.load(in);
            PATH = config.getProperty("paoding.index.home"); 
            DIRECTORY = FSDirectory.open(new File(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DIRECTORY;
    }
}
