package cn.saobei.entity.request.manager;

import cn.saobei.entity.utils.Required;

/**
 * <pre>
 * inst_no 机构编号，扫呗分配 是 String 15
 trace_no 请求流水号，uuid 是 String 32
 merchant_type 创建商户类型，1 普通商户，2 二级商户 是 String 15
 merchant_name 商户名称，扫呗系统全局唯一不可重复 是 String 80
 merchant_alias 商户简称 是 String 20
 merchant_company 经营实体名称，须与营业执 照名称保持一致是 String 80
 merchant_province 所在省 是 String 10
 merchant_province_code 省编码 是 String 3
</pre>

 * @author Enzo
 * @date 2016年11月24日
 */
public class MerchantUpdate {
	@Required()private String inst_no;
	@Required()private String trace_no;
	@Required()private String merchant_no;
//	@Required()private String merchant_type;
//	@Required()private String merchant_alias;
	@Required()private String merchant_name;
	@Required()private String merchant_province;
	@Required()private String merchant_province_code;
	@Required()private String merchant_city;
	@Required()private String merchant_city_code;
	@Required()private String merchant_address;
	@Required()private String merchant_person;
	@Required()private String merchant_phone;
	@Required()private String merchant_email;
	@Required()private String merchant_id_no;
	@Required()private String merchant_id_expire;
	@Required()private String business_name;
	@Required()private String business_code;
	@Required()private String account_type;
	@Required()private String account_name;
	@Required()private String account_no;
	@Required()private String bank_name;
	@Required()private String bank_no;
	@Required()private String settle_type;
	@Required()private int settle_amount;
	private String account_phone;
	private String img_license;
	private String img_idcard_a;
	private String img_idcard_b;
	private String img_bankcard_a;
	private String img_bankcard_b;
	private String img_logo;
	private String img_indoor;
	private String img_contract;
	private String img_other;
	private String notify_url;
//	private String parent_no;
	private String wx_appid;
	private String wx_appsecret;
//	private String salesman_id;
//	private String agent_id;
//	private String user_id;
	private String key_sign;
	
	
	
