package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class UnionPublic implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -896726643826792691L;

	private Long id;

    private String merchid;
    private String merchname;

    private String mchid;

    private String creator;

    private Date createtime;

    private String audit;

    private String orgid;

    private String orgname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

	public String getMerchid() {
		return merchid;
	}

	public void setMerchid(String merchid) {
		this.merchid = merchid;
	}

	public String getMerchname() {
		return merchname;
	}

	public void setMerchname(String merchname) {
		this.merchname = merchname;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
}