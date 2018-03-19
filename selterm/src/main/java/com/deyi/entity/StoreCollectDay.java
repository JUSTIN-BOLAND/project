package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class StoreCollectDay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2041318150493837503L;

	private Integer id;

    private Long storeid;

    private String storename;

    private Integer wxcount;

    private Integer alicount;
    private Integer unioncount;

    private double alipaymoney;

    private double wxpaymoney;
    private double unionpaymoney;

    private double summoney;

    private double sumrefundmoney;

    private Date starttime;

    private Date endtime;

    private Date creattime;
    
    
    private double alirefundMoney;
    
    private double wxrefundMoney;
    private double unionrefundMoney;
    private double ratemoney;
    
    
    

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StoreCollectDay [id=" + id + ", storeid=" + storeid + ", storename=" + storename + ", wxcount="
				+ wxcount + ", alicount=" + alicount + ", alipaymoney=" + alipaymoney + ", wxpaymoney=" + wxpaymoney
				+ ", summoney=" + summoney + ", sumrefundmoney=" + sumrefundmoney + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", creattime=" + creattime + ", alirefundMoney=" + alirefundMoney
				+ ", wxrefundMoney=" + wxrefundMoney + "]";
	}

	/**
	 * @return the alirefundMoney
	 */
	public double getAlirefundMoney() {
		return alirefundMoney;
	}

	/**
	 * @param alirefundMoney the alirefundMoney to set
	 */
	public void setAlirefundMoney(double alirefundMoney) {
		this.alirefundMoney = alirefundMoney;
	}

	/**
	 * @return the wxrefundMoney
	 */
	public double getWxrefundMoney() {
		return wxrefundMoney;
	}

	/**
	 * @param wxrefundMoney the wxrefundMoney to set
	 */
	public void setWxrefundMoney(double wxrefundMoney) {
		this.wxrefundMoney = wxrefundMoney;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public Integer getWxcount() {
        return wxcount;
    }

    public void setWxcount(Integer wxcount) {
        this.wxcount = wxcount;
    }

    public Integer getAlicount() {
        return alicount;
    }

    public void setAlicount(Integer alicount) {
        this.alicount = alicount;
    }

    public double getAlipaymoney() {
        return alipaymoney;
    }

    public void setAlipaymoney(double alipaymoney) {
        this.alipaymoney = alipaymoney;
    }

    public double getWxpaymoney() {
        return wxpaymoney;
    }

    public void setWxpaymoney(double wxpaymoney) {
        this.wxpaymoney = wxpaymoney;
    }

    public double getSummoney() {
        return summoney;
    }

    public void setSummoney(double summoney) {
        this.summoney = summoney;
    }

    public double getSumrefundmoney() {
        return sumrefundmoney;
    }

    public void setSumrefundmoney(double sumrefundmoney) {
        this.sumrefundmoney = sumrefundmoney;
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

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

	public Integer getUnioncount() {
		return unioncount;
	}

	public void setUnioncount(Integer unioncount) {
		this.unioncount = unioncount;
	}

	public double getUnionpaymoney() {
		return unionpaymoney;
	}

	public void setUnionpaymoney(double unionpaymoney) {
		this.unionpaymoney = unionpaymoney;
	}

	public double getUnionrefundMoney() {
		return unionrefundMoney;
	}

	public void setUnionrefundMoney(double unionrefundMoney) {
		this.unionrefundMoney = unionrefundMoney;
	}

	public double getRatemoney() {
		return ratemoney;
	}

	public void setRatemoney(double ratemoney) {
		this.ratemoney = ratemoney;
	}
}