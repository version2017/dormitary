package com.merit.utils.weixin.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息模版发送返回的结果
 * 
 * @author Jack
 * 
 */
public class TemplateMsg implements Serializable {
	private static Map<String, String> codeMap = new HashMap<String, String>();
	static {
		codeMap.put("-1", "系统繁忙");
		codeMap.put("0", "请求成功");
		codeMap.put("40001", "验证失败");
		codeMap.put("40002", "不合法的凭证类型");
		codeMap.put("40003", "不合法的OpenID");
		codeMap.put("40004", "不合法的媒体文件类型");
		codeMap.put("40005", "不合法的文件类型");
		codeMap.put("40006", "不合法的文件大小");
		codeMap.put("40007", "不合法的媒体文件id");
		codeMap.put("40008", "不合法的消息类型");
		codeMap.put("40009", "不合法的图片文件大小");
		codeMap.put("40010", "不合法的语音文件大小");
		codeMap.put("40011", "不合法的视频文件大小");
		codeMap.put("40012", "不合法的缩略图文件大小");
		codeMap.put("40013", "不合法的APPID");
		codeMap.put("41001", "缺少access_token参数");
		codeMap.put("41002", "缺少appid参数");
		codeMap.put("41003", "缺少refresh_token参数");
		codeMap.put("41004", "缺少secret参数");
		codeMap.put("41005", "缺少多媒体文件数据");
		codeMap.put("41006", "access_token超时");
		codeMap.put("42001", "需要GET请求");
		codeMap.put("43002", "需要POST请求");
		codeMap.put("43003", "需要HTTPS请求");
		codeMap.put("44001", "多媒体文件为空");
		codeMap.put("44002", "POST的数据包为空");
		codeMap.put("44003", "图文消息内容为空");
		codeMap.put("45001", "多媒体文件大小超过限制");
		codeMap.put("45002", "消息内容超过限制");
		codeMap.put("45003", "标题字段超过限制");
		codeMap.put("45004", "描述字段超过限制");
		codeMap.put("45005", "链接字段超过限制");
		codeMap.put("45006", "图片链接字段超过限制");
		codeMap.put("45007", "语音播放时间超过限制");
		codeMap.put("45008", "图文消息超过限制");
		codeMap.put("45009", "接口调用超过限制");
		codeMap.put("46001", "不存在媒体数据");
		codeMap.put("47001", "解析JSON/XML内容错误");
	}

	private String errcode;
	private String errmsg;
	private String msgid;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		if (null == codeMap.get(this.errcode))
			return "";
		else
			return codeMap.get(this.errcode);
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

}
