package com.merit.utils.weixin.core;


import com.merit.utils.TextUtils;
import com.merit.utils.weixin.WxConstants;
import com.merit.utils.weixin.model.AppBizWxTip;
import com.merit.utils.weixin.util.MediaDownUtil;
import com.merit.utils.weixin.util.MessageUtil;
import com.merit.utils.weixin.util.WeixinUtil;
import com.merit.utils.weixin.util.response.Article;
import com.merit.utils.weixin.util.response.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class CoreService {
	/**
	 * 处理微信发来的请求 换行符仍然是"\n"
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request, String wxid) {
		String respMessage = null;
		String path = request.getRealPath("/") + File.separator + "files" + File.separator + "wx" + File.separator;
		try {
			// 默认返回的文本消息内容
			String respContent = "您的消息已经收到，我们稍后马上联系您！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// System.out.println("fromUserName===" + fromUserName);
			// System.out.println("toUserName===" + toUserName);
			// System.out.println("msgType===" + msgType);
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			AppBizWxTip tip = new AppBizWxTip();
			tip.setId(TextUtils.getUUID());
			tip.setMsgid(requestMap.get("MsgId"));
			tip.setOpenid(fromUserName);
			tip.setAppid(toUserName);
			tip.setCreatetime(new Date());
			tip.setMsgType(msgType);
			tip.setUpdatetime(new Date());
			tip.setSj(new Date());
			tip.setExp1("0");

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 文本消息内容
				// String content = requestMap.get("Content");
				// NewsMessage newsMessage = new NewsMessage();
				// newsMessage.setToUserName(fromUserName);
				// newsMessage.setFromUserName(toUserName);
				// newsMessage.setCreateTime(new Date().getTime());
				// newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				// newsMessage.setFuncFlag(0);
				// List<Article> articleList = new ArrayList<Article>();
				tip.setTxt(requestMap.get("Content"));

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// 图片消息
				String tpmc = System.currentTimeMillis() + "";
				MediaDownUtil.saveImageToDisk(WxConstants.JSSDKCONS.accessTokenMap.get(wxid).getToken(), requestMap.get("MediaId"), tpmc, path);
				tip.setTpFile(tpmc + ".jpg");
				tip.setTpLink(requestMap.get("PicUrl"));
				tip.setTpId(requestMap.get("MsgId"));
				// respContent = "您发送的是图片消息！/微笑";
				respContent = "";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 地理位置消息
				// respContent = "您发送的是地理位置消息！";
				tip.setGpsJd(requestMap.get("Location_Y"));
				tip.setGpsWd(requestMap.get("Location_X"));
				tip.setGpsSf(requestMap.get("Label"));
				respContent = "";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				// 链接消息
				// respContent =
				// "您发送的是<a href=\"http://www.24hs.cn/\">链接</a>消息！";
				tip.setLink(requestMap.get("Url"));
				tip.setLinkTitle(requestMap.get("Title"));
				tip.setLinkDesc(requestMap.get("Description"));
				respContent = "";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// 音频消息
				// respContent = "您发送的是音频消息！";
				String tpmc = System.currentTimeMillis() + "";
				MediaDownUtil.saveVoiceToDisk(WxConstants.JSSDKCONS.accessTokenMap.get(wxid).getToken(), requestMap.get("MediaId"), tpmc, path);
				tip.setVoiceFile(tpmc + ".amr");
				tip.setVoiceId(requestMap.get("MsgId"));
				respContent = "";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件推送
				// 事件类型
				String eventType = requestMap.get("Event");
				// System.out.println("==eventType==" + eventType);
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					List<Article> articleList = new ArrayList<Article>();
					respContent = "你好，欢迎关注樂天商城！您的关注是我们前进的动力";
					// 获取关注人的信息，存入到会员表中
					Map<String, Object> userInfo = WeixinUtil.getSingUserInfo(WxConstants.JSSDKCONS.accessTokenMap.get(wxid).getToken(), fromUserName);
					String hql = " from AppBizUser where  yhwxh=?";
					// AppBizUser appuser = null;
					// if (null == obj) {
					// appuser = new AppBizUser();
					// appuser.setYhid(TextUtils.getUUID());
					// appuser.setYhnc(null == userInfo.get("nickname") ? "" :
					// userInfo.get("nickname").toString());
					// appuser.setYhwxh(fromUserName);
					// appuser.setTx(null == userInfo.get("headimgurl") ? "" :
					// userInfo.get("headimgurl").toString());
					// appuser.setSf(null == userInfo.get("province") ? "" :
					// userInfo.get("province").toString());
					// appuser.setCs(null == userInfo.get("city") ? "" :
					// userInfo.get("city").toString());
					// appuser.setCreatetime(new Date());
					// appuser.setUpdatetime(new Date());
					// appuser.setExp1("1");
					// appuser.setGzsj(new Date());
					// if (!TextUtils.isEmpty(userInfo.get("sex")))
					// appuser.setXb(new Short(userInfo.get("sex").toString()));
					// ser.saveOrUpdate(appuser);
					// }
					// return respContent;
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅
					// String hql = " from AppBizUser where  yhwxh=?";
					// Object obj = ser.queryOneByHql(hql, fromUserName);
					// if (null != obj) {
					// AppBizUser appuser = (AppBizUser) obj;
					// appuser.setExp1("0");
					// appuser.setUpdatetime(new Date());
					// ser.saveOrUpdate(appuser);
					// }
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 自定义菜单点击事件
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					// System.out.println("" + eventKey);
					// System.out.println(respContent);
				}
			}

			textMessage.setContent(respContent);
			// System.out.println("===="+respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
			// System.out.println(respMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}