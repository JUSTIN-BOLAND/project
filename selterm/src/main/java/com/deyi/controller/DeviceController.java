package com.deyi.controller;

import com.alipay.inf.trade.config.*;
import com.deyi.dao.*;
import com.deyi.entity.*;
import com.deyi.service.ActionLogService;

import com.deyi.util.*;
import com.deyi.util.Constants;
import com.deyi.vo.ReturnVo;
import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang.*;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping(value = "device")
public class DeviceController extends Component<Device> {
	private Logger log = LoggerFactory.getLogger(Device.class);

    @Autowired
    private DeviceMapper deviceDao;
	@Autowired
	private DeviceTypeMapper deviceTypeMapper;
	@Autowired
	private DealerMapper dealerDao;
	@Autowired
	private RechargePlanMapper rechargePlanMapper;

	@Autowired
	private DevicePlanMapper devicePlanMapper;
    @Autowired
    private UserDao userDao;
	@Autowired
	private ActionLogService actionLogService;

@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
	    List<DeviceType>  deviceTypes= deviceTypeMapper.getDeviceTypes();
	    mav.addObject("deviceTypes",deviceTypes);
	 	mav.setViewName("device/deviceList");
		return  mav;
	}

	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, Device device, Page<Device> page, HttpServletRequest request) {
		UserInfo userinfo = UserManage.getCurrUserInfo();
		String userType = userinfo.getType();

		setParams(request, device, page);
		List<Device> deviceList = deviceDao.getPageDevices(page);
		page.setResults(deviceList);
		mav.addObject("page", page);
		mav.setViewName("device/pageDevice");
		return mav;

	}

	@RequestMapping(value = "toAdd")
	public ModelAndView toAdd(ModelAndView mav) {
		List<DeviceType>  deviceTypes= deviceTypeMapper.getDeviceTypes();
		mav.addObject("deviceTypes",deviceTypes);
		Page<Dealer> page = new Page<Dealer>();
		UserInfo userinfo = UserManage.getCurrUserInfo();

		if(Constants.USER_SYS.equals(userinfo.getType())){
			// .set.setParentId("-1");
		}
		else{
		/*	Dealer paramDealer = new Dealer();
			paramDealer.setUserId(userinfo.getId());
			Dealer dealer = dealerDao.getDealerByCode(paramDealer);*/
			page.getParams().put("userId",userinfo.getId());
			page.getParams().put("creator",userinfo.getId());
		}
		List<Dealer> dealers = dealerDao.getDealers(page);
		mav.addObject("dealers",dealers);
		RechargePlan rechargePlan = new RechargePlan();
		rechargePlan.setCreator(userinfo.getId());
		List<RechargePlan> plans = rechargePlanMapper.getRechargePlans(rechargePlan);
		mav.addObject("plans",plans);
		mav.setViewName("device/deviceAdd");
		return mav;
	}

	@RequestMapping(value = "add")

	public @ResponseBody ReturnVo<Object>  add( HttpServletRequest request, HttpServletResponse response,Device device,String[] flag) {
        ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
        String planIds = device.getFlag();
		device.setFlag(null);
		device.setCreator(userInfo.getId());
		device.setCreateTime(new Date());
		device.setStatus("0");

		//生成二维码
		/*Dealer paramDealer = new Dealer();
		paramDealer.setUserId(userInfo.getId());
		Dealer dealer = dealerDao.getDealerByCode(paramDealer);
		if(dealer == null ) {

			vo.setSuccess(false);
			vo.setMessage("只有代理商能添加设备");
			return vo;
		}*/
		Dealer dealer = dealerDao.getDealer(Integer.parseInt(device.getDealerId()));
		String filePath = request.getSession().getServletContext().getRealPath("/");
		filePath = filePath + "deviceQr" + System.getProperty("file.separator");
		File pathFile = new File(filePath);
		if (!pathFile.exists()) pathFile.mkdirs();
		String fileName = dealer.getSeries()+device.getDeviceCode();
		String imgPath = filePath + fileName + ".bmp";
		File file = new File(imgPath);
		if (file.exists()) {
			file.delete();
		}
		String url = getUrl(request) + "/trade/scanCode.html?series="+dealer.getSeries()+"&deviceCode=" +device.getDeviceCode();
		log.info("[add] : url="+url);
		QrCodeUtil.generateQrCode(filePath, url, fileName);
		String qrUrl = "/deviceQr/"+fileName+".bmp";
		log.info("[add] :  fileName="+fileName+",filePath="+filePath);
		device.setQrCode(fileName);
		device.setQrUrl(qrUrl);
		deviceDao.insert(device);
		planIds = org.apache.commons.lang.StringUtils.join(flag,",");
		//log.info("[add] :"+(device.getId()==null?"id is null":device.getId())+"\nplanIds="+planIds+" : "+device.getFlag());
		devicePlanMapper.insert(device.getId(),planIds);


		actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_ADD, String.format("【%s】添加设备【%s】", userInfo.getName(),device.getId()));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("device/deviceList");
		vo.setSuccess(true);
		vo.setMessage("保存成功");
		return vo;
	}

	@RequestMapping("enable")
	public @ResponseBody ReturnVo<Object> enable(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			UserInfo userInfo = UserManage.getCurrUserInfo();
			Device CurDevice = deviceDao.getDevice(id);
			Device device = new Device();
 			device.setId(CurDevice.getId());
			device.setStatus("0");
			deviceDao.update(device);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】启用【%s】", userInfo.getName(),device.getId()));
			vo.setSuccess(true);

		}catch(Exception e){
			log.info("",e);
			vo.setSuccess(false);
		}
		return vo;
	}


	@RequestMapping("disable")
	public @ResponseBody ReturnVo<Object> disable(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			UserInfo userInfo = UserManage.getCurrUserInfo();
            Device CurDevice = deviceDao.getDevice(id);
            Device device = new Device();
            device.setId(CurDevice.getId());
			device.setStatus("1");
			deviceDao.update(device);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),device.getId()));
			vo.setSuccess(true);

		}catch(Exception e){
			log.info("",e);
			vo.setSuccess(false);
		}
		 return vo;
	}
	
	@RequestMapping("toEdit")
	public ModelAndView toEdit(ModelAndView mav, Integer id){
		List<DeviceType>  deviceTypes= deviceTypeMapper.getDeviceTypes();
		mav.addObject("deviceTypes",deviceTypes);
		Page<Dealer> page = new Page<Dealer>();
		UserInfo userinfo = UserManage.getCurrUserInfo();

		if(Constants.USER_SYS.equals(userinfo.getType())){
			// .set.setParentId("-1");
		}
		else{
			Dealer paramDealer = new Dealer();
			paramDealer.setUserId(userinfo.getId());
			Dealer dealer = dealerDao.getDealerByCode(paramDealer);
			page.getParams().put("parentId",dealer.getId());
		}
		List<Dealer> dealers = dealerDao.getDealers(page);
		mav.addObject("dealers",dealers);

		Device device  = deviceDao.getDevice(id);
		mav.addObject("device", device);

		RechargePlan rechargePlan = new RechargePlan();
		rechargePlan.setCreator(userinfo.getId());
		List<RechargePlan> plans = rechargePlanMapper.getRechargePlans(rechargePlan);


		mav.addObject("plans",plans);
		List<RechargePlan> selectPlans = rechargePlanMapper.getRechargePlansByDeviceId(id);
		mav.addObject("selectPlans",selectPlans);
		mav.setViewName("device/deviceEdit");
		return mav;
	}
	
	@RequestMapping("edit")
	public @ResponseBody ReturnVo<Object> edit(HttpServletRequest request, HttpServletResponse response,Device device,String[] flag){
		ReturnVo<Object> vo = new ReturnVo<>();
        UserInfo userInfo = UserManage.getCurrUserInfo();
		try {
			String planIds = device.getFlag();
			device.setFlag(null);
			deviceDao.update(device);
			planIds = org.apache.commons.lang.StringUtils.join(flag,",");
			log.info("[edit] :"+(device.getId()==null?"id is null":device.getId())+"\nplanIds="+planIds+" : "+device.getFlag());
			devicePlanMapper.delete(device.getId());
			devicePlanMapper.insert(device.getId(),planIds);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_EDIT, String.format("【%s】编辑设备【%s】", userInfo.getName(),device.getId()));
		}catch (Exception e){
			log.info("",e);
			vo.setSuccess(false);
            return vo;
		}
        vo.setSuccess(true);
        vo.setMessage("保存成功");
		return vo;
	}
    @ResponseBody
    @RequestMapping(value="delete")
    public ReturnVo<Object> delete(Integer id){
        ReturnVo<Object> vo = new ReturnVo<>();
       deviceDao.delete(id);

        return vo;
    }
    @RequestMapping(value = "uniqueDeviceName")
    public @ResponseBody Boolean uniqueDeviceName(ModelAndView mav, String deviceName) {
        Device deviceParam = new Device();
        deviceParam.setDeviceName(deviceName);
        Device device = deviceDao.getDeviceByCode(deviceParam);
        if(device == null) return true;
        else return false;
    }
	@RequestMapping(value = "uniqueDeviceCode")
	public @ResponseBody Boolean uniqueDeviceCode(ModelAndView mav, String deviceCode,String dealerId) {
		Device deviceParam = new Device();
		deviceParam.setDeviceCode(deviceCode);
		deviceParam.setDealerId(dealerId);
		Device device = deviceDao.getDeviceByCode(deviceParam);
		if(device == null) return true;
		else return false;
	}
	@RequestMapping(value = "uniqueUpdateDeviceCode")
	public @ResponseBody Boolean uniqueUpdateDeviceCode(ModelAndView mav, String deviceCode,String dealerId, String oldDeviceCode) {
		Device deviceParam = new Device();
		deviceParam.setDeviceCode(deviceCode);
		deviceParam.setDealerId(dealerId);
		deviceParam.setHints(oldDeviceCode);
		Device device = deviceDao.getDeviceByCode(deviceParam);
		if(device == null) return true;
		else {
			return false;
		}
	}
    @RequestMapping(value = "uniqueUpdateDeviceName")
    public @ResponseBody Boolean uniqueUpdateDeviceName(ModelAndView mav, String deviceName,String oldDeviceName) {
        Device deviceParam = new Device();
        deviceParam.setDeviceName(deviceName);
        deviceParam.setMemo(oldDeviceName);
        Device device = deviceDao.getDeviceByCode(deviceParam);
        if(device == null) return true;
        else return false;
    }



	@RequestMapping(value = "getSelectPlans")
	public @ResponseBody List<RechargePlan> getSelectPlans(ModelAndView mav, HttpServletRequest request, Integer deviceId, HttpServletResponse response) {
		 return  rechargePlanMapper.getRechargePlansByDeviceId(deviceId);

	}

	@RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@Transactional
	public void export(Device device,HttpServletRequest request, HttpServletResponse resp) throws IOException {

		String domain = this.getUrl(request);//request.getContextPath();
		List<Device> deviceList = deviceDao.getDevices(device);
		// 转换时间格式
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		HSSFWorkbook wb = new HSSFWorkbook();
        String[] titleFields={"设备名称,经销商名称,设备分类,设备列序号,机器编号,套餐,二维码编号,二维码URL,机器状态,业务状态,创建时间",
                              "deviceName,memo,flag,creator,deviceCode,planName,qrCode,qrUrl,machineStatus,status,createTime"};
		exportSheet(wb, deviceList,"设备",titleFields,domain);
		try {
			// 文件名称
			String fmString = "device";
			String filename = fmString + dateString;
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-disposition",
					"attachment;filename=" + new String((filename + ".xls").getBytes(), "iso-8859-1"));
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportSheet(HSSFWorkbook wb, List<Device> list,String sheetName,String[] titleFields,String domain) {
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(25); // 默认宽度
		sheet.setDefaultRowHeight((short) 400);
		HSSFRow row1 = sheet.createRow((int) 0);
		row1.setHeight((short) 500);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中样式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
		headerFont.setFontName("Times New Roman"); // 设置字体类型
		headerFont.setFontHeightInPoints((short) 8); // 设置字体大小
		style.setFont(headerFont); // 为标题样式设置字体样式
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		//String title = "设备名称,经销商名称,设备分类,设备列序号,机器编号,套餐,机器状态,业务状态,创建时间";
		String[] titles = titleFields[0].split(",");
		String[] values = new String[titles.length];
		HSSFCell cell = null;
		for(int i=0 ;i< titles.length;i++){
			cell = row1.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}

		String[] fields =  titleFields[1].split(",");
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 设置数据
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row2 = sheet.createRow(i + 1);
			Device device = list.get(i);
			if (device != null) {
                for(int j = 0 ; j < fields.length; j++){
                    Object value = this.getMethodValue(device,fields[j]);
                    this.log("exportSheet","value="+value);
                    values[j] = ( value == null ? "":value.toString());
                    if (values[j] != null) {
						if("qrUrl".equals(fields[j])) values[j] = domain+values[j];
                        row2.createCell(j).setCellValue(values[j]);
                    }
                }



			}
		}
	}

}
