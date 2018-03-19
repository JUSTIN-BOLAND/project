package cn.saobei;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import cn.saobei.entity.request.pay.Cancelpay;
import cn.saobei.entity.request.pay.JsPay;
import cn.saobei.entity.request.pay.Micropay;
import cn.saobei.entity.request.pay.PayQuery;
import cn.saobei.entity.request.pay.PrePay;
import cn.saobei.entity.request.pay.Refund;
import cn.saobei.entity.response.pay.CancelResp;
import cn.saobei.entity.response.pay.JsPayResp;
import cn.saobei.entity.response.pay.MicroPayResp;
import cn.saobei.entity.response.pay.PayQueryResp;
import cn.saobei.entity.response.pay.PrePayResp;
import cn.saobei.entity.response.pay.RefundResp;
import cn.saobei.entity.utils.HttpUtils;

/**
 * 集成扫呗的支付SDK<br/>
 * 1.在使用该里面的内容之前，我们首先要先进行初始化SDK(必须)<br/>
 * 2.在里面包含有刷卡支付，预支付，订单查询，退款功能
 * @author Enzo
 * @date 2016年11月29日
 */
public class PaySdk {

	private static final Logger  LOG = LoggerFactory.getLogger(PaySdk.class);
	/**
	 * 初始化参数
	 * @param inst_no  机构号
	 * @param merchant_no   商户号
	 * @param terminal_id   终端号
	 * @param access_token   终端号令牌
	 * void 
	 * @author Enzo
	 * @date 2016年11月24日
	 */
	public static void initsdk(String merchant_no,String terminal_id,String access_token){
		SaoConfig.setPay_ver("100");
//		SaoConfig.setInst_no(inst_no);
		SaoConfig.setAccess_token(access_token);
		SaoConfig.setMerchant_no(merchant_no);
		SaoConfig.setTerminal_id(terminal_id);
	}
	
	public static void clearconfig(){
		SaoConfig.setAccess_token(null);
		SaoConfig.setMerchant_no(null);
		SaoConfig.setTerminal_id(null);
	}
	
	/**
	 * 刷卡支付
	 * @param micropay
	 * @return
	 * @throws Exception   
	 * MicroPayResp 
	 * @author Enzo
	 * @date 2016年11月29日
	 */
	public static MicroPayResp micpay(Micropay micropay) throws Exception{
		String send = HttpUtils.send(SaoConfig.PAY_BARCODEPAY, micropay);
		LOG.info(send);
		MicroPayResp fromJson = new Gson().fromJson(send, MicroPayResp.class);
		System.out.println(fromJson.toString());
		return fromJson;
	}
	/**
	 * 撤销接口20170516 add by hjx
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static CancelResp cancel(Cancelpay entity) throws Exception{
		String send = HttpUtils.send(SaoConfig.PAY_CANCEL, entity);
		LOG.info("撤销："+send);
		CancelResp fromJson = new Gson().fromJson(send, CancelResp.class);
		System.out.println(fromJson.toString());
		return fromJson;
	}
	
	/**
	 * 订单查询
	 * @param payquery
	 * @return
	 * @throws Exception   
	 * PayQueryResp 
	 * @author Enzo
	 * @date 2016年11月29日
	 */
	public static PayQueryResp tradeQuery(PayQuery payquery)throws Exception{
		String send = HttpUtils.send(SaoConfig.PAY_QUERY, payquery);
		LOG.info(send);
		PayQueryResp fromJson = new Gson().fromJson(send, PayQueryResp.class);
		return fromJson;
	}
	
	
	/**
	 * 退款
	 * @param payquery
	 * @return
	 * @throws Exception   
	 * RefundResp 
	 * @author Enzo
	 * @date 2016年11月29日
	 */
	public static RefundResp Refund(Refund payquery)throws Exception{
		String send = HttpUtils.send(SaoConfig.PAY_REFUND, payquery);
		LOG.info(send);
		RefundResp fromJson = new Gson().fromJson(send, RefundResp.class);
		return fromJson;
	}
	
	/**
	 * 生成预支付订单
	 * @param payquery
	 * @return
	 * @throws Exception   
	 * PrePayResp 
	 * @author Enzo
	 * @date 2016年11月29日
	 */
	public static PrePayResp PrePay(PrePay payquery)throws Exception{
		String send = HttpUtils.send(SaoConfig.PAY_PREPAY, payquery);
		LOG.info(send);
		PrePayResp fromJson = new Gson().fromJson(send, PrePayResp.class);
		return fromJson;
	}
	
	
	/**
	 * 公众号支付
	 * @param jspay
	 * @return
	 * @throws Exception   
	 * JsPayResp 
	 * @author Enzo
	 * @date 2016年12月6日
	 */
	public static JsPayResp jspay(JsPay jspay) throws Exception{
		String send = HttpUtils.send(SaoConfig.PAY_JSPAY, jspay);
		LOG.info(send);
		JsPayResp fromJson = new Gson().fromJson(send, JsPayResp.class);
		return fromJson;
	}
	
}
