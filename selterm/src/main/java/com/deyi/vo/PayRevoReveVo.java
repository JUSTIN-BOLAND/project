package com.deyi.vo;


/**
 * 
 * 二维码扫码支付消费撤销值对象（被扫），退货类：消费撤销,同步应答
 *
 */
public class PayRevoReveVo {
	private String version; //版本号
	private String encoding;//编码方式
	private String signature;//签名
	private String signMethod;//签名方法
	private String txnType;//交易类型
	private String txnSubType;//交易子类
	private String bizType;//产品类型
	private String accessType;//接入类型
	private String merId;//商户代码
	private String orderId;//商户订单号
	private String origQryId;//原始交易流水号
	private String txnTime;//订单发送时间
	private String txnAmt;//交易金额，单位分
	private String reqReserved;//请求保留域，商户自定义保留域，交易应答时会原样返回.
	private String reserved;//保留域，可空
	private String queryId;//银联交易流水号
	private String respCode;//响应码
	private String respMsg;//应答信息
	private String signPubKeyCert;//签名公钥证书,使用RSA签名方式时必选，此域填写银联签名公钥证书
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnSubType() {
		return txnSubType;
	}
	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrigQryId() {
		return origQryId;
	}
	public void setOrigQryId(String origQryId) {
		this.origQryId = origQryId;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getQueryId() {
		return queryId;
	}
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getSignPubKeyCert() {
		return signPubKeyCert;
	}
	public void setSignPubKeyCert(String signPubKeyCert) {
		this.signPubKeyCert = signPubKeyCert;
	}
	
	
	
}
