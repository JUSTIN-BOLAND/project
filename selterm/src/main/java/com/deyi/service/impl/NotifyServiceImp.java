/**
 *
 */
package com.deyi.service.impl;

import com.alipay.api.response.AlipayTradeCancelResponse;
import com.deyi.dao.FacilorMapper;
import com.deyi.dao.OrderDao;


import com.deyi.dao.WxPublicMapper;

import com.deyi.entity.Facilor;
import com.deyi.entity.Merchant;
import com.deyi.entity.Order;
import com.deyi.entity.Org;
import com.deyi.service.INotifyService;
import com.deyi.service.OrgService;
import com.deyi.util.ConstantsPay;
import com.deyi.util.DateUtils;

import com.lb.al.AliPayInf;
import com.lb.wx.ResponseTrade;
import com.lb.wx.WxPublic;
import com.lb.wx.WxTradePay;
import com.lb.wx.WxUnifiedRes;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * pure
 * @author hejx
 * @2016年4月11日
 */
@Service("notifyService")
public class NotifyServiceImp implements INotifyService {
	private Logger log = LoggerFactory.getLogger(NotifyServiceImp.class);
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrgService orgService;
    @Autowired
    private FacilorMapper facilorMapper;
    @Autowired
    private WxPublicMapper wxPublicMapper;



	private static XStream XSTREAM = new XStream();

	@Override
	public String notify(String payType,String notifyUrl,Object param) throws Exception {
		try {

		    String returnCode = "";
			String resultCode = "";
			String oderNo = "";
			String tradeNo = "";
			String payTime = "";
			Double totalFee = 0d ;
			String ret = "success";
			String payAccount = "";
            Order order = new Order();

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
                //交易失败自动取消订单
				String tradeStatus =  params.get("trade_status").toUpperCase();
				log.info(" [notify] : ali :tradeStatus="+tradeStatus);
				if(!"TRADE_FINISHED".equals(tradeStatus) && !"TRADE_SUCCESS".equals(tradeStatus)
						&& !"TRADE_CLOSED".equals(tradeStatus) && !"WAIT_BUYER_PAY".equals(tradeStatus) ){
					AliPayInf aliPayInf = new AliPayInf();
					AlipayTradeCancelResponse cancelResponse = aliPayInf.checkQueryAndCancel(oderNo,1,0);
					//order.setPayStatus();
					if(cancelResponse!=null){
                        order.setCancelNo(cancelResponse.getTradeNo());
                        order.setCancelTime(new Date());
					}
				}

			}
			else if("2".equals(payType) ) {
				String wxXml = (String )param;
				log.info(" [notify] : wxRequest xml:"+wxXml);
				XSTREAM.alias("xml", WxUnifiedRes.class);
				WxUnifiedRes wxPrepayResp = (WxUnifiedRes) XSTREAM.fromXML(wxXml);
				returnCode = wxPrepayResp.getReturn_code();
				resultCode = wxPrepayResp.getResult_code();
				log.info(" [notify] : wx :returnCode="+returnCode+",resultCode="+resultCode);
				if(!ConstantsPay.SUCCESS.equals(resultCode)) {
					if("!USERPAYING".equals(returnCode)) {
						returnCode = "FAIL";
						ret = ConstantsPay.getFormatNotfyFail("付款失败");
                        WxPublic wxPublic = new WxPublic();
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
                            order.setCancelNo(cancelResponse.getWxQueryRes().getTransaction_id());
                            order.setCancelTime(new Date());
                        }

					}
					else ret = ConstantsPay.getFormatNotfyFail("等待付款");

				}
				else  {
                    ret=ConstantsPay.NOTIFY_SUCCESS;



                }
				resultCode = wxPrepayResp.getResult_code();
				oderNo = wxPrepayResp.getOut_trade_no();
				tradeNo = wxPrepayResp.getTransaction_id();
				payTime = wxPrepayResp.getTime_end();

				totalFee = (wxPrepayResp.getTotal_fee() ==null ?0d: Double.valueOf(wxPrepayResp.getTotal_fee())/100);
				payAccount = wxPrepayResp.getSub_openid();

			}


			order.setOrderNo(oderNo);
			System.out.println("lbs:"+returnCode);
			Order ord = orderDao.getOrder(oderNo);
			if(ConstantsPay.SUCCESS.equals(returnCode)){
			 //支付成功，修改订单

					order.setOrderStatus("1");

					if(payTime!= null && payTime.trim().length() > 0 ){
						Date payTimeDate =  null;
						if("1".equals(payType)){
							payTimeDate = DateUtils.formatStringToDate(payTime, "yyyy-MM-dd HH:mm:ss");
						}
						else  if("2".equals(payType)){
							payTimeDate = DateUtils.formatStringToDate(payTime, "yyyyMMddHHmmss");
						}
						order.setPayTime(payTimeDate);
					}
					order.setPayStatus("2");
					order.setPayNo(tradeNo);
					order.setPayMoney(totalFee);
					order.setPayAccount(payAccount);



				}
			  else {
				//order.setPayStatus("3");
				if("1".equals(ord.getPayMode())) order.setOrderStatus("2");
				else if("2".equals(ord.getPayMode())) order.setOrderStatus("4");
				order.setCancelTime(new Date());

			}

