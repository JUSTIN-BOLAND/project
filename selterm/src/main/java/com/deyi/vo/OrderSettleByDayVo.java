package com.deyi.vo;

import java.io.Serializable;

/**
 * 订单查询类
 */
public class OrderSettleByDayVo implements Serializable{
	                               
                   
	/**
	 * 
	 */
	private static final long serialVersionUID = 9168640509470460364L;
	private String creator;
	private String payDate;
	private Double payMoney = 0.0;
	private Double refundMoney = 0.0;
	private Double money = 0.0;
	
	private Double payMoneyWx = 0.0;
	private Double refundMoneyWx = 0.0;
	private Double moneyWx = 0.0;
	
	private Double payMoneyAli = 0.0;
	private Double refundMoneyAli = 0.0;
	private Double moneyAli = 0.0;
	
	private String payTimeStart;
	private String payTimeEnd;
	private String storeId;
	private String payEnv;
	
	
	public String getPayEnv() {
		return payEnv;
	}
	public void setPayEnv(String payEnv) {
		this.payEnv = payEnv;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPayTimeStart() {
		return payTimeStart;
	}
	public String getPayTimeEnd() {
		return payTimeEnd;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getMoneyWx() {
		return moneyWx;
	}
	public void setMoneyWx(Double moneyWx) {
		this.moneyWx = moneyWx;
	}
	public Double getMoneyAli() {
		return moneyAli;
	}
	public void setMoneyAli(Double moneyAli) {
		this.moneyAli = moneyAli;
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
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public Double getPayMoneyWx() {
		return payMoneyWx;
	}
	public void setPayMoneyWx(Double payMoneyWx) {
		this.payMoneyWx = payMoneyWx;
	}
	public Double getRefundMoneyWx() {
		return refundMoneyWx;
	}
	public void setRefundMoneyWx(Double refundMoneyWx) {
		this.refundMoneyWx = refundMoneyWx;
	}
	public Double getPayMoneyAli() {
		return payMoneyAli;
	}
	public void setPayMoneyAli(Double payMoneyAli) {
		this.payMoneyAli = payMoneyAli;
	}
	public Double getRefundMoneyAli() {
		return refundMoneyAli;
	}
	public void setRefundMoneyAli(Double refundMoneyAli) {
		this.refundMoneyAli = refundMoneyAli;
	}
	
	
	public void setPayTimeStart(String payTimeStart) {
		if(payTimeStart != null && !"".equals(payTimeStart)){
			this.payTimeStart = payTimeStart +" 00:00:00";
		}else{
			this.payTimeStart = payTimeStart;
		}
	}
	
	public void setPayTimeEnd(String payTimeEnd) {
		if(payTimeEnd != null && !"".equals(payTimeEnd)){
			this.payTimeEnd = payTimeEnd +" 23:59:59";
		}else{
			this.payTimeEnd = payTimeEnd;
		}
	}
}
