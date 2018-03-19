package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5503273071891583436L;

	private Long id;

    private String rolename;

    private String roletype;

    private String roledesc;

    private Date createtime;

    private String creator;

    private String creatorname;

    private String rolestatus;
    
    private String orgId;
    
    private String remark;
    
    private String orgMerId;//增加角色时 加入字段
    
    private String orgName;//扩
    
    
    private String usertype;
    
	private Role parentRole;
    
    private List<Role> children;
    
    
    private String roleids;
    
    
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getRolestatus() {
		return rolestatus;
	}

	public void setRolestatus(String rolestatus) {
		this.rolestatus = rolestatus;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgMerId() {
		return orgMerId;
	}

	public void setOrgMerId(String orgMerId) {
		this.orgMerId = orgMerId;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Role getParentRole() {
		return parentRole;
	}

	public void setParentRole(Role parentRole) {
		this.parentRole = parentRole;
	}

	public List<Role> getChildren() {
		return children;
	}

	public void setChildren(List<Role> children) {
		this.children = children;
	}
    
    
    
}