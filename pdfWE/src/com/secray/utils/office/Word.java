package com.secray.utils.office;

import java.awt.Color;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.lowagie.text.Anchor;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.field.RtfPageNumber;
import com.lowagie.text.rtf.field.RtfTotalPageNumber;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.style.RtfParagraphStyle;
import com.secray.utils.JDBC.JDBC;
import com.secray.utils.chart.Chart;
/**
 * 类  名: Office
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月16日 下午3:23:13
 *
 */
public class Word {

	// @return the commonWiths


	public static final Logger log = Logger.getLogger(Word.class);
	private Rectangle pageSize;
	private String fileName;
	private  Document document;
	private  int[] withs ;
	private String titles;
	private String tableHeader;
	private String sql;
	private  int[] commonWiths ;
	private String tableCommonHeader;
	private String commonSql;

	private String statDay;

	public String getTitles() {
		return titles;
	}

	// @param TODO

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public void setTablePorperty( String tableHeader,int[] withs,String sql){
		this.tableHeader = tableHeader;
		this.withs = withs;
		this.sql = sql;
	}
	public void setCommonTablePorperty( String tableCommonHeader,int[] commonWiths,String commonSql){
		this.tableCommonHeader = tableCommonHeader;
		this.commonWiths = commonWiths;
		this.commonSql = commonSql;
	}
	// @return the statDay

	public String getStatDay() {
		return statDay;
	}

	// @param TODO

	public void setStatDay(String statDay) {
		this.statDay = statDay;
	}

	public Word(Rectangle pageSize,String fileName)
	{
		this.pageSize = pageSize;
		this.fileName = fileName;

		document = new Document(pageSize);

	}

