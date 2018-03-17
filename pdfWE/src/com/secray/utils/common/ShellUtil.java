package com.secray.utils.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class ShellUtil {
	public static final Logger log = Logger.getLogger(ShellUtil.class);


	/**
	 *
	 * 函数名    : executeWithRet
	 * 功   能   : 执行cmd命令，并返回执行结果
	 * 参   数   : @param  shellCmd
	 * 返回值    : java.util.List<java.lang.String>
	 * 编写者    : root
	 * 编写时间  : 2017-07-18 10:03:30 上午 10:03
	 */
	public static List<String> executeWithRet(String shellCmd)
	{
		Process process = null;
		BufferedReader input = null;

		List<String> processList = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec(shellCmd);
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}

			return processList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( input!= null ) input.close();
				input = null;
				process = null;
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
        return null;


	}

	public static String executeCmd(String shellCmd)
	{
		Process process = null;
		BufferedReader input = null;

		String result="";
		try {
			process = Runtime.getRuntime().exec(shellCmd);
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				result += line;
			}
            log.info("executeCmd->cmd : "+shellCmd);
			log.info("executeCmd->result : "+result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( input!= null ) input.close();
				input = null;
				process = null;
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return null;


	}
	public static String execute(String shellCmd,String keyword)
	{
		Process process = null;
		BufferedReader input = null;
        String[] keywords = keyword.split(",");

		try {
			process = Runtime.getRuntime().exec(shellCmd);
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
                for(String kw : keywords){
					if(line.indexOf(kw) > -1 ) return line;
				}
			}

			return "";
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( input!= null ) input.close();
				input = null;
				process = null;
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return null;


	}

	public static String executeRet(String shellCmd)
	{
		String result="";
		Process process = null;
		Scanner scanner = null;
		String[] cmd = { "/bin/sh", "-c", shellCmd };

		try
		{
		    process = Runtime.getRuntime().exec(cmd);

		    scanner = new Scanner(process.getInputStream());
	        String line = null;
	        while(scanner.hasNextLine()){
	        	line = scanner.nextLine();
				if(line.indexOf("Destination Host Unreachable") > -1 ) return line;
	            result += line+"\n";
            	//System.out.println("executeRet[line] : "+line);
	        }




		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			result="-1";
		}
		finally
		{
			try
			{
				//if( is != null) is.close();
				//if( isr != null) isr.close();
				if( scanner != null) scanner.close();

			}
			catch(Exception e )
			{
				e.printStackTrace();
			}
			 process = null;
			 cmd =null;
		}
		//log.info("executeRet : "+shellCmd+" -> "+result);
		//System.out.println("executeRet : "+shellCmd+" -> "+result);
		return result;
	}


	public static String calPacketLoss(String shellCmd)
	{
		String result="";
		Process process = null;
		Scanner scanner = null;
		String[] cmd = { "/bin/sh", "-c", shellCmd };

		try
		{
		//	LogUtil.log("calPacketLoss","exe->begin");
			process = Runtime.getRuntime().exec(cmd);
           // LogUtil.log("calPacketLoss","exec end");
			scanner = new Scanner(process.getInputStream());
			//LogUtil.log("calPacketLoss","scanner end");
			String line = null;

			String lostPackPer = "";
			int i=0;
			double totalLen = 0;

			//LogUtil.log("calPacketLoss","exec-end");

			while(scanner.hasNextLine()){
				//LogUtil.log("calPacketLoss","hasNextLine -> line " );
				line = scanner.nextLine();
				if(StrUtil.trimEnter(line).trim().length() < 3  ) continue;

				//LogUtil.log("calPacketLoss","hasNextLine -> line : " + line);

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
                    break;
				}
				else if(line.indexOf("ttl=") > -1 ) {  //
					String[] values = line.split("\\s+");
					//System.out.println("【calPacketLoss:ttl 】 : "+line+" : "+values.length);
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
			//LogUtil.log("calPacketLoss","hasNextLine end");
			scanner.close();
			process.destroy();

			result = (lostPackPer.trim().length() > 0  ? lostPackPer.replace("%","") : "0")+","+ (i ==0 ? "0" : String.format("%.2f", totalLen/i) );
			//System.out.println("【calPacketLoss 】 : "+ shellCmd + " -> "+  result );
			return result;

		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			result="0,0";
		}
		finally
		{
			try
			{
				//if( is != null) is.close();
				//if( isr != null) isr.close();
				if( scanner != null) scanner.close();

			}
			catch(Exception e )
			{
				e.printStackTrace();
			}
			process = null;
			cmd =null;
		}

		return result;
	}
	public static String cal(String exp)
	{
		String cmd = "echo 'scale=2;"+exp+"'|bc";
		return executeRet(cmd);
	}

	public static int execute(String shellCmd)
	{
		Process process = null;
		InputStream is = null;
		try
		{
		    process = Runtime.getRuntime().exec(shellCmd);
		    is = process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while((line = br.readLine())!=null){


            }

		    return process.exitValue();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			try
			{
				if( is != null) is.close();
			}
			catch(Exception e )
			{
				e.printStackTrace();
			}
		}
		finally
		{
			 process = null;
		}
	    return -1;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(args[0]+" -> "+ShellUtil.cal(args[0]));
		String line="From 104.148.23.92 icmp_seq=1 Packet filtered";
		System.out.println(line.indexOf("icmp_seq") );
	}

}
