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
* ��  ��: OfficeUtil 
* ��  ��: TODO(������һ�仰��������������) 
* ������: root
* ��   ˾: ֣���Ŵ�������Ϣ���޹�˾
* ����ʱ��: 2017��2��16�� ����11:38:40 
*  
*/
public class OfficeUtil {
    
	public static void wordPage(Table table) throws Exception
	{
		FileOutputStream out = new FileOutputStream("e:\\demo.doc");
        //����ֽ�Ŵ�С
        Document document = new Document(PageSize.A4); 
        //����һ����д������document�������
        RtfWriter2.getInstance(document, out); 

        document.open();
        //�½�һ
        Chapter chapter1 = new Chapter(1);
        chapter1.add(new Paragraph("Hello World1"));
        //�½ڶ�
        Chapter chapter2 = new Chapter(2);
        chapter2.add(new Paragraph("Hello World2"));
        //�½���
        Chapter chapter3 = new Chapter(3);
        chapter3.add(new Paragraph("Hello World3"));
        //���½ڼ���document��
        document.add(chapter1);
        //���½ڶ�������ʾ
        document.setPageSize(PageSize.A4.rotate());
        document.add(chapter2);
        //���½�����ԭ�ɺ���
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
		        // DOCTYPE ����д���������� �������ַ���������ִ���  
		        html.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  
		        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");  
		        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")  
		                .append("<head>")  
		                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")  
		                .append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")  
		                .append("</head>")  
		                .append("<body><strong><span style=\"font-size: 20pt; \">��ӭʹ��</span></strong>");  
		        html.append("<div>֧�����ģ�</div>");  
		        html.append("</body></html>");  
		        System.out.println(html.toString());  
		        renderer.setDocument(html.toString());  
		        //renderer.setDocument(uri);
		        // ���ͼƬ�����·������  
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
			System.out.println("ascii��ֵ : "+s+" : "+(char)s);
			System.out.println("+3�����ĸ"+(char)(s+3));
		}	*/
		
		ArrayList<String> result = new ArrayList<String>(); 
		result.add("sdf");
		result.add("12");
		System.out.println(result.size());
		String[] sts  = result.toArray(new String[result.size()]);
		for(String a : sts){
			System.out.println(a);
		}
	
		
		String address="�������������г�209��";
		String province="����";
		String company="����ʡͨ�����ؼ����������´�";
		
		/*address="֣�ݻ�԰·68�ſƼ��г������703��";
		 company="���϶��Ϸ���";*/
		
		
		 address="���� ����� ����·";
		 company="�����ά����ˮ���޹�˾"; 
		SpiderCDA.getAreas(address, company, province);
//		/System.out.println("ʡ��".indexOf("ʡ"));
		String[] abs = "�ӱ�ʡ�е���˫��������·С��4--1���� - ��Ӫ��𣺹��һ���".split("\\s+-\\s+");
		for(String ab: abs){
			System.out.println(ab);
		}
	}

}
