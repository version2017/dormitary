package com.merit.utils.weixin.model;
/**
 * 微信通用接口凭证
 * 
 * @author 杨建冬
 * @date 2013-09-10
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return "AccessToken{" +
				"token='" + token + '\'' +
				", expiresIn=" + expiresIn +
				'}';
	}
}