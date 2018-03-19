package com.deyi.entity;

import java.io.Serializable;

/**
 * Created by root on 2017/10/12 0012.
 */
public class DistrictStd implements Serializable {
    private static final long serialVersionUID = 7118825401634155161L;

    private Integer id;


    private String districtCode;

    private String districtName;

    private Integer cityId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

}
