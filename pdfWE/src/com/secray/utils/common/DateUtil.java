package com.secray.utils.common;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	long startTime=System.currentTimeMillis();
	public static String SHORT_DATEMIN = "yyyyMMddHHmmsssss";
	public static String DATEMIN = "yyyy-MM-dd HH:mm:ss:sss";
	public static String DATE = "yyyy-MM-dd HH:mm:ss";
	public static String LONGDAY = "yyyy-MM-dd";
	public static String SHORTDAY = "yyyyMMdd";
	public static String LONGMONTH = "yyyy-MM";
	public static String SHORTMONTH = "yyyyMM";

	public static String YEAR = "yyyy";
	public static String MONTH = "MM";
	public static String DAY = "dd";
	public static String HOUR = "HH";
	public static String MINUTE = "mm";
	public static String SECOND = "ss";

	public static String SMONTH = "M";
	public static String SDAY = "d";
	public static String SHOUR = "H";
	public static String SMINUTE = "m";

	/**
	 *
	 * 函数名    : formatDateToStr
	 * 功   能	 : 日期转换成字符长
	 * 参   数    : @param date
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月21日 下午3:54:35
	 */
	public static String formatDateToStr(Date date,String formatExp) {

		SimpleDateFormat format = new SimpleDateFormat(formatExp);
		String str = format.format(date);
		return str;
	}

	/**
	 *
	 * 函数名    : formatStrToDate
	 * 功   能	 : 字符长转日期
	 * 参   数    : @param str
	 * 参   数    : @return    设定文件
	 * 返回值	 : Date    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月21日 下午3:57:55
	 */
	public static Date formatStrToDate(String str,String formatExp) {

		SimpleDateFormat format = new SimpleDateFormat(formatExp);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	public static Date formatDate(Date src,String formatExp) throws ParseException
	{

		SimpleDateFormat df = new SimpleDateFormat(formatExp);

		return df.parse(df.format(src) );

	}

	/**
	 *
	 * 函数名    : formatCurDateStr
	 * 功   能     : 把当前日期转换成格式的字符 串
	 * 参   数    : @formatExp ：yyyy-MM，yyyy-MM-dd,yyyy-MM-dd HH:mm:ss
	 * 参   数    : @return
	 * 参   数    : @throws ParseException    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月21日 下午4:01:26
	 */
	public static String formatCurDate(String formatExp)
	{

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(formatExp);
		return df.format(c.getTime()) ;

	}
	public static String formatCurChinaDate(String formatExp)
	{
		String result ="";
		if(formatExp.indexOf("-")>-1 ){
			String[] dts = formatExp.split("-");
			if(formatExp.trim().indexOf(" ")>-1) {
				String[] dayTime = formatExp.trim().split("\\s+");
				String[] days =  dayTime[0].split("-");
				String[] times =  dayTime[1].split(":");
				dts = ArrayUtil.concat(days, times);
			}

			Calendar c = Calendar.getInstance();
			for(String value : dts){
				String chinaValue = null;
				switch(value){
					case "yyyy":   chinaValue="年";  break;
					case "MM":   chinaValue="月"; break;
					case "dd":   chinaValue="日"; break;
					case "HH":   chinaValue="点"; break;
					case "mm":   chinaValue="分"; break;
					case "ss":   chinaValue="秒"; break;
				}
				SimpleDateFormat df = new SimpleDateFormat(value);
				result +=  df.format(c.getTime())+chinaValue ;
			}
		}
		return result;
	}
	public static String formatChinaDate(String dateStr)
	{
		String result ="";
		if(dateStr.indexOf("-")>-1 ){
			String[] dts = dateStr.split("-");
			if(dateStr.trim().indexOf(" ")>-1) {
				String[] dayTime = dateStr.trim().split("\\s+");
				String[] days =  dayTime[0].split("-");
				String[] times =  dayTime[1].split(":");
				dts = ArrayUtil.concat(days, times);
			}

			Calendar c = Calendar.getInstance();
			for(int i=0 ; i<  dts.length ; i++){
				String chinaValue = null;
				switch(i){
					case 0:   chinaValue="年";  break;
					case 1:   chinaValue="月"; break;
					case 2:   chinaValue="日"; break;
					case 3:   chinaValue="点"; break;
					case 4:   chinaValue="分"; break;
					case 5:   chinaValue="秒"; break;
				}

				result +=  dts[i]+chinaValue ;
			}
		}
		return result;
	}
	/**
	 *
	 * 函数名    : formatDateStr
	 * 功   能     : 把当前日期转换成格式的字符 串
	 * 参   数    : @formatExp ：yyyy-MM，yyyy-MM-dd,yyyy-MM-dd HH:mm:ss
	 * 参   数    : @return
	 * 参   数    : @throws ParseException    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月21日 下午4:01:26
	 */
	public static String formatDateStr(String dateStr,String formatExp,String ymdhmsExp)
	{
		String result = null;

		if(dateStr ==null || dateStr.trim().length()==0) return result;
		SimpleDateFormat df = new SimpleDateFormat(formatExp);
		Calendar c = Calendar.getInstance();
		try
		{
			c.setTime(df.parse(dateStr) );
			df = new SimpleDateFormat(ymdhmsExp);
			result =  df.format(c.getTime()) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}
	/**
	 *
	 * 函数名    : formatDateStr
	 * 功   能    : 把当前日期向前或者向后退几天在转换成格式的字符 串
	 * 参   数    : @param formatExp
	 * 参   数    : @param dayMonthNum
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月22日 上午10:38:02
	 */
	public static String formatDateStr(String formatExp,int dayMonthNum)
	{

		Calendar c = Calendar.getInstance();

		if(  DateUtil.DAY.equals(formatExp) || DateUtil.DATE.equals(formatExp)
				|| DateUtil.LONGDAY.equals(formatExp) || DateUtil.SHORTDAY.equals(formatExp))
		{
			c.add(Calendar.DAY_OF_MONTH, dayMonthNum);
		}
		else if( DateUtil.MONTH.equals(formatExp) || DateUtil.LONGMONTH.equals(formatExp)
				|| DateUtil.SHORTMONTH.equals(formatExp) || DateUtil.SMONTH.equals(formatExp))
		{
			c.add(Calendar.MONTH, dayMonthNum);
		}
		else if( DateUtil.YEAR.equals(formatExp))
		{
			c.add(Calendar.YEAR, dayMonthNum);
		}
		else if( DateUtil.DAY.equals(formatExp) || DateUtil.SDAY.equals(formatExp))
		{
			c.add(Calendar.DAY_OF_MONTH, dayMonthNum);
		}
		SimpleDateFormat df = new SimpleDateFormat(formatExp);
		return df.format(c.getTime()) ;

	}

	public  void  begin(String msg)
	{
		long endTime=System.currentTimeMillis();

		startTime=System.currentTimeMillis();
	}
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println( DateUtil.formatDateStr(DateUtil.DAY,0));

		System.out.println( CommonUtil.parseInt(DateUtil.formatDateStr("20131026121819","yyyyMMddHHmmss","HH"),-1));

		//CommonUtil.parseInt( DateUtil.formatDateStr("20131026121819","yyyyMMddHHmmss","HH"),-1);
		//System.out.println( Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+"\02");
		//System.out.println( Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+" ");

	}

}
