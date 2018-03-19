package com.deyi.vo;

import java.io.Serializable;
import java.util.Date;

public class ActionLogVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8153305168640538291L;

	private Integer id;

	private String userid;

	private String type;

	private String subtype;

	private Date createtime;

	private String content;

	private String username;

	private String querystarttime;

	private String queryendtime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the subtype
	 */
	public String getSubtype() {
		return subtype;
	}

	/**
	 * @param subtype
	 *            the subtype to set
	 */
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the querystarttime
	 */
	public String getQuerystarttime() {
		return querystarttime;
	}

	/**
	 * @param querystarttime
	 *            the querystarttime to set
	 */
	public void setQuerystarttime(String querystarttime) {
		this.querystarttime = querystarttime;
	}

	/**
	 * @return the queryendtime
	 */
	public String getQueryendtime() {
		return queryendtime;
	}

	/**
	 * @param queryendtime
	 *            the queryendtime to set
	 */
	public void setQueryendtime(String queryendtime) {
		this.queryendtime = queryendtime;
	}
}
