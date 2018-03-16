package com.lb.client;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.nio.CharBuffer;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lb.server.util.CardUtil;
import com.lb.server.util.MathUtil;
import com.lb.server.util.JDBC.JDBC;

public class TransferClient {
	private static final Logger log = LoggerFactory.getLogger(TransferClient.class);
	private  Socket socket= null;



	public TransferClient(Socket socket){
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
	public String rechargeError(String errorType,String clientId,String machineId,String cardId,int rechargeMoney,int reserveMoney,String balanceMoney){
		String reserveAmount = MathUtil.decimalToHex(reserveMoney*100,4);
		//计算效验字节
		String validByte= MathUtil.hexXor(cardId+" "+balanceMoney);
		//String permitByte = getPermitFlag(cardId);
		String rechargeCmd = "11 66 "+clientId+" "+machineId+" "+errorType+" "+cardId+" "+
				MathUtil.decimalToHex(rechargeMoney*100,4)+" "+reserveAmount+" "+validByte+" 99";
		String ret =  send(rechargeCmd);
		/*if(ret !=null){
			if(ret.indexOf("0A 66") > -1 ){
				String[] rets = ret.split("\\s+");
				if(rets.length > 9 ){
					return rets[9];
				}
			}
		}
		return "-1";*/
		return rechargeCmd;

	}
	public String recharge(String clientId,String machineId,String cardId,int rechargeMoney,int reserveMoney,String balanceMoney){
		String reserveAmount = MathUtil.decimalToHex(reserveMoney*100,4);
		//计算效验字节
		//3个字节的卡号与充值前的卡上余额高,低字节依次进行异或运算所得的结果。
		// 例如:01 00 04为卡号,mH,mL为卡上余额，即计算方法为:SS=01^00^04^mH^mL
		String validByte= MathUtil.hexXor(cardId+" "+balanceMoney);
		String permitByte = "66";//getPermitFlag(cardId);
		String rechargeCmd = "11 66 "+clientId+" "+machineId+" "+permitByte+" "+cardId+" "+
				MathUtil.decimalToHex(rechargeMoney*100,4)+" "+reserveAmount+" "+validByte+" 99";
		String ret =  send(rechargeCmd);
		if(ret !=null){
			if(ret.indexOf("0A 66") > -1 ){
				String[] rets = ret.split("\\s+");
				if(rets.length > 9 ){
					//return rets[9];
				}
			}
		}
		//return "-1";
		return rechargeCmd;
	}
	private String getPermitFlag(String cardId){
		String[] cardIds = cardId.split("\\s+");
		if(cardIds.length == 3 ){
			String dCardId = CardUtil.getDecialCardNo(cardIds[0], cardIds[1]+cardIds[2]);
			JDBC jdbc = new JDBC(JDBC.DB);
			String sql="SELECT COUNT(1) cnt ,SUM(CASE WHEN IFNULL(reserve_money,0) <=0 THEN 0 ELSE 1 END ) isZero FROM t_card WHERE card_id='"+dCardId+"'";
			System.out.println("[getPermitFlag] : "+sql);
			try
			{
				jdbc.connect();
				String cnt = jdbc.queryForSingle(sql, ",");
				String[] cnts =cnt.split(",");
				int isExist = Integer.parseInt(cnts[0]);
				int isZero = Integer.parseInt(cnts[1]);
				if(isExist==0){
					return "55";
				}
				else{
					if(isZero==0){
						return "22";
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try {
					if(jdbc!=null) jdbc.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return "66";
	}
	public  byte[] convert(String cmd){
		return MathUtil.hexStringToBytes(cmd.replaceAll("\\s+", ""));

	}
	public String send(String cmd){


		OutputStream  writer =  null;
		String ret=null;
		try{

			writer = socket.getOutputStream();

			log.info("cmd="+cmd);
			byte[] cmdByte = this.convert(cmd);
			writer.write(cmdByte);
			//writer.newLine();
			writer.flush();


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



	public static void main(String[] args) {

	}
}
