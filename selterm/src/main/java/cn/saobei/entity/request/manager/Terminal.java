package cn.saobei.entity.request.manager;

import java.util.UUID;

import cn.saobei.SaoConfig;
import cn.saobei.entity.utils.Required;
import cn.saobei.entity.utils.SaoSign;

public class Terminal {

	@Required()private String inst_no;
	@Required()private String trace_no;
	@Required()private String merchant_no;
	private String key_sign;
	
	
	
	public Terminal(String merchant_no) throws Exception {
		super();
		this.inst_no = SaoConfig.getInst_no();
		this.trace_no = UUID.randomUUID().toString().replace("-", "");
		this.merchant_no = merchant_no;
		this.key_sign = SaoSign.DataSign(this);
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
