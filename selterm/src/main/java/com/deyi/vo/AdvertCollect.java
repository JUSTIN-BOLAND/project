package com.deyi.vo;

import java.util.Date;

/**
 * 广告统计
 * @author Enzo
 * @date 2017年2月24日
 */
public class AdvertCollect {

	private Integer merid;
	
	private String mername;
	
	private Integer sumAdvert;
	
	private Integer successAdvert;
	
	private Integer completeAdvert;
	
	
	private Date starttime;
	
	private Date endtime;
	
	
	private String merids;
	
	
	

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
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the merid
	 */
	public Integer getMerid() {
		return merid;
	}

	/**
	 * @param merid the merid to set
	 */
	public void setMerid(Integer merid) {
		this.merid = merid;
	}

	/**
	 * @return the mername
	 */
	public String getMername() {
		return mername;
	}

	/**
	 * @param mername the mername to set
	 */
	public void setMername(String mername) {
		this.mername = mername;
	}

	/**
	 * @return the sumAdvert
	 */
	public Integer getSumAdvert() {
		return sumAdvert;
	}

	/**
	 * @param sumAdvert the sumAdvert to set
	 */
	public void setSumAdvert(Integer sumAdvert) {
		this.sumAdvert = sumAdvert;
	}

	/**
	 * @return the successAdvert
	 */
	public Integer getSuccessAdvert() {
		return successAdvert;
	}

	/**
	 * @param successAdvert the successAdvert to set
	 */
	public void setSuccessAdvert(Integer successAdvert) {
		this.successAdvert = successAdvert;
	}

	/**
	 * @return the completeAdvert
	 */
	public Integer getCompleteAdvert() {
		return completeAdvert;
	}

	/**
	 * @param completeAdvert the completeAdvert to set
	 */
	public void setCompleteAdvert(Integer completeAdvert) {
		this.completeAdvert = completeAdvert;
	}
	
	
	
}
