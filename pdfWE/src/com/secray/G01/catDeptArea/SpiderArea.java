package com.secray.G01.catDeptArea;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.RegUtil;
import com.secray.utils.http.Spider;

/**
 * 类  名: SpiderArea
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年3月3日 上午11:42:38
 *
 */
public class SpiderArea {

	public static final Logger log = Logger.getLogger(SpiderArea.class);

	/**
	 CREATE TABLE `tb_dic_tmp_area`(
	 `area_id` VARCHAR(50) ,
	 `area_name` VARCHAR(100) ,
	 `parent_id` VARCHAR(50)

	 )
	 */
	public static void fetchProvince(String province,String url){

		Spider spider = new Spider(url);
		String[] contents = spider.getPageContent(null,1,"#");
		spider.releaseConnection();
		String[] topUrls = RegUtil.getRegContent(contents[0],RegUtil.AREA_FIRSTURL,1,"#");
		String[] areaContents =  RegUtil.getRegContent(contents[0],RegUtil.AREA_PROVINCE,1,"#");
		areaContents[0] = areaContents[0].substring(0,areaContents[0].indexOf("</div>"));
		//contents[0] = contents[0].substring(0,  contents[0].indexOf("</div>"));

		String[] provinces = RegUtil.getRegContent(areaContents[0], RegUtil.A, 4, "@");
		String[] urls = RegUtil.getRegContent(areaContents[0], RegUtil.A, 2, "@");
		JDBC jdbc =  new JDBC(JDBC.DB);
		String trimProv ;
		String sql="insert into tb_dic_tmp_area values(";
		String vSql;
		String id;
		try
		{
			jdbc.connect();
			jdbc.truncate("tb_dic_tmp_area");

			for(int i = 0; i< provinces.length ; i++){
				if(provinces[i].indexOf("洲") > -1 )  continue;
				trimProv = provinces[i].replaceAll("\\s+","").replace("　","");
				if(trimProv.indexOf("香港")> -1 ||trimProv.indexOf("澳门")> -1 || trimProv.indexOf("台湾")> -1 ) continue;

				if(trimProv.equals(province.trim()) || "ALL".equals(province.trim().toUpperCase())){
					id = i > 9 ? "0"+(i+1) : "00"+(i+1);
					vSql="'"+id+"','"+trimProv+"','-1')";
					log.info("fetchProvince: "+ trimProv +" : "+urls[i]+" : \n "+sql+vSql);
					jdbc.update(sql+vSql);
					SpiderArea.fetchCtiy(jdbc,id,trimProv,url+urls[i]);
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

	public static void fetchCtiy(JDBC jdbc,String pid,String province,String url) throws SQLException
	{

		Spider spider = new Spider(url);
		String[] contents = spider.getPageContent(RegUtil.AREA_CITY_CONTENT,1,"#");
		contents[0] = contents[0].substring(0, contents[0].indexOf("</div>"));
		spider.releaseConnection();
		String id,cId;
		String[] citys = RegUtil.getRegContent(contents[0],RegUtil.UL,2,"@");
		String[] countrys = null;
		String[] cityNames = null;
		String[] cUrls = null;
		String sql="insert into tb_dic_tmp_area values(";
		String vSql;
		for(int i = 0; i< citys.length ; i++){
			if( i == 0 ) continue;
			countrys = RegUtil.getRegContent(citys[i],RegUtil.AREA_COUNTRY,3,"#");
			cUrls = RegUtil.getRegContent(citys[i],RegUtil.AREA_COUNTRY,1,"#");
			cityNames = RegUtil.getRegContent(citys[i],RegUtil.AREA_CITY_NAME,2,"#");

			if(cityNames  == null )  {
				cityNames = RegUtil.getRegContent(citys[i],RegUtil.AREA_CITY_NAME_OTHERS,2,"#");
				if(cityNames  == null ) {
					log.info("fetchCtiy[city is null]: "+province);
					continue;
				}
			}

			String[] provCitys = RegUtil.getRegContent(citys[i],RegUtil.A,4,"#");
			if(cityNames[0].equals("省直辖")) cityNames[0] =  provCitys[1];
			id = pid+(i > 9 ? "0"+(i) : "00"+(i));
			vSql="'"+id+"','"+cityNames[0]+"','"+pid+"')";
			log.info("fetchCtiy[city]: "+sql+vSql);
			jdbc.update(sql+vSql);
			if(countrys==null){
				log.info("sadfd");
				continue;
			}
			for(int j = 0; j < countrys.length ; j++){
				if( j < 2 ) continue;

				cId = id+( (j-1) > 9 ? "0"+(j-1) : "00"+(j-2));
				vSql="'"+cId+"','"+countrys[j]+"','"+id+"')";



				log.info("fetchCtiy[country]: "+sql+vSql);
				jdbc.update(sql+vSql);
			}


			//				/}
		}
		/*JDBC jdbc =  new JDBC(JDBC.DB);
 		try
 		{
 			//jdbc.connect();
 			//jdbc.truncate("tb_dic_tmp_area");
 			for(int i = 0; i< citys.length ; i++){
 				countrys = RegUtil.getRegContent(citys[i],RegUtil.LI,1,"#");

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

 		}*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=1 ) return;


		if(args[0].trim().length() >0 ){

			String url = "http://www.xzqh.org/html/";
			SpiderArea.fetchProvince(args[0].trim(),url);
		}

	}

}
