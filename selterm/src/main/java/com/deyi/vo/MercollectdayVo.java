package com.deyi.vo;

import java.io.Serializable;
import java.util.Date;

public class MercollectdayVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 59678611276251027L;

	private Integer id;

	private Integer mer_id;

	private String mer_code;
	private String mer_name;

	private Integer wxcount;

	private Integer alicount;
	private Integer unioncount;

	private double alipaymoney;

	private double wxpaymoney;
	private double unionpaymoney;

	private double summoney;
	private double ratemoney;
	private double sumratemoney;

	private double sumrefundmoney;

	private Date starttime;

	private Date endtime;

	private Date creattime;

	// extend
	private String querystarttime;

	private String queryendtime;
	
	
    private double alirefundMoney;
    
    private double wxrefundMoney;
    private double unionrefundMoney;
    
    
    private String month;
    
    
    private String flag = "1";   // 1.进行日统计   2.进行按月统计
    
    
    private String mertype;
    
    
    
    
    
    

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
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
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

	private String orgIds;// 扩展查询字段
	private String merchantIds;// 扩展查询字段

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

	public String getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
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

	public String getMer_code() {
		return mer_code;
	}

	public void setMer_code(String mer_code) {
		this.mer_code = mer_code;
	}

}
