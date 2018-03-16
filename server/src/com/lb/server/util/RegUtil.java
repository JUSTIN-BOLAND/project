package com.lb.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class RegUtil {
	public static final Logger log = Logger.getLogger(RegUtil.class);
	public static String IPS = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	public static String IP = "(.*)//(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:.*/(\\w+)\\?.*";
	public static String ALL_HTML="<(\\w+)([^<>]*)>([^<>]+)</\\1>";
	public static String META="<meta([^<>]*)content=\"([^<>]*)\".*>";  //鍘讳富鍒嗙被
	public static String A="<a(.*?)\\s+href=\"([^<\"]*)\"(.*?)>([^<>]+)</a>";  //鍘讳富鍒嗙被


	public static String[] getRegContent(String src,String regExp,int indx)
	{
		return getRegContent(src,regExp,indx,",");
	}
	public static String[] getRegContent(String src,String regExp,int indx,String seperator)
	{
		String result = "";
		//  log.info("getRegContent : "+regExp+" \b : "+src);
		if(src == null  ) return null;
		Pattern pat = Pattern.compile(regExp/*,Pattern.CASE_INSENSITIVE | Pattern.DOTALL*/);
		Matcher mat = pat.matcher(src);
		while (mat.find()) {
			//System.out.println(mat.group(indx) +" : "+mat.groupCount());
			if( result.length() > 0 )  result += seperator;
			result += mat.group(indx) ;

		}

		if(result.length() == 0) return null;
		else return result.split(seperator);
	}

	public static  boolean find(String content,String regExpr){
		Pattern pat = Pattern.compile(regExpr);

		Matcher mat = pat.matcher(content);

		boolean isFind = mat.find();

		return isFind;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "&nbsp;&nbsp; 鐗堟潈鎵�鏈�</span>:娲涢槼甯備汉鎵嶆湇鍔′腑蹇�";
		//src="鍦板潃锛氫俊闃冲钩妗ュ伐涓氬洯&nbsp;&nbsp; 鐢佃瘽锛�0376-6152981 / 6152986&nbsp; 浼犵湡锛�0376-3772981<br />"+
		//	"鐗堟潈鎵�鏈�:淇￠槼甯傚ぉ瀹囩熆涓氭湁闄愬叕鍙�&nbsp;&nbsp; 鎶�鏈敮鎸侊細";
		/*	src=" <h3 class=\"lh30 txtindent f14\">娌冲崡鐪侀儜宸炲競閲戞按鍖洪粍娌宠矾3鍙�</h3> "+
         "<h3 class=\"lh30 txtindent f14\">娌冲崡鐪侀儜宸炲競閲戞按鍖虹粡浜旇矾2鍙�</h3> "+
         "<h3 class=\"lh30 txtindent f14\">浜轰簨琛屾斂鐑嚎锛�0371-65889757</h3>	";*/
		src = "Copyright漏 2002-2014 绁炲窞涔愬櫒缃戠増鏉冩墍鏈�</li>";
		src = "192.168.2.1";
		src =  "               鍔炲叕鍦扮偣 锛氶儜涓滄柊鍖烘鍏夎矾11鍙风渷鐩存満鍏崇患鍚堝姙鍏ゼ 閭紪锛�450018\n" ;
		if(RegUtil.find(src,RegUtil.IPS)) {
			System.out.println("ip");
		}
		else System.out.println("no ip");
		String exp=".*鍔炲叕鍦扮偣\\s*[锛殀:]\\s*(.*)\\s*閭紪.*";
		String[] sts = RegUtil.getRegContent(src,exp,1,"@");
		for(String s :sts){
			System.out.println("lb : "+s+" : "/*+s.split(",")[1].replaceAll("[^0-9]", "")*/);
		}



	}
}

