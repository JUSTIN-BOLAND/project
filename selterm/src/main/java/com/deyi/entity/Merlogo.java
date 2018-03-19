package com.deyi.entity;

import java.util.Date;

public class Merlogo {
    private Integer id;

    private Integer merid;

    private String mername;

    private String merlogo;

    private String audit;

    private Date createtime;

    private String deletestatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerid() {
        return merid;
    }

    public void setMerid(Integer merid) {
        this.merid = merid;
    }

    public String getMername() {
        return mername;
    }

    public void setMername(String mername) {
        this.mername = mername == null ? null : mername.trim();
    }

    public String getMerlogo() {
        return merlogo;
    }

    public void setMerlogo(String merlogo) {
        this.merlogo = merlogo == null ? null : merlogo.trim();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(String deletestatus) {
        this.deletestatus = deletestatus == null ? null : deletestatus.trim();
    }
}