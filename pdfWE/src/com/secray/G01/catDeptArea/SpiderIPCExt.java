package com.secray.G01.catDeptArea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.ChineseToEnglish;
import com.secray.utils.common.CommonUtil;
import com.secray.utils.common.RegUtil;
import com.secray.utils.http.Spider;

/**
 * 类  名: SpiderCDA
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月23日 上午11:19:38
 *
 */
public class SpiderIPCExt {

	public static final Logger log = Logger.getLogger(SpiderIPCExt.class);

	/*
    CREATE TABLE tb_dic_noheader_ipc (
           url VARCHAR(200),
           company  VARCHAR(200),
           address  VARCHAR(200)
        )

	 */
	public static void fetchProvince(){
		JDBC jdbc =  new JDBC(JDBC.HF);
		JDBC uJdbc =  new JDBC(JDBC.HF);
		ResultSet rs  = null;
		String url = null,hUrl = null;
		String[] urls = null;
		try
		{
			jdbc.connect();

			if(jdbc.queryForInt("select count(1) from tb_dic_noheader where flag!=0") == 0 ){
				jdbc.truncate("tb_dic_noheader_ipc");
			}

			//String sql="select * from tb_dic_noheader where  url='www.hajy.hrss.gov.cnrbj.jiyuan.gov.cn'";
			String sql="select * from tb_dic_noheader where flag=0";

			rs = jdbc.query(sql);
			uJdbc.connect();
			while(rs.next()){
				url = rs.getString(1);
				urls = url.split(".");
				if(urls.length == 2 ) hUrl = "www."+url;
				else hUrl = url;
				fetchCity(uJdbc,url,"http://"+hUrl);
			}

			log.info("fetchProvince : 生成tb_dic_noheader_ipc结果表数据!" );
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{

			try{
				if(rs != null ) rs.close();
				if(jdbc!=null) jdbc.close();
				if(uJdbc!=null) uJdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}
	}

	public static void fetchCity(JDBC jdbc,String srcUrl,String url){

		String sql="insert into tb_dic_noheader_ipc values";
		String[] ipcs =  null;
		String[] address = null;
		String[] depts = null;
		boolean isEnableOpen = true;
		try
		{
			log.info("fetchCity : "+url);
			Spider spider = new Spider(url);
			String[] contents = spider.getPageContent(null,1,"#");
			spider.releaseConnection();
			//log.info("ret: "+contents[0].indexOf("找不到站点")+" : "+contents[0].indexOf("403"));
			if(contents[0].indexOf("找不到站点") >-1 ) {
				isEnableOpen = false;
			}
			else{
				String[] titles = RegUtil.getRegContent(contents[0], RegUtil.TITLE,2,",");
				if(titles[0].indexOf("404") > -1 /*|| titles[0].indexOf("403") > -1 */|| titles[0].indexOf("&") > -1){
					isEnableOpen = false;
				}
			}
			if(spider.getStatusCode() == 200 && isEnableOpen){
				String[] refUrls =  RegUtil.getRegContent(contents[0], RegUtil.REFRESH,1,"#");
				if(refUrls != null){
					log.info("fetchCity : "+url+" 重定向 -> "+refUrls[0]);
					spider = new Spider(refUrls[0]);
					contents = spider.getPageContent(null,1,"#");
					spider.releaseConnection();
				}
				//log.info("content ->  " + contents[0]);

				depts = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_OWNER,2,"#");
				if(depts == null){
					depts = RegUtil.getRegContent(contents[0], RegUtil.TITLE,2,"#");
					if(depts[0].indexOf("双汇集团")>-1)  depts[0]="双汇集团";
					else depts[0] = depts[0].split("[-|/|]")[0];
				}
				ipcs =  RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_IPC,1,"@");
				address = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_ADDRESS,1,"#");
				if(address == null  || depts == null) {

					String tmp = url.replace("http://", "");
					int pos = tmp.indexOf(".");
					tmp = tmp.substring(pos+1);
					String needUrl = "http://"+(tmp.indexOf("www.")>-1 ? tmp : "www."+tmp);
					if(url.indexOf("www.xxgk.jiyuan.gov.cn")>-1) {
						needUrl  = "http://www.jiyuan.gov.cn";
					}
					log.info("needUrl : " + needUrl);
					spider = new Spider(needUrl);
					contents = spider.getPageContent(null,1,"#");
					spider.releaseConnection();
					log.info(contents[0]);
					depts = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_OWNER,2,"#");
					if(depts == null) {
						//log.info(contents[0].indexOf("主办")+" : "+ contents[0].indexOf("承办："));
						String[] dept = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_CREATOR,1,"#"); //contents[0].substring(contents[0].indexOf("主办"), contents[0].indexOf("承办："));
						dept[0] = dept[0].replaceAll("&nbsp;", " ");
						dept = dept[0].split("\\s+");
						depts = new String[]{dept[1]};
						if(depts == null){
							String deptStr = contents[0].substring(contents[0].indexOf("版权所有")+4,20);
							deptStr = deptStr.replaceAll("[^\u4e00-\u9fa5]+","");
							depts = new String[]{deptStr};

						}
						if(depts == null){
							depts = RegUtil.getRegContent(contents[0], RegUtil.TITLE,2,"@");
							if(depts[0].indexOf("双汇集团")>-1)  depts[0]="双汇集团";
							else depts[0] = depts[0].split("[-|/|]")[0];
						}

					}
					ipcs =  RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_IPC,1,"@");
					address = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_ADDRESS,1,"#");
					if(address == null){
						address = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_ADDRESS_EXT,1,"@");
						address[0] = "河南省"+address[0];
					}
					if(address == null) {
						String[] linkmans = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_LINKMAN,1,"@");
						if(linkmans != null) {
							String linkUrl = (linkmans[0].indexOf("http://") ==-1 ?  needUrl + linkmans[0].substring(1) : linkmans[0]);
							spider = new Spider(linkUrl);
							contents = spider.getPageContent(null,1,"#");
							spider.releaseConnection();
							String content = contents[0].replaceAll("&nbsp;", "").replaceAll("：",":").replaceAll("\\s+","");
							// log.info(content);
							String[] realAddress = RegUtil.getRegContent(content, RegUtil.GOV_HN_LINK_ADDRESS,1,"@");
							address = new String[]{realAddress[0]};
						}
					}
				}
			}
		}
		catch(Exception e ){
			log.info(e.getMessage());
			//e.printStackTrace();
			//return;
		}






		try
		{

			if(address!= null) {
				int idx = address[0].indexOf("&nbsp;");
				if(idx==-1) idx = address[0].indexOf("电话");
				if(idx!=-1) address[0] = address[0].substring(0, idx);
			}
			if( ( address == null  && ipcs == null && depts == null ) || !isEnableOpen) {
				jdbc.updateSql("update tb_dic_noheader set flag=-1 where url='"+srcUrl+"' and flag=0");
				return;
			}
			if(address == null ) address = new String[]{""};
			if(ipcs == null) ipcs = new String[]{""};
			if( depts == null ) depts = new String[]{""};
			if(address[0].trim().length() > 0 || depts[0].trim().length() > 0 ) {
				String sqlValue = sql +"('"+srcUrl+"','"+depts[0]+"','"+address[0]+"')";
				//sqlValue = sqlValue.replace("''","'");
				log.info("sql : "+sqlValue);
				jdbc.update(sqlValue);
				jdbc.updateSql("update tb_dic_noheader set flag=1 where url='"+srcUrl+"' and flag=0");
			}



		}
		catch(Exception e)
		{
			e.printStackTrace();

		}



	}




	public static void main(String[] args) {

		SpiderIPCExt.fetchProvince();

	}

}
