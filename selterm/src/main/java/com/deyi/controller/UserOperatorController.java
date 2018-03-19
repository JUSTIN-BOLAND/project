package com.deyi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deyi.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.entity.InheritRecordSheet;
import com.deyi.entity.Org;
import com.deyi.entity.Role;
import com.deyi.entity.UserInfo;
import com.deyi.entity.UserOperator;
import com.deyi.util.Component;
import com.deyi.util.Constants;
import com.deyi.util.GetIdsFromList;
import com.deyi.util.Page;
import com.deyi.util.UserManage;
import com.deyi.vo.ReturnVo;

/**
 * 用户运营商
 * 
 * @author admin
 *
 */

@Controller
@RequestMapping(value = "userOperator")
public class UserOperatorController extends Component<UserOperator> {

	private Logger log = LoggerFactory.getLogger(UserOperatorController.class);


	@Autowired
	private OrgService orgService;

	@Autowired
	private UserOperatorService userOperatorService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private InheritRecordSheetService inheritRecordSheetService;

	@Autowired
	private ActionLogService actionLogservice;


	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav) {
		log.info("userOperator viwe ...");
		mav.setViewName("userOperator/operatorList");
		return mav;
	}

	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, HttpServletRequest request, UserOperator userOperator,
			Page<UserOperator> page) {
		 UserInfo userInfo = UserManage.getCurrUserInfo();
		
		 String ids = userOperatorService.getUserIdsbyLoginname(userInfo);
		 
		 String replace = StringUtils.replace(ids, "'", "");
		 String[] newId = replace.split(",");
		 List<String> news = new ArrayList<String>();
		 for (String string : newId) {
			 news.add(string);
		}
		 List<InheritRecordSheet> InheritRecordSheets =  inheritRecordSheetService.queryInheritRecordSheet();
		 for (InheritRecordSheet inheritRecordSheet : InheritRecordSheets) {
			 if(news.contains(String.valueOf(inheritRecordSheet.getRawid()))){
				 news.remove(String.valueOf(inheritRecordSheet.getRawid()));
			 }
		}
//		 List<Object> arrayList = new ArrayList<>();
//		 for (String id : news) {
//			 userOperatorService.selectByPrimaryAccountName(id)
//			 arrayList
//		}
		String newIds = GetIdsFromList.getIdsByList(news);
		 userOperator.setIds(newIds);

		setParams(request, userOperator, page);
		List<UserOperator> userOperatorList = userOperatorService.getUserOperatorMapperList(page);
		page.setResults(userOperatorList);
		mav.addObject("page", page);
		mav.setViewName("userOperator/pageUserOperator");
		return mav;
	}

	@RequestMapping(value = "toAddUserOperator")
	public ModelAndView toAddUserOperator(ModelAndView mav, HttpServletResponse response, Page<Role> page) {

		UserInfo userInfo = UserManage.getCurrUserInfo();
		String usertype = userInfo.getType();
		String loginName = userInfo.getId();
		List<Org> orgS =new ArrayList<>();
//		if(Constants.USER_OPERATOR.equals(usertype)){
//			Org org = orgService.getOrgByorgAccount(loginName);
//			if ("1".equals(org.getLevel())) {// 运营商
//				orgS = orgService.getOrgsByOrgAccount(loginName);
//			}else{//代理商
//			    Org	orgLi  = orgService.getOrgByorgAccount(loginName);
//				orgS.add(orgLi);
//			}
//		}else if(Constants.SYS_USER_OPERATOR.equals(usertype)){
//			UserOperator userOper	= userOperatorService.selectByPrimaryAccountName(loginName);
//			Role role = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
//			if("6".equals(role.getUsertype()) || "7".equals(role.getUsertype())){
//				Org orgById = orgService.getOrgById(userOper.getOrgId()+"");
//				List<Org> orgsByOrgAccount = orgService.getOrgsByOrgAccount(orgById.getOrgAccount());
//				orgS.addAll(orgsByOrgAccount);
//			}else{
//				Org org = orgService.getOrgById(String.valueOf(userOper.getOrgId()));
//				orgS.add(org);
//				
//			}
//		}
		List<Role> roles = selectRole(response, Long.parseLong("1"));
		/*orgS = orgService.getAllOrg();
		mav.addObject("orgS", orgS);*/
		mav.addObject("roleList", roles);
		mav.setViewName("userOperator/toAddUserOperator");
		return mav;
	}

	@RequestMapping("userOperatorAdd")
	public @ResponseBody ReturnVo<Object> userOperatorAdd(ModelAndView mav, UserOperator userOperator,
			HttpServletRequest request) {
		ReturnVo<Object> vo = new ReturnVo<Object>();
		String roleId = request.getParameter("roleid");
		Role role = roleService.selectByPrimaryKey(Long.valueOf(roleId));
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			String userType = userInfo.getType();
			if (Constants.USER_OPERATOR.equals(userType) || Constants.SYS_USER_OPERATOR.equals(userType)) {
				//Org org = orgService.getOrgByorgAccount(userInfo.getId());
				//userOperator.setOrgId(Long.valueOf(org.getId())); // 运营商编码
				userOperatorService.insertSelective(userOperator);
			}
			UserInfo userInfo2 = new UserInfo();
			userInfo2.setRolename(role.getRolename());
			userInfo2.setRoleid(roleId);
			userInfo2.setId(userOperator.getLoginnmame());
			userInfo2.setType("5");
			userInfo2.setPassword(userOperator.getLoginnmame());

			userService.save(userInfo2);
			actionLogservice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_USER, Constants.SUBTYPE_ADD, String.format("【%s】添加用户【%s】", userInfo.getName(),userOperator.getName()));
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "deleteUserOperator")
	@Transactional
	public @ResponseBody ReturnVo<Object> deleteUserOperator(ModelAndView mav, HttpServletRequest request, String id) {
		ReturnVo<Object> vo = new ReturnVo<Object>();
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			UserOperator userOperator = userOperatorService.selectByPrimaryKey(id);
			String loginName = userOperator.getLoginnmame();
			UserInfo user = userService.getUser(loginName);
			if (user != null) {
				userService.deleteUserInfo(user.getId());
			}
			userOperatorService.deleteByPrimaryKey(id);
			actionLogservice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_USER, Constants.SUBTYPE_DELETE, String.format("【%s】删除用户【%s】", userInfo.getName(),userOperator.getName()));

		} catch (Exception e) {
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value = "toEditUserOperator")
	@Transactional
	public ModelAndView toEditUserOperator(ModelAndView mav, String id, Page<Role> page,HttpServletRequest request) {
//		List<Role> roleList = roleService.getRoleList(page);
		log.info("编辑用户："+ id);
		UserInfo userInfo = UserManage.getCurrUserInfo();
		
		UserOperator userOperator = userOperatorService.selectByPrimaryKey(id);
		UserInfo userInfos =  userService.getUser(userOperator.getLoginnmame());
		
		//编辑用户的角色
		Role role = roleService.selectByPrimaryKey(Long.parseLong(userInfos.getRoleid()));
		
		//当前用户
		UserOperator edituser = userOperatorService.selectByPrimaryKey(id);
		//上级用户
		UserOperator parentuser = userOperatorService.selectByPrimaryKey(String.valueOf(edituser.getParentid()));
	
		Role parentRole = null;
		if(Constants.USER_OPERATOR.equals(userInfo.getType())){
			parentRole = role.getParentRole();
			List<UserOperator> listuser = new ArrayList<UserOperator>();
			List<String> useridByRoleList = userService.getUseridByRoleList("'"+parentRole.getId()+"'");
			
			for (String string : useridByRoleList) {
				UserOperator selectByPrimaryAccountName = userOperatorService.selectByPrimaryAccountName(string);
				if(null == selectByPrimaryAccountName){
					continue;
				}
				listuser.add(selectByPrimaryAccountName);
			}
			List<Role> selectByOrgId = roleService.selectByOrgId(edituser.getOrgId());
			List<Role> roleList = new ArrayList<Role>();
//			List<String> ids = roleService.selectGetChildrenRoleAndSelf(Long.parseLong(userInfo.getRoleid()));
			for (Role string : selectByOrgId) {
				if("4".equals(string.getUsertype())||"5".equals(string.getUsertype())||"0".equals(string.getUsertype())){
					continue;
				}
				roleList.add(string);
			}
			mav.addObject("roleList", role);		//角色
			mav.addObject("parentuser", parentuser);	//上级用户回显
			mav.addObject("listuser", listuser);		//上级角色用户
			mav.addObject("roleList", roleList);		//角色列表
			mav.addObject("roleId", userInfos.getRoleid());
			mav.addObject("password",userInfos.getPassword());
			mav.addObject("userOperator", userOperator);
			mav.setViewName("userOperator/toEditUserAdmin");
			return mav;
		}else if(Constants.SYS_USER_OPERATOR.equals(userInfo.getType())){
			
		}

		
		
//		List<UserOperator> listuser = new ArrayList<UserOperator>();
//		List<String> useridByRoleList = userService.getUseridByRoleList("'"+parentRole.getId()+"'");
//		
//		for (String string : useridByRoleList) {
//			UserOperator selectByPrimaryAccountName = userOperatorService.selectByPrimaryAccountName(string);
//			if(null == selectByPrimaryAccountName){
//				continue;
//			}
//			listuser.add(selectByPrimaryAccountName);
//		}
		
		mav.addObject("roleId", userInfos.getRoleid());
		mav.addObject("userOperator", userOperator);	
		mav.addObject("roleList", role);		//角色
		mav.addObject("password",userInfos.getPassword());
		mav.addObject("parentuser", parentuser);	//上级用户回显
		mav.setViewName("userOperator/toEditUserOperator");
		
		
		

		
//		
//		mav.addObject("roleId", userInfos.getRoleid());
//		mav.addObject("userOperator", userOperator);	
//		mav.addObject("roleList", roleList);		//角色列表
//		mav.setViewName("userOperator/toEditUserOperator");
		
		return mav;
	}

	@RequestMapping(value = "editUserOperator")
	@Transactional
	public @ResponseBody ReturnVo<Object> editUserOperator(ModelAndView mav, HttpServletRequest request,
			UserOperator userOperator) {
		ReturnVo<Object> vo = new ReturnVo<>();
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
//			String roleId = request.getParameter("roleid");
//			String id = request.getParameter("id");
			UserOperator userOperator2 = userOperatorService.selectByPrimaryKey(String.valueOf(userOperator.getId()));
//			String loginName = userOperator2.getLoginnmame();
			UserInfo user = userService.getUser(userOperator2.getLoginnmame());
			if (null != userOperator.getRoleid()) {
				Role role = roleService.selectById(userOperator.getRoleid());
				user.setRoleid(String.valueOf(userOperator.getRoleid()));
				user.setRolename(role.getRolename());
				userService.upadeUser(user);
			}
			userOperatorService.updateByPrimaryKeySelective(userOperator);
			actionLogservice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_USER, Constants.SUBTYPE_EDIT, String.format("【%s】编辑用户【%s】", userInfo.getName(),userOperator.getName()));
		} catch (Exception e) {
			log.info("",e);
			vo.setSuccess(false);
		}
		return vo;
	}
	
	
	
	@RequestMapping(value="accountInherit")
	public ModelAndView accountInherit(ModelAndView mav,String id){
		UserOperator userOperator = userOperatorService.selectByPrimaryKey(id);
		List<UserOperator> userOperatorS = userOperatorService.selectByOrgid(String.valueOf(userOperator.getOrgId()));
		List<UserOperator> newUserOperator = new ArrayList<UserOperator>();
		for (UserOperator userOperator2 : userOperatorS) {
			if(userOperator2.getId() == userOperator.getId()){
				continue;
			}
			newUserOperator.add(userOperator2);
		}
		
		mav.addObject("newUserOperator", newUserOperator);
		mav.addObject("userOperator", userOperator);
		mav.setViewName("userOperator/accountInherit");
		return mav;
	}
	
	/**
	 * 保存继承记录
	 */
	@RequestMapping(value="saveInherit")
	public @ResponseBody ReturnVo<Object> saveInherit(ModelAndView mav, InheritRecordSheet inheritRecordSheet, HttpServletRequest request) {
		ReturnVo<Object> vo =new ReturnVo<>();
		String rawid = request.getParameter("rawid");//原账号id
		String inheritId = request.getParameter("inheritId");//继承账号id
		
		UserOperator raw = userOperatorService.selectByPrimaryKey(rawid);
		UserOperator inherit = userOperatorService.selectByPrimaryKey(inheritId);
		
		InheritRecordSheet InheritRecord = new InheritRecordSheet();
		InheritRecord.setRawid(raw.getId());
		InheritRecord.setRawname(raw.getName());
		InheritRecord.setRawaccount(raw.getLoginnmame());
		InheritRecord.setRawrolename(raw.getRolename());
		
		InheritRecord.setInheritid(inherit.getId());
		InheritRecord.setInheritname(inherit.getName());
		InheritRecord.setInheritaccount(inherit.getLoginnmame());
		InheritRecord.setInheritrolename(inherit.getRolename());
		inheritRecordSheetService.insertSelective(InheritRecord);
		
		return vo;
		
	}
	
	@ResponseBody
	@RequestMapping("selectRole")
	public List<Role> selectRole(HttpServletResponse response,Long id){
		UserInfo userInfo = UserManage.getCurrUserInfo();
	    List<Role> roleList = roleService.selectByOrgId(Long.valueOf(id));
	    Role role1 = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
	    Role argrole  =  null;
	    List<String> selectGetChildrenRoleAndSelf = new ArrayList<>();
	    if("6".equals(role1.getUsertype()) || "7".equals(role1.getUsertype())){
	    	for (Role role : roleList) {
				if("0".equals(role.getUsertype())){
					argrole = role;
					break;
				}
			}
	    	selectGetChildrenRoleAndSelf = roleService.selectGetChildrenRoleAndSelf(argrole.getId());
	    }else{
	    	selectGetChildrenRoleAndSelf = roleService.selectGetChildrenRoleAndSelf(Long.valueOf(userInfo.getRoleid()));
	    }
	    
	    List<Role> roles = new ArrayList<>();
//	    for (Role role : roleList) {
//	    
//			if(StringUtils.isNotBlank(role.getUsertype()) && role.getUsertype().equals("4")){
//				continue;
//			}
//			
//			if("0".equals(role.getUsertype()) || "5".equals(role.getUsertype())){
//				continue;
//			}
//			if(userInfo.getRoleid().equals(String.valueOf(role.getId()))){
//				continue;
//			}
//			if(selectGetChildrenRoleAndSelf.contains(String.valueOf(role.getId()))){
//				roles.add(role);
//			}
//		}
	    roles = roleService.selectByOrgId(id);
	    return roles;
	    
	}
	
	
	/**
	 * 
	 * @param id  角色ID
	 * @return
	 */
	@RequestMapping(value="selectparentiduser")
	public @ResponseBody List<UserOperator> selectparentiduser(Long id){
		List<UserOperator> arrayList = new ArrayList<UserOperator>();
		
		log.info("选择上级用户");
		
		Role selectByPrimaryKey = roleService.selectByPrimaryKey(id);
		Role parentRole = selectByPrimaryKey.getParentRole();
		
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Role role1 = roleService.selectById(Long.valueOf(userInfo.getRoleid()));
		if(Constants.SYS_USER_OPERATOR.equals(userInfo.getType())){
			
			if("6".equals(role1.getUsertype()) || "7".equals(role1.getUsertype())){
				List<String> useridByRoleList = userService.getUseridByRoleList("'"+parentRole.getId()+"'");
				for (String string : useridByRoleList) {
					UserOperator selectByPrimaryAccountName = userOperatorService.selectByPrimaryAccountName(string);
					arrayList.add(selectByPrimaryAccountName);
				}
				return arrayList;
			}else{
				UserOperator userOperator = userOperatorService.selectByPrimaryAccountName(userInfo.getId());
//				UserOperator selectbyid = userOperatorService.selectbyid(userOperator.getId());
				List<String> useridByRoleList = userService.getUseridByRoleList("'"+parentRole.getId()+"'");
				if(useridByRoleList.contains(userOperator.getLoginnmame()+"")){
					UserOperator selectByPrimaryKey2 = userOperatorService.selectByPrimaryAccountName(userOperator.getLoginnmame());
					arrayList.add(selectByPrimaryKey2);
				}
				return arrayList;
			}
		}else if(Constants.USER_OPERATOR.equals(userInfo.getType())){
			List<String> useridByRoleList = userService.getUseridByRoleList("'"+parentRole.getId()+"'");
			for (String string : useridByRoleList) {
				UserOperator selectByPrimaryAccountName = userOperatorService.selectByPrimaryAccountName(string);
				arrayList.add(selectByPrimaryAccountName);
			}
			
			return arrayList;
		}
		return arrayList;
	}
	
	@ResponseBody
	@RequestMapping(value="resetpass")
	public ReturnVo<Object> resetpass(String id){
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
		try {
			UserOperator selectByPrimaryKey = userOperatorService.selectByPrimaryKey(id);
			UserInfo user = userService.getUser(selectByPrimaryKey.getLoginnmame());
			user.setPassword(selectByPrimaryKey.getLoginnmame());
			userService.upadeUser(user);
			actionLogservice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_USER, Constants.SUBTYPE_RESETPASSWD, String.format("【%s】重置用户【%s】密码", userInfo.getName(),selectByPrimaryKey.getName()));
		} catch (Exception e) {
			log.info("",e);
			vo.setSuccess(false);
			vo.setMessage("重置密码失败");
			return vo;
		}
		vo.setMessage("重置密码成功");
	
		return vo;
	}
	
	
	/**
	 * 启用用户
	 * @author xiongqq 
	 * @time  2016-06-17
	 * @param id 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="enableuser")
	public ReturnVo<Object> enableuser(String id){
		ReturnVo<Object> vo = new ReturnVo<>();
		
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			UserOperator userOperator = userOperatorService.selectByPrimaryKey(id);
			userOperator.setStatus("1");
			userOperatorService.updateByPrimaryKeySelective(userOperator);
			actionLogservice.savelog(userInfo.getId(),userInfo.getName(), Constants.TYPE_USER,Constants.SUBTYPE_ENABLE, String.format("【%s】启用用户【%s】",userInfo.getName(),userOperator.getName()));
			vo.setMessage("启用成功");
		} catch (Exception e) {
			vo.setMessage("启用失败");
			vo.setSuccess(false);
		}
		
		return vo;
	}
	
	/**
	 * 禁用用户
	 * @author xiongqq 
	 * @time  2016-06-17
	 * @param id 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="disableuser")
	public ReturnVo<Object> disableuser(String id){
		ReturnVo<Object> vo = new ReturnVo<>();
		
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			UserOperator userOperator = userOperatorService.selectByPrimaryKey(id);
			userOperator.setStatus("2");
			userOperatorService.updateByPrimaryKeySelective(userOperator);
			actionLogservice.savelog(userInfo.getId(),userInfo.getName(), Constants.TYPE_USER,Constants.SUBTYPE_DISABLEMAC, String.format("【%s】禁用用户【%s】",userInfo.getName(),userOperator.getName()));
			vo.setMessage("禁用成功");
		} catch (Exception e) {
			vo.setMessage("禁用失败");
			vo.setSuccess(false);
		}
		
		return vo;
	}
	
}
