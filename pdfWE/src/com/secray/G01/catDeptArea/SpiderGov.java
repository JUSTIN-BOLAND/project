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
public class SpiderGov {

	public static final Logger log = Logger.getLogger(SpiderGov.class);

	/*
 CREATE TABLE .`tb_gov_cat_area`(
    `id` INT ,
   `cat` VARCHAR(100) ,
   `company` VARCHAR(500) ,
    `address` VARCHAR(500) ,
    `province` VARCHAR(150) ,
   `city` VARCHAR(150) ,
   `county` VARCHAR(260) ,
   `town` VARCHAR(350)

 )
 http://www.xfdzw.com/gov/sf/11.htm

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


			jdbc.truncate("tb_gov_cat_area");


			Spider spider = new Spider(domain+url);
			String[] contents = spider.getPageContent(RegUtil.GOV_PROV_CONTENT,1,"#");
			spider.releaseConnection();
			//log.info(contents[0]);
			String[] provs = RegUtil.getRegContent(contents[0], RegUtil.GOV_CAT,2,"@");
			String[] urls = RegUtil.getRegContent(contents[0], RegUtil.GOV_CAT,1,"@");
			for(int i=0; i< provs.length; i++ ){
				if(urls[i].indexOf("sf") == -1) continue;
				String provName = provs[i].trim().replace("政府机关及事业单位名录","");
				log.info("fetchProvince: "+provName+" : "+province);
				if(provName.equals(province) || "ALL".equals(province.toUpperCase()) ){
					fetchCity(provName,domain,urls[i]);
				}
			}

			jdbc.dropTable("tb_dic_department");
			log.info("fetchProvince : 删除tb_dic_department" );
			String sql="CREATE TABLE tb_dic_department AS\n" +
					"SELECT @rownum:=@rownum+1 rownum, a.*\n" +
					"\tFROM\n" +
					"\t(\n" +
					"\t  SELECT DISTINCT a.province,a.city,a.county,a.company,a.address,a.cat fcat FROM tb_gov_cat_area a \n" +
					"\t) a , (SELECT @rownum:=0) r";
			jdbc.update(sql);
			log.info("fetchProvince : 生成tb_dic_department中间表数据!" );
			jdbc.dropTable("tb_dic_result_gov");
			log.info("fetchProvince : 删除tb_dic_result_gov" );
			sql="CREATE TABLE  tb_dic_result_gov as  SELECT  IFNULL(f_gov_cat(a.company),a.fcat) cat ,a.* FROM tb_dic_department  a ";
			jdbc.update(sql);
			log.info("fetchProvince : 生成tb_dic_result_gov结果表数据!" );
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

	public static void fetchCity(String province,String domain,String url){

		String sql="insert into tb_gov_cat_area(cat,company,address,province,city,county) values";
		Spider spider = new Spider(domain+url);
		String[] contents = spider.getPageContent(RegUtil.GOV_CAT_CONTENT,1,"#");
		spider.releaseConnection();
		//log.info("content ->  " + contents[0]);
		String[] citys = RegUtil.getRegContent(contents[0], RegUtil.GOV_CAT,2,"#");
		String[] cUrls = RegUtil.getRegContent(contents[0], RegUtil.GOV_CAT,1,"#");



		JDBC jdbc =  new JDBC(JDBC.DB);

		try
		{
			jdbc.connect();



			//String delSql = "delete FROM tb_gov_cat_area  where province='"+province+"' ";
			//log.info("fetchCity : "+delSql);
			//jdbc.update(delSql);

			for(int i=0 ; i< citys.length ; i++){
				if(cUrls[i].indexOf("cs") == -1) continue;
				String cityName = citys[i].trim().replace("政府机关及事业单位名录","");
				//取得市级的单位
				spider = new Spider(domain+cUrls[i]);
				String[] pageSizes = spider.getPageContent(RegUtil.GOV_PAGESIZE,2,"@");
				spider.releaseConnection();
				int pageSize = Integer.parseInt(pageSizes[0]);
				//获取单位分页的数据
				for(int j=1 ; j<=pageSize; j++ ){
					String urlSuffix = (j == 1?".htm": "_"+j+".htm");
					String cUrl = domain + cUrls[i].replace(".htm",urlSuffix);
					//log.info("fetchCity: "+cUrl);
					spider = new Spider(cUrl);
					String[] cc = spider.getPageContent(null,1,"@");
					spider.releaseConnection();

					String[] companys = RegUtil.getRegContent(cc[0], RegUtil.GOV_COMPANY,1,"@");
					String[] addresses = RegUtil.getRegContent(cc[0], RegUtil.GOV_COMPANY,2,"@");


					for(int m=0 ; m< companys.length; m++ ){
						String[] cas = addresses[m].trim().split("\\s+-\\s+");
						log.info("fetchCity : "+ addresses[m]+" : "+cas.length);
						//长度为1,有乱码，不处理
						if(cas.length== 1) continue;
						String address = (cas[0].split("：").length==2 ?  cas[0].split("：")[1]: cas[0].split("：")[0]);
						String ncat = (cas[1].split("：").length==2 ?  cas[1].split("：")[1]: cas[1].split("：")[0]);
						String country = address.replace(province+"省","").replace(cityName,"");
						String[] keys={"县","区","市"};
						for( int n=0; n<keys.length; n++) {
							int pos = country.indexOf(keys[n]);
							if(pos > -1) {
								country = country.substring(0,pos+1);
								break;
							}
						}
						String sqlValue = sql +"('"+ncat+"','"+companys[m]+"','"+address+"','"+province+"','"+cityName+"','"+country+"')";
						sqlValue = sqlValue.replace("''","'");
						log.info(sqlValue);
						jdbc.update(sqlValue);
						//log.info("fetchCity : "+province+" : "+cityName+" : "+country+ " : " +companys[m]/*+" : "+addresses[m]*/+" : "+address+" : "+ncat);
					}
				}


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
		if (args.length != 1) return;

		if (args[0].trim().length() > 0) {
			String domain = "http://www.xfdzw.com";
			String url = "";
			log.info("SpiderGov : " + args[0].trim());
			SpiderGov.fetchProvince(args[0].trim(), domain, url);

		}
	}

}
