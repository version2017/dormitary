package com.merit.utils.weixin.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 *  从文件加载微信的配置信息
 * </p>
 * @author Jack Zhou
 * @version $Id: ConfKit.java,v 0.1 2015-9-30 下午11:44:57 Jack Exp $
 */
public class ConfKit {

	private static Properties props = new Properties();

	static {
		try {
			//框架下要用这种方式加载
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/app/weixin.properties"));
//			WxConstants.appID = props.get("AppId").toString();
//			WxConstants.appSecrect = props.get("AppSecret").toString();
//			WxConstants.macId = props.get("macid").toString();
//			WxConstants.payNotifyURL = props.get("notify_url").toString();
//			WxConstants.payKey = props.get("key").toString();
//			WxConstants.certPosition = props.get("cert_path").toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static void setProps(Properties p) {
		props = p;
	}
}
