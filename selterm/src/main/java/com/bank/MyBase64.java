package com.bank;

import java.io.IOException;

import sun.misc.BASE64Encoder;

public class MyBase64 {
	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bt;
	}
	
	public static void main(String[] args) {
		System.out.println(encode(MD5Util.getMD5("345345345345345").getBytes()).length());
	}
}
