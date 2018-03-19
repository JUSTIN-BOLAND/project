package com.deyi.vo;

/**
 * 银联主扫，申请二维码值对象
 * @author tongbiao
 *
 */
public class UnionQRCode {
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
	 * 渠道类型
	 */
	private String channelType;
	/**
	 * 接入类型
	 */
	private String accessType;
	/**
	 * 商户代码
	 */
	private String merId;
	/**
	 * 二级商户代码
	 */
	private String subMerId;
	/**
	 * 二级商户全称
	 */
	private String subMerName;
	/**
	 * 二级商户简称
	 */
	private String subMerAbbr;
	/**
	 * 商户订单号
	 */
	private String orderId;
	/**
	 * 订单发送时间
	 */
	private String txnTime;
	/**
	 * 交易金额,单位分
	 */
	private String txnAmt;
	/**
	 * 交易币种
	 */
	private String currencyCode;
	/**
	 * 收款方信息
	 */
	private String payeeVerifiInfo;
	/**
	 * 二维码有效时间
	 */
	private String qrValidTime;
	/**
	 * 支付次数限制
	 */
	private String limitCount;
	/**
	 * 收款方附言
	 */
	private String payeeComments;
	/**
	 * 请求方保留域
	 */
	private String reqReserved;
	/**
	 * 保留域
	 */
	private String reserved;
	/**
	 * 交易通知地址
	 */
	private String backUrl;
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
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
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
	public String getSubMerId() {
		return subMerId;
	}
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}
	public String getSubMerName() {
		return subMerName;
	}
	public void setSubMerName(String subMerName) {
		this.subMerName = subMerName;
	}
	public String getSubMerAbbr() {
		return subMerAbbr;
	}
	public void setSubMerAbbr(String subMerAbbr) {
		this.subMerAbbr = subMerAbbr;
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
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getPayeeVerifiInfo() {
		return payeeVerifiInfo;
	}
	public void setPayeeVerifiInfo(String payeeVerifiInfo) {
		this.payeeVerifiInfo = payeeVerifiInfo;
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
	public String getPayeeComments() {
		return payeeComments;
	}
	public void setPayeeComments(String payeeComments) {
		this.payeeComments = payeeComments;
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
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	
	
	
	
}
