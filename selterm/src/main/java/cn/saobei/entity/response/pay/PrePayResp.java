package cn.saobei.entity.response.pay;

import cn.saobei.entity.utils.Seq;

public class PrePayResp {

	@Seq(0)private String return_code;
	@Seq(1)private String return_msg;
	@Seq(2)private String result_code;
	@Seq(3)private String pay_type;
	@Seq(4)private String merchant_name;
	@Seq(5)private String merchant_no;
	@Seq(6)private String terminal_id;
	@Seq(7)private String terminal_trace;
	@Seq(8)private String terminal_time;
	@Seq(9)private String total_fee;
	@Seq(10)private String qr_code;
	@Seq(11)private String out_trade_no;
	private String key_sign;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PrePayResp [return_code=" + return_code + ", return_msg=" + return_msg + ", result_code=" + result_code + ", pay_type=" + pay_type + ", merchant_name=" + merchant_name + ", merchant_no=" + merchant_no + ", terminal_id=" + terminal_id + ", terminal_trace=" + terminal_trace + ", terminal_time=" + terminal_time + ", total_fee=" + total_fee + ", qr_code=" + qr_code + ", out_trade_no=" + out_trade_no + ", key_sign=" + key_sign + "]";
	}
	/**
	 * @return the return_code
	 */
	public String getReturn_code() {
		return return_code;
	}
	/**
	 * @param return_code the return_code to set
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	/**
	 * @return the return_msg
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	/**
	 * @param return_msg the return_msg to set
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	/**
	 * @return the result_code
	 */
	public String getResult_code() {
		return result_code;
	}
	/**
	 * @param result_code the result_code to set
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
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
	 * @return the qr_code
	 */
	public String getQr_code() {
		return qr_code;
	}
	/**
	 * @param qr_code the qr_code to set
	 */
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
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
