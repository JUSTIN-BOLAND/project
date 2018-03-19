package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class DealerPay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6651577217128858694L;




    private String 	dealerId;


    private String wxMchId;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getWxMchId() {
        return wxMchId;
    }

    public void setWxMchId(String wxMchId) {
        this.wxMchId = wxMchId;
    }

    public String getAliMachId() {
        return aliMachId;
    }

    public void setAliMachId(String aliMachId) {
        this.aliMachId = aliMachId;
    }

    private String aliMachId;

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
    }

    private String appAuthToken;






}