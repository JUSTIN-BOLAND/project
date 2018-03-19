package com.deyi.controller;


import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.deyi.dao.*;
import com.deyi.entity.*;

import com.deyi.pay.Pay;
import com.deyi.service.*;
import com.deyi.util.*;
import com.deyi.util.ConstantsPay;
import com.deyi.util.HttpClient;
import com.deyi.util.sms.SmsUtil;
import com.deyi.vo.ActionLogVo;
import com.deyi.vo.JsPayVo;
import com.deyi.vo.ReturnVo;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.lb.al.AliPayAuthInf;
import com.lb.al.AliPayInf;
import com.lb.al.AliPayPageInf;
import com.lb.msg.AliMsg;
import com.lb.util.*;
import com.lb.wx.ResponseTrade;
import com.lb.wx.WxTradePay;
import com.lb.wx.WxUnifiedRes;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(value="trade")
@Controller
public class TradePageContrller extends Component<ActionLogVo>{

	private static final Logger log = LoggerFactory.getLogger(TradePageContrller.class);
	private static XStream XSTREAM = new XStream();

	@Autowired
	private Pay pay;
	@Autowired
	private DeviceMapper deviceDao;
    @Autowired
    private CardMapper cardDao;
	@Autowired
	private DevicePlanMapper devicePlanDao;
	@Autowired
	private FacilorMapper facilorMapper;

	@Autowired
	private RechargeMapper rechargeMapper;

	@Autowired
	private ActionLogService actionLogService;


	@Autowired
	private DealerMapper dealerDao;

	@Autowired
	private DealerPayMapper dealerPayDao;




