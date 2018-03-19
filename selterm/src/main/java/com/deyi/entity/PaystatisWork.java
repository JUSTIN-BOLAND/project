package com.deyi.entity;

import java.io.Serializable;

public class PaystatisWork implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7794302977447453812L;

	private String id;

    private String wxpaysum;

    private String alipaysum;
    private String unionpaysum;

    private String wxpayMoney;

    private String alipayMoney;
    private String unionpayMoney;

    private String wxrefundMoney;

    private String alirefundMoney;
    private String unionrefundMoney;

    private String wxMoney;

    private String aliMoney;
    private String unionMoney;

    private String starttime;

    private String endtime;

    private String creator;//班次结算人
    
    private String loginName;//班次创建人

    private String storeid;

    private String payMoney;

    private String refundMoney;

    private String money;
    
    private String payEnv;
    
    private String  timeStart;//扩展查询字段
    
	private String timeEnd;//扩展查询字段
    
	
	private String storename;
	
	private String merchantname;
	
	
	
	
	
	
	/**
	 * @return the merchantname
	 */
	public String getMerchantname() {
		return merchantname;
	}

	/**
	 * @param merchantname the merchantname to set
	 */
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getPayEnv() {
		return payEnv;
	}

	public void setPayEnv(String payEnv) {
		this.payEnv = payEnv;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWxpaysum() {
		return wxpaysum;
	}

	public void setWxpaysum(String wxpaysum) {
		this.wxpaysum = wxpaysum;
	}

	public String getAlipaysum() {
		return alipaysum;
	}

	public void setAlipaysum(String alipaysum) {
		this.alipaysum = alipaysum;
	}

	public String getWxpayMoney() {
		return wxpayMoney;
	}

	public void setWxpayMoney(String wxpayMoney) {
		this.wxpayMoney = wxpayMoney;
	}

	public String getAlipayMoney() {
		return alipayMoney;
	}

	public void setAlipayMoney(String alipayMoney) {
		this.alipayMoney = alipayMoney;
	}

	public String getWxrefundMoney() {
		return wxrefundMoney;
	}

	public void setWxrefundMoney(String wxrefundMoney) {
		this.wxrefundMoney = wxrefundMoney;
	}

	public String getAlirefundMoney() {
		return alirefundMoney;
	}

	public void setAlirefundMoney(String alirefundMoney) {
		this.alirefundMoney = alirefundMoney;
	}

	public String getWxMoney() {
		return wxMoney;
	}

	public void setWxMoney(String wxMoney) {
		this.wxMoney = wxMoney;
	}

	public String getAliMoney() {
		return aliMoney;
	}

	public void setAliMoney(String aliMoney) {
		this.aliMoney = aliMoney;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}


	public String getUnionpayMoney() {
		return unionpayMoney;
	}

	public void setUnionpayMoney(String unionpayMoney) {
		this.unionpayMoney = unionpayMoney;
	}

	public String getUnionrefundMoney() {
		return unionrefundMoney;
	}

	public void setUnionrefundMoney(String unionrefundMoney) {
		this.unionrefundMoney = unionrefundMoney;
	}

	public String getUnionMoney() {
		return unionMoney;
	}

	public void setUnionMoney(String unionMoney) {
		this.unionMoney = unionMoney;
	}

	@Override
	public String toString() {
		return "PaystatisWork [id=" + id + ", wxpaysum=" + wxpaysum + ", alipaysum=" + alipaysum + ", wxpayMoney="
				+ wxpayMoney + ", alipayMoney=" + alipayMoney + ", unionpayMoney=" + unionpayMoney + ", wxrefundMoney="
				+ wxrefundMoney + ", alirefundMoney=" + alirefundMoney + ", unionrefundMoney=" + unionrefundMoney
				+ ", wxMoney=" + wxMoney + ", aliMoney=" + aliMoney + ", unionMoney=" + unionMoney + ", starttime="
				+ starttime + ", endtime=" + endtime + ", creator=" + creator + ", loginName=" + loginName
				+ ", storeid=" + storeid + ", payMoney=" + payMoney + ", refundMoney=" + refundMoney + ", money="
				+ money + ", payEnv=" + payEnv + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", storename="
				+ storename + ", merchantname=" + merchantname + "]";
	}

	public String getUnionpaysum() {
		return unionpaysum;
	}

	public void setUnionpaysum(String unionpaysum) {
		this.unionpaysum = unionpaysum;
	}
	
}