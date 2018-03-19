package com.deyi.vo;

/**
 * 查询
 * pure
 * @author hejx
 * @2017年4月28日
 */
public class PayQueryVO {
	private String merId;//商户代码
	private String orderId;//商户订单号
	private String txnTime;//订单发送时间
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
	
}
