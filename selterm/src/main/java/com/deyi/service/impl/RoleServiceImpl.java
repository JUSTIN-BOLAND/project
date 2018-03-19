package com.deyi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deyi.dao.RoleMapper;
import com.deyi.entity.Role;
import com.deyi.entity.RoleMenu;
import com.deyi.service.MenuService;
import com.deyi.service.RoleMenuService;
import com.deyi.service.RoleService;
import com.deyi.util.Page;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleMenuService roleMenuService;

	@Override
	public void deleteRole(Long id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Role> getRoleList(Page<Role> page) {
		return roleMapper.getRoleList(page);
	}

	@Override
	public List<Role> selectByOrgId(Long orgid) {
		return roleMapper.selectByOrgId(orgid);
	}

	@Override
	public void saveRole(Role role) {
		roleMapper.insertSelective(role);

	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Role selectById(Long id) {
		return roleMapper.selectById(id);
	}

	@Override
	public List<Role> selectRoleByroleids(String ids) {
		return roleMapper.selectRoleByroleids(ids);
	}

	@Override
	public Role selectManerchatRoleIdOrgId(String roleid,String type) {
		switch (type) {
		case "1":
			return roleMapper.selectManerchatRoleIdByOrgRoleid(roleid,4);
		case "2":
			return roleMapper.selectManerchatRoleIdByOrgRoleid(roleid,5);
		default:
			break;
		}
		return new Role();
	}

	public List<String> selectGetChildrenRoleAndSelf(Long roleid) {
		ArrayList<String> arrayList = new ArrayList<String>();
		Role role = roleMapper.selectByPrimaryKey(roleid);
		if (role == null) {
			return arrayList;
		}

		arrayList.add(String.valueOf(role.getId()));
		if (!role.getChildren().isEmpty()) {
			List<String> getroleid = getroleid(role.getChildren());
			arrayList.addAll(getroleid);
		}

		return arrayList;
	}

	@Transactional
	@Override
	public Role addorgrole(String orgMerId, String creator, String creatorName, String Rolestatus, String orgId) {
		// 新增代理商
		Role org = new Role();
		org.setOrgMerId(orgMerId);
		org.setRolename("代理商角色");
		org.setRoletype("1");
		org.setRoledesc("代理商角色");
		org.setCreator(creator);
		org.setCreatorname(creatorName);
		org.setRolestatus(Rolestatus);
		org.setOrgId(orgId);
		org.setUsertype("0");
		roleMapper.insertSelective(org);
		
		//中台审核
		Role auid = new Role();
		auid.setOrgMerId(String.valueOf(org.getId()));
		auid.setRolename("中台审核角色");
		auid.setRoletype("1");
		auid.setRoledesc("中台审核角色");
		auid.setCreator(creator);
		auid.setCreatorname(creatorName);
		auid.setRolestatus(Rolestatus);
		auid.setOrgId(orgId);
		auid.setUsertype("6");
		roleMapper.insertSelective(auid);
		
		
		//财务查帐
		Role money = new Role();
		money.setOrgMerId(String.valueOf(org.getId()));
		money.setRolename("财务查帐角色");
		money.setRoletype("1");
		money.setRoledesc("财务查帐角色");
		money.setCreator(creator);
		money.setCreatorname(creatorName);
		money.setRolestatus(Rolestatus);
		money.setOrgId(orgId);
		money.setUsertype("7");
		roleMapper.insertSelective(money);

		// 新增经理角色
		Role manager = new Role();
		manager.setRolename("经理角色");
		manager.setRoletype("1");
		manager.setRoledesc("经理角色");
		manager.setCreator(creator);
		manager.setCreatorname(creatorName);
		manager.setRolestatus(Rolestatus);
		manager.setOrgMerId(String.valueOf(org.getId()));
		manager.setOrgId(orgId);
		manager.setUsertype("1");
		roleMapper.insertSelective(manager);
		

		// 新增组长角色
		Role groupleader = new Role();
		groupleader.setRolename("组长角色");
		groupleader.setRoletype("1");
		groupleader.setRoledesc("组长角色");
		groupleader.setCreator(creator);
		groupleader.setCreatorname(creatorName);
		groupleader.setRolestatus(Rolestatus);
		groupleader.setOrgMerId(String.valueOf(manager.getId()));
		groupleader.setOrgId(orgId);
		groupleader.setUsertype("2");
		roleMapper.insertSelective(groupleader);

		// 新增业务员角色
		Role saleman = new Role();
		saleman.setRolename("业务员角色");
		saleman.setRoletype("1");
		saleman.setRoledesc("业务员角色");
		saleman.setCreator(creator);
		saleman.setCreatorname(creatorName);
		saleman.setRolestatus(Rolestatus);
		saleman.setOrgId(orgId);
		saleman.setOrgMerId(String.valueOf(groupleader.getId()));
		saleman.setUsertype("3");
		roleMapper.insertSelective(saleman);

		// 新增商户角色
		Role dis_merchant = new Role();
		dis_merchant.setRolename("商户角色");
		dis_merchant.setRoletype("1");
		dis_merchant.setRoledesc("商户角色");
		dis_merchant.setCreator(creator);
		dis_merchant.setCreatorname(creatorName);
		dis_merchant.setRolestatus(Rolestatus);
		dis_merchant.setOrgId(orgId);
		dis_merchant.setOrgMerId(String.valueOf(org.getId()));
		dis_merchant.setUsertype("4");
		roleMapper.insertSelective(dis_merchant);
		
		
		
		// 新增二清商户角色
		Role merchant_two = new Role();
		merchant_two.setRolename("二清商户角色");
		merchant_two.setRoletype("1");
		merchant_two.setRoledesc("二清商户角色");
		merchant_two.setCreator(creator);
		merchant_two.setCreatorname(creatorName);
		merchant_two.setRolestatus(Rolestatus);
		merchant_two.setOrgId(orgId);
		merchant_two.setOrgMerId(String.valueOf(org.getId()));
		merchant_two.setUsertype("5");
		roleMapper.insertSelective(merchant_two);
		
		
		
		for (String string : merchanttwomenu()) {
			RoleMenu roleMenu2 = new RoleMenu(); // 二清商户角
			
			roleMenu2.setRoleid(merchant_two.getId());
			roleMenu2.setMenuid(Long.parseLong(string));
			roleMenuService.insertSelective(roleMenu2);
		}

		String[] agentMenu2 = agentMenu();
		for (int i = 0; i < agentMenu2.length; i++) {
			RoleMenu roleMenu = new RoleMenu(); // 代理商
			roleMenu.setRoleid(org.getId());
			roleMenu.setMenuid(Long.parseLong(agentMenu2[i]));
			roleMenuService.insertSelective(roleMenu);
		}

		String[] agentMenu = userMenu();
		for (String string : agentMenu) {

			RoleMenu roleMenu2 = new RoleMenu(); // 经理
			RoleMenu roleMenu3 = new RoleMenu(); // 组长
			RoleMenu roleMenu4 = new RoleMenu(); // 业务员

			roleMenu2.setRoleid(manager.getId());
			roleMenu2.setMenuid(Long.parseLong(string));

			roleMenu3.setRoleid(groupleader.getId());
			roleMenu3.setMenuid(Long.parseLong(string));

			roleMenu4.setRoleid(saleman.getId());
			roleMenu4.setMenuid(Long.parseLong(string));

			roleMenuService.insertSelective(roleMenu2);
			roleMenuService.insertSelective(roleMenu3);
			roleMenuService.insertSelective(roleMenu4);
		}

		String[] merchantMenu = merchantMenu();
		for (String string : merchantMenu) {
			RoleMenu roleMenu = new RoleMenu(); // 商户
			roleMenu.setRoleid(dis_merchant.getId());
			roleMenu.setMenuid(Long.parseLong(string));
			roleMenuService.insertSelective(roleMenu);

		}

		return org;
	}

	/**
	 * 商户菜单
	 * 
	 * @return
	 */
	private String[] merchantMenu() {
		String[] menuid = new String[] { "3", "31", "3101", "3102", "3103", "3104", "3105", "32", "3201", "3202",
				"3203", "3204", "3205", "3206", "4", "40", "4001", "4002", "4003", "5", "50", "52", "53", "54", "55",
				"6", "60", "6001", "6002", "6003", "62", "63", "7", "71", "7104", "8", "80", "8001", "8002", "81",
				"8101", "8102" };
		return menuid;
	}

	private String[] userMenu() {

		String[] menu = new String[] { "2", "21", "3", "30", "3001", "3002", "3003", "3004", "3005", "31", "3101",
				"3102", "3103", "3104", "3105", "32", "3201", "3202", "3203", "3204", "3205", "3206", "4", "40", "4001",
				"4002", "4003", "5", "50", "51", "52", "53", "54", "55", "6", "60", "6001", "6002", "6003", "62", "63",
				"7", "70", "71","7104" };
		return menu;

	}

	/**
	 * 代理商角色菜单
	 * 
	 * @return
	 */
	private String[] agentMenu() {
		String[] menuid = new String[] {"1201","2", "21", "3", "30", "3001", "3002", "3003", "3004", "3005","3006","3007", "31", "3101",
				"3102", "3103", "3104", "3105", "32", "3201", "3202", "3203", "3204", "3205", "3206", "4", "40", "4001",
				"4002", "4003", "5", "50", "51", "52", "53", "54", "55", "6", "60", "6001", "6002", "6003", "61",
				"6101", "6102", "6103", "62", "63", "64", "7", "70", "7001", "7002", "7003", "71", "7101",
				"7102", "7103", "7104" };
		return menuid;
	}

	private List<String> getroleid(List<Role> role) {
		ArrayList<String> list = new ArrayList<String>();
		for (Role role2 : role) {
			list.add(String.valueOf(role2.getId()));
			if (!role2.getChildren().isEmpty()) {
				List<String> getroleid = getroleid(role2.getChildren());
				list.addAll(getroleid);
			}
		}
		return list;
	}
	
	private String[] merchanttwomenu(){
		String[] menu = new String[]{ "3", "31", "3101", "3102", "3103", "3104", "3105", "32", "3201", "3202",
				"3203", "3204", "3205", "3206",  "5", "50", "52", "53", "54", "55",
				"6", "60", "6001", "6002", "6003", "62", "63", "7", "71", "7104"};
		return menu;
	}

}
