package cn.saobei;

public class SaoConfig {

//	private static final String SERVER_IP = "http://test.lcsw.cn:8010";
//	private static final String SERVER_IP = "http://27.19.11.237:8021";
//	private static final String SERVER_IP = "http://27.19.11.237:8027";
//	private static final String SERVER_IP = "http://27.19.11.237:8010";
//	private static final String SERVER_IP = "http://118.178.116.123:80"; //正式
	private static final String SERVER_IP = "http://pay.lcsw.cn"; //正式
	public static final String MERCHANT_ADD = SERVER_IP + "/lcsw/merchant/100/add";
	public static final String MERCHANT_UPDATE = SERVER_IP + "/lcsw/merchant/100/update";
	public static final String MERCHANT_QUERY = SERVER_IP + "/lcsw/merchant/100/query";
	public static final String MERCHANT_CHECKNAME = SERVER_IP + "/lcsw/merchant/100/checkname";
	public static final String MERCHANT_NOTIFY = SERVER_IP + "/lcsw/merchant/100/notify";
	public static final String TERMINAL_ADD = SERVER_IP + "/lcsw/terminal/100/add";
	public static final String PAY_BARCODEPAY = SERVER_IP + "/lcsw/pay/100/barcodepay";
	public static final String PAY_CANCEL = SERVER_IP + "/lcsw/pay/100/cancel";
	public static final String PAY_PAY_SIGN = SERVER_IP + "/lcsw/pay/100/sign";
	public static final String PAY_QUERY = SERVER_IP + "/lcsw/pay/100/query"; 
	public static final String PAY_PREPAY = SERVER_IP + "/lcsw/pay/100/prepay"; 
	public static final String PAY_REFUND = SERVER_IP + "/lcsw/pay/100/refund"; 
	public static final String PAY_JSPAY = SERVER_IP + "/lcsw/pay/100/jspay"; 
	
	
	private static String inst_no = "61100002";
	
	private static String trace_no;
	
	private static String merchant_no;
	
	private static String pay_ver;
	
	private static String access_token;
	
	private static String terminal_id;
	
	private static String key = "575da299baff42da969358d5ac28fd1a";
	
	
	
	
	/**
	 * @return the key
	 */
	public static String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public static void setKey(String key) {
		SaoConfig.key = key;
	}

	/**
	 * @return the terminal_id
	 */
	public static String getTerminal_id() {
		return terminal_id;
	}

	/**
	 * @param terminal_id the terminal_id to set
	 */
	public static void setTerminal_id(String terminal_id) {
		SaoConfig.terminal_id = terminal_id;
	}

	/**
	 * @return the access_token
	 */
	public static String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token the access_token to set
	 */
	public static void setAccess_token(String access_token) {
		SaoConfig.access_token = access_token;
	}

	/**
	 * @return the pay_ver
	 */
	public static String getPay_ver() {
		return pay_ver;
	}

	/**
	 * @param pay_ver the pay_ver to set
	 */
	public static void setPay_ver(String pay_ver) {
		SaoConfig.pay_ver = pay_ver;
	}

	/**
	 * @return the inst_no
	 */
	public static String getInst_no() {
		return inst_no;
	}

	/**
	 * @param inst_no the inst_no to set
	 */
	public static void setInst_no(String inst_no) {
		SaoConfig.inst_no = inst_no;
	}

	/**
	 * @return the trace_no
	 */
	public static String getTrace_no() {
		return trace_no;
	}

	/**
	 * @param trace_no the trace_no to set
	 */
	public static void setTrace_no(String trace_no) {
		SaoConfig.trace_no = trace_no;
	}

	/**
	 * @return the merchant_no
	 */
	public static String getMerchant_no() {
		return merchant_no;
	}

	/**
	 * @param merchant_no the merchant_no to set
	 */
	public static void setMerchant_no(String merchant_no) {
		SaoConfig.merchant_no = merchant_no;
	}

	
	
}
