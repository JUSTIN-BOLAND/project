package com.secray.utils.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.secray.utils.JDBC.JDBC;

/**
 * 类  名: DBUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月28日 下午8:33:09
 *
 */
public class DBUtil {

	public static  void fetchCompany(String srcTableName,String destTableName,String province){

		JDBC jdbc =  new JDBC(JDBC.DB);
		JDBC uJdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;
		String sql="SELECT a.id,a.furl  FROM tb_dept_cat_area a where province='"+province+"' and flag=0 order by a.id ";
		int id=0;
		try
		{
			jdbc.connect();
			String delSql = "delete FROM tb_dept_company  where province='"+province+"' ";
			jdbc.update(delSql);

			rs = jdbc.query(sql);
			uJdbc.connect();
			while(rs.next()){
				id = rs.getInt(1);
				String url = rs.getString(2);
				//fetchDetailMain(url,uJdbc,id,province);
			}
			jdbc.update("update tb_dept_cat_area set flag=1 where id="+id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try{
				jdbc.update("update tb_dept_cat_area set flag=-1 where id="+id);


			}
			catch(SQLException sqle){ sqle.printStackTrace(); }
		}
		finally
		{

			try{
				if(rs!=null) rs.close();
				if(jdbc!=null) jdbc.close();
				if(uJdbc!=null) uJdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