	@RequestMapping(value="error")
	public ModelAndView error(ModelAndView mav, String orderNo,String msg){
		Order order = null;//orderService.getOrder(orderNo);
		msg=(msg==null?"您取消订单或者支付报错":msg);
		try {
			msg = URLDecoder.decode(msg,"UTF-8");
			System.out.println(" [error] : msg="+msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		mav.addObject("message", msg);

		mav.setViewName("tradePage/mobile/error");
		return mav;
	}
	@RequestMapping(value = "createOrder")
	public @ResponseBody JsPayVo<Object> createOrder(ModelAndView mav,HttpServletRequest request, String payType, String deviceId,  String orderMoney,
                                                     String userId,String cardId,String planId,String actualAmount) {
		JsPayVo<Object> vo = new JsPayVo<Object>();
		//0:payType 1:deviceId 2: orderMoney 3 : userId  4:subAppId 5 : notifyUrl 6:body  7:loginUserId
		String orderNo = genNo("P");

		String notifyUrl= this.getUrl(request)+"/trade/"+("1".equals(payType)?"alUNotify.html":"wxUNotify.html");
		this.log("createOrder","payType="+payType+",deviceId="+deviceId+",orderMoney="+orderMoney+",actualAmount="+actualAmount+",userId="+userId+",notifyUrl="+notifyUrl);
		String[] params = {payType,deviceId,orderMoney,userId,null,notifyUrl,orderNo};


		Recharge recharge = new Recharge();
		recharge.setPayType(Integer.parseInt(payType));
		recharge.setOrderNo(orderNo);
		recharge.setDeviceId(Integer.parseInt(deviceId));
        recharge.setCardNo(Integer.parseInt(cardId));
        recharge.setPlanId(Integer.parseInt(planId));
        recharge.setPayAmount(Double.parseDouble(orderMoney));
        recharge.setActualAmount(Double.parseDouble(actualAmount));
		recharge.setServiceType(1);
        recharge.setPayStatus("0");
        recharge.setStatus("1");
		Device device = deviceDao.getDevice(Integer.parseInt(deviceId));

		if(device!=null) recharge.setDealerId(Integer.parseInt(device.getDealerId()));

		//recharge.setActualAmount(Double.parseDouble(orderMoney));
		recharge.setBuyerId(userId);
		recharge.setCreatTime(new Date());
		saveRecharge(recharge);
		try {

			String ret =  pay.createOrder(params);

			if (ret.trim().length() >  0 ) {
				JSONObject obj = JSONObject.fromObject(ret);
				if("0".equals(obj.get("success")) && "0".equals(obj.get("resultCode"))) {



					log.info("[trade->createOrder]  : userId=" + userId + " : ret=" + ret);
					vo.setSuccess(true);
					vo.setPayType(payType);
					vo.setData(obj.getString("orderNo"));
					log.info("[trade->createOrder]  : getData=" + vo.getData() + " : orderNo=" + obj.getString("orderNo"));
					if ("1".equals(payType)) vo.setTradeNo(obj.getString("tradeNo"));
					else if ("2".equals(payType)) {
						if (obj.has("tradeNo")) {
							JSONArray jsonArray = obj.getJSONArray("tradeNo");
							vo.setAppId(jsonArray.getString(0));
							vo.setTimeStamp(jsonArray.getString(1));
							vo.setNonceStr(jsonArray.getString(2));
							vo.setPrepayId(jsonArray.getString(3));
							vo.setPaySign(jsonArray.getString(4));

						}
					}
				}
				else{
					vo.setSuccess(false);
					if(obj.get("errCodeDes")!=null) vo.setMessage(obj.get("errCodeDes").toString());
				}

			} else {
				vo.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setSuccess(false);
		}
		vo.setData(orderNo);
		return vo;
	}
	@RequestMapping(value="notify")
	public void notify(ModelAndView mav,HttpServletRequest request,HttpServletResponse response){
		XStream XSTREAM2 = new XStream();
		try {
			String xml = HttpClient.getRequestContent(request);
			log.info(" [notify] : 返回xml:" + xml);
			XSTREAM2.alias("xml", OrderRet.class);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "payinfo")
	public ModelAndView paySuccess(ModelAndView mav, String orderNo) {
        Recharge parmRecharge = new Recharge();
        parmRecharge.setOrderNo(orderNo);
        Recharge recharge = rechargeMapper.getRechargeByCode(parmRecharge);
        mav.addObject("recharge",recharge);
		mav.setViewName("tradePage/mobile/payinfo");
		return mav;
	}

	@RequestMapping(value="wxUNotify")
	public void wxUNotify(ModelAndView mav,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("【wxUNotify】");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter print = null;
		try {
			this.log("wxUNotify","before");
			print = response.getWriter();
			this.log("wxUNotify","after");
			String xml = com.lb.util.HttpClient.getRequestContent(request);
		;
			this.log("wxUNotify","返回xml:"+xml);

			String returnXml = notify("2",xml);

			print.print(returnXml);
		}catch(Exception e){
			log.error("系统异常",e);
			this.log("wxUNotify->error",e.getMessage());
			print.print(ConstantsPay.getFormatNotfyFail("系统错误"));
		}
	}
	@RequestMapping(value="alUNotify")
	public void alUNotify(ModelAndView mav,HttpServletRequest request,HttpServletResponse response){
		System.out.println("【alUNotify】");

		response.setContentType("text/html; charset=utf-8");

		PrintWriter print = null;
		try {
			print = response.getWriter();
			Map<String,String> params = com.lb.util.HttpClient.getRequestParams(request);

			String returnXml = notify("1",params);


			print.print(returnXml);
		}catch(Exception e){
			log.error("系统异常",e);
			this.log("alUNotify->error",e.getMessage());
			print.print(ConstantsPay.getFormatNotfyFail("系统错误"));
		}
	}

	protected  String notify(String payType,Object param){

		String returnCode = "";
		String resultCode = "";
		String oderNo = "";
		String tradeNo = "";
		String payTime = "";
		Double totalFee = 0d ;
		String ret = "success";
		String payAccount = "";
		Recharge recharge = new Recharge();
		recharge.setPayType(Integer.parseInt(payType));
		//支付宝
		if("1".equals(payType) ) {
			Map<String,String> params = (Map<String,String>)param;
			returnCode = params.get("trade_status").toUpperCase().replace("TRADE_","");
			if("WAIT_BUYER_PAY".equals(returnCode)) returnCode="USERPAYING";
			//用户等待返回fail,其他都返回成功[成功和失败]
			if(!"USERPAYING".equals(returnCode)) returnCode="SUCCESS";
			else returnCode = "FAIL";
			//log.info(" [notify] : ALI:returnCode="+returnCode);
			oderNo = params.get("out_trade_no");
			tradeNo = params.get("trade_no");
			payTime = params.get("gmt_payment");
			totalFee =  (params.get("total_amount") == null? 0d:Double.valueOf( params.get("total_amount")));
			payAccount =  params.get("buyer_logon_id");
			ret=returnCode;
			log.info(" [notify] : ali :payTime="+payTime);
			//交易失败自动取消订单
			String tradeStatus =  params.get("trade_status").toUpperCase();
			this.log(" [notify] : ali ","tradeStatus="+tradeStatus);
			if(!"TRADE_FINISHED".equals(tradeStatus) && !"TRADE_SUCCESS".equals(tradeStatus)
					&& !"TRADE_CLOSED".equals(tradeStatus) && !"WAIT_BUYER_PAY".equals(tradeStatus) ){
				AliMsg aliMsg = new AliMsg();
				Facilor facilor = facilorMapper.selectByPrimaryKey(Long.valueOf(2L));
				aliMsg.setDomain(PropertiesUtil.getProperty("ali_api_domain"));
				aliMsg.setAppId(facilor.getAppid());
				aliMsg.setPrivateKey(facilor.getSecret());
				aliMsg.setAliPublicKey(facilor.getKey());
				aliMsg.setSignType("RSA2");
				AliPayInf aliPayInf = new AliPayInf(aliMsg);
				AlipayTradeCancelResponse cancelResponse = aliPayInf.checkQueryAndCancel(oderNo,1,0);

				if(cancelResponse!=null){

					recharge.setOperatorId("2"); //撤销订单
					recharge.setPayStatus("3"); //撤销订单
				}
				else {
					recharge.setOperatorId("3"); //支付中
					recharge.setPayStatus("2");
				}
			}
			if("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) 	recharge.setPayStatus("1");
            else  recharge.setPayStatus("2");
            if("WAIT_BUYER_PAY".equals(tradeStatus))  recharge.setOperatorId("1");
            else   if("TRADE_CLOSED".equals(tradeStatus))  recharge.setOperatorId("3");


        }
		else if("2".equals(payType) ) {
			String wxXml = (String )param;
			//log.info(" [notify] : wxRequest xml:"+wxXml);

			XSTREAM.alias("xml", WxUnifiedRes.class);
			WxUnifiedRes wxPrepayResp = (WxUnifiedRes) XSTREAM.fromXML(wxXml);
			returnCode = wxPrepayResp.getReturn_code();
			resultCode = wxPrepayResp.getResult_code();
			log.info(" [notify] : wx :returnCode="+returnCode+",resultCode="+resultCode);
			if(!ConstantsPay.SUCCESS.equals(resultCode)) {
				if("!USERPAYING".equals(returnCode)) {
					returnCode = "FAIL";
					ret = ConstantsPay.getFormatNotfyFail("付款失败");
					com.lb.wx.WxPublic wxPublic = new com.lb.wx.WxPublic();
					wxPublic.setMchId(wxPrepayResp.getSub_mch_id());
					Facilor facilor = facilorMapper.selectByPrimaryKey(Long.valueOf(1));
					com.lb.wx.Facilor wxFacilor = new com.lb.wx.Facilor();
					wxFacilor.setAppid(facilor.getAppid());
					wxFacilor.setId(facilor.getId());
					wxFacilor.setKey(facilor.getKey());
					wxFacilor.setSecret(facilor.getSecret());
					wxFacilor.setCertpath(facilor.getCertpath());
					wxFacilor.setMchid(facilor.getMchid());
					WxTradePay wxTradePay = new WxTradePay(wxFacilor,wxPublic);

					ResponseTrade cancelResponse =  wxTradePay.checkQueryAndCancel(wxPrepayResp.getOut_trade_no());
					if(cancelResponse!= null) {
						log.info("[notify] : " + cancelResponse.getWxQueryRes().toString());
						//order.setCancelNo(cancelResponse.getWxQueryRes().getTransaction_id());
						//order.setCancelTime(new Date());
						recharge.setOperatorId("2");
						recharge.setPayStatus("3");
					}
					else {
						recharge.setPayStatus("2");
					}

				}
				else {
					ret = ConstantsPay.getFormatNotfyFail("等待付款");
					recharge.setOperatorId("1");
				}
				recharge.setPayStatus("2");

			}
			else  {
				ret=ConstantsPay.NOTIFY_SUCCESS;

               recharge.setPayStatus("1");

			}
			resultCode = wxPrepayResp.getResult_code();
			oderNo = wxPrepayResp.getOut_trade_no();
			tradeNo = wxPrepayResp.getTransaction_id();
			payTime = wxPrepayResp.getTime_end();

			totalFee = (wxPrepayResp.getTotal_fee() ==null ?0d: Double.valueOf(wxPrepayResp.getTotal_fee())/100);
			payAccount = wxPrepayResp.getSub_openid();

		}

		recharge.setOrderNo(oderNo);
		recharge.setOutTradeNo(tradeNo);
        recharge.setPayAmount(totalFee);
		recharge.setBuyerId(payAccount);
		log.info(" [notify] : payType="+payType+",payTime="+payTime);
		if("2".equals(payType)) recharge.setPayTime(DateUtils.convertStrToDate(payTime,DateUtils.YMDHMS));
		else recharge.setPayTime(DateUtils.convertStrToDate(payTime,DateUtils.Y_M_D_HMS));
		recharge.setCreatTime(new Date());

        //更新支付流水的状态
		saveRecharge(recharge);

        //支付成功，则更新卡的账户余额
        if("1".equals(recharge.getPayStatus())){
			Recharge paramRecharge = new Recharge();
			paramRecharge.setOrderNo(oderNo);
			recharge = rechargeMapper.getRechargeByCode(paramRecharge);
			this.log("notify->balance","orderNo="+recharge.getOrderNo()+",cardNo="+recharge.getCardNo());
            Recharge parmRecharge = new Recharge();
            parmRecharge.setOrderNo(recharge.getOrderNo());
            recharge = rechargeMapper.getRechargeByCode(parmRecharge);
            Card curCard = cardDao.getCard(recharge.getCardNo());
			Double oldBalance = curCard.getBalance();
            if(curCard!=null) {

                Card card = new Card();
                card.setId(recharge.getCardNo());
                card.setBalance( oldBalance+ recharge.getActualAmount());
				this.log("notify->balance","newBalance="+card.getBalance()+",oldBalance="+oldBalance + ",ActualAmount="+ recharge.getActualAmount());
                cardDao.update(card);
				recharge.setBeforeAmount(oldBalance);
				recharge.setAfterAmount(card.getBalance());
				//rechargeMapper.update(recharge);
            }
        }
		rechargeMapper.update(recharge);
		return ret;
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
	@RequestMapping(value="alAuthCallback")
	public String  alAuthCallback(ModelAndView mav,HttpServletRequest request,HttpServletResponse response){
		String appAuthCode= request.getParameter("app_auth_code");
		String dealerId= request.getParameter("dealerId");
		Facilor facilor = facilorMapper.selectByPrimaryKey(2L);
		String url="tradePage/mobile/error";
		if(facilor!=null) {
			AliMsg aliMsg = new AliMsg();

			aliMsg.setDomain(PropertiesUtil.getProperty("ali_api_domain"));
			aliMsg.setAppId(facilor.getAppid());
			aliMsg.setPrivateKey(facilor.getSecret());
			aliMsg.setAliPublicKey(facilor.getKey());
			aliMsg.setSignType("RSA2");
			AliPayAuthInf aliAuth = new AliPayAuthInf(aliMsg);
			//this.log("alAuthCallback","getDomain="+aliMsg.getDomain()+"\n getAppId="+aliMsg.getAppId()+"\ngetPrivateKey="+aliMsg.getPrivateKey()+"\ngetPublicKey="+aliMsg.getPublicKey());
			AlipayOpenAuthTokenAppResponse authResponse = aliAuth.getAuthTokenByCode(AliPayAuthInf.AUTH_CODE, appAuthCode);
			this.log("alAuthCallback","getErrorCode="+aliAuth.getErrorCode());
			if (aliAuth.getErrorCode() == 0) {
				String authToken = authResponse.getAppAuthToken();
				String userId = authResponse.getUserId();
				String authAppId = authResponse.getAuthAppId();
				String refreshToken = authResponse.getAppRefreshToken();
				DealerPay dealerPay = dealerPayDao.getDealerPay(dealerId);
				if(dealerPay == null) {
					DealerPay iDealerPay = new DealerPay();
					iDealerPay.setDealerId(dealerId);
					iDealerPay.setAliMachId(userId);
					iDealerPay.setAppAuthToken(authToken);
					dealerPayDao.insert(iDealerPay);
				}
				else{
					//if(!dealerPay.getAliMachId().trim().equals(userId.trim())){
						dealerPay.setAliMachId(userId);
						dealerPay.setAppAuthToken(authToken);
                        dealerPay.setAppAuthToken(authToken);
						dealerPayDao.update(dealerPay);
					//}
				}
                this.log("alAuthCallback","authToken"+authToken+",userId="+userId+",authAppId="+authAppId+",refreshToken="+refreshToken);
				url="https://b.alipay.com/settling/index.htm?appId="+facilor.getAppid();
			}
			else url += "?message=获取授权信息失败";
		}
		else url += "?message=获取商户支付宝参数失败";
		return "redirect:"+url;

	}

	@RequestMapping(value="authScanCode")
	public ModelAndView authScanCode(ModelAndView mav,HttpServletRequest request,String dealerId){
		this.log("authScanCode","dealerId="+dealerId);
		Facilor facilor = facilorMapper.selectByPrimaryKey(2L);
		if(facilor!=null) {
			String callbackUrl = this.getUrl(request) + "/" + "trade/alAuthCallback.html?dealerId="+dealerId;
			this.log("authScanCode", "callbackUrl=" + callbackUrl);
			String returnUrl = "https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=" + facilor.getAppid() + "&redirect_uri=" + URLEncoder.encode(callbackUrl);
			this.log("authScanCode", "returnUrl=" + returnUrl);
			return new ModelAndView("redirect:" + returnUrl);
		}
		return null;
	}
	@RequestMapping(value = "updatePayStatus")
	public @ResponseBody void updatePayStatus(ModelAndView mav,String orderNo,String payStatus) {

		Recharge recharge = new Recharge();
		recharge.setOrderNo(orderNo);
		recharge.setPayStatus(payStatus);
		rechargeMapper.update(recharge);

	}
    @RequestMapping(value = "isExistCard")
    public @ResponseBody Boolean isExistCard(ModelAndView mav,String cardNo,String deviceId) {
		Dealer dealer = deviceDao.getDealerByDeviceId(deviceId);
        Card paramCard = new Card();
        paramCard.setCardNo(cardNo);
		paramCard.setCreator(dealer.getUserId());
        Card card = cardDao.getCardByCode(paramCard);
       // this.log("validPwd","cardNo="+cardNo+",passwd="+passwd+",srcPasswd="+card.getPasswd());
        if(card == null) return false;
         else return true;
    }
	@RequestMapping(value = "uniqueCardName")
	public @ResponseBody Boolean uniqueCardName(ModelAndView mav, String cardName) {
		Card cardParam = new Card();
		cardParam.setName(cardName);
		Card card = cardDao.getCardByCode(cardParam);
		if(card == null) return false;
		else return true;
	}
    @RequestMapping(value = "validPwd")
	public @ResponseBody Boolean validPwd(ModelAndView mav,String cardNo,String passwd) {

		Card paramCard = new Card();
		paramCard.setCardNo(cardNo);
		Card card = cardDao.getCardByCode(paramCard);
		this.log("validPwd","cardNo="+cardNo+",passwd="+passwd+",srcPasswd="+card.getPasswd());
		if(card == null) return false;
		if(passwd.trim().equals(card.getPasswd())) return true;
		else return false;
	}

	@RequestMapping(value = "validOldPwd")
	public @ResponseBody Boolean validOldPwd(ModelAndView mav,String cardId,String passwd) {

		Card paramCard = new Card();
		paramCard.setId(Integer.parseInt(cardId));
		//paramCard.setPasswd(passwd);
		Card card = cardDao.getCardByCode(paramCard);
		this.log("validOldPwd","cardId="+cardId+",passwd="+passwd+",srcPasswd="+card.getPasswd());
		if(card == null) return false;
		else if(!card.getPasswd().equals(passwd)) return false;
		return true;
	}
	@RequestMapping(value = "validReset")
	public @ResponseBody int validReset(ModelAndView mav,String cardNo,String name,String mobile) {

		Card paramCard = new Card();
		paramCard.setCardNo(cardNo);
        this.log("validReset","cardNo="+cardNo+",name="+name+",mobile="+mobile);
		Card card = cardDao.getCardByCode(paramCard);

		if(card == null) return 1;
		else {
			if(!card.getName().equals(name)) return 2;
			else if(!card.getMobile().equals(mobile)) return 3;
		}
		return 0;
	}

	@RequestMapping(value = "sendSms")
	public @ResponseBody String sendSms(ModelAndView mav,HttpServletRequest request,String mobile,int operate) {
		String[] optNames = {"用户注册","修改资料","卡挂失"};
        String smsCode ="";
        try {
            smsCode = SmsUtil.send(mobile,optNames[operate]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("smsCode",smsCode);
		return smsCode;
	}
	@RequestMapping(value="scanCode")
	public ModelAndView scanCode(ModelAndView mav,HttpServletRequest request){
		String deviceCode = request.getParameter("deviceCode");

		if(deviceCode==null || deviceCode.trim().length() ==0 ){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","二维码URL中少设备编号["+deviceCode+"]参数或者参数格式不对");
			return  mav;
		}
		Device paramDevice = new Device();
        paramDevice.setDeviceCode(deviceCode);
		Device device = deviceDao.getDeviceByCode(paramDevice);
		if(device==null  ){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","二维码URL中设备["+deviceCode+"]不存在");
			return  mav;
		}

		if("1".equals(device.getStatus())  ){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","二维码URL中设备["+deviceCode+"]被禁用");
			return  mav;
		}
		if("1".equals(device.getMachineStatus())  ){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","二维码URL中设备["+deviceCode+"]不在线");
			return  mav;
		}

		Dealer dealer = dealerDao.getDealer(Integer.parseInt(device.getDealerId()));
		if(dealer==null  ){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","二维码URL中设备["+deviceCode+"]对应的经销商不存在");
			return  mav;
		}
		if("1".equals(dealer.getStatus())  ){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","二维码URL中设备["+deviceCode+"]对应的经销商["+dealer.getDealerName()+"]被禁用");
			return  mav;
		}

		mav.addObject("deviceId",device.getId());
		if(device.getDeviceType() == 1 ){ //圈存
			mav.setViewName("tradePage/mobile/login");
		} else {
			//mav.setViewName("tradePage/mobile/recharge");
			return redirectPay(mav,request,device,null);
		}

		return mav;
	}

	protected  ModelAndView redirectPay(ModelAndView mav,HttpServletRequest request,Device device,Card card){
		Facilor facilor= facilorMapper.selectByPrimaryKey(2L);
		String aliAppid = facilor.getAppid();
		facilor= facilorMapper.selectByPrimaryKey(1L);
		String wxAppid = facilor.getAppid();
		String webUrl = this.getUrl(request);
		String aliCallbackUrl = webUrl + "/trade/ali_callback.html?deviceId="+device.getId();
		if(card != null) aliCallbackUrl = webUrl + "/trade/ali_callback.html?deviceId="+device.getId()+"&cardId="+card.getId();

		String wxCallbackUrl = webUrl + "/trade/wxCallback.html?deviceId="+device.getId();
		if(card != null) wxCallbackUrl = webUrl + "/trade/wxCallback.html?deviceId="+device.getId()+"&cardId="+card.getId();
		String aliAuthUrl="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id="+ aliAppid+
				"&scope=auth_base&redirect_uri="+URLEncoder.encode(aliCallbackUrl);
		log("scanCode","wxCallbackUrl="+wxCallbackUrl);


		String wxAuthUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ wxAppid
				+"&redirect_uri="+URLEncoder.encode(wxCallbackUrl)
				+"&response_type=code&scope=snsapi_base&state=gl1229$wechat_redirect";
		log("scanCode","wxAuthUrl="+wxAuthUrl);
		mav.addObject("aliAuthUrl",aliAuthUrl);
		mav.addObject("wxAuthUrl",wxAuthUrl);
		mav.setViewName("tradePage/mobile/scanCode");
		return mav;
	}

	@RequestMapping(value="ali_callback")
	public ModelAndView ali_callback(ModelAndView mav,HttpServletRequest request) {
		String deviceId = request.getParameter("deviceId");
		String cardId = request.getParameter("cardId");

		String authCode = request.getParameter("auth_code");

		System.out.println("[ali_callback] : deviceId=" + deviceId + "\nauthCode=" + (authCode == null ? "" : authCode));
		Facilor facilor = facilorMapper.selectByPrimaryKey(2L);
		if (facilor == null) {
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message", "支付宝未配置");
			return mav;
		}
		AliMsg aliMsg = new AliMsg();

		aliMsg.setDomain(PropertiesUtil.getProperty("ali_api_domain"));
		aliMsg.setAppId(facilor.getAppid());
		aliMsg.setPrivateKey(facilor.getSecret());
		aliMsg.setAliPublicKey(facilor.getKey());
		aliMsg.setSignType("RSA2");

		AliPayPageInf aliPayPageInf = new AliPayPageInf(aliMsg);
		String userId = aliPayPageInf.OauthToke(authCode, AliPayPageInf.USERID);
		mav.addObject("deviceId", deviceId);
		mav.addObject("userId", userId);
		mav.addObject("payType", "1");


		List<RechargePlan> rechargePlanList = devicePlanDao.getRechargePlans(Integer.parseInt(deviceId));
		mav.addObject("rechargePlanList", rechargePlanList);

		Device device = deviceDao.getDevice(Integer.parseInt(deviceId));

		mav.addObject("device",device);
		if (cardId != null && cardId.trim().length() > 0) {
			Card card = cardDao.getCard(Integer.parseInt(cardId));

			mav.addObject("card", card);
			mav.setViewName("tradePage/mobile/recharge");
		}
		else mav.setViewName("tradePage/mobile/rechargeOther");

		return mav;
	}
	@RequestMapping(value="wxCallback")
	public ModelAndView wxCallback(ModelAndView mav,HttpServletRequest request){
		String deviceId = request.getParameter("deviceId");
		String cardId = request.getParameter("cardId");
		Facilor facilor = facilorMapper.selectByPrimaryKey(Long.valueOf(1));
		if(facilor == null){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","微信未配置");
			return  mav;
		}

		String code = request.getParameter("code");

		log("wx_callback","deviceId="+deviceId+",code="+code);
		com.lb.wx.Facilor wxFacilor = new com.lb.wx.Facilor();
		wxFacilor.setAppid(facilor.getAppid());
		wxFacilor.setId(facilor.getId());
		wxFacilor.setKey(facilor.getKey());
		wxFacilor.setSecret(facilor.getSecret());
		wxFacilor.setCertpath(facilor.getCertpath());
		wxFacilor.setMchid(facilor.getMchid());

		WxTradePay wxTradePay =new WxTradePay(wxFacilor,null);
		String userId = wxTradePay.getUseropenid(code);
		mav.addObject("deviceId",deviceId);
		mav.addObject("payType","2");

		mav.addObject("userId", userId);


		List<RechargePlan> rechargePlanList =  devicePlanDao.getRechargePlans(Integer.parseInt(deviceId));
		mav.addObject("rechargePlanList",rechargePlanList);

		Device device = deviceDao.getDevice(Integer.parseInt(deviceId));

		mav.addObject("device",device);

		if (cardId != null && cardId.trim().length() > 0) {
			Card card = cardDao.getCard(Integer.parseInt(cardId));

			mav.addObject("card", card);
			mav.setViewName("tradePage/mobile/recharge");
		}
		else mav.setViewName("tradePage/mobile/rechargeOther");
		return mav;
	}

	@RequestMapping(value="login")
	public ModelAndView login(ModelAndView mav,HttpServletRequest request,String deviceId,String cardNo,String passwd){
		this.log("login","cardNo="+cardNo+",passwd="+passwd);

		Device device = deviceDao.getDevice(Integer.parseInt(deviceId));
        Card paramCard = new Card();
        paramCard.setCardNo(cardNo);
        Card card = cardDao.getCardByCode(paramCard);
        if(card==null){
            mav.setViewName("tradePage/mobile/error");
            mav.addObject("message","该卡号["+cardNo+"]对应的卡不存在");
            return  mav;
        }
		if("1".equals(card.getStatus())){
			mav.setViewName("tradePage/mobile/error");
			mav.addObject("message","该卡["+cardNo+"]已挂失");
			return  mav;
		}
        request.getSession().setAttribute("card",card);
		request.getSession().setAttribute("device",device);
        mav.addObject("card",card);
		mav.addObject("device",device);

		//mav.setViewName("tradePage/mobile/recharge");
		//return mav;
		return redirectPay(mav,request,device,card);
	}
	@RequestMapping(value="logOut")
	public ModelAndView logOut(ModelAndView mav,HttpServletRequest request,String deviceId){

		request.getSession().setAttribute("card",null);
		request.getSession().setAttribute("device",null);
		mav.addObject("deviceId",deviceId);
		mav.setViewName("tradePage/mobile/login");
		return mav;
	}
	@RequestMapping(value="toRecharge")
	public ModelAndView toRecharge(ModelAndView mav,HttpServletRequest request,String deviceId){

		Card card = (Card)request.getSession().getAttribute("card");
		Device device = (Device)request.getSession().getAttribute("device");

		List<RechargePlan> rechargePlanList =  devicePlanDao.getRechargePlans(device.getId());
		mav.addObject("card",card);
		mav.addObject("device",device);
		mav.addObject("rechargePlanList",rechargePlanList);
		return redirectPay(mav,request,device,card);
		/*mav.setViewName("tradePage/mobile/recharge");
		return mav;*/
	}
	@RequestMapping(value="recharge")
	public ModelAndView recharge(ModelAndView mav,HttpServletRequest request,String cardNo,String passwd){
		this.log("recharge","cardNo="+cardNo+",passwd="+passwd);

		mav.setViewName("tradePage/mobile/recharge");
		return mav;
	}


	@RequestMapping(value="toRegister")
	public ModelAndView toRegister(ModelAndView mav,HttpServletRequest request,String deviceId){
/*
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Dealer paramDealer = new Dealer();
		paramDealer.setUserId(userInfo.getId());
		Dealer dealer = dealerDao.getDealerByCode(paramDealer);
		mav.addObject("dealer",dealer);
*/
		mav.addObject("deviceId",deviceId);
		mav.setViewName("tradePage/mobile/register");
		return mav;
	}

	@RequestMapping(value="register")
	public ModelAndView register(ModelAndView mav,HttpServletRequest request,Card card,String deviceId){
		UserInfo userInfo = UserManage.getCurrUserInfo();
		Dealer dealer = deviceDao.getDealerByDeviceId(deviceId);

		card.setBatchNo("-1");
		card.setBalance(0d);
		card.setDeposit(0d);
		if(deviceId!=null) card.setCreator(dealer.getUserId());
		else card.setCreator("-1");
		card.setCreateTime(new Date());
		card.setMemo("会员注册");
		card.setStatus("0");
		this.log("register","DeviceName="+card.getDeviceName()+",passwd="+card.getPasswd());
		cardDao.insert(card);
		Device device = deviceDao.getDevice(Integer.parseInt(deviceId));
//		actionLogService.savelog(userInfo.getId(), userInfo.getName(), "注册", Constants.SUBTYPE_EDIT, String.format("【%s】注册【%s】", userInfo.getName(),card.getId()));
		request.getSession().setAttribute("card",card);
		request.getSession().setAttribute("device",device);
		mav.addObject("deviceId",deviceId);
		mav.addObject("card",card);
		mav.setViewName("tradePage/mobile/userCenter");
		return mav;
	}

	@RequestMapping(value="toUserCenter")
	public ModelAndView toUserCenter(ModelAndView mav,HttpServletRequest request/*,String cardId,String deviceId*/){
		/*UserInfo userInfo = UserManage.getCurrUserInfo();

		Device device = deviceDao.getDevice(Integer.parseInt(deviceId));
		Card card = cardDao.getCard(Integer.parseInt(cardId));
		request.getSession().setAttribute("card",card);
		request.getSession().setAttribute("device",device);
		*/

		/*Card card = (Card)request.getSession().getAttribute("card");
		Device device = (Device)request.getSession().getAttribute("device");


		mav.addObject("deviceId",device.getId());
		mav.addObject("card",card);*/
		mav.setViewName("tradePage/mobile/userCenter");
		return mav;
	}
	@RequestMapping(value="toModify")
	public ModelAndView toModify(ModelAndView mav,HttpServletRequest request){
		mav.setViewName("tradePage/mobile/modify");
		return mav;
	}

	@RequestMapping(value="modify")
	@ResponseBody
	ReturnVo<Object> modify(ModelAndView mav, HttpServletRequest request,Card card){
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
        cardDao.update(card);
        request.getSession().setAttribute("card",card);
		return vo;
	}

	@RequestMapping(value="toModifyPwd")
	public ModelAndView toModifyPwd(ModelAndView mav,HttpServletRequest request){
		mav.setViewName("tradePage/mobile/modifyPwd");
		return mav;
	}

	@RequestMapping(value="modifyPwd")
	@ResponseBody
	ReturnVo<Object> modifyPwd(ModelAndView mav, HttpServletRequest request,Card card){
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
		cardDao.update(card);
		request.getSession().setAttribute("card",card);
		return vo;
	}


	@RequestMapping(value="toReportLossCard")
	public ModelAndView toReportLossCard(ModelAndView mav,HttpServletRequest request){
		mav.setViewName("tradePage/mobile/reportLossCard");
		return mav;
	}

	@RequestMapping(value="reportLossCard")
	@ResponseBody
	ReturnVo<Object> reportLossCard(ModelAndView mav, HttpServletRequest request,String cardId){
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();

		try {
			Card card = new Card();
			card.setId(Integer.parseInt(cardId));
			card.setStatus("2");
			cardDao.update(card);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			vo.setSuccess(false);
		}
		return vo;
	}

	@RequestMapping(value="toResetPwd")
	public ModelAndView toResetPwd(ModelAndView mav,HttpServletRequest request){
		mav.setViewName("tradePage/mobile/resetPwd");
		return mav;
	}

	@RequestMapping(value="resetPwd")
	@ResponseBody
	ReturnVo<Object> resetPwd(ModelAndView mav, HttpServletRequest request,String cardNo){
		ReturnVo<Object> vo = new ReturnVo<>();
		UserInfo userInfo = UserManage.getCurrUserInfo();
        String defaultPwd = RandomUtil.random(1).toUpperCase()+RandomUtil.randomNum(2)+RandomUtil.random(2)+RandomUtil.randomNum(2);

		try {
			Card card = new Card();
			card.setId(Integer.parseInt(cardNo));
			card.setPasswd(defaultPwd);
			cardDao.update(card);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			vo.setSuccess(false);
		}
		vo.setMessage(defaultPwd);
		return vo;
	}
}
