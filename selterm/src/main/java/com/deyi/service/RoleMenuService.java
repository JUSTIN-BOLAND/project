package com.deyi.service;

import java.util.List;

import com.deyi.entity.RoleMenu;
import com.deyi.util.Page;

public interface RoleMenuService {
	List<RoleMenu> getUserInfoList(Page<RoleMenu> page);

	List<RoleMenu> getAllRoleMenu();
	
	RoleMenu selectByPrimaryKey(Long id);
	
	int deleteRoleId(Long roleId);
	
	int deleteByPrimaryKey(Long id);
	
	int insertSelective(RoleMenu record);
	
	List<RoleMenu> getMenuByRoleId(String roleId);
	
	List<String> getMenuIdsByRoleId(String roleId);
}
