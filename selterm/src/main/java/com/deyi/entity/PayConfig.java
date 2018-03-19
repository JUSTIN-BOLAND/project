package com.deyi.entity;

import java.io.Serializable;

/**
 * Created by root on 2017/11/5 0005.
 */
public class PayConfig implements Serializable {

    private static final long serialVersionUID = 7401926219683772372L;
    private String wxAppId;
    private String wxMchId;
    private String wxSecret;
    private String wxKey;

    private String aliAppId;
    private String aliMchId;
    private String aliSecret;
    private String aliKey;

    public String getWxSecret() {
        return wxSecret;
    }

    public void setWxSecret(String wxSecret) {
        this.wxSecret = wxSecret;
    }

    public String getAliSecret() {
        return aliSecret;
    }

    public void setAliSecret(String aliSecret) {
        this.aliSecret = aliSecret;
    }



    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getWxMchId() {
        return wxMchId;
    }

    public void setWxMchId(String wxMchId) {
        this.wxMchId = wxMchId;
    }


    public String getWxKey() {
        return wxKey;
    }

    public void setWxKey(String wxKey) {
        this.wxKey = wxKey;
    }

    public String getAliAppId() {
        return aliAppId;
    }

    public void setAliAppId(String aliAppId) {
        this.aliAppId = aliAppId;
    }

    public String getAliMchId() {
        return aliMchId;
    }

    public void setAliMchId(String aliMchId) {
        this.aliMchId = aliMchId;
    }



    public String getAliKey() {
        return aliKey;
    }

    public void setAliKey(String aliKey) {
        this.aliKey = aliKey;
    }


}
