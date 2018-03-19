package com.deyi.util;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * pure
 * @author hejx
 * @2016年3月19日
 */
public class PropertiesUtil {
	private final static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);
	/**
	 * 读取配置文件对象
	 */
	private final static Properties proper = new Properties();

	static
	{
		try {
			InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream("/config/sysconfig.properties");
			if(null == is){
				//System.out.println();
				LOG.error("配置文件sysconfig.properties没有正常加载...");
				String clsPath = PropertiesUtil.class.getResource("/").getPath();
				String path =clsPath.replace("test-classes/","classes/")+"/config/sysconfig.properties";
				LOG.info("path="+path);
				try {
					path = URLDecoder.decode(path, "UTF-8");
					path = path.substring(1,path.length());
					path = path.replace("/","\\");
					is = (new FileInputStream(new File(path)));
				} catch (Exception ue) {
					LOG.error("PropertiesUtil error",ue);
				}
			}
			proper.load(is);
		} catch (IOException e) {
			LOG.error("PropertiesUtil error",e);


		}
	}

	/**
	 * 方法用途: 获取配置信息<br>
	 * 实现步骤: <br>
	 *
	 * @param key
	 *            配置信息的键值
	 * @return
	 */
	public static String getProperty(String key) {
		return proper.getProperty(key);
	}

}
