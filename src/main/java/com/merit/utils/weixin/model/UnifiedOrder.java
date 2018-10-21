package com.merit.utils.weixin.model;


import com.merit.utils.TextUtils;

import java.io.Serializable;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>
 * 统一下单接口
 * </p>
 * 
 * @author Jack Zhou
 * @version $Id: UnifiedOrder.java,v 0.1 2015-9-30 下午11:55:03 Jack Exp $
 */
public class UnifiedOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appid;// 公众账号ID
	private String mch_id;// 商户号
	private String nonce_str;// 随机字符串
	private String sign;// 签名
	private String body;// 商品描述
	private String out_trade_no;// 商户订单号
	private int total_fee;// 总金额
	private String spbill_create_ip;// 终端IP
	private String notify_url;// 通知地址
	private String trade_type;// 交易类型取值如下：JSAPI，NATIVE，APP，WAP,
	private String openid;// 用户标识

	// option
	private String device_info;// 设备号
	private String detail;// 商品详情
	private String attach;// 附加数据
	private String fee_type;// 货币类型
	private String time_start;// 交易起始时间
	private String time_expire;// 交易结束时间
	private String goods_tag;// 商品标记
	private String product_id;// 商品ID
	private String limit_pay;// 指定支付方式

	/**
	 * <p>
	 * 生成统一下单的xml文件
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: UnifiedOrder.java,v 0.1 2015-10-1 上午12:34:43 Jack Exp $
	 */
	public String toxml() {
		String back = "<xml>";
		if (!TextUtils.isEmpty(this.appid))
			back += " <appid><![CDATA[" + this.appid + "]]></appid>";
		if (!TextUtils.isEmpty(this.mch_id))
			back += " <mch_id><![CDATA[" + this.mch_id + "]]></mch_id>";
		if (!TextUtils.isEmpty(this.openid))
			back += " <openid><![CDATA[" + this.openid + "]]></openid>";
		if (!TextUtils.isEmpty(this.nonce_str))
			back += " <nonce_str><![CDATA[" + this.nonce_str
					+ "]]></nonce_str>";
		if (!TextUtils.isEmpty(this.sign))
			back += " <sign><![CDATA[" + this.sign + "]]></sign>";
		if (!TextUtils.isEmpty(this.body))
			back += " <body><![CDATA[" + this.body + "]]></body>";
		if (!TextUtils.isEmpty(this.out_trade_no))
			back += " <out_trade_no><![CDATA[" + this.out_trade_no
					+ "]]></out_trade_no>";
		back += " <total_fee><![CDATA[" + this.total_fee + "]]></total_fee>";
		if (!TextUtils.isEmpty(this.spbill_create_ip))
			back += " <spbill_create_ip><![CDATA[" + this.spbill_create_ip
					+ "]]></spbill_create_ip>";
		if (!TextUtils.isEmpty(this.notify_url))
			back += " <notify_url><![CDATA[" + this.notify_url
					+ "]]></notify_url>";
		if (!TextUtils.isEmpty(this.trade_type))
			back += " <trade_type><![CDATA[" + this.trade_type
					+ "]]></trade_type>";
		if (!TextUtils.isEmpty(this.device_info))
			back += " <device_info><![CDATA[" + this.device_info
					+ "]]></device_info>";
		if (!TextUtils.isEmpty(this.detail))
			back += " <detail><![CDATA[" + this.detail + "]]></detail>";
		if (!TextUtils.isEmpty(this.attach))
			back += " <attach><![CDATA[" + this.attach + "]]></attach>";
		if (!TextUtils.isEmpty(this.fee_type))
			back += " <fee_type><![CDATA[" + this.fee_type + "]]></fee_type>";
		if (!TextUtils.isEmpty(this.time_start))
			back += " <time_start><![CDATA[" + this.time_start
					+ "]]></time_start>";
		if (!TextUtils.isEmpty(this.time_expire))
			back += " <time_expire><![CDATA[" + this.time_expire
					+ "]]></time_expire>";
		if (!TextUtils.isEmpty(this.goods_tag))
			back += " <goods_tag><![CDATA[" + this.goods_tag
					+ "]]></goods_tag>";
		if (!TextUtils.isEmpty(this.product_id))
			back += " <product_id><![CDATA[" + this.product_id
					+ "]]></product_id>";
		if (!TextUtils.isEmpty(this.limit_pay))
			back += " <limit_pay><![CDATA[" + this.limit_pay
					+ "]]></limit_pay>";
		back += "</xml>";
		return back;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		if (!TextUtils.isEmpty(this.appid))
			result += "appid=" + this.appid + "&";
		if (!TextUtils.isEmpty(this.attach))
			result += "attach=" + this.attach + "&";
		if (!TextUtils.isEmpty(this.body))
			result += "body=" + this.body + "&";
		if (!TextUtils.isEmpty(this.detail))
			result += "detail=" + this.detail + "&";
		if (!TextUtils.isEmpty(this.device_info))
			result += "device_info=" + this.device_info + "&";
		if (!TextUtils.isEmpty(this.fee_type))
			result += "fee_type=" + this.fee_type + "&";
		if (!TextUtils.isEmpty(this.goods_tag))
			result += "goods_tag=" + this.goods_tag + "&";

		if (!TextUtils.isEmpty(this.limit_pay))
			result += "limit_pay=" + this.limit_pay + "&";

		if (!TextUtils.isEmpty(this.mch_id))
			result += "mch_id" + this.mch_id + "&";
		if (!TextUtils.isEmpty(this.nonce_str))
			result += "nonce_str" + this.nonce_str + "&";
		if (!TextUtils.isEmpty(this.notify_url))
			result += "notify_url=" + this.notify_url + "&";
		if (!TextUtils.isEmpty(this.openid))
			result += "openid=" + this.openid + "&";
		if (!TextUtils.isEmpty(this.out_trade_no))
			result += "out_trade_no=" + this.out_trade_no + "&";
		if (!TextUtils.isEmpty(this.product_id))
			result += "product_id=" + this.product_id + "&";
		if (!TextUtils.isEmpty(this.sign))
			result += "sign" + this.sign + "&";
		if (!TextUtils.isEmpty(this.spbill_create_ip))
			result += "spbill_create_ip=" + this.spbill_create_ip + "&";
		if (!TextUtils.isEmpty(this.time_expire))
			result += "time_expire=" + this.time_expire + "&";
		if (!TextUtils.isEmpty(this.time_start))
			result += "time_start=" + this.time_start + "&";
		result += "total_fee=" + this.total_fee + "&";
		if (!TextUtils.isEmpty(this.trade_type))
			result += "trade_type=" + this.trade_type + "&";

		System.out.println("result" + result);
		return result;
	}

	/**
	 * 组成按照 key的ascII从小打到排列
	 * 
	 * @return
	 */
	public SortedMap<Object, Object> getSortMap() {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		if (!TextUtils.isEmpty(this.appid))
			parameters.put("appid", this.appid);
		if (!TextUtils.isEmpty(this.attach))
			parameters.put("attach", this.attach);
		if (!TextUtils.isEmpty(this.body))
			parameters.put("body", this.body);
		if (!TextUtils.isEmpty(this.detail))
			parameters.put("detail", this.detail);
		if (!TextUtils.isEmpty(this.device_info))
			parameters.put("device_info", this.device_info);
		if (!TextUtils.isEmpty(this.fee_type))
			parameters.put("fee_type", this.fee_type);
		if (!TextUtils.isEmpty(this.goods_tag))
			parameters.put("goods_tag", this.goods_tag);
		if (!TextUtils.isEmpty(this.limit_pay))
			parameters.put("limit_pay", this.limit_pay);
		if (!TextUtils.isEmpty(this.mch_id))
			parameters.put("mch_id", this.mch_id);
		if (!TextUtils.isEmpty(this.nonce_str))
			parameters.put("nonce_str", this.nonce_str);
		if (!TextUtils.isEmpty(this.notify_url))
			parameters.put("notify_url", this.notify_url);
		if (!TextUtils.isEmpty(this.openid))
			parameters.put("openid", this.openid);
		if (!TextUtils.isEmpty(this.out_trade_no))
			parameters.put("out_trade_no", this.out_trade_no);
		if (!TextUtils.isEmpty(this.product_id))
			parameters.put("product_id", this.product_id);
		if (!TextUtils.isEmpty(this.sign))
			parameters.put("sign", this.sign);
		if (!TextUtils.isEmpty(this.spbill_create_ip))
			parameters.put("spbill_create_ip", this.spbill_create_ip);
		if (!TextUtils.isEmpty(this.time_expire))
			parameters.put("time_expire", this.time_expire);
		if (!TextUtils.isEmpty(this.time_start))
			parameters.put("time_start", this.time_start);
		parameters.put("total_fee", this.total_fee);
		if (!TextUtils.isEmpty(this.trade_type))
			parameters.put("trade_type", this.trade_type);

		return parameters;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: UnifiedOrder.java,v 0.1 2015-9-30 下午11:55:00 Jack Exp $
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
