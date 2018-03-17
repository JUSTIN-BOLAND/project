package com.secray.utils.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.secray.utils.JDBC.JDBC;

/**
 * 类  名: ChartUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月17日 上午10:31:07
 *
 */
public class Chart {

	public static  int  CHART_2D=1;
	public static  int  CHART_3D=2;

	public static int  CHART_PIE=1;
	public static int  CHART_BAR=2;

	private String sql;

	private int charType;
	private int charShape;
	private  JFreeChart chart;
	private boolean isCategory = false;

	// @return the sql

	public String getSql() {
		return sql;
	}
	// @param TODO

	public void setSql(String sql) {
		this.sql = sql;
	}






	// @return the isCategory

	public boolean isCategory() {
		return isCategory;
	}

	// @param TODO

	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}

	public Chart(int charType,int charShape)
	{
		this.charType = charType;
		this.charShape = charShape;
	}
	public CategoryDataset buildBarDataSet(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;

		try
		{
			jdbc.connect();
			rs = jdbc.query(sql);

			while(rs.next()){
				String column = "";//rs.getString(1);
				if( isCategory ) column=rs.getString(2);
				dataset.addValue( NumberFormat.getInstance().parse(rs.getString(3)) ,column, rs.getString(1));

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

		return dataset;
	}
	public DefaultPieDataset buildPieDataSet(){
		DefaultPieDataset dataset = new DefaultPieDataset();

		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;

		try
		{
			jdbc.connect();
			rs = jdbc.query(sql);
			while(rs.next()){

				dataset.setValue( rs.getString(1),NumberFormat.getInstance().parse(rs.getString(2)));

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


		return dataset;
	}
	public void setBarColor(CategoryPlot plot){
		// 使用自定义的渲染器
		CustomRenderer renderer = new CustomRenderer();
		// 设置柱子宽度
		renderer.setMaximumBarWidth(0.2);
		// 设置柱子高度
		renderer.setMinimumBarLength(0.2);
		// 设置柱子边框颜色
		renderer.setBaseOutlinePaint(Color.BLACK);
		// 设置柱子边框可见
		renderer.setDrawBarOutline(true);
		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(0.5);

		// 显示每个柱的数值，并修改该数值的字体属性
		renderer.setIncludeBaseInRange(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		plot.setRenderer(renderer);
		// 设置柱的透明度
		plot.setForegroundAlpha(1.0f);

		// 背景色 透明度
		plot.setBackgroundAlpha(0.5f);
	}
	private  void processChart(JFreeChart chart) {
		Font titleFont = new Font("宋体", Font.PLAIN, 20);
		Font labelFont = new Font("宋体", Font.PLAIN, 12);
		Font tickLabelFont = new Font("sans-serif", Font.PLAIN, 11);

		switch(this.charType){
			case 1:
				//得到标题
				TextTitle texttitle = chart.getTitle();
				//标题
				texttitle.setFont(titleFont);
				texttitle.setToolTipText("A title tooltip!");
				//得到绘图区
				PiePlot pieplot = (PiePlot) chart.getPlot();
				//设置标签字体
				pieplot.setLabelFont(labelFont);
				pieplot.setNoDataMessage("No data available");
				pieplot.setCircular(false);
				pieplot.setLabelGap(0.02);
				//提示条字体
				chart.getLegend().setItemFont(tickLabelFont);
				break;
			case 2:
				CategoryPlot plot = chart.getCategoryPlot();
				CategoryAxis domainAxis = plot.getDomainAxis();
				ValueAxis rAxis = plot.getRangeAxis();
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				TextTitle textTitle = chart.getTitle();
				textTitle.setFont(titleFont);
				domainAxis.setTickLabelFont(tickLabelFont);
				domainAxis.setLabelFont(labelFont);
				rAxis.setTickLabelFont(tickLabelFont);
				rAxis.setLabelFont(labelFont);
				chart.getLegend().setItemFont(labelFont);

				NumberAxis vn = (NumberAxis) plot.getRangeAxis();
				// vn.setAutoRangeIncludesZero(true);
				DecimalFormat df = new DecimalFormat("#0.0");
				vn.setNumberFormatOverride(df);
				if(this.charShape == this.CHART_2D) this.setBarColor(plot);
				break;
		}
	}
	public  void writeChartAsImage(String imgFileName) {
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream(imgFileName);
			ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 600, 500, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
			}
		}
	}
	public  void buildChart(String title){


		// 构造chart
		//JFreeChart chart = null;
		String[] titles = {"标题","横轴 ","纵轴"};
		if(title != null || title.trim().length() > 0)  titles = title.split(",");
		switch(this.charType){
			case 1:
				DefaultPieDataset dataset = this.buildPieDataSet();
				if(this.charShape == this.CHART_3D)

					chart = ChartFactory.createPieChart3D(
							titles[0], // 图表标题
							dataset, // 数据集
							true, // 是否显示图例(对于简单的柱状图必须
							true, // 是否生成工具
							false // 是否生成URL链接
					);
				else
					chart = ChartFactory.createPieChart(
							titles[0], // 图表标题

							dataset, // 数据集
							true, // 是否显示图例(对于简单的柱状图必须
							true, // 是否生成工具
							false // 是否生成URL链接
					);
				break;
			case 2:
				// 得到数据
				CategoryDataset catDataset = buildBarDataSet();
				if(this.charShape == this.CHART_3D)
					chart = ChartFactory.createBarChart3D(
							titles[0], // 图表标题
							titles[1], // 目录轴的显示标签--横轴
							titles[2], // 数值轴的显示标签--纵轴
							catDataset, // 数据集
							PlotOrientation.VERTICAL, // 图表方向：水平、
							true, // 是否显示图例(对于简单的柱状图必须
							false, // 是否生成工具
							false // 是否生成URL链接
					);
				else
					chart = ChartFactory.createBarChart(
							titles[0], // 图表标题
							titles[1], // 目录轴的显示标签--横轴
							titles[2], // 数值轴的显示标签--纵轴
							catDataset, // 数据集
							PlotOrientation.VERTICAL, // 图表方向：水平、
							true, // 是否显示图例(对于简单的柱状图必须
							false, // 是否生成工具
							false // 是否生成URL链接
					);
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				break;
		}

		this.processChart(chart);
		//writeChartAsImage();


	}
	public void showChart(String title){
		ChartFrame chartFrame=new ChartFrame( title,chart);
		//chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		chartFrame.pack(); //以合适的大小展现图形
		chartFrame.setVisible(true);//图形是否可见
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String statDay="2017-02-16";
		String sql="select tt1.source_addr ,tt1.source_ip,count(1) c1 from t_attack_log_history tt1 "
				+"	where tt1.attack_time_str='"+statDay
				+"'	group by tt1.source_addr ,tt1.source_ip "
				+"  order by count(1) desc  limit 10";
		Chart chart = new Chart(Chart.CHART_BAR,Chart.CHART_2D);
		chart.setSql(sql);
		//chart.setCategory(true);
		//chart.setImgFileName("e:\\pie.jpg");
		chart.buildChart("统计图,攻击源,攻击次数");
		chart.writeChartAsImage("e:\\pie.jpg");
		chart.showChart("统计图");
	}

}
