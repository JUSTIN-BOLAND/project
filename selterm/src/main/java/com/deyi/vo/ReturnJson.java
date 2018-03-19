/**  
* @Title: ReturnJson.java
* @Package com.yiqi.controller
* @Description: 深圳市得壹科技有限公司
*/ 
package com.deyi.vo;

import java.io.Serializable;

/**
 * @Description: 页面ajax 访问所返回类参数
 * @author hejx
 * @date 2015年10月21日 
 *
 */
public class ReturnJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1616879649656755399L;
	/**
	 * 0 为成功 其它为失败
	 */
	private String error;
	/**
	 * 成功或失败时返回 信息
	 */
	private String message;
	/**
	 * list
	 */
	private Object itemList;
	/**
	 * 单个类
	 */
	private Object item;
	private Object item2;
	/**
	 * 单个参数
	 */
	private Object itemString;
	
	public Object getItemString() {
		return itemString;
	}
	public void setItemString(Object itemString) {
		this.itemString = itemString;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
	public Object getItemList() {
		return itemList;
	}
	public void setItemList(Object itemList) {
		this.itemList = itemList;
	}
	public Object getItem2() {
		return item2;
	}
	public void setItem2(Object item2) {
		this.item2 = item2;
	}
	
	
}
