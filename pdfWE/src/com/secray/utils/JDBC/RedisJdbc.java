package com.secray.utils.JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.secray.utils.common.StrUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

/**
 * 类  名: RedisJdbc
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年3月7日 下午2:00:21
 *
 */
public class RedisJdbc {

	public static final Logger log = Logger.getLogger(RedisJdbc.class);

	private RedisPool pool ;
	private Jedis jedis;
	//private ShardedJedis clusterJedis;
	private JedisCluster jedisCluster;


	public RedisJdbc(){
		pool = new RedisPool();

	}
	public void getConnection(){
		this.jedisCluster = pool.connect();
		if(pool.isCluster()){
			//this.clusterJedis = (ShardedJedis)pool.getConnect();
		}
		else{
			this.jedis = (Jedis)pool.getConnect();
		}
	}
	public void set(String key, String value){
		if(pool.isCluster()){
			this.jedisCluster.set(key, value);
		}
		else{
			this.jedis.set(key, value);
		}
	}

	public String get(String key){
		if(pool.isCluster()){
			return  this.jedisCluster.get(key);
		}
		else{
			return this.jedis.get(key);
		}
	}

	public void append(String key, String value){
		if(pool.isCluster()){
			this.jedisCluster.append(key, value);
		}
		else{
			this.jedis.append(key, value);
		}
	}

	//map


	public <T> void setList(String key ,List<T> list){
		try {

			if(pool.isCluster()){
				this.jedisCluster.set(key.getBytes(),ObjectTranscoder.serialize(list));
			}
			else{
				this.jedis.set(key.getBytes(),ObjectTranscoder.serialize(list));
			}
		} catch (Exception e) {
			log.error("Set key error : "+e);
		}
	}
	/**
	 * 获取list
	 * @param <T>
	 * @param key
	 * @return list
	 */
	public <T> List<T> getList(String key){
		// String bKey = buildKey(key);

		byte[] in = null;//getJedis().get(key.getBytes());
		if(pool.isCluster()){
			if(jedisCluster == null || !jedisCluster.exists(key.getBytes())){
				return null;
			}
			in = this.jedisCluster.get(key.getBytes());
		}
		else{
			if(jedis == null || !jedis.exists(key.getBytes())){
				return null;
			}
			in = this.jedis.get(key.getBytes());
		}
		List<T> list = (List<T>) ObjectTranscoder.deserialize(in);
		return list;
	}
	/**
	 * 设置 map
	 * @param <T>
	 * @param key
	 * @param value
	 */
	public <T> void setMap(String key ,Map<String,T> map){
		try {

			if(pool.isCluster()){
				//this.clusterJedis.set(key.getBytes(),ObjectTranscoder.serialize(map));
				this.jedisCluster.set(key.getBytes(),ObjectTranscoder.serialize(map));

			}
			else{
				this.jedis.set(key.getBytes(),ObjectTranscoder.serialize(map));
			}
		} catch (Exception e) {
			log.error("Set key error : "+e);
		}
	}
	/**
	 * 获取list
	 * @param <T>
	 * @param key
	 * @return list
	 */
	public <T> Map<String,T> getMap(String key){


		byte[] in = null;
		if(pool.isCluster()){
    	   /*if(clusterJedis == null || !clusterJedis.exists(key.getBytes())){
               return null;
           }
    	   in = this.clusterJedis.get(key.getBytes()); */

			if(jedisCluster == null || !jedisCluster.exists(key.getBytes())){
				return null;
			}
			in = this.jedisCluster.get(key.getBytes());


		}
		else{
			if(jedis == null || !jedis.exists(key.getBytes())){
				return null;
			}
			in = this.jedis.get(key.getBytes());
		}
		Map<String,T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);
		return map;
	}

	//map over
	public void close(){
		if( this.jedis != null ) this.jedis.close();
		//if( this.clusterJedis != null ) this.clusterJedis.close();
		this.pool.close();
	}

	public void cacheTable(String tableName,String sql){
		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs = null;
		ResultSetMetaData rst = null;
		String key= null;
		String[] values = null;
		LinkedHashMap<String,String[]> rowsMap = new LinkedHashMap<String,String[]>();
		try
		{
			jdbc.connect();
			rs = jdbc.query(sql);
			rst = rs.getMetaData();

			while(rs.next()){
				key = rs.getString(1);
				values = new String[rst.getColumnCount()-2];
				for(int i=2; i< rst.getColumnCount(); i++){
					values[i-2] = rs.getString(i);
				}
				rowsMap.put(key, values);

			}

			this.setMap(tableName, rowsMap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null ) rs.close();
				if(jdbc!=null) jdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;
			rs = null;
			rst = null;
			rowsMap = null;
		}
	}
	public LinkedHashMap getCachedTable(String tableName){
		return (LinkedHashMap)this.getMap(tableName);
	}
	public void show(String tableName){
		LinkedHashMap<String,String[]> hm = this.getCachedTable(tableName);
		Iterator iter = hm.entrySet().iterator();
		int row=1;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();

			log.info("show ["+tableName+"]:第["+row+"]行 : 主键:  "+entry.getKey() +" ->值: "+StrUtil.mkString((String[])entry.getValue(),","));
			row++;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String st="192.168.12.9:6379 192.168.12.10:7379 192.168.12.11:8379 192.168.12.11:8479   192.168.12.12:9379  192.168.12.12:9479";
		String[] sts = st.split("[,|\\s+]+");
		for(String s: sts){
			if(s.trim().length()==0) System.out.println(StrUtil.convertCharToNum(s.charAt(0))); else System.out.println(s);
		}

		RedisJdbc rJdbc = new RedisJdbc();
		rJdbc.getConnection();
		//rJdbc.set("lb", "liangbo");
		//System.out.println(rJdbc.get("lb"));
		String sql="SELECT 	area_id,  "+
				"	area_name, "+
				"	parent_id "+

				"	FROM  "+
				"	xdtrdata.tb_dic_tmp_area ";
		String tableName="tb_dic_tmp_area";
		//sql="SELECT *  FROM tb_dic_tmp_company_cat a";
		tableName="tb_dic_tmp_company_cat";
		//rJdbc.cacheTable(tableName, sql);
		rJdbc.show(tableName);
		rJdbc.close();


	}

}
