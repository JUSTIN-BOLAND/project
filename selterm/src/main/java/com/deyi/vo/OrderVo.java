package com.deyi.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单查询类
 */
public class OrderVo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -6111509257172702020L;
	private String orderNo;
	private String payNo;//支付宝或微信第三方支付流水号
	private String orderMoney;//订单金额
	private String payMoney;//支付金额
	private String refundMoney;//退款金额
	private String creator;//订单创建人
	private Integer orgId;//代理商id
	private String orgName;//商户所以机构类型
	private String merId;//  商户id
	private String merName;// 商户名称
	private String terId;//终端编号
	private String storeId;//门店id
	private String storeName;//门店名称
	private String goneType;// 1扫码 保留字段
	private String payType;//1， 支付宝   2 微信
	private String orderTime;//订单时间
	private String payTime;//支付时间
	private String payStatus;//1.未支付2 支付成功  3 失败
	private String orderStatus;//1 正常 2 撤销3.已退款
	private String payAccount;//买家账号：支付宝微信账号
	private String authCode;//扫码授权码

	private String  orderTimeStart;//订单时间
	private String orderTimeEnd;//订单时间
	private String payTimeStart;//支付时间
	private String payTimeEnd;//支付时间
	private String payDate;//支付时间
	private String payEnv;

	private String orgIds;//扩展查询字段
	private String merchantIds;//扩展查询字段

	private String clerkname ;

	private Double undiscountMoney;

	private String org_name;
	private double ratemoney;
	private double sumratemoney;
	private String payMode;
	private String cancelNo;
	private String  cancelTime;

	public String getRefundMoney() {
		return refundMoney;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	private String refundTime;//退款时间

	public String getClerkCode() {
		return clerkCode;
	}

	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}

	private String clerkCode;

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	private String notifyUrl;

	public String getCancelNo() {
		return cancelNo;
	}

	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}






	/**
	 * @return the undiscountMoney
	 */
	public Double getUndiscountMoney() {
		return undiscountMoney;
	}
	/**
	 * @param undiscountMoney the undiscountMoney to set
	 */
	public void setUndiscountMoney(Double undiscountMoney) {
		this.undiscountMoney = undiscountMoney;
	}
	public String getClerkname() {
		return clerkname;
	}
	public void setClerkname(String clerkname) {
		this.clerkname = clerkname;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getMerchantIds() {
		return merchantIds;
	}
	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
	}
	public String getPayEnv() {
		return payEnv;
	}
	public void setPayEnv(String payEnv) {
		this.payEnv = payEnv;
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
	public String getrMoney() {
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

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
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
		if(orderTime!=null && orderTime.indexOf(".") > -1 ) this.orderTime = orderTime.substring(0,orderTime.indexOf("."));
		else this.orderTime = orderTime;
	}
	public String getPayTime() {

		return payTime;
	}
	public void setPayTime(String payTime) {
		if(payTime!=null && payTime.indexOf(".") > -1 ) this.payTime = payTime.substring(0,payTime.indexOf("."));

		else this.payTime = payTime;
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
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public double getRatemoney() {
		return ratemoney;
	}
	public void setRatemoney(double ratemoney) {
		this.ratemoney = ratemoney;
	}
	public double getSumratemoney() {
		return sumratemoney;
	}
	public void setSumratemoney(double sumratemoney) {
		this.sumratemoney = sumratemoney;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
}
