/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.merit.utils.weixin.model;

public class PayFeedBack {

	public static final String MSGTYPE_REQUEST = "request";
	public static final String MSGTYPE_CONFIRM = "confirm";
	public static final String MSGTYPE_REJECT = "reject";

	private String OpenId; // 用户openid
	private String AppId; // appid
	private long TimeStamp; // 时间戳
	private String MsgType; // 通知类型： request 用户提交投诉 ， confirm 用户确认消除 投诉， reject
							// 用户拒绝消除投诉
	private String Phone; // 电话
	private String FeedBackId; // 投诉单号
	private String TransId; // 交易订单号
	private String Reason; // 用户投诉原因
	private String Solution; // 用户希望解决方案
	private String ExtInfo; // 备注信息
	private String AppSignature; // 签名；字段来源：对前面的其他字段与 appKey按照字典序排序后，使用 SHA1
									// 算法得到的结果。由商户生成后传入。
	private String SignMethod; // sha1
	private String bank_type;// 付款银行
	private String cash_fee;// 付款金额
	private String device_info;// 付款金额
	private String fee_type;
	private String is_subscribe;
	private String mch_id;
	private String nonce_str;
	private String openid;

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	private String out_trade_no;
	private String result_code;
	private String return_code;
	private String sign;
	private String time_end;
	private String total_fee;
	private String trade_type;
	private String transaction_id;

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getOpenId() {
		return OpenId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}

	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
	}

	public long getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		TimeStamp = timeStamp;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getFeedBackId() {
		return FeedBackId;
	}

	public void setFeedBackId(String feedBackId) {
		FeedBackId = feedBackId;
	}

	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getSolution() {
		return Solution;
	}

	public void setSolution(String solution) {
		Solution = solution;
	}

	public String getExtInfo() {
		return ExtInfo;
	}

	public void setExtInfo(String extInfo) {
		ExtInfo = extInfo;
	}

	public String getAppSignature() {
		return AppSignature;
	}

	public void setAppSignature(String appSignature) {
		AppSignature = appSignature;
	}

	public String getSignMethod() {
		return SignMethod;
	}

	public void setSignMethod(String signMethod) {
		SignMethod = signMethod;
	}

	@Override
	public String toString() {
		return "WeChatFeedBackDto [OpenId=" + OpenId + ", AppId=" + AppId + ", TimeStamp=" + TimeStamp + ", MsgType=" + MsgType + ", Phone=" + Phone + ", FeedBackId=" + FeedBackId + ", TransId="
				+ TransId + ", Reason=" + Reason + ", Solution=" + Solution + ", ExtInfo=" + ExtInfo + ", AppSignature=" + AppSignature + ", SignMethod=" + SignMethod + "]";
	}
}