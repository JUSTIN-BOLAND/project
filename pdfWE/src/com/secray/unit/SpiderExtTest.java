package com.secray.unit;

import java.io.IOException;

import org.apache.http.StatusLine;

import com.secray.utils.http.SpiderExt;

/** 
* ��  ��: SpiderExtTest 
* ��  ��: TODO(������һ�仰��������������) 
* ������: root
* ��   ˾: ֣���Ŵ�������Ϣ���޹�˾
* ����ʱ��: 2017��3��20�� ����5:38:19 
*  
*/
public class SpiderExtTest {

	/** 
	* ������    : main 
	* ��   ��	 : TODO(������һ�仰��������������) 
	* ��   ��    : @param args    �趨�ļ� 
	* ����ֵ	 : void    �������� 
	* ��д��    : root
	* ��дʱ�� : 2017��3��20�� ����5:38:19
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://beian.cndns.com/token-cndns-icp-3b382b319b2f69559ca7a82fd857db5c.php";
		url="http://beian.cndns.com";
		SpiderExt spider = new SpiderExt(SpiderExt.GET,url);
		try
		{
			//spider.setCookie("PHPSESSID=bqvtej224osns754hql3adoft6");
			//int retCode = spider.submit(SpiderExt.XMLHttpRequest);
			int retCode = spider.submit(SpiderExt.SUBMIT);
			spider.getCookieValue("a");
			if( retCode == 200 ){
				
			}
			
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally
		{
			spider.close();
		}
	}

}
