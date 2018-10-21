package com.merit.utils.weixin.sdk.service.impl;


import com.merit.utils.weixin.WxConstants;
import com.merit.utils.weixin.model.AccessToken;
import com.merit.utils.weixin.model.JSTickets;
import com.merit.utils.weixin.model.WxModel;
import com.merit.utils.weixin.sdk.service.WeixinJobSer;
import com.merit.utils.weixin.util.WeixinUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WeixinJobSerImpl implements WeixinJobSer {
	protected static Log log = LogFactory.getLog(WeixinJobSerImpl.class);

	public void initJSSDK() {
		Set set = WxConstants.wxMap.keySet();
		for (Object object : set) {
			WxModel model = WxConstants.wxMap.get(object.toString());
			if (model.getType() == 1) {
				// 服务号
				AccessToken token = WeixinUtil.getAccessToken(model.getAppID(), model.getAppSecrect());
				WxConstants.JSSDKCONS.accessTokenMap.put(object.toString(), token);
				if (null != token) {
					JSTickets ticket = WeixinUtil.getTicket(token.getToken());
					WxConstants.JSSDKCONS.ticketMap.put(object.toString(), ticket);
				}
			}
			if (model.getType() == 2) {
				// 企业号
				AccessToken token = WeixinUtil.getCompAccessToken(model.getAppID(), model.getAppSecrect());
				WxConstants.JSSDKCONS.accessTokenMap.put(object.toString(), token);
				if (null != token) {
					JSTickets ticket = WeixinUtil.getQyTicket(token.getToken());
					WxConstants.JSSDKCONS.ticketMap.put(object.toString(), ticket);
				}
			}
		}

	}
}
