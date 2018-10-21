package com.merit.utils.weixin.model;

/**
 * 微信通用接口凭证
 * 
 * @author 杨建冬
 * @date 2013-09-10
 */
public class JSTickets {
	// 获取到的凭证
	private String ticket;
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public int getExpiresIn() {
		return expiresIn;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}