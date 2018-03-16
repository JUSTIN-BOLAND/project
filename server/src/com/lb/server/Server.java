package com.lb.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lb.client.Client;
import com.lb.server.util.JDBC.JDBC;

public class Server {
	private static final Logger log = LoggerFactory.getLogger(Server.class);

	private int count = 0;

	private boolean isStartServer = false;

	private int port= 7002;
	private String interval="1s";

	private int SOCKET_ACTIVE_TIME = 60 ; //单位是秒



	private ArrayList<SocketThread> mThreadList = new ArrayList<SocketThread>();


	public void Server(){

	}
	public void Server(int port, String interval){
		this.port = port;
		this.interval = interval;
	}

	public boolean isStartServer() {
		return isStartServer;
	}
	public int getSOCKET_ACTIVE_TIME() {
		return SOCKET_ACTIVE_TIME;
	}

	/**

	 * 开启服务端的Socket

	 * @throws IOException

	 */

	public void start() throws IOException {

		//initDeviceStatus();
		// 启动服务ServerSocket，设置端口号

		ServerSocket ss = new ServerSocket(this.port);

		log.info("服务端已开启，等待客户端连接:");

		isStartServer = true;

		int socketID = 0;

		Socket socket = null;

		startMessageThread();

		while (isStartServer) {

			// 此处是一个阻塞方法，当有客户端连接时，就会调用此方法

			socket = ss.accept();

			System.out.println("客户端连接成功" + socket.getInetAddress()+":"+socket.getPort());

			// 4. 为这个客户端的Socket数据连接

			SocketThread thread = new SocketThread(this,socket, socketID++);

			thread.start();

			mThreadList.add(thread);

		}

	}

   private void initDeviceStatus() {
	   JDBC jdbc = new JDBC(JDBC.DB);


	   String sql = "update t_device set status='1'";
	   try {

		   jdbc.connect();
		   jdbc.update(sql);

	   } catch (Exception e) {
			try {
				jdbc.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   e.printStackTrace();
	   } finally {
		   try {

			   if (jdbc != null) jdbc.close();

		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
   }

	private void startMessageThread() {
		int sendInteval = 0 ;
		String suffix = interval.substring(interval.length()-1,interval.length());
		try {
			sendInteval = Integer.parseInt(interval.toUpperCase().replaceAll("[H|M|S]", ""));
		} catch (Exception e) {
			sendInteval = 30;
			suffix = "S";
		}

		if("M".equals(suffix.toUpperCase())){
			sendInteval = sendInteval * 60;
		}
		else if("H".equals(suffix.toUpperCase())){
			sendInteval = sendInteval * 60 * 60;
		}
		new Timer().schedule(new TimerTask() {



			@Override

			public void run() {

				try {

					for (SocketThread st : mThreadList) {// 分别向每个客户端发送消息

						if (st.socket == null || st.socket.isClosed())

							continue;

	                       /* System.out.println("客户端的userId：" + st.userId + "  消息编号：" + count);

	                        if (st.userId == null || "".equals(st.userId))// 如果暂时没有确定Socket对应的用户Id先不发

	                            continue;*/

						String content = "我是从服务器发来的消息：" + count;

						handleTask(st.socket);
	                        /*
	                        // 根据userId模拟服务端向不同的客户端推送消息

	                        if (count % 2 == 0) {

	                            if (st.userId.equals("002"))

	                                content = "我是从服务器发发送给用户002的消息：" + count;

	                            else

	                                continue;

	                        } else {

	                            if (st.userId.equals("001"))

	                                content = "我是从服务器发发送给用户001的消息：" + count;

	                            else

	                                continue;

	                        }

	                        SocketMessage message = new SocketMessage();

	                        message.setFrom(Custom.NAME_SERVER);

	                        message.setTo(Custom.NAME_CLIENT);

	                        message.setMessage(content);

	                        message.setType(Custom.MESSAGE_EVENT);

	                        message.setUserId(st.userId);



	                        String jMessage = Util.initJsonObject(message).toString() + "\n";*/
	                     /*   BufferedWriter writer = st.writer;
	                        String jMessage = "OK";
	                        writer.write(jMessage);
	                        writer.newLine();
	                        writer.flush();
*/
						//System.out.println("向客户端" + st.socket.getInetAddress() + "发送了消息：" + content);

					}

					count++;

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}



			}

		}, 0, 1000 * sendInteval);//此处设置定时器目的是模仿服务端向客户端推送消息，假定每隔30秒推送一条消息



	}



	/**

	 * 关闭与SocketThread所代表的客户端的连接

	 * @param socketThread 要关闭的客户端

	 * @throws IOException

	 */

	public void closeSocketClient(SocketThread socketThread) throws IOException {

		if (socketThread.socket != null && !socketThread.socket.isClosed()) {

			if (socketThread.reader != null)

				socketThread.reader.close();

			if (socketThread.writer != null)

				socketThread.writer.close();

			socketThread.socket.close();

		}

		mThreadList.remove(socketThread);

		socketThread = null;

	}

	private void handleTask(Socket socket){
		JDBC jdbc = new JDBC(JDBC.DB);
		JDBC uJdbc = new JDBC(JDBC.DB);
		ResultSet rs = null;
		String clientId, machineId;
		int money, timeLen;
		int taskType = 0;
		Client client= new Client(socket);
		String sql=	"SELECT b.* "+
				"FROM t_machine_ip a, t_task b "+
				"WHERE a.client_id = b.client_id  "+
				"   AND a.machine_id = b.machine_id "+
				"   AND a.ip='"+socket.getInetAddress().getHostAddress()+"' AND a.port='"+socket.getPort()+"' and b.flag=0";
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
				if(rs != null) rs.close();
				if(jdbc != null) jdbc.close();
				if(uJdbc != null) uJdbc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException {


//            boolean isRun = false;
		Server server = new Server();
		server.start();
          /*  while(!isRun){
		        try {
					
					isRun = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }*/

	}

}
