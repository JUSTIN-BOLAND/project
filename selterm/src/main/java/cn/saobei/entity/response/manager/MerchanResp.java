package cn.saobei.entity.response.manager;

import cn.saobei.entity.utils.Required;

public class MerchanResp {

	@Required()private String return_code;
	@Required()private String return_msg;
	@Required()private String trace_no;
	private String merchant_no;
	private String key_sign;
	private String result_code;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MerchanResp [return_code=" + return_code + ", return_msg=" + return_msg + ", trace_no=" + trace_no + ", merchant_no=" + merchant_no + ", key_sign=" + key_sign + ", result_code=" + result_code + "]";
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
