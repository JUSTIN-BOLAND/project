package com.deyi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TeQrcode implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3342350864763423988L;

	private Long id;

    private String qrcodeadress;

    private String paytype;

    private String fixation;

    private BigDecimal mony;

    private Long merid;

    private String name;

    private Long storeid;

    private String storename;

    private Date creattime;
    
    private String storeids;


    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler;
    }

    private String saler;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;
    

    public String getStoreids() {
		return storeids;
	}

	public void setStoreids(String storeids) {
		this.storeids = storeids;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrcodeadress() {
        return qrcodeadress;
    }

    public void setQrcodeadress(String qrcodeadress) {
        this.qrcodeadress = qrcodeadress == null ? null : qrcodeadress.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public String getFixation() {
        return fixation;
    }

    public void setFixation(String fixation) {
        this.fixation = fixation == null ? null : fixation.trim();
    }

    public BigDecimal getMony() {
        return mony;
    }

    public void setMony(BigDecimal mony) {
        this.mony = mony;
    }

    public Long getMerid() {
        return merid;
    }

    public void setMerid(Long merid) {
        this.merid = merid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }
}