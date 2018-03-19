package com.deyi.vo;


/**
 * 银联主扫，申请二维码应答报文值对象
 *
 *
 */
public class UnionQRCodeRece {
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 编码方式
	 */
	private String encoding;
	/**
	 * 证书ID
	 */
	private String certId;
	/**
	 * 签名
	 */
	private String signature;
	/**
	 * 签名方法
	 */
	private String signMethod;
	/**
	 * 交易类型
	 */
	private String txnType;
	/**
	 * 交易子类
	 */
	private String txnSubType;
	/**
	 * 产品类型
	 */
	private String bizType;
	/**
	 * 接入类型
	 */
	private String accessType;
	/**
	 * 商户代码
	 */
	private String merId;
	/**
	 * 商户订单号
	 */
	private String orderId;
	/**
	 * 订单发送时间
	 */
	private String txnTime;
	/**
	 * 请求方保留域
	 */
	private String reqReserved;
	/**
	 * 保留域
	 */
	private String reserved;
	/**
	 * 响应码
	 */
	private String respCode;
	/**
	 * 应答信息
	 */
	private String respMsg;
	/**
	 * 二维码
	 */
	private String qrCode;
	/**
	 * 二维码有效时间
	 */
	private String qrValidTime;
	/**
	 * 支付次数限制
	 */
	private String limitCount;
	/**
	 * 签名公钥证书
	 */
	private String signPubKeyCert;
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
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
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
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
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
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getQrValidTime() {
		return qrValidTime;
	}
	public void setQrValidTime(String qrValidTime) {
		this.qrValidTime = qrValidTime;
	}
	public String getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}
	public String getSignPubKeyCert() {
		return signPubKeyCert;
	}
	public void setSignPubKeyCert(String signPubKeyCert) {
		this.signPubKeyCert = signPubKeyCert;
	}
	
	
	
	
}
