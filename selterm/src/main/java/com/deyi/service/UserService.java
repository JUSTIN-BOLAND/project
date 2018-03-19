package com.deyi.service;

import com.deyi.entity.Store;
import com.deyi.entity.UserInfo;
import com.deyi.util.Page;
import com.deyi.vo.UserVo;

import java.util.List;

public interface UserService {
	UserInfo getUser(String loginName, String passwd);

	UserInfo getUserAccont(String loginName, String passwd);

	List<Store> getStores(Store store);

	UserInfo getUser(String loginName);

	void save(UserInfo userInfo);

	void upadeUser(UserInfo userInfo);

	void deleteUserInfo(String id);

	List<UserVo> getUserInfoList(Page<UserVo> page);

	/**
	 * 根据角色得到用户的有登录ID
	 * @param rolelist
	 * @return
	 */
	List<String> getUseridByRoleList(String rolelist);

	int deleteByRoleId(String roleId);

	UserInfo getUserById(String id);
}
