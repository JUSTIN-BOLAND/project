package com.deyi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.deyi.service.OrgService;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.UserOperatorMapper;
import com.deyi.entity.Org;
import com.deyi.entity.Role;
import com.deyi.entity.UserInfo;
import com.deyi.entity.UserOperator;
import com.deyi.service.RoleService;
import com.deyi.service.UserOperatorService;
import com.deyi.util.Constants;
import com.deyi.util.GetIdsFromList;
import com.deyi.util.Page;

@Service
public class UserOperatorServiceImpl implements UserOperatorService {

	@Autowired
	private UserOperatorMapper userOperatorMapper;

	@Autowired
	private OrgService orgService;
	
	
	@Autowired
	private RoleService roleService;


	@Override
	public int insert(UserOperator record) {
		return userOperatorMapper.insert(record);
	}

	@Override
	public int insertSelective(UserOperator record) {
		return userOperatorMapper.insertSelective(record);
	}

	@Override
	public UserOperator selectByPrimaryKey(String id) {
		return userOperatorMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserOperator> getUserOperatorMapperList(Page<UserOperator> page) {
		return userOperatorMapper.getUserOperatorMapperList(page);
	}

	@Override
	public int updateByPrimaryKeySelective(UserOperator record) {
		return userOperatorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return userOperatorMapper.deleteByPrimaryKey(id);
	}

	@Override
	public UserOperator selectByPrimaryAccountName(String accountName) {
		return userOperatorMapper.selectByPrimaryAccountName(accountName);
	}

	@Override
	public List<UserOperator> selectByOrgid(String id) {
		return userOperatorMapper.selectByOrgid(id);
	}

	@Override
	public String getUserIdsbyLoginname(UserInfo userInfo) {

		List<String> arrayList = new ArrayList<String>();
		if (Constants.USER_OPERATOR.equals(userInfo.getType())) {
			List<Org> orgsByOrgAccount = orgService.getOrgsByOrgAccount(userInfo.getId());

			for (Org org : orgsByOrgAccount) {
				List<UserOperator> selectByOrgid = selectByOrgid(org.getId());
				for (UserOperator userOperator : selectByOrgid) {
					arrayList.add(Long.toString(userOperator.getId()));
				}
			}

			return GetIdsFromList.getIdsByList(arrayList);
			// 用户表中的 当登录用户是用户表时的时候，
		} else if (Constants.SYS_USER_OPERATOR.equals(userInfo.getType())) {
			
			UserOperator userOperator = selectByPrimaryAccountName(userInfo.getId());
			
			Role role = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
			if("6".equals(role.getUsertype()) || "7".equals(role.getUsertype())){
				Org org = orgService.selectByPrimaryKey(Long.valueOf(userOperator.getOrgId()));
				List<Org> orgsByOrgAccount = orgService.getOrgsByOrgAccount(org.getOrgAccount());
				List<UserOperator> selectByOrgid = new ArrayList<>();
				for (Org org2 : orgsByOrgAccount) {
					List<UserOperator> selectByOrgid2 = selectByOrgid(org2.getId());
					selectByOrgid.addAll(selectByOrgid2);
				}
				return GetIdsFromList.getIdsByUserList(selectByOrgid);
			}
			
			
			
			UserOperator selectbyid = selectbyid(userOperator.getId());
			List<String> getalluserid = getalluserid(selectbyid);
//			List<String> rolelist = roleService.selectGetChildrenRoleAndSelf(Long.parseLong(userInfo.getRoleid()));
//			String idsByList = GetIdsFromList.getIdsByList(rolelist);
//			List<String> useridlist = userService.getUseridByRoleList(idsByList);
			String userids = GetIdsFromList.getIdsByList(getalluserid);

			return userids;
		}
		return "''";
	}

	@Override
	public UserOperator selectbyid(Long id) {
		return userOperatorMapper.selectbyid(id);
	}
	@Override
	public List<UserOperator> selectbyuserids(String ids){
		return userOperatorMapper.selectbyuserids(ids);
	}

	@Override
	public List<String> getalluserLoginnid(UserOperator userOperator) {
		List<String> userlist = new ArrayList<String>();
		if(null == userOperator){
			throw new NullArgumentException("用户不能为空");
		}
		if(null == userOperator.getChilderuser()){
			userOperator = selectbyid(userOperator.getId());
		}
		
		userlist.add(userOperator.getLoginnmame());
		
		if (!userOperator.getChilderuser().isEmpty()) {
			List<String> children = getChildren(userOperator.getChilderuser());
			userlist.addAll(children);
		}

		return userlist;

	}

	private List<String> getChildren(List<UserOperator> userOperator) {
		List<String> userlist = new ArrayList<String>();
		for (UserOperator string : userOperator) {
			userlist.add(string.getLoginnmame());
			if (!string.getChilderuser().isEmpty()) {
				List<String> children = getChildren(string.getChilderuser());
				userlist.addAll(children);
			}
		}

		return userlist;
	}
	
	
	
	private List<String> getalluserid(UserOperator userOperator) {
		List<String> userlist = new ArrayList<String>();
		if(null == userOperator){
			throw new NullArgumentException("用户不能为空");
		}
		userlist.add(String.valueOf(userOperator.getId()));
		
		if (!userOperator.getChilderuser().isEmpty()) {
			List<String> children = getChildren1(userOperator.getChilderuser());
			userlist.addAll(children);
		}

		return userlist;

	}

	private List<String> getChildren1(List<UserOperator> userOperator) {
		List<String> userlist = new ArrayList<String>();
		for (UserOperator string : userOperator) {
			userlist.add(String.valueOf(string.getId()));
			if (!string.getChilderuser().isEmpty()) {
				List<String> children = getChildren1(string.getChilderuser());
				userlist.addAll(children);
			}
		}

		return userlist;
	}

	@Override
	public int deleteByLoginName(String loginName) {
		return userOperatorMapper.deleteByLoginName(loginName);
	}
}
