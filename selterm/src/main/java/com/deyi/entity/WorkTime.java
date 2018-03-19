package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class WorkTime implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5219089183799959755L;

	private String id;

    private String storeid;

    private String loginname;

    private Date logintime;

    private Date logouttime;

    private String statisday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    public String getStatisday() {
        return statisday;
    }

    public void setStatisday(String statisday) {
        this.statisday = statisday == null ? null : statisday.trim();
    }
}