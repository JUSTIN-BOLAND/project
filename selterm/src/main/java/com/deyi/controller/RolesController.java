package com.deyi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deyi.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.entity.Menu;
import com.deyi.entity.Merchant;
import com.deyi.entity.Org;
import com.deyi.entity.Role;
import com.deyi.entity.RoleMenu;
import com.deyi.entity.UserInfo;
import com.deyi.entity.UserOperator;
import com.deyi.util.Component;
import com.deyi.util.Constants;
import com.deyi.util.GetIdsFromList;
import com.deyi.util.Page;
import com.deyi.util.ResponseWriteUtils;
import com.deyi.util.UserManage;
import com.deyi.vo.ReturnVo;
import com.google.gson.Gson;

/**
 * 角色
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "role")
public class RolesController extends Component<Role> {
	private Logger log = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private ActionLogService actionLogService;
	
	@Autowired
	private UserOperatorService userOperatorService;
	
	@Autowired
	private UserService userService;

	/**
	 * 跳转页面
	 * 
	 * @param mavg
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request, Merchant merchant, Page<Merchant> page) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		String userType = userInfo.getType();
		mav.addObject("userType", userType);
		mav.addObject("userMerchant", Constants.USER_MERCHANT);

		mav.setViewName("managet/roleList");
		return mav;
	}

	/**
	 * 页面数据
	 */
	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, HttpServletRequest request, Role role, Page<Role> page) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		String roleids = "''";
		Role selectById = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
		if("6".equals(selectById.getUsertype())|| "7".equals(selectById.getUsertype())){
			List<Role> arrayList = new ArrayList<Role>();
			UserOperator userOperator = userOperatorService.selectByPrimaryAccountName(userInfo.getId());
			Org org = orgService.selectByPrimaryKey(userOperator.getOrgId());
			List<Org> orgsByOrgAccount = orgService.getOrgsByOrgAccount(org.getOrgAccount());
			for (Org org2 : orgsByOrgAccount) {
				List<Role> selectByOrgId = roleService.selectByOrgId(Long.valueOf(org2.getId()));
				arrayList.addAll(selectByOrgId);
			}
			roleids = GetIdsFromList.getIdsByRoleList(arrayList);
		}else{
			List<String> idslist = roleService.selectGetChildrenRoleAndSelf(Long.valueOf(userInfo.getRoleid()));
			roleids = GetIdsFromList.getIdsByList(idslist);
			
		}
