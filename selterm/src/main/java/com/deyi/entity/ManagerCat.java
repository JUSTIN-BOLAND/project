package com.deyi.entity;

import java.io.Serializable;

/**
 * Created by root on 2017/10/12 0012.
 */
public class ManagerCat implements Serializable {


    private static final long serialVersionUID = 13395496529345323L;
    private Integer id;


    private String catCode;

    private String catName;
    private Integer parentId;

    private String rate;

    private String cycle;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }




}
