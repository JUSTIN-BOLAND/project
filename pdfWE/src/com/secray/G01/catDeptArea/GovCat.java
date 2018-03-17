package com.secray.G01.catDeptArea;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.secray.utils.JDBC.JDBC;

/**
 * 类  名: GovCat
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月27日 上午11:55:04
 *
 */
public class GovCat {

	public static void genGovCat(){
		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;
		String sql="insert into tb_dic_gov_dept(department,lvl,descs) values ";
		String catStr = "外交部，国防部，国家发展和改革委员会，教育部，科学技术部，工业和信息化部，国家民族事务委员会，公安部，国家安全部，监察部，民政部，司法部，财政部，人力资源和社会保障部，国土资源部，环境保护部，住房和城乡建设部，交通运输部，水利部，农业部，商务部，文化部，卫生和计划生育委员会，中国人民银行，审计署"+
				"国家统计局，国家质量监督检验检疫总局，国家林业局，国家医药监督管理局，国家海关总署，国家税务总局，国家环境保护总局，中国民航总局，国家广播电影电视总局，国家体育总局，国家工商行政管理局，国家新闻出版署，国家知识产权局，国家旅游局，国家宗教事务局，国务院参事室，国务院机关事务管理局，国家安全生产监督管理局"+
				"，人民防空办公室";
		try
		{
			jdbc.connect();
			jdbc.truncate("tb_dic_gov_dept");


			String[] cats = catStr.split("，");
			for(String cat : cats){
				String shortCat = cat.replace("部", "").replace("国家", "").replace("总局", "").replace("局", "");
				shortCat = shortCat.replace("总署", "").replace("行政管理", "");
				String dSql = sql+"('"+cat+"',1,'"+shortCat+"')";

				System.out.println(dSql);
				jdbc.update(dSql);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try{
				if(rs!=null) rs.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }
		}
		finally
		{

			try{
				if(jdbc!=null) jdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}
	}
	/*  1.总工会2.妇联3.监察局4.科技局5.国土资源局6.旅游民族宗教局7.发展计划局8.林业局9.矿管局10.商务局
      11.药品监督局12.轻工协会13.交通局14.运管站15.供电公司16.安全监督局17.水利局18.水产局19.畜牧局
      20.地税局21.国税局22.烟草局23.司法局24.民政局25.计划生育局26.卫生局27.人事局28.审计局29.住房公积金管理中心
      30.档案局31.物价局32.统计局33.劳动和社会保障局34.质量技术监督局35.建材协会36.农业局37.农机监督管理中心38.肉食办39.粮食局
      40.工商局41.公安局42.教育局43.文化局44.城建局45.财政局46.残联47.信访局48.人民代表大会常务委员会49.人民防空办公室50.农机局
      51.保密局52.广播电影电视局53.街道办事处54.无线电管理局*/
	public static void genLocalGovCat(){
		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;
		String sql="insert into tb_dic_gov_dept(department,lvl,descs) values ";
		String catStr = " 1.总工会2.妇联3.监察局4.科技局5.国土资源局6.旅游局61.民族宗教局7.发展计划局8.林业局9.矿管局10.商务局"+
				"11.药品监督局12.轻工协会13.交通局14.运管站15.供电公司16.安全监督局17.水利局18.水产局19.畜牧局"+
				"20.地税局21.国税局22.烟草局23.司法局24.民政局25.计划生育局26.卫生局27.人事局28.审计局29.住房公积金管理中心"+
				"30.档案局31.物价局32.统计局33.劳动和社会保障局34.质量技术监督局35.建材协会36.农业局37.农机监督管理中心38.肉食办39.粮食局"+
				"40.工商局41.公安局42.教育局43.文化局44.城建局45.财政局46.残联47.信访局48.人民代表大会常务委员会49.人民防空办公室50.农机局"+
				"51.环保局52.老干部局53.建设局54.土地管理局55.轻工业局56.机要局57.组织部58.宣传部59.纪检委60.物资局61.工业局62.海关"+
				"63.出入境检验检疫局64.消防队65.规划局66.气象局67.商务局68.统战部69.农牧局70.企业管理局71.招商局72.海事局73.体育局"+
				"74.水务局75.园艺局76.国有资产管理局77.房屋管理局78.供销合作社79.社会事业局80.房地产管理局81.农林局";
		try
		{
			jdbc.connect();
			//jdbc.update("delete from tb_dic_gov_dept where id>45");
			jdbc.truncate("tb_dic_gov_dept");

			String[] cats = catStr.split("\\d+.");
			for(String cat : cats){
				if(cat == null || cat.trim().length() ==0  ) continue;

				String shortCat = cat.replace("部", "").replace("国家", "").replace("总局", "").replace("局", "");
				shortCat = shortCat.replace("总署", "").replace("行政管理", "");
				String dSql = sql+"('"+cat+"',1,'"+shortCat+"')";

				System.out.println(dSql);
				jdbc.update(dSql);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try{
				if(rs!=null) rs.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }
		}
		finally
		{

			try{
				if(jdbc!=null) jdbc.close();

			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

			jdbc = null;

		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//GovCat.genGovCat();
		GovCat.genLocalGovCat();
	}

}
