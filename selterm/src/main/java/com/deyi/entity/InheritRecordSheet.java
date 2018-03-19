package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class InheritRecordSheet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5606410991407557590L;

	private Long id;

    private Long rawid;

    private String rawname;

    private String rawaccount;

    private String rawrolename;

    private Long inheritid;

    private String inheritname;

    private String inheritaccount;

    private String inheritrolename;
    
    private Date inheritTime;

    
    
    public Date getInheritTime() {
		return inheritTime;
	}

	public void setInheritTime(Date inheritTime) {
		this.inheritTime = inheritTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRawid() {
        return rawid;
    }

    public void setRawid(Long rawid) {
        this.rawid = rawid;
    }

    public String getRawname() {
        return rawname;
    }

    public void setRawname(String rawname) {
        this.rawname = rawname == null ? null : rawname.trim();
    }

    public String getRawaccount() {
        return rawaccount;
    }

    public void setRawaccount(String rawaccount) {
        this.rawaccount = rawaccount == null ? null : rawaccount.trim();
    }

    public String getRawrolename() {
        return rawrolename;
    }

    public void setRawrolename(String rawrolename) {
        this.rawrolename = rawrolename == null ? null : rawrolename.trim();
    }

    public Long getInheritid() {
        return inheritid;
    }

    public void setInheritid(Long inheritid) {
        this.inheritid = inheritid;
    }

    public String getInheritname() {
        return inheritname;
    }

    public void setInheritname(String inheritname) {
        this.inheritname = inheritname == null ? null : inheritname.trim();
    }

    public String getInheritaccount() {
        return inheritaccount;
    }

    public void setInheritaccount(String inheritaccount) {
        this.inheritaccount = inheritaccount == null ? null : inheritaccount.trim();
    }

    public String getInheritrolename() {
        return inheritrolename;
    }

    public void setInheritrolename(String inheritrolename) {
        this.inheritrolename = inheritrolename == null ? null : inheritrolename.trim();
    }
}