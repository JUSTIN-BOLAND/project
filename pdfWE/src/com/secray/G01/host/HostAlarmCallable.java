package com.secray.G01.host;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.DateUtil;
import com.secray.utils.common.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Created by root on 2017/7/31 0031.
 */
public class HostAlarmCallable implements Callable<Object> {
   private int taskId;
   private int pageSize;
   private int start;
   private String tableName;

   public HostAlarmCallable(int taskId,int pageSize,String tableName){
       this.taskId = taskId;
       this.pageSize = pageSize;
       this.tableName = tableName;
       this.start = (this.taskId -1 ) * this.pageSize;
   }
   public Object call() throws Exception {
       JDBC jdbc = new JDBC(JDBC.DB);
       JDBC uJdbc = new JDBC(JDBC.DB);
       ResultSet rs = null;
       String querSql = "select a.* from ( SELECT site_id, site_name,site_domain FROM tbc_dic_site where enable_opened=1 order by site_id) a  limit "+this.start +","+this.pageSize;

       String sql = "";
       String domain, siteId;
       try {

           jdbc.connect();
           if(this.taskId==1) jdbc.update("truncate table " + this.tableName);
           rs = jdbc.query(querSql);

           String[] wbStatus = null;
           uJdbc.connect();
           while (rs.next()) {
               siteId = rs.getString("site_id");
               domain = rs.getString("site_domain");
               domain = (domain == null ? "" : domain.trim());
               if (domain.length() == 0)
                   continue;

               //LogUtil.log("HostAlarmCallable", " getWebSiteStatusQuick begin");
               wbStatus = HostUtil.getWebSiteStatusQuick(domain);
               //LogUtil.log("HostAlarmCallable", "getWebSiteStatusQuick end");

               String statisDate = DateUtil.formatCurDate(DateUtil.LONGDAY);
               String startTime = DateUtil.formatCurDate(DateUtil.DATE);
               String checkTime = DateUtil.formatCurDate(DateUtil.SHORT_DATEMIN);


               int alarmLevel = this.getAlarmLevel(uJdbc, wbStatus);

               sql = "insert into "+this.tableName+" values('" + statisDate + "'," + siteId + ",'" + domain + "'," + "'" + checkTime + "','" + startTime + "','" + wbStatus[0] + "','" + wbStatus[1] + "','" + wbStatus[2] + "',null,null" + "," + alarmLevel + ",0,null )";
               // this.log.info("insert: " + sql);
               uJdbc.update(sql);
           }

           LogUtil.log("HostAlarmCallable", "running【"+this.taskId+"】 is over!");

       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               if (rs != null)                  rs.close();
               if (jdbc != null)                 jdbc.close();
               if (uJdbc != null)                uJdbc.close();

           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       return "子线程【"+this.taskId+"】 获取【"+this.pageSize+"】个网站信息!";
   }

    public int getAlarmLevel(JDBC jdbc,String[] wbStatus){

        //判断告警级别
        int alarmLevel = 3;
        //网站打不开，是一级
        if("0".equals(wbStatus[2])){
            alarmLevel = 1 ;
        }
        else {  //响应时间长度超过阈值，丢包率超过阈值，任一个超过阀值，则是二级
            String  sql="SELECT SUM(a.flag) \n" +
                    "FROM(\n" +
                    "  SELECT "+wbStatus[1]+"  BETWEEN a.changed_threshold_level1 AND a.changed_threshold_level2  AS flag  FROM tbc_dic_alarm_threshold a WHERE a.threshold_id=1 \n" +
                    "UNION ALL\n" +
                    "  SELECT "+wbStatus[0]+" BETWEEN a.changed_threshold_level1 AND a.changed_threshold_level2   FROM tbc_dic_alarm_threshold a WHERE a.threshold_id=2 \n"
                    + ") a \n" ;
            // this.log.info("getAlarmLevel : "+sql);
            int throld = jdbc.queryForInt(sql);
            if(throld > 0) {
                alarmLevel = 2 ;
            }

        }
        return alarmLevel;
    }
}