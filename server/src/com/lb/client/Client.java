package com.lb.client;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.nio.CharBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lb.server.util.MathUtil;

public class Client {
	private static final Logger log = LoggerFactory.getLogger(Client.class);
	private  Socket socket= null; 
	/*private String ip;
	private int port;
	private String clientId;
	private String machineId;*/


	public Client(Socket socket){
		this.socket = socket;
	}



	public void hbFeedback(String clientId,String machineId){

		String consumeCmd = "09 66 "+clientId+" "+machineId+" 11 99";
		String ret =  send(consumeCmd);


	}

	public String query(String clientId,String machineId){

		String consumeCmd = "09 66 "+clientId+" "+machineId+" 22 99";
		String ret =  send(consumeCmd);
		if(ret !=null){
			if(ret.indexOf("09 66") > -1 ){
				String[] rets = ret.split("\\s+");
				if(rets.length > 9 ){
					return rets[9];
				}
			}
		}
		return "-1";
	}

	public String consume(String clientId,String machineId,int money,int timeLen){

		int fenMoney = money*100;
		String consumeCmd = "0D 66 "+clientId+" "+machineId+" 06 "+MathUtil.decimalToHex(fenMoney,4)+" "+MathUtil.decimalToHex(timeLen,4)+" 99";
		String ret =  send(consumeCmd);
		if(ret !=null){
			if(ret.indexOf("0A 66") > -1 ){
				String[] rets = ret.split("\\s+");
				if(rets.length > 9 ){
					return rets[9];
				}
			}
		}
		return "-1";
	}
	public  byte[] convert(String cmd){
		return MathUtil.hexStringToBytes(cmd.replaceAll("\\s+", ""));
		//String[] cmds = cmd.split("\\s+");
		/*byte[] ret = new byte[cmd.length()];
		for(int i=0; i< cmds.length; i++){
			ret[i] = (byte)Integer.parseInt(cmds[i],16);
		}
		*/
		//String st= cmd.replaceAll("\\s+","");
		/*byte[] ret = new byte[cmd.toCharArray().length];
		char[] chrs = cmd.toCharArray();
		for(int i=0; i< chrs.length; i++) {
			System.out.println(chrs[i]+"->"+(byte)chrs[i]);
			ret[i] = (byte)chrs[i];
		}*/

		//byte[] ret = cmd.getBytes();
		//return ret;
	}
	public String send(String cmd){

		//BufferedReader reader = null;

		//BufferedWriter writer =  null;
		//DataInputStream inputStream ;
		OutputStream  writer =  null;
		String ret=null;
		try{
			//inputStream = new DataInputStream(socket.getInputStream());
			//reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = socket.getOutputStream();
			//writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			log.info("cmd="+cmd);
			byte[] cmdByte = this.convert(cmd);
			writer.write(cmdByte);
			//writer.newLine();
			writer.flush();
            /*
            String chr = null;
            byte[] buf=new byte[4096];
            int length =0;
            String requestXmlStr="";
            
	        while((length = inputStream.read(buf))!=-1){
	            byte[] tmp=new byte[length];
	            System.arraycopy(buf, 0, tmp, 0, length);
	            chr =  MathUtil.bytesToHexString(tmp);
	            log.info("[send] :chr="+chr+"");
	           
	            requestXmlStr += chr;
	            //log.info("[getRecieve] :"+requestXmlStr);
	        }
	        log.info("[send] : requestXmlStr="+requestXmlStr);*/
           /* if (reader.ready()) {
            	ret = reader.readLine();
            }*/

		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
        	 /*try {
				//if(inputStream != null) inputStream.close();
				 if(writer != null) writer.close();
				 if(socket != null) socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		return ret;
	}
	class SendThread extends Thread{
		private Socket socket;
		public SendThread(Socket socket){
			this.socket=socket;
		}
		@Override
		public void run(){
			while(true){
				try{
					Thread.sleep(1000);
					String send="<SOAP-ENV:Envelope>"+System.currentTimeMillis()+"</SOAP-ENV:Envelope>";
					log.info(send);
					PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					pw.write(send+"\n");
					pw.flush();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	class ReceiveThread extends Thread{
		private Socket socket;
		public ReceiveThread(Socket socket){
			this.socket=socket;
		}
		@Override
		public void run(){
			while(true){
				try{
					Reader reader=new InputStreamReader(socket.getInputStream());
					CharBuffer charbuffer=CharBuffer.allocate(8192);
					int index=-1;
					while((index=reader.read(charbuffer))!=-1){
						charbuffer.flip();//设置从0到刚刚读取到的位置
						System.out.println("client:"+charbuffer.toString());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	public void start(){
		try{

			new SendThread(socket).start();
			new ReceiveThread(socket).start();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	    /*
	     * 参数:
	     *   0 : 类型
	     *   1 : IP
	     *   2 : 端口
	     *   3 : 客户编号
	     *   4 : 机器编号
	     *   5 : 套餐价格
	     *   6 : 套餐时长
	     */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new Client().start();
			 /*if(args.length >= 3  ){
				 for(int i = 0 ; i< args.length; i++)  log.info("args["+i+"]="+args[i]);
				 
				 Client client = new Client(args[1],args[2]);
				 if("query".equals(args[0].toLowerCase())){
					 client.query();
				 }
				 else{
					 client.consume( Integer.parseInt(args[3]), Integer.parseInt(args[4]));
				 }
			 }*/
	}
}
