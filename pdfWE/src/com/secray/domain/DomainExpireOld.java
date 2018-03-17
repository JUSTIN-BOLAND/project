package com.secray.domain;

import com.secray.utils.common.RegUtil;
import com.secray.utils.http.Spider;

/**
 * Created by root on 2017/5/27 0027.
 */
public class DomainExpireOld {


    public  String[] getContent(String url,String regExp,int index) {
        Spider spider = null;
        String[] contents = null;
        try
        {
            spider = new Spider(url,Spider.GET);
            contents = spider.getPageContent(regExp,index,"#");
            spider.releaseConnection();

        }
        catch(Exception e ){
            spider = new Spider(url,Spider.GET);
            contents = spider.getPageContent(regExp,index,"#");
            spider.releaseConnection();
        }
        finally {

        }
        if( contents  != null ) {
            if(contents[0] != null) contents[0] = contents[0].toLowerCase();
        }

        return contents;
    }
     public String[] getDomainExpire(String domain){
           String[] result = {"","",""};
          if(domain != null ){
              String url="http://tool.chinaz.com/DomainDel/?wd="+domain;
              String reg="<div\\s+class=\"fl\\s+zTContleft\">([^<>]*)</div><div\\s+class=\"fr\\s+zTContrig\"><span>([^<>]*)</span></div>";
              String[] conttents = this.getContent(url,null,1);
              if( conttents != null ){
                  String[] names = RegUtil.getRegContent(conttents[0],reg,1);
                  String[] values = RegUtil.getRegContent(conttents[0],reg,2);
                  result[0]= values[0];
                  result[1]= values[1];
                  result[2]= values[2];
              }
          }
          return result;
     }

     public static DomainExpireOld getInstance()
     {
         DomainExpireOld de = new DomainExpireOld();
         return de;
     }

    public static void main(String[] args) {
        DomainExpireOld.getInstance().getDomainExpire("www.163.com");
    }
}
