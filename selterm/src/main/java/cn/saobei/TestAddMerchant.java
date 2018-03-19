package cn.saobei;

/*

public class TestAddMerchant {
	
	private static final String inst_no = SaoConfig.getInst_no();
//	private static final String inst_no = "55100001";
	
	private static final String appid = "wx87368d23f79ecced";
	
	private static final String mchid = "1387169302";
	
	private static final String appkey  = "DiYethirdFRP5deyiconfidkeys20160";
	
	private static final String AppSecret  = "3894ef221f3dd3b0ff948aeffa508ceb";

	@Test
	public  void addmerchant() throws Exception {
		
		long currentTimeMillis = System.currentTimeMillis();
		SaoMerchant merchant = new SaoMerchant();
		merchant.setInst_no(inst_no);
		merchant.setTrace_no(UUID.randomUUID().toString().replace("-", ""));
		merchant.setMerchant_type("1");
		merchant.setMerchant_name("广西兴佳测试商户");
		merchant.setMerchant_alias("广西兴佳");
		merchant.setMerchant_company("得壹科技");
		merchant.setMerchant_province("广东省");
		merchant.setMerchant_province_code("440");
		merchant.setMerchant_city("深圳市");
		merchant.setMerchant_city_code("5840");
		merchant.setMerchant_address("测试商户地址");
		merchant.setMerchant_person("Enzo");
		merchant.setMerchant_phone("13530618523");
		merchant.setMerchant_email("xiongqq@5deyi.com");
		merchant.setMerchant_id_no("360427199104082712");
		merchant.setMerchant_id_expire("29991231");
		merchant.setBusiness_name("其他综合零售");;
		merchant.setBusiness_code("208");
		merchant.setAccount_name("熊");
		merchant.setAccount_no("23311233131313");
		merchant.setAccount_name("allla");
		merchant.setAccount_type("1");
		merchant.setBank_no("103100000026");
		merchant.setBank_name("中国银行");
		merchant.setSettle_type("1");
		merchant.setSettle_amount(1);
		
		merchant.setWx_appid("wx024377d8e56715e8");
		merchant.setWx_appsecret("ef18fe790bfb995fe35337a6c695cbd7 ");
		
		merchant.setKey_sign(SaoSign.DataSign(merchant));
//		test.lcsw.cn:8010
//		test.lcsw.cn:8021
		
		
		
		
		String sendpost = HttpUtils.send(SaoConfig.MERCHANT_ADD, merchant);
		MerchanResp  resp  = new Gson().fromJson(sendpost, MerchanResp.class);
		System.out.println(resp.toString());
		System.out.println(System.currentTimeMillis()  - currentTimeMillis);
		
	}
	
	@Test
	public void testsign() throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
		
		//[return_code=01, return_msg=创建成功,请等待审核通过, trace_no=593df7c8edf6432fbf2ed22a9aff00f5, merchant_no=837200322000001, key_sign=d64eaf96a486adeb8625030ce5fc4225, result_code=01]
		MerchanResp resp = new MerchanResp();
		resp.setReturn_code("01");
		resp.setReturn_msg("创建成功,请等待审核通过");
		resp.setTrace_no("593df7c8edf6432fbf2ed22a9aff00f5");
		resp.setMerchant_no("837200322000001");
		resp.setResult_code("01");
		String dataSign = SaoSign.DataSign(resp);
		System.out.println(dataSign);
	}
	
	//account_name=熊&account_no=23311233131313&account_type=1&bank_name=中国银行&bank_no=103100000026&business_code=208&business_name=其他综合零售&inst_no=52100002&merchant_address=测试商户地址&merchant_city=深圳市&merchant_city_code=5840&merchant_email=aa1@5deyi.com&merchant_id_expire=29991231&merchant_id_no=360427199104082712&merchant_name=测试商户222&merchant_person=Enzo&merchant_phone=13714549398&merchant_province=广东省&merchant_province_code=440&settle_amount=1&settle_type=1&trace_no=5518a3436441439da3ee1c22e859cff8&key=c9da36bd6c0d426c8a97e2e8a8a2dbc9
	//account_name=熊&account_no=23311233131313&account_type=1&bank_name=中国银行&bank_no=103100000026&business_code=208&business_name=其他综合零售&inst_no=52100002&merchant_address=测试商户地址&merchant_city=深圳市&merchant_city_code=5840&merchant_email=aa1@5deyi.com&merchant_id_expire=29991231&merchant_id_no=360427199104082712&merchant_name=测试商户222&merchant_person=Enzo&merchant_phone=13714549398&merchant_province=广东省&merchant_province_code=440&settle_amount=1&settle_type=1&trace_no=395b3c0368a24b2abf20f2e422d6718a&key=c9da36bd6c0d426c8a97e2e8a8a2dbc9
	
	@Test
	public void updatemerchant() throws Exception{
		long currentTimeMillis = System.currentTimeMillis();
		MerchantUpdate merchant = new MerchantUpdate();
		merchant.setInst_no(inst_no);
		merchant.setTrace_no(UUID.randomUUID().toString().replace("-", ""));
		merchant.setMerchant_name("贵州仁怀宇琳网络");
		merchant.setMerchant_no("870300158000001");
		merchant.setMerchant_province("贵州省");
		merchant.setMerchant_province_code("520");
		merchant.setMerchant_city("遵义市");
		merchant.setMerchant_city_code("7030");
		merchant.setMerchant_address("贵州省遵义市仁怀市鲁班镇隆堡村");
		merchant.setMerchant_person("王世斌");
		merchant.setMerchant_phone("13314435444");
		merchant.setMerchant_email("wb0852@126.com");
		merchant.setMerchant_id_no("522130198512221610");
		merchant.setMerchant_id_expire("29991231");
		merchant.setBusiness_name("其他行业");;
		merchant.setBusiness_code("158");
		merchant.setAccount_name("王世斌");
		merchant.setAccount_no("6217007110010353047");
		merchant.setAccount_type("1");
		merchant.setBank_no("105704282493");
		merchant.setAccount_phone("13314435444");
		merchant.setBank_name("中国建设银行股份有限公司仁怀国酒大道支行");
		merchant.setSettle_type("1");
		merchant.setSettle_amount(1);
		merchant.setWx_appid(appid);
		merchant.setWx_appsecret("ed8e5f00b62419289d996e0621e867eb");
		
		merchant.setKey_sign(SaoSign.DataSign(merchant));
//		test.lcsw.cn:8010
//		test.lcsw.cn:8021
		
		
		String sendpost = HttpUtils.send(SaoConfig.MERCHANT_UPDATE, merchant);
		System.out.println(sendpost);
		System.out.println(System.currentTimeMillis()  - currentTimeMillis);
	}
	
	@Test
	public void testquery() throws Exception{
		MerchantQuery merchantQuery = new MerchantQuery();
		merchantQuery.setInst_no(inst_no);
		merchantQuery.setTrace_no(UUID.randomUUID().toString().replace("-", ""));
		merchantQuery.setMerchant_no("861100153000005");
		merchantQuery.setKey_sign(SaoSign.DataSign(merchantQuery));
		String sendpost = HttpUtils.send(SaoConfig.MERCHANT_QUERY, merchantQuery);
		PayQuery fromJson = new Gson().fromJson(sendpost, PayQuery.class);
		System.out.println(fromJson.toString());
		System.out.println(sendpost);
	}
	
	@Test
	public void testcheckname() throws Exception{
		long currentTimeMillis = System.currentTimeMillis();
		CheckName checkName = new CheckName();
		checkName.setInst_no(inst_no);
		checkName.setTrace_no(UUID.randomUUID().toString().replace("-", ""));
		checkName.setMerchant_name("11");
		checkName.setKey_sign(SaoSign.DataSign(checkName));
		String send = HttpUtils.send(SaoConfig.MERCHANT_CHECKNAME, checkName);
		CheckNameResp  resp  = new Gson().fromJson(send, CheckNameResp.class);
		System.out.println(resp.toString());
		System.out.println(System.currentTimeMillis()  -currentTimeMillis );
	}
	
	@Test
	public void testaddterminal() throws Exception{
		long currentTimeMillis = System.currentTimeMillis();
		Terminal terminal = new Terminal("889611002710001");
		
		String send = HttpUtils.send(SaoConfig.TERMINAL_ADD, terminal);
		TerminalResp fromJson = new Gson().fromJson(send, TerminalResp.class);
		String dataSign = SaoSign.DataSign(fromJson);
		System.out.println(dataSign);
		System.out.println(fromJson.toString());
		System.out.println(String.format("请求时间:%d" , System.currentTimeMillis()  -currentTimeMillis ));
	}
	
	
	*//**
	 * @deprecated Use {@link SaoSign#DataSign(Object)} instead
	 *//*
	public static String DataSign(Object o) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		return SaoSign.DataSign(o);
	}
}
*/