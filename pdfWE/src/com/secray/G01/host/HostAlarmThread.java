package com.secray.G01.host;

import com.secray.utils.JDBC.ConfigUtil;
import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.LogUtil;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by root on 2017/7/31 0031.
 */
public class HostAlarmThread  {
    private String tableName= ConfigUtil.getProperty("parseWord.tableName");
    private int pageSize =0 ;
    private int total =0;

    public HostAlarmThread(){
        String value =  ConfigUtil.getProperty("parseWord.pageSize");
        if(value.length() ==0 ) value="0";
        this.pageSize = Integer.parseInt(value);
        JDBC jdbc = new JDBC(JDBC.DB);
        try {
            jdbc.connect();
            total = jdbc.queryForInt("SELECT count(1) FROM tbc_dic_site where enable_opened=1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(jdbc != null ) jdbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void run(){
        ExecutorService exec = Executors.newCachedThreadPool();
        int pageCount =  ( this.total % this.pageSize ==0 ?  this.total / this.pageSize :  this.total / this.pageSize +1);
        for (int i = 1; i <=pageCount; i++) {
            LogUtil.log("HostAlarmThread",i+" : pageCount="+pageCount +" : total=" + this.total  + " : pageSize=" + this.pageSize+" : tableName="+ this.tableName);
            exec.submit(new HostAlarmCallable(i, this.pageSize, this.tableName));
        }
        exec.shutdown();
    }

    public static void main(String[]  args){
        HostAlarmThread hostAlarmThread = new HostAlarmThread();
        hostAlarmThread.run();
    }
}
