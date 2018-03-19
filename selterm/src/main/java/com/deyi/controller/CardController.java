package com.deyi.controller;

import com.deyi.dao.CardMapper;
import com.deyi.dao.DealerMapper;
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
@RequestMapping(value = "card")
public class CardController extends Component<Card> {
	private Logger log = LoggerFactory.getLogger(Card.class);

    @Autowired
    private CardMapper cardDao;
	@Autowired
	private RechargeMapper rechargeMapper;
	@Autowired
	private DealerMapper dealerDao;
    @Autowired
    private UserDao userDao;
	@Autowired
	private ActionLogService actionLogService;

@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("card/cardList");
		return mav;
	}

	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, Card card, Page<Card> page, HttpServletRequest request) {
		UserInfo userinfo = UserManage.getCurrUserInfo();
		String userType = userinfo.getType();

		setParams(request, card, page);
		page.getParams().put("creator",userinfo.getId());
		List<Card> cardList = cardDao.getPageCards(page);
		page.setResults(cardList);
		mav.addObject("page", page);
		mav.setViewName("card/pageCard");
		return mav;

	}

	@RequestMapping(value = "toAdd")
	public ModelAndView toAdd(ModelAndView mav) {
		mav.setViewName("card/cardAdd");
		return mav;
	}

	@RequestMapping(value = "add")

	public @ResponseBody ReturnVo<Object>  add( HttpServletRequest request, HttpServletResponse response,Card card) {
        ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();

		card.setCreator(userInfo.getId());
		card.setCreateTime(new Date());
		card.setStatus("0");
		card.setFlag("0");
		cardDao.insert(card);
		actionLogService.savelog(userInfo.getId(), userInfo.getName(),  "一卡通", Constants.SUBTYPE_ADD, String.format("【%s】添加设备【%s】", userInfo.getName(),card.getId()));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("card/cardList");
		vo.setSuccess(true);
		vo.setMessage("保存成功");
		return vo;
	}

	@RequestMapping("enable")
	public @ResponseBody ReturnVo<Object> enable(ModelAndView mav, Integer id){
		ReturnVo<Object> vo =new ReturnVo<>();
		try{
			UserInfo userInfo = UserManage.getCurrUserInfo();
			Card CurCard = cardDao.getCard(id);
			Card card = new Card();
 			card.setId(CurCard.getId());
			card.setStatus("0");
			card.setFlag("0");
			cardDao.update(card);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(),  "一卡通", Constants.SUBTYPE_REVOKE, String.format("【%s】启用【%s】", userInfo.getName(),card.getId()));
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
            Card CurCard = cardDao.getCard(id);
            Card card = new Card();
            card.setId(CurCard.getId());
			card.setStatus("1");
			card.setFlag("1");
			cardDao.update(card);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), "一卡通", Constants.SUBTYPE_REVOKE, String.format("【%s】禁用【%s】", userInfo.getName(),card.getId()));
			vo.setSuccess(true);

		}catch(Exception e){
			log.info("",e);
			vo.setSuccess(false);
		}
		 return vo;
	}
	
	@RequestMapping("toEdit")
	public ModelAndView toEdit(ModelAndView mav, Integer id){
		Card card  = cardDao.getCard(id);
		mav.addObject("card", card);
		mav.setViewName("card/cardEdit");
		return mav;
	}
	
	@RequestMapping("edit")
	public @ResponseBody ReturnVo<Object> edit(HttpServletRequest request, HttpServletResponse response,Card card){
		ReturnVo<Object> vo = new ReturnVo<>();
        UserInfo userInfo = UserManage.getCurrUserInfo();
		try {
			cardDao.update(card);
			actionLogService.savelog(userInfo.getId(), userInfo.getName(), "一卡通", Constants.SUBTYPE_EDIT, String.format("【%s】编辑设备【%s】", userInfo.getName(),card.getId()));
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
       cardDao.delete(id);

        return vo;
    }
    @RequestMapping(value = "uniqueName")
    public @ResponseBody Boolean uniqueCardName(ModelAndView mav, String cardName) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
        Card cardParam = new Card();
        cardParam.setName(cardName);
		cardParam.setCreator(userInfo.getId());
        Card card = cardDao.getCardByCode(cardParam);
        if(card == null) return true;
        else return false;
    }

	@RequestMapping(value = "uniqueCardNo")
	public @ResponseBody Boolean uniqueCardNo(ModelAndView mav,String cardNo) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Card paramCard = new Card();
		paramCard.setCardNo(cardNo);
		paramCard.setCreator(userInfo.getId());
		Card card = cardDao.getCardByCode(paramCard);
		// this.log("validPwd","cardNo="+cardNo+",passwd="+passwd+",srcPasswd="+card.getPasswd());
		if(card == null) return true;
		else return false;
	}

    @RequestMapping(value = "uniqueUpdateCardNo")
    public @ResponseBody Boolean uniqueUpdateCardNo(ModelAndView mav, String cardNo,String oldCardNo) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
        Card cardParam = new Card();
        cardParam.setCardNo(cardNo);
        cardParam.setPasswd(oldCardNo);
		cardParam.setCreator(userInfo.getId());
        Card card = cardDao.getCardByCode(cardParam);
		if(card == null) return true;
		else return false;
    }
	@RequestMapping(value = "uniqueUpdateCardName")
	public @ResponseBody Boolean uniqueUpdateCardName(ModelAndView mav, String cardName,String oldCardName) {
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Card cardParam = new Card();
		cardParam.setName(cardName);
		cardParam.setMemo(oldCardName);
		cardParam.setCreator(userInfo.getId());
		Card card = cardDao.getCardByCode(cardParam);
		if(card == null) return true;
		else return false;
	}

	@RequestMapping("toRecharge")
	public ModelAndView toRecharge(ModelAndView mav, Integer id){
		Card card  = cardDao.getCard(id);
		mav.addObject("card", card);
		mav.setViewName("card/recharge");
		return mav;
	}

	@RequestMapping("recharge")
	public @ResponseBody ReturnVo<Object> recharge(HttpServletRequest request, HttpServletResponse response,Card card){
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
		try {
			this.log("recharge","getServiceType="+card.getServiceType());
			if( 1 ==card.getServiceType())  {

				Card existCard = cardDao.getCard(card.getId());
				Double oldBalance = 0d ;
				if(existCard!=null) oldBalance = existCard.getBalance();
				Double rechargeMoney = card.getRecharge();
				Double curBalance = oldBalance + rechargeMoney;
				card.setBalance(curBalance);
				card.setDeposit(null);
				card.setRecharge(null);
				card.setServiceType(null);
				cardDao.update(card);
				String orderNo = this.genNo("P");

				Dealer paramDealer = new Dealer();
				paramDealer.setUserId(existCard.getCreator());
				Dealer dealer = dealerDao.getDealerByCode(paramDealer);

				Recharge recharge = new Recharge();
				recharge.setPayType(3);
				recharge.setServiceType(1);  //预存
				recharge.setOrderNo(orderNo);
				//recharge.setDeviceId(existCard.getd);
				if(dealer!=null) recharge.setDealerId(dealer.getId());
				recharge.setCardNo(card.getId());

				recharge.setPayAmount(rechargeMoney);
				recharge.setActualAmount(rechargeMoney);
				recharge.setBeforeAmount(oldBalance);
				recharge.setAfterAmount(curBalance);
				recharge.setPayStatus("1");
				recharge.setStatus("1");
				recharge.setOperatorId(userInfo.getId());
				recharge.setCreatTime(new Date());
				saveRecharge(recharge);
			}

			actionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_EDIT, String.format("【%s】充值【%s】", userInfo.getName(),card.getId()));
		}catch (Exception e){
			log.info("",e);
			vo.setSuccess(false);
			return vo;
		}
		vo.setSuccess(true);
		vo.setMessage("保存成功");
		return vo;
	}
	protected void saveRecharge(Recharge recharge){
		Recharge paramRecharge = new Recharge();
		paramRecharge.setOrderNo(recharge.getOrderNo());
		Recharge existRecharge = rechargeMapper.getRechargeByCode(paramRecharge);
		if(existRecharge == null) rechargeMapper.insert(recharge);
		else {
			recharge.setId(existRecharge.getId());
			recharge.setOrderNo(null);
			rechargeMapper.update(recharge);
		}
	}
@RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@Transactional
	public void export(Card card,HttpServletRequest request, HttpServletResponse resp) throws IOException {


		List<Card> cardList = cardDao.getCards(card);
		// 转换时间格式
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		HSSFWorkbook wb = new HSSFWorkbook();
        String[] titleFields={"卡号,姓名,身份证,手机号,账户余额,充值账户余额,业务状态,创建时间",
                              "cardNo,name,idCardNo,mobile,balance,deposit,status,createTime"};
		exportSheet(wb, cardList,"一卡通",titleFields);
		try {
			// 文件名称
			String fmString = "card";
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

	public void exportSheet(HSSFWorkbook wb, List<Card> list,String sheetName,String[] titleFields) {
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
			Card card = list.get(i);
			if (card != null) {
                for(int j = 0 ; j < fields.length; j++){
                    Object value = this.getMethodValue(card,fields[j]);
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