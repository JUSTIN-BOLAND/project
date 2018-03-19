package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class PayLog implements Serializable{

    private static final long serialVersionUID = -3848895386356901206L;
    private Integer id ;
    private Integer workType;
    private Integer subWorkType;
    private String marchantId;
    private String outTradeNo;
    private String buyerId;
    private String subject;
    private String totalAmount;
    private String discountableAmount;
    private String undiscountableAmount;
    private String body;
    private String storeId;
    private String subMerchantId;
    private String payStatus;
    private Date creatTime;
    private String operatorId;
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Integer getSubWorkType() {
        return subWorkType;
    }

    public void setSubWorkType(Integer subWorkType) {
        this.subWorkType = subWorkType;
    }

    public String getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(String marchantId) {
        this.marchantId = marchantId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscountableAmount() {
        return discountableAmount;
    }

    public void setDiscountableAmount(String discountableAmount) {
        this.discountableAmount = discountableAmount;
    }

    public String getUndiscountableAmount() {
        return undiscountableAmount;
    }

    public void setUndiscountableAmount(String undiscountableAmount) {
        this.undiscountableAmount = undiscountableAmount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    ;
}