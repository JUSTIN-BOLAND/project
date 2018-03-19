package com.deyi.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
* @Description: TODO
* @author hejx
* @date 2015年12月12日
*
 */
public class HttpClient {

	private static Logger log=LoggerFactory.getLogger(HttpClient.class);
	public static final String BOUNDARYSTR = "aifudao7816510d1hq";
	public static final String BOUNDARY = "--" + BOUNDARYSTR + "\r\n";

	public static String getRequestContent(HttpServletRequest request) throws ServletException, IOException {
		String resp = "";

		try {
			try {
				ServletInputStream e = request.getInputStream();
				BufferedReader breader = new BufferedReader(new InputStreamReader(e, "UTF-8"));

				for(String str = breader.readLine(); str != null; str = breader.readLine()) {
					resp = resp + str;
				}
			} catch (Exception var8) {
				var8.printStackTrace();
			}

			return resp;
		} finally {
			;
		}
	}

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

	public static String getContext(HttpServletRequest request) throws ServletException, IOException {

   	 String resp = "";
   	 try{
   	   // 获取页面内容
   	   java.io.InputStream in = request.getInputStream();
   	   java.io.BufferedReader breader = new BufferedReader(
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


	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	public static String doPostSSL(String url, String xml,String encoding) throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {
		String body = "";
		//采用绕过验证的方式处理https请求
		SSLContext sslcontext = createIgnoreVerifySSL();

		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext))
				.build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);

		//创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();

		// /       CloseableHttpClient client = HttpClients.createDefault();

		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		StringEntity stringEntity = new StringEntity(xml);
		//设置参数到请求对象中
		httpPost.setEntity(stringEntity);

		System.out.println("请求地址："+url);
		System.out.println("请求参数："+stringEntity.toString());

		//设置header信息
		//指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		//获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		//释放链接
		response.close();
		return body;
	}
}