	public Word(String fileName)
	{
		//this.pageSize = PageSize.A4;
		this(PageSize.A4,fileName);
	}
	public void buildCommonTable() throws Exception
	{
		String[] tableHeaders =  this.tableCommonHeader.split(",");

		Table table = new Table(tableHeaders.length);

		table.setBorderWidth(1);
		table.setBorderColor(Color.BLACK);
		table.setPadding(0);
		table.setSpacing(0);
		table.setWidths(this.commonWiths);

	    /*
	     * 添加表头的元素
	     */
		Cell cell = null;
		for(int i=0; i< tableHeaders.length ; i++ ){
			cell = new Cell(tableHeaders[i]);//单元格

			cell.setHeader(true);
			cell.setBackgroundColor(new Color(128, 138, 135));
			table.addCell(cell);

		}
		table.endHeaders();// 表头结束

	 /*

	    int month = Calendar.getInstance().get(Calendar.MONTH)+1;

	    String statDay = Calendar.getInstance().get(Calendar.YEAR)+"-"+(month<10 ? "0"+month: month)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);*/
		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;
		String sql= this.commonSql;
		try
		{
			jdbc.connect();

			log.info("执行sql"+sql);
			rs = jdbc.query(sql);
			int j=1;

			while(rs.next()){
				table.addCell(j+"");
				for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){

					table.addCell(new Paragraph(rs.getString(i)));

				}


				j++;
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			try
			{
				jdbc.rollback();
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		finally
		{
			try{
				if(jdbc!=null) jdbc.close();
				if(rs!=null) rs.close();
			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

		}
		document.add(table);
	}
	public void buildTable() throws Exception
	{
		String[] tableHeaders =  this.tableHeader.split(",");

		Table table = new Table(tableHeaders.length);

		table.setBorderWidth(1);
		table.setBorderColor(Color.BLACK);
		table.setPadding(0);
		table.setSpacing(0);
		table.setWidths(this.withs);

	    /*
	     * 添加表头的元素
	     */
		Cell cell = null;
		for(int i=0; i< tableHeaders.length ; i++ ){
			cell = new Cell(tableHeaders[i]);//单元格

			cell.setHeader(true);
			cell.setBackgroundColor(new Color(128, 138, 135));
			table.addCell(cell);
			//if(i == tableHeaders.length-2) table.endHeaders();// 表头结束
		}
		table.endHeaders();// 表头结束

		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;

		try
		{
			jdbc.connect();
			jdbc.call("SP_ATTACT_STAT", new Object[]{statDay}, null);
			log.info("SP_ATTACT_STAT runed over!");
			log.info("执行sql"+sql);
			rs = jdbc.query(sql);
			int j=1;
			String dept="";
			while(rs.next()){
				if(!dept.equals( rs.getString(2))){
					cell = new Cell(j+"");
					cell.setRowspan(5);
					table.addCell(cell);
					j++;
				}
				//table.addCell(j+"");
				for( int i = 2; i < rs.getMetaData().getColumnCount(); i++){
					//
					if(i==2  ) {
						if(!dept.equals( rs.getString(2))) {
							cell = new Cell(rs.getString(i));
							cell.setRowspan(5);
							table.addCell(cell);

						}
					}
					//table.addCell(cell);
					else table.addCell(new Paragraph(rs.getString(i)));
					//log.info(rs.getString(i));
				}

				dept = rs.getString(2);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			try
			{
				jdbc.rollback();
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		finally
		{
			try{
				if(jdbc!=null) jdbc.close();
				if(rs!=null) rs.close();
			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

		}
		document.add(table);
	}
	public void buildGraph() throws Exception
	{
		String sql="select tt1.source_addr ,tt1.source_ip,count(1) c1 from t_attack_log_history tt1 "
				+"	where tt1.attack_time_str='"+statDay
				+"'	group by tt1.source_addr ,tt1.source_ip "
				+"  order by count(1) desc  limit 10";
		String imgFileName = "e:\\pie.jpg";
		Chart chart = new Chart(Chart.CHART_BAR,Chart.CHART_2D);
		chart.setSql(sql);

		chart.buildChart("统计图,攻击源,攻击次数");
		chart.writeChartAsImage(imgFileName);


		Table charTable = new Table(1);
		charTable.setOffset(0);
		charTable.setBorder(0);
		Image image = Image.getInstance(imgFileName);
		Cell cell = new Cell(image);
		charTable.addCell(cell);
		document.add(charTable);
	}

	public void buildTable1() throws Exception
	{
		String[] tableHeaders =  this.tableHeader.split(",");

		PdfPTable  table = new PdfPTable (tableHeaders.length);

		table.setWidthPercentage(100);
		table.setSplitLate(true);
		table.setWidths(this.withs);

	    /*
	     * 添加表头的元素
	     */
		PdfPCell cell = null;
		for(int i=0; i< tableHeaders.length ; i++ ){
			cell = new PdfPCell(new Paragraph(tableHeaders[i]));//单元格


			cell.setBackgroundColor(new Color(128, 138, 135));
			table.addCell(cell);

		}


		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;

		try
		{
			jdbc.connect();
			jdbc.call("SP_ATTACT_STAT", new Object[]{statDay}, null);
			log.info("SP_ATTACT_STAT runed over!");
			log.info("执行sql"+sql);
			rs = jdbc.query(sql);
			int j=1;
			String dept="";
			while(rs.next()){
				if(!dept.equals( rs.getString(2))){
					cell = new PdfPCell(new Paragraph(j+""));
					cell.setRowspan(5);
					table.addCell(cell);
					j++;
				}
				//table.addCell(j+"");
				for( int i = 2; i < rs.getMetaData().getColumnCount(); i++){
					//
					if(i==2  ) {
						if(!dept.equals( rs.getString(2))) {
							cell = new PdfPCell(new Paragraph(rs.getString(i)));
							cell.setRowspan(5);
							table.addCell(cell);

						}
					}
					//table.addCell(cell);
					else table.addCell(new Paragraph(rs.getString(i)));
					//log.info(rs.getString(i));
				}

				dept = rs.getString(2);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			try
			{
				jdbc.rollback();
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		finally
		{
			try{
				if(jdbc!=null) jdbc.close();
				if(rs!=null) rs.close();
			}
			catch(SQLException sqle){ sqle.printStackTrace(); }

		}
		document.add(table);
	}

	public void buildWord(String docTitle){
		Paragraph p;
		Font f;
		try {
			RtfWriter2.getInstance(document,  new FileOutputStream(this.fileName));

			String[] aTitles = {"","","","","",""};
			String[] aRealTitles = this.titles.split(",");
			for(int i=0; i< aRealTitles.length ; i++ ){
				aTitles[i] = aRealTitles[i];
			}
			document.addTitle("Title@"+aTitles[0]); // 标题
			document.addAuthor("Author@"+aTitles[1]);// 作者
			document.addSubject("Subject@"+aTitles[2]);// 主题
			document.addKeywords("Keywords@"+aTitles[3]);// 关键字
			document.addCreator("Creator@"+aTitles[4]);// 创建者
			document.open();

			//设置合同头

			f  = new Font();

			docTitle = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+ docTitle;
			p = new Paragraph(docTitle,  new Font(Font.NORMAL, 26, Font.BOLD, new Color(0, 0, 0)) );
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);
			document.newPage();

			buildDocMap(1,"一、攻击源排行");
			//buildDocMap(2,"攻击源清单");
			this.buildCommonTable();
			buildDocMap(1,"二、攻击源排行图");
			this.buildGraph();
			this.buildLink("http://www.163.com");
			document.newPage();
			buildDocMap(1,"三、被攻击单位排行");
			this.buildTable();

			this.buildHeader();
			this.buildFooter();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			if( document != null )  document.close();
		}
	}
	public void buildLink(String url) throws DocumentException
	{
		Font linkFont = FontFactory.getFont(
				FontFactory.HELVETICA_BOLDOBLIQUE, 12, Font.UNDERLINE,
				new Color(0, 0, 255));
		Paragraph pUrl = new Paragraph();
		Anchor link = new Anchor(url);
		link.setReference(url);
		link.setFont(linkFont);
		pUrl.add(link);
		document.add(pUrl);
	}
	public void buildDocMap(int docLevel,String title) throws DocumentException
	{
		RtfParagraphStyle rtfGsBt = RtfParagraphStyle.STYLE_HEADING_1;

		switch(docLevel){
			case 1:
				rtfGsBt = RtfParagraphStyle.STYLE_HEADING_1;
				rtfGsBt.setAlignment(Element.ALIGN_CENTER);
				rtfGsBt.setStyle(Font.BOLD);
				rtfGsBt.setSize(14);
				break;
			case 2:
				rtfGsBt = RtfParagraphStyle.STYLE_HEADING_2;
				rtfGsBt.setAlignment(Element.ALIGN_CENTER);
				rtfGsBt.setStyle(Font.BOLD);
				rtfGsBt.setSize(12);
				break;
		}
		Paragraph paragraph1 = new Paragraph(title);
		paragraph1.setAlignment(Element.ALIGN_CENTER);
		paragraph1.setFont(rtfGsBt);
		document.add(paragraph1);
	}
	public void buildHeader()
	{
		Paragraph paraheader = new Paragraph();
		Font paraheaderFont = new RtfFont("宋体_GB2312", 10, Font.BOLD, Color.BLACK);
		paraheader.setFont(new Font(paraheaderFont));
		paraheader.add(new Phrase("郑州市信达天瑞技术信息有限公司"));


		HeaderFooter header = new RtfHeaderFooter(paraheader);
		header.setAlignment(Element.ALIGN_RIGHT);
		header.setBorder(Rectangle.NO_BORDER);
		document.setHeader(header);
	}
	public void buildFooter()
	{
		Paragraph parafooter = new Paragraph();
		Font footerFont = new RtfFont("宋体_GB2312", 12, Font.BOLD, Color.BLACK);
		parafooter.setFont(new Font(footerFont));
		parafooter.add(new Phrase("第"));
		parafooter.add(new RtfPageNumber());
		parafooter.add(new Phrase("页  共"));
		parafooter.add(new RtfTotalPageNumber());
		parafooter.add(new Phrase("页"));

		HeaderFooter footer = new RtfHeaderFooter(parafooter);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.NO_BORDER);
		document.setFooter(footer);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		String statDay = "2017-02-15";//Calendar.getInstance().get(Calendar.YEAR)+"-"+(month<10 ? "0"+month: month)+"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
		String tableHeader = "序号,单位,攻击源IP\n(前5位),攻击源地域,高危攻击\n总数,Sql注入\n(高危事件),Xss跨站\n(高危事件),CC攻击\n(高危事件),文件下载\n(中危事),文件解析漏洞利用\n(中危),全部攻击\n总数";
		int[] withs = { 3, 9, 9, 6, 4 , 4, 4, 4, 4, 4, 4 };
		String sql="select * FROM gov01.t_tmp_attact_result where stat_day='"+statDay+"'";

		Word word = new Word(PageSize.B4,"E:/网防G01河南省简报(2017年02月15日).doc");
		word.setTitles("攻击统计,梁波,subject,攻击,梁波");
		word.setStatDay(statDay);
		word.setTablePorperty(tableHeader, withs, sql);

		tableHeader = "排名,攻击来源,Ip地址,攻击总数";
		withs = new int[]{ 3, 9, 9,  4 };
		sql="select tt1.source_addr ,tt1.source_ip,count(1) c1 from t_attack_log_history tt1 "
				+"	where tt1.attack_time_str='"+statDay
				+"'	group by tt1.source_addr ,tt1.source_ip "
				+"  order by count(1) desc  limit 10";
		word.setCommonTablePorperty(tableHeader, withs, sql);
		word.buildWord("网防G01河南省简报");
	}



}
