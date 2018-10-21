package com.merit.utils.weixin.util;

import net.sf.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>
 * 媒体下载接口
 * </p>
 * 
 * @author Jack Zhou
 * @version $Id: MediaDownUtil.java,v 0.1 2015-11-1 下午9:07:00 Jack Exp $
 */
public class MediaDownUtil {

	public static InputStream getInputStream(String accessToken, String mediaId) {
		InputStream is = null;
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			// 获取文件转化为byte流
			is = http.getInputStream();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;

	}

	public static void saveImageToDisk(String accessToken, String mediaId, String picName, String picPath) throws Exception {
		InputStream inputStream = getInputStream(accessToken, mediaId);
		byte[] data = new byte[10240];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(picPath + picName + ".jpg");
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void saveVoiceToDisk(String accessToken, String mediaId, String picName, String picPath) throws Exception {
		InputStream inputStream = getInputStream(accessToken, mediaId);
		byte[] data = new byte[10240];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(picPath + picName + ".amr");
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 图片下载
	 * 
	 * @param accessToken
	 * @param mediaId
	 */
	public static void getPic(String accessToken, String mediaId) {
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		// System.out.println(requestUrl);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		// System.out.println("======" + jsonObject);
	}

	public static void main(String[] args) {
//		JSSDKCONS.accessToken = WeixinUtil.getAccessToken(WxConstants.appID, WxConstants.appSecrect);
		String mediaId = "XaFAd6m1VmWbQPlZQbJxS0XWGJ43670-V7F2sNEKBR-VrAZh-43LztnRhONHYLvI";
		try {
//			saveImageToDisk(JSSDKCONS.accessToken.getToken(), mediaId, "file", "g:/");
			System.out.println("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// SaveImage.getPic(JSSDKCONS.accessToken.getToken(),
		// "XaFAd6m1VmWbQPlZQbJxS0XWGJ43670-V7F2sNEKBR-VrAZh-43LztnRhONHYLvI");

	}

}
