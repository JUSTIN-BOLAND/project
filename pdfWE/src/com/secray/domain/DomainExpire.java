package com.secray.domain;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.RegUtil;
import com.secray.utils.http.Spider;
import com.secray.utils.random.RandomUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 2017/5/26 0026.
 */
public class DomainExpire {
    private String domain ;

    public DomainExpire(String domain){
        this.domain = domain;
    }
    public  void post(String url) {
        Spider spider = null;

        try
        {
            spider = new Spider(url,Spider.POST);
            spider.releaseConnection();
        }
        catch(Exception e ){
            spider = new Spider(url,Spider.POST);
            spider.releaseConnection();
        }
        finally {

        }

    }
    public  String[] getContent(String url,String regExp,int index) {
        Spider spider = null;
        String[] contents = null;
        try
        {
            spider = new Spider(url,Spider.GET);
            contents = spider.getPageContent(regExp,index,"$");

        }
        catch(Exception e ){
            spider = new Spider(url,Spider.GET);
            contents = spider.getPageContent(regExp,index,"$");
            spider.releaseConnection();
        }
        finally {
            spider.releaseConnection();
        }
        return contents;
    }
    public  void sleep(){
       long rand =  RandomUtil.random(1,60);
        try {
            Thread.currentThread().sleep(rand * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String[] getDomainExpire(){
        String[] result = null;
        String url = "http://tool.chinaz.com/DomainDel/?wd=" + this.domain;
        String expireReg = "<ul\\s+class=\"zTlrCont\">([\\s\\S]*?)</ul>";
        String[] contents = this.getContent(url,null,1);
        expireReg = "<li[^>]*><div\\s+class=\"fl\\s+zTContleft\">([\\s\\S]*?)</div>" +
                "<div\\s+class=\"fr\\s+zTContrig\"><span>([\\s\\S]*?)</span></div></li>";
        if(contents!= null ) {
               String content =  contents[0];
           // for (String content : contents) {
                String results = "";
                String[] labels = RegUtil.getRegContent(content, expireReg, 1, "@");
                String[] dates = RegUtil.getRegContent(content, expireReg, 2, "@");
                if(labels == null || dates == null )  return null;
                for (int i = 0; i < labels.length; i++) {
                    System.out.println("lb1 : " + labels[i] + " -> " + dates[i]);
                    if (i != 1) {
                        if (results.length() > 0) results += ",";
                        results += dates[i];
                    }
                }
                if (results.length() > 0) {
                    result = results.split(",");
                    return result;
                }
            //}
        }


        return null;
    }

    public static void getDomainExpires(){
        JDBC jdbc =  new JDBC(JDBC.HF);
        JDBC uJdbc =  new JDBC(JDBC.HF);
        ResultSet rs  = null;
        DomainExpire domainExpire = null;
        String sql="SELECT srcTitle  FROM tb_dic_ipc where length(domainAge) = 0 OR domainAge IS NULL ";
        //sql="SELECT srcTitle  FROM tb_dic_ipc limit 1 ";

        try {
            jdbc.connect();
            rs = jdbc.query(sql);
            uJdbc.connect();
            while(rs.next()) {
                String domain = rs.getString(1);
                domainExpire = new DomainExpire(domain);
                String[] expires = domainExpire.getDomainExpire();
                if (expires != null) {
                     String updateSql = "update tb_dic_ipc set domainAge='"+expires[0]+"',domainExpireDate='"+expires[1]+"',domainDelDate='"+expires[2]+"' where srcTitle='"+domain+"'";
                    System.out.println(updateSql);
                    uJdbc.updateSql(updateSql);

                    domainExpire.sleep();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(rs!=null) rs.close();
                if(jdbc!=null) jdbc.close();
                if(uJdbc!=null) uJdbc.close();


            }
            catch(SQLException sqle){ sqle.printStackTrace(); }

            jdbc = null;
        }
    }

    public static void main(String[] args) {
        DomainExpire.getDomainExpires();
    }
}
