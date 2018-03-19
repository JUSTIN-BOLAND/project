package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class DisClerk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6438689000888162291L;
	private String id;                            
	private String clerkCode;              
	private Integer storeId;              
	private String storeName;              
	private String clerkName;              
	private String clerkJob;
	private String phone;                    
	private Date createTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClerkCode() {
		return clerkCode;
	}
	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public String getClerkJob() {
		return clerkJob;
	}
	public void setClerkJob(String clerkJob) {
		this.clerkJob = clerkJob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
