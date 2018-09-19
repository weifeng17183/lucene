package com.justfind.service;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>maxmin<br>
 * <b>日期：</b> 2015年6月24日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public interface PicService {
	
	/**
	 * <br>
	 * <b>功能：</b>查询特征图片<br>
	 * <b>作者：</b>maxmin<br>
	 * <b>日期：</b> 2015年6月24日 <br>
	 * @param url
	 * @return
	 */
	public List<String> queryPic(String url);
	
	/**
	 * <br>
	 * <b>功能：</b>生成特征图片<br>
	 * <b>作者：</b>maxmin<br>
	 * <b>日期：</b> 2015年6月24日 <br>
	 */
	public void generatePic();
}
