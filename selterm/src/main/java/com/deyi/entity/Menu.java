package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable{

	private static final long serialVersionUID = 8236469690933529842L;
	private String menuId;                                              
	private String menuName;                                        
	private String menuUrl;                                            
	private String meunIcon;                                          
	private String parentMenuId;           
	private String menuLevel;    
	private String menuStatus;                    
	private String isNode;                             
	private String menuClass;                                
	private String creator;                    
	private Date createTime ;
	/**
	 * 扩展字段
	 */
	private Boolean checked;
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMeunIcon() {
		return meunIcon;
	}
	public void setMeunIcon(String meunIcon) {
		this.meunIcon = meunIcon;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	
	public String getIsNode() {
		return isNode;
	}
	public void setIsNode(String isNode) {
		this.isNode = isNode;
	}
	public String getMenuClass() {
		return menuClass;
	}
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	                                                 
}
