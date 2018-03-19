package com.deyi.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deyi.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.dao.WorkTimeMapper;
import com.deyi.entity.Advertise;
import com.deyi.entity.Bulletin;
import com.deyi.entity.CityStd;
import com.deyi.entity.InheritRecordSheet;
import com.deyi.entity.Menu;
import com.deyi.entity.Merchant;
import com.deyi.entity.Org;
import com.deyi.entity.ProvinceStd;
import com.deyi.entity.Role;
import com.deyi.entity.Salesman;
import com.deyi.entity.SysRegion;
import com.deyi.entity.UserInfo;
import com.deyi.entity.UserOperator;
import com.deyi.entity.WorkTime;
import com.deyi.paysdk.utils.Configs;
import com.deyi.util.Component;
import com.deyi.util.Constants;
import com.deyi.util.DateUtils;
import com.deyi.util.DownloadUtils;
import com.deyi.util.Page;
import com.deyi.util.UserManage;
import com.deyi.util.ZipUtils;
import com.deyi.vo.OrderDaySum;
import com.deyi.vo.ReturnVo;
import com.unionpay.acp.sdk.SDKConfig;

@Controller
@RequestMapping(value = "system")
public class SystemController extends Component<Bulletin> {

	private final static Logger Log = Logger.getLogger(SystemController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private OrgService orgService;



	@Autowired
	private UserOperatorService userOperatorService;

	@Autowired
	private ISysRegionService sysRegionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AdvertiseService advertiseService;

	@Autowired
	private IProvinceService provinceService;

	@Autowired
	private BulletinService bulletinService;



	@Autowired
	private InheritRecordSheetService inheritRecordSheetService;
	@Autowired
	private  WorkTimeMapper workTimeMapper;
	@RequestMapping(value = "checkLoginName")
	public @ResponseBody Boolean checkLoginName(ModelAndView mav, String loginName) {
		UserInfo userInfo = userService.getUser(loginName);
		if (null == userInfo) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "main")
	public ModelAndView main(ModelAndView mav) {

		mav.setViewName("index");
		return mav;
	}

	/**
	 * 用户登录
	 *
	 * @return
	 */
	@RequestMapping(value = "login")
	public ModelAndView login(ModelAndView mav, String loginName, String passwd, HttpServletRequest request, Bulletin bulletin, Page<Bulletin> page) {
		HttpSession session = request.getSession();
		UserInfo userInfo = userService.getUser(loginName, passwd);

		if (userInfo == null) {
			mav.setViewName("login");
			mav.addObject("errMsg", "密码错误！");
			return mav;
		}
		String type = userInfo.getType();


		List<String> urls = new ArrayList<String>();// 对应角色有权限的url地址
		List<Menu> menuList = menuService.getMenusByUser(loginName);
		for (Menu menu : menuList) {
			String url = menu.getMenuUrl();
			if (!StringUtils.isBlank(url)) {
				String[] meunArray = url.split(",");
				for (String str : meunArray) {
					urls.add(str);
				}
			}
		}
		Object attribute = session.getAttribute(Constants.SESSION_USERINFO);
		if (null != attribute) {
			session.removeAttribute(Constants.SESSION_USERINFO);
		}

		session.setAttribute(Constants.MENU_LIST, menuList);
		session.setAttribute(Constants.SESSION_USERINFO, userInfo);
		session.setAttribute(Constants.SESSION_USER_URLS, urls);
		UserManage.setCurrUserInfo(userInfo);
		// 初始化属性文件
		Configs.init("zfbjspai.properties");
		SDKConfig.getConfig().loadPropertiesFromSrc();

		//测试 不要提交
//		test();

		 WorkTime 	workTime = workTimeMapper.getWorkTimeByStoreId("1221");
		System.out.println("-workTime-----"+workTime);
		mav.setViewName("redirect:main.html");
		return mav;
	}




	/**
	 * 初始化登录页面 方法用途: 用户首页<br>
	 *
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "index")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}

	/**
	 * pc退出登录
	 *
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loginOut")
	public ModelAndView loginOut(ModelAndView mav, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		UserManage.removeCurrUserInfo();
		mav.setViewName("forward:/system/index.html");
		return mav;
	}

	@RequestMapping(value = "toEditUserPad")
	public ModelAndView toEditUserPad(ModelAndView mav, HttpServletRequest request) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		mav.addObject("userInfo", userInfo);
		mav.setViewName("editUserPad");
		return mav;
	}

	@RequestMapping(value = "editUserPad")
	public @ResponseBody ReturnVo<Object> editUserPad(ModelAndView mav, UserInfo userinfo, HttpServletRequest request) {
		ReturnVo<Object> vo = new ReturnVo<>();
		try {
			userService.upadeUser(userinfo);

		} catch (Exception e) {
			vo.setSuccess(false);
			vo.setMessage("修改失败！");
		}
		return vo;
	}

	/**
	 * 验证原密码是否有
	 */
	@RequestMapping(value = "comparePad")
	public @ResponseBody Boolean comparePad(ModelAndView mav, String pad) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		String yuanPad = userInfo.getPassword();
		if (pad.equals(yuanPad)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 账号信息
	 */
	@RequestMapping("accountMessage")
	public ModelAndView accountMessage(ModelAndView mav, Integer id) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		String userType = userInfo.getType();
		String loginName = userInfo.getId();


			mav.setViewName("accountMessage/userOperatorMessage");


		return mav;
	}

	@RequestMapping(value = "systemindex")
	public ModelAndView systemindex(ModelAndView mav, HttpServletRequest request, Bulletin bulletin, Page<Bulletin> page) {

		UserInfo userInfo = UserManage.getCurrUserInfo();

		String type = userInfo.getType();
		String loginName = userInfo.getId();


		// 做×10倍的操作
//		orderDay.getPaymoney();
//		PaystatisVo paystatisVo = orderService.toDayCollectDay(merchantIds);

		java.math.BigDecimal d1=new java.math.BigDecimal("0");
		java.math.BigDecimal refundMoney =new java.math.BigDecimal("0");
		java.math.BigDecimal rateMoney =new java.math.BigDecimal("0");
		java.math.BigDecimal needRateMoney =new java.math.BigDecimal("0");

		java.math.BigDecimal d2=new java.math.BigDecimal(String.valueOf("0"));

		java.math.BigDecimal d3=new java.math.BigDecimal("0");
		java.math.BigDecimal d4=new java.math.BigDecimal(String.valueOf("0"));

		java.math.BigDecimal d5=new java.math.BigDecimal(String.valueOf("0"));
		java.math.BigDecimal d6=new java.math.BigDecimal(String.valueOf("0"));

		java.math.BigDecimal d7=new java.math.BigDecimal(String.valueOf("0"));
		java.math.BigDecimal d8=new java.math.BigDecimal(String.valueOf("0"));

		java.math.BigDecimal d9=new java.math.BigDecimal("0");
		java.math.BigDecimal d10=new java.math.BigDecimal("0");

		java.math.BigDecimal d11=new java.math.BigDecimal("0");
		java.math.BigDecimal d12=new java.math.BigDecimal("0");

		java.math.BigDecimal d13=new java.math.BigDecimal("0");
		java.math.BigDecimal d14=new java.math.BigDecimal(String.valueOf("0"));

		java.math.BigDecimal d15=new java.math.BigDecimal("0");
		java.math.BigDecimal d16=new java.math.BigDecimal(String.valueOf("0"));


		mav.addObject("summoney", d1.add(d2).setScale(2,BigDecimal.ROUND_HALF_UP));// 总金额
		mav.addObject("refundMoney", refundMoney.setScale(2,BigDecimal.ROUND_HALF_UP));// 总金额
		mav.addObject("rateMoney", rateMoney.setScale(2,BigDecimal.ROUND_HALF_UP));// 总金额
		mav.addObject("needRateMoney", needRateMoney.setScale(2,BigDecimal.ROUND_HALF_UP));// 总金额

		mav.addObject("sumNumber",d3.add(d4).setScale(0,BigDecimal.ROUND_HALF_UP));// 总数量
		mav.addObject("wxPaySum", d5.add(d6).setScale(0,BigDecimal.ROUND_HALF_UP));// 微信支付笔数
		mav.addObject("aliPaySum", d7.add(d8).setScale(0,BigDecimal.ROUND_HALF_UP));// 支付宝支付笔数
		mav.addObject("weixmoney",d9.add(d10).setScale(2,BigDecimal.ROUND_HALF_UP));// 微信金额
		mav.addObject("zhifbaomoney", d11.add(d12).setScale(2,BigDecimal.ROUND_HALF_UP));// 支付宝支付金额
		mav.addObject("unionSum", d13.add(d14).setScale(0,BigDecimal.ROUND_HALF_UP));// 银联笔数
		mav.addObject("unionmoney",d15.add(d16).setScale(2,BigDecimal.ROUND_HALF_UP));// 银联金额











		mav.addObject("userInfo", userInfo);
		mav.setViewName("indexnew");
		return mav;
	}

	@RequestMapping(value = "download")
	public void downlfile(String fileName, HttpServletRequest request, HttpServletResponse response) {
		Log.info("download: filepath: " + fileName);
		try {

			String fileSavePath = request.getSession().getServletContext().getRealPath("/");
			fileName = fileSavePath.substring(0, fileSavePath.indexOf("webapps") + 7) + fileName;
			DownloadUtils.download(request, response, fileName);
		} catch (IOException e) {
			Log.info("", e);
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "downloadorg")
	public void downorgfile(String id, HttpServletRequest request, HttpServletResponse response) {
		String fileSavePath = request.getSession().getServletContext().getRealPath("/");
		String rootpath = fileSavePath.substring(0, fileSavePath.indexOf("webapps") + 7);
		String path = rootpath + File.separator + "xingjpay" + File.separator + "zip";
		Org orgById = orgService.getOrgById(id);
		List<File> list = new ArrayList<File>();
		list.add(new File(rootpath + orgById.getAccountPermit()));
		list.add(new File(rootpath + orgById.getLegalIdentityCardPositive()));
		list.add(new File(rootpath + orgById.getLegalIdentityCardOpposite()));
		list.add(new File(rootpath + orgById.getBusinessLicense()));

		String filename = DateUtils.getDbDate() + DateUtils.getRandomNum();
		ZipUtils.createZip(filename + ".zip", path, list);

		try {
			String downfile = path + File.separator + filename + ".zip";
			Log.info("下载 :" + downfile);
			DownloadUtils.download(request, response, downfile);
			File file = new File(downfile);
			file.delete();
		} catch (IOException e) {
			Log.info("", e);
			e.printStackTrace();
		}

	}



}
