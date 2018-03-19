package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.RoleMenu;
import com.deyi.util.Page;

@Repository
public interface RoleMenuMapper {
	int deleteByPrimaryKey(Long id);
	
	int deleteRoleId(Long roleId);

	int insert(RoleMenu record);

	int insertSelective(RoleMenu record);

	RoleMenu selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RoleMenu record);

	int updateByPrimaryKey(RoleMenu record);

	List<RoleMenu> getRoleMenuList(Page<RoleMenu> page);
	
	List<RoleMenu> getAllRoleMenu();
	
	List<RoleMenu> getMenuByRoleId(String roleId);
	
	List<String> getMenuIdsByRoleId(String roleId);
}