package com.secray.utils.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.secray.utils.random.RandomUtil;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import org.apache.commons.httpclient.*;

import com.secray.utils.common.RegUtil;
/**
 *
 * @author CallMeWhy
 *
 */
public class Spider {
	public static final Logger log = Logger.getLogger(Spider.class);

	public static int GET = 1;
	public static int POST = 2;

	private HttpClient httpClient;
	private GetMethod getMethod;
	private PostMethod postMethod ;
	private String url;
	private int statusCode;
	private String charSet;
	private int requestMethod;

	// @return the postMethod

	public PostMethod getPostMethod() {
		return postMethod;
	}

	// @return the statusCode

	public int getStatusCode() {
		return statusCode;
	}

	public Spider(String url){
		this(url,Spider.GET);
	}

	public Spider(String url,int requestMethod){
		httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(100000000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(100000000);
		//httpClient.setConnectionTimeout(10000000);
		this.url = url;

		try{

			if( requestMethod == Spider.GET){
				getMethod = new GetMethod(url);
				getMethod.setRequestHeader("x-forwarded-for", RandomUtil.getIP());
				getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
				statusCode = httpClient.executeMethod(getMethod);
			}
			else {
				postMethod = new PostMethod(url);
				postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
				httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
				httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
				// postMethod.setRequestBody(param);
				statusCode = httpClient.executeMethod(postMethod);
				//statusCode = postMethod.getStatusCode();
			}
			this.requestMethod = requestMethod;

			WebEncoding web=new WebEncoding();
			try
			{
				this.charSet = web.getCharset(url);
			}
			catch(Exception e){
				this.charSet = "UTF-8";
			}
			//System.out.println("charSet:"+charSet);
			//this.charSet = "UTF-8";//"GBK";
			// getCookie();
			web = null;
		}
		catch(IOException ioe){
			statusCode = HttpStatus.SC_EXPECTATION_FAILED;
			//ioe.printStackTrace();
		}
	}

	public String getCookie(){
	/* Header[] headers = postMethod.getResponseHeaders();
	 HeaderElement[] elements = null;
	 for(Header header : headers){
		 if(header.getElements().length ==0 ) break;
		 elements = header.getElements();
		 for(HeaderElement element : elements ) {
			log.info(element.getName()+" : "+element.getValue());
		 }
	 }*/

		Cookie[] cookies = httpClient.getState().getCookies();
		for(Cookie cookie : cookies){
			log.info(cookie.getName()+" : "+cookie.getValue());
		}
		return "";

	}
	public boolean downloadPage(String destPath) throws Exception {

		InputStream input = null;
		OutputStream output = null;


		if (statusCode == HttpStatus.SC_OK) {
			input = getMethod.getResponseBodyAsStream();
			// getMethod.get

			String filename =  destPath+"/"+url.substring(url.lastIndexOf('/') + 1)
					+ ".html";

			output = new FileOutputStream(filename);

			int tempByte = -1;
			while ((tempByte = input.read()) > 0) {
				output.write(tempByte);
			}

			if (input != null) {
				input.close();
			}

			if (output != null) {
				output.close();
			}
			return true;
		}
		return false;
	}

	public String[] getPageContent(String regExp,int idx,String seperator){
		String[] content = new String[1];

		if (statusCode == HttpStatus.SC_OK) {
			try{
				// log.info("charSet : "+charSet);

				if( requestMethod == Spider.GET){
					//String st = getMethod.getResponseBodyAsString(2100000000);
					//content[0] = new String(getMethod.getResponseBodyAsString().getBytes("ISO-8859-1"), this.charSet.toUpperCase());
					content[0] = convertStreamToString(getMethod.getResponseBodyAsStream());
				}
				else{
					content[0] = convertStreamToString(postMethod.getResponseBodyAsStream());
					//content[0] = new String(postMethod.getResponseBodyAsString().getBytes("ISO-8859-1"), this.charSet.toUpperCase());
				}
				//log.info(content[0]);
				if(regExp != null && regExp.trim().length() > 0) {
					//                 /System.out.println(content[0]);
					content = RegUtil.getRegContent(content[0], regExp,idx,seperator);
				}
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		return content;
	}
	public  String convertStreamToString(InputStream is) {
		StringBuilder sb1 = new StringBuilder();
		//String sb2="";
		byte[] bytes = new byte[8192];
		int size = 0;
		String str=null;

		try {
			while ((size = is.read(bytes)) > 0) {
				str = new String(bytes, 0, size,this.charSet);
             /*log.info("lb: --------begin---------- ");
             log.info("lb: "+str);
             log.info("lb: --------end---------- ");*/
				sb1.append(str);
				//sb2 +=str;
			}
			// log.info("lb: "+sb1.length());
			return sb1.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void releaseConnection(){
		if( requestMethod == Spider.GET){
			if(this.getMethod != null ) this.getMethod.releaseConnection();
			this.getMethod = null;
		}
		else {
			if(this.postMethod != null ) this.postMethod.releaseConnection();
			this.postMethod = null;
		}

		this.httpClient = null;
	}

	public static void main(String[] args) {
		try {

			Spider spider =new Spider("http://www.qyyoga.com.cn/",Spider.GET);
			log.info(spider.getStatusCode());
			//spider.releaseConnection();
			//spider =new Spider("http://www.beianbeian.com/search/sina.com",Spider.POST);
			String[] sts = spider.getPageContent(null, 1, "%");
			log.info(sts[0]);
			spider.releaseConnection();
			//spider.downloadPage("e:/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}