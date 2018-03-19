package com.deyi.vo;

import java.io.Serializable;

public class PaystatisWorkVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8478789894546074701L;

	private String payMoney;

    private String refundMoney;

    private String money;
    
    private String number;
    
    
    
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	

	

	
}