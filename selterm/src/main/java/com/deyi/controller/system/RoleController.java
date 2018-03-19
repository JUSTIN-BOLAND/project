package com.deyi.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.entity.Role;
import com.deyi.entity.UserInfo;
import com.deyi.service.RoleService;
import com.deyi.util.Component;
import com.deyi.util.Page;
import com.deyi.util.UserManage;

@Controller
@RequestMapping(value ="roleInfo")
public class RoleController extends Component<Role>{
	private Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	/**
	 * 
	 * @param mav
	 * @param loginName
	 * @param passwd	
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav,HttpServletRequest request,Role role,Page<Role> page){
		log.info("角色list...");
		setParams(request, role, page);	
		List<Role> roles = roleService.getRoleList(page);
		page.setResults(roles);
		mav.addObject("page", page);
		mav.setViewName("system/roleInfoLsit");
		return mav;
	}
	
	/**
	 * 跳转角色新增
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toRoleAdd")
	public ModelAndView toRoleAdd(ModelAndView mav,HttpServletRequest request){
		log.info("跳转角色新增...");
		mav.setViewName("system/roleInfoAdd");
		return mav;
	}
	
	/**
	 * 删除角色
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delRole")
	public ModelAndView delRole(ModelAndView mav,HttpServletRequest request,Role role){
		log.info("删除角色...");
		roleService.deleteRole(role.getId());
		mav.setViewName("redirect:/roleInfo/list.html");
		return mav;
	}
	/**
	 * 删除角色
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateRole")
	public ModelAndView updateRole(ModelAndView mav,HttpServletRequest request,Role role){
		log.info("停用 /启用 角色。。。。。。。。");
		roleService.updateRole(role);
		mav.setViewName("redirect:/roleInfo/list.html");
		return mav;
	}
	
	/**
	 * 保存添加角色
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="roleSave")
	@Transactional
	public ModelAndView roleSave(ModelAndView mav,HttpServletRequest request ,Role role){
		log.info("保存角色....");
		UserInfo userInfo = UserManage.getCurrUserInfo();
		//默认机构id
		role.setOrgId(userInfo.getOrgId());
		//非系统角色
		role.setRoletype("2");
		//创建人id
		role.setCreator(userInfo.getId());
		//创建人姓名
		role.setCreatorname("admin");
		roleService.saveRole(role);
		mav.setViewName("redirect:/roleInfo/list.html");
		return mav;
	}
}
