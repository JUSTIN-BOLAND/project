package com.deyi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	/**
	 * 校验验金额 两位小数
	 * @param d
	 * @return
	 */
	public static Boolean checkMoney(String money) {
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){2})?$");   
		Matcher matcher = pattern.matcher(money);
		return matcher.matches();
	}
	
}
