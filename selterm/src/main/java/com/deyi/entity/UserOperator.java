package com.deyi.entity;

import java.io.Serializable;
import java.util.List;

public class UserOperator implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3104146462389040758L;

	private Long id;

    private Long orgId;

    private String loginnmame;

    private String name;

    private String contact;

    private String remark;
    
    private Long roleid;
    
    private String rolename;
    
    private String ids;
    
    private String orgname;
    
    
    private Long parentid;
    
    private String parentname;
    
    private UserOperator parentuser;
    
    private List<UserOperator> childeruser;
    
    
    private String status;
    
    private String roleusertype;
    
    
    
    
    
    
    
    
    /**
	 * @return the roleusertype
	 */
	public String getRoleusertype() {
		return roleusertype;
	}

	/**
	 * @param roleusertype the roleusertype to set
	 */
	public void setRoleusertype(String roleusertype) {
		this.roleusertype = roleusertype;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public UserOperator getParentuser() {
		return parentuser;
	}

	public void setParentuser(UserOperator parentuser) {
		this.parentuser = parentuser;
	}

	public List<UserOperator> getChilderuser() {
		return childeruser;
	}

	public void setChilderuser(List<UserOperator> childeruser) {
		this.childeruser = childeruser;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getLoginnmame() {
        return loginnmame;
    }

    public void setLoginnmame(String loginnmame) {
        this.loginnmame = loginnmame == null ? null : loginnmame.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	/**
	 * @return the parentname
	 */
	public String getParentname() {
		return parentname;
	}

	/**
	 * @param parentname the parentname to set
	 */
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
    
    
}