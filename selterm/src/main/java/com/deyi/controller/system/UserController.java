package com.deyi.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.service.UserService;
import com.deyi.util.Component;
import com.deyi.util.Page;
import com.deyi.vo.UserVo;
@Controller
@RequestMapping(value ="userInfo")
public class UserController extends Component<UserVo>{
	private Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	/**
	 * 用户列表
	 * @param mav
	 * @param request
	 * @param userVo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav,HttpServletRequest request,UserVo userVo,Page<UserVo> page){
		log.info("用户list...");
		setParams(request, userVo, page);	
		List<UserVo> userVos = userService.getUserInfoList(page);
		page.setResults(userVos);
		mav.addObject("page", page);
		mav.setViewName("system/userInfoLsit");
		return mav;
	}
	/**
	 * 删除用户
	 * @param mav
	 * @param loginName
	 * @param passwd	
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteUserInfo")
	public ModelAndView deleteUserInfo(ModelAndView mav,HttpServletRequest request,UserVo userVo){
		log.info("删除用户...");
		//判断用户下面是否关联角色
		//TODO
		userService.deleteUserInfo(userVo.getId());
		mav.setViewName("system/userInfoLsit");
		return mav;
	}
	
}
