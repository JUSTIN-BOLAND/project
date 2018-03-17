package com.lb.server.util;

import java.math.BigInteger;

public class MathUtil {
	public static String hexXor(String hexStr){
		String[] hexs = hexStr.split("\\s+");
		String result = hexStrXor(hexs[0],hexs[1]);
		for(int i=2; i< hexs.length; i++){
			result = hexStrXor(result,hexs[i]);
			//result = xor(result,hexs[i]);
		}
		if(result.length() <2) result = "0"+result;
		return result;

	}
	/*public static String xor(String strHex_X,String strHex_Y){   
        //将x、y转成二进制形式
        String anotherBinary=Integer.toBinaryString(Integer.valueOf(strHex_X,16));
        String thisBinary=Integer.toBinaryString(Integer.valueOf(strHex_Y,16));
        String result = "";
        //判断是否为8位二进制，否则左补零
        if(anotherBinary.length() != 8){
        for (int i = anotherBinary.length(); i <8; i++) {
                anotherBinary = "0"+anotherBinary;
            }
        }
        if(thisBinary.length() != 8){
        for (int i = thisBinary.length(); i <8; i++) {
                thisBinary = "0"+thisBinary;
            }
        }
        //异或运算
        for(int i=0;i<anotherBinary.length();i++){
        //如果相同位置数相同，则补0，否则补1
                if(thisBinary.charAt(i)==anotherBinary.charAt(i))
                    result+="0";
                else{
                    result+="1";
                }
            }
        result = Integer.toHexString(Integer.parseInt(result, 2));
        System.out.println(strHex_X+" ^ "+strHex_Y+" = " +result);
        return result;
    }  */
	public static String hexStrXor(String x,String y){
		BigInteger big1= new BigInteger(x, 16);

		BigInteger big2= new BigInteger(y, 16);
		//System.out.println(x+" ^ "+y+" = " + big1.xor(big2).toString(16));
		return	big1.xor(big2).toString(16);



	}
	public static String str2HexStr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			// sb.append(' ');
		}
		return sb.toString().trim();
	}
	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			//System.out.println(i+" : src="+src[i]+" : v="+v+":hv="+hv);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			if(i< src.length -1 ) hv += " ";
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	/**
	 * Convert hex string to byte[]
	 * @param hexString the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	public static String decimalToHex(int decimal,int strLen){
		String hex = Integer.toHexString(decimal);
        if(strLen<2) strLen = 2;
		int len = strLen - hex.length();
		//len = 0;
		//System.out.println(hex.length());
		for(int i=0;i< len ; i++ ) {
			hex = "0"+hex;
			//System.out.println(i+" -> "+hex);
		}
		//System.out.println(hex);
		return (strLen==2?hex:hex.substring(0,2) + " " + hex.substring(2,4));
		//else return hex;
	}
	public static String hexToDecimal(String hex){
		return Integer.valueOf(hex,16).toString();
	}
	public static String showBytes(String st){

		byte[] bytes = st.getBytes();
		for(byte b:bytes) System.out.println(b);
		return null;
	}
	public static void printHexString( byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() );
		}

	}
	public static void printByte( byte[] b) {
		for (int i = 0; i < b.length; i++) {

			System.out.println("["+i+"] -> "+b[i] );
		}

	}
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
				String s = encode;
				return s;      //是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";        //如果都不是，说明输入的内容不属于常见的编码格式。
	}

	public static  byte[] convert(String cmd){
		//return MathUtil.hexStringToBytes(cmd.replaceAll("\\s+", ""));
		String[] cmds = cmd.split("\\s+");
		byte[] ret = new byte[cmd.length()];
		for(int i=0; i< cmds.length; i++){
			ret[i] = (byte)Integer.parseInt(cmds[i],16);
		}
		return ret;
	}
	public static void printAsii(String cmd){
		String st= cmd.replaceAll("\\s+","");
		for(char ch: st.toCharArray()) System.out.println(ch+"->"+(byte)ch);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String str="01 00 04 27 10";

		//System.out.println(MathUtil.hexXor(str) + " -> "+MathUtil.decimalToHex(500)+"->"+MathUtil.hexToDecimal("2710"));
		System.out.println(MathUtil.decimalToHex(10,2) + " -> "+MathUtil.decimalToHex(1005,4)+"->"+MathUtil.hexToDecimal("001f"));
	}

}
