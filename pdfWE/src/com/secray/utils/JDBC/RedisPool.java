package com.secray.utils.JDBC;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.secray.utils.common.CommonUtil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public final class RedisPool {

	private  String HOSTS = "192.168.0.100";
	//Redis服务器IP


	//访问密码
	private  String user = "admin";

	//可用连接实例的最大数目，默认值为8；
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private  int maxTotal = 1024;

	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private  int maxIdle = 200;

	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private  int maxWaitMilles = 10000;

	private  int timeout = 10000;

	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private  boolean testOnBorrow = true;

	private  boolean isCluster = false;

	private  JedisPool jedisPool = null;
	private ShardedJedisPool clusterPool = null;
	private JedisCluster jedisCluster = null;

	// @return the clusterPool

	public ShardedJedisPool getClusterPool() {
		return clusterPool;
	}


	// @param TODO

	public void setClusterPool(ShardedJedisPool clusterPool) {
		this.clusterPool = clusterPool;
	}


	// @return the isCluster

	public boolean isCluster() {
		return isCluster;
	}


	// @param TODO

	public void setCluster(boolean isCluster) {
		this.isCluster = isCluster;
	}


	public RedisPool(){
		Configuration rc = new Configuration("./conf/jdbc.properties");
		this.HOSTS = rc.getProperty("redis.pool.hosts");
		this.user = rc.getProperty("redis.redis.user");
		this.maxTotal = CommonUtil.parseInt(rc.getProperty("redis.redis.maxTotal"),8);
		this.maxIdle = CommonUtil.parseInt(rc.getProperty("redis.redis.maxIdle"),8);

		this.maxWaitMilles = CommonUtil.parseInt(rc.getProperty("redis.redis.maxWaitMilles"),8);
		this.timeout = CommonUtil.parseInt(rc.getProperty("redis.redis.timeout"),8);
		String testOnBorrow = rc.getProperty("redis.redis.testOnBorrow");
		this.testOnBorrow = ( testOnBorrow == null || testOnBorrow.trim().length() ==0 || "FALSE".equals(testOnBorrow.trim().toUpperCase()) ? false: true );


	}


	/**
	 *
	 * 函数名    : getConnect
	 * 功   能	 : 返回连接池中redis(isCluster==true: ShardedJedis false:Jedis)
	 * 参   数    : @return    设定文件
	 * 返回值	 : Object    返回类型
	 * 编写者    : root
	 * 编写时间 : 2017年3月7日 上午11:33:22
	 */
	public synchronized  Object getConnect() {

		if(isCluster){

			if (clusterPool != null) {
				return clusterPool.getResource();
				//return jedisCluster.set

			} else {
				return null;
			}
		}
		else{
			if (jedisPool != null) {
				return jedisPool.getResource();

			} else {
				return null;
			}
		}

	}

	public JedisCluster connect(){
		//try {

		String[] clusterHosts = this.HOSTS.split("[,|\\s+]+");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(this.maxTotal);

		config.setMaxIdle(this.maxIdle);
		config.setMaxWaitMillis(this.maxWaitMilles);
		config.setTestOnBorrow(this.testOnBorrow);
		if(clusterHosts.length == 1 ){
			String[] hostPorts  = clusterHosts[0].split(":");
			jedisPool = new JedisPool(config, hostPorts[0], CommonUtil.parseInt(hostPorts[1],6379), this.timeout,this.user);
			hostPorts  = null;
			return null;
		}
		else
		{
			String[] hostPorts  = null;
			JedisShardInfo jedisShardInfo = null;
			List<JedisShardInfo> clusterList  = new LinkedList<JedisShardInfo>();
			Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

			for(String clusterHost : clusterHosts){
				hostPorts  = clusterHost.split(":");
				jedisShardInfo = new JedisShardInfo(hostPorts[0], CommonUtil.parseInt(hostPorts[1],6379));
				clusterList.add(jedisShardInfo);
				jedisClusterNodes.add(new HostAndPort(hostPorts[0], CommonUtil.parseInt(hostPorts[1],6379)));
			}
			hostPorts  = null;
			jedisShardInfo = null;
			//clusterPool = new ShardedJedisPool(config, clusterList);
			jedisCluster = new JedisCluster(jedisClusterNodes, this.timeout, 100,config);
			this.isCluster = true;
			return jedisCluster;
		}
        /*} catch (Exception e) {
            e.printStackTrace();
        }*/
	}
	/**
	 * 释放jedis资源
	 * @param jedis
	 */
	public  void close() {
		try
		{
			if(jedisPool != null) jedisPool.close();
			if(clusterPool != null ) clusterPool.close();
			if(jedisCluster != null ) jedisCluster.close();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}

	}
}
