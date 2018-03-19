package com.deyi.vo;

import java.io.Serializable;

public class OrderParams implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNo = 1;//页码，默认是第一页  
    private int pageSize = 10;//每页显示的记录数，默认是10  
    
    private String orderNo;
	private String payNo;// 支付宝或微信第三方支付流水号
	private String orderMoney;// 订单金额
	private String payMoney;// 支付金额
	private String refundMoney;// 退款金额
	private String creator;// 订单创建人
	private Integer orgId;// 代理商id
	private Integer orgName;// 商户所以机构类型 1.运营商 2.代理商
	private Integer merId;// 商户id
	private String merName;// 商户名称
	private String terId;// 终端编号
	private Integer storeId;// 门店id
	private String storeName;// 门店名称
	private String goneType;// 1扫码 保留字段
	private String payType;// 1， 支付宝 2 微信
	private String payTypeName;// 1， 支付宝 2 微信
	private String orderTime;// 订单时间
	private String payTime;// 支付时间
	private String payStatus;// 1.未支付2 支付成功 3 失败
	private String orderStatus;// 1 正常 2 撤销3.已退款
	private String payAccount;// 买家账号：支付宝微信账号
	private String authCode;// 扫码授权码

	private String orderTimeStart;// 订单时间
	private String orderTimeEnd;// 订单时间
	private String payTimeStart;// 支付时间
	private String payTimeEnd;// 支付时间
	private String payDate;// 支付时间
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getOrgName() {
		return orgName;
	}
	public void setOrgName(Integer orgName) {
		this.orgName = orgName;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getTerId() {
		return terId;
	}
	public void setTerId(String terId) {
		this.terId = terId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getGoneType() {
		return goneType;
	}
	public void setGoneType(String goneType) {
		this.goneType = goneType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getOrderTimeStart() {
		return orderTimeStart;
	}
	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}
	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}
	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}
	public String getPayTimeStart() {
		return payTimeStart;
	}
	public void setPayTimeStart(String payTimeStart) {
		this.payTimeStart = payTimeStart;
	}
	public String getPayTimeEnd() {
		return payTimeEnd;
	}
	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	
	
}
