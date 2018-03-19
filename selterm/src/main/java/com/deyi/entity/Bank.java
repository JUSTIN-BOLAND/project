package com.deyi.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class Bank implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4413245193392713503L;

	private Integer id;

    private Integer parentBankNo;

    private String parentBankName;

    private String quickUnionBankNo;

    private String bankName;

    private String unionBankNo;

    private String cityCode;

    private String address;

    private String postCode;

    private String status;

    private String cityId;

    private String bankparentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentBankNo() {
        return parentBankNo;
    }

    public void setParentBankNo(Integer parentBankNo) {
        this.parentBankNo = parentBankNo;
    }

    public String getParentBankName() {
        return parentBankName;
    }

    public void setParentBankName(String parentBankName) {
        this.parentBankName = parentBankName == null ? null : parentBankName.trim();
    }

    public String getQuickUnionBankNo() {
        return quickUnionBankNo;
    }

    public void setQuickUnionBankNo(String quickUnionBankNo) {
        this.quickUnionBankNo = quickUnionBankNo == null ? null : quickUnionBankNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getUnionBankNo() {
        return unionBankNo;
    }

    public void setUnionBankNo(String unionBankNo) {
        this.unionBankNo = unionBankNo == null ? null : unionBankNo.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getBankparentId() {
        return bankparentId;
    }

    public void setBankparentId(String bankparentId) {
        this.bankparentId = bankparentId == null ? null : bankparentId.trim();
    }
}