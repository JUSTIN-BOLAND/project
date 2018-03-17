package com.secray.G01.catDeptArea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.ChineseToEnglish;
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
public class SpiderGovExt {

	public static final Logger log = Logger.getLogger(SpiderGovExt.class);

	/*
 CREATE TABLE .`tb_gov_catalog`(
    `id` INT ,
   `cat` VARCHAR(100) ,
   `company` VARCHAR(500) ,
    `address` VARCHAR(500) ,
    `province` VARCHAR(150) ,
   `city` VARCHAR(150) ,
   `county` VARCHAR(260) ,
   `ipc` VARCHAR(350)

 )

 CREATE TABLE tb_dic_department AS
SELECT @rownum:=@rownum+1 rownum, a.*
	FROM
	(
	  SELECT DISTINCT a.province,a.city,a.county,a.company,a.address  FROM tb_gov_cat_area a
	) a , (SELECT @rownum:=0) r
	 */
	public static void fetchProvince(String province,String domain,String url){
		JDBC jdbc =  new JDBC(JDBC.DB);

		try
		{
			jdbc.connect();


			jdbc.truncate("tb_gov_catalog");


			Spider spider = new Spider(domain+url);
			String[] contents = spider.getPageContent(RegUtil.GOV_HN_CONTENT,2,",");
			spider.releaseConnection();
			String[] websites = null;
			String[] urls = null;
			for(int i=0; i< contents.length; i++ ){
				if(contents[i].indexOf("省政府部门") > -1
						|| contents[i].indexOf("省直有关单位") > -1 || contents[i].indexOf("辖市") > -1)
				{
					//log.info("fetchProvince: "+contents[i]+" : "+province);
					websites = RegUtil.getRegContent(contents[i], RegUtil.GOV_HN_WEBSITE, 2, ",");
					urls = RegUtil.getRegContent(contents[i], RegUtil.GOV_HN_WEBSITE,1, ",");
					for(int j=0; j< websites.length; j++ ){
						if(websites[j].indexOf("省政府部门") > -1
								|| websites[j].indexOf("省直有关单位") > -1 || websites[j].indexOf("辖市") > -1)
						{
							continue;
						}
						String city="";
						if(websites[j].indexOf("市") > -1 && websites[j].length() <5) {
							city = websites[j];
							websites[j] = websites[j]+"政府网站";
						}
						log.info("fetchProvince["+i+"]: "+websites[j]+" -> "+urls[j]);
						fetchCity(city,websites[j],urls[j]);
					}

				}
			}


			log.info("fetchProvince : 生成tb_gov_catalog结果表数据!" );
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{

			try{
				if(jdbc!=null) jdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}
	}

	public static void fetchCity(String provice,String domain,String url){

		String sql="insert into tb_gov_catalog(cat,company,address,province,city,county,ipc) values";
		String[] ipcs =  null;
		String[] address = null;
		try
		{
			Spider spider = new Spider(url);
			String[] contents = spider.getPageContent(null,1,"#");
			spider.releaseConnection();
			//log.info("content ->  " + contents[0]);
			//String[] depts = RegUtil.getRegContent(contents[0], RegUtil.TITLE,2,"#");
			ipcs =  RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_IPC,1,"@");
			address = RegUtil.getRegContent(contents[0], RegUtil.GOV_HN_ADDRESS,1,"#");
		}
		catch(Exception e ){

		}
		if(address == null ) address = new String[]{""};
		if(ipcs == null) ipcs = new String[]{""};
		//if( depts == null ) depts = new String[]{domain};



		JDBC jdbc =  new JDBC(JDBC.DB);

		try
		{
			jdbc.connect();



			if(address[0].trim().length() > 0 || ipcs[0].trim().length() > 0 ) {
				String sqlValue = sql +"('政府网站','"+domain+"','"+address[0]+"','河南','"+provice+"','"+url.replace("&nbsp;", "")+"','"+ipcs[0]+"')";
				//sqlValue = sqlValue.replace("''","'");
				log.info(sqlValue);
				jdbc.update(sqlValue);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{

			try{
				if(jdbc!=null) jdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}


	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	if (args.length != 1) return;

		if (args[0].trim().length() > 0) {*/
		String domain = "";
		String url = "http://www.henan.gov.cn/";
		//log.info("SpiderGovExt : " + args[0].trim());
		SpiderGovExt.fetchProvince("河南", domain, url);

	/*	}*/
	}

}
