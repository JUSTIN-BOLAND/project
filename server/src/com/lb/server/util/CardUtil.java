package com.lb.server.util;

public class CardUtil {

	 public static String getDecialCardNo(String serail,String cardNo){
		 String hexCardNo = MathUtil.hexToDecimal(cardNo);
		 int len = hexCardNo.length();
		 for(int i=0; i< (5-len);i++){
			 hexCardNo = "0"+hexCardNo;
		 }
		 return serail+hexCardNo;
	 }
	public static String getDecialDeviceCode(String hexDeviceCode){
		String deviceCode = MathUtil.hexToDecimal(hexDeviceCode);
		int len = deviceCode.length();
		for(int i=0; i< (5-len);i++){
			deviceCode = "0"+deviceCode;
		}
		return deviceCode;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       System.out.println(CardUtil.getDecialCardNo("01","0004")+"->"+CardUtil.getDecialDeviceCode("001f"));
	}

}
