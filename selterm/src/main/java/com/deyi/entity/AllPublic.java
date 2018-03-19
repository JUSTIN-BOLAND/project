package com.deyi.entity;

public class AllPublic {
	private Integer merId;
	private String merName;
	private String wxAppId;
	private String wxMerCode;
	private String aliAppId;
	private String aliMerCode;
	private String aliPkey;
	private String unionMerCode;
	private  String notifyUrl;


	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}



	public String getWxAppId() {
		return wxAppId;
	}
	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}
	public String getWxMerCode() {
		return wxMerCode;
	}
	public void setWxMerCode(String wxMerCode) {
		this.wxMerCode = wxMerCode;
	}
	public String getAliAppId() {
		return aliAppId;
	}
	public void setAliAppId(String aliAppId) {
		this.aliAppId = aliAppId;
	}
	public String getAliMerCode() {
		return aliMerCode;
	}
	public void setAliMerCode(String aliMerCode) {
		this.aliMerCode = aliMerCode;
	}
	public String getAliPkey() {
		return aliPkey;
	}
	public void setAliPkey(String aliPkey) {
		this.aliPkey = aliPkey;
	}
	public String getUnionMerCode() {
		return unionMerCode;
	}
	public void setUnionMerCode(String unionMerCode) {
		this.unionMerCode = unionMerCode;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
}
