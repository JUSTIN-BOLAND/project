package com.deyi.entity;

import java.io.Serializable;

public class RoleMenu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8098571960109617331L;

	private Long id;

    private Long roleid;

    private Long menuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}