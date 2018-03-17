package com.secray.utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.secray.utils.common.RegUtil;


public class MigrationUtil {
	public static final Logger log = Logger.getLogger(MigrationUtil.class);

	public static void migrationTable(boolean isOnlyTabStruct,String tableNames){
		Properties srcProp = new Properties();
		srcProp.setProperty("jdbc.db.Driver","com.mysql.jdbc.Driver");
		srcProp.setProperty("jdbc.db.URL","jdbc:mysql://192.168.12.125:3306/gov01_v3?useUnicode=true&characterEncoding=utf-8");
		srcProp.setProperty("jdbc.db.userName","root");
		srcProp.setProperty("jdbc.db.password","123");
		Properties destProp = new Properties();

		destProp.setProperty("jdbc.db.Driver","com.mysql.jdbc.Driver");
		destProp.setProperty("jdbc.db.URL","jdbc:mysql://192.168.12.12:3306/G01?useUnicode=true&characterEncoding=utf-8");
		destProp.setProperty("jdbc.db.userName","root");
		destProp.setProperty("jdbc.db.password","123456");
		JDBC srcJdbc =  new JDBC(srcProp);
		JDBC destJdbc =  new JDBC(destProp);

		ResultSet rs = null;
		ResultSet trs = null;
		Statement tstatement = null;
		String sql;
		String tabName;

		String whereSql = (isOnlyTabStruct ? " where 1=2": "" );
		try
		{
			tstatement = srcJdbc.fetchConnect();
			rs = srcJdbc.getTables(tableNames);
			destJdbc.fetchConnect();

			while(rs.next()){
				int rows = 0;
				tabName = rs.getString("TABLE_NAME").trim();
				//System.out.println("asfd: " + destJdbc.getTableSql(tabName));
				log.info("migrationTable: 迁移数据库 ["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"] 下的表 ["+tabName+"] -> " +
						"["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"]");
				if( destJdbc.isTableExist(tabName))  {
					//destJdbc.dropTable(tabName);
					destJdbc.truncate(tabName);
				}
				else
				{
					String tableStructSql = srcJdbc.getTableStruct(tabName);
					destJdbc.update(tableStructSql);
				}
				trs = tstatement.executeQuery("select * from "+ tabName);
				while(trs.next()){
					//sql = "create table "+ tabName + "as select * from " + tabName +  whereSql;
					sql = ""  ;
					for(int i=1;i<=trs.getMetaData().getColumnCount();i++)
					{
						String colType = trs.getMetaData().getColumnTypeName(i);
						//String colName = trs.getMetaData().getColumnName(i).toUpperCase();

						if(sql.trim().length() > 0 ) sql +=",";
						if(colType.indexOf("INT")>0) {

							sql +=  trs.getInt(i);
						}
						else
							sql +=  "'"+trs.getString(i)+"'";

					}
					sql =  "insert into  "+ tabName + " values( "+sql+")";
					log.info("migrationTable :"+sql);
					destJdbc.update(sql);
					rows++;
				}
				log.info("migrationTable: 迁移数据库 ["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"] 下的表 ["+tabName+"] -> " +
						"["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"] 结束!工有["+rows+"] 行!");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{

			try{
				if(rs != null ) rs.close();
				if(trs != null ) trs.close();
				if(tstatement != null ) tstatement.close();
				if(srcJdbc!=null) srcJdbc.close();
				if(destJdbc!=null) destJdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			srcJdbc = null;

		}
	}

	public static void migrationDB(boolean isOnlyTabStruct){

		JDBC srcJdbc =  new JDBC(JDBC.DB);
		JDBC srcJdbc1 =  new JDBC(JDBC.DB);
		JDBC destJdbc =  new JDBC(JDBC.HF);

		ResultSet rs = null;
		ResultSet trs = null;
		Statement tstatement = null;
		String sql;
		String tabName;

		String whereSql = (isOnlyTabStruct ? " where 1=2": "" );
		try
		{
			 srcJdbc.connect();
			tstatement = srcJdbc.getConnection().createStatement();
			rs = srcJdbc.getTables("");
			destJdbc.connect();
			srcJdbc1.connect();

			while(rs.next()){
				int rows = 0;
				tabName = rs.getString("TABLE_NAME").trim();
				//System.out.println("asfd: " + destJdbc.getTableSql(tabName));
				log.info("migrationTable: 迁移数据库 ["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"] 下的表 ["+tabName+"] -> " +
						"["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"]");
				if( destJdbc.isTableExist(tabName))  {
					//destJdbc.dropTable(tabName);
					destJdbc.dropTable(tabName);
				}
				String tableStructSql = srcJdbc1.getTableStruct(tabName);
				log.info(tableStructSql);
				destJdbc.update(tableStructSql);
				trs = tstatement.executeQuery("select * from "+ tabName);
				while(trs.next()){
					//sql = "create table "+ tabName + "as select * from " + tabName +  whereSql;
					sql = ""  ;
					for(int i=1;i<=trs.getMetaData().getColumnCount();i++)
					{
						String colType = trs.getMetaData().getColumnTypeName(i);
						//String colName = trs.getMetaData().getColumnName(i).toUpperCase();

						if(sql.trim().length() > 0 ) sql +=",";
						if(colType.indexOf("INT")>-1 || colType.indexOf("INT")>-1) {

							sql +=  trs.getInt(i);
						}
						else
							sql +=  "'"+trs.getString(i)+"'";

					}
					sql =  "insert into  "+ tabName + " values( "+sql+")";
					log.info("migrationTable :"+sql);
					destJdbc.update(sql);
					rows++;
				}
				log.info("migrationTable: 迁移数据库 ["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"] 下的表 ["+tabName+"] -> " +
						"["+ srcJdbc.getDBIP()  +" : " +srcJdbc.getSchema()+"] 结束!工有["+rows+"] 行!");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{

			try{
				if(rs != null ) rs.close();
				if(trs != null ) trs.close();
				if(tstatement != null ) tstatement.close();
				if(srcJdbc!=null) srcJdbc.close();
				if(destJdbc!=null) destJdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			srcJdbc = null;

		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MigrationUtil.migrationDB(false);
		//if(args.length == 1 ){

		//	MigrationUtil.migrationTable(false, args[0].trim());
		//MigrationUtil.migrationTable(false,"tbc_md_attack_hacker_list");
		//}
		//MigrationUtil.lb();
	}

}
