package com.merit.utils.weixin.model;

import java.io.Serializable;

/**
 * <p>
 * 微信退款返回类
 * </p>
 * 
 * @author Jack Zhou
 * @version $Id: RefundBack.java,v 0.1 2015-10-19 上午11:33:05 Jack Exp $
 */
public class RefundBack implements Serializable {

	/**    */
	private static final long serialVersionUID = 7140437126433945168L;
	private String return_code;// 错误代码
	private String return_msg;// 错误代码描述
	private String appid;// 公众账号ID
	private String mch_id;// 商户号
	private String nonce_str;// 随机字符串
	private String sign;// 签名
	private String out_trade_no;// 商户订单号
	private String result_code;// 业务结果SUCCESS退款申请接收成功，结果通过退款查询接口查询FAIL 提交业务失败
	private String refund_channel;// 退款渠道
	private String transaction_id;// 微信订单号
	private String out_refund_no;// 商户退款单号
	private String refund_id;// 微信退款单号
	private String coupon_refund_fee;// 代金券或立减优惠退款金额
	private String total_fee;// 订单总金额
	private String cash_fee;// 现金支付金额
	private String refund_fee;// 退款金额
	private String coupon_refund_count;// 代金券或立减优惠使用数量
	private String cash_refund_fee;// 代金券或立减优惠退款金额
	private String err_code;// 代金券或立减优惠退款金额
	private String err_code_des;// 代金券或立减优惠退款金额
	
	

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getRefund_channel() {
		return refund_channel;
	}

	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(String coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public void setCoupon_refund_count(String coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}

	public String getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(String cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: RefundBack.java,v 0.1 2015-10-19 上午11:33:03 Jack Exp $
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