			if(ord!=null){
				String merCode = ord.getMerId();
				System.out.println("[1]: merCode="+(merCode==null?"null":merCode) );
				System.out.println("[1]: getRatemoney="+(ord.getRatemoney()==null?"null":ord.getRatemoney()) );
				System.out.println("[1]: getNotifyUrl="+(ord.getNotifyUrl()==null?"null":ord.getNotifyUrl()) );
				if(merCode!=null ){
					if( (ord.getRatemoney()==null || ord.getRatemoney().trim().length() ==0) ||
							(ord.getNotifyUrl()==null || ord.getNotifyUrl().trim().length() ==0)	) {
						Merchant merchant = null;//merchantService.getMerchantByCode(merCode);
						System.out.println("[2]: Rate="+(merchant==null?"null":merchant.getRate()) );
						if (merchant != null) {
							String rate = "1";

							if (merchant != null && merchant.getRate() != null) {
								rate = merchant.getRate();
								if (rate.trim().length() == 0) rate = "1";

							}
							String rateMoney = null;
							try {
								Double rateMoneyD = totalFee * Double.parseDouble(rate);
								rateMoney = String.format("%.2f", rateMoneyD / 1000);
							} catch (NumberFormatException e) {
								log.info("[notify->rate] : " + e.getMessage());
							}
							System.out.println("[3]: Rate="+rate +",rateMoney="+ rateMoney);
							order.setNotifyUrl(merchant.getRate());
							order.setRatemoney(rateMoney);
						}
					}

				}
			}
			log.info("[notifyGL] : order="+order.toString());
			orderDao.update(order);

				//异步通知渠道商
			boolean isSuccess = false;
			for(int i=0; i< 10; i++) {
				try {
					threadNotify(payType, oderNo, returnCode, notifyUrl);
					isSuccess = true;
				} catch (Exception e) {
					//e.printStackTrace();
					isSuccess = false;
					Thread.sleep(2000);
				}
				if(isSuccess) break;
			}
			if(!isSuccess){
				try {
					if(orderDao.getOrderAsynsCnt(order.getOrderNo())==0) orderDao.saveOrderAsyn(oderNo);
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}
				return ret;
			} catch (Exception e) {

                e.printStackTrace();
				return ConstantsPay.NOTIFY_SUCCESS;
			}
	}

	private void threadNotify(final String payType,final String orderNo,final String returnCode,final String notifyUrl) {
		new Thread(){
			public void run() {

				SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String notifyJson = "{\"success\":\"false\",\"message\":\"failure\",\"outTradeNo\":\"" + orderNo + "\",\"timeEnd\":\"" + dataTimeFormat.format(new Date()) + "\",\"sign\":\"\"}";
                String notifyUrlUse = notifyUrl;
				//if(ConstantsPay.SUCCESS.equals(returnCode)) {

					//notifyJson = "{\"success\":\"true\",\"message\":\"\",\"transactionId\":" + item.getPayNo() + ",\"openid\":" + item.getPayAccount() + "," + "\"outTradeNo\":" + item.getOrderNo() + ",\"timeEnd\":" + dataTimeFormat.format(item.getPayTime()) + ",\"payMoney\":" + item.getPayMoney() + "" + "\"payStatus\":" + item.getPayStatus() + ",\"sign\":\"\"}";
				//}
				log.info("[threadNotify]: notifyUrlUse=" + notifyUrlUse);
				try {
					Order item = orderDao.getOrder(orderNo);
					log.info("[threadNotify] :"+item.getOrderNo()+" -> "+item.getOrg_id());
					Org org = orgService.getOrgById(item.getOrg_id());
					if(org!=null) {
						if(org.getNotifyUrl()!=null && org.getNotifyUrl().trim().length() > 0 )  notifyUrlUse = org.getNotifyUrl();
					}
					//Org entity = orgService.getOrgById(item.getOrg_id() + "");
					XSTREAM.alias("xml", Order.class);
					notifyJson = XSTREAM.toXML(item);

					if(notifyUrlUse!= null && notifyUrlUse.trim().length() > 0 ) {
						String result = com.deyi.util.HttpClient.sendXml(notifyUrlUse, notifyJson, "UTF-8", "UTF-8");

						log.info("[threadNotify] : result:" + result);
					}
				} catch (Exception e) {
					log.error("threadNotify 异常",e);
				}

			}
		}.start();
	}




}
