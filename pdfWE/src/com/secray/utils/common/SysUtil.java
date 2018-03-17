package com.secray.utils.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * 类  名: SysUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月24日 下午5:28:46
 *
 */
public class SysUtil {
    /**
     *
     * 函数名    : getIpAddress
     * 功   能	 : 获取本机地址
     * 参   数    : @return
     * 参   数    : @throws UnknownHostException    设定文件
     * 返回值	 : String    返回类型
     * 编写者    : root
     * 编写时间 : 2017年2月24日 下午5:29:06
     */
    public static String getIpAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        return address.getHostAddress();
    }


    public static String getMACAddress(){

        String address = "";

        String os = System.getProperty("os.name");
        String osUser=System.getProperty("user.name");
        if (os != null && os.startsWith("Windows")) {

            try {

                String command = "cmd.exe /c ipconfig /all";

                Process p = Runtime.getRuntime().exec(command);

                BufferedReader br =new BufferedReader(new InputStreamReader(p.getInputStream()));

                String line;

                while ((line = br.readLine()) != null) {

                    if (line.indexOf("Physical Address") > 0) {

                        int index = line.indexOf(":");

                        index += 2;

                        address = line.substring(index);

                        break;

                    }

                }

                br.close();

                return address.trim();

            }

            catch (IOException e) {
            }

        }
        return address;

    }

    public static String getOS(){
        Properties props=System.getProperties(); //获得系统属性集
        return props.getProperty("os.name");

    }

    public static boolean isWin(){
        String os = getOS();
        if(os.toLowerCase().startsWith("win")){
            return true;
        }
        return false;
    }
}
