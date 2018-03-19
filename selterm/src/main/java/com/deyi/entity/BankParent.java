package com.deyi.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class BankParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -164318439389535279L;

	private Integer id;

    private String bankCode;

    private String bankName;

    private String bankNo;

    private String bankType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }
}