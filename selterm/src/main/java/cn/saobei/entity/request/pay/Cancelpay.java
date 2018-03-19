package cn.saobei.entity.request.pay;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.saobei.SaoConfig;
import cn.saobei.entity.utils.SaoSign;
import cn.saobei.entity.utils.Seq;
/**
 * 
 * pure
 * @author hejx
 * @2017年5月16日
 */
public class Cancelpay {
	@Seq(0)private String pay_ver;
	@Seq(1)private String pay_type;
	@Seq(2)private String service_id;
	@Seq(3)private String merchant_no;
	@Seq(4)private String terminal_id;
	@Seq(5)private String terminal_trace;
	@Seq(6)private String terminal_time;
	@Seq(7)private String out_trade_no;
	private String key_sign;
	
	
	/**
	 * 
	 * @param pay_type (必填)支付类型：“010”微信，“020”支付宝，“060”qq 钱包
	 * @param terminal_trace
	 * @param out_trade_no 
	 * @param data 终端交易时间 
	 * @throws Exception
	 */
	public Cancelpay(String pay_type,String terminal_trace, String out_trade_no,Date data) throws Exception {
		this.pay_ver = SaoConfig.getPay_ver();
		this.pay_type = pay_type;
		this.service_id = "040";
		this.merchant_no = SaoConfig.getMerchant_no();
		this.terminal_id = SaoConfig.getTerminal_id();
		this.terminal_trace =terminal_trace;
		this.terminal_time = new SimpleDateFormat("yyyyMMddHHmmss").format(data);
		this.out_trade_no =out_trade_no;
		this.key_sign = SaoSign.DataPaySign(this);
	}
	
	
	public String getPay_ver() {
		return pay_ver;
	}
	public void setPay_ver(String pay_ver) {
		this.pay_ver = pay_ver;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
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
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getKey_sign() {
		return key_sign;
	}
	public void setKey_sign(String key_sign) {
		this.key_sign = key_sign;
	}

}
