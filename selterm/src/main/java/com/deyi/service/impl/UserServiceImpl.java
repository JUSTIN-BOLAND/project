package com.deyi.service.impl;

import com.deyi.dao.UserDao;
import com.deyi.entity.Store;
import com.deyi.entity.UserInfo;
import com.deyi.service.RoleService;
import com.deyi.service.UserService;
import com.deyi.util.Page;
import com.deyi.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;




	@Override
	public UserInfo getUser(String loginName, String passwd) {
		return userDao.getUserPassword(loginName, passwd);
	}

	@Override
	public UserInfo getUser(String loginName) {

		return userDao.selectByPrimaryKey(loginName);
	}

	@Override
	public void save(UserInfo userInfo) {
		userDao.insert(userInfo);
	}

	@Override
	public void upadeUser(UserInfo userInfo) {
		userDao.updateByPrimaryKeySelective(userInfo);

	}

	@Override
	public void deleteUserInfo(String id) {
		userDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<UserVo> getUserInfoList(Page<UserVo> page) {
		return userDao.getUserInfoList(page);
	}

	@Override
	public List<Store> getStores(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getUserAccont(String loginName, String passwd) {
		return userDao.getAccountPassword(loginName, passwd);
	}

	@Override
	public List<String> getUseridByRoleList(String rolelist) {
		return this.userDao.getUseridByRoleList(rolelist);
	}

	@Override
	public int deleteByRoleId(String roleId) {
		return userDao.deleteByRoleId(roleId);
	}

	@Override
	public UserInfo getUserById(String id){
		return userDao.getUserById(id);
	}
}
