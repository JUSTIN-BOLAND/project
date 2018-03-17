package com.secray.utils.common;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class CommonUtil {

	public static String PREFIX="1";
	public static String SUFFIX="0";
	public static String NEED_ESC_CHARS = "$,(,),*,+,.,[,],?,\\,^,{,},|";

	public static String upperFirstLetter(String src){
       if(src==null || src.trim().length() ==0 ) return src;
		return src.substring(0,1).toUpperCase() + src.substring(1);
	}
	public static String lowerFirstLetter(String src){
		if(src==null || src.trim().length() ==0 ) return src;
		return src.substring(0,1).toLowerCase() + src.substring(1);
	}

	public static  boolean isIP(String addr)
	{
		if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
		{
			return false;
		}
		/**
		 * 判断IP格式和范围
		 */
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		return RegUtil.find(rexp,addr);


	}


	/**
	 *
	 * 函数名    : concat
	 * 功   能    : 把对象数组合并成 seperator 分割的字符串
	 * 参   数    : @param srcStr 		: 数组
	 * 参   数    : @param seperator	: 分隔符
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月27日 下午2:20:58
	 */
	public static  String concat(Object[] srcStr,String seperator)
	{
		String result = "";
		for(int i=0;i<srcStr.length;i++)
		{
			if( result.trim().length()>0 ) result += seperator;
			result += (srcStr[i] == null ? "":srcStr[i].toString());
		}
		return result;
	}

	/**
	 *
	 * 函数名    : concat
	 * 功   能    : 把对象数组合并成 seperator 分割的字符串
	 * 参   数    : @param srcStr 		: 数组
	 * 参   数    : @param seperator	: 分隔符
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月27日 下午2:20:58
	 */
	public static  String concat(double[] srcStr,String seperator)
	{
		String result = "";
		for(int i=0;i<srcStr.length;i++)
		{
			if( result.trim().length()>0 ) result += seperator;
			result += srcStr[i]+"";
		}
		return result;
	}
	/**
	 *
	 * 函数名    : concat
	 * 功   能    : 把数组合并成 seperator 分割的字符串
	 * 参   数    : @param srcStr 		: 数组
	 * 参   数    : @param seperator	: 分隔符
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月27日 下午2:20:58
	 */
	public static  String concat(int[] srcStr,String seperator)
	{
		String result = "";
		for(int i=0;i<srcStr.length;i++)
		{
			if( result.trim().length()>0 ) result += seperator;
			result += srcStr[i];
		}
		return result;
	}
	/**
	 *
	 * 函数名    : substr
	 * 功   能    : TODO(这里用一句话描述这个类的作用)
	 * 参   数    : @param srcStr
	 * 参   数    : @param begin	: 数组的开始位置
	 * 参   数    : @param end		: 数组的结束位置
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月27日 下午2:22:33
	 */
	public static  String substr(String[] srcStr,int begin,int end)
	{
		String result="";
		if(begin<0 || end <0 || begin>end || srcStr==null ) return result;
		for(int i=0;i<srcStr.length;i++)
		{
			if(i>=begin && i<=end)
			{
				if(result.length()>0) result = result+",";
				result = result+srcStr[i];
			}
		}
		return result;
	}
	public static  long parseLong(String srcStr)
	{
		String temp = srcStr;
		if(temp==null || temp.trim().length()==0) temp= "0";
		return Long.parseLong(temp.trim());

	}
	public static  int parseInt(String srcStr)
	{
		String temp = srcStr;
		if(temp==null || temp.trim().length()==0) temp= "0";
		if("\\N".equals(temp.trim()) )
			return 0;
		else
			return Integer.parseInt(temp.trim());

	}
	public static  String convertDoubleToStr(double srcStr)
	{
		Double d = new Double(srcStr);

		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setGroupingUsed(false);

		return nf.format(d);

	}
	public static  Double parseDouble(Object srcStr,Double defaultValue)
	{
		String temp = srcStr.toString();
		if(temp==null || temp.trim().length()==0) return defaultValue;
		else if("\\N".equals(temp.trim()) )
			return 0d;
		else{
			DecimalFormat df = new DecimalFormat("#.##");
			return Double.parseDouble(df.format(srcStr));
		}

	}
	public static  int parseInt(String srcStr,int defaultValue)
	{
		String temp = srcStr;
		if(temp==null || temp.trim().length()==0 ) return defaultValue;
		else if("\\N".equals(temp.trim()) )
			return 0;
		else
			return Integer.parseInt(temp.trim());

	}

	public static  String reverse(String srcStr)
	{
		if(srcStr==null) return "";
		else
		{
			StringBuffer sb = new StringBuffer(srcStr);
			return sb.reverse().toString().trim();


		}
	}
	public static  String convertToNull(String srcStr)
	{
		if(srcStr==null) return "";
		else return srcStr;

	}
	public static  String convertToNullWithTrim(String srcStr)
	{
		if(srcStr==null) return "";
		else return srcStr.trim();

	}
	public static  String convertToNullWithDefault(String srcStr,String defaultValue)
	{
		if(srcStr==null) return defaultValue;
		else return srcStr.trim();
	}

	public static  String[] resizeArray(String[] src,String[] dest,boolean isNull)
	{
		int destLen = dest.length;
		if(dest[destLen-1].trim().length()==0) destLen--;
		String[] result = new String[src.length];
		for(int i=0;i<src.length;i++)
		{
			if(i<destLen)
				result[i] = dest[i];
			else
			{
				if(isNull) result[i] ="";
				else result[i] = src[i];
			}
		}
		return result;

	}
	public static  String lpad(String src,int len,String pad)
	{
		String result=src;
		if(src!=null && pad!=null)
		{
			if(src.length()<len)
			{
				for(int i=1;i<=(len-src.length());i++)
				{
					result=pad+result;
				}
			}
		}
		return result;
	}
	public static  String getExcludePath(String excludePath)
	{
		String result="";
		if(CommonUtil.convertToNullWithTrim(excludePath).length()==0) return "";
		String[] paths = CommonUtil.split(excludePath, ",", null, null);
		for(int i=0;i<paths.length;i++)
		{
			if(result.trim().length()>0) result=result+" ";
			result=result+" --exclude="+paths[i];
		}
		return result;
	}
	public static  String[] split(String src,String split,String appendStr,String fixType)
	{
		if(src==null || split==null)  return null;
		String[] result = src.split(split);
		if(appendStr==null) appendStr="";
		if(fixType==null) fixType = CommonUtil.PREFIX;
		for(int i=0;i<result.length;i++)
		{
			if(CommonUtil.PREFIX.equals(fixType))
				result[i]=appendStr+result[i].trim();
			else
				result[i]=result[i].trim()+appendStr;
			System.out.println(result[i]);
		}
		return result;
	}
	public static String getCurrentDay()
	{
		return Calendar.getInstance().get(Calendar.YEAR)+"-"+CommonUtil.lpad(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1),2,"0")+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	public static void printArray(String[] src)
	{
		for(int i=0;i<src.length;i++)
		{
			System.out.println("printArray: "+src[i]);
		}
	}
	public static boolean isInt(String src){
		if( convertToNull(src).length() == 0) return false;
		try
		{
			Integer.parseInt(src);
			return true;
		}
		catch(java.lang.NumberFormatException nfe)
		{
			return false;
		}
	}

	public static boolean isTwinExist(String src)
	{
		Pattern pat = Pattern.compile("\\[.*?\\]");
		Matcher mat = pat.matcher(src);
		if (mat.find()) {
			System.out.println(mat.group(0) );
			return true;
		}
		return false;
	}
	/**
	 *
	 * 函数名    : getBuildInVars
	 * 功   能     : 获取【】内的内容
	 * 参   数    : @param src
	 * 参   数    : @return    设定文件
	 * 返回值    : String[]    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月22日 上午11:24:58
	 */
	public static String[] getBuildInVars(String src)
	{
		String result = "";

		Pattern pat = Pattern.compile("\\[(.*?)\\]");
		Matcher mat = pat.matcher(src);
		while (mat.find()) {
			//System.out.println(mat.group(0) +" : "+mat.groupCount());
			if( result.length() > 0 )  result += ",";
			result += mat.group(0) ;

		}

		if(result.length() == 0) return null;
		else return result.split(",");
	}

	/**
	 *
	 * 函数名    : addEscapeCHar
	 * 功   能    : 添加转义符号
	 * 参   数    : @param src
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年10月10日 下午2:43:19
	 */
	public static String addEscapeCHar(String src)
	{
		String result = "";
		boolean isMatch = false;
		String charStr = null;
		String[] escpChars = NEED_ESC_CHARS.split(",");

		for( int j = 0 ; j < src.length() ; j++)
		{
			isMatch = false;
			charStr = src.charAt(j)+"";
			for( int i = 0 ; i < escpChars.length ; i++)
			{

				if(charStr.equals(escpChars[i])){
					result += charStr.replace(escpChars[i], "\\" + escpChars[i]);
					isMatch = true;
					break;
				}
			}
			if(!isMatch) result += charStr;
		}
		return result;
	}

	/**
	 *
	 * 函数名    : addExp
	 * 功   能     : 为表达式添加 CommonUtil.parseInt(
	 * 参   数    : @param src
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年11月8日 下午12:54:00
	 */
	public static String addExp(String src)
	{
		String result = src;

		Pattern pat = Pattern.compile("values\\[(.*?)\\]");
		Matcher mat = pat.matcher(src);
		while (mat.find()) {
			System.out.println(mat.group(0) +" : "+mat.groupCount());
			result = result.replace(mat.group(0),"CommonUtil.parseInt("+ mat.group(0)+",0)");

		}
		return result;
	}

	public static int indexOf(String src,String sperator,int index)
	{
		int result=0;
		if(src == null || src.trim().length() ==0 ) return -1;
		String[] values = src.split(sperator);
		for(int i=0 ;i< values.length; i++){
			if( i == index-1){
				result += CommonUtil.convertToNullWithDefault(values[i],"").length()+sperator.length() * index;
				return result -1;
			}
			else
				result += CommonUtil.convertToNullWithDefault(values[i],"").length();
		}
		return result;

	}

	public static String replace(String exp,double[] values){

		String result = exp;
		String[] exps = CommonUtil.getBuildInVars(exp);
		for(String str : exps){
			int idx = Integer.parseInt(str.replaceAll("[\\[\\]]", ""));
			result = result.replace("values"+str, CommonUtil.convertDoubleToStr(values[idx])+"");
			//Log.info("replace: "+"values"+str+" -> "+values[idx]+" ->"+idx);
			//System.out.println(str+""+result);
		}
		return result;
	}
	public static String asString(String[] values,String splitSysmbol){
		String result="";
		for(String value: values){
			if(result.trim().length()>0) result += splitSysmbol;
			result +=value;
		}
		return result;
	}


	// 判断一个字符串是否都为数字
	public static boolean isDigit(String strNum) {
		return strNum.matches("[0-9]{1,}");
	}




	//截取数字
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	// 截取非数字
	public static String getNotNumber(String content) {
		Pattern pattern = Pattern.compile("\\D+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	// 判断一个字符串是否含有数字

	public static boolean hasDigit(String content) {

		boolean flag = false;

		Pattern p = Pattern.compile(".*\\d+.*");

		Matcher m = p.matcher(content);

		if (m.matches())

			flag = true;

		return flag;

	}


	public static void main(String[] args) {
		String st="UserName";
		System.out.println(CommonUtil.lowerFirstLetter(st));
	}

}
