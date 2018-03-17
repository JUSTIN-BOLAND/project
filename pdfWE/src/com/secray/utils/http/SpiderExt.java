package com.secray.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 类  名: SpiderExt
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年3月17日 上午11:00:03
 *
 */
public class SpiderExt {

	public static final Logger log = Logger.getLogger(SpiderExt.class);

	public static int GET=1;
	public static int POST=2;

	public static int SUBMIT=1;
	public static int LOGIN=2;
	//ajax方式提交
	public static int XMLHttpRequest=3;

	private Map<String, String> args;
	private String url;
	private String host;
	private String userName;
	private String password;
	private int submitMethod;
	private String cookie;

	private HttpGet httpGet = null;
	private HttpPost httpPost = null;
	protected CloseableHttpClient httpclient = null;
	protected CloseableHttpResponse response1 = null;
	protected  BasicCookieStore cookieStore = null;
	protected  HttpEntity entity = null;
	private Header[] headers = {
			new BasicHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1; SV1; .NET CLR 2.0.50727; CIBA)"),
			new BasicHeader("Accept-Language", "zh-cn,zh"),
			new BasicHeader("Accept", " image/gif, image/x-xbitmap, image/jpeg, " +
					"image/pjpeg, application/x-silverlight, application/vnd.ms-excel, " +
					"application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, */*"),
			new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
			new BasicHeader("Accept-Encoding", "gzip, deflate")};



	// @return the host

	public String getHost() {
		return host;
	}
	// @param TODO

	public void setHost(String host) {
		this.host = host;
	}
	// @return the userName

	public String getUserName() {
		return userName;
	}
	// @param TODO

	public void setUserName(String userName) {
		this.userName = userName;
	}
	// @return the password

	public String getPassword() {
		return password;
	}
	// @param TODO

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	// @return the cookieStore

	public BasicCookieStore getCookieStore() {
		return cookieStore;
	}
	// @param TODO

	public void setCookieStore(BasicCookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
	public SpiderExt(int submitMethod,String url){
		this(submitMethod,url,null,null,null);
	}
	public SpiderExt(int submitMethod,String url,String userName,String password,Map<String, String> args){
		this.submitMethod = submitMethod;
		this.url = url;
		this.userName = userName;
		this.password = password;
		if (args == null) {
			args = new HashMap<String, String>();
		}
		cookieStore = new BasicCookieStore();
		httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		//httpclient.
		// httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);

	}

	public   Header[] concat(Header[] a, Header[] b) {
		Header[] c= new Header[a.length+b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	public int submit(int submitType) throws IOException {


		//初始化url中的参数
		if( this.host == null || this.host.trim().length() == 0 ) {
			String noHttpStr = "http://";
			String noHttp = this.url.replace(noHttpStr, "");
			if(noHttp.indexOf("/")==-1) this.host = noHttp;
			else this.host = this.url.substring(0, noHttpStr.length()+noHttp.indexOf("/")+1);

			Header[] hostHeaders = {
					new BasicHeader("Referer", this.host),
					new BasicHeader("Host", this.host.replace("http://", ""))
			};

			this.headers = concat(this.headers, hostHeaders);
		}
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();  // 构建参数
		if( submitType == SpiderExt.LOGIN ) {
			nvps.add(new BasicNameValuePair("username",this.userName));
			nvps.add(new BasicNameValuePair("password", this.password));
	       /* Header[] xmlHeaders = { };
	        this.headers = concat(this.headers, xmlHeaders);*/
		}
		if( submitType == XMLHttpRequest){
			Header[] xmlHeaders = { new BasicHeader("X_REQUESTED_WITH", "XMLHttpRequest")/*,
					 				new BasicHeader("Referer", this.host),
					 				new BasicHeader("Host", this.host.replace(noHttpStr, ""))*/
			};
			this.headers = concat(this.headers, xmlHeaders);
			/* Cookie: PHPSESSID=bqvtej224osns754hql3adoft6
			 Cookie cookie */
			// this.cookieStore.addCookie(cookie);
		}

		if(cookie != null && cookie.trim().length() >0) {
			Header[] cookieHeaders = { new BasicHeader("Cookie", this.cookie)};
			this.headers = concat(this.headers, cookieHeaders);
		}
		// 增加其他的参数
		if( args != null ){
			Set<String> set = args.keySet();
			for (String string : set) {
				nvps.add(new BasicNameValuePair(string, args.get(string)));
			}
		}


		if( this.submitMethod == SpiderExt.GET ){
			httpGet = new HttpGet(this.url);
			//httpGet.setParams(params);//.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			httpGet.setHeaders(headers);
			response1 = httpclient.execute(httpGet);
		}
		else
		{
			httpPost = new HttpPost(this.url);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			httpPost.setHeaders(headers);
			response1 = httpclient.execute(httpPost);
		}




		entity = response1.getEntity();


		log.info("submit : " + response1.getStatusLine());
		return response1.getStatusLine().getStatusCode();


	}

	public List<Cookie> getCookies(){
		return  cookieStore.getCookies();
	}
	public String getCookieValue(String cookieName){

		List<Cookie> cookies = getCookies();
		if (cookies.isEmpty()) {
			return null;
		} else {
			String result = null;
			for (int i = 0; i < cookies.size(); i++) {
				log.info("- " + cookies.get(i).toString());
				if(cookieName.toUpperCase().equals(cookies.get(i).getName().toUpperCase())){
					result = cookies.get(i).getValue();
					break;
				}
			}
			return result;
		}
	}
	public String getPageContent() {
		String result =  null;

		try
		{
			//HttpEntity entity = response1.getEntity();

			BufferedReader rd = new BufferedReader(
					new InputStreamReader(this.entity.getContent()));

			StringBuffer pageContent = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				log.info("getPageContent : "+line);
				pageContent.append(line);
			}
			result  = pageContent.toString();
		}
		catch(IOException ioe){
			ioe.getMessage();
		}
		return result;
	}


	public void close(){
		try
		{
			if( entity != null) EntityUtils.consume(entity);
			if( response1 != null) response1.close();
			if( httpclient != null) httpclient.close();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void lbs()
	{
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		try {

			HttpPost post = new HttpPost("http://beian.cndns.com/");
			BasicClientCookie cookie = new BasicClientCookie("name", "zhaoke");
			cookie.setVersion(0);
			//cookie.setDomain("/pms/");   //设置范围
			cookie.setPath("/");
			//cookieStore.addCookie(cookie);
			httpClient.execute(post);//
			List<Cookie> cookies = cookieStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println("Local cookie: " + cookies.get(i));
			}
			Header[] h =post.getAllHeaders();
			for (Header header : h) {
				System.out.println(header.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}
	}
	public static void lb(){
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		CloseableHttpResponse response1 = null;
		try {
			//HttpGet httpget = new HttpGet("https://someportal/");
			//HttpGet httpget = new HttpGet("https://www.oschina.net/home/login");
			HttpGet httpget = new HttpGet("http://beian.cndns.com/");
			response1 = httpclient.execute(httpget);
			//try {
		 /*HttpEntity entity = response1.getEntity();

			BufferedReader rd = new BufferedReader(
			new InputStreamReader(response1.getEntity().getContent()));
			System.out.println("--------------------Next will show something else");
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
			  System.out.println(line);
			}*/

			System.out.println("Login form get: " + response1.getStatusLine());
			//EntityUtils.consume(entity);

			System.out.println("Initial set of cookies:");
			List<Cookie> cookies = cookieStore.getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{  response1.close(); } catch(IOException ioe){ioe.printStackTrace();}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		SpiderExt.lb();

	}

}
