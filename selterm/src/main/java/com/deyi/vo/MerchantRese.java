package com.deyi.vo;


/**
 * 
 * 二维码支付被扫异步应答值对象
 *
 */
public class MerchantRese {
	private String version; //版本号
	private String encoding;//编码方式
	private String signature;//签名
	private String signMethod;//签名方法
	private String txnType;//交易类型
	private String txnSubType;//交易子类
	private String bizType;//产品类型
	private String accessType;//接入类型
	private String merId;//商户代码
	private String qrNo;//C2B码
	private String orderId;//商户订单号
	private String txnTime;//订单发送时间
	private String txnAmt;//交易金额
	private String currencyCode;//交易币种
	private String reqReserved;//请求保留域
	private String reserved;//保留域
	private String queryId;//交易查询流水号
	private String respCode;//响应码
	private String respMsg;//响应信息
	private String settleAmt;//清算金额
	private String settleCurrencyCode;//清算币种
	private String settleDate;//清算日期
	private String traceNo;//系统跟踪号
	private String traceTime;//交易传输时间
	private String exchangeDate;//兑换日期
	private String exchangeRate;//汇率
	private String accNo;//账号
	private String payCardIssueName;//支付卡名称
	private String payCardType;//支付卡类型
	private String payType;//支付方式
	private String signPubKeyCert;//签名公钥证书
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
	public String getQrNo() {
		return qrNo;
	}
	public void setQrNo(String qrNo) {
		this.qrNo = qrNo;
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
	public String getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}
	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getTraceTime() {
		return traceTime;
	}
	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getPayCardIssueName() {
		return payCardIssueName;
	}
	public void setPayCardIssueName(String payCardIssueName) {
		this.payCardIssueName = payCardIssueName;
	}
	public String getPayCardType() {
		return payCardType;
	}
	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getSignPubKeyCert() {
		return signPubKeyCert;
	}
	public void setSignPubKeyCert(String signPubKeyCert) {
		this.signPubKeyCert = signPubKeyCert;
	}
	
	
	
}
