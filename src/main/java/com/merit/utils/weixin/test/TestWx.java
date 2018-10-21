package com.merit.utils.weixin.test;


import com.merit.utils.weixin.model.AccessToken;
import com.merit.utils.weixin.model.JSTickets;
import com.merit.utils.weixin.util.WeixinUtil;

public class TestWx {

	/**
	 * <p>
	 *  
	 * </p>
	 * @author Jack Zhou
	 * @version $Id: TestWx.java,v 0.1 2014-4-7 下午10:36:40 Jack Exp $
	 */
	public static void main(String[] args) {
		String appId="wx1cb6ab9cf529d65c";
		String appsecret ="da216c144ee31648f579f0cbd4141594";
		AccessToken token = WeixinUtil.getAccessToken(appId, appsecret);
//		System.out.println(token.getToken());
		JSTickets tickets= WeixinUtil.getTicket(token.getToken());
		System.out.println(tickets.getTicket());
//		String apptoken = "UiLwNdF2e3nbQ9wTUN909HmJIXk-uYB9HapPN92YjxKtola5PkRyRyIdv5StL2qgEtomBS9uUCTRwHqtES95MUw38OuEZnMorcx-wXbCxv9p-6uYnxv_nBr8usoPfcbfruHiahyta04ERIwGNYdnnA";
//		WeixinUtil.getUserList(apptoken, "");
//		
//		String wxh = "o_N9EuMRzM-_lkZPmIjU0-U7t_Z0";
//		
//		TextMessage txt = new TextMessage();
//		txt.setContent("hello test");
//		txt.setToUserName("o_N9EuMRzM-_lkZPmIjU0-U7t_Z0");
//		txt.setMsgType("text");
//		System.out.println(MessageUtil.textMessageToXml(txt));
//		
//		JSONObject jo=new JSONObject();
//		jo.put("touser", "o_N9EuMRzM-_lkZPmIjU0-U7t_Z0");
//		jo.put("msgtype", "text");
//		JSONObject _jo=new JSONObject();
//		_jo.put("content", "hello world");
//		jo.put("text", _jo);
//		System.out.println(jo.toString());

	}

}
