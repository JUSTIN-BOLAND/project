package com.deyi.controller;


import com.deyi.dao.DealerPayMapper;
import com.deyi.dao.DicExpirationDateMapper;
import com.deyi.dao.UserDao;
import com.deyi.entity.*;
import com.deyi.service.ActionLogService;

import com.deyi.service.DealerService;
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
@RequestMapping(value = "dealer")
public class DealerController extends Component<Dealer> {
	private Logger log = LoggerFactory.getLogger(Dealer.class);

	@Autowired
	private DealerService dealerService;
    @Autowired
    private DealerPayMapper dealerPayDao;

    @Autowired
    private DicExpirationDateMapper dicExpirationDateDao;
    @Autowired
    private UserDao userDao;
	@Autowired
	private ActionLogService actionLogService;


	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("dealer/dealerList");
		return mav;
	}

	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, Dealer dealer, Page<Dealer> page, HttpServletRequest request) {
		UserInfo userinfo = UserManage.getCurrUserInfo();
		String userType = userinfo.getType();

		setParams(request, dealer, page);
        page.getParams().put("userId",userinfo.getId());
        page.getParams().put("creator",userinfo.getId());
		List<Dealer> dealerList = dealerService.getDealers(page);
		page.setResults(dealerList);
		mav.addObject("page", page);
		mav.setViewName("dealer/pageDealer");
		return mav;

	}

	@RequestMapping(value = "toAddDealer")
	public ModelAndView toAddDealer(ModelAndView mav) {
        List<DicExpirationDate> dicExpirationDateList = dicExpirationDateDao.getDicExpirationDates(new DicExpirationDate());
        mav.addObject("dealerId",this.genDealerCode());
        mav.addObject("dicExpirationDateList",dicExpirationDateList);
		mav.setViewName("dealer/dealerAdd");
		return mav;
	}

	@RequestMapping(value = "addDealer")

	public @ResponseBody ReturnVo<Object>  addDealer( HttpServletRequest request, HttpServletResponse response,@RequestParam("adlogofile") MultipartFile[] files,Dealer dealer) {
        ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();

        String filePath =  request.getSession().getServletContext().getRealPath("/");
        filePath = filePath + "logo"+System.getProperty("file.separator");
        File pathFile = new File(filePath);
        if(!pathFile.exists()) pathFile.mkdirs();


        MultipartFile file =  files[0];
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String imgFileName = genLogoImgName()+suffix;
        String uploadFileName =filePath + imgFileName;

        String saveLogoFileName = "logo/"+imgFileName;
        this.log("addDealer","filePath="+filePath+",uploadFileName="+uploadFileName);
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
                if(fos!=null) fos.close();
                if(is!=null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(Constants.USER_SYS.equals(userInfo.getType()))       dealer.setParentId("-1");
        else if(Constants.USER_DEALER.equals(userInfo.getType()))    {
            Dealer param = new Dealer();
            param.setUserId(userInfo.getId());
            Dealer resultDealer = dealerService.getDealerByCode(param);
            dealer.setParentId(resultDealer.getId()+"");
        }
        dealer.setAdLogo(saveLogoFileName);
		dealer.setCreator(userInfo.getId());
		dealer.setCreateTime(new Date());
		dealer.setStatus("0");
		dealerService.insert(dealer);
		actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_ADD, String.format("【%s】添加代理商【%s】", userInfo.getName(),dealer.getDealerId()));
        ModelAndView mav = new ModelAndView();
       mav.setViewName("dealer/dealerList");
        vo.setSuccess(true);
        vo.setMessage("保存成功");
		return vo;
	}

	@RequestMapping("enable")
	public @ResponseBody ReturnVo<Object> enable(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			UserInfo userInfo = UserManage.getCurrUserInfo();
			Dealer CurDealer = dealerService.getDealer(id);
            Dealer dealer = new Dealer();
            dealer.setId(CurDealer.getId());
			dealer.setStatus("0");
			dealerService.update(dealer);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),dealer.getDealerId()));
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
            Dealer CurDealer = dealerService.getDealer(id);
            Dealer dealer = new Dealer();
            dealer.setId(CurDealer.getId());
			dealer.setStatus("1");
			dealerService.update(dealer);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),dealer.getDealerId()));
			vo.setSuccess(true);

		}catch(Exception e){
			log.info("",e);
			vo.setSuccess(false);
		}
		 return vo;
	}
	
	@RequestMapping("toEditDealer")
	public ModelAndView toEditDealer(ModelAndView mav, Integer id){
		Dealer dealer  = dealerService.getDealer(id);
        List<DicExpirationDate> dicExpirationDateList = dicExpirationDateDao.getDicExpirationDates(new DicExpirationDate());

        mav.addObject("dicExpirationDateList",dicExpirationDateList);
		mav.addObject("dealer", dealer);
		mav.setViewName("dealer/dealerEdit");
		return mav;
	}
	
	@RequestMapping("editDealer")

	public @ResponseBody ReturnVo<Object> editDealer(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestParam("adlogofile") MultipartFile[] files,Dealer dealer){
		ReturnVo<Object> vo = new ReturnVo<>();
        UserInfo userInfo = UserManage.getCurrUserInfo();
        Dealer curDealer = dealerService.getDealer(dealer.getId());
        if(!curDealer.getAdLogo().equals(dealer.getAdLogo())) {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            filePath = filePath + "logo" + System.getProperty("file.separator");
            File pathFile = new File(filePath);
            if (!pathFile.exists()) pathFile.mkdirs();


            MultipartFile file = files[0];
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String imgFileName = genLogoImgName()+suffix;
            String uploadFileName =filePath + imgFileName;

            String saveLogoFileName = "logo/"+imgFileName;
            this.log("editDealer", "filePath=" + filePath + ",uploadFileName=" + uploadFileName);
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
            dealer.setAdLogo(saveLogoFileName);
        }
		try {
            UserInfo user = new UserInfo();
            user.setId(dealer.getUserId());
            user.setPassword(dealer.getPasswd());
            userDao.updateByPrimaryKeySelective(user);
			dealerService.update(dealer);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_EDIT, String.format("【%s】编辑代理商【%s】", userInfo.getName(),dealer.getDealerId()));
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
    @RequestMapping(value="deleteDealer")
    public ReturnVo<Object> deleteDealer(Integer id){
        ReturnVo<Object> vo = new ReturnVo<>();
       dealerService.delete(id);

        return vo;
    }
    @RequestMapping(value = "uniqueDealerName")
    public @ResponseBody Boolean uniqueDealerName(ModelAndView mav, String dealerName) {
        Dealer dealerParam = new Dealer();
        dealerParam.setDealerName(dealerName);
        Dealer dealer = dealerService.getDealerByCode(dealerParam);
        if(dealer == null) return true;
        else return false;
    }

    @RequestMapping(value = "uniqueUpdateDealerName")
    public @ResponseBody Boolean uniqueUpdateDealerName(ModelAndView mav, String dealerName,String oldDealerName) {
        Dealer dealerParam = new Dealer();
        dealerParam.setDealerName(dealerName);
        dealerParam.setPasswd(oldDealerName);
        Dealer dealer = dealerService.getDealerByCode(dealerParam);
        if(dealer == null) return true;
        else return false;
    }
    @RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    @Transactional
    public void export(Dealer dealer, Page<Dealer> page,HttpServletRequest request, HttpServletResponse resp) throws IOException {

        setParams(request, dealer, page);
        List<Dealer> dealerList = dealerService.getDealers(page);
        // 转换时间格式
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        HSSFWorkbook wb = new HSSFWorkbook();
        exportSheet(wb, dealerList);
        try {
            // File file = FileUtil.createOrGetFile("D:/" + name + ".xls");
            // 文件名称
            String fmString = "dealer";
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

    public void exportSheet(HSSFWorkbook wb, List<Dealer> list) {
        HSSFSheet sheet = wb.createSheet("经销商");
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
        String title = "经销商编号,经销商名称,设备序列号,经销商账号,账户有效期,营业执照名称,营业执照号,详细地址," +
                "联系人姓名,联系人邮箱,联系人手机,单次圈存最大金额,圈存机日限次数,圈存机月限次数,广告词,微信支付商户号,支付宝商户号,状态,创建时间";
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
            Dealer dealer = list.get(i);
            if (dealer != null) {
                values[0] = dealer.getDealerId();
                values[1]= dealer.getDealerName();
                values[2] = dealer.getSeries();
                String expirationId =dealer.getAccountExpiration();
               /* if("1".equals(expirationId)) expirationId="一年";
                else if("2".equals(expirationId)) expirationId="二年";
                else if("3".equals(expirationId)) expirationId="三年";
                else if("5".equals(expirationId)) expirationId="五年";*/
                values[3]  = dealer.getUserId();
                values[4] = expirationId;

                values[5]  = dealer.getBusLicence();
                values[6]  = dealer.getBusLicenceNo();

                values[7]  = dealer.getAddress();
                values[8]  = dealer.getLinkman();
                values[9]  = dealer.getEmail();
                values[10]  = dealer.getMobile();
                values[11]  = dealer.getSingleMaxAmount();
                values[12] = dealer.getDayMaxCount();
                values[13] = dealer.getMonthMaxCount();
                values[14]= dealer.getAdWords();
                values[15]= dealer.getWxMchId();
                values[16]= dealer.getAliMachId();
                String  status= dealer.getStatus();
                if("0".equals(status)) status="启用";
                else status="禁用";
                values[17]=status;
                Date oCreateTime = dealer.getCreateTime();
                String oCreateTime2 = formatter.format(oCreateTime);// 创建时间createTime
                values[18]=oCreateTime2;
                for(int j=0;j<values.length; j++){
                    if (values[j] != null) {
                        row2.createCell(j).setCellValue(values[j]);
                    }
                }

            }
        }
    }


    @RequestMapping(value = "showConfigPayParam")
    public ModelAndView showConfigPayParam(ModelAndView mav,HttpServletRequest request) {
        UserInfo userInfo = UserManage.getCurrUserInfo();
        DealerPay dealerPay = null;
        this.log("showConfigPayParam","getType="+userInfo.getType()+" : "+Constants.USER_DEALER);
        if(Constants.USER_DEALER.equals(userInfo.getType())) {
            dealerPay = dealerPayDao.getDealerPayByUserId(userInfo.getId());

            String filePath = request.getSession().getServletContext().getRealPath("/");
            filePath = filePath + "authQr" + System.getProperty("file.separator");
            File pathFile = new File(filePath);
            if (!pathFile.exists()) pathFile.mkdirs();

            String imgPath = filePath + dealerPay.getDealerId() + ".bmp";
            File file = new File(imgPath);
            if (!file.exists()) {
                String url = getUrl(request) + "/trade/authScanCode.html?dealerId=" + dealerPay.getDealerId();
                QrCodeUtil.generateQrCode(filePath, url, dealerPay.getDealerId());
            }
        }
        else{
            dealerPay =new DealerPay();
            dealerPay.setDealerId("");
            dealerPay.setWxMchId("");
        }
        String qrUrl = "/authQr/"+dealerPay.getDealerId()+".bmp";
        mav.addObject("userType",userInfo.getType());
        mav.addObject("qrUrl",qrUrl);
        mav.addObject("dealerPay",dealerPay);
        mav.setViewName("dealer/configPayParam");
        return mav;
    }
    @RequestMapping(value = "editConfigPayParam")
    @Transactional
    public @ResponseBody ReturnVo<Object> editConfigPayParam(ModelAndView mav,HttpServletRequest request,DealerPay dealerPay) {
        ReturnVo<Object> vo  = new ReturnVo<Object>();
        DealerPay dealerPayParma = dealerPayDao.getDealerPay(dealerPay.getDealerId());
        if(dealerPayParma==null )  dealerPayDao.insert(dealerPay);
        else dealerPayDao.update(dealerPay);
        return vo;

    }
    @RequestMapping(value = "blank")
    public ModelAndView blank(ModelAndView mav,HttpServletRequest request) {
        mav.setViewName("dealer/blank");
        return mav;
    }

}
