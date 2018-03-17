package com.secray.G01.host;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.DateUtil;
import com.secray.utils.common.LogUtil;
import com.secray.utils.common.ShellUtil;
import com.secray.utils.common.StrUtil;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 2017/7/19 0019.
 */
public class HostAlarm {
    public  final Logger log = Logger.getLogger(HostAlarm.class);



     public static HostAlarm getInstance(){
         return new HostAlarm();
     }

    public String[]  getWebSiteStatus(String domain){

        String pingInfo = ShellUtil.executeRet("ping "+ domain + " -c 3" );
        //this.log.info("【getWebSiteStatus 】 : "+pingInfo);
        String[] lines = pingInfo.split("\n");
        String lostPackPer = "";
        int i=0;
        double totalLen = 0;
        for(String line : lines) {
              // this.log.info("【getWebSiteStatus:line 】 : "+line);
               if(line.indexOf("Destination Host Unreachable") > -1 ){
                   lostPackPer = "0";
                   totalLen = 0;
                   break;
               }
                if (line.indexOf("packet loss") > -1) {
                    String[] ipInfos = line.split(",");
                    //this.log.info("【getWebSiteStatus:packet loss 】 : "+ipInfos.length);
                    lostPackPer = ipInfos[2].split("\\s+")[1];
                    lostPackPer = (lostPackPer==null ||lostPackPer.trim().length() ==0 ? "0":lostPackPer);

                }
                else if(line.indexOf("ttl=") > -1 ) {  //
                     String[] values = line.split("\\s+");
                    this.log.info("【getWebSiteStatus:icmp_seq 】 : "+line+" : "+values.length);
                    // this.log.info("【getWebSiteStatus:icmp_seq 】 : "+values[5] +" : "+ values[7]);
                     String ssl = values[6].replace("time=","");
                    if(line.indexOf("(") > -1 ){
                        ssl = values[7].replace("time=","");
                    }
                    double  sslValue = 0;
                    try {
                         sslValue =Double.parseDouble(ssl);
                    } catch (NumberFormatException e) {

                    }
                    totalLen += sslValue;
                     i++;

                }

        }
        String httpCode = ShellUtil.executeRet(" curl -sL -w '%{http_code}' '"+ domain + "' -o /dev/nu" );
        String result = (lostPackPer.trim().length() > 0  ? lostPackPer.replace("%","") : "0")+","+ (i ==0 ? "0" : String.format("%.2f", totalLen/i) ) + "," + (httpCode.equals("200") ? "1" : "0");
        this.log.info("getWebSiteStatus : "+result);
        return result.split(",");

    }
    public String[]  getWebSiteStatusQuick(String domain){
        String lostPackPer = "100,0";
        String httpCode = ShellUtil.executeRet(" curl -sL -w '%{http_code}' '"+ domain + "' -o /dev/null" );
        httpCode = StrUtil.trimEnter(httpCode);
        httpCode = (httpCode.equals("200") ? "1" : "0");

        if("1".equals(httpCode)){
            lostPackPer = ShellUtil.calPacketLoss("ping "+ domain + " -c 3");

        }
        lostPackPer = lostPackPer+","+httpCode;
        this.log.info("【getWebSiteStatusQuick】 : " + lostPackPer);
        return lostPackPer.split(",");
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
    public void updateWebSiteStatus(String runType){
        JDBC jdbc = new JDBC(JDBC.DB);
        JDBC uJdbc = new JDBC(JDBC.DB);
        ResultSet rs = null;
        String querSql="SELECT site_id, site_name,site_domain FROM tbc_dic_site where enable_opened=1";
        if("INC".equals(runType.toUpperCase())){
            querSql="SELECT site_id, site_name,site_domain FROM tbc_dic_site a  where enable_opened=1  "+
                     "AND NOT EXISTS (\n" + "  SELECT 1 FROM tb_md_site_alarm_health_list b WHERE b.site_id = a.site_id\n" + ")";
        }
        String sql="";
        String domain,siteId;
        try {

            jdbc.connect();
            rs = jdbc.query(querSql);

            String[] wbStatus =  null;
            uJdbc.connect();
            while(rs.next()){
                siteId = rs.getString("site_id");
                domain = rs.getString("site_domain");
                domain = (domain == null ? "": domain.trim());
                if(domain.length() == 0 ) continue;

                LogUtil.log("updateWebSiteStatus"," getWebSiteStatusQuick begin");
                wbStatus = this.getWebSiteStatusQuick(domain);
                LogUtil.log("updateWebSiteStatus","getWebSiteStatusQuick end");
                sql = "select flag_open,case when end_time is null then 0 else 1 end endTimeFlag,datediff(sysdate(),end_time) as diff from tb_md_site_alarm_health_list where site_id = " + siteId;
                String valueStr = uJdbc.queryForSingle(sql,",");
                //LogUtil.log("updateWebSiteStatus","site is exist");
                String statisDate = DateUtil.formatCurDate(DateUtil.LONGDAY);
                String startTime  = DateUtil.formatCurDate(DateUtil.DATE);
                String checkTime  = DateUtil.formatCurDate(DateUtil.SHORT_DATEMIN);

                if(valueStr.length() > 0 ){
                    String[]  values = valueStr.split(",");
                    //网站以前能打开，现在打不开
                    if("1".equals(values[0]) && "0".equals(wbStatus[2]) )  {
                        //网站的问题结束时间为空，更新，否则，新插入一条
                       if("0".equals(values[1])){
                           sql = "update tb_md_site_alarm_health_list \n"+
                                   " set check_time='" + checkTime + "',"+
                                   " lost_rate='" + wbStatus[0] + "',respon_time='" + wbStatus[1] + "' \n" +
                                   " flag_open='" + wbStatus[2] + "',last_time='"+ values[2] +"'"+
                                   "  where site_id = " + siteId;
                          // this.log.info("[open,notopen]: " + sql);
                           uJdbc.update(sql);


                       }
                       else{  //不为空，则插入一条
                           int alarmLevel = this.getAlarmLevel(uJdbc,wbStatus);
                           sql = "insert into tb_md_site_alarm_health_list values('"+statisDate+"',"+siteId+",'"+domain+"',"
                                   +"'"+checkTime+"','"+startTime+"','"+wbStatus[0]+"','"+wbStatus[1]+"','"+wbStatus[2]+"',null,null"
                                   +","+alarmLevel+",0,null )";
                          // this.log.info("add: " + sql);
                           uJdbc.update(sql);
                       }
                    }
                    //网站以前打不开
                    if("0".equals(values[0])  )  {
                        String subSql= "";
                        sql = "update tb_md_site_alarm_health_list \n"+
                                " set check_time='" + checkTime + "',"+
                                " lost_rate='" + wbStatus[0] + "',respon_time='" + wbStatus[1] + "', \n" +
                                " flag_open='" + wbStatus[2] + "' ";


                        //现在能打开
                        if("1".equals(wbStatus[2])){
                            subSql  = ",last_time='"+ values[2] +"'";
                        }
                        sql += subSql +   "  where site_id = " + siteId;
                        //this.log.info("notopen: " + sql);
                        uJdbc.update(sql);
                    }
                }
                else{
                    int alarmLevel = this.getAlarmLevel(uJdbc,wbStatus);

                    sql = "insert into tb_md_site_alarm_health_list values('"+statisDate+"',"+siteId+",'"+domain+"',"
                            +"'"+checkTime+"','"+startTime+"','"+wbStatus[0]+"','"+wbStatus[1]+"','"+wbStatus[2]+"',null,null"
                            +","+alarmLevel+",0,null )";
                   // this.log.info("insert: " + sql);
                    uJdbc.update(sql);
                }

                //LogUtil.log("updateWebSiteStatus","over");

            }

            LogUtil.log("updateWebSiteStatus","running is over!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if( rs != null ) rs.close();
                if(jdbc != null ) jdbc.close();
                if(uJdbc != null ) uJdbc.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


     public static  void main(String[] args){
        /* String st= "5 packets transmitted, 5 received, 0% packet loss, time 4748m";
         String[] sts = st.split(",");
         System.out.println(sts[2].split("\\s+")[1]);*/

         if(args.length  == 1 ){
             if("ALL".equals(args[0].trim().toUpperCase()) || "INC".equals(args[0].trim().toUpperCase())) {
                 HostAlarm.getInstance().updateWebSiteStatus(args[0]);
             }
             else HostAlarm.getInstance().getWebSiteStatus("www.huishengchina.com");
         }


     }

}
