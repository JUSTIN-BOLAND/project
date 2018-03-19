package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Dealer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6651577217128858694L;



    private Integer id;
    private String 	dealerId;
    private String 	dealerName;
    private String 	series;
    private String 	expirationId;
    private String 	busLicence;
    private String 	busLicenceNo;
    private String 	address;
    private String 	linkman;
    private String 	email;
    private String 	mobile;
    private String 	singleMaxAmount;
    private String 	dayMaxCount;
    private String 	monthMaxCount;
    private String 	adWords;
    private String 	adLogo;
    private String 	parentId;
    private String 	creator;
    private Date createTime;

    private String 	userId;
    private String 	passwd;
    private String accountExpiration;
    private String wxMchId;
    private String aliMachId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAccountExpiration() {
        return accountExpiration;
    }

    public void setAccountExpiration(String accountExpiration) {
        this.accountExpiration = accountExpiration;
    }



    public String getWxMchId() {
        return wxMchId;
    }

    public void setWxMchId(String wxMchId) {
        this.wxMchId = wxMchId;
    }

    public String getAliMachId() {
        return aliMachId;
    }

    public void setAliMachId(String aliMachId) {
        this.aliMachId = aliMachId;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String 	status;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getExpirationId() {
        return expirationId;
    }

    public void setExpirationId(String expirationId) {
        this.expirationId = expirationId;
    }

    public String getBusLicence() {
        return busLicence;
    }

    public void setBusLicence(String busLicence) {
        this.busLicence = busLicence;
    }

    public String getBusLicenceNo() {
        return busLicenceNo;
    }

    public void setBusLicenceNo(String busLicenceNo) {
        this.busLicenceNo = busLicenceNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSingleMaxAmount() {
        return singleMaxAmount;
    }

    public void setSingleMaxAmount(String singleMaxAmount) {
        this.singleMaxAmount = singleMaxAmount;
    }

    public String getDayMaxCount() {
        return dayMaxCount;
    }

    public void setDayMaxCount(String dayMaxCount) {
        this.dayMaxCount = dayMaxCount;
    }

    public String getMonthMaxCount() {
        return monthMaxCount;
    }

    public void setMonthMaxCount(String monthMaxCount) {
        this.monthMaxCount = monthMaxCount;
    }

    public String getAdWords() {
        return adWords;
    }

    public void setAdWords(String adWords) {
        this.adWords = adWords;
    }

    public String getAdLogo() {
        return adLogo;
    }

    public void setAdLogo(String adLogo) {
        this.adLogo = adLogo;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}