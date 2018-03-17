package com.secray.utils.JDBC;

import java.io.*;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.secray.utils.common.StrUtil;
import com.secray.utils.office.Excel;

/**
 * 类  名: ExpImpUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年4月6日 下午3:06:35
 *
 */
public class ExpImpUtil {
	public static final Logger log = Logger.getLogger(ExpImpUtil.class);

	public static void importFromExcel(String fileName,String tableName,int horToVerCol){
		Excel excel = new Excel(fileName);
		excel.setInsertTableName(tableName);
		excel.setHorToVer(horToVerCol,null);
		//excel.getContent(Excel.ALLSHEET);
		excel.getContent(0);
	}
	/**
	 *
	 * 函数名    : importFromTxts
	 * 功   能	 : TODO(这里用一句话描述这个类的作用)
	 * 参   数    : @param fileName
	 * 参   数    : @param tableName
	 * 参   数    : @param sperator: 为空: 插入整行 不为空: 分隔符,索引位置[@,1]
	 * 参   数    : @param insertType : i:先删除表中数据  a: 追加
	 * 返回值	 : void    返回类型
	 * 编写者    : root
	 * 编写时间 : 2017年4月6日 下午4:46:40
	 */
	public static void importFromTxts(String fileName,String tableName,String sperator,String insertType){

		File file = new File(fileName);
		if(file.isFile() ) file = file.getParentFile();
		File[] files = file.listFiles();
		JDBC jdbc = new JDBC(JDBC.DB);
		try
		{
			jdbc.connect();
			if("i".equals(insertType.trim().toLowerCase())){
				jdbc.update("truncate table "+tableName);
			}
			for( int i =0 ;i < files.length ; i++){
				file = files[i];
				log.info("importFromTxt : "+file.getName()+" : "+file.getAbsoluteFile());
				if(file.isFile() && file.getName().toUpperCase().endsWith(".TXT")){
					String cat = file.getName().substring(0,file.getName().indexOf(".")-1);
					BufferedReader br = null;
					String line = null;
					String[] values = null;
					String value = null;
					String sep = ( sperator == null ? null : sperator.split("!")[0] );
					int idx = ( sperator == null ? null : Integer.parseInt(sperator.split("!")[1]) );

					String isql = "insert into "+tableName+" values(";
					try
					{
						br = new BufferedReader(new FileReader(file.getAbsoluteFile()));

						while(( line = br.readLine())!=null){
							if(sperator == null ) value = line;
							else{
								values = StrUtil.split(line, sep);
								value = values[idx];
							}
							String sql = isql+"'"+cat+"','"+value+"')";
							log.info(sql);
							jdbc.update(sql);
						}

					}
					catch(Exception ioe){
						ioe.printStackTrace();
					}
					finally
					{
						try{
							if( br != null ) br.close();

						}
						catch(Exception e){
							e.printStackTrace();
						}
					}

				}
			}
		}
		catch(Exception ioe){
			ioe.printStackTrace();
		}
		finally
		{
			try{

				if( jdbc != null ) jdbc.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}


	}

	public static void importFromTxt(String fileName,String tableName,String sperator,String insertType){

		File file = new File(fileName);

		if(file.isFile() && file.getName().toUpperCase().endsWith(".TXT")){
			String cat = file.getName().substring(0,file.getName().indexOf(".")-1);
			BufferedReader br = null;
			String line = null;
			String[] values = null;
			String value = null;
			String sep = ( sperator == null ? null : sperator.split("!")[0] );
			int idx = ( sperator == null ? null : Integer.parseInt(sperator.split("!")[1]) );

			String isql = "insert into "+tableName+" values(";
			JDBC jdbc = new JDBC(JDBC.DB);
			try
			{
				br = new BufferedReader(new FileReader(fileName));
				jdbc.connect();
				while(( line = br.readLine())!=null){
					if(sperator == null ) value = line;
					else{
						values = StrUtil.split(line, sep);
						value = values[idx];
					}
					String sql = isql+"'"+cat+"','"+value+"')";
					log.info(sql);
					jdbc.update(sql);
				}

			}
			catch(Exception ioe){
				ioe.printStackTrace();
			}
			finally
			{
				try{
					if( br != null ) br.close();
					if( jdbc != null ) jdbc.close();

				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

		}

	}

	public static void importFromSql(String fileName,String srcTableName,String destTableName){
		FileWriter fw=null;
		BufferedWriter bw=null;

		File file = new File(fileName);
		String errorFile = "E:\\个人目录\\梁波\\新工作\\project\\GLBank\\sql\\error.log";
		File errFile = new File(errorFile);



		if(file.isFile() && file.getName().toUpperCase().endsWith(".SQL")){
			String cat = file.getName().substring(0,file.getName().indexOf(".")-1);
			BufferedReader br = null;
			String line = null;
			String[] values = null;
			String value = null;
		    JDBC jdbc = new JDBC(JDBC.DB);
			String sql="";
			try
			{
				if(errFile.exists()) errFile.delete();
				errFile.createNewFile();

				fw=new FileWriter(errFile.getAbsoluteFile(),true);  //true表示可以追加新内容
				//fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
				bw=new BufferedWriter(fw);


				br = new BufferedReader(new FileReader(fileName));
				jdbc.connect();
				while(( line = br.readLine())!=null){
					sql = line.replace("�","").replace(";","").replace("\uFEFF","");
					if(srcTableName!= null && srcTableName.trim().length() > 0
					  && destTableName!= null && destTableName.trim().length() > 0) {
						sql = sql.replace(srcTableName,destTableName);
					}
					log.info(sql);
					jdbc.update(sql);
				}
				bw.close();
			}
			catch(Exception ioe){
				try {
					bw.write(sql);
					ioe.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//ioe.printStackTrace();
			}
			finally
			{
				try{
					if( br != null ) br.close();
					if( jdbc != null ) jdbc.close();

				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String eiType = args[0].trim().toUpperCase();
		if("FROMSQL".equals(eiType)){
			ExpImpUtil.importFromSql("E:\\个人目录\\梁波\\新工作\\project\\GLBank\\sql\\init_p_bank.sql","p_bank","p_bank_lb");
		}
		else if("FROMEXCEL".equals(eiType)){
			if(args.length == 4 ){
				ExpImpUtil.importFromExcel(args[1].trim(), args[2].trim(),Integer.parseInt(args[3].trim()) );
			}
		}
		else if(eiType.startsWith("FROMTXT")  ){
			if(args.length == 5 ){
				String sep = (args.length == 4 ? null: args[3].trim());
				String iType = (args.length == 4 ? args[3].trim(): args[4].trim());
				if(iType.indexOf("!") == -1){
					if("FROMTXT".equals(eiType))
						ExpImpUtil.importFromTxt(args[1].trim(), args[2].trim(), sep, iType);
					else
						ExpImpUtil.importFromTxts(args[1].trim(), args[2].trim(), sep, iType);
				}
				else
				{
					log.info("ExpImpUtil[error] : 请使用以下格式: \n e:\\lb tb_lb @=1 i \ne:\\lb tb_lb i");
				}

			}
		}
		log.info("ExpImpUtil : 导入完成!");
	}

}
