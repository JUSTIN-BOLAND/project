package cn.saobei.entity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpUtils {

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	public static String send(String url, Object object) throws ClientProtocolException, IOException {
		CloseableHttpClient createDefault = HttpClients.createDefault();
		
		
		
		String json = new Gson().toJson(object);
		System.out.println(json);
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		httpPost.addHeader("charset","UTF-8");
		
		StringEntity stringEntity = new StringEntity(json,"UTF-8");
		httpPost.setEntity(stringEntity);
		stringEntity.setContentType(CONTENT_TYPE_TEXT_JSON);
		stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
		CloseableHttpResponse response = createDefault.execute(httpPost);
		HttpEntity entity = httpPost.getEntity();
		
		System.out.println("-----------------------");
		System.out.println(response.getStatusLine());
		String text  = null;
		StringBuffer stringBuffer = new StringBuffer();
		if(null != entity){
			System.out.println("Response context length:" + entity.getContentLength());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			while ((text =bufferedReader.readLine()) != null  ) {
				stringBuffer.append(text);
			}
		}
		String string = EntityUtils.toString(response.getEntity(), "UTF-8");
		EntityUtils.consume(entity);
		System.out.println("buffer:" + stringBuffer.toString());
		response.close();
		createDefault.close();
		return string;

	}
}
