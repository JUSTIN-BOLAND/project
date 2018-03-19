package com.deyi.vo;


/**
 * 
 * 二维码支付被扫请求参数值对象
 *
 */
public class QRCodePay {
	
	private String version; //版本号
	private String encoding;//编码方式
	private String certId;//证书ID
	private String signature;//签名
	private String signMethod;//签名方法
	private String txnType;//交易类型
	private String txnSubType;//交易子类
	private String bizType;//产品类型
	private String channelType;//渠道类型
	private String backUrl;//后台通知地址
	private String accessType;//接入类型
	private String merId;//商户代码
	private String termId;//终端号
	private String qrNo;//C2B码
	private String subMerId;//二级商户代码,可选 可选 商户类型为平台类商户接入时必须上送
	private String subMerName;//二级商户名称，可选 商户类型为平台类商户接入时必须上送
	private String orderId;//商户订单号
	private String txnTime;//订单发送时间
	private String txnAmt;//交易金额，单位分
	private String currencyCode;//交易币种，默认156
	private String reqReserved;//请求保留域，商户自定义保留域，交易应答时会原样返回.
	private String reserved;//保留域，可空
	private String riskRateInfo;//风险信息域，可空
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
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
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
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getQrNo() {
		return qrNo;
	}
	public void setQrNo(String qrNo) {
		this.qrNo = qrNo;
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
	public String getRiskRateInfo() {
		return riskRateInfo;
	}
	public void setRiskRateInfo(String riskRateInfo) {
		this.riskRateInfo = riskRateInfo;
	}
	
	
	

}
