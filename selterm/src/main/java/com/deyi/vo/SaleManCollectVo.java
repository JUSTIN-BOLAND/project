package com.deyi.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleManCollectVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4712484402504387620L;
	private String userid;
	private String username;
	private int merchantcount;
	private BigDecimal money;
	private BigDecimal yesterdaymoney;
	private BigDecimal monthmoney;
	
	private int sumcount;// 门店数目
	
	private String parentuser;  //上级用户
	
	
	private String merids;   //extent
	
	
	private String userloginids;
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the userloginids
	 */
	public String getUserloginids() {
		return userloginids;
	}
	/**
	 * @param userloginids the userloginids to set
	 */
	public void setUserloginids(String userloginids) {
		this.userloginids = userloginids;
	}
	/**
	 * @return the parentuser
	 */
	public String getParentuser() {
		return parentuser;
	}
	/**
	 * @param parentuser the parentuser to set
	 */
	public void setParentuser(String parentuser) {
		this.parentuser = parentuser;
	}
	/**
	 * @return the sumcount
	 */
	public int getSumcount() {
		return sumcount;
	}
	/**
	 * @param sumcount the sumcount to set
	 */
	public void setSumcount(int sumcount) {
		this.sumcount = sumcount;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the merchantcount
	 */
	public int getMerchantcount() {
		return merchantcount;
	}
	/**
	 * @param merchantcount the merchantcount to set
	 */
	public void setMerchantcount(int merchantcount) {
		this.merchantcount = merchantcount;
	}
	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * @return the yesterdaymoney
	 */
	public BigDecimal getYesterdaymoney() {
		return yesterdaymoney;
	}
	/**
	 * @param yesterdaymoney the yesterdaymoney to set
	 */
	public void setYesterdaymoney(BigDecimal yesterdaymoney) {
		this.yesterdaymoney = yesterdaymoney;
	}
	/**
	 * @return the monthmoney
	 */
	public BigDecimal getMonthmoney() {
		return monthmoney;
	}
	/**
	 * @param monthmoney the monthmoney to set
	 */
	public void setMonthmoney(BigDecimal monthmoney) {
		this.monthmoney = monthmoney;
	}
	
	

}
