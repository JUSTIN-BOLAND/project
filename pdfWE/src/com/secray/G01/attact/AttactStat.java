package com.secray.G01.attact;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;

import com.secray.utils.common.DateUtil;
import com.secray.utils.office.Excel;

/**
 * 类  名: AttactStat
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月21日 上午10:58:06
 *
 */
public class AttactStat {
	public static final Logger log = Logger.getLogger(AttactStat.class);
	public static void attactStat(String statDay ,String fileName) {

		Excel<TableRow> excel = new Excel<TableRow>(fileName);
		excel.setWrapText(true);
		excel.setHeight(20);
		excel.buildSheet("简述");
		excel.setHeaderColor(HSSFColor.LIGHT_ORANGE.index);
		String[] headerColumns = {"用途_#_30","sql_#_90"};
		String[] fieldColumns = {"col0","col1"};
		excel.buildHeader(headerColumns);
		List<TableRow> rows = new ArrayList<TableRow>();
		TableRow row = new TableRow();
		row.setCol0("攻击源排行");
		row.setCol1("select tt1.source_addr ,tt1.source_ip,count(1) c1 from t_attack_log_history tt1  \n"+
				"where tt1.attack_time_str=\"2017-02-15\"  \n"+
				"group by tt1.source_addr ,tt1.source_ip \n"+
				"order by count(1) desc  limit 10");
		rows.add(row);
		row = new TableRow();
		row.setCol0("被攻击单位top10");
		row.setCol1("select tt2.dept1 from ( \n"+
				"select dept_name dept1 ,count(1) c1 from t_attack_log_history tt1 \n"+
				"where tt1.attack_time_str=\"2017-02-15\" group by dept_name \n"+
				" order by count(1) desc  limit 10");
		rows.add(row);
		row = new TableRow();
		row.setCol0("被攻击单位排行");
		row.setCol1("select \n"+
				"dept_name,\n"+
				"a.source_ip,\n"+
				"count(case when attack_type =\"SQL注入\" or attack_type=\"XSS攻击\" or attack_type=\"CC攻击\" then 1 else null end) as \"高危\",\n"+
				"count(case when attack_type=\"SQL注入\" then 1 else null end) as \"sql注入\",  \n"+
				"count(case when attack_type=\"XSS攻击\" then 1 else null end) as \"xss\",\n"+
				" count(case when attack_type=\"CC攻击\" then 1 else null end) as \"CC攻击\",\n"+
				" count(case when attack_type=\"文件下载\" then 1 else null end) as \"文件下载\",\n"+
				"count(case when attack_type=\"文件解析\" then 1 else null end) as \"文件解析\",\n"+
				"count(1) as \"全部\"\n"+
				" FROM t_attack_log_history a WHERE attack_time_str = \"2017-02-15\"\n"+
				"AND dept_name =\"河南省工商局\"\n"+
				"group by a.source_ip order by count(1) desc limit 10");
		rows.add(row);
		try
		{
			excel.setHeight(60);
			excel.buildSheetContent(rows,  fieldColumns);

			String sql="SELECT  @rowno:=@rowno+1 AS rowno, t.source_addr ,t.source_ip,t.c1   "+
					"FROM "+
					"( "+
					"select tt1.source_addr ,tt1.source_ip,count(1) c1 "+
					" from t_attack_log_history tt1, (select @rowno:=0) t"
					+"	where tt1.attack_time_str='"+statDay
					+"'	group by tt1.source_addr ,tt1.source_ip "
					+"  order by count(1) desc  limit 10"+
					") t,(SELECT @rowno:=0) a";
			excel.buildSheet("攻击源排行报表");

			headerColumns = new String[]{"排名_#_7","攻击来源_#_30","Ip地址_#_30","攻击总数_#_12"};
			excel.setHeaderColor(HSSFColor.GREY_25_PERCENT.index);
			excel.setHeight(20);
			excel.buildHeader(headerColumns);

			excel.buildSheetContent(sql,false);

			//被攻击单位排行报表
			sql="SELECT 	@rowno:=@rowno+1 AS rowno,  "+
					"department, "+
					"source_ip, "+
					"city, "+
					"topLevelAttackCnt, "+
					"sqlRejectCnt, "+
					"XSsCnt, "+
					"ccCnt, "+
					"fileDownloadCnt, "+
					"fileAnalyzeCnt, "+
					"totalCnt "+
					"FROM "+
					"gov01.t_tmp_attact_result , (SELECT @rowno:=0) t "+
					"WHERE stat_day='"+statDay+"'";
			excel.buildSheet("被攻击单位排行报表");



			headerColumns = new String[]{"序号_#_7","单位_#_15","攻击源IP\n(前5位)_#_20","攻击源地域_#_25"
					,"高危攻击总数_#_10"
					,"Sql注入\n(高危事件)_#_10"
					,"Xss跨站\n(高危事件)_#_10"
					,"CC攻击\n(高危事件)_#_10"
					,"文件下载\n(中危事)_#_10"
					,"文件解析漏洞\n利用(中危)_#_10"
					,"全部攻击总数_#_10"};
			excel.setHeaderColor(HSSFColor.GREY_25_PERCENT.index);
			excel.setHeight(50);
			excel.buildHeader(headerColumns);

			excel.setHeight(20);
			excel.buildSheetContent(sql,true);
			excel.buildSum(Excel.SUM+":_*",4);

			excel.buildSheet("图表");
			excel.buildChart("e:/pie.jpg");
			excel.buildFile();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String statDay = DateUtil.formatDateStr(DateUtil.LONGDAY,-1);
		String statDayChina = DateUtil.formatCurChinaDate(DateUtil.LONGDAY);

		if(args.length == 1  ) {
			statDay = args[0];
			statDayChina =  DateUtil.formatChinaDate(statDay);
		}
		String fileName = "E:/网防G01河南省简报("+statDayChina+").xls";
		log.info(statDay+" : "+fileName);
		AttactStat.attactStat(statDay,fileName);
	}

}
