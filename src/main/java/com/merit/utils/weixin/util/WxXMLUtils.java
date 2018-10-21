package com.merit.utils.weixin.util;


import com.merit.utils.TextUtils;
import com.merit.utils.weixin.WxConstants;
import com.merit.utils.weixin.model.WxModel;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class WxXMLUtils {
	public WxXMLUtils() {
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/app/wx.xml");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			Document doc = null;
			doc = DocumentHelper.parseText(sb.toString());
			Element root = doc.getRootElement();// 获得文档根节点
			List<Element> eles = root.elements("wx");
			Element _ele = null;
			String _val = "";
			for (Element ele : eles) {
				_ele = ele.element("id");
				_val = _ele.getText();
				WxModel wx = new WxModel();
				if (TextUtils.isEmpty(_val))
					continue;
				wx.setWxid(_val);
				wx.setType(new Integer(ele.element("type").getText()));
				wx.setAppID(ele.element("AppId").getText());
				wx.setAppSecrect(ele.element("AppSecret").getText());
				wx.setMacId(ele.element("macid").getText());
				wx.setPayKey(ele.element("key").getText());
				wx.setPayNotifyURL(ele.element("notify_url").getText());
				wx.setPcPayNotifyURL(ele.element("web_notify_url").getText());
				wx.setRedirctURL(ele.element("redirect_uri").getText());
				wx.setCertPosition(ele.element("cert_path").getText());
				WxConstants.wxMap.put(wx.getWxid(), wx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
