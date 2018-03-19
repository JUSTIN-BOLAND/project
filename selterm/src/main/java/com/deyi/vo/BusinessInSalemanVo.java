package com.deyi.vo;

import java.io.Serializable;

public class BusinessInSalemanVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -695176365081474264L;

	private String creator;
	
	private int one;
	
	private int two;
	
	private int sumcount;
	
	private String querystarttime;
	
	private String queryendtime;
	
	private String orgids;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}

	public int getSumcount() {
		return sumcount;
	}

	public void setSumcount(int sumcount) {
		this.sumcount = sumcount;
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

	public String getOrgids() {
		return orgids;
	}

	public void setOrgids(String orgids) {
		this.orgids = orgids;
	}
	
	
	
}
