package com.deyi.entity;

import java.util.Date;

/**
 * Created by root on 2017/12/3 0003.
 */
public class InfLog {

    private String orderNo;
    private String request;
    private String resonse;

    public String getInfType() {
        return infType;
    }

    public void setInfType(String infType) {
        this.infType = infType;
    }

    private String infType;
    private Date logDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResonse() {
        return resonse;
    }

    public void setResonse(String resonse) {
        this.resonse = resonse;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }


}
