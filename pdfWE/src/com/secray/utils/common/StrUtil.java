package com.secray.utils.common;

/**
 * 类  名: StrUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月22日 下午4:02:23
 *
 */
public class StrUtil {

	public static String trimEnter(String src ){
		if(src == null  || src.trim().length() == 0 ) return src;
		return src.replaceAll("(\r\n|\r|\n|\n\r)","");
	}
	public static char convertNumToChar(int num){
		return (char)num;
	}

	public static int convertCharToNum(char num){
		return String.valueOf(num).getBytes()[0];
	}

	public static String[] split(String src,String exp){
		if(src == null || src.trim().length()==0 ||
				exp == null || exp.trim().length()==0) return null;
		return src.split(exp,-1);
	}
	public static String[] split(String src,String exp,String defaultValue){
		String[] result = split(src,exp);
		for(int i=0; i< result.length; i++){
			result[i] = ( result[i] == null ||  result[i].trim().length() ==0  ? defaultValue : result[i]);
		}
		return result;
	}
	public static String mkString(String[] values,String seperator){
		if( values == null || values.length ==0 ) return null;
		String result="";
		for(String v : values){
			if( result.trim().length() > 0 ) result += seperator;
			result += v;
		}
		return result;
	}
	public static String mkString(String[] values){
		return mkString(values,",");
	}
}
