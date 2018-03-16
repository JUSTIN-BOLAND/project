package com.lb.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 *
* @Description: TODO
* @author hejx
* @date 2015年12月12日
*
 */
public class HttpClient {

	private static Logger log=LoggerFactory.getLogger(HttpClient.class);


	public static String sendXml(String postURL, String requestBody, String sendCharset, String readCharset)
			throws Exception {
		HttpURLConnection httpConn = null;

		StringBuffer responseSb = new StringBuffer();

		try {
			URL postUrl = new URL(postURL);
			// 打开连接
			httpConn = (HttpURLConnection) postUrl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=" + sendCharset);
			httpConn.setConnectTimeout(1000 * 10);
			httpConn.setReadTimeout(1000 * 10);
			httpConn.connect();
			DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
			out.write(requestBody.getBytes(sendCharset));
			out.flush();
			out.close();
			int status = httpConn.getResponseCode();

			if (status != HttpURLConnection.HTTP_OK) {
				log.info("发送请求失败，状态码：[" + status + "] 返回信息：" + httpConn.getResponseMessage());
				return null;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), readCharset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseSb.append(line.trim());
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("发送请求[" + postURL + "]失败，" + ex.getMessage());
			throw ex;
		} finally {
			httpConn.disconnect();
		}
		return responseSb.toString().trim();
	}
	/**
	 * 功能：
	 *
	 * @param postURL
	 * @param requestBody
	 * @param sendCharset
	 * @param readCharset
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static String send(String postURL, String requestBody, String sendCharset, String readCharset)
			throws Exception {
		HttpURLConnection httpConn = null;

		StringBuffer responseSb = new StringBuffer();

		try {
			URL postUrl = new URL(postURL);
			// 打开连接
			httpConn = (HttpURLConnection) postUrl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + sendCharset);
			httpConn.setConnectTimeout(1000 * 10);
			httpConn.setReadTimeout(1000 * 10);
			httpConn.connect();
			if(requestBody!=null && requestBody.trim().length() > 0 ) {
				DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
				out.write(requestBody.getBytes(sendCharset));
				out.flush();
				out.close();
			}
			int status = httpConn.getResponseCode();

			if (status != HttpURLConnection.HTTP_OK) {
				log.info("发送请求失败，状态码：[" + status + "] 返回信息：" + httpConn.getResponseMessage());
				return null;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), readCharset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseSb.append(line.trim());
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("发送请求[" + postURL + "]失败，" + ex.getMessage());
			throw ex;
		} finally {
			httpConn.disconnect();
		}
		return responseSb.toString().trim();
	}

	public static String getRequestContent(HttpServletRequest request) throws ServletException, IOException {

   	 String resp = "";
   	 try{
   	   // 获取页面内容
   	   InputStream in = request.getInputStream();
   	   BufferedReader breader = new BufferedReader(
   	     new InputStreamReader(in, "UTF-8"));
   	   String str = breader.readLine();
   	   while (str != null) {
	    	    resp +=str;
	    	    str = breader.readLine();
	    	   }
   	  } catch (Exception e) {
   		  e.printStackTrace();
   	  } finally {
   	   }

   	 return resp;


}

	public static Map<String,String> getRequestParams(HttpServletRequest request) throws ServletException, IOException {
		Map<String,String> params = new HashMap<String,String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			params.put(parameterName,request.getParameter(parameterName));
			//log.info("[getRequestParams] :"+parameterName+" :"+ request.getParameter(parameterName));
		}
		return params;
	}



	/**
	 *
	  * @purpose: 证书方式的请求
	  * @param url
	  * @param xml
	  * @param certPath
	  * @return
	  * @throws Exception
	  * String
	 */
	 public static String sendPostSSL(String url,String xml,String certPath,String merId) throws Exception {
		    String back = "";
	        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	        //"c:/cert/apiclient_cert.p12"
	        FileInputStream instream = new FileInputStream(new File(certPath));
	        try {
	            keyStore.load(instream, merId.toCharArray());
	        } finally {
	            instream.close();
	        }

	        // Trust own CA and all self-signed certs
	        SSLContext sslcontext = SSLContexts.custom()
	                .loadKeyMaterial(keyStore, merId.toCharArray())
	                .build();
	        // Allow TLSv1 protocol only
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
	        try {

	        	StringEntity stringEntity = new StringEntity(xml);
	        	//"https://api.mch.weixin.qq.com/secapi/pay/refund"
	        	HttpPost httpget = new HttpPost(url);

	        	httpget.setEntity(stringEntity);
	            CloseableHttpResponse response = httpclient.execute(httpget);
	            try {
	                HttpEntity entity = response.getEntity();
	                if (entity != null) {
	                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
	                    String text;
	                    while ((text = bufferedReader.readLine()) != null) {
//	                        System.out.println(text);
	                        back += text;
	                    }

	                }
	                EntityUtils.consume(entity);
	            } finally {
	                response.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return back;
	    }
}
