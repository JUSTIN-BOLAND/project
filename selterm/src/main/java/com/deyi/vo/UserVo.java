package com.deyi.vo;

import java.io.Serializable;

public class UserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3206096773589836526L;

	private String id;
	
	private String account;//登录用户名       
	
	private String contact;//联系方式      
	
	private String contactperson;//联系人    
	
	private String rolename;//所属角色            
	
	private String creattime;//创建时间        
	
	private String remark;//备注
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
