package com.secray.utils.JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.secray.utils.common.CommonUtil;
import com.secray.utils.common.RegUtil;







/**
 * 类  名: JDBC
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2016年9月5日 下午3:41:18
 *
 */
public class JDBC {

	public static int HIVE=0;
	public static int DB=1;
	public static int HF=2;
	public static int MS=3;

	public static int ORACLE=0;
	public static int DB2=1;
	public static int MYSQL=2;

	public static int TABLE=0;
	public static int COLUMN=1;
	public static int VIEW=2;

	public static int ARG_IN=1;
	public static int ARG_OUT=2;
	//数据库字典表
	private String[] tables={"all_tables","","INFORMATION_SCHEMA.TABLES",""};
	private String[] columns={"all_TAB_COLUMNS","","INFORMATION_SCHEMA.COLUMNS",""};
	private String[] views={"all_views","","INFORMATION_SCHEMA.VIEWS",""};

	//日期函数
	private String[] dateFuncs={"sysdate","","now()",""};
	private String[] chars={"VARCHAR2(100)","","CHAR(100)","String"};

	private Connection connection = null;
	//private Statement statements = null;
	protected Statement statement = null;
	private ResultSet rs = null;
	private int jdbcType=0;
	private String DBDRIVER = null;
	private int dbType ;
	protected String schema;
	private String dbIP;

	private String[] dbNames={"ORACLE","DB2","MYSQL","HIVE"};

	private Properties prop;

	public static final Logger log = Logger.getLogger(JDBC.class);


	//新增为数据抽取
	public JDBC(Properties prop)
	{
		this.prop = prop;
	}
	public Statement fetchConnect() throws Exception
	{
		DBDRIVER = prop.getProperty("jdbc.db.Driver");
		String URL = prop.getProperty("jdbc.db.URL");
		String userName = prop.getProperty("jdbc.db.userName");
		String password = prop.getProperty("jdbc.db.password");


		for( int i = 0;i < dbNames.length ; i++)
		{
			if(this.DBDRIVER.toUpperCase().indexOf("."+dbNames[i]+".") > 0)
			{
				dbType = i;
				break;
			}
		}
		log.info("connect : "+URL+"\n "+userName+"\n "+password);
		String[] ips = RegUtil.getRegContent(URL, RegUtil.IP,3,"@");
		if("MYSQL".equals(this.dbNames[this.dbType])){
			schema =   (ips == null ? "": ips[0]);
		}
		ips = RegUtil.getRegContent(URL, RegUtil.IP,2,"@");
		this.dbIP = (ips == null ? "": ips[0]);

		Class.forName(DBDRIVER).newInstance();

		//Connect to the database
		connection = DriverManager.getConnection(URL, userName, password);
		this.setConnection(connection);
		statement = connection.createStatement();
		return statement;
	}

	public Statement fetchStatement() throws Exception
	{

		statement = connection.createStatement();
		return statement;
	}

