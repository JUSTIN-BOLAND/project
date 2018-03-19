package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 3906352099255297172L;
	private String orderNo;
	private String payNo;//支付宝或微信第三方支付流水号
	private Double orderMoney;//订单金额
	private Double payMoney;//支付金额
	private Double refundMoney;//退款金额
	private String creator;//订单创建人
	private Integer orgId;//代理商id
	private String orgName;//代理商名称
	private String merId;//  商户id
	private String merName;// 商户名称
	private String terId;//终端编号
	private String storeId;//门店id
	private String storeCode;//门店编号
	private String storeName;//门店名称
	private String goneType;// 1扫码 保留字段
	private String payType;//1， 支付宝   2 微信
	private Date orderTime;//订单时间
	private Date payTime;//支付时间
	private String payStatus;//1.未支付2 支付成功  3 失败
	private String orderStatus;//1 正常 2 撤销3.已退款
	private String payAccount;//买家账号：支付宝微信账号
	private String authCode;//扫码授权码
	private Double undiscountMoney;//不打折金额
	private String errMsg;//支付失败原因
	private String refundNo;//退款流水号
	private Date refundTime;//退款时间
	private String payEnv;
	private String payStatusName;
	private String orderStatusName;
	private String payTypeName;

	private String mertype;
	private String orgCode;
	private String merCode;

	private String cancelNo;
	private Date  cancelTime;
	private String notifyUrl;
	private String clerkCode;
	private String org_id;


	private String payMode;

	public String getRatemoney() {
		return ratemoney;
	}

	public void setRatemoney(String ratemoney) {
		this.ratemoney = ratemoney;
	}

	private String ratemoney;

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}



	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getClerkCode() {
		return clerkCode;
	}

	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}





	public String getDistType() {
		return distType;
	}

	public void setDistType(String distType) {
		this.distType = distType;
	}

	private String distType;

	public String getCancelNo() {
		return cancelNo;
	}

	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}


	public String getDisType() {
		return disType;
	}

	public void setDisType(String disType) {
		this.disType = disType;
	}

	private String disType;







	/**
	 * @return the mertype
	 */
	public String getMertype() {
		return mertype;
	}
	/**
	 * @param mertype the mertype to set
	 */
	public void setMertype(String mertype) {
		this.mertype = mertype;
	}
	/**
	 * @return the payStatusName
	 */
	public String getPayStatusName() {
		return payStatusName;
	}
	/**
	 * @param payStatusName the payStatusName to set
	 */
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}
	/**
	 * @return the orderStatusName
	 */
	public String getOrderStatusName() {
		return orderStatusName;
	}
	/**
	 * @param orderStatusName the orderStatusName to set
	 */
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	/**
	 * @return the payTypeName
	 */
	public String getPayTypeName() {
		return payTypeName;
	}
	/**
	 * @param payTypeName the payTypeName to set
	 */
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	private String orderTimeString;  //2016-06-06 01:00:00 格式
	private String payTimeString; //2016-06-06 01:00:00 格式

	private String clerkname;



	/**
	 * @return the clerkname
	 */
	public String getClerkname() {
		return clerkname;
	}
	/**
	 * @param clerkname the clerkname to set
	 */
	public void setClerkname(String clerkname) {
		this.clerkname = clerkname;
	}
	/**
	 * @return the orderTimeString
	 */
	public String getOrderTimeString() {
		return orderTimeString;
	}
	/**
	 * @param orderTimeString the orderTimeString to set
	 */
	public void setOrderTimeString(String orderTimeString) {
		this.orderTimeString = orderTimeString;
	}
	/**
	 * @return the payTimeString
	 */
	public String getPayTimeString() {
		return payTimeString;
	}
	/**
	 * @param payTimeString the payTimeString to set
	 */
	public void setPayTimeString(String payTimeString) {
		this.payTimeString = payTimeString;
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
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public Double getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(Double refundMoney) {
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
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
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
	public Double getUndiscountMoney() {
		return undiscountMoney;
	}
	public void setUndiscountMoney(Double undiscountMoney) {
		this.undiscountMoney = undiscountMoney;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", payNo=" + payNo + ", orderMoney=" + orderMoney + ", payMoney="
				+ payMoney + ", refundMoney=" + refundMoney + ", creator=" + creator + ", orgId=" + orgId + ", orgName="
				+ orgName + ", merId=" + merId + ", merName=" + merName + ", terId=" + terId + ", storeId=" + storeId
				+ ", storeCode=" + storeCode + ", storeName=" + storeName + ", goneType=" + goneType + ", payType="
				+ payType + ", orderTime=" + orderTime + ", payTime=" + payTime + ", payStatus=" + payStatus
				+ ", orderStatus=" + orderStatus + ", payAccount=" + payAccount + ", authCode=" + authCode
				+ ", undiscountMoney=" + undiscountMoney + ", errMsg=" + errMsg + ", refundNo=" + refundNo
				+ ", refundTime=" + refundTime + ", payEnv=" + payEnv + "]";
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
}
