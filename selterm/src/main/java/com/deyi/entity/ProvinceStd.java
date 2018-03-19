package com.deyi.entity;

import java.io.Serializable;


public class ProvinceStd implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6205687715661672372L;

	private Integer id;

    private String provinceCode;

    private String provinceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }
}