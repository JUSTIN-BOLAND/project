package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable{
    /**
	 * 序列化的作用是有利于对象转换成2进制，便于前后端传输；
	 */
	private static final long serialVersionUID = 7609099125445060472L;


	private String id;


    private String roleid;

    private String rolename;


    private String password;

    private String type;
    
    private Date loginTime;

    
    /** 扩展字段**/
    private String name;
    
    /** 扩展字段 职务 **/
    private String job;
    
    private Date creatTime;
    
    private String remark;
    /** 扩展字段  **/
    private String orgId;
    
	public String getOrgId() {
		return orgId;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", roleid=" + roleid + ", rolename=" + rolename + ", password=" + password
				+ ", type=" + type + ", loginTime=" + loginTime + ", name=" + name + ", job=" + job + ", creatTime="
				+ creatTime + ", remark=" + remark + ", orgId=" + orgId + "]";
	}

   
}