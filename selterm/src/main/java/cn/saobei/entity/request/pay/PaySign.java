package cn.saobei.entity.request.pay;

import cn.saobei.SaoConfig;
import cn.saobei.entity.utils.SaoSign;
import cn.saobei.entity.utils.Seq;

public class PaySign {

	@Seq(0)private String pay_ver;
	@Seq(1)private String service_id;
	@Seq(2)private String merchant_no;
	@Seq(3)private String terminal_id;
	@Seq(4)private String terminal_trace;
	@Seq(5)private String terminal_time;
	private String key_sign;
	
	
	
	public PaySign( String merchant_no, String terminal_id) throws Exception {
		super();
		this.pay_ver = SaoConfig.getPay_ver();
		this.service_id = "090";
		this.merchant_no = merchant_no;
		this.terminal_id = terminal_id;
		this.terminal_trace = SaoSign.getRandomString(6);
		this.terminal_time = SaoSign.getDate();
		this.key_sign = SaoSign.DatasignSign(this);
	}
	/**
	 * @return the pay_ver
	 */
	public String getPay_ver() {
		return pay_ver;
	}
	/**
	 * @param pay_ver the pay_ver to set
	 */
	public void setPay_ver(String pay_ver) {
		this.pay_ver = pay_ver;
	}
	/**
	 * @return the service_id
	 */
	public String getService_id() {
		return service_id;
	}
	/**
	 * @param service_id the service_id to set
	 */
	public void setService_id(String service_id) {
		this.service_id = service_id;
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
	 * @return the terminal_id
	 */
	public String getTerminal_id() {
		return terminal_id;
	}
	/**
	 * @param terminal_id the terminal_id to set
	 */
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	/**
	 * @return the terminal_trace
	 */
	public String getTerminal_trace() {
		return terminal_trace;
	}
	/**
	 * @param terminal_trace the terminal_trace to set
	 */
	public void setTerminal_trace(String terminal_trace) {
		this.terminal_trace = terminal_trace;
	}
	/**
	 * @return the terminal_time
	 */
	public String getTerminal_time() {
		return terminal_time;
	}
	/**
	 * @param terminal_time the terminal_time to set
	 */
	public void setTerminal_time(String terminal_time) {
		this.terminal_time = terminal_time;
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
