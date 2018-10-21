package com.merit.utils.weixin;


import com.merit.utils.weixin.model.AccessToken;
import com.merit.utils.weixin.model.JSTickets;
import com.merit.utils.weixin.model.WxModel;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 微信ID
 * </p>
 * 
 * @author Jack Zhou
 * @version $Id: WxConstants.java,v 0.1 2015-9-30 下午11:43:03 Jack Exp $
 */
public class WxConstants {

	/**
	 * 多个微信配置文件，从xml中读取
	 */
	public static Map<String, WxModel> wxMap = new HashMap<String, WxModel>();

	public static class JSSDKCONS {
		/**
		 * 根据id获取对应的tickets和accesstomekn
		 */
		public static Map<String, JSTickets> ticketMap = new HashMap<String, JSTickets>();
		public static Map<String, AccessToken> accessTokenMap = new HashMap<String, AccessToken>();

	}
}
