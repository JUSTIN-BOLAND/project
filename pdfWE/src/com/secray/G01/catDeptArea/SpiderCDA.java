package com.secray.G01.catDeptArea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.ArrayUtil;
import com.secray.utils.common.ChineseToEnglish;
import com.secray.utils.common.RegUtil;
import com.secray.utils.common.StrUtil;
import com.secray.utils.http.Spider;

/**
 * 类  名: SpiderCDA
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月23日 上午11:19:38
 *
 */
public class SpiderCDA {

	public static final Logger log = Logger.getLogger(SpiderCDA.class);

	/*
	 * CREATE TABLE `tb_dic_tmp_company_cat`(
    `id` INT ,
   `cat` VARCHAR(100) ,
   `scat` VARCHAR(100) ,
   `tcat` VARCHAR(200) ,
   `fcat` VARCHAR(200) ,
   `furl` VARCHAR(500) ,
    province VARCHAR(100),
    flag int

 )
	 */
	public static void fetchProvince(String province,String url){

		Spider spider = new Spider(url);

		String[] contents = spider.getPageContent(RegUtil.COM_PROVINCE,1,"#");
		contents[0] = contents[0].substring(0,  contents[0].indexOf("</div>"));
		spider.releaseConnection();
		String[] privinces = RegUtil.getRegContent(contents[0], RegUtil.A, 4, "@");
		String[] urls = RegUtil.getRegContent(contents[0], RegUtil.A, 2, "@");
		JDBC jdbc =  new JDBC(JDBC.DB);

		try
		{
			jdbc.connect();
			jdbc.truncate("tb_dic_tmp_company_cat");

			for(int i = 0; i< privinces.length ; i++){
				if(privinces[i].trim().equals(province.trim()) || "ALL".equals(province.trim().toUpperCase())){
					log.info("fetchProvince: "+ privinces[i]+" : "+urls[i]);
					SpiderCDA.fetchCat( privinces[i],urls[i]);
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
	public static void fetchCat(String province,String url){

		String sql="insert into tb_dic_tmp_company_cat(cat,scat,tcat,fcat,furl,province,flag) values";
		Spider spider = new Spider(url);
		//String[] hc = spider.getPageContent(null,1,"#");
		String[] contents = spider.getPageContent(RegUtil.CAT_CONTENT,1,"#");
		spider.releaseConnection();
		//log.info("content ->  " + contents[0]);
		String[] cats = RegUtil.getRegContent(contents[0], RegUtil.CAT,2,"#");
		String[] scatIds = RegUtil.getRegContent(contents[0], RegUtil.SCAT_ID,2,"#");
		String scatContentReg = null;
		ArrayList<String> tfs = null;
		String[] tfCat;
		for(int i=0 ; i< scatIds.length ; i++ )  scatIds[i] = scatIds[i].split(",")[1].replaceAll("[^0-9]", "");
		JDBC jdbc =  new JDBC(JDBC.DB);

		try
		{
			jdbc.connect();



 			/*String delSql = "delete FROM tb_dept_cat_area  where province='"+province+"' ";
 			log.info(delSql);
 			jdbc.update(delSql);*/

			for(int i=0 ; i< cats.length ; i++){
				// if(i >= 2 ) continue;

				scatContentReg="<div\\s+class=[\"|']tag_tx[\"|']\\s+id=[\"|']subcatlisting_"+(10+Integer.parseInt(scatIds[i]))+"[\"|'].*?>([\\s\\S]*)</div>";   //二级分类主分类
				String[] scatContents = RegUtil.getRegContent(contents[0], scatContentReg,1,"#");
				scatContents[0] = scatContents[0].substring(0, scatContents[0].indexOf("</div>"));
				//获取二级分类的url和名称
				String[] scatUrls = RegUtil.getRegContent(scatContents[0], RegUtil.SCAT,1,"#");
				String[] scats = RegUtil.getRegContent(scatContents[0], RegUtil.SCAT,3,"#");

				for(int j=0 ; j< scats.length ; j++ ){
					// if( j!= 0 ) continue;

					log.info("firstcat :[ "+cats[i]+" : "+scats[j]+"]-> scat: "+scatIds[i]+" : "+scatUrls[j]);
					tfs = fetchThreeFourCat(scatUrls[j],1);

					if(tfs == null || tfs.size() == 0 ) continue;
					for(String tf : tfs){
						tfCat = tf.split(";");

						String[] tcats = tfCat[0].split(",");
						String[] fcats ={" ",tcats[1]};
						if(tfCat.length<2){
							//log.info("src[tf]-> first: "+cats[i]+" : second: "+scats[j]+" : "+tcats[0]+" : "+tf);
						}
						else {
							fcats = tfCat[1].split(",");
						}
						String sqlValue = "('"+cats[i]+"','"+scats[j]+"','"+tcats[0]+"','"+fcats[0]+"','"+fcats[1]+"','"+province+"',0)";
						sqlValue = sqlValue.replace("''", "");
						String cSql= sql+sqlValue;

						log.info("执行sql : "+cSql );
						jdbc.update(cSql);

					}
				}
				//jdbc.commit();
				scatUrls = null;
				scats = null;
				scatContents = null;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
 			/*try
 			{
 				jdbc.rollback();
 			}
 			catch(SQLException sqle)
 			{
 				sqle.printStackTrace();
 			}*/
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
	public static ArrayList fetchThreeFourCat(String url,int level){
		ArrayList<String> result = new ArrayList<String>();
		Spider spider = new Spider(url);
		String[] tcontents = spider.getPageContent(null,1,"#");
		spider.releaseConnection();
		//log.info("fetchThreeFourCat: "+ tcontents[0]);
		String[] tcatUrls = RegUtil.getRegContent(tcontents[0], RegUtil.TCAT,1,"#");
		String[] tcats = RegUtil.getRegContent(tcontents[0], RegUtil.TCAT,3,"#");
		int lvl = level+1;

		ArrayList<String> fcats;
		if(tcats !=null) {
			for(int m=0 ; m< tcats.length ; m++){
				boolean isHaveSubCat = true;
				if("河南商业专用设备公司".equals(tcats[m])){
					//log.info("lb");
				}
				if(isHaveSubCat) {
					fcats = fetchThreeFourCat(tcatUrls[m],lvl);
					 /* String[] fcats =  (String[])fList.toArray(new String[fList.size()]);
					  if(result.size() == 0 ) fcats = null;*/
					if(level ==1 ){
						// log.info("level 1");
					}
					if(fcats != null && fcats.size()>0 ) {
						for(String fcat : fcats){
							//tcats[m] = tcats[m]+","+tcatUrls[m]+";"+fcat;
							result.add(tcats[m]+","+tcatUrls[m]+";"+fcat);
							//log.info("fetchThreeFourCat:["+level+"] "+ tcats[m]+","+tcatUrls[m]+";"+fcat);
						}
					}
					else {
						isHaveSubCat = false;
						//tcats[m] = tcats[m]+","+tcatUrls[m];
						result.add( tcats[m]+","+tcatUrls[m]);
						//log.info("fetchThreeFourCat[null : ["+level+"]: "+  tcats[m]+","+tcatUrls[m]);
					}
					fcats = null;
				}
				else
				{

					//						  /tcats[m] = tcats[m]+","+tcatUrls[m];
					result.add(tcats[m]+","+tcatUrls[m]);
					//log.info("fetchThreeFourCat[null : ["+level+"]: "+ tcats[m]);

				}

			}
		}

		tcontents = null;
		tcatUrls = null;
		tcats = null;
		return result;
	}

	/*
CREATE TABLE `tb_dic_tmp_company`(
	`cat_id` INT ,

   `company` VARCHAR(500) ,
   `adress` VARCHAR(500) ,
   `province` VARCHAR(150) ,
   `city` VARCHAR(150) ,
   `county` VARCHAR(260) ,
   `town` VARCHAR(350)
 )
	 */
	public static  void fetchCompany(String province){

		JDBC jdbc =  new JDBC(JDBC.DB);
		JDBC uJdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;
		String sql="SELECT a.id,a.furla,province  FROM tb_dic_tmp_company_cat a where province='"+province+"' and flag=0 order by a.id ";
		if("ALL".equals(province.trim().toUpperCase())){
			sql="SELECT a.id,a.furl,province  FROM tb_dic_tmp_company_cat a where  flag=0 order by a.id ";
		}
		int id=0;
		try
		{
			jdbc.connect();
			//jdbc.truncate("tb_dic_tmp_company");

			rs = jdbc.query(sql);
			uJdbc.connect();
			while(rs.next()){
				id = rs.getInt(1);
				String url = rs.getString(2);
				fetchDetailMain(url/*,uJdbc*/,id,rs.getString(3));
				uJdbc.update("update tb_dic_tmp_company_cat set flag=1 where id="+id);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			try{
				uJdbc.update("update tb_dic_tmp_company_cat set flag=-1 where id="+id);


			}
			catch(SQLException sqle){ sqle.printStackTrace(); }
		}
		finally
		{

			try{
				if(rs!=null) rs.close();
				if(jdbc!=null) jdbc.close();
				if(uJdbc!=null) uJdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}
	}
	public static  void fetchDetailMain(String url,/*JDBC jdbc,*/int id,String province){
		Spider spider = new Spider(url);
		if(spider.getStatusCode() == HttpStatus.SC_EXPECTATION_FAILED) {
			spider = new Spider(url);
		}
		String[] rows = spider.getPageContent(RegUtil.DETAIL_PAGESIZE,1,"@");
		if(rows==null) {
			log.info("url : "+url);
			rows = new String[]{"0","0"};
		}
		log.info("rows: "+rows[0]);
		int pageCnt = Integer.parseInt(rows[0]) / 20 ;
		if( Integer.parseInt(rows[0]) % 20 !=0 ) pageCnt++;
		spider.releaseConnection();
		String insertSql="insert into tb_dic_tmp_company values";
		String[] companyAddress;

		JDBC jdbc =  new JDBC(JDBC.DB);
		try
		{
			jdbc.connect();
			for( int a=1; a<=pageCnt; a++){
				rows = fetchDetail(url+"pn"+a+"/");
				//在这插入数据库
				for(String row: rows){
					log.info("fetchDetailMain[row : "+id+" : "+a+"] : "+row);
					companyAddress = StrUtil.split(row, ","," ");//row.split(",");
					String[] cityTowns = {" "," "};
					if(companyAddress.length ==2 && companyAddress[1].trim().length() > 0 ){
						cityTowns = SpiderCDA.getAreas(companyAddress[1], companyAddress[0], province);
					}
					//companyAddress = ArrayUtil.replace(companyAddress, "''", "'");
					//cityTowns = ArrayUtil.replace(cityTowns, "''", "'");
					String companySql = insertSql+ "("+id+",'"+companyAddress[0]+"','"+companyAddress[1]+"','"+province+"','"+cityTowns[0]+"','"+cityTowns[1]+"',0)";
					companySql = companySql.replace("'',", "',").replace(",''", ",'");
					log.info("fetchDetailMain [sql :  "+a+"] : " + companySql);
					jdbc.update(companySql);
				}
			}
		}
		catch(Exception sqle){
			sqle.printStackTrace();
			//log.error("fetchDetailMain [error :  "+id+"] : " + companySql);
		}
		finally
		{
			try{

				if(jdbc!=null) jdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;
			companyAddress = null;
			spider = null;
			rows = null;
		}

	}
	public static  String[] fetchDetail(String url){
		Spider spider = new Spider(url);
		String[] detailContents = spider.getPageContent(RegUtil.DETAIL_CONTENT,3,"@");
		spider.releaseConnection();
		String[] result = new String[detailContents.length];
		int i=0;
		for(String detailContent : detailContents) {
			result[i] = (RegUtil.getRegContent(detailContent, RegUtil.DETAIL_COMPANY,2,"@") == null ? "":RegUtil.getRegContent(detailContent, RegUtil.DETAIL_COMPANY,2,"@")[0])+
					","+(RegUtil.getRegContent(detailContent, RegUtil.DETAIL_ADDRESS,1,"@") == null ? "":RegUtil.getRegContent(detailContent, RegUtil.DETAIL_ADDRESS,1,"@")[0]);
			log.info("fetchDetail : "+result[i]);
			i++;
		}

		detailContents = null;
		return result;
	}

	public static String[] getNeedCityTown(String src/*,String cityTown*/,int areaType){
		log.info("getNeedCityTown [src]: "+src);
		String[] addressReal ={"",""};
		String[] keys= null;
		String areaStr = "郑州,开封,平顶山,洛阳,商丘,安阳,新乡,许昌,鹤壁,焦作,濮阳,漯河,三门峡,周口,驻马店,南阳,信阳,济源";
		switch(areaType){
			case 1: //市
				keys=areaStr.split(",");
				break;
			case 2: //县
				keys=new String[] {"区","县","市"};
		}
		int pos=-1;
		for( int n=0; n<keys.length; n++) {
			pos = src.indexOf(keys[n]);
			//地市直接从18个地市中查找
			if(pos > -1) {
				if(areaType==1 )     	addressReal[0] = keys[n];
				else
				{
					int begin = src.indexOf("市");

					if(begin > -1 && begin < pos
	            			/*&& src.indexOf("市委") ==-1 &&  src.indexOf("市政")   ==-1
	            			&&  src.indexOf("市人大") ==-1 &&  src.indexOf("市内") ==-1
	            			&&  src.indexOf("市外") ==-1 &&  src.indexOf("市公") ==-1
	            			&&  src.indexOf("市医") ==-1 &&  src.indexOf("市场") ==-1*/
							){
						int len = src.length();
						if(begin == -1 || begin == len -1 ) begin = 0;
						else begin++;
					}
					else begin = 0;

					if(begin >0 ) addressReal[0] = src.substring(0,begin+1);
					addressReal[1] = src.substring(begin,pos+1);

					if(areaStr.indexOf(addressReal[1].replace("市", "")) > -1){
						addressReal[0] = src.substring(0,pos+1);
						addressReal[1] ="";
					}
				}
				break;
			}

		}
		/*if(areaStr.indexOf(cityTown.replace("市", "")) > -1){
			addressReal[0] = cityTown;
		}
		else{
			addressReal[1] =cityTown;
		}*/
		if(addressReal[1].trim().length() > 0) {
			areaStr = "河南,"+areaStr;
			String[] filters = areaStr.split(",");
			String areaLvel = "省";
			String areaReg = null;
			for( int i = 0; i< filters.length ; i++){
				if(i>0) areaLvel="市";
				//areaReg = "["+filters[i]+"|"+filters[i]+areaLvel+"]";
				if( addressReal[1].indexOf(filters[i]+"县") == -1 ){
					addressReal[1] = addressReal[1].replace(filters[i],"").replace(filters[i]+areaLvel, "");//.replaceAll(areaReg, "");
				}
			}
			if(addressReal[1].indexOf("市") > 6 || addressReal[1].indexOf("市") ==1 || addressReal[1].indexOf("市") ==0){
				addressReal[1]="";
			}
		}
		return addressReal;
	}
	public static String[] getAreas(String address,String company,String province){
		String[] result = {" "," "};
		if( address != null && address.trim().length() > 0){

			String[] ads = StrUtil.split(address, "\\s+");
			String addressNoProv = "";


			//如果地址没有空格分割
			if( ads.length == 1 ){
				addressNoProv = ads[0].replaceAll("["+province+"|"+province+"省]", "");


			}
			else
			{
				/*for(int i=2 ; i< ads.length ; i++){
					if(addressNoProv.trim().length()>0) addressNoProv += ",";
				    addressNoProv += ads[i].replaceAll("["+province+"|"+province+"省]", "");
				}*/
				addressNoProv += ads[1].replaceAll("["+province+"|"+province+"省]", "");
			}
			String city = getNeedCityTown(addressNoProv,1)[0];
			if(city==null || city.trim().length() ==0 ){
				city = getNeedCityTown(company,1)[0];
				if(city==null || city.trim().length() ==0 ){
					if(ads.length == 3 ) city = getNeedCityTown(ads[2],1)[0];
				}
			}
			String town = getNeedCityTown(addressNoProv,2)[1];
			if(town==null || town.trim().length() ==0 ){
				town = getNeedCityTown(company,2)[1];
				if(town==null || town.trim().length() ==0 ){
					if(ads.length == 3 ){
						String[] cts = getNeedCityTown(ads[2],2);
						if(cts.length == 2 ) town = cts[1];
					}
				}
			}

			result[0] = (city == null || city.trim().length() ==0  ? " ": city);
			result[1] = (town == null || town.trim().length() ==0  ? " ": town);

		}
		log.info(" getAreas : "+result[0]+" : "+result[1]);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=2 ) return;

		if("CAT".equals(args[0].toUpperCase())){

			if(args[1].trim().length() >0 ){
				/*String spell = ChineseToEnglish.getFullSpell(args[1].trim());
				String url = "http://b2b.huangye88.com/"+spell+"/";
				log.info("spiderCDA : "+ args[1].trim()+" : "+ spell +" : \n " + url );
				SpiderCDA.fetchCat(args[1].trim(),url);*/
				String url = "http://b2b.huangye88.com/";
				SpiderCDA.fetchProvince(args[1].trim(),url);
			}
		}
		else if("COMPANY".equals(args[0].toUpperCase())){
			SpiderCDA.fetchCompany(args[1].trim());
		}

		/*
		url = "http://b2b.huangye88.com/henan/jixie/";
		//url="http://b2b.huangye88.com/henan/zhiye/";
		//url="http://b2b.huangye88.com/henan/ganzaoshebei164/";

		ArrayList<String> scats = SpiderCDA.fetchThreeFourCat(url,1);
		for(String scat :scats ){
			System.out.println("spider-> " + scat);
		}

		url="http://b2b.huangye88.com/henan/wenduyibiao31/";
		//SpiderCDA.fetchDetailMain(url);

		 */
	}

}
