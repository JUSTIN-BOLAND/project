package com.deyi.controller;

import com.deyi.entity.*;
import com.deyi.service.*;
import com.deyi.service.impl.FacilorServiceImpl;
import com.deyi.util.*;
import com.deyi.vo.ReturnVo;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "payConfig")
public class PayConfigController extends Component<Clerk> {

	private Logger LOG = LoggerFactory.getLogger(PayConfigController.class);

	@Autowired
	private FacilorServiceImpl facilorServiceImpl;


	@Autowired
	private ActionLogService actionLogService;

	@RequestMapping(value = "showPayConfig")
	public ModelAndView showPayConfig(ModelAndView mav,HttpServletRequest request) {
		UserInfo userInfo = UserManage.getCurrUserInfo();

		Facilor wxFacilor = facilorServiceImpl.getWxFacilor();
		if(wxFacilor==null) {
			wxFacilor = new Facilor();
			wxFacilor.setId(1L);
			wxFacilor.setAppid("");
			wxFacilor.setMchid("");
			wxFacilor.setKey("");
			wxFacilor.setSecret("");
			wxFacilor.setCertpath("");
		}
		Facilor aliFacilor = facilorServiceImpl.getAliFacilor();
		if(aliFacilor==null) {
			aliFacilor = new Facilor();
			aliFacilor.setId(2L);
			aliFacilor.setAppid("");
			aliFacilor.setMchid("");
			aliFacilor.setKey("");
			aliFacilor.setSecret("");
			aliFacilor.setCertpath("");
		}
		String userType = userInfo.getType();
		mav.addObject("userType", userType);
		mav.addObject("wxFacilor", wxFacilor);
		mav.addObject("aliFacilor", aliFacilor);
		if(wxFacilor.getCertpath().indexOf("\\") > -1 ) wxFacilor.setCertpath(wxFacilor.getCertpath().replace("\\","\\\\"));
		this.log("showPayConfig","Certpath="+wxFacilor.getCertpath());

		String webUrl = request.getRequestURL().toString();
		System.out.println("serverName="+request.getServerName());
		mav.addObject("authDoamin", this.getDomain(webUrl));
		mav.addObject("authPath", this.getUrl(webUrl)+"/trade/");
		mav.addObject("authIp", this.getIpAddress());


		mav.setViewName("payConfig/showPayConfig");
		return mav;
	}


