package com.deyi.vo;

import java.io.Serializable;
import java.util.Date;

public class OrgCollectDayVo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1700466236732146953L;
	private Integer id;
	private Long orgid;
	private String orgname;
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
	
	
	private String querystarttime;
	private String queryendtime;
	
	private String orgIds;
	
	
    private double alirefundMoney;
    
    private double wxrefundMoney;
    private double unionrefundMoney;
    private double ratemoney;
    private double sumratemoney;
    
    

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

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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

	public String getQuerystarttime() {
		return querystarttime;
	}

	public void setQuerystarttime(String querystarttime) {
		this.querystarttime = querystarttime;
	}

	public String getQueryendtime() {
		return queryendtime;
	}

	public void setQueryendtime(String queryendtime) {
		this.queryendtime = queryendtime;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
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

	public double getSumratemoney() {
		return sumratemoney;
	}

	public void setSumratemoney(double sumratemoney) {
		this.sumratemoney = sumratemoney;
	}
	

}
