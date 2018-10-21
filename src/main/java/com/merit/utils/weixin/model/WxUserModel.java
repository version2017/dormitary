package com.merit.utils.weixin.model;

import java.io.Serializable;

public class WxUserModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String openId;
	private String userId;// 企业号，员工id
	private Integer subscribe;// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	private String nickname;
	private String sex;
	private String city;
	private String country;
	private String province;
	private String headimgurl;
	private String subscribe_time;
	private String unionid;
	private String remark;
	private String groupid;
	private String deviceId;
	private String randmonNo;//微信访问随机码

	
	
	public String getRandmonNo() {
		return randmonNo;
	}

	public void setRandmonNo(String randmonNo) {
		this.randmonNo = randmonNo;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Override
	public String toString() {
		return "WxUserModel{" +
				"openId='" + openId + '\'' +
				", userId='" + userId + '\'' +
				", subscribe=" + subscribe +
				", nickname='" + nickname + '\'' +
				", sex='" + sex + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", province='" + province + '\'' +
				", headimgurl='" + headimgurl + '\'' +
				", subscribe_time='" + subscribe_time + '\'' +
				", unionid='" + unionid + '\'' +
				", remark='" + remark + '\'' +
				", groupid='" + groupid + '\'' +
				", deviceId='" + deviceId + '\'' +
				", randmonNo='" + randmonNo + '\'' +
				'}';
	}
}
