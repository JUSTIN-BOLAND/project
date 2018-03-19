package cn.saobei.entity.request.manager;

import cn.saobei.entity.utils.Required;

public class CheckName {

	@Required()private String inst_no;
	@Required()private String trace_no;
	@Required()private String merchant_name;
	private String key_sign;
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
