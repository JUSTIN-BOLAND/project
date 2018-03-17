package com.secray.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.secray.G01.catDeptArea.SpiderCDA;
import com.secray.utils.common.SysUtil; 
/** 
* 类  名: OfficeUtil 
* 描  述: TODO(这里用一句话描述这个类的作用) 
* 创建者: root
* 公   司: 郑州信达天瑞信息有限公司
* 创建时间: 2017年2月16日 上午11:38:40 
*  
*/
public class OfficeUtil {
    
	public static void wordPage(Table table) throws Exception
	{
		FileOutputStream out = new FileOutputStream("e:\\demo.doc");
        //设置纸张大小
        Document document = new Document(PageSize.A4); 
        //建立一个书写器，与document对象关联
        RtfWriter2.getInstance(document, out); 

        document.open();
        //章节一
        Chapter chapter1 = new Chapter(1);
        chapter1.add(new Paragraph("Hello World1"));
        //章节二
        Chapter chapter2 = new Chapter(2);
        chapter2.add(new Paragraph("Hello World2"));
        //章节三
        Chapter chapter3 = new Chapter(3);
        chapter3.add(new Paragraph("Hello World3"));
        //将章节加入document中
        document.add(chapter1);
        //将章节二纵向显示
        document.setPageSize(PageSize.A4.rotate());
        document.add(chapter2);
        //将章节三还原成横向
        document.setPageSize(PageSize.A4);
        document.add(chapter3);
        document.close();  
	}
	public static void convertHtmlToPdf() throws Exception
	{
		        String outputFile = "e:/test.pdf";  
		        OutputStream os = new FileOutputStream(outputFile);  
		        ITextRenderer renderer = new ITextRenderer();  
		        ITextFontResolver fontResolver = renderer.getFontResolver();  
		        fontResolver.addFont("C:/Windows/fonts/simsun.ttc",  
		                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
		        StringBuffer html = new StringBuffer();  
		        // DOCTYPE 必需写否则类似于 这样的字符解析会出现错误  
		        html.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  
		        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");  
		        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")  
		                .append("<head>")  
		                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")  
		                .append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")  
		                .append("</head>")  
		                .append("<body><strong><span style=\"font-size: 20pt; \">欢迎使用</span></strong>");  
		        html.append("<div>支持中文！</div>");  
		        html.append("</body></html>");  
		        System.out.println(html.toString());  
		        renderer.setDocument(html.toString());  
		        //renderer.setDocument(uri);
		        // 解决图片的相对路径问题  
		        // renderer.getSharedContext().setBaseURL("file:/F:/teste/html/");  
		        renderer.layout();  
		        renderer.createPDF(os);  
		        os.close();  

	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*try
		{
			  OfficeUtil.convertHtmlToPdf();
					
		     
	   } catch (Exception e) {    
	    e.printStackTrace();    
	   }  */  
		
		
	/*	String str="azAZ";
		byte [] cha=str.getBytes();
		for(byte  s:cha){
		//char[] cha=str.toCharArray();
		//for(char s:cha){
			System.out.println("ascii的值 : "+s+" : "+(char)s);
			System.out.println("+3后的字母"+(char)(s+3));
		}	*/
		
		ArrayList<String> result = new ArrayList<String>(); 
		result.add("sdf");
		result.add("12");
		System.out.println(result.size());
		String[] sts  = result.toArray(new String[result.size()]);
		for(String a : sts){
			System.out.println(a);
		}
	
		
		String address="西工区五金机电市场209号";
		String province="河南";
		String company="河南省通用起重集团洛阳办事处";
		
		/*address="郑州花园路68号科技市场数码港703室";
		 company="河南东南防雷";*/
		
		
		 address="河南 濮阳县 红旗路";
		 company="濮阳县维康供水有限公司"; 
		SpiderCDA.getAreas(address, company, province);
//		/System.out.println("省市".indexOf("省"));
		String[] abs = "河北省承德市双桥区中兴路小区4--1组团 - 经营类别：国家机关".split("\\s+-\\s+");
		for(String ab: abs){
			System.out.println(ab);
		}
	}

}
