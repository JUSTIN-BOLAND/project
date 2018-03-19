package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class WxPublic implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1248600574468847009L;

	private String id;

    private String merchid;

    private String merchname;

    private String appId;

    private String mchId;

    private String applicKey;

    private String certLocalpath;


    private String creator;

    private Date createtime;
    
    private String audit;
    
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

    public String getMerchid() {
        return merchid;
    }

    public void setMerchid(String merchid) {
        this.merchid = merchid == null ? null : merchid.trim();
    }

    public String getMerchname() {
        return merchname;
    }

    public void setMerchname(String merchname) {
        this.merchname = merchname == null ? null : merchname.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getApplicKey() {
        return applicKey;
    }

    public void setApplicKey(String applicKey) {
        this.applicKey = applicKey == null ? null : applicKey.trim();
    }

    public String getCertLocalpath() {
        return certLocalpath;
    }

    public void setCertLocalpath(String certLocalpath) {
        this.certLocalpath = certLocalpath == null ? null : certLocalpath.trim();
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