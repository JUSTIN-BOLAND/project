package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.Menu;

@Repository("menuDao")
public interface MenuDao {
	/**
	 * 获取所有正常状态的菜单
	 * @return
	 */
	List<Menu> getAllMenus();
	
	/**
	 * 获取某个用户的权限菜单
	 * @return
	 */
	List<Menu> getMenusByUser(String loginName);
	
	/**
	 * 根据menuId 得到menu的列表
	 */
    Menu getMenuById(String id);
	
}
