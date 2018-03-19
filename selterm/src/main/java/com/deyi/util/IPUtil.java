package com.deyi.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by root on 2017/6/28 0028.
 */
public class IPUtil {
      public static String getLocalIP(){
          String result = null;
          try
          {
              result =  InetAddress.getLocalHost().getHostAddress();
              System.out.println("本机的IP = " +result);
              if(result == null || result.indexOf("127.0") > -1 ){
                  Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
                  InetAddress ip = null;
                  while (allNetInterfaces.hasMoreElements())
                  {
                      NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                      System.out.println(netInterface.getName());
                      Enumeration addresses = netInterface.getInetAddresses();
                      while (addresses.hasMoreElements())
                      {
                          ip = (InetAddress) addresses.nextElement();
                          if (ip != null && ip instanceof Inet4Address)
                          {
                              result =  ip.getHostAddress();
                              System.out.println("本机的IP = " + ip.getHostAddress());
                          }
                      }
                  }
              }
          } catch (Exception e)
          {
              e.printStackTrace();
          }
         return result;
      }
}
