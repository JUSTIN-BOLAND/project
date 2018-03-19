
package com.deyi.util;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * 描述：
 * 
 * @author tongbiao
 * @创建时间：Jan 21, 2010
 */
public class StringUtils {

	private static AtomicInteger num = new AtomicInteger(0);
	private final static int MAXNUM = 9999;

	/**
	 * 功能：不定长参数,其中一个参数为null或空则返回true,负责返回false
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String... str) {
		for (String s : str) {
			if (StringUtils.isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 功能：不定长参数,其中一个参数为null或空或为空格字符串则返回true,负责返回false
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isBlank(String... str) {
		for (String s : str) {
			if (StringUtils.isBlank(s))
				return true;
		}
		return false;
	}

	/**
	 * 功能：判断字符串是否是数值. 默认允许有正负号,默认允许有小数点
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		boolean sign = true;
		int point_bef = Integer.MAX_VALUE;// 小数点前有几位
		int point_aft = Integer.MAX_VALUE;// 小数点后有几位
		return isNumeric(str, sign, point_bef, point_aft);
	}

	/**
	 * 功能：判断字符串是否是数值
	 * 
	 * @param str
	 * @param sign
	 *          是否允许有正负号
	 * @param point
	 *          是否允许有小数点
	 * @return
	 */
	public static boolean isNumeric(String str, boolean sign, boolean point) {
		int point_bef = Integer.MAX_VALUE;// 小数点前有几位
		int point_aft = Integer.MAX_VALUE;// 小数点后有几位
		if (!point)
			point_aft = 0;

		return isNumeric(str, sign, point_bef, point_aft);
	}

	public static boolean isquert(String str,String pic){
		String quert[] = str.split(",");
		for(int i=0;i<quert.length;i++){
			if(pic.equals(quert[i])){
				return true;
			}
		}
		return false;
	}
	/**
	 * 功能：判断字符串是否是数值
	 * 
	 * @param str
	 * @param sign
	 *          是否允许有正负号
	 * @param point_bef
	 *          精度,小数点前有几位
	 * @param point_aft
	 *          精度,小数点后有几位,如果为0,则为整数
	 * 
	 * @return
	 */
	public static boolean isNumeric(String str, boolean sign, int point_bef, int point_aft) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		boolean point = true;// 是否允许小数点
		if (point_aft == 0) {
			point = false;// 不允许有小数点
		} else {
			point = true;
		}
		StringBuffer pat = new StringBuffer();
		if (sign) {
			pat.append("[+|-]?");
		}
		if (point_bef == 0) {
			pat.append("[0]");
		} else {
			pat.append("[0-9]{1,");
			pat.append(point_bef);
			pat.append("}");
		}
		if (point && str.indexOf(".") != -1) {// 允许小数点,并且有小数点
			pat.append("[.]");
			pat.append("[0-9]{1,");// 小数点后必须有一位
			pat.append(point_aft);
			pat.append("}");
		}
		Pattern pattern = Pattern.compile(pat.toString());
		if (!pattern.matcher(str).matches()) {
			return false;
		} else {// 排除如00.1,返回false
			if (str.indexOf(".") != -1 && str.substring(0, str.indexOf(".")).length() > 1
					&& Integer.valueOf(str.substring(0, str.indexOf("."))) == 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 功能：查看字符串是否有这个子字符串
	 * 
	 * @param str
	 *          主字符串
	 * @param substr
	 *          字字符串
	 * @return
	 */
	public static boolean hasSubstring(String str, String substr) {
		if (str == null || substr == null)
			return false;
		int strLen = str.length();
		int substrLen = substr.length();
		for (int i = 0; (i + substrLen) <= strLen; i++) {
			if (str.substring(i, i + substrLen).equalsIgnoreCase(substr)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 功能：验证是否是正确的手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (StringUtils.isBlank(mobile))
			return false;
		return Pattern.matches("^(1[3|5|8])\\d{9}$", mobile);
	}

	/**
	 * 
	 * 方法用途: 替换空字符串<br>
	 * 实现步骤: <br>
	 * @param field
	 * @return
	 */
	public static String replaceNull(String field) {
		if (field == null || "undefined".equals(field) || "null".equalsIgnoreCase(field) || "".equals(field.trim())) {
			return "";
		} else {
			return field;
		}
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return boolean
	 * @date 2010-8-21
	 * @author yhd
	 */
	public static boolean strIsNull(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 功能：字符串不以"/"结尾，则在串尾加"/"
	 * 
	 * @param s
	 * @return
	 */
	public static String addSlashInEnd(String s) {
		if (s != null) {
			if (!s.endsWith("/")) {
				s = s + "/";
			}
		}
		return s;
	}

	/**
	 * 功能：传入一个数字类型的参数，返回一个小数点后两位的小数
	 * 
	 * @param parm
	 */
	public static String ConverDouble(String parm) {
		if (isNumeric(parm, false, true)) {
			if (parm.indexOf(".") >= 0) {
				String value = parm.substring(parm.indexOf(".") + 1);
				if (value.length() == 1) {
					return parm + "0";
				} else if (value.length() > 2) {
					return parm.substring(0, parm.indexOf(".") + 1) + value.substring(0, 2);
				} else {
					return parm;
				}

			} else {
				return parm + ".00";
			}
		}
		return null;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String generatorNumber(int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int t = Math.abs(new Random().nextInt()) % 10;
			sb.append(t);
		}
		return sb.toString();
	}

	/**
	 * 
	 * 方法用途: 生成新的文件名<br>
	 * 实现步骤: <br>
	 * @return
	 */
	public static String getNewFileName() {
		StringBuffer sb = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		sb.append(calendar.get(Calendar.YEAR));
		sb.append(calendar.get(Calendar.MONTH) + 1);
		sb.append(calendar.get(Calendar.DAY_OF_MONTH));
		sb.append(calendar.get(Calendar.HOUR_OF_DAY));
		sb.append(calendar.get(Calendar.MINUTE));
		sb.append(calendar.get(Calendar.SECOND));
		sb.append(num.addAndGet(1));
		if (num.get() > MAXNUM) {
			num.set(0);
		}
		return sb.toString();
	}

	/**
	   * 获取定长的字符串
	   * @param val
	   * @param len
	   * @return
	   */
	public static String getFixedLengthSeq(String val, int len) {
		int valLen = val.length();
		if (valLen < len) {
			StringBuffer seq = new StringBuffer();
			for (int i = 0; i < len - valLen; i++) {
				seq.append("0");
			}
			seq.append(val);
			return seq.toString();
		} else {
			return val.substring(0, len);
		}
	}

	public static void main(String arg[]) {
		// String str = "{extraPerson=, Single=100.0, Double=400.0, dd,
		// date=2010-03-02, currenyCode=RMB},{extraPerson=0.0, Single=300.0,
		// Double=400.0, date=2010-03-03, currenyCode=RMB},{extraPerson=0.0,
		// Single=300.0, Double=400.0, date=2010-03-04, currenyCode=RMB}";
		// String str = "{extraPerson=0.0, Single=300.0,
		// Double=400.0,date=2010-03-02, currenyCode=RMB}";
		// System.out.println(str);
		// stringToData(str);
		// System.out.println(isNumeric("1.11", true, 2, 2));

		// System.out.println(isMobile("18z22312539"));
		// System.out.println(generatorNumber(3));

		String str = "8888888-9999999|777777-5555";
		String[] arr = str.split("\\|");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