	@RequestMapping(value = "upload")
	public @ResponseBody ReturnVo<Object> doUpload(HttpServletRequest request, HttpServletResponse response,
														@RequestParam("certfile") MultipartFile[] files) {
		//ModelAndView mav = new ModelAndView();
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();

		String wxAppid = request.getParameter("wxAppId");
		String wxMchId = request.getParameter("wxMchId");
		String wxSecret = request.getParameter("wxSecret");
		String wxKey = request.getParameter("wxKey");

		String aliAppId = request.getParameter("aliAppId");
		String aliMchId = request.getParameter("aliMchId");
		String aliSecret = request.getParameter("aliSecret");
		String aliKey = request.getParameter("aliKey");


		String certPath =  request.getSession().getServletContext().getRealPath("/");
		certPath = certPath + "cert"+System.getProperty("file.separator");
		File pathFile = new File(certPath);
		if(!pathFile.exists()) pathFile.mkdirs();
		else{
			File[] pFiles = pathFile.listFiles();
			if(pFiles!= null) {
				for (File f : pFiles) {

					if (f.isFile()) f.delete();
				}
			}
		}

		MultipartFile file =  files[0];
		String uploadFileName =certPath + file.getOriginalFilename();
		this.log("doUpload","certPath="+certPath+",uploadFileName="+uploadFileName);
		InputStream is = null;
			FileOutputStream fos = null;
			try {
				File nFIle = new File(uploadFileName);
				//if(!nFIle.exists()) nFIle.createNewFile();

				is = file.getInputStream();
				fos = new FileOutputStream(nFIle);

				// 一次30kb
				byte[] readBuff = new byte[1024 * 30];
				int count = -1;
				while ((count = is.read(readBuff, 0, readBuff.length)) != -1) {
					fos.write(readBuff, 0, count);
				}
				fos.flush();
			} catch (IOException e) {
				e.printStackTrace();
				vo.setSuccess(false);
				vo.setMessage("上传文件失败");
			} finally {
				try {
					if(fos!=null) fos.close();
					if(is!=null) is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		Facilor wxFacilorShow = facilorServiceImpl.getWxFacilor();
		Facilor aliFacilorShow = facilorServiceImpl.getAliFacilor();

		Facilor wxSaveFacilor = new Facilor();
		wxSaveFacilor.setId(1L);
		wxSaveFacilor.setAppid(wxAppid);
		wxSaveFacilor.setMchid(wxMchId);
		wxSaveFacilor.setSecret(wxSecret);
		wxSaveFacilor.setKey(wxKey);
		wxSaveFacilor.setCertpath(uploadFileName);



		if(wxFacilorShow==null)  facilorServiceImpl.save(wxSaveFacilor);
		else facilorServiceImpl.update(wxSaveFacilor);
		Facilor aliSaveFacilor = new Facilor();
		aliSaveFacilor.setId(2L);
		aliSaveFacilor.setAppid(aliAppId);
		aliSaveFacilor.setMchid(aliMchId);
		aliSaveFacilor.setSecret(aliSecret);
		aliSaveFacilor.setKey(aliKey);


		if(aliFacilorShow==null)  facilorServiceImpl.save(aliSaveFacilor);
		else facilorServiceImpl.update(aliSaveFacilor);

		actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_PAYSET, Constants.SUBTYPE_EDIT,
				String.format("【%s】编辑支付配置", userInfo.getName()));





		/* wxFacilorShow = facilorServiceImpl.getWxFacilor();
		 aliFacilorShow = facilorServiceImpl.getAliFacilor();
		if(wxFacilorShow==null) {
			wxFacilorShow = new Facilor();
			wxFacilorShow.setId(1L);
			wxFacilorShow.setAppid("");
			wxFacilorShow.setMchid("");
			wxFacilorShow.setKey("");
			wxFacilorShow.setSecret("");
			wxFacilorShow.setCertpath("");
		}

		if(aliFacilorShow==null) {
			aliFacilorShow = new Facilor();
			aliFacilorShow.setId(2L);
			aliFacilorShow.setAppid("");
			aliFacilorShow.setMchid("");
			aliFacilorShow.setKey("");
			aliFacilorShow.setSecret("");
			aliFacilorShow.setCertpath("");
		}
		String userType = userInfo.getType();
		mav.addObject("userType", userType);
		mav.addObject("wxFacilor", wxFacilorShow);
		mav.addObject("aliFacilor", aliFacilorShow);

		String webUrl = request.getRequestURL().toString();
		System.out.println("serverName="+request.getServerName());
		mav.addObject("authDoamin", this.getDomain(webUrl));
		mav.addObject("authPath", this.getUrl(webUrl)+"/trade/");
		mav.addObject("authIp", this.getIpAddress());*/

		vo.setSuccess(true);
		vo.setMessage("保存成功");
		//mav.setViewName("payConfig/showPayConfig1");
		return vo;
	}



	/**
	 * 编辑店员 @RequestParam(value="fileUpload",required=false)
	 */
	@RequestMapping(value = "editPayConfig")
	@Transactional
	public @ResponseBody ReturnVo<Object> editPayConfig(ModelAndView mav,HttpServletRequest request,PayConfig payConfig) {
	//	ModelAndView mav = new ModelAndView();
		ReturnVo<Object> vo = new ReturnVo<>();
		try {
			UserInfo userInfo = UserManage.getCurrUserInfo();
			Facilor wxFacilor = facilorServiceImpl.getWxFacilor();
			Facilor wxSaveFacilor = new Facilor();
			wxSaveFacilor.setId(1L);
			wxSaveFacilor.setAppid(payConfig.getWxAppId());
			wxSaveFacilor.setMchid(payConfig.getWxMchId());
			wxSaveFacilor.setSecret(payConfig.getWxSecret());
			wxSaveFacilor.setKey(payConfig.getWxKey());



			if(wxFacilor==null)  facilorServiceImpl.save(wxSaveFacilor);
			else facilorServiceImpl.update(wxSaveFacilor);
			Facilor aliSaveFacilor = new Facilor();
			aliSaveFacilor.setId(2L);
			aliSaveFacilor.setAppid(payConfig.getAliAppId());
			aliSaveFacilor.setMchid(payConfig.getAliMchId());
			aliSaveFacilor.setSecret(payConfig.getAliSecret());
			aliSaveFacilor.setKey(payConfig.getAliKey());

			Facilor aliFacilor = facilorServiceImpl.getAliFacilor();
			if(aliFacilor==null)  facilorServiceImpl.save(aliSaveFacilor);
			else facilorServiceImpl.update(aliSaveFacilor);

			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_PAYSET, Constants.SUBTYPE_EDIT,
					String.format("【%s】编辑支付配置", userInfo.getName()));
		} catch (Exception e) {
			vo.setSuccess(false);
			vo.setMessage("修改失败！");
		}
		return vo;
	}


}
