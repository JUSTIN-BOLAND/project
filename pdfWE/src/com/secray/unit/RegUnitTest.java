package com.secray.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.secray.utils.common.RegUtil;

/** 
* 类  名: RegUnitTest 
* 描  述: TODO(这里用一句话描述这个类的作用) 
* 创建者: root
* 公   司: 郑州信达天瑞信息有限公司
* 创建时间: 2017年3月22日 下午4:47:58 
*  
*/
public class RegUnitTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = " 版权所有：河南省公安厅   备案序号：豫ICP备08005136号<br />";
		a=">豫ICP备11012831</a>&nbsp;郑公备";
		String[] st = RegUtil.getRegContent(a, RegUtil.GOV_HN_IPC,1,"@");
		for(String s :st){
			System.out.println("lb : "+s);
		}
		
	/*	a="  <a href=\"./index/dblm/wzsm/201306/t20130626_107529.html\">网站声明</a> "+
	     "| <a href=\"./index/dblm/wzdt/201307/t20130703_109561.html\">网站地图</a>"+
         "| <a href=\"./index/dblm/lxwm/201306/t20130626_107530.html\">联系我们</a>"+
         "| <a href=\"./index/dblm/xgxz/\" target=\"_blank\">相关下载</a>"+
         "| <a href=\"./index/dblm/dsj/\" target=\"_blank\">大事记</a>";
		a="    <p align=\"justify\">&nbsp;&nbsp;&nbsp; 网&nbsp;&nbsp;&nbsp; 址：<a href=\"http://www.jiyuan.gov.cn/\">http://www.jiyuan.gov.cn</a>&nbsp;&nbsp;</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; 地&nbsp;&nbsp;&nbsp; 址：济源市黄河路和沁园路交叉口向东200米路南综合行政服务中心1号楼</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; 邮&nbsp;&nbsp;&nbsp; 编：459000</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; 电&nbsp;&nbsp;&nbsp; 话：0391-6633495</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; 传&nbsp;&nbsp;&nbsp; 真：0391-6633400</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; 监督电话：0391-6633418</p>";
		a = a.replaceAll("&nbsp;", "").replaceAll("\\s+", "");
		 st = RegUtil.getRegContent(a, RegUtil.GOV_HN_LINK_ADDRESS,1,"@");
		for(String s :st){
			System.out.println("lb : "+s);
		}
		
		a = "主办：中共济源市委&nbsp;&nbsp;济源市人民政府&nbsp;&nbsp;版权所有禁止私自转载<br /> "+
   	       "承办：济源市电子政务办公室&nbsp;&nbsp;济源市行政服务中心&nbsp;&nbsp;豫ICP备05001062 ";
		 st = RegUtil.getRegContent(a, RegUtil.GOV_HN_CREATOR,1,"@");
		for(String s :st){
			System.out.println("lbs : "+s);
		}*/
		
		a="a-b|cdf";
		st = a.split("[-|/|]");
		for(String s :st){
			System.out.println("lbs : "+s);
		}
	}

}
