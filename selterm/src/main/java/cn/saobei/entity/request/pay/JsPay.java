package cn.saobei.entity.request.pay;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.saobei.SaoConfig;
import cn.saobei.entity.utils.SaoSign;
import cn.saobei.entity.utils.Seq;

/**
 *  公众号支付
 * @author Enzo
 * @date 2016年12月6日
 */
public class JsPay {

	@Seq(0)private String pay_ver;
	@Seq(1)private String pay_type;
	@Seq(2)private String service_id;
	@Seq(3)private String merchant_no;
	@Seq(4)private String terminal_id;
	@Seq(5)private String terminal_trace;
	@Seq(6)private String terminal_time;
	private String operator_id;
	private String open_id;
	@Seq(7)private String total_fee;
	private String order_body;
	private String notify_url;
	private String attach;
	private String key_sign;
	
	
	
	/**
	 * 公众号支付
	 * @param pay_type (必填)支付类型：“010”微信，“020”支付宝，“060”qq 钱包
	 * @param terminal_trace  终端流水号： 我们自己的订单号
	 * @param operator_id	
	 * @param open_id		微信 openid，pay_type 为 010 时必传
	 * @param total_fee	支付金钱
	 * @param order_body
	 * @param notify_url
	 * @param attach
	 * @throws Exception
	 */
	public JsPay( String pay_type, String terminal_trace,  String operator_id, String open_id, String total_fee, String order_body, String notify_url, String attach,Date date ) throws Exception {
		this.pay_ver = SaoConfig.getPay_ver();
		this.pay_type = pay_type;
		this.service_id = "012";
		this.merchant_no = SaoConfig.getMerchant_no();
		this.terminal_id = SaoConfig.getTerminal_id();
		this.terminal_trace = terminal_trace;
		this.terminal_time = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		this.operator_id = operator_id;
		this.open_id = open_id;
		this.total_fee = total_fee;
		this.order_body = order_body;
		this.notify_url = notify_url;
		this.attach = attach;
		this.key_sign = SaoSign.DataPaySign(this);
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
	 * @return the pay_type
	 */
	public String getPay_type() {
		return pay_type;
	}
	/**
	 * @param pay_type the pay_type to set
	 */
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
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
	 * @return the operator_id
	 */
	public String getOperator_id() {
		return operator_id;
	}
	/**
	 * @param operator_id the operator_id to set
	 */
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	/**
	 * @return the open_id
	 */
	public String getOpen_id() {
		return open_id;
	}
	/**
	 * @param open_id the open_id to set
	 */
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	/**
	 * @return the total_fee
	 */
	public String getTotal_fee() {
		return total_fee;
	}
	/**
	 * @param total_fee the total_fee to set
	 */
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	/**
	 * @return the order_body
	 */
	public String getOrder_body() {
		return order_body;
	}
	/**
	 * @param order_body the order_body to set
	 */
	public void setOrder_body(String order_body) {
		this.order_body = order_body;
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
	 * @return the attach
	 */
	public String getAttach() {
		return attach;
	}
	/**
	 * @param attach the attach to set
	 */
	public void setAttach(String attach) {
		this.attach = attach;
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
