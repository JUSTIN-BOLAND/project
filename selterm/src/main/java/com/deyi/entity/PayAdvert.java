package com.deyi.entity;

import java.util.Date;

public class PayAdvert {
    private Integer id;

    private String advname;

    private String mername;
    
    private Integer merid;

    private String advimage;

    private String advlink;

    private String advplace;

    private Date starttime;

    private Date endtime;

    private String pushstatus;

    private String deletestatus;

    private String auditstatus;

    private Integer clicknum;

    private Date createtime;

    private String createor;
    
    private String createname;
    
    private int remainDay;   // 剩余天数
    
    
    private String merids;
    
    private String storeIds;
    
    private String storeName;//门店名称
    
    private Integer storeId;//门店名称
    
    private String promulgator; //发布者(登录名)
    
    public String getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(String storeIds) {
		this.storeIds = storeIds;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getPromulgator() {
		return promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the merids
	 */
	public String getMerids() {
		return merids;
	}

	/**
	 * @param merids the merids to set
	 */
	public void setMerids(String merids) {
		this.merids = merids;
	}

	/**
	 * @return the createname
	 */
	public String getCreatename() {
		return createname;
	}

	/**
	 * @param createname the createname to set
	 */
	public void setCreatename(String createname) {
		this.createname = createname;
	}

	/**
	 * @return the remainDay
	 */
	public int getRemainDay() {
		return remainDay;
	}

	/**
	 * @param remainDay the remainDay to set
	 */
	public void setRemainDay(int remainDay) {
		this.remainDay = remainDay;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvname() {
        return advname;
    }

    public void setAdvname(String advname) {
        this.advname = advname == null ? null : advname.trim();
    }

    public String getMername() {
        return mername;
    }

    public void setMername(String mername) {
        this.mername = mername == null ? null : mername.trim();
    }

    public Integer getMerid() {
        return merid;
    }

    public void setMerid(Integer merid) {
        this.merid = merid;
    }

    public String getAdvimage() {
        return advimage;
    }

    public void setAdvimage(String advimage) {
        this.advimage = advimage == null ? null : advimage.trim();
    }

    public String getAdvlink() {
        return advlink;
    }

    public void setAdvlink(String advlink) {
        this.advlink = advlink == null ? null : advlink.trim();
    }

    public String getAdvplace() {
        return advplace;
    }

    public void setAdvplace(String advplace) {
        this.advplace = advplace == null ? null : advplace.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(String pushstatus) {
        this.pushstatus = pushstatus == null ? null : pushstatus.trim();
    }

    public String getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(String deletestatus) {
        this.deletestatus = deletestatus == null ? null : deletestatus.trim();
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus == null ? null : auditstatus.trim();
    }

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateor() {
        return createor;
    }

    public void setCreateor(String createor) {
        this.createor = createor == null ? null : createor.trim();
    }
}