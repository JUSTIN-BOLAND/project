package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Recharge implements Serializable{

	private static final long serialVersionUID = 8236469690933529842L;
	private Integer id;

	private String orderNo;

	private Integer dealerId;

	private Integer deviceId;

	private Integer payType;

	private String outTradeNo;

	private String authCode;

	private String buyerId;

	private Integer cardNo;

	private Integer serviceType;

	private Double payAmount;

	private Double actualAmount;

	private Double beforeAmount;

	private Double afterAmount;

	private String subMerchantId;

	private String status;

	private String payStatus;

	private Date payTime;

	private Date creatTime;

	private String operatorId;

	private String memo;
	private Integer planId;
	private String sendCmd;
	private String recieveCmd;

	private String hexSerial;
	private String hexDeviceCode;

	public String getHexSerial() {
		return hexSerial;
	}

	public void setHexSerial(String hexSerial) {
		this.hexSerial = hexSerial;
	}

	public String getHexDeviceCode() {
		return hexDeviceCode;
	}

	public void setHexDeviceCode(String hexDeviceCode) {
		this.hexDeviceCode = hexDeviceCode;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	private String updateStatus;


	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getSendCmd() {
		return sendCmd;
	}

	public void setSendCmd(String sendCmd) {
		this.sendCmd = sendCmd;
	}

	public String getRecieveCmd() {
		return recieveCmd;
	}

	public void setRecieveCmd(String recieveCmd) {
		this.recieveCmd = recieveCmd;
	}



	public Integer getId(){
	    return id;
	}
	public void setId(Integer id){
	    this.id=id;
	}

	public String getOrderNo(){
	    return orderNo;
	}
	public void setOrderNo(String orderNo){
	    this.orderNo=orderNo;
	}

	public Integer getDealerId(){
	    return dealerId;
	}
	public void setDealerId(Integer dealerId){
	    this.dealerId=dealerId;
	}

	public Integer getDeviceId(){
	    return deviceId;
	}
	public void setDeviceId(Integer deviceId){
	    this.deviceId=deviceId;
	}

	public Integer getPayType(){
	    return payType;
	}
	public void setPayType(Integer payType){
	    this.payType=payType;
	}

	public String getOutTradeNo(){
	    return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo){
	    this.outTradeNo=outTradeNo;
	}

	public String getAuthCode(){
	    return authCode;
	}
	public void setAuthCode(String authCode){
	    this.authCode=authCode;
	}

	public String getBuyerId(){
	    return buyerId;
	}
	public void setBuyerId(String buyerId){
	    this.buyerId=buyerId;
	}

	public Integer getCardNo(){
	    return cardNo;
	}
	public void setCardNo(Integer cardNo){
	    this.cardNo=cardNo;
	}

	public Integer getServiceType(){
	    return serviceType;
	}
	public void setServiceType(Integer serviceType){
	    this.serviceType=serviceType;
	}

	public Double getPayAmount(){
	    return payAmount;
	}
	public void setPayAmount(Double payAmount){
	    this.payAmount=payAmount;
	}

	public Double getActualAmount(){
	    return actualAmount;
	}
	public void setActualAmount(Double actualAmount){
	    this.actualAmount=actualAmount;
	}

	public Double getBeforeAmount(){
	    return beforeAmount;
	}
	public void setBeforeAmount(Double beforeAmount){
	    this.beforeAmount=beforeAmount;
	}

	public Double getAfterAmount(){
	    return afterAmount;
	}
	public void setAfterAmount(Double afterAmount){
	    this.afterAmount=afterAmount;
	}

	public String getSubMerchantId(){
	    return subMerchantId;
	}
	public void setSubMerchantId(String subMerchantId){
	    this.subMerchantId=subMerchantId;
	}

	public String getStatus(){
	    return status;
	}
	public void setStatus(String status){
	    this.status=status;
	}

	public String getPayStatus(){
	    return payStatus;
	}
	public void setPayStatus(String payStatus){
	    this.payStatus=payStatus;
	}

	public Date getPayTime(){
	    return payTime;
	}
	public void setPayTime(Date payTime){
	    this.payTime=payTime;
	}

	public Date getCreatTime(){
	    return creatTime;
	}
	public void setCreatTime(Date creatTime){
	    this.creatTime=creatTime;
	}

	public String getOperatorId(){
	    return operatorId;
	}
	public void setOperatorId(String operatorId){
	    this.operatorId=operatorId;
	}

	public String getMemo(){
	    return memo;
	}
	public void setMemo(String memo){
	    this.memo=memo;
	}

}