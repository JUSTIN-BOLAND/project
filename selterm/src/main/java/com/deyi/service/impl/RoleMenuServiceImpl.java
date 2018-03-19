package com.deyi.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.RoleMenuMapper;
import com.deyi.entity.RoleMenu;
import com.deyi.service.RoleMenuService;
import com.deyi.util.Page;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public List<RoleMenu> getUserInfoList(Page<RoleMenu> page) {
		return roleMenuMapper.getRoleMenuList(page);
	}

	@Override
	public RoleMenu selectByPrimaryKey(Long id) {
		return roleMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<RoleMenu> getAllRoleMenu() {
		return roleMenuMapper.getAllRoleMenu();
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return roleMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(RoleMenu record) {
		return roleMenuMapper.insertSelective(record);
	}

	@Override
	public int deleteRoleId(Long roleId) {
		return roleMenuMapper.deleteRoleId(roleId);
	}

	@Override
	public List<RoleMenu> getMenuByRoleId(String roleId) {
		return roleMenuMapper.getMenuByRoleId(roleId);
	}

	@Override
	public List<String> getMenuIdsByRoleId(String roleId) {
		return roleMenuMapper.getMenuIdsByRoleId(roleId);
	}


	
	
	
}
