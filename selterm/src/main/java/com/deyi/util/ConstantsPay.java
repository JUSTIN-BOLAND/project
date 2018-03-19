package com.deyi.util;

public class ConstantsPay {
	public final static String PAY_CODE="pay_code";
	public final static String PAY_MSG="pay_msg";
	public final static String PAY_MONEY="pay_money";
	
	public final static String USERPAYING="USERPAYING";

	public final static String NOTPAY="NOTPAY";
	public final static String SUCCESS="SUCCESS";

	public static final String NOTIFY_SUCCESS ="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	private static final String NOTIFY_FAIL ="<xml><return_code><![CDATA[Fail]]></return_code><return_msg><![CDATA[%s]]></return_msg></xml>";
	public static final String PAY_QRCODE_CONTENT = "qrcode_content";
	
	/**
	 * 取回掉的
	 * @param errorMsg
	 * @return
	 */
	public static String getFormatNotfyFail(String errorMsg){
		
		return String.format(NOTIFY_FAIL, errorMsg);
	}
}