//		role.setRoleids(roleids);
		setParams(request, role, page);
		List<Role> roleList = roleService.getRoleList(page);
		
		
		
		page.setResults(roleList);
		mav.addObject("page", page);
		mav.setViewName("managet/pageRole");
		return mav;
	}

	/**
	 * 删除角色
	 */
	@RequestMapping(value = "delectRole")
	@Transactional
	public @ResponseBody ReturnVo<Object> delectRole(ModelAndView mav, String id) {
		ReturnVo<Object> vo = new ReturnVo<Object>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Role role = roleService.selectByPrimaryKey(Long.valueOf(id));
		if("1".equals(role.getRoletype())){
			vo.setSuccess(false);
			vo.setMessage("系统角色不能删除");
			return vo;
		}
		try {
			// 先删除角色的菜单权限
			roleMenuService.deleteRoleId(role.getId());
			List<String> uIds = userService.getUseridByRoleList(role.getId().toString());
			for (String uId : uIds) {
				userOperatorService.deleteByLoginName(uId);
			}
			userService.deleteByRoleId(role.getId().toString());
			roleService.deleteRole(role.getId());
			actionLogService.savelog(userInfo.getId(),userInfo.getName(),Constants.TYPE_ROLE, Constants.SUBTYPE_DELETE,String.format("【%s】删除角色【%s】", userInfo.getName(),role.getRolename()));
		} catch (Exception e) {
			log.error("orgAdd ============= error", e);
			vo.setSuccess(false);
		}
		return vo;
	}

	/**
	 * 
	 */
	@RequestMapping(value = "toRoleAdd")
	@Transactional
	public ModelAndView toRoleAdd(ModelAndView mav) {
		
		List<Role> arrlist = new ArrayList<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
		if(Constants.USER_OPERATOR.equals(userInfo.getType())){
			Org org = orgService.getOrgByorgAccount(userInfo.getId());
			List<Role> list = roleService.selectByOrgId(Long.parseLong(org.getId()));
			
			for (Role role : list) {
				arrlist.add(role);
			}
	
		}else if(Constants.SYS_USER_OPERATOR.equals(userInfo.getType())){
//			Role role = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
//			if("6".equals(role.getUsertype()) || "7".equals(role.getUsertype())){
				UserOperator userOperator = userOperatorService.selectByPrimaryAccountName(userInfo.getId());
				Org org = orgService.selectByPrimaryKey(userOperator.getOrgId());
				List<Role> list = roleService.selectByOrgId(Long.parseLong(org.getId()));
				
				for (Role role2 : list) {
					arrlist.add(role2);
				}
		
//			}
		}
				
		
		
		mav.addObject("rolelist", arrlist);
		mav.setViewName("managet/toAddRole");
		return mav;
	}

	/**
	 * 添加角色
	 */
	@RequestMapping(value = "roleAdd")
	@Transactional
	public @ResponseBody ReturnVo<Object> roleAdd(ModelAndView mav, Role role) {

		ReturnVo<Object> vo = new ReturnVo<Object>();
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			String loginName = userInfo.getId();
			String userType = userInfo.getType();
			if (Constants.USER_MERCHANT.equals(userType)) {// 商户
				role.setOrgId(userInfo.getOrgId());// 默认机构id
				role.setCreator(userInfo.getId());// 创建人id
				role.setRoletype("2");
				role.setCreatorname(userInfo.getRolename());// 创建人姓名
			} else if (Constants.USER_OPERATOR.equals(userType)) {
				Org org = orgService.getOrgByorgAccount(loginName);
				if ("1".equals(org.getLevel())) {// 运营商
					role.setOrgId(userInfo.getOrgId());// 默认机构id
					role.setCreator(userInfo.getId());// 创建人id
					role.setRoletype("2");
					role.setOrgId(org.getId());
					role.setCreatorname(userInfo.getRolename());// 创建人姓名
				} else {// 代理商
					role.setOrgId(userInfo.getOrgId());// 默认机构id
					role.setCreator(userInfo.getId());// 创建人id
					role.setRoletype("2");
					role.setOrgId(org.getId());
					role.setCreatorname(userInfo.getRolename());// 创建人姓名
				}
			} else {
				role.setOrgId(userInfo.getOrgId());// 默认机构id
				role.setCreator(userInfo.getId());// 创建人id
				role.setRoletype("2");
				role.setCreatorname(userInfo.getRolename());// 创建人姓名
			}
			roleService.saveRole(role);
			actionLogService.savelog(userInfo.getId(),userInfo.getName(),Constants.TYPE_ROLE, Constants.SUBTYPE_ADD,String.format("【%s】添加角色【%s】", userInfo.getName(),role.getRolename()));
		} catch (Exception e) {
			vo.setSuccess(false);
		}

		return vo;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "toEditRole")
	public ModelAndView toEditRole(ModelAndView mav, Long id) {
		Role selectById = roleService.selectById(id);
		Org orgById = orgService.getOrgById(selectById.getOrgId());
		List<Role> roleAndSelf = roleService.selectByOrgId(Long.parseLong(orgById.getId()));
		
		Role role = roleService.selectById(id);
		if (role != null) {
			mav.addObject("role", role);
			mav.setViewName("managet/toEditRole");
		}
		mav.addObject("rolelist", roleAndSelf);
		return mav;
	}

	/**
	 * 
	 */
	@RequestMapping(value = "editRole")
	public @ResponseBody ReturnVo<Object> editRole(ModelAndView mav, Role role) {
		ReturnVo<Object> vo = new ReturnVo<>();
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			
			roleService.updateRole(role);
			actionLogService.savelog(userInfo.getId(),userInfo.getName(),Constants.TYPE_ROLE, Constants.SUBTYPE_EDIT,String.format("【%s】编辑角色【%s】", userInfo.getName(),role.getRolename()));
		} catch (Exception e) {
			vo.setSuccess(false);
			vo.setMessage("修改失败！");
		}
		return vo;
	}

	/**
	 * 授权
	 */
	@RequestMapping(value = "toAuthorizedRole")
	public ModelAndView toAuthorizedRole(ModelAndView mav, String id) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		String loginName = userInfo.getId();
		String userType = userInfo.getType();

		if (Constants.USER_OPERATOR.equals(userType) || Constants.SYS_USER_OPERATOR.equals(userType)) {
			Org org = null;
			Role role = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
			if("5".equals(role.getUsertype())){
				org = orgService.getOrgByorgAccount(loginName);
			}else{
				UserOperator userOperator = userOperatorService.selectByPrimaryAccountName(loginName);
				org = orgService.selectByPrimaryKey(userOperator.getOrgId());
			}
			List<Menu> menus = null;
			if("1".equals(org.getNode())){//运营商
				 menus = menuService.getAllMenus();
			}else{//代理商
				 menus = menuService.getMenusByUser(loginName);
			}
			
			List<String> roleMenus = roleMenuService.getMenuIdsByRoleId(id);
			for (Menu menu : menus) {
				String menuId = menu.getMenuId();
				if (roleMenus.contains(menuId)) {
					menu.setChecked(true);
				} else {
					menu.setChecked(false);
				}
			}
			mav.addObject("menuList", new Gson().toJson(menus));
			mav.addObject("roleId", id);
		} else {
			List<Menu> menuList = menuService.getMenusByUser(loginName);
			List<String> roleMenus = roleMenuService.getMenuIdsByRoleId(id);
			for (Menu menu : menuList) {
				String menuId = menu.getMenuId();
				if (roleMenus.contains(menuId)) {
					menu.setChecked(true);
				} else {
					menu.setChecked(false);
				}

			}

			mav.addObject("menuList", new Gson().toJson(menuList));
			mav.addObject("roleId", id);
		}
		mav.setViewName("managet/toAuthorizedRole");
		return mav;
	}

	/**
	 * 授权
	 */
	@RequestMapping(value = "authorizedRole")
	@Transactional
	public void authorizedRole(String id, String[] menuId,
			HttpServletRequest request,HttpServletResponse response) {
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Role role = roleService.selectById(Long.valueOf(id));
		try {
			roleMenuService.deleteRoleId(Long.valueOf(id));
			RoleMenu rm = null;
			for (int i = 0; i < menuId.length; i++) {
				rm = new RoleMenu();
				rm.setMenuid(Long.valueOf(menuId[i]));
				rm.setRoleid(Long.valueOf(id));
				roleMenuService.insertSelective(rm);
			}
		} catch (Exception e) {
			vo.setMessage("授权失败");
			vo.setData("1");
			vo.setSuccess(false);
			ResponseWriteUtils.returnAjax(response, vo);
		}
		actionLogService.savelog(userInfo.getId(),userInfo.getName(),Constants.TYPE_ROLE, Constants.SUBTYPE_DELETE,String.format("【%s】对角色【%s】授权", userInfo.getName(),role.getRolename()));
		vo.setMessage("授权成功");
		vo.setData("0");
		ResponseWriteUtils.returnAjax(response, vo);
	}

}
