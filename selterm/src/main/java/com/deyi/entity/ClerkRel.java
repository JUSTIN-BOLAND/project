package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class ClerkRel implements Serializable{


	private static final long serialVersionUID = 1747890293248296671L;
	private String orgId        ;
	private String orgName      ;
	private String merchantCode ;
	private String merchantName ;
	private String storeCode    ;
	private String storeName    ;
	private String clerkCode    ;
	private String aliMerchantId;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	private String merchantId;

	public String getAliMerchantId() {
		return aliMerchantId;
	}

	public void setAliMerchantId(String aliMerchantId) {
		this.aliMerchantId = aliMerchantId;
	}



	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getClerkCode() {
		return clerkCode;
	}

	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	private String clerkName    ;

}