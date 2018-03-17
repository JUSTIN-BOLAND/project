package com.secray.utils.office;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.DateUtil;
import com.secray.utils.common.StrUtil;



public class Excel<T> {
	public static final Logger log = Logger.getLogger(Excel.class);

	public static String AVERAGE="AVERAGE";
	public static String SUM="SUM";

	public static int ALLSHEET = -1;

	private boolean isHorizontalCENTER = false;
	private boolean isVerticalCENTER = false;
	private boolean isWrapText  = false;
	private int height = 25;
	private short headerColor = HSSFColor.GREY_25_PERCENT.index;
	private int horToVerCol  = -1;
	private String  horToVerSep = "[\\s+|，]";


	protected HSSFWorkbook workbook = new HSSFWorkbook();
	private HSSFSheet sheet;

	private String excelFileName;
	private String insertTableName;

	public void setHorToVer( int horToVerCol,String  horToVerSep) {
		this.horToVerCol = horToVerCol;
		if(horToVerSep != null && horToVerSep.trim().length() > 0)  this.horToVerSep = horToVerSep;
	}
	public void setHeaderColor(short headerColor) {
		this.headerColor = headerColor;
	}

	// @return the isHorizontalCENTER

	public boolean isHorizontalCENTER() {
		return isHorizontalCENTER;
	}
	// @param TODO

	public void setHorizontalCENTER(boolean isHorizontalCENTER) {
		this.isHorizontalCENTER = isHorizontalCENTER;
	}
	// @return the isVerticalCENTER

	public boolean isVerticalCENTER() {
		return isVerticalCENTER;
	}
	// @param TODO

	public void setVerticalCENTER(boolean isVerticalCENTER) {
		this.isVerticalCENTER = isVerticalCENTER;
	}
	// @return the isWrapText

	public boolean isWrapText() {
		return isWrapText;
	}
	// @param TODO

	public void setWrapText(boolean isWrapText) {
		this.isWrapText = isWrapText;
	}
	// @return the height

	public int getHeight() {
		return height;
	}

	// @param TODO

	public void setHeight(int height) {
		this.height = height;
	}

