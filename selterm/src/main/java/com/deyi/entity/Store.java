package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店
 * @author admin
 *
 */
public class Store implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 7833853924844946282L;
	private Integer id;
	private String storeName;
	private String storeCode;
	private String storeAdress;
	private String status;
	private Integer merId;
	private String merName;
	private Date creatTime;
	private String macstatus;

	private String merchantIds;//扩展字段
	private String deletedStatus;//删除状态0(为删除)1(已删除)
	private String creator;    // 这个是商户的创建人
	private String creatorname;    // 这个是商户的创建人
	private String storecreattime;

	private String refundstatus;






	/**
	 * @return the refundstatus
	 */
	public String getRefundstatus() {
		return refundstatus;
	}
	/**
	 * @param refundstatus the refundstatus to set
	 */
	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	/**
	 * @return the macstatus
	 */
	public String getMacstatus() {
		return macstatus;
	}
	/**
	 * @param macstatus the macstatus to set
	 */
	public void setMacstatus(String macstatus) {
		this.macstatus = macstatus;
	}
	/**
	 * @return the storecreattime
	 */
	public String getStorecreattime() {
		return storecreattime;
	}
	/**
	 * @param storecreattime the storecreattime to set
	 */
	public void setStorecreattime(String storecreattime) {
		this.storecreattime = storecreattime;
	}
	/**
	 * @return the creatorname
	 */
	public String getCreatorname() {
		return creatorname;
	}
	/**
	 * @param creatorname the creatorname to set
	 */
	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getMerchantIds() {
		return merchantIds;
	}
	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAdress() {
		return storeAdress;
	}
	public void setStoreAdress(String storeAdress) {
		this.storeAdress = storeAdress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

}
