package com.deyi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.deyi.service.OrgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.entity.Bulletin;
import com.deyi.entity.Org;
import com.deyi.entity.UserInfo;
import com.deyi.service.ActionLogService;
import com.deyi.service.BulletinService;
import com.deyi.util.Component;
import com.deyi.util.Constants;
import com.deyi.util.Page;
import com.deyi.util.UserManage;
import com.deyi.vo.ReturnVo;

/**
 * 公告
 * @author admin
 *
 */

@Controller
@RequestMapping(value = "bulletin")
public class BulletinController extends Component<Bulletin> {
	
	private static Logger log = Logger.getLogger(BulletinController.class);
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private BulletinService bulletinService;
	
	@Autowired
	private ActionLogService actionLogSevice;
	
	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("bulletin/bulletinList");
		return mav;
	}
	
	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, Bulletin bulletin, Page<Bulletin> page, HttpServletRequest request) {
		UserInfo userinfo = UserManage.getCurrUserInfo();
		String userType = userinfo.getType();
		if (Constants.USER_OPERATOR.equals(userType)) {

			Org org = orgService.getOrgByorgAccount(userinfo.getId());
			if ("1".equals(org.getLevel())) {// 运营商
				 
			} else {// 代理商
				bulletin.setOrgid(Long.valueOf(org.getId()));
			}
		}
		setParams(request, bulletin, page);
		List<Bulletin> bulletinList = bulletinService.getBulletinList(page);
		page.setResults(bulletinList);		
		mav.addObject("page", page);
		mav.setViewName("bulletin/pageBulletin");
		return mav;
	}
	
	@RequestMapping(value = "toAddBuller")
	public ModelAndView toAddBuller(ModelAndView mav) {
		mav.setViewName("bulletin/bulletinAdd");
		return mav;
	}

	@RequestMapping(value = "addBuller")
	public @ResponseBody ReturnVo<Object> addBuller(ModelAndView mav, Bulletin bulletin, HttpServletRequest request) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			if (Constants.USER_OPERATOR.equals(userInfo.getType())) {
				Org org = orgService.getOrgByorgAccount(userInfo.getId());
				if ("1".equals(org.getLevel())) {// 运营商.
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String code = sdf.format(date);
					bulletin.setCode(code);
					bulletin.setCreatetime(date);
					bulletin.setCreator(org.getName());
					bulletin.setStatus("1");
					bulletin.setOrgid(Long.valueOf(org.getId()));
				} else {// 代理商
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String code = sdf.format(date);
					bulletin.setCode(code);
					bulletin.setCreatetime(date);
					bulletin.setCreator(org.getName());
					bulletin.setStatus("1");
					bulletin.setOrgid(Long.valueOf(org.getId()));
				}
			} 
			 bulletinService.insertSelective(bulletin);
			 
			 actionLogSevice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_BULLET, Constants.SUBTYPE_ADD,String.format("【%s】添加公告【%s】", userInfo.getName(),bulletin.getTitle()));
		}catch (Exception e){
			vo.setSuccess(false);
		}
		return vo;
	}
	
	@RequestMapping("revoke")
	public @ResponseBody ReturnVo<Object> revoke(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			
			UserInfo userInfo = UserManage.getCurrUserInfo();
			Bulletin bulletin = bulletinService.selectByPrimaryKey(Long.valueOf(id));
			bulletin.setStatus("2");
			bulletinService.updateByPrimaryKeySelective(bulletin);
			actionLogSevice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_BULLET, Constants.SUBTYPE_REVOKE,String.format("【%s】撤销公告【%s】", userInfo.getName(),bulletin.getTitle()));
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("",e);
		}
		 return vo;
	}
	
	@RequestMapping("toEditBulletin")
	public ModelAndView toEditBulletin(ModelAndView mav, Integer id){
		try{
			
			Bulletin bulletin = bulletinService.selectByPrimaryKey(Long.valueOf(id));
			
			mav.addObject("bulletin", bulletin);
			mav.setViewName("bulletin/editBulletin");
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("",e);
		}
		
		return mav;
	}
	
	@RequestMapping("editBulletin")
	public @ResponseBody ReturnVo<Object> editBulletin(ModelAndView mav, Bulletin bulletin){
		ReturnVo<Object> vo = new ReturnVo<>();
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			bulletinService.updateByPrimaryKeySelective(bulletin);
			actionLogSevice.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_BULLET, Constants.SUBTYPE_EDIT,String.format("【%s】编辑公告【%s】", userInfo.getName(),bulletin.getTitle()));
		}catch (Exception e){
			vo.setSuccess(false);
		}
		return vo;
	}
	
	@RequestMapping("bulletinDetails")
	public ModelAndView bulletinDetails(ModelAndView mav,Integer id){
		Bulletin bulletin = bulletinService.selectByPrimaryKey(Long.valueOf(id));
		mav.addObject("bulletin", bulletin);
		mav.setViewName("bulletin/bulletinDetails");
		return mav;
	}
	
	
	


}
