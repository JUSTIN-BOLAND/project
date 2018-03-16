package com.lb.server.util.JDBC;

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

import com.lb.server.util.CommonUtil;
import com.lb.server.util.RegUtil;












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
	//鏁版嵁搴撳瓧鍏歌〃
	private String[] tables={"all_tables","","INFORMATION_SCHEMA.TABLES",""};
	private String[] columns={"all_TAB_COLUMNS","","INFORMATION_SCHEMA.COLUMNS",""};
	private String[] views={"all_views","","INFORMATION_SCHEMA.VIEWS",""};

	//鏃ユ湡鍑芥暟
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

	public JDBC(){
		jdbcType=-1;
	}


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
		this.DBDRIVER = rc.getProperty("driver");
		String URL = rc.getProperty("url");
		String userName = rc.getProperty("username");
		String password = rc.getProperty("password");
		//System.out.println("connect :  jdbcType="+jdbcType+" -> "+pos);
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
		//System.out.println("connect : "+URL+"\n "+userName+"\n "+password);

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
		String result="-1";

		ResultSet rs = null;
		try
		{
			rs = query(sql);
			if(rs.next())
			{
				result =  rs.getString(1);
				log.info("queryForStr : "+sql+" : "+result);

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


	public  int dropTable( String tableName) throws SQLException {

		this.statement = connection.createStatement();
		return  this.statement.executeUpdate("drop table " + tableName);
	}


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
		String sql = "{CALL "+procName+"("+args+")}"; //璋冪敤瀛樺偍杩囩▼
		CallableStatement cstm = null;
		try
		{
			log.info(sql);
			cstm = connection.prepareCall(sql); //瀹炰緥鍖栧璞stm
			for(int i=0 ; i< insLen; i++){
				setParameter(cstm,ins[i],i+1,JDBC.ARG_IN);
			}
			for(int i=0 ; i< outsLen; i++){
				setParameter(cstm,ins[i],insLen+i,JDBC.ARG_OUT);
			}
			// cstm.setString(1, "myd"); //瀛樺偍杩囩▼杈撳叆鍙傛暟
			//cstm.setInt(2, 2); // 瀛樺偍杩囩▼杈撳叆鍙傛暟
			//cstm.registerOutParameter(2, Types.INTEGER); // 璁剧疆杩斿洖鍊肩被鍨� 鍗宠繑鍥炲��
			cstm.execute(); // 鎵ц瀛樺偍杩囩▼
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
		JDBC jdbc = new JDBC(JDBC.DB);
		String tableName = "t_machine_ip";
		try
		{
			jdbc.connect();
			System.out.println(jdbc.queryForInt("select count(1) from t_machine_ip"));
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