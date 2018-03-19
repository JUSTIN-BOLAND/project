/**
* @Title: AuthorityInterceptor.java
* @Package com.yiqi.controller
* @Description: 深圳市得壹科技有限公司
*/
package com.deyi.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.deyi.entity.UserInfo;
import com.deyi.service.UserService;
import com.deyi.service.impl.UserServiceImpl;
import com.deyi.vo.ReturnVo;
import com.google.gson.Gson;


/**
 * @Description: 用户权限拦截器
 * @author hejx
 * @date 2015年10月29日
 *
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityInterceptor.class);
	/**
	 * 1.过滤用户是否有当前菜单权限
	 * 2.sesion是否失效 跳转到登录页面
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String path = request.getContextPath()+"";// 工程名
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo)session.getAttribute(Constants.SESSION_USERINFO);



		String uri = request.getRequestURI().toUpperCase()+"";
		System.out.println("URI:"+uri);
		String projectName=request.getContextPath()+"";
		String[] strs = uri.split("\\/");
//		if(strs.length != 4){
//			return false;
//		}
		String url = "";
		if(uri.startsWith(projectName.toUpperCase())){
			url = uri.replaceFirst(projectName.toUpperCase(), "");
		}else{
			url = uri;
		}


//		String url = "/"+strs[2].toLowerCase()+"/"+strs[3].toLowerCase();

		String uriMap = "";
		if(!projectName.equals("")){//在项目目录下
			if(strs.length > 2){
				for(int i=2;i<strs.length;i++){
					uriMap +="/"+strs[i];
				}
			}else {
				uriMap = "/";
			}
		}else{//把项目直接发布到tomcat的ROOT目录下
			if(strs.length > 1){
				for(int i=1;i<strs.length;i++){
					uriMap +="/"+strs[i];
				}
			}else {
				uriMap = "/";
			}
		}
		if(uriMap.toLowerCase().indexOf("/trade/") > -1 || uriMap.toLowerCase().indexOf("PAYCONFIG/UPLOAD.HTML") > -1
				|| uriMap.toLowerCase().indexOf("PAYCONFIG/editPayConfig.HTML".toUpperCase()) > -1){
			return true;
		}
		//不需要过滤的地址
		String no_filter_paths = PropertiesUtil.getProperty("no_filter_paths");
		String[] indexStrs = no_filter_paths.split(",");
		for (String item : indexStrs) {
			if(uriMap.equalsIgnoreCase(item)){
				System.out.println("URI:"+uri+" is ok!");
				return true;
			}
		}

		if("api".equals(strs[2].toLowerCase())){//终端用户的请求
			String loginName = request.getParameter("loginName");//获取登录名

			UserService userService = (UserServiceImpl)SpringContextUtil.getBean("userServiceImpl");
			userInfo = userService.getUser(loginName);
			if(userInfo == null){
				ReturnVo<Object> vo = new ReturnVo<Object>();
				vo.setSuccess(false);
				UserManage.removeCurrUserInfo();
				System.out.println("loginName="+loginName +"====== "+strs[3]);
				vo.setMessage("userTimeOut please login!");
				LOGGER.info("用户拦截,用户session为空..."+"uri="+uri +"?loginName="+loginName);
				response.getWriter().print(new Gson().toJson(vo));
				response.getWriter().flush();

				return false;
			}

			UserManage.setCurrUserInfo(userInfo);
			Date loginTime = userInfo.getLoginTime();
			Integer loginDate = Integer.valueOf(PropertiesUtil.getProperty("login_date"));
			Date loginDateMiss = DateUtils.addMinute(loginTime, loginDate);
			if(new Date().getTime() > loginDateMiss.getTime()){//登录超期
				ReturnVo<Object> vo = new ReturnVo<Object>();
				vo.setSuccess(false);
				vo.setMessage("userTimeOut please login!");
				LOGGER.info("用户拦截,用户session为空..");
				response.getWriter().print(new Gson().toJson(vo));
				response.getWriter().flush();
				return false;
			}else{
				return true;
			}
		}


		//session为空跳转到登录
		String loginPath = path + "/system/index.html";
		if(null == userInfo){
			try {
				LOGGER.info("用户拦截,用户session为空...2");
				response.getWriter().print(renturnUrl(loginPath));
				response.getWriter().flush();
			} catch (IOException e) {
				LOGGER.error("",e);
			}
			return false;
		}

		//过滤用户是否有当前菜单权限
		String no_right_paths = PropertiesUtil.getProperty("no_filter_paths");
		if(!StringUtils.isBlank(no_right_paths)){ // 不需要拦截的
			String[] paths = no_right_paths.toUpperCase().split(",");
			for(String item : paths){
				if(uriMap.equalsIgnoreCase(item)){
					return true;
				}
			}
		}
		UserManage.setCurrUserInfo(userInfo);
		@SuppressWarnings("unchecked")
		List<String> urls = (List<String>)session.getAttribute(Constants.SESSION_USER_URLS);

		if(urls.contains(url)){
			return true;
		}

		return true;//先对请求的url地址不做权限拦截
//		LOGGER.info("没有权限进行登陆");
//		return false;
	}

	/**
	 * This implementation is empty.
	 */
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	private String renturnUrl(String url){
		String startContent = "<html>\r\n" + "	<head>\r\n"
				+ "		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>";
		String ajaxContent = "<script type=\"text/javascript\">" + "window.parent.document.location.href='"
				+ url + "'" + "</script>";
		String endContent = "</head>\r\n" + "	<body>\r\n<span>userTimeOut</span>" + "	    \r\n" + "	</body>\r\n" + "</html>";

		return startContent + ajaxContent + endContent;
	}

}
