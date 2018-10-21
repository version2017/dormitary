package com.merit.utils.weixin.util;

import com.merit.utils.weixin.model.PayFeedBack;
import com.merit.utils.weixin.model.RefundBack;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组装返回的xml文件
 * </p>
 * 
 * @author Jack Zhou
 * @version $Id: XMLUtils.java,v 0.1 Feb 14, 2011 10:36:51 AM Jack Exp $
 */
public class XMLUtils {

	public final static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; // xml

	// 文件头

	/**
	 * <p>
	 * 分析客户端传入的xml数据，解析以下内容：
	 * imei，imsi，phoneno，sessionid，user，password，applicationid
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: XMLUtils.java,v 0.1 Feb 15, 2011 4:09:03 PM Jack Exp $
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> transXMLParams(String xml, String rootName, String[] nodes) {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();// 获得文档根节点
			List<Element> eles = root.elements(rootName);
			Element _ele = null;
			for (Element ele : eles) {
				for (int i = 0; i < nodes.length; i++) {
					_ele = ele.element(nodes[i]);
					if (null != _ele)
						map.put(nodes[i], _ele.getText());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * <p>
	 * 解析微信返回的支付xml数据，把数据放入到 payFeeBack 对象内。
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: XMLUtils.java,v 0.1 2015-10-13 下午9:56:55 Jack Exp $
	 */
	public static PayFeedBack transXMLPayFeedBack(String xml) {
		PayFeedBack back = new PayFeedBack();
		Document doc = null;
		try {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmls>" + xml + "</xmls>";
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();// 获得文档根节点
			List<Element> eles = root.elements("xml");
			Element _ele = null;
			String tmp = "";
			for (Element ele : eles) {
				_ele = ele.element("appid");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setAppId(tmp);

				_ele = ele.element("bank_type");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setBank_type(tmp);

				_ele = ele.element("cash_fee");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setCash_fee(tmp);

				_ele = ele.element("device_info");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setDevice_info(tmp);

				_ele = ele.element("fee_type");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setFee_type(tmp);

				_ele = ele.element("is_subscribe");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setIs_subscribe(tmp);

				_ele = ele.element("mch_id");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setMch_id(tmp);

				_ele = ele.element("nonce_str");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setNonce_str(tmp);

				_ele = ele.element("openid");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setOpenid(tmp);

				_ele = ele.element("out_trade_no");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setOut_trade_no(tmp);

				_ele = ele.element("result_code");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setResult_code(tmp);

				_ele = ele.element("return_code");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setReturn_code(tmp);

				_ele = ele.element("sign");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setSign(tmp);

				_ele = ele.element("time_end");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setTime_end(tmp);

				_ele = ele.element("total_fee");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setTotal_fee(tmp);

				_ele = ele.element("trade_type");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setTrade_type(tmp);

				_ele = ele.element("transaction_id");
				tmp = _ele.getText();
				if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
					tmp = tmp.substring(8, tmp.length() - 3);
				}
				back.setTransaction_id(tmp);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return back;
	}

	/**
	 * <p>
	 * 解析退款返回
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: XMLUtils.java,v 0.1 2015-10-19 上午11:32:30 Jack Exp $
	 */
	public static RefundBack transXMLRefundBack(String xml) {
		RefundBack back = new RefundBack();
		Document doc = null;
		try {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmls>" + xml + "</xmls>";
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();// 获得文档根节点
			List<Element> eles = root.elements("xml");
			Element _ele = null;
			String tmp = "";
			for (Element ele : eles) {
				_ele = ele.element("return_code");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setReturn_code(tmp);
				}
				_ele = ele.element("return_msg");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setReturn_msg(tmp);
				}
				_ele = ele.element("appid");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setAppid(tmp);
				}
				_ele = ele.element("mch_id");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setMch_id(tmp);
				}
				_ele = ele.element("nonce_str");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setNonce_str(tmp);
				}
				_ele = ele.element("sign");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setSign(tmp);
				}
				_ele = ele.element("out_trade_no");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setOut_trade_no(tmp);
				}
				_ele = ele.element("result_code");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setResult_code(tmp);
				}
				_ele = ele.element("refund_channel");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setRefund_channel(tmp);
				}
				_ele = ele.element("transaction_id");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setTransaction_id(tmp);
				}
				_ele = ele.element("out_refund_no");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setOut_refund_no(tmp);
				}
				_ele = ele.element("refund_id");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setRefund_id(tmp);
				}
				_ele = ele.element("coupon_refund_fee");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setCoupon_refund_fee(tmp);
				}
				_ele = ele.element("total_fee");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setTotal_fee(tmp);
				}
				_ele = ele.element("cash_fee");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setCash_fee(tmp);
				}
				_ele = ele.element("refund_fee");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setRefund_fee(tmp);
				}

				_ele = ele.element("coupon_refund_count");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setCoupon_refund_count(tmp);
				}
				_ele = ele.element("cash_refund_fee");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setCash_refund_fee(tmp);
				}
				_ele = ele.element("err_code_des");
				if (null != _ele) {
					tmp = _ele.getText();
					if (tmp.startsWith("<![CDATA[") && tmp.endsWith("]]>")) {
						tmp = tmp.substring(8, tmp.length() - 3);
					}
					back.setErr_code_des(tmp);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return back;
	}

	/**
	 * <p>
	 * 组装返回给微信服务器
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: XMLUtils.java,v 0.1 2015-10-13 下午11:11:09 Jack Exp $
	 */
	public static String paySuccess(String returnCode, String returnMsg) {
		String s = "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnCode + "]]></return_msg></xml>";
		return s;
	}

	/**
	 * <p>
	 * 判断是否是完整的xml文件
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: XMLUtils.java,v 0.1 Feb 15, 2011 9:33:03 AM Jack Exp $
	 */
	public static boolean checkXML(String xml) {
		boolean result = false;
		try {
			@SuppressWarnings("unused")
            Document doc = DocumentHelper.parseText(xml);
			result = true;
		} catch (DocumentException e) {
			// 转换错误，不是正确的xml格式
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public static void main(String[] args) {
		// String xml =
		// "<?xml version=\"1.0\" encoding=\"UTF-8\"?><params><param><imei>000000000000000</imei><imsi>310260000000000</imsi><phoneno>15555215554</phoneno><sessionid>BF76NMSF9876123WR77834</sessionid><user>user2</user><password>1</password><applicationid>001</applicationid><objs><obj><name>com.boolar.model.AppBoolarDuit</name><cols><col><name>createtime</name><type>java.util.Date</type><value>Fri Jun 17 21:50:46 Asia/Taipei 2011</value></col><col><name>id</name><type>java.lang.String</type><value>dt_20110617215007</value></col><col><name>des</name><type>java.lang.String</type><value>r</value></col><col><name>appId</name><type>java.lang.String</type><value>RF3845700</value></col><col><name>heigh</name><type>java.lang.Double</type><value>3.0</value></col><col><name>width</name><type>java.lang.Double</type><value>2.0</value></col><col><name>length</name><type>java.lang.Double</type><value>1.0</value></col><col><name>user</name><type>java.lang.String</type><value>user2</value></col></cols></obj></objs></param></params>";
		// System.out.println(XMLUtils.checkXML(xml));
		// String xml =
		// "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmls><xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx469baf1fcaa81a80]]></appid><mch_id><![CDATA[1269898201]]></mch_id><device_info><![CDATA[WEB]]></device_info><nonce_str><![CDATA[OAspAPSTXKk3A0q0]]></nonce_str><sign><![CDATA[4F104F0D629332839CF3C86DE59065D2]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx20151013192528afea25bc9e0781362915]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml></xmls>";

		// Map<String, String> map = XMLUtils.transXMLParams(xml, "xml", new
		// String[] { "return_code", "return_msg", "appid", "mch_id",
		// "nonce_str", "sign", "result_code", "prepay_id", "trade_type" });
		String s1 = "2015-10-10 11:11:11";
		String s2 = "2015-10-16 23:23:23";
//		System.out.println(DateUtil.getBetweenMinutesYMDHMS(s1, s2));
	}

}
