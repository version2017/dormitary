package com.merit.utils.weixin.model;

import java.io.Serializable;

/**
 * 微信号配置文件
 * 
 * @author Jack
 * 
 */
public class WxModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 26084544896631959L;
	private String wxid = "";
	private Integer type = 0;
	private String appID = "";
	private String appSecrect = "";
	private String macId = "";//
	private String payKey = "";//
	private String payNotifyURL = "";
	private String pcPayNotifyURL = "";
	private String certPosition = "";//
	private String USER_OPENID = "";
	private String redirctURL="";
	
	

	public String getRedirctURL() {
		return redirctURL;
	}

	public void setRedirctURL(String redirctURL) {
		this.redirctURL = redirctURL;
	}

	public String getPcPayNotifyURL() {
		return pcPayNotifyURL;
	}

	public void setPcPayNotifyURL(String pcPayNotifyURL) {
		this.pcPayNotifyURL = pcPayNotifyURL;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecrect() {
		return appSecrect;
	}

	public void setAppSecrect(String appSecrect) {
		this.appSecrect = appSecrect;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public String getPayNotifyURL() {
		return payNotifyURL;
	}

	public void setPayNotifyURL(String payNotifyURL) {
		this.payNotifyURL = payNotifyURL;
	}

	public String getCertPosition() {
		return certPosition;
	}

	public void setCertPosition(String certPosition) {
		this.certPosition = certPosition;
	}

	public String getUSER_OPENID() {
		return USER_OPENID;
	}

	public void setUSER_OPENID(String uSER_OPENID) {
		USER_OPENID = uSER_OPENID;
	}

}
