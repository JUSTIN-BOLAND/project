package com.secray.utils.JDBC;

import com.secray.utils.common.LogUtil;

/**
 * Created by root on 2017/7/31 0031.
 */
public class ConfigUtil {

    public static String getProperty(String propertyName){
        String propFile  = "./conf/sys.properties";
        String path = null;
        String clsPath = Configuration.class.getResource("/").getPath();
        int pos = clsPath.indexOf("classes");

        if( pos > -1 ) {
            path = clsPath.substring(0,pos);
            path = path + propFile;
        }
        else path = propFile;

        Configuration rc = new Configuration(path);
        String propertyValue  = rc.getProperty(propertyName);
        return (propertyValue == null ? "" : propertyValue);
    }

    public static void main(String[]  args){
        LogUtil.log("test",ConfigUtil.getProperty("parseWord.tableName"));
    }
}
