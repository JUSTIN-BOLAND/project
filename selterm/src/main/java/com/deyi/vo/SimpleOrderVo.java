package com.deyi.vo;

import java.io.Serializable;

public class SimpleOrderVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541704983230317916L;

	private String orderNo;
	
	private String orderTime;// 订单时间
	
	private String orderMoney;// 订单金额
	
	private String orderStatus;// 1 正常 2 撤销3.已退款
	
	private String payStatus;
	
	
	

	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * @return the orderMoney
	 */
	public String getOrderMoney() {
		return orderMoney;
	}

	/**
	 * @param orderMoney the orderMoney to set
	 */
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleOrderVo [orderNo=" + orderNo + ", orderTime=" + orderTime + ", orderMoney=" + orderMoney
				+ ", orderStatus=" + orderStatus + "]";
	}
	
	
}
