package com.secray.G01.catDeptArea;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.CommonUtil;
import com.secray.utils.common.RegUtil;
import com.secray.utils.http.IPUtil;
import com.secray.utils.http.Spider;

/**
 * 类  名: SpiderIPC
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年3月16日 下午5:42:23
 *
 *  CREATE TABLE tb_dic_ipc(
 deptName VARCHAR(300),
 deptType VARCHAR(300),
 ipc VARCHAR(300),
 website VARCHAR(300),
 url VARCHAR(300),
 srcTitle VARCHAR(300)
 ) ENGINE=MyISAM DEFAULT CHARSET=utf8;


 SELECT m.url,m.title,n.deptName,n.website FROM tb_dic_web_title_flag m,tb_dic_ipc n
 WHERE m.id = n.srcTitle
 //查询域名失效时间
 http://tool.chinaz.com/DomainDel/?wd=163.com
 */
public class SpiderIPC {

	public static final Logger log = Logger.getLogger(SpiderIPC.class);

	public static void fetchIPCMain(){
		String tableName="tbc_dic_url_count";  //tb_dic_web_title_flag
		JDBC jdbc =  new JDBC(JDBC.HF);
		JDBC uJdbc =  new JDBC(JDBC.HF);
		ResultSet rs  = null;
		String sql="SELECT REPLACE(REPLACE(REPLACE(a.url,'http://',''),'admin.',''),'2012.','') furl,a.url srcUrl,a.url id  FROM "+tableName+" a where flag_num=0 and flag_mark=0";
		String uSql="update "+tableName+" set flag_mark=1  where url=";
		String cntSql="select count(1) from "+tableName+" where flag_num=0 and flag_mark=1";
		String ipUrl = null;
		String url= null;
		String srcUrl = null;
		String id = null;
		Spider spider =  null;
		int cnt = 0;
		try
		{
			jdbc.connect();

			cnt = jdbc.queryForInt(cntSql);
			if( cnt ==0 ) {
				jdbc.truncate("tb_dic_ipc");
				log.info("["+cnt+"] 【等于】零，【删除】数据! ");
			}
			else log.info("["+cnt+"] 【大于】零，【不】删除数据! ");

			//jdbc.fetchStatement();
			rs = jdbc.query(sql);
			log.info("querysql : "+sql);
			uJdbc.connect();

			while(rs.next()){
				ipUrl = rs.getString(1);
				srcUrl =  rs.getString(2);
				id =  rs.getString(3);
				String updateSql = uSql+"'"+id+"'";
				log.info(ipUrl+" -> "+srcUrl);
				//continue;
				if(CommonUtil.isIP(ipUrl)){
					ipUrl = IPUtil.getRemoteHostName(ipUrl);
				}
				if(CommonUtil.isIP(ipUrl) || "UnKnown".equals(ipUrl)) {
					jdbc.updateSql(updateSql);
					continue;
				}
				//处理原始URL
				url="http://www.beianbeian.com/s?keytype=0&q="+ipUrl;
				log.info("genUrl: "+url);
				try
				{
					spider = new Spider(url,Spider.POST);
					spider.releaseConnection();
				}
				catch(Exception e ){
					spider = new Spider(url,Spider.POST);
					spider.releaseConnection();
				}
				url="http://www.beianbeian.com/search/"+ipUrl;
				log.info("getUrl: "+url);
				String result = fetchIPC(uJdbc,url,id);
				//如果获取不到结果，则去掉二级域名(第一个.之前的内容)，在次获取一级域名
 					/*if( result == null ) {
	 					String sUrl = ipUrl.substring(ipUrl.indexOf(".")+1, ipUrl.length() );
	 					url="http://www.beianbeian.com/s?keytype=0&q="+sUrl;
	 					log.info("genUrl: "+url);
	 					try
	 					{
		 					spider = new Spider(url,Spider.POST);
		 					spider.releaseConnection();
	 					}
	 					catch(Exception e ){
	 						spider = new Spider(url,Spider.POST);
		 					spider.releaseConnection();
	 					}
	 					url="http://www.beianbeian.com/search/"+sUrl;
	 					log.info("getUrl: "+url);
	 					fetchIPC(uJdbc,url,id);
 					}*/
				uJdbc.updateSql(updateSql);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();

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

	public static String fetchIPC(JDBC jdbc,String url,String id){
		String iSql="insert into tb_dic_ipc values(";
		Spider spider = null;
		String[] contents = null;
		try
		{
			spider = new Spider(url,Spider.POST);
			contents = spider.getPageContent(RegUtil.IPC_CONTENT,2,"#");
			spider.releaseConnection();
		}
		catch(Exception e ){
			spider = new Spider(url,Spider.POST);
			contents = spider.getPageContent(RegUtil.IPC_CONTENT,2,"#");
			spider.releaseConnection();
		}
		if(contents== null  ) {

			return null;
		}
		else {
			if(contents[0]==null || contents[0].trim().length() ==0) return null;
			String[] ipcs = RegUtil.getRegContent(contents[0], RegUtil.AA, 2, "@");
			String[] ipc_company = RegUtil.getRegContent(contents[0], RegUtil.DIV, 2, "@");
			String[] ipc_net = RegUtil.getRegContent(contents[0], RegUtil.IPC_NET, 1, "@");
			if(ipcs != null && ipc_company!=null && ipc_net!=null ) {
					/* if("http://admin.qxzf.gov.cn".equals(srcUrl)){
						 log.info("sdfs");
					 }*/
				if( ipc_company[0].indexOf("<div>") > -1) {
					ipc_company[0]="";

					ipcs[3] =  ipcs[2];
					ipcs[3] = ipcs[3].replace("</em>","");
					ipcs[3]="-1";
				}
				String sql = iSql+"'"+ipc_company[0]+"','"+ipc_company[1]+"','"+ipcs[0]+"','"+ipc_net[0].trim()+"','"+ipcs[3].replace("<em>", "")+"','"+id+"',null,null,null)";
				log.info("result : " +sql);
				try{
					jdbc.update(sql);
				}
				catch(SQLException sqle){
					//sqle.printStackTrace();
				}

				return "1";
			}
			else return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpiderIPC.fetchIPCMain();
	}

}
