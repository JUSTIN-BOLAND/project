package com.deyi.vo;

import java.io.Serializable;

/**
 * 当天订单总金额，支付宝总金额，微信总金额
 * @author gongz
 *
 */
public class PaystatisVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4462027105240064198L;

	private String wxPaySum;//微信支付笔数
	
	private String aliPaySum;//支付宝支付笔数
	private String unionPaySum;//银联支付笔数
	
	private String sumNumber; //总笔数
	
	private String weixmoney;

    private String zhifbaomoney;
    private String unionmoney;

    private String summoney;

    private String merids;
    
	public String getWxPaySum() {
		return wxPaySum;
	}

	public void setWxPaySum(String wxPaySum) {
		this.wxPaySum = wxPaySum;
	}

	public String getAliPaySum() {
		return aliPaySum;
	}

	public void setAliPaySum(String aliPaySum) {
		this.aliPaySum = aliPaySum;
	}

	public String getSumNumber() {
		return sumNumber;
	}

	public void setSumNumber(String sumNumber) {
		this.sumNumber = sumNumber;
	}

	public String getWeixmoney() {
		return weixmoney;
	}

	public void setWeixmoney(String weixmoney) {
		this.weixmoney = weixmoney;
	}

	public String getZhifbaomoney() {
		return zhifbaomoney;
	}

	public void setZhifbaomoney(String zhifbaomoney) {
		this.zhifbaomoney = zhifbaomoney;
	}

	public String getSummoney() {
		return summoney;
	}

	public void setSummoney(String summoney) {
		this.summoney = summoney;
	}

	public String getMerids() {
		return merids;
	}

	public void setMerids(String merids) {
		this.merids = merids;
	}

	public String getUnionPaySum() {
		return unionPaySum;
	}

	public void setUnionPaySum(String unionPaySum) {
		this.unionPaySum = unionPaySum;
	}

	public String getUnionmoney() {
		return unionmoney;
	}

	public void setUnionmoney(String unionmoney) {
		this.unionmoney = unionmoney;
	}
    
    

	

	

	
}