	/**
	 * @return the account_phone
	 */
	public String getAccount_phone() {
		return account_phone;
	}
	/**
	 * @param account_phone the account_phone to set
	 */
	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}
	/**
	 * @return the inst_no
	 */
	public String getInst_no() {
		return inst_no;
	}
	/**
	 * @param inst_no the inst_no to set
	 */
	public void setInst_no(String inst_no) {
		this.inst_no = inst_no;
	}
	/**
	 * @return the trace_no
	 */
	public String getTrace_no() {
		return trace_no;
	}
	/**
	 * @param trace_no the trace_no to set
	 */
	public void setTrace_no(String trace_no) {
		this.trace_no = trace_no;
	}
	/**
	 * @return the merchant_no
	 */
	public String getMerchant_no() {
		return merchant_no;
	}
	/**
	 * @param merchant_no the merchant_no to set
	 */
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	/**
	 * @return the merchant_name
	 */
	public String getMerchant_name() {
		return merchant_name;
	}
	/**
	 * @param merchant_name the merchant_name to set
	 */
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	/**
	 * @return the merchant_province
	 */
	public String getMerchant_province() {
		return merchant_province;
	}
	/**
	 * @param merchant_province the merchant_province to set
	 */
	public void setMerchant_province(String merchant_province) {
		this.merchant_province = merchant_province;
	}
	/**
	 * @return the merchant_province_code
	 */
	public String getMerchant_province_code() {
		return merchant_province_code;
	}
	/**
	 * @param merchant_province_code the merchant_province_code to set
	 */
	public void setMerchant_province_code(String merchant_province_code) {
		this.merchant_province_code = merchant_province_code;
	}
	/**
	 * @return the merchant_city
	 */
	public String getMerchant_city() {
		return merchant_city;
	}
	/**
	 * @param merchant_city the merchant_city to set
	 */
	public void setMerchant_city(String merchant_city) {
		this.merchant_city = merchant_city;
	}
	/**
	 * @return the merchant_city_code
	 */
	public String getMerchant_city_code() {
		return merchant_city_code;
	}
	/**
	 * @param merchant_city_code the merchant_city_code to set
	 */
	public void setMerchant_city_code(String merchant_city_code) {
		this.merchant_city_code = merchant_city_code;
	}
	/**
	 * @return the merchant_address
	 */
	public String getMerchant_address() {
		return merchant_address;
	}
	/**
	 * @param merchant_address the merchant_address to set
	 */
	public void setMerchant_address(String merchant_address) {
		this.merchant_address = merchant_address;
	}
	/**
	 * @return the merchant_person
	 */
	public String getMerchant_person() {
		return merchant_person;
	}
	/**
	 * @param merchant_person the merchant_person to set
	 */
	public void setMerchant_person(String merchant_person) {
		this.merchant_person = merchant_person;
	}
	/**
	 * @return the merchant_phone
	 */
	public String getMerchant_phone() {
		return merchant_phone;
	}
	/**
	 * @param merchant_phone the merchant_phone to set
	 */
	public void setMerchant_phone(String merchant_phone) {
		this.merchant_phone = merchant_phone;
	}
	/**
	 * @return the merchant_email
	 */
	public String getMerchant_email() {
		return merchant_email;
	}
	/**
	 * @param merchant_email the merchant_email to set
	 */
	public void setMerchant_email(String merchant_email) {
		this.merchant_email = merchant_email;
	}
	/**
	 * @return the merchant_id_no
	 */
	public String getMerchant_id_no() {
		return merchant_id_no;
	}
	/**
	 * @param merchant_id_no the merchant_id_no to set
	 */
	public void setMerchant_id_no(String merchant_id_no) {
		this.merchant_id_no = merchant_id_no;
	}
	/**
	 * @return the merchant_id_expire
	 */
	public String getMerchant_id_expire() {
		return merchant_id_expire;
	}
	/**
	 * @param merchant_id_expire the merchant_id_expire to set
	 */
	public void setMerchant_id_expire(String merchant_id_expire) {
		this.merchant_id_expire = merchant_id_expire;
	}
	/**
	 * @return the business_name
	 */
	public String getBusiness_name() {
		return business_name;
	}
	/**
	 * @param business_name the business_name to set
	 */
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	/**
	 * @return the business_code
	 */
	public String getBusiness_code() {
		return business_code;
	}
	/**
	 * @param business_code the business_code to set
	 */
	public void setBusiness_code(String business_code) {
		this.business_code = business_code;
	}
	/**
	 * @return the account_type
	 */
	public String getAccount_type() {
		return account_type;
	}
	/**
	 * @param account_type the account_type to set
	 */
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	/**
	 * @return the account_name
	 */
	public String getAccount_name() {
		return account_name;
	}
	/**
	 * @param account_name the account_name to set
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	/**
	 * @return the account_no
	 */
	public String getAccount_no() {
		return account_no;
	}
	/**
	 * @param account_no the account_no to set
	 */
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	/**
	 * @return the bank_name
	 */
	public String getBank_name() {
		return bank_name;
	}
	/**
	 * @param bank_name the bank_name to set
	 */
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	/**
	 * @return the bank_no
	 */
	public String getBank_no() {
		return bank_no;
	}
	/**
	 * @param bank_no the bank_no to set
	 */
	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}
	/**
	 * @return the settle_type
	 */
	public String getSettle_type() {
		return settle_type;
	}
	/**
	 * @param settle_type the settle_type to set
	 */
	public void setSettle_type(String settle_type) {
		this.settle_type = settle_type;
	}
	/**
	 * @return the settle_amount
	 */
	public int getSettle_amount() {
		return settle_amount;
	}
	/**
	 * @param settle_amount the settle_amount to set
	 */
	public void setSettle_amount(int settle_amount) {
		this.settle_amount = settle_amount;
	}
	/**
	 * @return the img_license
	 */
	public String getImg_license() {
		return img_license;
	}
	/**
	 * @param img_license the img_license to set
	 */
	public void setImg_license(String img_license) {
		this.img_license = img_license;
	}
	/**
	 * @return the img_idcard_a
	 */
	public String getImg_idcard_a() {
		return img_idcard_a;
	}
	/**
	 * @param img_idcard_a the img_idcard_a to set
	 */
	public void setImg_idcard_a(String img_idcard_a) {
		this.img_idcard_a = img_idcard_a;
	}
	/**
	 * @return the img_idcard_b
	 */
	public String getImg_idcard_b() {
		return img_idcard_b;
	}
	/**
	 * @param img_idcard_b the img_idcard_b to set
	 */
	public void setImg_idcard_b(String img_idcard_b) {
		this.img_idcard_b = img_idcard_b;
	}
	/**
	 * @return the img_bankcard_a
	 */
	public String getImg_bankcard_a() {
		return img_bankcard_a;
	}
	/**
	 * @param img_bankcard_a the img_bankcard_a to set
	 */
	public void setImg_bankcard_a(String img_bankcard_a) {
		this.img_bankcard_a = img_bankcard_a;
	}
	/**
	 * @return the img_bankcard_b
	 */
	public String getImg_bankcard_b() {
		return img_bankcard_b;
	}
	/**
	 * @param img_bankcard_b the img_bankcard_b to set
	 */
	public void setImg_bankcard_b(String img_bankcard_b) {
		this.img_bankcard_b = img_bankcard_b;
	}
	/**
	 * @return the img_logo
	 */
	public String getImg_logo() {
		return img_logo;
	}
	/**
	 * @param img_logo the img_logo to set
	 */
	public void setImg_logo(String img_logo) {
		this.img_logo = img_logo;
	}
	/**
	 * @return the img_indoor
	 */
	public String getImg_indoor() {
		return img_indoor;
	}
	/**
	 * @param img_indoor the img_indoor to set
	 */
	public void setImg_indoor(String img_indoor) {
		this.img_indoor = img_indoor;
	}
	/**
	 * @return the img_contract
	 */
	public String getImg_contract() {
		return img_contract;
	}
	/**
	 * @param img_contract the img_contract to set
	 */
	public void setImg_contract(String img_contract) {
		this.img_contract = img_contract;
	}
	/**
	 * @return the img_other
	 */
	public String getImg_other() {
		return img_other;
	}
	/**
	 * @param img_other the img_other to set
	 */
	public void setImg_other(String img_other) {
		this.img_other = img_other;
	}
	/**
	 * @return the notify_url
	 */
	public String getNotify_url() {
		return notify_url;
	}
	/**
	 * @param notify_url the notify_url to set
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	/**
	 * @return the wx_appid
	 */
	public String getWx_appid() {
		return wx_appid;
	}
	/**
	 * @param wx_appid the wx_appid to set
	 */
	public void setWx_appid(String wx_appid) {
		this.wx_appid = wx_appid;
	}
	/**
	 * @return the wx_appsecret
	 */
	public String getWx_appsecret() {
		return wx_appsecret;
	}
	/**
	 * @param wx_appsecret the wx_appsecret to set
	 */
	public void setWx_appsecret(String wx_appsecret) {
		this.wx_appsecret = wx_appsecret;
	}
	/**
	 * @return the key_sign
	 */
	public String getKey_sign() {
		return key_sign;
	}
	/**
	 * @param key_sign the key_sign to set
	 */
	public void setKey_sign(String key_sign) {
		this.key_sign = key_sign;
	}
	
}
