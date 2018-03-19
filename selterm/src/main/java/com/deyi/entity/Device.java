package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable{

	private static final long serialVersionUID = 8236469690933529842L;
	private Integer id;

	private Integer deviceType;

	private String dealerId;

	private String deviceCode;

	private String deviceName;

	private Integer dayMaxCount;

	private Integer monthMaxCount;

	private String installAddress;

	private String machineStatus;

	private String memo;

	private String status;

	private String creator;

	private Date createTime;

	private String flag;
	private String qrCode;

	public String getQrUrl() {
		return qrUrl;
	}

	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

	private String qrUrl;

	public String getHints() {
		return hints;
	}

	public void setHints(String hints) {
		this.hints = hints;
	}

	private String hints;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	private String planName;

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}




	public Integer getId(){
	    return id;
	}
	public void setId(Integer id){
	    this.id=id;
	}

	public Integer getDeviceType(){
	    return deviceType;
	}
	public void setDeviceType(Integer deviceType){
	    this.deviceType=deviceType;
	}

	public String getDealerId(){
	    return dealerId;
	}
	public void setDealerId(String dealerId){
	    this.dealerId=dealerId;
	}

	public String getDeviceCode(){
	    return deviceCode;
	}
	public void setDeviceCode(String deviceCode){
	    this.deviceCode=deviceCode;
	}

	public String getDeviceName(){
	    return deviceName;
	}
	public void setDeviceName(String deviceName){
	    this.deviceName=deviceName;
	}

	public Integer getDayMaxCount(){
	    return dayMaxCount;
	}
	public void setDayMaxCount(Integer dayMaxCount){
	    this.dayMaxCount=dayMaxCount;
	}

	public Integer getMonthMaxCount(){
	    return monthMaxCount;
	}
	public void setMonthMaxCount(Integer monthMaxCount){
	    this.monthMaxCount=monthMaxCount;
	}

	public String getInstallAddress(){
	    return installAddress;
	}
	public void setInstallAddress(String installAddress){
	    this.installAddress=installAddress;
	}

	public String getMachineStatus(){
	    return machineStatus;
	}
	public void setMachineStatus(String machineStatus){
	    this.machineStatus=machineStatus;
	}

	public String getMemo(){
	    return memo;
	}
	public void setMemo(String memo){
	    this.memo=memo;
	}

	public String getStatus(){
	    return status;
	}
	public void setStatus(String status){
	    this.status=status;
	}

	public String getCreator(){
	    return creator;
	}
	public void setCreator(String creator){
	    this.creator=creator;
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

}