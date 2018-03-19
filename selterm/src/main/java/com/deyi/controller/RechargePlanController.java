package com.deyi.controller;


import com.deyi.dao.RechargePlanMapper;

import com.deyi.dao.UserDao;
import com.deyi.entity.RechargePlan;

import com.deyi.entity.DicExpirationDate;
import com.deyi.entity.UserInfo;
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
@RequestMapping(value = "rechargePlan")
public class RechargePlanController extends Component<RechargePlan> {
	private Logger log = LoggerFactory.getLogger(RechargePlan.class);


    @Autowired
    protected RechargePlanMapper rechargePlanDao;


    @Autowired
    private UserDao userDao;
	@Autowired
	private ActionLogService actionLogService;


	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("rechargePlan/rechargePlanList");
		return mav;
	}

	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, RechargePlan rechargePlan, Page<RechargePlan> page, HttpServletRequest request) {
		UserInfo userinfo = UserManage.getCurrUserInfo();
		String userType = userinfo.getType();

		setParams(request, rechargePlan, page);
        page.getParams().put("creator",userinfo.getId());
		List<RechargePlan> rechargePlanList = rechargePlanDao.getPageRechargePlans(page);
		page.setResults(rechargePlanList);
		mav.addObject("page", page);
		mav.setViewName("rechargePlan/pageRechargePlan");
		return mav;

	}

	@RequestMapping(value = "toAddRechargePlan")
	public ModelAndView toAddRechargePlan(ModelAndView mav) {

		mav.setViewName("rechargePlan/rechargePlanAdd");
		return mav;
	}

	@RequestMapping(value = "addRechargePlan")

	public @ResponseBody ReturnVo<Object>  addRechargePlan( HttpServletRequest request, HttpServletResponse response,@RequestParam("planImgfile") MultipartFile[] files,RechargePlan rechargePlan) {
        ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
        if(rechargePlan.getPlanImg()!=null && rechargePlan.getPlanImg().trim().length() > 0 ) {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            filePath = filePath + "rechargePlan" + System.getProperty("file.separator");
            File pathFile = new File(filePath);
            if (!pathFile.exists()) pathFile.mkdirs();


            MultipartFile file = files[0];
            String imgFileName = genRpImgName();
            String uploadFileName = filePath + imgFileName;
            String saveLogoFileName = "rechargePlan/" + imgFileName;
            this.log("addRechargePlan", "filePath=" + filePath + ",uploadFileName=" + uploadFileName);
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
                return vo;
            } finally {
                try {
                    if (fos != null) fos.close();
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            rechargePlan.setPlanImg(saveLogoFileName);
        }
        rechargePlan.setCreator(userInfo.getId());
        rechargePlan.setCreateTime(new Date());
        rechargePlan.setStatus(0);
		rechargePlanDao.insert(rechargePlan);
		actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_ADD, String.format("【%s】添加充值套餐【%s】", userInfo.getName(),rechargePlan.getId()));
        ModelAndView mav = new ModelAndView();
       mav.setViewName("RechargePlan/RechargePlanList");
        vo.setSuccess(true);
        vo.setMessage("保存成功");
		return vo;
	}

	@RequestMapping("enable")
	public @ResponseBody ReturnVo<Object> enable(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			UserInfo userInfo = UserManage.getCurrUserInfo();
			RechargePlan CurRechargePlan = rechargePlanDao.getRechargePlan(id);
            RechargePlan rechargePlan = new RechargePlan();
            rechargePlan.setId(CurRechargePlan.getId());
            rechargePlan.setStatus(0);
			rechargePlanDao.update(rechargePlan);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),rechargePlan.getId()));
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
            RechargePlan CurRechargePlan = rechargePlanDao.getRechargePlan(id);
            RechargePlan rechargePlan = new RechargePlan();
            rechargePlan.setId(CurRechargePlan.getId());
            rechargePlan.setStatus(1);
			rechargePlanDao.update(rechargePlan);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),rechargePlan.getId()));
			vo.setSuccess(true);

		}catch(Exception e){
			log.info("",e);
			vo.setSuccess(false);
		}
		 return vo;
	}
	
	@RequestMapping("toEditRechargePlan")
	public ModelAndView toEditRechargePlan(ModelAndView mav, Integer id){
		RechargePlan rechargePlan  = rechargePlanDao.getRechargePlan(id);
        if(rechargePlan.getPlanImg()==null || rechargePlan.getPlanImg().trim().length() ==0 ){
            rechargePlan.setPlanImg("/man/images/upload.png");
        }
		mav.addObject("rechargePlan", rechargePlan);
		mav.setViewName("rechargePlan/rechargePlanEdit");
		return mav;
	}
	
	@RequestMapping("editRechargePlan")

	public @ResponseBody ReturnVo<Object> editRechargePlan(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestParam("planImgfile") MultipartFile[] files,RechargePlan rechargePlan){
		ReturnVo<Object> vo = new ReturnVo<>();
        UserInfo userInfo = UserManage.getCurrUserInfo();
        RechargePlan curRechargePlan = rechargePlanDao.getRechargePlan(rechargePlan.getId());
        if( (  rechargePlan.getPlanImg()!=null && rechargePlan.getPlanImg().trim().length() > 0 ) && !curRechargePlan.getPlanImg().equals(rechargePlan.getPlanImg())) {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            filePath = filePath + "rechargePlan" + System.getProperty("file.separator");
            File pathFile = new File(filePath);
            if (!pathFile.exists()) pathFile.mkdirs();


            MultipartFile file = files[0];
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String imgFileName = genRpImgName()+suffix;

            String uploadFileName = filePath + imgFileName;
            String saveLogoFileName = "rechargePlan/"+imgFileName;
            this.log("editRechargePlan", "filePath=" + filePath + ",uploadFileName=" + uploadFileName);
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
                return vo;
            } finally {
                try {
                    if (fos != null) fos.close();
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rechargePlan.setPlanImg(saveLogoFileName);
        }
		try {

			rechargePlanDao.update(rechargePlan);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_EDIT, String.format("【%s】编辑充值套餐【%s】", userInfo.getName(),rechargePlan.getId()));
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
    @RequestMapping(value="deleteRechargePlan")
    public ReturnVo<Object> deleteRechargePlan(Integer id){
        ReturnVo<Object> vo = new ReturnVo<>();
       rechargePlanDao.delete(id);

        return vo;
    }
    @RequestMapping(value = "uniqueRechargePlanName")
    public @ResponseBody Boolean uniqueRechargePlanName(ModelAndView mav, String rechargePlanName) {

        RechargePlan rechargePlanParam = new RechargePlan();
        rechargePlanParam.setPlanName(rechargePlanName);
        RechargePlan rechargePlan = rechargePlanDao.getRechargePlanByCode(rechargePlanParam);
        if(rechargePlan == null) return true;
        else return false;
    }

    @RequestMapping(value = "uniqueUpdateRechargePlanName")
    public @ResponseBody Boolean uniqueUpdateRechargePlanName(ModelAndView mav, String rechargePlanName,String oldRechargePlanName) {
        RechargePlan rechargePlanParam = new RechargePlan();
        rechargePlanParam.setPlanName(rechargePlanName);
        rechargePlanParam.setFlag(oldRechargePlanName);
        RechargePlan RechargePlan = rechargePlanDao.getRechargePlanByCode(rechargePlanParam);
        if(RechargePlan == null) return true;
        else return false;
    }
    @RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    @Transactional
    public void export(RechargePlan RechargePlan, Page<RechargePlan> page,HttpServletRequest request, HttpServletResponse resp) throws IOException {

        setParams(request, RechargePlan, page);
        List<RechargePlan> RechargePlanList = rechargePlanDao.getPageRechargePlans(page);
        // 转换时间格式
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        HSSFWorkbook wb = new HSSFWorkbook();
        exportSheet(wb, RechargePlanList);
        try {
            // File file = FileUtil.createOrGetFile("D:/" + name + ".xls");
            // 文件名称
            String fmString = "rechargePlan";
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

    public void exportSheet(HSSFWorkbook wb, List<RechargePlan> list) {
        HSSFSheet sheet = wb.createSheet("充值套餐");
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
        String title = "套餐名称,充值金额,赠送金额,到账金额,售价,状态,描述,创建人,创建时间";
        String[] titles = title.split(",");
        String[] values = new String[titles.length];
        HSSFCell cell = null;
        for(int i=0 ;i< titles.length;i++){
            cell = row1.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 设置数据
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row2 = sheet.createRow(i + 1);
            RechargePlan rechargePlan = list.get(i);
            if (rechargePlan != null) {
                values[0] = rechargePlan.getPlanName()+"";
                values[1]= rechargePlan.getRefillAmount()+"";
                values[2] = rechargePlan.getGiftAmount()+"";

                values[3]  = rechargePlan.getActualAmount()+"";
                values[4] = rechargePlan.getPrice()+"";

                Integer  status= rechargePlan.getStatus();
                if(status == 0)   values[5]="启用";
                else   values[5]="禁用";


                values[6]  = rechargePlan.getMemo();
                values[7]  = rechargePlan.getCreator();


                Date oCreateTime = rechargePlan.getCreateTime();
                String oCreateTime2 = formatter.format(oCreateTime);// 创建时间createTime
                values[8]=oCreateTime2;
                for(int j=0;j<values.length; j++){
                    if (values[j] != null) {
                        row2.createCell(j).setCellValue(values[j]);
                    }
                }

            }
        }
    }




}
