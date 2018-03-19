package cn.saobei;

import java.util.Date;

import cn.saobei.entity.request.pay.Cancelpay;
import cn.saobei.entity.response.pay.CancelResp;

public class TestPay {

//	private static final String inst_no = "52100002";
	private static final String merchant_no =  "889611002710001";//"889584002080004";
	private static final String terminal_id = "10060954";//"10025214";
//	
//	public static final String access_token = "2ae1403b450e431ebe7c20fe1d847d2f";//eeed46eb6b074d4489f1b05272e48550";
//	
//	
//	private static final String appid = "wx87368d23f79ecced";
//	
//	private static final String mchid = "1387169302";
//	
//	private static final String appkey  = "DiYethirdFRP5deyiconfidkeys20160";
//	
//	
//	//TerminalResp [return_code=01, return_msg=创建成功, trace_no=b3a958dd13ab459a9be0b77e48e177d6, 
//	//result_code=01, merchant_no=889611002710001, terminal_id=10060906, 
//	//access_token=c294925274544f1a973425b4e1a9281e, key_sign=1d1d605a4884bbb404e8fd400ae44115]
//	@Test
//	public void testPaySign() throws Exception{
//		long currentTimeMillis = System.currentTimeMillis();
//		PaySdk.initsdk(merchant_no,terminal_id,access_token);
//		PaySign paySign = new PaySign(merchant_no,terminal_id);
//		String send = HttpUtils.send(SaoConfig.PAY_PAY_SIGN, paySign);
//		System.out.println(send);
//		System.out.println(String.format("请求时间:%d" , System.currentTimeMillis()  -currentTimeMillis ));
//	}
//	@Test
//	public void testmicpay() throws Exception{
//		long currentTimeMillis = System.currentTimeMillis();
//		PaySdk.initsdk(merchant_no,terminal_id,access_token);
//		Micropay micropay = new Micropay("010", "130042793302926855", SaoSign.getRandomString(11),"1", "方便面");
//		String send = HttpUtils.send(SaoConfig.PAY_BARCODEPAY, micropay);
//		MicroPayResp fromJson = new Gson().fromJson(send, MicroPayResp.class);
//		System.out.println(fromJson.toString());
//		System.out.println(String.format("请求时间:%d" , System.currentTimeMillis()  -currentTimeMillis ));
//	}
//	//MicroPayResp [return_code=01, return_msg=, result_code=02, pay_type=010, merchant_name=广西兴佳测试商户, merchant_no=889584002080004, terminal_id=10025214, 
//	//terminal_trace=UL0ATT, terminal_time=20161129093634, total_fee=1, end_time=20161129093707, out_trade_no=100252140021116112909365100001, key_sign=e1dd7e3372a58c5eb03fe5c8e76e949b]
//	
//	@Test
//	public void testquery() throws Exception{
//		long currentTimeMillis = System.currentTimeMillis();
//		PaySdk.initsdk("870300158000001","10070445","531a6a2bf7ad4a959c94568ea8a106c2");
//		PayQuery payquery = new PayQuery("020","20161226133535407883", "",new SimpleDateFormat("yyyyMMddHHmmss").parse("20161226133535"));
//		
////		Micropay micropay = new Micropay("010", terminal_id, "130195281061564563", "1", "方便面");
//		String send = HttpUtils.send(SaoConfig.PAY_QUERY, payquery);
//		PayQueryResp fromJson = new Gson().fromJson(send, PayQueryResp.class);
//		System.out.println(fromJson.toString());
//		System.out.println(String.format("请求时间:%d" , System.currentTimeMillis()  -currentTimeMillis ));
//	}
//	@Test
//	public void testprepay() throws Exception{
//		long currentTimeMillis = System.currentTimeMillis();
//		PaySdk.initsdk(merchant_no,terminal_id,access_token);
//		PrePay payquery = new PrePay("010", SaoSign.getRandomString(6) ,null, "1", null, null, null);
//		
////		Micropay micropay = new Micropay("010", terminal_id, "130195281061564563", "1", "方便面");
//		String send = HttpUtils.send(SaoConfig.PAY_PREPAY, payquery);
//		PrePayResp fromJson = new Gson().fromJson(send, PrePayResp.class);
//		System.out.println(fromJson.toString());
//		System.out.println(String.format("请求时间:%d" , System.currentTimeMillis()  -currentTimeMillis ));
//	}
//	
//	
//	@Test
//	public void testRefund() throws Exception{
//		long currentTimeMillis = System.currentTimeMillis();
////		PaySdk.initsdk(merchant_no,terminal_id,access_token);
//		PaySdk.initsdk("856900116000001","10062638","767be46876b348fcab476320abbf8628");
////		String[] aa =  "100626380622216121215354301242";
////		for (String string : aa) {
//			Refund payquery = new Refund("020", "990" ,"100626380622216121215354301242", "1");
//			
////		Micropay micropay = new Micropay("010", terminal_id, "130195281061564563", "1", "方便面");
//			String send = HttpUtils.send(SaoConfig.PAY_REFUND, payquery);
//			System.out.println(send);
//			RefundResp fromJson = new Gson().fromJson(send, RefundResp.class);
//			System.out.println(fromJson.toString());
//			
////		}
//		System.out.println(String.format("请求时间:%d" , System.currentTimeMillis()  -currentTimeMillis ));
//	}
//	
//	
//	@Test
//	public void testjson() throws Exception{
//		String json = "{\"return_code\":\"02\",\"return_msg\":\"请求签名验证失败！\",\"result_code\":\"1111111\",\"pay_type\":null,\"merchant_name\":null,\"merchant_no\":null,\"terminal_id\":null,\"terminal_trace\":null,\"terminal_time\":null,\"total_fee\":null,\"end_time\":null,\"out_trade_no\":null,\"key_sign\":null}";
//		MicroPayResp fromJson = new Gson().fromJson(json, MicroPayResp.class);
//		System.out.println(fromJson);
//	}
	
		
}
