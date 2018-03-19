package cn.saobei.entity.response.manager;

public class CheckNameResp {

	private String inst_no;
	private String trace_no;
	private String merchant_name;
	private String key_sign;
	private String return_code;
	private String return_msg;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CheckNameResp [inst_no=" + inst_no + ", trace_no=" + trace_no + ", merchant_name=" + merchant_name + ", key_sign=" + key_sign + ", return_code=" + return_code + ", return_msg=" + return_msg + "]";
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
	
	
	
	
}
