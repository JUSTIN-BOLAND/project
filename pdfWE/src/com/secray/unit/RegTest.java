package com.secray.unit;

import com.secray.utils.common.RegUtil;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
public class RegTest {



    public static void test3(){
        String src="<HTML><HEAD><TITLE>新乡职业技术学院</TITLE><META Name=\"keywords\" Content=\"新乡职业技术学院,首页\" />";

        src="<div class=\"fl zTContleft\">域名创建时间</div><div class=\"fr zTContrig\"><span>1997-09-15</span></div></li><li class=\"bor-b1s bg-list clearfix\"><div class=\"fl zTContleft\">域名到期时间</div><div class=\"fr zTContrig\"><span>2018-09-14</span></div></li><li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名删除时间</div><div class=\"fr zTContrig\"><span>2018-11-28</span></div>";
        //src=" <div class=\"fl zTContleft\">域名创建时间</div>";
        String GOV_HN_LINKMAN = "<div\\s+class=\"fl\\s+zTContleft\">([^<>]*)</div>";
        String[] st = RegUtil.getRegContent(src,GOV_HN_LINKMAN,1,"@");


        for(String s :st){
            System.out.println("ul : "+s+" : "/*+s.split(",")[1].replaceAll("[^0-9]", "")*/);


        }
    }
    public static void main(String[] args) {

        //RegTest.test3();

        String st="uid$设备号$$$";
        String[] sts = st.split("\\$",-1);
        System.out.println(sts.length);
    }

}
