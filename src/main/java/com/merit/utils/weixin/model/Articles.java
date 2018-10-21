/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.merit.utils.weixin.model;

import java.io.Serializable;


/**
 * <p>
 *  图文消息
 * </p>
 * @author Jack Zhou
 * @version $Id: Articles.java,v 0.1 2015-11-7 下午9:54:55 Jack Exp $
 */
public class Articles implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String picurl;
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
