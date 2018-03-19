package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Bulletin implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2186413787515859836L;

	private Long id;

    private String code;

    private String title;

    private Date createtime;

    private String status;

    private Long orgid;

    private String content;

    private String creator;
    
    //扩展字段 
    private Long orgidTwo;
    
  //扩展字段 
    private Long day;//时间相减天数
    
    

    public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

	public Long getOrgidTwo() {
		return orgidTwo;
	}

	public void setOrgidTwo(Long orgidTwo) {
		this.orgidTwo = orgidTwo;
	}
    
    
}