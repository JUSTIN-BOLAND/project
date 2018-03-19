package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable{

	private static final long serialVersionUID = 8236469690933529842L;
	private Integer id;

	private Integer serviceType;

	private String batchNo;

	private String cardNo;

	private String deviceName;

	private String passwd;

	private String name;

	private String mobile;

	private Double balance;

	private Double deposit;

	private Double recharge;

	private String idCardNo;

	private String memo;

	private String STATUS;

	private Date createTime;

	private String flag;

	private String creator;


	public Integer getId(){
	    return id;
	}
	public void setId(Integer id){
	    this.id=id;
	}

	public Integer getServiceType(){
	    return serviceType;
	}
	public void setServiceType(Integer serviceType){
	    this.serviceType=serviceType;
	}

	public String getBatchNo(){
	    return batchNo;
	}
	public void setBatchNo(String batchNo){
	    this.batchNo=batchNo;
	}

	public String getCardNo(){
	    return cardNo;
	}
	public void setCardNo(String cardNo){
	    this.cardNo=cardNo;
	}

	public String getDeviceName(){
	    return deviceName;
	}
	public void setDeviceName(String deviceName){
	    this.deviceName=deviceName;
	}

	public String getPasswd(){
	    return passwd;
	}
	public void setPasswd(String passwd){
	    this.passwd=passwd;
	}

	public String getName(){
	    return name;
	}
	public void setName(String name){
	    this.name=name;
	}

	public String getMobile(){
	    return mobile;
	}
	public void setMobile(String mobile){
	    this.mobile=mobile;
	}

	public Double getBalance(){
	    return balance;
	}
	public void setBalance(Double balance){
	    this.balance=balance;
	}

	public Double getDeposit(){
	    return deposit;
	}
	public void setDeposit(Double deposit){
	    this.deposit=deposit;
	}

	public Double getRecharge(){
	    return recharge;
	}
	public void setRecharge(Double recharge){
	    this.recharge=recharge;
	}

	public String getIdCardNo(){
	    return idCardNo;
	}
	public void setIdCardNo(String idCardNo){
	    this.idCardNo=idCardNo;
	}

	public String getMemo(){
	    return memo;
	}
	public void setMemo(String memo){
	    this.memo=memo;
	}

	public String getSTATUS(){
	    return STATUS;
	}
	public void setSTATUS(String STATUS){
	    this.STATUS=STATUS;
	}

	public Date getCreateTime(){
	    return createTime;
	}
	public void setCreateTime(Date createTime){
	    this.createTime=createTime;
	}

	public String getFlag(){
	    return flag;
	}
	public void setFlag(String flag){
	    this.flag=flag;
	}

	public String getCreator(){
	    return creator;
	}
	public void setCreator(String creator){
	    this.creator=creator;
	}

}