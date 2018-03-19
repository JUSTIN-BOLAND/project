package com.deyi.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.MenuDao;
import com.deyi.entity.Menu;
import com.deyi.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> getAllMenus() {
		return menuDao.getAllMenus();
	}

	@Override
	public List<Menu> getMenusByUser(String loginName) {
		return menuDao.getMenusByUser(loginName);
	}

	@Override
	public Menu getMenuById(String id) {
		return menuDao.getMenuById(id);
	}

	
	
}
