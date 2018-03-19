package com.deyi.entity;

import java.io.Serializable;

public class Description implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3351515430054283484L;

	private Integer versioncode;

    private String versionname;

    private String description;

    private Integer platform;

    private String url;

    private String updatetime;

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname == null ? null : versionname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}