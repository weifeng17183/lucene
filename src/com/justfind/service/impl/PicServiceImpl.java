package com.justfind.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.justfind.service.PicService;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>maxmin<br>
 * <b>日期：</b> 2015年6月24日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class PicServiceImpl implements PicService{
	static Logger logger = Logger.getLogger(PicServiceImpl.class);
	
	/**
	 * <br>
	 * <b>功能：</b>查询特征图片<br>
	 * <b>作者：</b>maxmin<br>
	 * <b>日期：</b> 2015年6月24日 <br>
	 * @param url
	 * @return
	 */
	public List<String> queryPic(String url){
		List<String> list=new ArrayList<String>();
		String command = "/home/zhaokou/ImageFeature/ImgFeatures-rec";
		try {
			ProcessBuilder pb = new ProcessBuilder(command, url);
			Map<String, String> env = pb.environment();
			env.put("DYLD_LIBRARY_PATH","/opt/opencv/lib:/home/zhaokou/ImageFeature/*");
			Process p = pb.start();
			p.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				list.add(line);
				logger.info("命中目标："+line);
			}
		} catch (Exception e) {
			logger.info("------------------------"+e.getMessage()+"----");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * <br>
	 * <b>功能：</b>生成特征图片<br>
	 * <b>作者：</b>maxmin<br>
	 * <b>日期：</b> 2015年6月24日 <br>
	 */
	public void generatePic(){
		String url="/opt/kz/apache-tomcat-7.0.61/webapps/justfind/featurepic";
		String command = "/home/zhaokou/ImageFeature/ImgFeatures-train";
		try {
			ProcessBuilder pb = new ProcessBuilder(command, url,"./");
			Map<String, String> env = pb.environment();
			env.put("DYLD_LIBRARY_PATH",".:/opt/opencv/lib:/home/zhaokou/ImageFeature/*");
			Process p = pb.start();
			p.waitFor();
		} catch (Exception e) {
			logger.info("------------------------"+e.getMessage()+"----");
			e.printStackTrace();
		}
	}
}
