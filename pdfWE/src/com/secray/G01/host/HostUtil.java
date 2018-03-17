package com.secray.G01.host;

import com.secray.utils.common.LogUtil;
import com.secray.utils.common.ShellUtil;
import com.secray.utils.common.StrUtil;
import org.apache.log4j.Logger;

/**
 * Created by root on 2017/7/31 0031.
 */
public class HostUtil {


    public static String[]  getWebSiteStatus(String domain){

        String pingInfo = ShellUtil.executeRet("ping "+ domain + " -c 3" );

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
                LogUtil.log("【getWebSiteStatus:icmp_seq 】",line+" : "+values.length);
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
        LogUtil.log("getWebSiteStatus  ",result);
        return result.split(",");

    }
    public static String[]  getWebSiteStatusQuick(String domain){
        String lostPackPer = "100,0";
        String httpCode = ShellUtil.executeRet(" curl -sL -w '%{http_code}' '"+ domain + "' -o /dev/null" );
        httpCode = StrUtil.trimEnter(httpCode);
        httpCode = (httpCode.equals("200") ? "1" : "0");

        if("1".equals(httpCode)){
            lostPackPer = ShellUtil.calPacketLoss("ping "+ domain + " -c 3");

        }

        lostPackPer = lostPackPer+","+httpCode;
       // LogUtil.log("getWebSiteStatusQuick", lostPackPer);
        return lostPackPer.split(",");
    }
}