	public String getTableSql(String tableName){

		ResultSet trs = null;
		try{
			String sql = "SHOW CREATE TABLE "+tableName;
			trs = this.statement.executeQuery(sql);
			if(trs.next()) return trs.getString(1);
			else return "";
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		finally
		{
			try{
				if(rs != null ) rs.close();
			}
			catch(SQLException e){

			}
		}
		return "";
	}
	public boolean isTableExist(String tableNames) throws SQLException
	{
		String sql="select count(1) from "+tables[dbType] + " where  upper(table_schema)=upper('neweye')  and  upper(table_name) =upper('"+tableNames +"')" ;
		System.out.println(sql);
		int ret = this.queryForInt(sql);
		String result = ( ret > 0 ? "true": "false");
		return Boolean.parseBoolean(result);
	}
	public ResultSet getTables(String tableNames) throws SQLException
	{
		String filterSql = "";
		if(tableNames != null ||
				(tableNames.trim().length() >0 && "ALL".equals(tableNames.toUpperCase()) )) {
			filterSql = " and table_name in('"+ tableNames.replace(",", "','") +"')";
		}
		String sql="SELECT * FROM "+tables[dbType]+" t WHERE upper(t.table_schema)=upper('"+schema+"')"+filterSql;

		if(dbType == 0) {
			sql="SELECT * FROM "+tables[dbType]+" t WHERE upper(t.owner)=upper('"+prop.getProperty("jdbc.db.userName")+"')"+filterSql;

		}
		sql="SELECT * FROM "+tables[dbType]+" t where upper(t.table_schema)=upper('ayesaaa') and upper(t.table_name)<>upper('ims_mc_members') and upper(t.table_name) not in(select upper(a) from tb_lb)";
		log.info("getTables : "+ sql);
		return this.statement.executeQuery(sql);
	}


	//新增为数据抽取 结束

	public JDBC(int jdbcType)
	{
		this.jdbcType =  jdbcType;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public String getSchema()
	{
		return schema;

	}

	public String getDBIP()
	{
		return this.dbIP;

	}
	public String getDbType(){
		return this.dbNames[this.dbType];
	}
	public Statement connect() throws Exception
	{
		String propFile  = "./conf/jdbc.properties";
		String path = null;
		String clsPath = Configuration.class.getResource("/").getPath();
		int pos = clsPath.indexOf("classes");
		//log.info("connect : jdbc properties[claspath] : "+clsPath+" -> "+pos);
		if( pos > -1 ) {
			path = clsPath.substring(0,pos);
			path = path + propFile;
		}
		else path = propFile;
		//log.info("connect : jdbc properties[in] : "+path);
		Configuration rc = new Configuration(path);
		this.DBDRIVER = rc.getProperty("jdbc.hive.Driver");
		String URL = rc.getProperty("jdbc.hive.URL");
		String userName = rc.getProperty("jdbc.hive.userName");
		String password = rc.getProperty("jdbc.hive.password");

		if(jdbcType == this.DB)
		{
			DBDRIVER = rc.getProperty("jdbc.db.Driver");
			URL = rc.getProperty("jdbc.db.URL");
			userName = rc.getProperty("jdbc.db.userName");
			password = rc.getProperty("jdbc.db.password");
		}
		else if(jdbcType == this.HF)
		{
			DBDRIVER = rc.getProperty("jdbc.hf.Driver");
			URL = rc.getProperty("jdbc.hf.URL");
			userName = rc.getProperty("jdbc.hf.userName");
			password = rc.getProperty("jdbc.hf.password");
		}
		else if(jdbcType == this.MS)
		{
			DBDRIVER = rc.getProperty("jdbc.mysql.Driver");
			URL = rc.getProperty("jdbc.mysql.URL");
			userName = rc.getProperty("jdbc.mysql.userName");
			password = rc.getProperty("jdbc.mysql.password");
		}

		rc = null;
		for( int i = 0;i < dbNames.length ; i++)
		{
			if(this.DBDRIVER.toUpperCase().indexOf("."+dbNames[i]+".") > 0)
			{
				dbType = i;
				break;
			}
		}
		//log.info("connect : "+URL+"\n "+userName+"\n "+password);

		String[] ips = RegUtil.getRegContent(URL, RegUtil.IP,3,"@");
		if("MYSQL".equals(this.dbNames[this.dbType])){
			schema =   (ips == null ? "": ips[0]);
		}
		ips = RegUtil.getRegContent(URL, RegUtil.IP,2,"@");
		this.dbIP = (ips == null ? "": ips[0]);

		Class.forName(DBDRIVER).newInstance();

		//Connect to the database
		connection = DriverManager.getConnection(URL, userName, password);
		this.setConnection(connection);
		statement = connection.createStatement();
		return statement;

	}

	public boolean isHaveData(String sql) throws SQLException
	{
		boolean isExist = false;
		log.info("isHaveData : "+sql);
		ResultSet rs = this.query(sql);
		if(rs.next())  if(rs.getInt(1)>0) return true;
		return isExist;
	}
	public String queryForSingle(String sql,String split)
	{
		String result="";
		try {
			//Statement st = connection.createStatement();
			ResultSet rs =  this.statement.executeQuery(sql);
			if(rs.next())
			{
				//System.out.println(rs.getMetaData().getColumnCount());
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					if(result.trim().length()>0) result += split;
					result += rs.getString(i);
				}
			}
			rs.close();

			return result;

		}
		catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	public String queryRows(String sql,String split)
	{
		String result="";
		try {
			ResultSet rs = query(sql);
			while(rs.next())
			{
				if(result.trim().length()>0) result += split;
				result += rs.getString(1);
			}
			return result;

		}
		catch( Exception e ) {
			e.printStackTrace();
			return "";
		}
	}

	public int queryForInt(String sql)
	{
		int result=0;
		ResultSet rs = null;
		try {

			rs = query(sql);

			//log.info("queryForInt[after query] : "+sql);
			if(rs.next())
			{
				result =  rs.getInt(1);
				//log.info("queryForInt[ok] : "+sql+" : "+result);
			}
			//else log.info("queryForInt[not] : "+sql);


		}
		catch( Exception e ) {
			e.printStackTrace();

		}
		finally{
			try{
				if(rs!=null) rs.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
		return result;
	}

	public String queryForStr(String sql)
	{
		String result="0";

		ResultSet rs = null;
		try
		{
			rs = query(sql);
			if(rs.next())
			{
				result =  rs.getString(1);
				//log.info("queryForStr : "+sql+" : "+result);

			}


		}
		catch( Exception e ) {
			e.printStackTrace();

		}
		finally{
			try{
				if(rs!=null) rs.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}

		return result;
	}

	public ResultSet querys(String sql)
	{

		try {
			//statements= connection.createStatement();
			return  statement.executeQuery(sql);

		}
		catch( Exception e ) {
			e.printStackTrace();
			return null;
		}

	}
	public ResultSet query(String sql) throws SQLException
	{
        this.statement = this.getConnection().createStatement();
		return statement.executeQuery(sql);

	}
	public boolean execute(String sql)
	{
		try {

			//Obtain a statement object
			//statement = connection.createStatement();
			return statement.execute(sql);

		}
		catch( Exception e ) {
			e.printStackTrace();
			return false;
		}
	}
	public static void  updates(int jdbcType,String sql)
	{

		JDBC jdbc =  new JDBC(jdbcType);

		try
		{
			jdbc.connect();
			jdbc.execute(sql);
			jdbc.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			try
			{
				jdbc.rollback();
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		finally
		{
			try{
				if(jdbc!=null) jdbc.close();
			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

		}

	}
	public int truncate(String tableName) throws SQLException
	{
		if(tableName == null || tableName.trim().length() ==0 ) return -1;
		return this.update("truncate table "+tableName);
	}
	public String[] getFields(ResultSetMetaData rsmd){
		String[] result = {"",""};
		try
		{

			for(int i=1;i<=rsmd.getColumnCount();i++){
				if(result[0].trim().length()>0) result[0] += ",";
				if(result[1].trim().length()>0) result[1] += ",";

				result[0] +=rsmd.getColumnName(i).toUpperCase();
				result[1] +=rsmd.getColumnTypeName(i).toUpperCase();
			}
			result[0] = result[0].replaceAll("[A-Z]+\\.", "");
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return result;
	}
	public void commit() throws SQLException
	{
		connection.commit();
	}
	public void rollback() throws SQLException
	{
		connection.rollback();
	}
	public int update(String sql) throws SQLException
	{
		return statement.executeUpdate(sql);
	}

	public int updateSql(String sql) throws SQLException
	{
		if(statement==null || statement.isClosed()) statement = connection.createStatement();
		return statement.executeUpdate(sql);
	}

	public String getTableStruct(String tableName) throws SQLException{
		String sql="SHOW CREATE TABLE "+tableName;
		if("ORACLE".equals(this.dbNames[this.dbType])){
			sql="SELECT DBMS_METADATA.GET_DDL('TABLE','"+tableName+"') FROM DUAL";
		}
		else if("DB2".equals(this.dbNames[this.dbType])){
			sql=" describe table "+tableName;
		}
		ResultSet rs = this.query(sql);
		if( rs.next()){
			return rs.getString(2);
		}
		return null;
	}
	public void close() throws SQLException
	{
		//Time to close everthing up.

		if(rs !=null)  rs.close();
		if( statement != null ) {statement.close();	}
		//if( statements != null ) {statements.close();	}

		if( connection != null ) {
			connection.close();
		}
	}

	/**
	 *
	 * 函数名    : createTempTable
	 * 功   能	 :创建临时表
	 * 参   数    : @param tableName
	 * 参   数    : @param field		字段
	 * 参   数    : @param dataType     数据类型
	 * 参   数    : @param splitSysmol  分隔符
	 * 参   数    : @return    设定文件
	 * 返回值	 : int    返回类型  -1:参数为空 -2:字段和数据类型个数不一致
	 * 编写者    : root
	 * 编写时间 : 2016年9月5日 下午5:08:49
	 * @throws SQLException
	 */
	public int createTempTable(String tableName,String field,String dataType,String fSplitSysmol ) throws SQLException
	{
		if(CommonUtil.convertToNull(tableName).length() == 0
				|| CommonUtil.convertToNull(field).length() == 0
				|| CommonUtil.convertToNull(dataType).length() == 0
				)
		{
			return -1;
		}
		String splitSysmol= ",";
		String[] fields = field.split(splitSysmol);
		String[] dataTypes = dataType.split(splitSysmol);
		if(fields.length != dataTypes.length) return -2;
		String sql="";

		if(CommonUtil.isInt(dataType))
		{
			System.out.println(this.connection.getMetaData().getDriverMinorVersion());
			String dType=this.chars[this.dbType];
			if(this.DBDRIVER.indexOf(".hive.") > 0 ) dType = "STRING";
			for(int i = 0; i < Integer.parseInt(dataType); i++)
			{
				if( i != Integer.parseInt(dataType) - 1 )
					sql += field+ String.valueOf(i) +"	"+ dType +",\n";
				else
					sql += field+ String.valueOf(i)  + "	" + dType;
			}
		}
		else
		{
			for(int i = 0; i < fields.length; i++)
			{
				if( i != fields.length-1 )
					sql += fields[i] + "	" + dataTypes[i] + ",\n";
				else
					sql += fields[i] + "	" + dataTypes[i];
			}
		}
		sql = "create table " + tableName + "\n"
				+ "(\n" + sql + "\n) \n";
		if(this.DBDRIVER.indexOf(".hive.") > 0 )
		{
			sql = sql  +"  ROW FORMAT DELIMITED FIELDS TERMINATED BY  '" + fSplitSysmol + "' \n"
					+" STORED AS TEXTFILE";
		}
		log.info(sql);
		this.statement = connection.createStatement();
		return  this.statement.executeUpdate(sql);

	}

	public int createTableBySql(String tableName,String selectSql ) throws SQLException
	{
		String sql=" CREATE TABLE "+tableName+" as " +selectSql;
		log.info(sql);
		this.statement = connection.createStatement();
		return  this.statement.executeUpdate(sql);
	}

	/**
	 *
	 * 函数名    : dropTable
	 * 功   能    : 删除表
	 * 参   数    : @param tableName
	 * 参   数    : @return
	 * 参   数    : @throws SQLException    设定文件
	 * 返回值	 : int    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月7日 上午9:15:32
	 */
	public  int dropTable( String tableName) throws SQLException {

		this.statement = connection.createStatement();
		return  this.statement.executeUpdate("drop table " + tableName);
	}

	/**
	 *
	 * 函数名    : getDbDictoryTable
	 * 功   能     : 取得当前数据库的字典表
	 * 参   数    : @param dicType : 字典表类型(table,view,column)
	 * 参   数    : @return    设定文件
	 * 返回值	 : String    返回类型
	 * 编写者    : root
	 * 编写时间 : 2016年9月7日 上午9:31:11
	 */
	public String  getDbDictoryTable(int dicType)
	{
		String result=null;

		switch(dicType)
		{
			case 0: result = this.tables[dbType]; break;
			case 1: result = this.columns[dbType]; break;
			case 2: result = this.views[dbType]; break;
		}
		return result;
	}

	public void genTestData(String schedma,String tableName,int dataRowCnt) throws SQLException
	{
		String values="";
		int j=0;
		String sql = "SELECT column_name,data_type FROM " + this.getDbDictoryTable(this.COLUMN)
				+ " where table_schema='" + schedma + "' and  table_name='" + tableName + "' order by ordinal_position";
		log.info(sql);
		Map<String , String> columns  = new LinkedHashMap<String , String>();
		//获取表的字段和字段的数据类型
		ResultSet rs = this.query(sql);

		while(rs.next())
		{
			columns.put(rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"));
			// log.info(rs.getString("COLUMN_NAME")+ " : " + rs.getString("DATA_TYPE"));
		}
		rs.close();

		Iterator it = null;


		for(int i = 0 ; i < dataRowCnt; i++)
		{
			sql = "insert into " + schedma + "." + tableName + " values(";
			values = "";
			j=0;
			it = columns.keySet().iterator();
			while(it.hasNext()) {
				String key = (String)it.next();
				//log.info("hash: "+key+" : "+columns.get(key).toUpperCase());
				if(columns.get(key).toUpperCase().equals("VARCHAR2") || columns.get(key).toUpperCase().equals("VARCHAR")
						|| columns.get(key).toUpperCase().equals("CHAR") || columns.get(key).toUpperCase().equals("CLOB") )
				{
					if( values.length()>0) values +=  ",";
					values +=  "'data"+ (i + 1) + (j + 1) +"'";
				}
				if(columns.get(key).toUpperCase().equals("TINYINT") || columns.get(key).toUpperCase().equals("SMALLINT")
						|| columns.get(key).toUpperCase().equals("MEDIUMINT") || columns.get(key).toUpperCase().equals("INT")
						||  columns.get(key).toUpperCase().equals("INTEGER") || columns.get(key).toUpperCase().equals("BIGINT")
						|| columns.get(key).toUpperCase().equals("FLOAT") || columns.get(key).toUpperCase().equals("DOUBLE")

						||  columns.get(key).toUpperCase().equals("REAL") || columns.get(key).toUpperCase().equals("DECIMAL")
						|| columns.get(key).toUpperCase().equals("NUMERIC") )
				{
					if( values.length()>0) values +=  ",";
					values +=   (i + 1) + (j + 1) ;
				}
				if(columns.get(key).toUpperCase().equals("DATE") || columns.get(key).toUpperCase().equals("DATETIME") )
				{
					if( values.length()>0) values +=  ",";
					values +=   this.dateFuncs[this.dbType]  ;
				}

				j++;
			}
			log.info("values: "+values);
			sql +=  values + ") \n";
			log.info(sql);
			this.update(sql);
			//if(i % 100 == 0 ) this.commit();
		}

		// this.commit();

	}
	public void setParameter(CallableStatement cstm,Object param,int idx,int argType) throws SQLException
	{
		if (param instanceof Integer) {
			int value = ((Integer) param).intValue();
			if(ARG_IN == argType) cstm.setInt(idx, value);
			else cstm.registerOutParameter(idx, Types.INTEGER);
		} else if (param instanceof String) {
			String s = (String) param;
			log.info("setParameter : "+s);
			if(ARG_IN == argType)  cstm.setString(idx, s);
			else cstm.registerOutParameter(idx, Types.VARCHAR);
		} else if (param instanceof Double) {
			double d = ((Double) param).doubleValue();
			if(ARG_IN == argType) cstm.setDouble(idx, d);
			else cstm.registerOutParameter(idx, Types.DOUBLE);
		} else if (param instanceof Float) {
			float f = ((Float) param).floatValue();
			if(ARG_IN == argType) cstm.setFloat(idx, f);
			else cstm.registerOutParameter(idx, Types.FLOAT);
		} else if (param instanceof Long) {
			long l = ((Long) param).longValue();
			if(ARG_IN == argType) cstm.setLong(idx, l);
			else cstm.registerOutParameter(idx, Types.LONGVARCHAR);
		} else if (param instanceof Boolean) {
			boolean b = ((Boolean) param).booleanValue();
			if(ARG_IN == argType) cstm.setBoolean(idx, b);
			else cstm.registerOutParameter(idx, Types.BOOLEAN);
		} else if (param instanceof Date) {
			Date d = (Date) param;
			if(ARG_IN == argType) cstm.setDate(idx, (Date) param);
			cstm.registerOutParameter(idx, Types.DATE);
		}
	}
	public void call(String procName,Object[] ins,Object[] outs){
		String args = "";
		int insLen = 0;
		if(ins != null) insLen = ins.length;
		int outsLen =0;
		if(outs != null) outsLen = outs.length;

		for(int i=1 ; i<= insLen+outsLen; i++){
			if(args.length()>0) args += ",";
			args += "?";
		}
		String sql = "{CALL "+procName+"("+args+")}"; //调用存储过程
		CallableStatement cstm = null;
		try
		{
			log.info(sql);
			cstm = connection.prepareCall(sql); //实例化对象cstm
			for(int i=0 ; i< insLen; i++){
				setParameter(cstm,ins[i],i+1,JDBC.ARG_IN);
			}
			for(int i=0 ; i< outsLen; i++){
				setParameter(cstm,ins[i],insLen+i,JDBC.ARG_OUT);
			}
			// cstm.setString(1, "myd"); //存储过程输入参数
			//cstm.setInt(2, 2); // 存储过程输入参数
			//cstm.registerOutParameter(2, Types.INTEGER); // 设置返回值类型 即返回值
			cstm.execute(); // 执行存储过程
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			try {
				if(cstm != null ) cstm.close();
			}
			catch(SQLException e ){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args)
	{
		JDBC jdbc = new JDBC(JDBC.HIVE);
		String tableName = "tb_kf_test";
		try
		{
			jdbc.connect();
			System.out.println(jdbc.queryForStr("select ipaddress from ipdevicemap"));
			//jdbc.createTempTable("tb_kf_test","a","16",",");
			//jdbc.genTestData("hive",tableName, 10000);
		  /*String sql="mysql -h 127.0.0.1 -u root -p 123456 -P 3306 -e \"select * from "+tableName+"\"  > /home/root/lb";
		  jdbc.update(sql);*/
			//mysql -h 127.0.0.1 -u root -p 123456 -P 3306 -e "select * from tb_kf_test"  > /home/root/lb
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{
				if(jdbc != null) jdbc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}
}