package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class AliPublic  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6734277262364628863L;

	private String id;

    private String mechid;

    private String mechname;

    private String pid;

    private String appid;

    private String email;

    private String mobile;

    private String creator;

    private Date createtime;
    
    private String audit;
    
    private String pkey;

    private String publicKey;

    private String aliPublicKey;
    
    private String orgid;
    
    private String orgname;
    
    
    
    
    
   
    
    public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getAliPublicKey() {
		return aliPublicKey;
	}

	public void setAliPublicKey(String aliPublicKey) {
		this.aliPublicKey = aliPublicKey;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMechid() {
        return mechid;
    }

    public void setMechid(String mechid) {
        this.mechid = mechid == null ? null : mechid.trim();
    }

    public String getMechname() {
        return mechname;
    }

    public void setMechname(String mechname) {
        this.mechname = mechname == null ? null : mechname.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}