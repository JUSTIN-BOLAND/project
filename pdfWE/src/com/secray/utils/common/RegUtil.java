package com.secray.utils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 类  名: RegUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月23日 下午12:14:15
 *
 */
public class RegUtil {
	public static final Logger log = Logger.getLogger(RegUtil.class);
	public static String IPS = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	public static String IP = "(.*)//(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:.*/(\\w+)\\?.*";
	public static String ALL_HTML="<(\\w+)([^<>]*)>([^<>]+)</\\1>";
	public static String META="<meta([^<>]*)content=\"([^<>]*)\".*>";  //去主分类
	public static String A="<a(.*?)\\s+href=\"([^<\"]*)\"(.*?)>([^<>]+)</a>";  //去主分类

	//<a href="list/1.html"><span>北京市</span></a>
	public static String UL="<ul([^<>]*)>(.*?)</ul>";
	public static String LI="<li([^<>]*)>(.*?)</li>";

	public static String DIV="<div([^<>]*)>(.*?)</div>";
	public static String AA="<a([^<>]*)>(.*?)</a>";
	public static String TITLE="<title([^<>]*)>(.*?)</title>";
	public static String TITLE_EXT="<title([^<>]*)>([\\s\\S]*?)</title>";


	public static String CAT_CONTENT="<ul\\s+class=[\"|']tag_nav[\"|'].*?>([\\s\\S]*)</ul>";  //主分类的内容
	public static String CAT="<li([^<>]*)>([^<>]+)</li>";  //去主分类
	public static String SCAT_ID="<li(.*?)\\s+onmouseover=['\"]?(.*?)['\"]?>([^<>]+)</li>"; //去二级分类的ID
	public static String SCAT_CONTENT="<div\\s+class=[\"|']tag_tx[\"|']\\s+id=[\"|']subcatlisting_1[\"|'].*?>([\\s\\S]*)</ul>";   //二级分类主分类
	public static String SCAT="<li><a\\s+href=\"([^<\"]*)\"(.*?)>([^<>]+)</a></li>";
	public static String TCAT="<li><a\\s+style=\"font-size:14px;\"\\s+href=\"([^<\"]*)\"(.*?)>([^<>]+)</a></li>";

	public static String SPAN_SCAT="<div class=\"tag_tx\"([^\">]*)>([^<>]+)</div>";
	public static String DETAIL_CONTENT="<dl\\s+onMouseOver=\"this.className='on_over';s\\('(\\d+)'\\);\"([^>]*)>([\\s\\S]*?)</dl>";   //明细内容
	public static String DETAIL_COMPANY="<h4><a([^<>]+)>([^<>]+)</a></h4>";

	public static String DETAIL_DESC="<dd class=\"txt\">([^<>]+)</dd>";
	public static String DETAIL_ADDRESS="<span  itemprop=\"address\">([^<>]+)</span>";
	public static String DETAIL_PAGESIZE = "<em>([^<>]+)</em>";

	public static String  COM_PROVINCE="<div\\s+class=\"ad_list\">([\\s\\S]*)</div>";


	public static String GOV_PROV_CONTENT_lb="<div\\s+class=[\"|']sflist3[\"|']>([\\s\\S]*)</div>";


	public static String GOV_PROV_CONTENT="<div\\s+class=\"sflist3\">([\\s\\S]*)</div>";
	public static String GOV_CAT_CONTENT="<div\\s+class=sflist1>([\\s\\S]*)</div>";
	public static String GOV_CAT="<li><a\\s+href=\"(.*?)\"[^>]+>([^<>]+)</a></li>";
	public static String GOV_PAGESIZE="<div\\s+id=pagecount>当前是第(\\d+)页，共(\\d+)页，总共收录了(\\d+)条信息</div>";
	public static String GOV_COMPANY = "<div\\s+id=fllist1><a[^<>]+>([^<>]+)</a></div><div\\s+id=fllist2>([^<>]+)</div>";

