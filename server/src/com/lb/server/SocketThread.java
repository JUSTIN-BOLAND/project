package com.lb.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.lb.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lb.client.Client;
import com.lb.client.TransferClient;
import com.lb.server.util.CardUtil;
import com.lb.server.util.MathUtil;
import com.lb.server.util.JDBC.JDBC;



public class SocketThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(SocketThread.class);


	public int socketID;

	public Socket socket;//客户端的Socket

	public BufferedWriter writer;

	public BufferedReader reader;
	public DataInputStream inputStream ;

	public String userId;//客户端的UserId

	private long lastTime;
	private Server server;



	public SocketThread(Server server,Socket socket, int count) {

		socketID = count;

		this.socket = socket;

		this.server = server;

		lastTime = System.currentTimeMillis();

	}



	@Override

	public void run() {

		super.run();



		try {
			inputStream = new DataInputStream(socket.getInputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			//循环监控读取客户端发来的消息

			while (this.server.isStartServer()) {

				// 超出了发送心跳包规定时间，说明客户端已经断开连接了这时候要断开与该客户端的连接

				long interval = System.currentTimeMillis() - lastTime;

				if (interval >= (this.server.getSOCKET_ACTIVE_TIME() * 1000 )) {

					log.info("客户端发包间隔时间严重延迟，可能已经断开了interval：" + interval);

					log.info("Custom.SOCKET_ACTIVE_TIME * 1000:" + this.server.getSOCKET_ACTIVE_TIME() * 1000);
					updateShutdownStatus();
					this.server.closeSocketClient(this);

					break;

				}

				if (reader.ready()) {
					//getRecieve(socket);
					lastTime = System.currentTimeMillis();
					String chr = null;
					byte[] buf=new byte[4096];
					int length =0;


					while((length = inputStream.read(buf))!=-1){
						byte[] tmp=new byte[length];
						System.arraycopy(buf, 0, tmp, 0, length);
						//chr = new String(tmp,"utf-8");
						//chr = MathUtil.bytesToHexString(chr.getBytes("GB2312"));
						chr =  MathUtil.bytesToHexString(tmp);
						if(chr!=null){
							String[] sts = chr.split("\\s+");
							//if(sts.length > 8)
							log.info("[run] :chr="+chr+"");
						}
						handleRecieve(chr);
						//handleTask();
						//requestXmlStr += chr;
						//log.info("[getRecieve] :"+requestXmlStr);
					}
                 /*  // log.info("收到消息，准备解析:");

                    char[] cbuf = new char[100];
                    int value = 0;
                    while ((value = reader.read()) != -1) {
                      char c = (char) value;
                      String charStr = String.valueOf(c);
                      charStr = MathUtil.bytesToHexString(charStr.getBytes());
                      //if(charStr== null || charStr.trim().length() ==0 ) continue;
                      //System.out.println("[lb["+this.socket.getInetAddress()+":"+this.socket.getPort()+"] : "+c+"->"+charStr);
                    }
                  */


                  /*  SocketMessage from = null;//Util.parseJson(data);

                    //给UserID赋值，此处是我们项目的需求，根据客户端不同的UserId来分别进行推送

                    if (userId == null || "".equals(userId))

                        userId = from.getUserId();

                    SocketMessage to = new SocketMessage();

                    if (from.getType() == Custom.MESSAGE_ACTIVE) {//心跳包

                        log.info("收到心跳包：" + socket.getInetAddress());

                        to.setType(Custom.MESSAGE_ACTIVE);

                        to.setFrom(Custom.NAME_SERVER);

                        to.setTo(Custom.NAME_CLIENT);

                        to.setMessage("");

                        to.setUserId(userId);

                        writer.write(Util.initJsonObject(to).toString() + "\n");

                        writer.flush();

                    } else if (from.getType() == Custom.MESSAGE_CLOSE) {//关闭包

                        System.out.println("收到断开连接的包：" + socket.getInetAddress());

                        to.setType(Custom.MESSAGE_CLOSE);

                        to.setFrom(Custom.NAME_SERVER);

                        to.setTo(Custom.NAME_CLIENT);

                        to.setMessage("");

                        to.setUserId(userId);

                        //writer.write(Util.initJsonObject(to).toString() + "\n");

                        writer.flush();

                        closeSocketClient(this);

                        break;

                    } else if (from.getType() == Custom.MESSAGE_EVENT) {//事件包，客户端可以向服务端发送自定义消息

                        System.out.println("收到普通消息包：" + from.getMessage());

                    }


                */
				}
				Thread.sleep(100);

			}



		} catch (Exception e) {

			e.printStackTrace();

		}



	}
	private void updateShutdownStatus(){
		JDBC jdbc = new JDBC(JDBC.DB);



		String sql=	null;
		try
		{

			jdbc.connect();



			sql = "select count(1) from t_machine_ip a "+
					"WHERE  a.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND a.port='"+this.socket.getPort()+"' and status!='99'";
			//log.info("sql="+sql);
			if(jdbc.queryForInt( sql) > 0 ){
				sql=	"update t_machine_ip a set a.status='99' "+
						"WHERE  a.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND a.port='"+this.socket.getPort()+"'";
				jdbc.update(sql);
				sql="UPDATE t_task a SET a.status='99'  "+
						"WHERE CONCAT(a.client_id,a.machine_id) IN(  "+
						"SELECT CONCAT(b.client_id,b.machine_id) FROM t_machine_ip b  "+
						"where  b.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND b.port='"+this.socket.getPort()+"') "+
						" and   TO_DAYS(a.insert_date)=TO_DAYS(NOW())";
				log.info("[updateShutdownStatus] : "+sql);
				jdbc.update(sql);

                //更新设备状态
                //获取16进制的设备编号
                sql = "select machine_id  from t_machine_ip a "+
                        "WHERE  a.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND a.port='"+this.socket.getPort()+"'";
                String machineId = jdbc.queryForStr(sql);
                if(!"-1".equals(machineId)) {
                    //获取设备id
                    String deviceCde = MathUtil.hexToDecimal(machineId.replace(" ", ""));
                    sql = "select STATUS from (select STATUS from t_device where deviceCode='" + deviceCde + "' limit 1 UNION ALL SELECT '-1') a limit 1";
					System.out.println("[updateShutdownStatus] : "+sql);
                    String deviceStatus = jdbc.queryForStr(sql);
                    log.info("[hb] : deviceStatus=" + deviceStatus + ",sql=" + sql);
                    if (!"-1".endsWith(deviceStatus) && "0".equals(deviceStatus)) {
                        sql = "update t_device set STATUS='1' where deviceCode = '" + deviceCde + "' limit 1";
                        jdbc.update(sql);
                    }
                }
			}

		}
		catch(Exception e)
		{
			/*try {
				jdbc.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			e.printStackTrace();
		}

		finally
		{
			try
			{

				if(jdbc != null) jdbc.close();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	/*private void handleTask(){
		JDBC jdbc = new JDBC(JDBC.DB);
		JDBC uJdbc = new JDBC(JDBC.DB);
		ResultSet rs = null;
		String clientId, machineId;
		int money, timeLen;
		int taskType = 0;
		Client client= new Client(this.socket);
		String sql=	"SELECT b.* "+
				"FROM t_machine_ip a, t_task b "+
				"WHERE a.client_id = b.client_id  "+
				"   AND a.machine_id = b.machine_id "+
				"   AND a.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND a.port='"+this.socket.getPort()+"' and b.flag=0";
		try
		{
			jdbc.connect();
			uJdbc.connect();
			//log.info("[handleTask] : sql="+sql);
			rs = jdbc.query(sql);
			while(rs.next()){
				clientId = rs.getString("client_id");
				machineId = rs.getString("machine_id");
				taskType = rs.getInt("task_type");
				if( taskType == 0 ) client.query(clientId, machineId);
				else if( taskType == 1 ) {
					money = rs.getInt("money");
					timeLen = rs.getInt("time_len");
					client.consume(clientId, machineId, money, timeLen);
				}

				uJdbc.update("update t_task set flag=1 where id="+rs.getInt("id"));

			}
		}
		catch(Exception e)
		{
			*//*try {
				jdbc.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*//*
			e.printStackTrace();
		}

		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(jdbc != null) jdbc.close();
				if(uJdbc != null) uJdbc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}*/
	/*private String getRecieve(Socket socket) {

		String requestXmlStr = "";
		String chr = null;
		byte[] buf=new byte[4096];
		int length =0;

		try{
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			while((length = inputStream.read(buf))!=-1){
				byte[] tmp=new byte[length];
				System.arraycopy(buf, 0, tmp, 0, length);
				//chr = new String(tmp,"utf-8");
				//chr = MathUtil.bytesToHexString(chr.getBytes("GB2312"));
				chr =  MathUtil.bytesToHexString(tmp);
				//log.info("[getRecieve] :"+chr+" -> "+MathUtil.getEncoding(String.valueOf(tmp))+" -> "+MathUtil.getEncoding(chr));
				//requestXmlStr += chr;
				//log.info("[getRecieve] :"+requestXmlStr);
			}
			log.info("[getRecieve] : 接收到的报文为：" + requestXmlStr);
			return requestXmlStr;
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		return null;
	}*/

	private void handleRecieve(String recieveMsg){
		if(recieveMsg!=null && recieveMsg.trim().length()> 0 ){
			int begin = recieveMsg.indexOf("66");
			int end = recieveMsg.indexOf("99");
			if(begin==-1 || end ==-1 || begin>=end ) return;

			String recieve = recieveMsg.substring(begin, end+2);
			String[] recieves = recieve.split("\\s+");
			if(recieves.length > 3 ){
				//log.info("[handleRecieve] : recieves="+recieves.length);
				String code= recieves[recieves.length -3];
				String status= recieves[recieves.length -2];
				String clientId = null;
				String machineId = null;
				JDBC jdbc = new JDBC(JDBC.DB);
				String sql=null;
				try
				{
					jdbc.connect();
					//心跳
					if(recieves.length == 8 || recieves.length == 9){
						String hbCode = recieves[recieves.length -2];
						if("55".equals(hbCode)){
							String ip = this.socket.getInetAddress().getHostAddress();
							String port = this.socket.getPort()+"";

			    			/*clientId = recieves[2]+" "+recieves[3]+" "+recieves[4]+" "+recieves[5];
			    			machineId = recieves[6]+" "+recieves[7];
			    			if(recieves.length == 8){*/
							clientId = recieves[1]+" "+recieves[2]+" "+recieves[3]+" "+recieves[4];
							machineId = recieves[5]+" "+recieves[6];
							//}

							sql = "select count(1) from t_machine_ip where client_id='"+clientId+"' and machine_id='"+machineId+"'";

							if(jdbc.queryForInt( sql) == 0 ){

								sql="INSERT INTO t_machine_ip(client_id,machine_id,ip,PORT,status )"+
										"VALUES('"+clientId+"','"+machineId+"','"+ip+"','"+port+"','41')";
							}
							else{
								sql=" UPDATE t_machine_ip SET "+
										"ip = '"+ip+"' , "+
										"PORT = '"+port+"',status='41' "+
										"WHERE " +
										"client_id = '"+clientId+"' AND  "+
										"machine_id = '"+machineId+"' ";
							}
							//log.info("[handleRecieve->hb] :  "+sql);
							jdbc.update(sql);
							//更新设备状态
							//获取设备id
							String deviceCde = CardUtil.getDecialDeviceCode(machineId.replace(" ",""));//MathUtil.hexToDecimal(machineId.replace(" ",""));
					/*		sql = "select STATUS from (select STATUS from t_device where deviceCode='" + deviceCde + "' and flag='0' limit 1 UNION ALL SELECT '-1') a limit 1";
							System.out.println("[handleRecieve] : "+sql);
                            String deviceStatus = jdbc.queryForStr(sql);
                            log.info("[hb] : deviceStatus="+deviceStatus+",sql="+sql);
                            if(!"-1".endsWith(deviceStatus) && "1".equals(deviceStatus)){*/
                                sql="update t_device set machineStatus='0' where deviceCode = '"+deviceCde+"' and flag='0' limit 1";
                                jdbc.update(sql);
                            //}
							//发送心跳回执

							Client client = new Client(this.socket);
							client.hbFeedback(clientId, machineId);
							//发送查询指令
							//client.query(clientId, machineId);
						}

					}
					else if((Constant.CONSUME_CODE.equals(code)  //消费
							|| Constant.QUERY_CODE.equals(code) //查询
							||Constant.SHUTDOWN_CODE.equals(code) ) && recieves.length < 11){ //关机
						jdbc.connect();

						log.info("[handleRecieve->consume] : "+recieve);
						clientId = recieves[2]+" "+recieves[3]+" "+recieves[4]+" "+recieves[5];
						machineId = recieves[6]+" "+recieves[7];
						if(recieves.length == 10){
							clientId = recieves[1]+" "+recieves[2]+" "+recieves[3]+" "+recieves[4];
							machineId = recieves[5]+" "+recieves[6];
						}
						String taskType = "";
						String deviceCde = CardUtil.getDecialDeviceCode(machineId.replace(" ",""));//MathUtil.hexToDecimal(machineId.replace(" ", ""));
						sql="SELECT deviceType FROM t_device a WHERE a.deviceCode='"+deviceCde+"' ";
						String deviceType = jdbc.queryForStr(sql);
						int devType = Integer.parseInt(deviceType);
						if(devType == Constant.MESSAGE_ARECHAIR) { //按摩椅
							if (Constant.CONSUME_CODE.equals(code)) taskType = "1";
							else if (Constant.QUERY_CODE.equals(code)) taskType = "0";
							else if (Constant.SHUTDOWN_CODE.equals(code)) taskType = "2";
							sql = " UPDATE t_task SET " +
									"status = '" + status + "' " +

									"WHERE " +
									"client_id = '" + clientId + "' AND  " +
									"machine_id = '" + machineId + "' and status='0' and task_type=" + taskType;
							log.info("[handleRecieve->" + taskType + "->1]" + sql);
							jdbc.update(sql);

							sql = "update t_machine_ip set status='" + status + "' " +
									"WHERE " +
									"client_id = '" + clientId + "' AND  " +
									"machine_id = '" + machineId + "'";
							log.info("[handleRecieve->" + taskType + "->2]" + sql);
							jdbc.update(sql);
						}
						else if(devType == Constant.TRANSFER_MACHINE) { //圈存机的查询指令的结果

						}

						//所有设备都发送查询指令，并更新设备的业务状态
						if (Constant.QUERY_CODE.equals(code)){
							if("40".equals(status)) status="2";
                            else if("41".equals(status)) status="0";
                            else if("33".equals(status)) status="3";
							sql="update t_device a set machineStatus='"+status+"' WHERE a.deviceCode='"+deviceCde+"' ";
							jdbc.update(sql);

						}



					}
					else if(recieves.length ==14 || recieves.length ==15 ){//圈存
						code= recieves[recieves.length -7];
						clientId = recieves[2]+" "+recieves[3]+" "+recieves[4]+" "+recieves[5];
						machineId = recieves[6]+" "+recieves[7];
						String hexBatchNo = recieves[9];
						String batchNo =  MathUtil.hexToDecimal(hexBatchNo);
						//长卡号
						String cardId = recieves[9]+" "+recieves[10]+" "+recieves[11];
						//计算10进制的短卡号
						//String hexCardNo = MathUtil.hexToDecimal(recieves[10]+recieves[11]);
						String cardNo = CardUtil.getDecialCardNo("",recieves[10]+recieves[11]);
						if(recieves.length == 14){
							clientId = recieves[1]+" "+recieves[2]+" "+recieves[3]+" "+recieves[4];
							machineId = recieves[5]+" "+recieves[6];
							hexBatchNo = recieves[8];
							batchNo =  MathUtil.hexToDecimal(hexBatchNo);
							cardId = recieves[8]+" "+recieves[9]+" "+recieves[10];
							//hexCardNo = MathUtil.hexToDecimal(recieves[9]+recieves[10]);
							cardNo = CardUtil.getDecialCardNo("",recieves[9]+recieves[10]);
						}

						System.out.println("[lb] : cardId="+cardId);
						if(Constant.TRANS_RECHARGE_CODE.equals(code)){

							//sql="select count(1) FROM t_card a WHERE a.cardNo='"+cardId+"'";
							TransferClient client = new TransferClient(this.socket);


							String balanceMoney = recieves[12]+" "+recieves[13];
							if(recieves.length == 14){
								//clientId = recieves[1]+" "+recieves[2]+" "+recieves[3]+" "+recieves[4];
								//machineId = recieves[5]+" "+recieves[6];
								//cardId = recieves[8]+" "+recieves[9]+" "+recieves[10];
								balanceMoney = recieves[11]+" "+recieves[12];
							}
							jdbc.connect();
							/*//更新卡的批次号
							sql="update t_card set batchNo = '"+batchNo+"' where cardNo='"+cardNo+"'";
							jdbc.update(sql);*/
							batchNo = (batchNo.length() < 2 ? "0"+batchNo : batchNo);
							//更新卡的余额
							int cardBalance = Integer.parseInt(MathUtil.hexToDecimal(balanceMoney.replaceAll("\\s+","")));
							double dCardBalance = cardBalance /100;

							String sendCmd = null;
							String[] rets = {"","","",null};
							int needChargeMoney=0;
							String cardStautses[] = {"",""};
							String businessStatus = "2";
							//卡号不存在//充值前，判断卡号是否存在
							sql="select balance from t_card where   cardNo='"+(hexBatchNo+cardNo)+"'";

							String reserveMoney = jdbc.queryForStr(sql);
							//获取设备id
							String deviceCode = CardUtil.getDecialDeviceCode(machineId.replace(" ",""));//MathUtil.hexToDecimal(machineId.replace(" ",""));
							sql="select id from t_device where deviceCode = '"+deviceCode+"' limit 1";
							System.out.println("[lb->device->sql] : sql="+sql);
							String deviceId = jdbc.queryForStr(sql);
							if(deviceId==null || deviceId.trim().length() ==0 ) deviceId="-1";
							System.out.println("[lb] : batchNo="+batchNo+" ,cardNo="+cardNo+", reserveMoney="+reserveMoney+",deviceId="+deviceId);
							if("-1".equals(reserveMoney)){
								sendCmd = client.rechargeError(Constant.TRANS_CARD_NOT_ACCOUNT_CODE,clientId, machineId, cardId, 0, 0,balanceMoney);
							}
							else{


								sql="select id,ifnull(status,'0') as status, ifnull(flag,'0') as flag from t_card where  cardNo='"+(hexBatchNo+cardNo)+"'";
								System.out.println("[lb->cardStatus->sql] : sql="+sql);
								String cardStauts = jdbc.queryForSingle(sql,",");
								if(cardStauts==null || cardStauts.trim().length() ==0 ) cardStauts="1,1,1";
								//System.out.println("[lb->2] : cardStauts="+cardStauts);
								cardStautses = cardStauts.split(",");
								System.out.println("[lb->cardStatus] : status="+cardStautses[1]+" ,flag="+cardStautses[2]);
								if("1".equals(cardStautses[1]) || "1".equals(cardStautses[2])){
									sendCmd = client.rechargeError(Constant.TRANS_CARDLOST_CODE,clientId, machineId, cardId, 0, 0,balanceMoney);
								}
								else {
									//取得卡的最大充值次数
                                     sql="select * from (SELECT CASE WHEN  IFNULL(c.dayCnt,0)<=b.dayMaxCount THEN 0 ELSE 1 END dayFlag," +
											 "CASE WHEN IFNULL(c.cnt,0) <= b.monthMaxCount THEN 0 ELSE 1 END monthFlag,b.singleMaxAmount,b.id\n" +
											 "FROM t_card a JOIN t_dealer b ON a.creator = b.userId\n" +
											 "LEFT JOIN (\n" +
											 "\tSELECT c.cardNo,COUNT(1) cnt,IFNULL(SUM(CASE WHEN DATE_FORMAT(c.creatTime, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') THEN 1 ELSE 0 END),0) dayCnt\n" +
											 "\tFROM t_recharge c \n" +
											 "\tWHERE DATE_FORMAT(c.creatTime, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')\n" +
											 "\tAND c.authCode='"+(hexBatchNo+cardNo)+"'\n" +
											 "\tGROUP BY c.cardNo\n" +
											 ") c ON a.id = c.cardNo \n"+
									         " where   a.cardNo='"+(hexBatchNo+cardNo)+"'"+
									         "union all select 0,0,0,-1 ) a  limit 1";
									System.out.println("[lb->maxCnt->sql] : sql="+sql);
									String ret = jdbc.queryForSingle(sql,",");
									//System.out.println("[lb->3] : ret="+ret);
									System.out.println("[lb->maxCnt] : ret="+ret);
									if(ret.length() > 0 ){
										 rets = ret.split(",");
										System.out.println("[lb->maxCnt] : dayMaxCnt="+rets[0]+" ,monthMaxCnt="+rets[1]);
										if("1".equals(rets[0]) || "1".equals(rets[1])){
											sendCmd = client.rechargeError(Constant.TRANS_EXCEED_MAX_CODE,clientId, machineId, cardId, 0, 0,balanceMoney);
										}
										else{
											//判断充值金额，账户余额>=单次最大金额，则充值单次最大金额，否则，充值账户余额

											int reserve = Integer.parseInt(reserveMoney.replace(".00",""));
											 needChargeMoney = Integer.parseInt(rets[2].replace(".00",""));
											int leftMoney = 0 ;
											if(reserve <= needChargeMoney) needChargeMoney = reserve;
											else leftMoney =reserve - needChargeMoney;
											//发送充值指令
											sendCmd = client.recharge(clientId, machineId, cardId, needChargeMoney, reserve,balanceMoney);
											//记录充值流水
											if(cardNo.length()<5) cardNo = "0"+cardNo;
											businessStatus = "0"; //已充值
											/*sql="insert into t_recharge(client_id,machine_id,task_type,card_id,hex_card_id,reserve_money,recharge_money,balance_money,STATUS,flag,insert_date) "+
													"values('"+clientId+"','"+machineId+"',1,'"+cardNo+"','"+cardId+"','"+reserveMoney+"','"+reserveMoney+"','"+cardBalance+"',null,0,now())";*/

										}
									}
								}

							}
							String orderNo = genNo("P");
							sql="INSERT INTO selterm.t_recharge" +
									"(" +
									"orderNo," +
									"dealerId," +
									"deviceId," +
									"payType," +
									"outTradeNo," +
									"authCode," +
									"buyerId," +
									"cardNo," +
									"serviceType," +
									"payAmount," +
									"actualAmount," +
									"beforeAmount," +
									"afterAmount," +
									"subMerchantId," +
									"STATUS," +
									"payStatus," +
									"payTime," +
									"creatTime," +
									"operatorId," +
									"memo," +
									"planId," +
									"sendCmd," +
									"recieveCmd," +
									"hexSerial," +
									"hexDeviceCode," +
									"updateStatus," +
									"hexCardNo," +
									"hexCardBatchNo,"+
									"cardBatchNo"+
									") " +
									"VALUES" +
									"(" +
									"'"+orderNo+"'," +
									""+rets[3]+"," +
									""+("-1".equals(deviceId)?null:deviceId)+"," +
									"3," +
									"null," +
									"'"+(hexBatchNo+cardNo)+"'," +
									"null," +
									"'"+cardStautses[0]+"'," +
									"'2'," +
									""+needChargeMoney+"," +
									""+needChargeMoney+"," +
									""+dCardBalance+"," +  //操作前余额--充值卡的余额
									""+(dCardBalance+needChargeMoney)+"," +
									"null," +
									"'"+businessStatus+"'," +
									"'1'," +
									"now()," +
									"now()," +
									"null," +
									"null," +
									"null," +
									"'"+sendCmd+"'," +
									"null," +
									"'"+clientId+"'," +
									"'"+machineId+"'," +
									"'0'," +
									"'"+cardId+"'," +
									"'"+hexBatchNo+"',"+
									"'"+batchNo+"'"+
									")";
							//System.out.println("[lb->recharge] : sql="+sql);
							//String dSql="insert into t_debug(a,b) values( '1','"+sql.replace("'","")+"') ";
							//System.out.println("[lb->4] : dSql="+dSql);
							//jdbc.update(dSql);
							//System.out.println("[lb->4] : sql="+sql);
							jdbc.update(sql);
						}
						else if(Constant.TRANS_RECHARGE_CODE.equals(recieves[0]) && recieves.length == 14){ //充值返回的结果 66 SN1 SN2 SN3 SN4 [NO1] [NO2] MM 01 00 04 00 00 99
							status= recieves[recieves.length -7];
							System.out.println("[lb->recieve] : status="+status+",recieveMsg="+recieveMsg);
							jdbc.connect();


							if("33".equals(status)){
								sql="select authCode,payAmount,cardBatchNo from  t_recharge "+
										" where hexSerial='"+clientId+"' and hexDeviceCode='"+machineId+"' and hexCardNo='"+cardId+"' and cardBatchNo='"+hexBatchNo+"' and payType=3 and updateStatus=0";
								String idBalance = jdbc.queryForSingle(sql,",");

								System.out.println("[handleRecieve->1] : idBalance="+idBalance);
								if(idBalance.length() > 0 ) {
									String[] idBalances = idBalance.split(",");
									//卡的账号余额减去充值成功的金额
									sql = "update t_card set balance=balance-" + idBalances[1] + " where cardNo='" +( idBalances[2]+ idBalances[0]) + "'";
									System.out.println("[handleRecieve->11] : "+sql);
									jdbc.update(sql);
								}
								status="1"; //成功
							}
							else status="2"; //失败
							//更新充值明细表的业务状态
							sql="update t_recharge set status='"+status+"',updateStatus='1',recieveCmd='"+recieve+"'"+
									" where hexSerial='"+clientId+"' and hexDeviceCode='"+machineId+"' and hexCardNo='"+cardId+"' and cardBatchNo='"+hexBatchNo+"' and payType=3 and status=0";
							System.out.println("[handleRecieve->2] : "+sql);
							jdbc.update(sql);
						}
					}
					else {
						//非空闲，查询客户端的状态
						sql = "select count(1) from t_machine_ip a "+
								"WHERE  a.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND a.port='"+this.socket.getPort()+"' and status='41'";
						//log.info("sql="+sql);
						if(jdbc.queryForInt( sql) == 0 ){
							sql = "select count(1) from t_task a "+
									"WHERE CONCAT(a.client_id,a.machine_id) IN(  "+
									"SELECT CONCAT(b.client_id,b.machine_id) FROM t_machine_ip b  "+
									"where  b.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND b.port='"+this.socket.getPort()+"') "+
									" AND a.task_type=0 AND TIME_TO_SEC(TIMEDIFF(NOW(),a.insert_date)) < 60";
							if(jdbc.queryForInt( sql) <= 3 ){
								sql = "INSERT INTO t_task (client_id, machine_id, task_type, money,time_len,STATUS,flag,insert_date) "+
										"SELECT b.client_id,b.machine_id,0,null,null,null,0,now() FROM t_machine_ip b  "+
										"where  b.ip='"+this.socket.getInetAddress().getHostAddress()+"' AND b.port='"+this.socket.getPort()+"' ";
								jdbc.update(sql);
								//Thread.sleep(3000);
							}
						}
					}
				}
				catch(Exception e)
				{
	    			/*try {
						jdbc.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
					e.printStackTrace();
				}

				finally
				{
					try
					{
						if(jdbc != null) jdbc.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	public String genNo(String prefix){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		return prefix+df.format(c.getTime()) + RandomUtil.randomNum(6);
	}
}