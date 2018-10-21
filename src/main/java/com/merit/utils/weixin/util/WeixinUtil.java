package com.merit.utils.weixin.util;


import com.merit.utils.DateUtil;
import com.merit.utils.TextUtils;
import com.merit.utils.weixin.WxConstants;
import com.merit.utils.weixin.menuutil.Menu;
import com.merit.utils.weixin.menuutil.MyX509TrustManager;
import com.merit.utils.weixin.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公众平台通用接口工具类
 * 
 * @date 2013-09-10
 */
public class WeixinUtil {

	/**
	 * <p>
	 * 用来判断微信是否是5.0版本以上，否则不支持购买
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午11:12:30 Jack Exp $
	 */
	public static boolean isWeiXin(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (!TextUtils.isEmpty(userAgent)) {
			Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
			Matcher m = p.matcher(userAgent);
			String version = null;
			if (m.find()) {
				version = m.group(1);
			}
			return (null != version && Integer.parseInt(version) >= 5);
		}
		return false;
	}

	/**
	 * <p>
	 * 产生随机字符串
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午11:19:30 Jack Exp $
	 */
	public static String createNonceStr() {
		return UUID.randomUUID().toString();
	}

	/**
	 * <p>
	 * 传入内容进行SHA1签名
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 下午12:23:54 Jack Exp $
	 */
	public static String sign(String mess) {
		String signature = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(mess.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signature;
	}

	/**
	 * 微信支付的签名
	 * 
	 * @param characterEncoding
	 * @param parameters
	 * @param apiKey
	 * @return
	 */
	public static String createSign(String characterEncoding,
			SortedMap<Object, Object> parameters, String apiKey) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + apiKey);
		String sign = MD5Utils.MD5Encode(sb.toString(), characterEncoding)
				.toUpperCase();
		return sign;
	}

	/**
	 * <p>
	 * 字节转16进制
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 下午12:00:04 Jack Exp $
	 */
	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	// public static String getRequest(String url) {
	// try {
	// Response response =
	// Jsoup.connect(url).ignoreContentType(true).method(Method.GET).timeout(600000).execute();
	// if (response.statusCode() == 200) {
	// String result = response.body();
	// System.out.println("backData:" + result);
	// return result;
	// // JSONArray jsonArray = JSONArray.fromObject("[" + result +
	// // "]");
	// }
	// return "";
	// } catch (IOException e) {
	// e.printStackTrace();
	// return "";
	// }
	//
	// }

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
                                         String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			// log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * <p>
	 * 返回字符串
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午1:20:11 Jack Exp $
	 */
	public static String httpPostRequest(String requestUrl,
			String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			// log.error("https request error:{}", e);
		}
		return buffer.toString();
	}

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("json==" + jsonObject.toString());
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				e.printStackTrace();
				// 获取token失败 , jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg")
				System.out.println("获取token失败 errcode:{} errmsg:{}");
			}
		}
		return accessToken;
	}

	/**
	 * <p>
	 * 获得accessToken 字符串
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-9-2 下午3:01:54 Jack Exp $
	 */
	public static String getAccessStringToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				return jsonObject.getString("access_token");
			} catch (JSONException e) {
				return "";
			}
		}
		return "";
	}

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				// 创建菜单失败, jsonObject.getInt("errcode"),
				System.out.println(jsonObject.getString("errmsg"));
				System.out.println("创建菜单失败 ");
			}
		}
		return result;
	}

	// 发送客户消息给用户
	public static String send_msg_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	public static boolean sendBackMsg(Map map, String accessToken) {
		int result = 0;
		String url = send_msg_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(map).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				return false;
			}
		}
		return true;
	}

	// 获取关注者列表 限100（次/天）
	public static String get_user_list = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	// {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
	public static List<String> getUserList(String accessToken, String nextOpenId) {
		String requestUrl = get_user_list.replace("ACCESS_TOKEN", accessToken)
				.replace("NEXT_OPENID", nextOpenId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		List<String> list = new ArrayList<String>();
		// 如果请求成功
		if (null != jsonObject) {
			try {
				JSONObject datas = (JSONObject) jsonObject.get("data");
				JSONArray openidArray = datas.getJSONArray("openid");
				for (Object object : openidArray) {
					list.add(object.toString());
					System.out.println(object.toString());
				}
			} catch (Exception e) {
				// 获取token失败 , jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg")
				System.out.println("获取token失败 errcode:{} errmsg:{}");
			}
		}
		return list;
	}

	// 发送客户消息 48小时内
	public static String send_kf_message = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	public static void sendKfMessage(String accessToken, String sendMsg) {
		String requestUrl = send_kf_message
				.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", sendMsg);

	}

	// 获取 jsapi_ticket，为js-sdk的签名使用
	public static String send_jsticket_message = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESSTOKEN&type=jsapi";

	public static JSTickets getTicket(String accessToken) {
		JSTickets jsticket = null;
		String requestUrl = send_jsticket_message.replace("ACCESSTOKEN",
				accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				jsticket = new JSTickets();
				jsticket.setTicket(jsonObject.getString("ticket"));
				jsticket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				System.out.println("获取ticket失败 errcode:{} errmsg:{}"
						+ e.getMessage());
			}
		}
		return jsticket;
	}

	/** 获取用户关注者列表 */
	public static String user_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	public static Map<String, Object> getUser(String accessToken,
			String nextOpenId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String requestUrl = user_list_url.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("NEXT_OPENID", nextOpenId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				if (!TextUtils.isEmpty(jsonObject.get("errcode"))) {
					map.put("errcode", jsonObject.getString("errcode"));
					map.put("errmsg", jsonObject.getString("errmsg"));
				} else {
					map.put("errcode", "0");
					map.put("errmsg", "");
					map.put("total", jsonObject.getString("total"));
					map.put("count", jsonObject.getString("count"));
					List<String> list = new ArrayList<String>();
					JSONObject datas = (JSONObject) jsonObject.get("data");
					JSONArray openidArray = datas.getJSONArray("openid");
					for (Object object : openidArray) {
						list.add(object.toString());
					}
					map.put("data", list);
					map.put("next_openid", jsonObject.getString("next_openid"));
				}
			} catch (JSONException e) {
				System.out.println("获取关注着列表失败" + e.getMessage());
			}
		}
		return map;
	}

	public static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	public static Map<String, Object> getSingUserInfo(String accessToken,
			String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String requestUrl = user_info_url.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				if (!TextUtils.isEmpty(jsonObject.get("errcode"))) {
					map.put("errcode", jsonObject.getString("errcode"));
					map.put("errmsg", jsonObject.getString("errmsg"));
				} else {
					map.put("errcode", "0");
					map.put("errmsg", "");
					map.put("subscribe", jsonObject.getString("subscribe"));
					map.put("openid", jsonObject.getString("openid"));
					map.put("nickname", jsonObject.getString("nickname"));
					map.put("sex", jsonObject.getString("sex"));
					map.put("city", jsonObject.getString("city"));
					map.put("country", jsonObject.getString("country"));
					map.put("province", jsonObject.getString("province"));
					map.put("language", jsonObject.getString("language"));
					map.put("headimgurl", jsonObject.getString("headimgurl"));
					map.put("subscribe_time",
							jsonObject.getString("subscribe_time"));
					map.put("remark", jsonObject.getString("remark"));
					map.put("groupid", jsonObject.getString("groupid"));
				}
			} catch (JSONException e) {
				System.out.println("获取关注着列表失败" + e.getMessage());
			}
		}
		return map;
	}

	/**
	 * <p>
	 * 根据静默方式的授权来获取关注用户的信息，若无关注，则返回subscribe为0或者null
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-9-15 下午10:56:37 Jack Exp $
	 */
	public static String USER_CODE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public static WxUserModel getWxMemb(String accessToken, String code,
										String wxid) {
		WxUserModel user = null;
		System.out.println("fetch wxMemb accessToken:" + accessToken);
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(code))
			return null;
		String requestUrl = USER_CODE_URL
				.replace("APPID", WxConstants.wxMap.get(wxid).getAppID())
				.replace("SECRET", WxConstants.wxMap.get(wxid).getAppSecrect())
				.replace("CODE", code);
		System.out.println("requestUrl:"+requestUrl);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				user = new WxUserModel();
				String openId = "";
				if (jsonObject.has("openid")) {
					openId = jsonObject.getString("openid");
				}
				user.setOpenId(openId);
				try {
					// 获取该用户的其他信息
					requestUrl = user_info_url.replace("ACCESS_TOKEN",
							accessToken);
					requestUrl = requestUrl.replace("OPENID", openId);
					jsonObject = httpRequest(requestUrl, "GET", null);
					System.out.println(jsonObject);
					if (jsonObject.has("errcode")
							&& jsonObject.getString("errcode").toString()
									.equals("40001")) {

					}
					if (jsonObject.has("subscribe")
							&& !TextUtils.isEmpty(jsonObject
									.getString("subscribe"))) {

						user.setSubscribe(new Integer(jsonObject
								.getString("subscribe")));
					} else {
						user.setSubscribe(0);
					}
					// user.setOpenId(null == jsonObject.get("openid") ? "" :
					// jsonObject.getString("openid"));
					if (user.getSubscribe() != 0) {
						String _nickName = "";
						if (jsonObject.has("nickname")) {
							_nickName = jsonObject.getString("nickname");
							_nickName = EmojiFilterUtils.filterEmoji(_nickName);
						}
						user.setNickname(_nickName);
						if (jsonObject.has("sex"))
							user.setSex(jsonObject.getString("sex"));
						if (jsonObject.has("city"))
							user.setCity(jsonObject.getString("city"));
						if (jsonObject.has("country"))
							user.setCountry(jsonObject.getString("country"));
						if (jsonObject.has("province"))
							user.setProvince(jsonObject.getString("province"));
						if (jsonObject.has("headimgurl"))
							user.setHeadimgurl(jsonObject
									.getString("headimgurl"));
						if (jsonObject.has("subscribe_time"))
							user.setSubscribe_time(transferLongToDate(
									"yyyy-MM-dd HH:mm:ss",
									new Long(jsonObject
											.getString("subscribe_time"))));
						if (jsonObject.has("remark"))
							user.setRemark(jsonObject.getString("remark"));
						if (jsonObject.has("groupid"))
							user.setGroupid(jsonObject.getString("groupid"));
					}
					// if (TextUtils.isEmpty(user.getOpenId()))
					// user.setOpenId(TextUtils.getUUID());
					return user;
				} catch (Exception e) {
					System.out.println("==userInfo Error ==");
					e.printStackTrace();
					user.setSubscribe(0);
					// if (TextUtils.isEmpty(user.getOpenId()))
					// user.setOpenId(TextUtils.getUUID());
					return user;
				}

			} catch (Exception e) {
				System.out.println("==code Error ==");
				e.printStackTrace();
				return null;
			}
		} else {
			user = new WxUserModel();
			// user.setOpenId(TextUtils.getUUID());
			user.setSubscribe(0);
		}
		return user;
	}


	/**
	 * 非静默模式获取用户相关信息
	 * @param accessToken
	 * @param code
	 * @param wxid
	 * @return
	 */
	public static String snsapi_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN ";
	public static WxUserModel getWxMembByUserinfo(String accessToken,
			String code, String wxid) {
		WxUserModel user = null;
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(code))
			return null;
		String requestUrl = USER_CODE_URL
				.replace("APPID", WxConstants.wxMap.get(wxid).getAppID())
				.replace("SECRET", WxConstants.wxMap.get(wxid).getAppSecrect())
				.replace("CODE", code);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				user = new WxUserModel();
				String openId = "";
				if (jsonObject.has("openid")) {
					openId = jsonObject.getString("openid");
				}
				user.setOpenId(openId);
				try {
					// 获取该用户的其他信息
					requestUrl = snsapi_userinfo.replace("ACCESS_TOKEN",
							accessToken).replace("OPENID", openId);
					jsonObject = httpRequest(requestUrl, "GET", null);
					System.out.println(jsonObject);
					if (jsonObject.has("errcode")
							&& jsonObject.getString("errcode").toString()
									.equals("40001")) {

					}
					String _nickName = "";
					if (jsonObject.has("nickname")) {
						_nickName = jsonObject.getString("nickname");
						_nickName = EmojiFilterUtils.filterEmoji(_nickName);
					}
					user.setNickname(_nickName);
					if (jsonObject.has("sex"))
						user.setSex(jsonObject.getString("sex"));
					if (jsonObject.has("city"))
						user.setCity(jsonObject.getString("city"));
					if (jsonObject.has("country"))
						user.setCountry(jsonObject.getString("country"));
					if (jsonObject.has("province"))
						user.setProvince(jsonObject.getString("province"));
					if (jsonObject.has("headimgurl"))
						user.setHeadimgurl(jsonObject.getString("headimgurl"));
					return user;
				} catch (Exception e) {
					System.out.println("==userInfo Error ==");
					e.printStackTrace();
					user.setSubscribe(0);
					return user;
				}

			} catch (Exception e) {
				System.out.println("==code Error ==");
				e.printStackTrace();
				return null;
			}
		} else {
			user = new WxUserModel();
			// user.setOpenId(TextUtils.getUUID());
			user.setSubscribe(0);
		}
		return user;
	}

	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec * 1000);
		return sdf.format(date);
	}

	// 微信端统一下单接口
	public static String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * <p>
	 * 统一下单接口，返回订单号
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 上午12:16:08 Jack Exp $
	 */
	public static UnifiedOrderBack unifiedOrder(UnifiedOrder order) {
		UnifiedOrderBack orderback = new UnifiedOrderBack();
		String tmp = "";
		// System.out.println("===统一下单===" + order.toxml());
		String result = httpPostRequest(UNIFIEDORDER_URL, "POST", order.toxml());
		// System.out.println("====统一下单返回===" + result);
		if (TextUtils.isEmpty(result))
			return null;
		// 组成完成的xml数据，提供给xmlutils去解析
		Map<String, String> map = XMLUtils.transXMLParams(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmls>" + result
						+ "</xmls>", "xml", new String[] { "return_code",
						"return_msg", "appid", "mch_id", "nonce_str", "sign",
						"result_code", "prepay_id", "trade_type" });
		if (!TextUtils.isEmpty(map.get("return_code"))) {
			tmp = map.get("return_code");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setReturn_code(tmp);
		}
		if (!TextUtils.isEmpty(map.get("return_msg"))) {
			tmp = map.get("return_msg");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setReturn_msg(tmp);
		}
		if (!TextUtils.isEmpty(map.get("appid"))) {
			tmp = map.get("appid");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setAppid(tmp);
		}
		if (!TextUtils.isEmpty(map.get("mch_id"))) {
			tmp = map.get("mch_id");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setMch_id(tmp);
		}
		if (!TextUtils.isEmpty(map.get("nonce_str"))) {
			tmp = map.get("nonce_str");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setNonce_str(tmp);
		}
		if (!TextUtils.isEmpty(map.get("sign"))) {
			tmp = map.get("sign");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setSign(tmp);
		}
		if (!TextUtils.isEmpty(map.get("result_code"))) {
			tmp = map.get("result_code");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setResult_code(tmp);
		}
		if (!TextUtils.isEmpty(map.get("prepay_id"))) {
			tmp = map.get("prepay_id");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setPrepay_id(tmp);
		}
		if (!TextUtils.isEmpty(map.get("trade_type"))) {
			tmp = map.get("trade_type");
			if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>"))
				tmp = tmp.substring(8, tmp.length() - 3);
			orderback.setTrade_type(tmp);
		}
		return orderback;
	}

	/**
	 * <p>
	 * 微信支付js-sdk接口
	 * </p>
	 *
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-1 下午1:21:22 Jack Exp $
	 */
	public static Map<String, String> getWxPayData(String prePayId, String wxid) {
		Map<String, String> payMap = new HashMap<String, String>();
		String ymdhms = String.valueOf(System.currentTimeMillis() / 1000);
		String nonceStr = createNonceStr();
		if (nonceStr.length() > 32)
			nonceStr = nonceStr.substring(0, 31);
		payMap.put("timestamp", ymdhms);
		payMap.put("nonceStr", nonceStr);
		// System.out.println("prePayId" + prePayId);
		payMap.put("package", "prepay_id=" + prePayId);
		payMap.put("signType", "MD5");
		// 给微信支付用的信息
		SortedMap<Object, Object> params = new TreeMap<Object, Object>();
		params.put("appId", WxConstants.wxMap.get(wxid).getAppID());
		params.put("timeStamp", ymdhms);
		params.put("nonceStr", nonceStr);
		params.put("package", "prepay_id=" + prePayId);
		params.put("signType", "MD5");
		// 进行支付签名
		String sign = createSign("UTF-8", params, WxConstants.wxMap.get(wxid)
				.getPayKey());
		payMap.put("paySign", sign);
		// System.out.println("paySign:" + sign);
		return payMap;
	}

	/**
	 * <p>
	 * 微信退款接口
	 * </p>
	 *
	 * @author Jack Zhou
	 * @version $Id: WeixinUtil.java,v 0.1 2015-10-14 下午11:43:29 Jack Exp $
	 */
	public static RefundBack wxtk(Map<String, String> datas, String wxid) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		String nonStr = DateUtil.getDateStr("yyyyMMddHHmmss");
		packageParams.put("appid", WxConstants.wxMap.get(wxid).getAppID());// 公众账号ID
		packageParams.put("mch_id", WxConstants.wxMap.get(wxid).getMacId());// 商户号
		packageParams.put("nonce_str", nonStr);// 随机字符串
		packageParams.put("out_trade_no", datas.get("out_trade_no"));// 商户订单号
		packageParams.put("out_refund_no", datas.get("out_refund_no"));// 商户退款单号
		packageParams.put("total_fee", datas.get("total_fee"));// 总金额
		packageParams.put("refund_fee", datas.get("refund_fee"));// 总金额
		packageParams.put("op_user_id", WxConstants.wxMap.get(wxid).getMacId());// 操作员,操作员帐号,
																				// 默认为商户号

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(WxConstants.wxMap.get(wxid).getAppID(),
				WxConstants.wxMap.get(wxid).getAppSecrect(), WxConstants.wxMap
						.get(wxid).getPayKey());

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>"
				+ WxConstants.wxMap.get(wxid).getAppID() + "</appid>"
				+ "<mch_id>" + WxConstants.wxMap.get(wxid).getMacId()
				+ "</mch_id>" + "<nonce_str>" + nonStr + "</nonce_str>"
				+ "<sign><![CDATA[" + sign + "]]></sign>" + "<out_trade_no>"
				+ datas.get("out_trade_no") + "</out_trade_no>"
				+ "<out_refund_no>" + datas.get("out_refund_no")
				+ "</out_refund_no>" + "<total_fee>" + datas.get("total_fee")
				+ "</total_fee>" + "<refund_fee>" + datas.get("refund_fee")
				+ "</refund_fee>" + "<op_user_id>"
				+ WxConstants.wxMap.get(wxid).getMacId() + "</op_user_id>"
				+ "</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			String s = ClientCustomSSL.doRefund(createOrderURL, xml, wxid);
			RefundBack back = XMLUtils.transXMLRefundBack(s);
			// System.out.println(s);
			return back;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String user_info_plurl = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

	public static List<Map<String, Object>> getSingPLUserInfo(
			String accessToken, String mess) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String requestUrl = user_info_plurl
				.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, "POST", mess);
		if (null == jsonObject)
			return list;
		JSONArray datas = jsonObject.getJSONArray("user_info_list");
		if (null == datas)
			return list;
		for (Object object : datas) {
			JSONObject obj = (JSONObject) object;
			if (null == obj) {
				System.out.println("====cuwu+++");
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("errcode", "0");
			map.put("errmsg", "");
			map.put("subscribe", obj.getString("subscribe"));
			map.put("openid", obj.getString("openid"));
			if (obj.has("nickname"))
				map.put("nickname", obj.getString("nickname").toString());
			else
				map.put("nickname", "");
			if (obj.has("sex"))
				map.put("sex", obj.getString("sex").toString());
			else
				map.put("sex", "");
			if (obj.has("city"))
				map.put("city", obj.getString("city").toString());
			else
				map.put("city", "");
			if (obj.has("country"))
				map.put("country", obj.getString("country").toString());
			else
				map.put("country", "");
			if (obj.has("province"))
				map.put("province", obj.getString("province").toString());
			else
				map.put("province", "");
			if (obj.has("language"))
				map.put("language", obj.getString("language").toString());
			else
				map.put("language", "");
			if (obj.has("headimgurl"))
				map.put("headimgurl", obj.getString("headimgurl").toString());
			else
				map.put("headimgurl", "");
			if (obj.has("subscribe_time"))
				map.put("subscribe_time", obj.getString("subscribe_time")
						.toString());
			else
				map.put("subscribe_time", "");
			if (obj.has("remark"))
				map.put("remark", obj.getString("remark").toString());
			else
				map.put("remark", "");
			if (obj.has("groupid"))
				map.put("groupid", obj.getString("groupid").toString());
			else
				map.put("groupid", "");
			list.add(map);
		}

		return list;
	}

	// 发送消息模版
	public static String send_temp_msg_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	public static TemplateMsg sendTemplateMsg(Map map, String accessToken) {
		int result = 0;
		TemplateMsg msg = new TemplateMsg();
		String url = send_temp_msg_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(map).toString();
		System.out.println("jsonMenu===:" + jsonMenu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		System.out.println(jsonObject);
		if (null != jsonObject) {
			if (jsonObject.has("errcode"))
				msg.setErrcode(jsonObject.getInt("errcode") + "");
			if (jsonObject.has("errmsg"))
				msg.setErrcode(jsonObject.getString("errmsg"));
			if (jsonObject.has("msgid"))
				msg.setMsgid(jsonObject.getLong("msgid") + "");
		}
		return msg;
	}

	public final static String comp_access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getCompAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = comp_access_token_url.replace("APPID", appid)
				.replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				System.out.println("json==" + jsonObject.toString());
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				e.printStackTrace();
				// 获取token失败 , jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg")
				System.out.println("获取企业号token失败 errcode:{} errmsg:{}");
			}
		}
		return accessToken;
	}

	public static String COMP_USER_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";

	public static WxUserModel getWxCompMemb(String accessToken, String code,
			String wxid) {
		WxUserModel user = null;
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(code))
			return null;

		String requestUrl = COMP_USER_CODE_URL.replace("ACCESS_TOKEN",
				accessToken).replace("CODE", code);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				user = new WxUserModel();
				if (jsonObject.has("UserId")) {
					user.setUserId(jsonObject.getString("UserId"));
				}
				if (jsonObject.has("DeviceId")) {
					user.setDeviceId(jsonObject.getString("DeviceId"));
				}
				if (jsonObject.has("OpenId")) {
					user.setOpenId(jsonObject.getString("OpenId"));
				}

			} catch (Exception e) {
				System.out.println("==code Error ==");
				e.printStackTrace();
				return null;
			}
		}
		return user;
	}

	public static String COMP_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

	/**
	 * 企业号发送文本消息-主页型应用
	 * 
	 * @param accessToken
	 * @param toUsers
	 * @param toPartys
	 * @param toTags
	 * @param content
	 * @return
	 */
	public static void sendWxCompTextMess(String accessToken, String toUsers,
			String toPartys, String toTags, String content, String agentid) {
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(content))
			return;
		String requestUrl = COMP_MESSAGE_URL.replace("ACCESS_TOKEN",
				accessToken);
		Map<String, Object> map = new HashMap<String, Object>(7);
		map.put("touser", toUsers);
		map.put("toparty", toPartys);
		map.put("totag", toTags);
		map.put("msgtype", "text");
		map.put("agentid", agentid);

		Map<String, Object> nr = new HashMap<String, Object>(1);
		nr.put("content", content);
		map.put("text", nr);
		map.put("safe", 0);
		String jsonMenu = JSONObject.fromObject(map).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
		System.out.println(jsonObject);
	}

	/**
	 * 发送图文消息，带链接跳转
	 * 
	 * @param accessToken
	 * @param toUsers
	 * @param toPartys
	 * @param toTags
	 * @param content
	 * @param agentid
	 * @param articles
	 */
	public static void sendWxCompPicMess(String accessToken, String toUsers,
			String toPartys, String toTags, int agentid, List<Articles> articles) {
		if (TextUtils.isEmpty(accessToken))
			return;
		String requestUrl = COMP_MESSAGE_URL.replace("ACCESS_TOKEN",
				accessToken);
		Map<String, Object> map = new HashMap<String, Object>(7);
		map.put("touser", toUsers);
		map.put("toparty", toPartys);
		map.put("totag", toTags);
		map.put("msgtype", "news");
		map.put("agentid", agentid);
		Map<String, List<Articles>> content = new HashMap<String, List<Articles>>();
		content.put("articles", articles);
		map.put("news", content);
		String jsonMenu = JSONObject.fromObject(map).toString();
		System.out.println(jsonMenu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
		System.out.println(jsonObject);
	}

	/**
	 * 企业号的userid和openid的转换
	 */
	public static String QY_USERID_OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN";

	public static String userId2Openid(String accessToken, String userId) {
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(userId))
			return "";
		String requestUrl = QY_USERID_OPENID.replace("ACCESS_TOKEN",
				accessToken);
		Map<String, Object> map = new HashMap<String, Object>(7);
		map.put("userid", userId);
		map.put("agentid", "");
		String jsonMenu = JSONObject.fromObject(map).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
		if (null != jsonObject) {
			if (jsonObject.has("openid"))
				return jsonObject.getString("openid");
		}
		return "";
	}

	/**
	 * 企业号成员信息
	 */
	public static String QY_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

	public static Map<String, String> qyUserInfo(String accessToken,
			String userId) {
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(userId))
			return null;
		String requestUrl = QY_USER_INFO.replace("ACCESS_TOKEN", accessToken)
				.replace("USERID", userId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		Map<String, String> map = new HashMap<String, String>();
		// 如果请求成功
		if (null != jsonObject) {
			if (jsonObject.has("userid"))
				map.put("userid", jsonObject.getString("userid"));
			if (jsonObject.has("name"))
				map.put("name", jsonObject.getString("name"));
			if (jsonObject.has("department"))
				map.put("department", jsonObject.getString("department"));
			if (jsonObject.has("position"))
				map.put("position", jsonObject.getString("position"));
			if (jsonObject.has("mobile"))
				map.put("mobile", jsonObject.getString("mobile"));
			if (jsonObject.has("gender"))
				map.put("gender", jsonObject.getString("gender"));
			if (jsonObject.has("email"))
				map.put("email", jsonObject.getString("email"));
			if (jsonObject.has("weixinid"))
				map.put("weixinid", jsonObject.getString("weixinid"));
			if (jsonObject.has("avatar"))
				map.put("avatar", jsonObject.getString("avatar"));
			if (jsonObject.has("status"))
				map.put("status", jsonObject.getString("status"));
			if (jsonObject.has("extattr"))
				map.put("extattr", jsonObject.getString("extattr"));
			return map;
		}
		return null;
	}

	// 获取企业号jssdk
	public static String jsticket_comp_message = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKE";

	public static JSTickets getQyTicket(String accessToken) {
		JSTickets jsticket = null;
		String requestUrl = jsticket_comp_message.replace("ACCESS_TOKE",
				accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		System.out.println(jsonObject);
		if (null != jsonObject) {
			try {
				jsticket = new JSTickets();
				jsticket.setTicket(jsonObject.getString("ticket"));
				jsticket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				System.out.println("获取企业号的ticket失败 errcode:{} errmsg:{}"
						+ e.getMessage());
			}
		}
		return jsticket;
	}

	// 上传多媒体文件到微信服务器
	public static final String upload_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	/**
	 * 上传多媒体文件到微信服务器<br>
	 * 
	 * @see http://www.oschina.net/code/snippet_1029535_23824
	 * @see http://mp.weixin.qq.com/wiki/index.php?title=上传下载多媒体文件
	 * 
	 * @author qincd
	 * @date Nov 6, 2014 4:11:22 PM
	 */
	public static JSONObject uploadMediaToWX(String filePath, String type,
                                             String accessToken) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}

		String url = upload_media_url.replace("ACCESS_TOKEN", accessToken)
				.replace("TYPE", type);
		URL urlObj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);

		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // ////////必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(head);

		// 文件正文部分
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		/**
		 * 读取服务器响应，必须读取,否则提交不成功
		 */
		try {
			// 定义BufferedReader输入流来读取URL的响应
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			conn.disconnect();

			return JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		return null;
	}

	public static final String download_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	/**
	 * 从微信服务器下载多媒体文件
	 * 
	 * @author qincd
	 * @date Nov 6, 2014 4:32:12 PM
	 */
	public static String downloadMediaFromWx(String accessToken,
			String mediaId, String fileSavePath) throws IOException {
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(mediaId))
			return null;

		String requestUrl = download_media_url.replace("ACCESS_TOKEN",
				accessToken).replace("MEDIA_ID", mediaId);
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		InputStream in = conn.getInputStream();

		File dir = new File(fileSavePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!fileSavePath.endsWith("/")) {
			fileSavePath += "/";
		}

		String ContentDisposition = conn.getHeaderField("Content-disposition");
		String weixinServerFileName = ContentDisposition.substring(
				ContentDisposition.indexOf("filename") + 10,
				ContentDisposition.length() - 1);
		fileSavePath += weixinServerFileName;
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(fileSavePath));
		byte[] data = new byte[1024];
		int len = -1;

		while ((len = in.read(data)) != -1) {
			bos.write(data, 0, len);
		}

		bos.close();
		in.close();
		conn.disconnect();

		return weixinServerFileName;
	}

	/**
	 * 企业号下载临时文件
	 */
	public static final String qy_download_media_url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	/**
	 * 从微信服务器下载多媒体文件
	 * 
	 * @author qincd
	 * @date Nov 6, 2014 4:32:12 PM
	 */
	public static String downloadQyMediaFromWx(String accessToken,
			String mediaId, String fileSavePath) throws IOException {
		if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(mediaId))
			return null;

		String requestUrl = qy_download_media_url.replace("ACCESS_TOKEN",
				accessToken).replace("MEDIA_ID", mediaId);
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		InputStream in = conn.getInputStream();

		File dir = new File(fileSavePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!fileSavePath.endsWith("/")) {
			fileSavePath += "/";
		}

		String ContentDisposition = conn.getHeaderField("Content-disposition");
		String weixinServerFileName = ContentDisposition.substring(
				ContentDisposition.indexOf("filename") + 10,
				ContentDisposition.length() - 1);
		fileSavePath += weixinServerFileName;
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(fileSavePath));
		byte[] data = new byte[1024];
		int len = -1;

		while ((len = in.read(data)) != -1) {
			bos.write(data, 0, len);
		}

		bos.close();
		in.close();
		conn.disconnect();
		return weixinServerFileName;
	}

	public static void main(String[] args) {
		AccessToken token = WeixinUtil
				.getCompAccessToken("wx4c18a056ebf26d95",
						"Ly5JTkfFV2EwlrRryxwwxagFueRWOk7xI-JWZjgOhQ6MmYTNYAQl99xg67e14_qD");
		// String openId = WeixinUtil.userId2Openid(token.getToken(), "zhou");
		Articles art = new Articles();
		art.setTitle("消息中心测试");
		art.setDescription("您有新的工作未处理");
		art.setPicurl("http://iteeio.zust.edu.cn/zybx/jsp/images/gsjj_banner.jpg");
		art.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4c18a056ebf26d95&redirect_uri=http%3A%2F%2Fiteeio.zust.edu.cn%2Fzybx%2FwxCompIndex!shouyeGsgl.htm&response_type=code&scope=SCOPE&state=wx02#wechat_redirect");

		List<Articles> ats = new ArrayList<Articles>();
		ats.add(art);

		WeixinUtil.sendWxCompPicMess(token.getToken(), "zhou", "", "", 4, ats);
		// Map<String, String> map = WeixinUtil.qyUserInfo(token.getToken(),
		// "zhou");
		// Set set = map.keySet();
		// for (Object object : set) {
		// System.out.println(object + "=" + map.get(object.toString()));
		// }

		// AccessToken keys = getCompAccessToken("wx4c18a      056ebf26d95",
		// "Ly5JTkfFV2EwlrRryxwwxagFueRWOk7xI-JWZjgOhQ6MmYTNYAQl99xg67e14_qD");
		// System.out.println(keys.getToken());
		// sendWxCompTextMess(keys.getToken(), "zhou", "", "",
		// "你有新的保险投保咨询，请进入应用查看", "5");

		// Map<String, String> datas = new HashMap<String, String>();
		// datas.put("out_trade_no", "B4F9B07695F04765B918C390A3D5B205");
		// datas.put("out_refund_no", "123123");
		// datas.put("total_fee", "1");
		// datas.put("refund_fee", "1");
		// WeixinUtil.wxtk(datas);
		// Date d
		// Date d = new Date();

		// System.out.println(WeixinUtil.transferLongToDate("yyyy-MM-dd HH:mm:ss",
		// new Long(1434093047)));
		// String appid = "wx5c3a1a91cf6d93c3";
		// String appSecrect = "c578656d53b4f16d121e032a05175f53";
		// String token = WeixinUtil.getAccessStringToken(appid, appSecrect);
		// Map<String, Object> map = WeixinUtil.getUser(token, "");
		// List list = (List) map.get("data");
		// for (Object object : list) {
		// Map<String, Object> _map = WeixinUtil.getSingUserInfo(token,
		// object.toString());
		// System.out.println(_map.get("nickname"));
		// System.out.println(_map.get("headimgurl"));
		// }

		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("touser", "oS7wGswsk1QVKyrygX0bCtP07H90");
		// map.put("template_id",
		// "1d1rilsq-EtQSZhiz5cP_WojsickAOU9TWgFfUfohIQ");
		// map.put("url", "http://www.163.com");
		// map.put("topcolor", "#FF0000");
		// Map<String,Map<String,String>> valMap = new
		// HashMap<String,Map<String,String>>();
		// Map<String,String> firstMap = new HashMap<String,String>();
		// firstMap.put("value", "代办任务提醒");
		// firstMap.put("color", "#173177");
		// valMap.put("first", firstMap);
		//
		// Map<String,String> keyword1 = new HashMap<String,String>();
		// keyword1.put("value", "代办任务提醒1");
		// keyword1.put("color", "#173177");
		// valMap.put("keyword1", keyword1);
		// Map<String,String> keyword2 = new HashMap<String,String>();
		// keyword2.put("value", "代办任务提醒2");
		// keyword2.put("color", "#173177");
		// valMap.put("keyword2", keyword2);
		// Map<String,String> remark = new HashMap<String,String>();
		// keyword2.put("value", "代办任务提醒sd1");
		// keyword2.put("color", "#173177");
		// valMap.put("remark", remark);
		// map.put("data",valMap);
		// TemplateMsg msg = WeixinUtil.sendTemplateMsg(map, token);
		// System.out.println(msg.getErrmsg());
		//
		// String s =
		// "{\"remark\":{\"color\":\"#173177\",\"value\":\"代办任务提醒1\"},\"keyword1\":{\"color\":\"#173177\",\"value\":\"代办任务提醒1\"},\"keyword2\":{\"color\":\"#173177\",\"value\":\"代办任务提醒sd1\"},\"first\":{\"color\":\"#173177\",\"value\":\"代办任务提醒\"}}";
		// JSONObject json = JSONObject.fromObject(s);
		// Map<String, MorphDynaBean> map = (Map<String, MorphDynaBean>)
		// json.toBean(json, Map.class);
		// Set keys = map.keySet();
		// for (Object object : keys) {
		// System.out.println(object.toString());
		// MorphDynaBean _map = map.get(object.toString());
		// System.out.println(_map.get("color"));
		// System.out.println(_map.get("value"));
		// }

	}
}