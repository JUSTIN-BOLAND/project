package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Mercollectday implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9213892670068331257L;

	private Integer id;
    
    private Integer mer_id;
    
    private String mer_code;
    private String mer_name;
    private Long org_id;

	private Integer wxcount;

    private Integer alicount;
    private Integer unioncount;

    private double alipaymoney;

    private double wxpaymoney;
    private double unionpaymoney;

    private double summoney;
    private double ratemoney;

    private double sumrefundmoney;

    private Date starttime;

    private Date endtime;

    private Date creattime;
    
    private double alirefundMoney;
    
    private String mertype;
    
    

	private double wxrefundMoney;
	private double unionrefundMoney;
    
    
	

	/**
	 * @return the mertype
	 */
	public String getMertype() {
		return mertype;
	}

	/**
	 * @param mertype the mertype to set
	 */
	public void setMertype(String mertype) {
		this.mertype = mertype;
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

	public Integer getMer_id() {
		return mer_id;
	}

	public void setMer_id(Integer mer_id) {
		this.mer_id = mer_id;
	}

	public String getMer_name() {
		return mer_name;
	}

	public void setMer_name(String mer_name) {
		this.mer_name = mer_name;
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

	public double getUnionrefundMoney() {
		return unionrefundMoney;
	}

	public void setUnionrefundMoney(double unionrefundMoney) {
		this.unionrefundMoney = unionrefundMoney;
	}

	@Override
	public String toString() {
		return "Mercollectday [id=" + id + ", mer_id=" + mer_id + ", mer_name=" + mer_name + ", wxcount=" + wxcount
				+ ", alicount=" + alicount + ", unioncount=" + unioncount + ", alipaymoney=" + alipaymoney
				+ ", wxpaymoney=" + wxpaymoney + ", unionpaymoney=" + unionpaymoney + ", summoney=" + summoney
				+ ", sumrefundmoney=" + sumrefundmoney + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", creattime=" + creattime + ", alirefundMoney=" + alirefundMoney + ", mertype=" + mertype
				+ ", wxrefundMoney=" + wxrefundMoney + ", unionrefundMoney=" + unionrefundMoney + "]";
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

	public double getRatemoney() {
		return ratemoney;
	}

	public void setRatemoney(double ratemoney) {
		this.ratemoney = ratemoney;
	}

	public String getMer_code() {
		return mer_code;
	}

	public void setMer_code(String mer_code) {
		this.mer_code = mer_code;
	}

	public Long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

    
    
    

}