	public static String AREA_FIRSTURL = "<a\\s+href=\"(.*)\">全站首页</a>";
	public static String AREA_PROVINCE = "<div\\s+class=\"top_nav\">([\\s\\S]*)</div>";
	public static String AREA_CITY_CONTENT = "<div\\s+class=\"cate\\d*\">([\\s\\S]*)</div>";
	public static String AREA_COUNTRY="<li><a\\s+href=\"([^<\"]*)\"(.*?)>([^<>]*)</a></li>";
	public static String AREA_CITY_NAME="<li><a\\s+href=\"(.*?)\"><span>([^<>]*)</span></a></li>";
	public static String AREA_CITY_NAME_OTHERS="<li><span><a\\s+href=\"(.*?)\">([^<>]*)</a></span></li>";

	public static String IPC_CONTENT="<table\\s+id=\"show_table\"(.*?)>([\\s\\S]*)</table>";
	public static String IPC_NET="<td\\s+style=\"word-break:break-all;word-wrap:break-word;\">([^<>]*)</td>";

	public static String GOV_HN_CONTENT="<td><select\\s+name=\"cars\"\\s+class=\"bor2\"(.*?)>([\\s\\S]*?)</select></td>";
	public static String GOV_HN_WEBSITE="<option\\s+value=\"(.*?)\">([^<>]*)</option>";
	public static String GOV_HN_IPC="豫ICP(.*?)<";
	public static String GOV_HN_ADDRESS="地址[：|:](.*?)(?=<.*)";
	public static String GOV_HN_ADDRESS_EXT="<h3\\s+class=\"lh30 txtindent f14\">河南省([^<>]*)</h3>";
	public static String GOV_HN_OWNER="版权所有(</span>)?[：|:]([\u4e00-\u9fa5]+).*";

	public static String REFRESH="<meta\\s+http-equiv=\"refresh\"\\s+content=\"0;url=(.*?)\">";
	public static String GOV_HN_LINKMAN=".*<a\\s+href=\"([^\"]*)\".*>\\s*(联系我们|认识我们|联系方式)\\s*</a>";//"<a\\s+href=\"([^\"]*)\".*>(联系我们|认识我们|联系方式)</a>";
	public static String GOV_HN_LINK_ADDRESS=".*地址[：|:](.*?)(?=<.*)";
	public static String[] GOV_HN_LINK_ADDRESSES={".*[地址|学校地址][：|:]([\\s\\S]*?)(?=<.*)",
			                                       ".*[地址|办公地点]\\s*[：|:]\\s*(.*)\\s*邮编.*"
	                                               };
	public static String GOV_HN_CREATOR="[主办|主办单位][：|:](.*?)(?=(承办|<).*)";
	//("<title>([^</title>]*)");//



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
		String src = "[16-04-12 03:40:01 DEBUG] model.MappingNode:- ['/store/shopclass'] matched over.";
		//src="地址：信阳平桥工业园&nbsp;&nbsp; 电话：0376-6152981 / 6152986&nbsp; 传真：0376-3772981<br />"+
		//	"版权所有:信阳市天宇矿业有限公司&nbsp;&nbsp; 技术支持：";
		/*	src=" <h3 class=\"lh30 txtindent f14\">河南省郑州市金水区黄河路3号</h3> "+
         "<h3 class=\"lh30 txtindent f14\">河南省郑州市金水区经五路2号</h3> "+
         "<h3 class=\"lh30 txtindent f14\">人事行政热线：0371-65889757</h3>	";*/
	/*	src = "Copyright© 2002-2014 神州乐器网版权所有</li>";
		src = "192.168.2.1";
		src =  "               办公地点 ：郑东新区正光路11号省直机关综合办公楼 邮编：450018\n" ;
		if(RegUtil.find(src,RegUtil.IPS)) {
			System.out.println("ip");
		}
		else System.out.println("no ip");*/
		String exp="\\[(?<datetime>.*)\\]\\s+(?<method>(.*)):-\\s+\\[(?<url>(.*))\\]\\s+(?<content>(.*))";//"(\\[.*\\]) (.*):- (\\[.*\\]) (.*)";
		String[] sts = RegUtil.getRegContent(src,exp,3,"@");
		for(String s :sts){
			System.out.println("lb : "+s+" : "/*+s.split(",")[1].replaceAll("[^0-9]", "")*/);
		}



	}
}

