package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class RechargePlan implements Serializable{

	private static final long serialVersionUID = 8236469690933529842L;
	private Integer id;

	private String planName;

	private Double refillAmount;

	private Double giftAmount;

	private Double actualAmount;

	private Double price;

	private String planImg;

	private String memo;

	private Integer status;

	private Date createTime;

	private String flag;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	private String creator;


	public Integer getId(){
	    return id;
	}
	public void setId(Integer id){
	    this.id=id;
	}

	public String getPlanName(){
	    return planName;
	}
	public void setPlanName(String planName){
	    this.planName=planName;
	}

	public Double getRefillAmount(){
	    return refillAmount;
	}
	public void setRefillAmount(Double refillAmount){
	    this.refillAmount=refillAmount;
	}

	public Double getGiftAmount(){
	    return giftAmount;
	}
	public void setGiftAmount(Double giftAmount){
	    this.giftAmount=giftAmount;
	}

	public Double getActualAmount(){
	    return actualAmount;
	}
	public void setActualAmount(Double actualAmount){
	    this.actualAmount=actualAmount;
	}

	public Double getPrice(){
	    return price;
	}
	public void setPrice(Double price){
	    this.price=price;
	}

	public String getPlanImg(){
	    return planImg;
	}
	public void setPlanImg(String planImg){
	    this.planImg=planImg;
	}

	public String getMemo(){
	    return memo;
	}
	public void setMemo(String memo){
	    this.memo=memo;
	}

	public Integer getStatus(){
	    return status;
	}
	public void setStatus(Integer status){
	    this.status=status;
	}

	public Date getCreateTime(){
	    return createTime;
	}
	public void setCreateTime(Date createTime){
	    this.createTime=createTime;
	}

	public String getFlag(){
	    return flag;
	}
	public void setFlag(String flag){
	    this.flag=flag;
	}

}