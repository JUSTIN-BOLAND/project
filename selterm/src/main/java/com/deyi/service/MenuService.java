package com.deyi.service;

import java.util.List;

import com.deyi.entity.Menu;

public interface MenuService {

	List<Menu> getAllMenus();

	List<Menu> getMenusByUser(String loginName);

	/**
	 * 根据menuId 
	 */
    Menu getMenuById(String id);
}
