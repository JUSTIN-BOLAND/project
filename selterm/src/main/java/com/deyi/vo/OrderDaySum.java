package com.deyi.vo;

import java.io.Serializable;

public class OrderDaySum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6749615264017110318L;
	private double paymoney;   //支付金额
	private int count;		   //支付笔数
	private double refundMoney;//退款金额
	private double sumratemoney; // 费率金额
	private int refundcount;   //退款数量
	
	private String merids;
	private String paytype;		// null 统计所有的，  2 微信，1.支付宝
	
	private String storeid;
	
	private String startDate;
	
	private String endDate;
	private String orgid;

	public double getNeedRateMoney() {
		return needRateMoney;
	}

	public void setNeedRateMoney(double needRateMoney) {
		this.needRateMoney = needRateMoney;
	}

	private double needRateMoney;
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	private String payStatus;
	
	/**
	 * @return the refundcount
	 */
	public int getRefundcount() {
		return refundcount;
	}
	/**
	 * @param refundcount the refundcount to set
	 */
	public void setRefundcount(int refundcount) {
		this.refundcount = refundcount;
	}
	/**
	 * @return the storeid
	 */
	public String getStoreid() {
		return storeid;
	}
	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	/**
	 * @return the paymoney
	 */
	public double getPaymoney() {
		return paymoney;
	}
	/**
	 * @param paymoney the paymoney to set
	 */
	public void setPaymoney(double paymoney) {
		this.paymoney = paymoney;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the refundMoney
	 */
	public double getRefundMoney() {
		return refundMoney;
	}
	/**
	 * @param refundMoney the refundMoney to set
	 */
	public void setRefundMoney(double refundMoney) {
		this.refundMoney = refundMoney;
	}
	/**
	 * @return the merids
	 */
	public String getMerids() {
		return merids;
	}
	/**
	 * @param merids the merids to set
	 */
	public void setMerids(String merids) {
		this.merids = merids;
	}
	/**
	 * @return the paytype
	 */
	public String getPaytype() {
		return paytype;
	}
	/**
	 * @param paytype the paytype to set
	 */
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public double getSumratemoney() {
		return sumratemoney;
	}
	public void setSumratemoney(double sumratemoney) {
		this.sumratemoney = sumratemoney;
	}
	
	
}
