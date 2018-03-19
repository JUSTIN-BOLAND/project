package com.deyi.entity;

import java.io.Serializable;

public class Disparam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2581776102561487629L;

	public Disparam() {
		
	}
	
	public Disparam(String name,String value,String key,String storeid) {
		this.name = name;
		this.value = value;
		this.key = key;
		this.storeid = storeid;
	}
    private String id;

    private String name;

    private String value;

    private String storeid;

    private String key;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

    
}