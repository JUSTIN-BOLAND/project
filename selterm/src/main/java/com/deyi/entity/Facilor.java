package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Facilor implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3070382889996579310L;

	private Long id;

    private String appid;

    private String mchid;

    private String key;

    private Date createtime;
    private String certpath;
    private String secret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid == null ? null : mchid.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getCertpath() {
		return certpath;
	}

	public void setCertpath(String certpath) {
		this.certpath = certpath;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}