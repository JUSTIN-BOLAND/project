/**
 * 
 */
package cn.saobei.entity.response.pay;

import cn.saobei.entity.utils.Seq;

/**
 * pure
 * @author hejx
 * @2017年5月16日
 */
public class CancelResp {

	
	@Seq(0)private String return_code;
	@Seq(1)private String return_msg;
	@Seq(2)private String result_code;
	@Seq(3)private String pay_type;
	
	@Seq(4)private String merchant_no;
	@Seq(5)private String terminal_id;
	@Seq(6)private String terminal_trace;
	@Seq(7)private String terminal_time;
	@Seq(8)private String recall;
	private String key_sign;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getMerchant_no() {
		return merchant_no;
	}
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	public String getTerminal_trace() {
		return terminal_trace;
	}
	public void setTerminal_trace(String terminal_trace) {
		this.terminal_trace = terminal_trace;
	}
	public String getTerminal_time() {
		return terminal_time;
	}
	public void setTerminal_time(String terminal_time) {
		this.terminal_time = terminal_time;
	}
	public String getRecall() {
		return recall;
	}
	public void setRecall(String recall) {
		this.recall = recall;
	}
	public String getKey_sign() {
		return key_sign;
	}
	public void setKey_sign(String key_sign) {
		this.key_sign = key_sign;
	}
	
	
	
}
