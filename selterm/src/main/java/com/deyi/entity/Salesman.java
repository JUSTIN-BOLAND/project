package com.deyi.entity;

import java.io.Serializable;

public class Salesman implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5663214842622507685L;

	private Long saleid;

    private String salecode;

    private String salename;

    private String accountname;

    private String contact;

    private Long orgid;

    private String remark;

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
    }

    public String getSalecode() {
        return salecode;
    }

    public void setSalecode(String salecode) {
        this.salecode = salecode == null ? null : salecode.trim();
    }

    public String getSalename() {
        return salename;
    }

    public void setSalename(String salename) {
        this.salename = salename == null ? null : salename.trim();
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}