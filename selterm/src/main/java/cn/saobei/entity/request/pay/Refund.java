package cn.saobei.entity.request.pay;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.saobei.SaoConfig;
import cn.saobei.entity.utils.SaoSign;
import cn.saobei.entity.utils.Seq;

public class Refund {

	@Seq(0)private String pay_ver;
	@Seq(1)private String pay_type;
	@Seq(2)private String service_id;
	@Seq(3)private String merchant_no;
	@Seq(4)private String terminal_id;
	@Seq(5)private String terminal_trace;
	@Seq(6)private String terminal_time;
	@Seq(7)private String refund_fee;
	@Seq(8)private String out_trade_no;
	private String operator_id;
	private String key_sign;
	
	
	
	
	/**
	 * 
	 * @param pay_type ()请求类型，010 微信，020 支付宝， “060”qq 钱包
	 * @param refund_fee 退款金额，单位分
	 * @param out_trade_no 订单号，查询凭据，利楚订单号、微信 订单号、支付宝订单号任意一个
	 * @param operator_id 
	 * @throws Exception
	 */
	public Refund( String pay_type, String refund_fee, String out_trade_no, String operator_id) throws Exception {
		super();
		this.pay_ver = SaoConfig.getPay_ver();
		this.pay_type = pay_type;
		this.service_id = "030";
		this.merchant_no = SaoConfig.getMerchant_no();
		this.terminal_id = SaoConfig.getTerminal_id();
		this.terminal_trace = SaoSign.getRandomString(6);
		this.terminal_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		this.refund_fee = refund_fee;
		this.out_trade_no = out_trade_no;
		this.operator_id = operator_id;
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
	 * @return the refund_fee
	 */
	public String getRefund_fee() {
		return refund_fee;
	}
	/**
	 * @param refund_fee the refund_fee to set
	 */
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	/**
	 * @return the out_trade_no
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	/**
	 * @param out_trade_no the out_trade_no to set
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
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
