package com.secray.utils.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
	/**
	 *
	 * 函数名    : getIP
	 * 功   能   : 取得本地主机的IP地址
	 * 参   数   : @param  name
	 * 返回值    : java.lang.String
	 * 编写者    : root
	 * 编写时间  : 2017-07-18 15:36:31 下午 3:36
	 */
	public static String  getIP(String name){

		InetAddress address = null;
		try {
			address = InetAddress.getByName(name);
		} catch (UnknownHostException e) {

			e.printStackTrace();
			System.out.println("获取失败");
		}
		return address.getHostAddress().toString();
	}
	/**
	 *
	 * 函数名    : getHostName
	 * 功   能   : 根据本地IP获取主机名
	 * 参   数   : @param  IP
	 * 返回值    : java.lang.String
	 * 编写者    : root
	 * 编写时间  : 2017-07-18 15:37:34 下午 3:37
	 */
	public static String  getHostName(String IP){

		InetAddress address = null;
		try {
			address = InetAddress.getByName(IP);
		} catch (UnknownHostException e) {

			e.printStackTrace();
			System.out.println("获取失败");
		}
		return address.getHostName();
	}

	/**
	 *
	 * 函数名    : getRemoteHostName
	 * 功   能   : 获取远程主机的主机名称
	 * 参   数   : @param  clientIP
	 * 返回值    : java.lang.String
	 * 编写者    : root
	 * 编写时间  : 2017-07-18 15:38:51 下午 3:38
	 */
	public static String getRemoteHostName(String clientIP){

		String s = "";
		String sb = clientIP.trim().substring(0, 3);
		//System.out.println("clientIP="+clientIP+"\t"+"截取字符串為："+sb);
		if(sb.equals("127")){
			//System.out.println("是127.0.0.1");
			s = getLocalHostName();
		}else{
			//System.out.println("不是本地ip");
			try {
				String s1 = "nslookup "+clientIP; //"nbtstat -a "+clientIP;
				Process process = Runtime.getRuntime().exec(s1);
				BufferedReader bufferedreader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));
				String line = null;
				while( ( line = bufferedreader.readLine()) != null){
					if( line.indexOf("服务器:") > -1 ) {
						return line.split(":")[1].trim();
					}
				}

				bufferedreader.close();
				process.waitFor();
			}catch(Exception exception) {
				s = "";
			}
		}
		return s.trim();
	}

	/**
	 *
	 * 函数名    : getLocalHostName
	 * 功   能   : 得到本地IP的機器名
	 * 参   数   : @param
	 * 返回值    : java.lang.String
	 * 编写者    : root
	 * 编写时间  : 2017-07-18 15:40:35 下午 3:40
	 */
	public static String getLocalHostName() {

		String s = "";
		try {
			String s1 = "ipconfig /all";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String nextLine;
			for (String line = bufferedreader.readLine(); line != null; line = nextLine) {
				nextLine = bufferedreader.readLine();
				if (line.indexOf("Host Name") <= 0) {
					continue;
				}
				int i = line.indexOf("Host Name") + 36;
				s = line.substring(i);
				break;
			}
			bufferedreader.close();
			process.waitFor();
		} catch (Exception exception) {
			s = "";
		}
		return s.trim();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(IPUtil.getIP("www.163.com"));
		System.out.println(IPUtil.getRemoteHostName("116.255.192.241"));
	}

}
