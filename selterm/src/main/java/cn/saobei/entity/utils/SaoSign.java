package cn.saobei.entity.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import cn.saobei.SaoConfig;
import cn.saobei.entity.request.pay.PaySign;

public class SaoSign {

	public static String getDate() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String DatasignSign(PaySign o) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		Class<?> class1 = o.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		// List<String> arrayList = new ArrayList<String>();
		String[] aa = new String[declaredFields.length-1];

		for (Field f : declaredFields) {
			Seq annotation = f.getAnnotation(Seq.class);
			if (null == annotation)
				continue;

			f.setAccessible(true);
//			if (f.get(o) != null && f.get(o) != "") {
				aa[annotation.value()] = f.getName() + "=" + f.get(o);
//			}
		}

		String join = StringUtils.join(aa, "&");
		System.out.println("sign before md5:" + join);
		String upperCase = DigestUtils.md5Hex((join).getBytes("UTF-8")).toUpperCase();
		System.out.println("sign after  md5:" + upperCase);
		return upperCase;

	}

	public static String DataSign(Object o) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		Class<?> class1 = o.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		List<String> arrayList = new ArrayList<String>();

		for (Field f : declaredFields) {
			Required annotation = f.getAnnotation(Required.class);
			if(null == annotation)
				continue;
			f.setAccessible(true);
//			if (f.get(o) != null && f.get(o) != "") {
				arrayList.add(f.getName() + "=" + f.get(o) + "&");
//			}
		}
		int size = arrayList.size();
		String[] array = arrayList.toArray(new String[size]);
		Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(array[i]);
		}
		String result = sb.toString();
		result += "key=" + SaoConfig.getKey();
		System.out.println("sign before md5:" + result);
		String upperCase = DigestUtils.md5Hex((result).getBytes("UTF-8")).toUpperCase();
		System.out.println("sign after  md5:" + upperCase);
		return upperCase;

	}
	
	public static String DataUpdateSign(Object o) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		Class<?> class1 = o.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		List<String> arrayList = new ArrayList<String>();

		for (Field f : declaredFields) {
			Update annotation = f.getAnnotation(Update.class);
			if(null == annotation)
				continue;
			f.setAccessible(true);
//			if (f.get(o) != null && f.get(o) != "") {
				arrayList.add(f.getName() + "=" + f.get(o) + "&");
//			}
		}
		int size = arrayList.size();
		String[] array = arrayList.toArray(new String[size]);
		Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(array[i]);
		}
		String result = sb.toString();
		result += "key=" + SaoConfig.getKey();
		System.out.println("sign before md5:" + result);
		String upperCase = DigestUtils.md5Hex((result).getBytes("UTF-8")).toUpperCase();
		System.out.println("sign after  md5:" + upperCase);
		return upperCase;

	}

	public static String DataPaySign(Object o) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		Class<?> class1 = o.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		
		String[] aa = new String[declaredFields.length-1];
		
		for (Field f : declaredFields) {
			f.setAccessible(true);
			Seq annotation = f.getAnnotation(Seq.class);
			if (null == annotation)
				continue;

//			if (f.get(o) != null && f.get(o) != "") {
				aa[annotation.value()] = f.getName() + "=" + f.get(o);
//			}
		}
		
		
		ArrayList<String> arrayList = new ArrayList<String>();
		List<String> asList = Arrays.asList(aa);
		for (String string : asList) {
			if(StringUtils.isNotBlank(string))
				arrayList.add(string);
		}
		String result = StringUtils.join(arrayList,"&");
		
		result += "&access_token=" + SaoConfig.getAccess_token();
		System.out.println("sign before md5:" + result);
		String upperCase = DigestUtils.md5Hex((result).getBytes("UTF-8")).toUpperCase();
		System.out.println("sign after  md5:" + upperCase);
		return upperCase;

	}

	/**
	 * 
	 * 方法用途: 生成随机字符串<br>
	 * 实现步骤: <br>
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

}