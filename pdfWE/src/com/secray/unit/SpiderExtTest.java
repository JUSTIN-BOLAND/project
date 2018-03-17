package com.secray.unit;

import java.io.IOException;

import org.apache.http.StatusLine;

import com.secray.utils.http.SpiderExt;

/** 
* 类  名: SpiderExtTest 
* 描  述: TODO(这里用一句话描述这个类的作用) 
* 创建者: root
* 公   司: 郑州信达天瑞信息有限公司
* 创建时间: 2017年3月20日 下午5:38:19 
*  
*/
public class SpiderExtTest {

	/** 
	* 函数名    : main 
	* 功   能	 : TODO(这里用一句话描述这个类的作用) 
	* 参   数    : @param args    设定文件 
	* 返回值	 : void    返回类型 
	* 编写者    : root
	* 编写时间 : 2017年3月20日 下午5:38:19
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