	public Excel(String excelFileName){
		this.excelFileName = excelFileName;
	}
	public void buildSheet(String sheetname){
		this.sheet = this.workbook.createSheet(sheetname);
	}
	@SuppressWarnings("deprecation")
	public HSSFCellStyle buildCellStyle(boolean isHeader){
		HSSFCellStyle style = this.workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		if( this.isWrapText) style.setWrapText(true);
		if (isHorizontalCENTER) {
			style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		}

		if (isVerticalCENTER) {
			style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		}
		style.setLocked(true);
		if (isHeader) {
			style.setFillForegroundColor(this.headerColor);
			style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index);
			font.setFontHeightInPoints((short) 12);
			font.setBold(true);
			// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
		}
		return style;
	}


	public  void buildHeader(String[] headerColumns){
		HSSFCellStyle style = buildCellStyle(true);
		Row row = sheet.createRow(0);
		row.setHeightInPoints(this.height);
		for(int i=0;i<headerColumns.length;i++){
			Cell cell = row.createCell(i);
			String[] column = headerColumns[i].split("_#_");
			sheet.setColumnWidth(i, Integer.valueOf(column[1]) * 256);
			cell.setCellValue(column[0]);
			cell.setCellStyle(style);
			//row.setHeightInPoints(getRowAutoHeight(column[0]));
			log.info(column[0]+" : width: "+column[1]);
		}
	}

	public void setPwd(String pwd){
		if( pwd != null && pwd.trim().length() >0 ) sheet.protectSheet(pwd);//设置Excel保护密码
	}

	public void buildFile(){
		FileOutputStream fileOut = null;
		try{
			fileOut = new FileOutputStream(new File(this.excelFileName));
			workbook.write(fileOut);
			fileOut.flush();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				fileOut.close();

				if( workbook!= null ) workbook.close();
				sheet = null;
				workbook = null;
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}

	public int getRowAutoHeight(String value)
	{
		int defautHeight = 15;
		return value.split("\n").length * defautHeight;

	}
	/**
	 *
	 * 函数名    : buildSheet
	 * 功   能	 : 根据 dataset数据集创建sheet内容
	 * 参   数    : @param dataset
	 * 参   数    : @param headerColumns
	 * 参   数    : @param fieldColumns
	 * 参   数    : @throws NoSuchMethodException
	 * 参   数    : @throws IllegalAccessException
	 * 参   数    : @throws IllegalArgumentException
	 * 参   数    : @throws InvocationTargetException    设定文件
	 * 返回值	 : void    返回类型
	 * 编写者    : root
	 * 编写时间 : 2017年2月21日 上午10:26:02
	 */
    /*
     *
	    public static final String[] RECORES_COLUMNS = new String[]{
	            "User Name_#_3000",
	            "Address_#_7000"
	            };

	    public static final String[] RECORES_FIELDS = new String[]{
	        "name","address"
	    };
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void buildSheetContent(List<T> dataset,String[] fieldColumns)
			throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{

		HSSFCellStyle style = buildCellStyle(false);
		Row row = null;
		//SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		int rowNum = 0;
		for(T t:dataset){
			rowNum++ ;
			row = sheet.createRow(rowNum);
			row.setHeightInPoints(this.height);
			for(int i = 0; i < fieldColumns.length; i++){
				String fieldName = fieldColumns[i] ;

				String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
				try {
					Class clazz = t.getClass();
					Method getMethod;
					getMethod = clazz.getMethod(getMethodName, new Class[]{} );
					Object value = getMethod.invoke(t, new Object[]{});
					String cellValue = "";
					if (value instanceof Date){
						Date date = (Date)value;
						cellValue = DateUtil.formatDateToStr(date, DateUtil.LONGDAY);
					}else{
						cellValue = null != value ? value.toString() : "";
					}
					row.setHeightInPoints(getRowAutoHeight(cellValue));
					Cell cell = row.createCell(i);
					cell.setCellStyle(style);
					cell.setCellValue(cellValue);

					log.info(rowNum+" : "+  i + " : " + cellValue);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}



	public  void buildSheetContent(String sql,boolean isMerge)
	{

		HSSFCellStyle style = buildCellStyle(false);
		int rowNum = 0;
		JDBC jdbc =  new JDBC(JDBC.DB);
		ResultSet rs  = null;
		Cell cell = null;
		Row row = null;
		String value = null;
		String dept = "",tmp="";
		int rowTmp = 1;
		int ord = 0;
		try
		{
			jdbc.connect();

			log.info("执行sql"+sql);
			rs = jdbc.query(sql);


			while(rs.next()){
				rowNum++;
				row = sheet.createRow(rowNum);
				row.setHeightInPoints(this.height);
				for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
					value = rs.getString(i);
					row.setHeightInPoints(getRowAutoHeight(value));
					cell = row.createCell(i-1);
					cell.setCellStyle(style);
					if( "DECIMAL".equals(rs.getMetaData().getColumnTypeName(i))) {
						if(value.indexOf(".")>-1) cell.setCellValue(Double.parseDouble(value));
						else cell.setCellValue(Integer.parseInt(value));
					}
					//log.info(i+" -> "+ rs.getMetaData().getColumnTypeName(i));
					else
						cell.setCellValue(value);
					//log.info(rowNum+" : "+  i + " : " + value);
					if(i==2) dept =  value;
				}
				if( isMerge ){
					if( rowNum == 1 ) {
						tmp = dept;

					}

					if( !dept.equals(tmp) ) {
						log.info(rowNum+" : "+  dept + " : " + tmp+" -> "+ (rowTmp) +" : " +(rowNum-1 ));
						sheet.addMergedRegion(
								new CellRangeAddress( rowTmp ,   rowNum-1 ,0,0));
						sheet.addMergedRegion(
								new CellRangeAddress( rowTmp ,   rowNum-1 ,1,1));
						if(sheet.getRow(0).getCell(0).getStringCellValue().equals("序号")) {
							ord++;
							sheet.getRow(rowTmp).getCell(0).setCellValue(ord);
						}
						tmp = dept;
						rowTmp = rowNum;

					}
				}



			}
			if( isMerge ){
				//log.info(rowNum+" : "+  dept + " : " + tmp+" -> "+ (rowTmp) +" : " +(rowNum-1 ));
				sheet.addMergedRegion(
						new CellRangeAddress( rowTmp ,   rowNum ,0,0));
				sheet.addMergedRegion(
						new CellRangeAddress( rowTmp ,   rowNum ,1,1));
				if(sheet.getRow(0).getCell(0).getStringCellValue().equals("序号")) {
					ord++;
					sheet.getRow(rowTmp).getCell(0).setCellValue(ord);
				}
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
			rs  = null;
			cell = null;
			row = null;
			jdbc = null;
		}

	}

	public void buildSum(String formula,int rowAfter){

		int rowNum = sheet.getLastRowNum()+1;
		Row row = sheet.createRow(rowNum);
		row.setHeightInPoints(15);
		HSSFCellStyle style = buildCellStyle(false);
		Cell cell = null;
		sheet.setForceFormulaRecalculation(true);
		int colsNum =  sheet.getRow(0).getPhysicalNumberOfCells();



		String[] formulas = formula.split(",");
		if(":_*".equals(formula.substring(formula.length()-3,formula.length()))){
			formulas = new String[colsNum];
			for(int i=0; i< colsNum  ; i++) {
				if(i <= rowAfter-1) continue;

				String firstChar = ""+StrUtil.convertNumToChar(65+i);
				if( 65 + i > 90) {
					firstChar = "A"+StrUtil.convertNumToChar(65+i - 90 );
				}
				formulas[i] = formula.substring(0,formula.length()-3)+"("+firstChar+"1:"+firstChar+rowNum+")";
			}
		}


		for(int i=0; i<colsNum; i++){
			cell = row.createCell(i);
			cell.setCellStyle(style);
			if( i > rowAfter-1 ) cell.setCellFormula(formulas[i]);
			else if(i == 0 ){

				cell.setCellValue("汇总");
			}
			else
				cell.setCellValue("");
			log.info((rowNum+i+1)+" : "+formulas[i]);
		}

	}


	public void buildChart(String imgFileName){

		HSSFPatriarch patriarch = null;
		HSSFClientAnchor anchor = null;
		FileInputStream fis = null;
		try
		{
			// 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
			patriarch = sheet.createDrawingPatriarch();
			// 八个参数，前四个表示图片离起始单元格和结束单元格边缘的位置，
			// 后四个表示起始和结束单元格的位置，如下表示从第2列到第12列，从第1行到第15行,需要注意excel起始位置是0
			anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, (short) 1, (short) 12, (short) 18);
			anchor.setAnchorType(3);
			// 插入图片
			fis = new FileInputStream(imgFileName);
			byte[] bytes = IOUtils.toByteArray(fis);
			int pictureIdx = workbook.addPicture(bytes, workbook.PICTURE_TYPE_JPEG);

			patriarch.createPicture(anchor, pictureIdx);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fis.close();
				anchor = null;
				patriarch = null;
				fis = null;
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	protected  Cell getMergedRegionCell(int row , int column){
		int sheetMergeCount = sheet.getNumMergedRegions();

		for(int i = 0 ; i < sheetMergeCount ; i++){
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if(row >= firstRow && row <= lastRow){

				if(column >= firstColumn && column <= lastColumn){
					Row fRow = sheet.getRow(firstRow);
					return  fRow.getCell(firstColumn);

				}
			}
		}

		return null ;
	}
	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected String getCellValue(Cell cell){

		if(cell == null) return "";

		if(cell.getCellType() == Cell.CELL_TYPE_STRING){

			return cell.getStringCellValue();

		}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

			return String.valueOf(cell.getBooleanCellValue());

		}else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){

			return cell.getCellFormula() ;

		}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}

	public String getMergedRegionValue(int row , int column){
		Cell cell = this.getMergedRegionCell(row, column);
		if( cell != null) return getCellValue(cell);
		else return null;
	}

	public void getContent(int sheetIndex){
		InputStream is = null;
		Workbook hssfWorkbook =  null;
		Sheet hssfSheet = null;
		Row row = null;
		String rowStr = "";
		String isql = "insert into "+insertTableName+" values(";
		JDBC jdbc = new JDBC(JDBC.HF);
		try
		{
			is = new FileInputStream(this.excelFileName);

			if(this.excelFileName.endsWith("xls")){
				//2003
				hssfWorkbook = new HSSFWorkbook(is);
			}else if(this.excelFileName.endsWith("xlsx")){
				//2007
				hssfWorkbook = new XSSFWorkbook(is);
			}

			// hssfWorkbook = new HSSFWorkbook(is);
			int numSheetBegin = 0;
			int numSheetEnd = hssfWorkbook.getNumberOfSheets();
			if(sheetIndex !=  -1 ) {
				numSheetBegin = sheetIndex;
				numSheetEnd = numSheetBegin+1;
			}
			jdbc.connect();
			for (int numSheet = numSheetBegin; numSheet < numSheetEnd; numSheet++) {
				hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				log.info(hssfSheet.getSheetName());
				if (hssfSheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					row = hssfSheet.getRow(rowNum);
					rowStr ="";
					if (row != null) {

                        if( this.horToVerCol < 0  ) {
							for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
								log.info(row.getLastCellNum() + " : " + colNum);
								if (rowStr.trim().length() > 0)	rowStr += ",";
								rowStr += "'" + this.getCellValue(row.getCell((short) colNum)) + "'";

							}
							String sql = isql + rowStr + ")";
							log.info(sql);
							jdbc.update(sql);
						}
						else{
                            String horToVerCellValue = null;
							for(int colNum=0 ; colNum < row.getLastCellNum(); colNum++ ){
								log.info(row.getLastCellNum()+" : "+colNum);
								if (rowStr.trim().length() > 0)		rowStr += ",";
								rowStr += "'" + ( colNum ==this.horToVerCol ? "[HorToVer]" : this.getCellValue(row.getCell((short) colNum)) ) + "'";
								if( colNum ==this.horToVerCol  ) {
									horToVerCellValue =  this.getCellValue(row.getCell((short) colNum));
								}

							}
							if(horToVerCellValue != null && horToVerCellValue.trim().length() > 0){
								String[] values = horToVerCellValue.split(this.horToVerSep);
								for(String value : values){
                                    if(value == null || value.trim().length() == 0 ) continue;
									String sql = isql + rowStr.replace("[HorToVer]",value) + ")";
									log.info(sql);
									jdbc.update(sql);
								}
							}
						}

					}
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try{
				if( jdbc != null ) jdbc.close();
				if( hssfWorkbook != null ) hssfWorkbook.close();
				if( is != null) is.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}


	}
	public List getContentAsList(int sheetIndex){
		ArrayList<String> result = new ArrayList<String>();
		InputStream is = null;
		Workbook hssfWorkbook =  null;
		Sheet hssfSheet = null;
		Row row = null;
		String rowStr = "";

		try
		{
			is = new FileInputStream(this.excelFileName);

			if(this.excelFileName.endsWith("xls")){
				//2003
				hssfWorkbook = new HSSFWorkbook(is);
			}else if(this.excelFileName.endsWith("xlsx")){
				//2007
				hssfWorkbook = new XSSFWorkbook(is);
			}

			// hssfWorkbook = new HSSFWorkbook(is);
			int numSheetBegin = 0;
			int numSheetEnd = hssfWorkbook.getNumberOfSheets();
			if(sheetIndex !=  -1 ) {
				numSheetBegin = sheetIndex;
				numSheetEnd = numSheetBegin+1;
			}

			for (int numSheet = numSheetBegin; numSheet < numSheetEnd; numSheet++) {
				hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				log.info(hssfSheet.getSheetName());
				if (hssfSheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					row = hssfSheet.getRow(rowNum);
					rowStr ="";
					if (row != null) {
						//int colNum = ;
						//row.getLastCellNum()

						for(int colNum=0 ; colNum < row.getLastCellNum(); colNum++ ){
							log.info(row.getLastCellNum()+" : "+colNum);
							if(rowStr.trim().length() > 0 ) rowStr += ",";
							rowStr +=this.getCellValue( row.getCell((short) colNum) );
						}
						//String sql = isql+rowStr+")";
						//log.info(sql);
						result.add(rowStr);

					}
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try{

				if( hssfWorkbook != null ) hssfWorkbook.close();
				if( is != null) is.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
       return result;

	}
	// @param TODO

	public void setInsertTableName(String insertTableName) {
		this.insertTableName = insertTableName;
	}


}