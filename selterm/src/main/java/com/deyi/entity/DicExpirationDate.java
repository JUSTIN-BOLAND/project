package com.deyi.entity;

import java.io.Serializable;

public class DicExpirationDate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6651577217128858694L;

	private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


}