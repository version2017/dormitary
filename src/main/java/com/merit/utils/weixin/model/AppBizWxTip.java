package com.merit.utils.weixin.model;

import java.util.Date;

/**
 * AppBizWxTip entity. @author MyEclipse Persistence Tools
 */

public class AppBizWxTip implements java.io.Serializable {

	// Fields

	/**    */
	private static final long serialVersionUID = -2716475595793383264L;
	private String id;
	private String msgid;
	private String openid;
	private String appid;
	private Date sj;
	private String sjStr;
	private String msgType;
	private String msgTypeStr;
	private String txt;
	private String videoId;
	private String videoTpId;
	private String linkTitle;
	private String linkDesc;
	private String link;
	private String gpsWd;
	private String gpsJd;
	private String gpsSf;
	private String gpsMsg;
	private String voiceId;
	private String voiceGs;
	private String tpLink;
	private String tpId;
	private String tpFile;
	private String voiceFile;
	private String videoFile;
	private String videoTpFile;
	private String exp1;
	private String exp2;
	private String exp3;
	private String exp4;
	private String exp5;
	private String exp6;
	private String exp7;
	private String exp8;
	private String exp9;
	private Date createtime;
	private String creator;
	private String creatorName;
	private Date updatetime;
	private String updator;
	private String updatorName;
	private String hfr;
	private Date hfsj;
	private String hfsjStr;
	private String hfnr;

	public String getSjStr() {
		return sjStr;
	}

	public void setSjStr(String sjStr) {
		this.sjStr = sjStr;
	}

	public String getMsgTypeStr() {
		return msgTypeStr;
	}

	public void setMsgTypeStr(String msgTypeStr) {
		this.msgTypeStr = msgTypeStr;
	}

	public String getHfsjStr() {
		return hfsjStr;
	}

	public void setHfsjStr(String hfsjStr) {
		this.hfsjStr = hfsjStr;
	}

	// Constructors
	public String getHfr() {
		return hfr;
	}

	public void setHfr(String hfr) {
		this.hfr = hfr;
	}

	public Date getHfsj() {
		return hfsj;
	}

	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}

	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}

	/** default constructor */
	public AppBizWxTip() {
	}

	/** minimal constructor */
	public AppBizWxTip(String id) {
		this.id = id;
	}

	/** full constructor */
	public AppBizWxTip(String id, String msgid, String openid, String appid, Date sj, String msgType, String txt, String videoId, String videoTpId, String linkTitle, String linkDesc, String link,
			String gpsWd, String gpsJd, String gpsSf, String gpsMsg, String voiceId, String voiceGs, String tpLink, String tpId, String tpFile, String voiceFile, String videoFile, String videoTpFile,
			String exp1, String exp2, String exp3, String exp4, String exp5, String exp6, String exp7, String exp8, String exp9, Date createtime, String creator, String creatorName, Date updatetime,
			String updator, String updatorName) {
		this.id = id;
		this.msgid = msgid;
		this.openid = openid;
		this.appid = appid;
		this.sj = sj;
		this.msgType = msgType;
		this.txt = txt;
		this.videoId = videoId;
		this.videoTpId = videoTpId;
		this.linkTitle = linkTitle;
		this.linkDesc = linkDesc;
		this.link = link;
		this.gpsWd = gpsWd;
		this.gpsJd = gpsJd;
		this.gpsSf = gpsSf;
		this.gpsMsg = gpsMsg;
		this.voiceId = voiceId;
		this.voiceGs = voiceGs;
		this.tpLink = tpLink;
		this.tpId = tpId;
		this.tpFile = tpFile;
		this.voiceFile = voiceFile;
		this.videoFile = videoFile;
		this.videoTpFile = videoTpFile;
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.exp3 = exp3;
		this.exp4 = exp4;
		this.exp5 = exp5;
		this.exp6 = exp6;
		this.exp7 = exp7;
		this.exp8 = exp8;
		this.exp9 = exp9;
		this.createtime = createtime;
		this.creator = creator;
		this.creatorName = creatorName;
		this.updatetime = updatetime;
		this.updator = updator;
		this.updatorName = updatorName;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Date getSj() {
		return this.sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getTxt() {
		return this.txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getVideoId() {
		return this.videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTpId() {
		return this.videoTpId;
	}

	public void setVideoTpId(String videoTpId) {
		this.videoTpId = videoTpId;
	}

	public String getLinkTitle() {
		return this.linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkDesc() {
		return this.linkDesc;
	}

	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGpsWd() {
		return this.gpsWd;
	}

	public void setGpsWd(String gpsWd) {
		this.gpsWd = gpsWd;
	}

	public String getGpsJd() {
		return this.gpsJd;
	}

	public void setGpsJd(String gpsJd) {
		this.gpsJd = gpsJd;
	}

	public String getGpsSf() {
		return this.gpsSf;
	}

	public void setGpsSf(String gpsSf) {
		this.gpsSf = gpsSf;
	}

	public String getGpsMsg() {
		return this.gpsMsg;
	}

	public void setGpsMsg(String gpsMsg) {
		this.gpsMsg = gpsMsg;
	}

	public String getVoiceId() {
		return this.voiceId;
	}

	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}

	public String getVoiceGs() {
		return this.voiceGs;
	}

	public void setVoiceGs(String voiceGs) {
		this.voiceGs = voiceGs;
	}

	public String getTpLink() {
		return this.tpLink;
	}

	public void setTpLink(String tpLink) {
		this.tpLink = tpLink;
	}

	public String getTpId() {
		return this.tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public String getTpFile() {
		return this.tpFile;
	}

	public void setTpFile(String tpFile) {
		this.tpFile = tpFile;
	}

	public String getVoiceFile() {
		return this.voiceFile;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}

	public String getVideoFile() {
		return this.videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public String getVideoTpFile() {
		return this.videoTpFile;
	}

	public void setVideoTpFile(String videoTpFile) {
		this.videoTpFile = videoTpFile;
	}

	public String getExp1() {
		return this.exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

	public String getExp2() {
		return this.exp2;
	}

	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}

	public String getExp3() {
		return this.exp3;
	}

	public void setExp3(String exp3) {
		this.exp3 = exp3;
	}

	public String getExp4() {
		return this.exp4;
	}

	public void setExp4(String exp4) {
		this.exp4 = exp4;
	}

	public String getExp5() {
		return this.exp5;
	}

	public void setExp5(String exp5) {
		this.exp5 = exp5;
	}

	public String getExp6() {
		return this.exp6;
	}

	public void setExp6(String exp6) {
		this.exp6 = exp6;
	}

	public String getExp7() {
		return this.exp7;
	}

	public void setExp7(String exp7) {
		this.exp7 = exp7;
	}

	public String getExp8() {
		return this.exp8;
	}

	public void setExp8(String exp8) {
		this.exp8 = exp8;
	}

	public String getExp9() {
		return this.exp9;
	}

	public void setExp9(String exp9) {
		this.exp9 = exp9;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getUpdatorName() {
		return this.updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

}