import com.deyi.dao.RechargeMapper;
import com.deyi.dao.UserDao;
import com.deyi.entity.*;
import com.deyi.service.ActionLogService;

import com.deyi.util.*;
import com.deyi.vo.ReturnVo;
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
@RequestMapping(value = "recharge")
public class RechargeController extends Component<Recharge> {
	private Logger log = LoggerFactory.getLogger(Recharge.class);

    @Autowired
    private RechargeMapper rechargeDao;

    @Autowired
    private UserDao userDao;
	@Autowired
	private ActionLogService actionLogService;

@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("recharge/rechargeList");
		return mav;
	}

	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, Recharge recharge, Page<Recharge> page, HttpServletRequest request) {
		UserInfo userinfo = UserManage.getCurrUserInfo();
		String userType = userinfo.getType();

		setParams(request, recharge, page);
		List<Recharge> rechargeList = rechargeDao.getPageRecharges(page);
		page.setResults(rechargeList);
		mav.addObject("page", page);
		mav.setViewName("recharge/pageRecharge");
		return mav;

	}

	@RequestMapping(value = "toAdd")
	public ModelAndView toAdd(ModelAndView mav) {
		mav.setViewName("recharge/rechargeAdd");
		return mav;
	}

	@RequestMapping(value = "add")

	public @ResponseBody ReturnVo<Object>  add( HttpServletRequest request, HttpServletResponse response,Recharge recharge) {
        ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();

		recharge.setCreator(userInfo.getRolename());
		recharge.setCreateTime(new Date());
		recharge.setStatus("0");
		rechargeDao.insert(recharge);
		actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_ADD, String.format("【%s】添加充值明细【%s】", userInfo.getName(),recharge.getId()));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("recharge/rechargeList");
		vo.setSuccess(true);
		vo.setMessage("保存成功");
		return vo;
	}

	@RequestMapping("enable")
	public @ResponseBody ReturnVo<Object> enable(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			UserInfo userInfo = UserManage.getCurrUserInfo();
			Recharge CurRecharge = rechargeDao.getRecharge(id);
			Recharge recharge = new Recharge();
 			recharge.setId(CurRecharge.getId());
			recharge.setStatus("0");
			rechargeDao.update(recharge);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】启用【%s】", userInfo.getName(),recharge.getId()));
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
            Recharge CurRecharge = rechargeDao.getRecharge(id);
            Recharge recharge = new Recharge();
            recharge.setId(CurRecharge.getId());
			recharge.setStatus("1");
			rechargeDao.update(recharge);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),recharge.getId()));
			vo.setSuccess(true);

		}catch(Exception e){
			log.info("",e);
			vo.setSuccess(false);
		}
		 return vo;
	}
	
	@RequestMapping("toEdit")
	public ModelAndView toEdit(ModelAndView mav, Integer id){
		Recharge recharge  = rechargeDao.getRecharge(id);
		mav.addObject("recharge", recharge);
		mav.setViewName("recharge/rechargeEdit");
		return mav;
	}
	
	@RequestMapping("edit")
	public @ResponseBody ReturnVo<Object> edit(HttpServletRequest request, HttpServletResponse response,Recharge recharge){
		ReturnVo<Object> vo = new ReturnVo<>();
        UserInfo userInfo = UserManage.getCurrUserInfo();
		try {
			rechargeDao.update(recharge);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_EDIT, String.format("【%s】编辑充值明细【%s】", userInfo.getName(),recharge.getId()));
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
       rechargeDao.delete(id);

        return vo;
    }
    @RequestMapping(value = "uniqueRechargeName")
    public @ResponseBody Boolean uniqueRechargeName(ModelAndView mav, String rechargeName) {
        Recharge rechargeParam = new Recharge();
        rechargeParam.setRechargeName(rechargeName);
        Recharge recharge = rechargeDao.getRechargeByCode(rechargeParam);
        if(recharge == null) return true;
        else return false;
    }

    @RequestMapping(value = "uniqueUpdateRechargeName")
    public @ResponseBody Boolean uniqueUpdateRechargeName(ModelAndView mav, String rechargeName,String oldRechargeName) {
        Recharge rechargeParam = new Recharge();
        rechargeParam.setRechargeName(rechargeName);
        rechargeParam.setPasswd(oldRechargeName);
        Recharge recharge = rechargeDao.getRechargeByCode(rechargeParam);
        if(recharge == null) return true;
        else return false;
    }
@RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@Transactional
	public void export(Recharge recharge,HttpServletRequest request, HttpServletResponse resp) throws IOException {


		List<Recharge> rechargeList = rechargeDao.getRecharges(recharge);
		// 转换时间格式
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		HSSFWorkbook wb = new HSSFWorkbook();
        String[] titleFields={"商户订单号,经销商名称,机器名称,机器编号,支付方式,付款金额,到账金额,卡号,业务类型,操作前余额,操作后余额,时间,支付状态,业务结果",
                              "orderNo,authCode,subMerchantId,memo,payType,payAmount,actualAmount,sendCmd,serviceType,beforeAmount,afterAmount,payTime,payStatus,status"};
		exportSheet(wb, rechargeList,"充值明细",titleFields);
		try {
			// 文件名称
			String fmString = "recharge";
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

	public void exportSheet(HSSFWorkbook wb, List<Recharge> list,String sheetName,String[] titleFields) {
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
			Recharge recharge = list.get(i);
			if (recharge != null) {
                for(int j = 0 ; j < fields.length; j++){
                    Object value = this.getMethodValue(recharge,fields[j]);
                    this.log("exportSheet","value="+value);
                    values[j] = ( value == null ? "":value.toString());
                    if (values[j] != null) {
                        row2.createCell(j).setCellValue(values[j]);
                    }
                }

				

			}
		}
	}
}