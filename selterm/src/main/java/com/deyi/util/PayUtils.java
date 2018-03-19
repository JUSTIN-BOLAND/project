package com.deyi.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.deyi.entity.Order;
import com.deyi.exception.PayException;
import com.deyi.paysdk.entity.DeyiPay;
import com.deyi.paysdk.entity.WXPrepay;
import com.deyi.paysdk.entity.WXPrepayResp;
import com.deyi.paysdk.utils.Configs;
import com.deyi.paysdk.utils.DeyiPayExceptioin;
import com.deyi.paysdk.utils.WxSign;
import com.dy.util.HttpClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 *
 * pure
 * @author hejx
 * @2016年3月19日
 */
public class PayUtils {
	private static Logger log = LoggerFactory.getLogger(PayUtils.class);

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public  static boolean isquert(String str, String pic) {
		String quert[] = str.split(",");
		for (int i = 0; i < quert.length; i++) {
			if (pic.equals(quert[i])) {
				return true;
			}
		}
		return false;
	}

	private static Map<String, String> beanToMap(Object entity){
	        Map<String, String> parameter = new HashMap<String, String>();
	         Field[]   fields   =   entity.getClass().getDeclaredFields();
	        for(int i = 0; i < fields.length; i++){
	            String fieldName =  fields[i].getName();
	            Object o = null;
	            String firstLetter = fieldName.substring(0, 1).toUpperCase();
	               String getMethodName = "get" + firstLetter + fieldName.substring(1);
	               Method getMethod;
	            try {
	                getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});
	                 o = getMethod.invoke(entity, new Object[] {});
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            if(o != null){
	                parameter.put(fieldName, (String)o);
	            }
	        }
	        System.out.println("bean转map："+parameter.toString());
	        return parameter;
	    }



	/**
	 *
	 * 方法用途: 将参数按字母顺序排序并组装成如下格式<br>
	 * clientCode=01&clientDr=99&timestamp=20140716<br>
	 * 实现步骤: <br>

	 * @return
	 */
	public static String genSortParams(String[] params) {
		if (params == null || params.length <= 0) {
			return null;
		}

		Arrays.sort(params);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < params.length; i++) {
			//System.out.println("[genSortParams] : "+ (params[i] == null? "": params[i]));
			//防止空value
			if(params[i] == null || params[i].length() <= 0 || params[i].indexOf("=") <= 0 ) {
				continue;
			}
			String tmp = params[i].substring(params[i].indexOf("=") + 1);
			if(tmp == null || tmp.length() <= 0 || "NULL".equals(tmp.trim().toUpperCase())) {
				continue;
			}
			sb.append(params[i]);
			if (i < params.length - 1) {
				sb.append("&");
			}
		}
		System.out.println("签名参数："+sb.toString());
		return sb.toString();
	}

	public static Map<String, String> genSortMapParams(String[] params) {
		if (params == null || params.length <= 0) {
			return null;
		}

		Arrays.sort(params);

		Map<String, String> sortMap = new HashMap<String, String>();
		for (int i = 0; i < params.length; i++) {
			String tmp =  params[i];
			String key = tmp.substring(0, tmp.indexOf("="));
			String value = tmp.substring(tmp.indexOf("=") + 1);
			sortMap.put(key, value);
		}

		return sortMap;
	}

	/**
	 *
	 * 方法用途: 根据参数返回支付宝要求的处理结果数据<br>
	 * 实现步骤: <br>
	 * @param obj
	 * @return
	 */
	public static String composeOrderResult4Alipay(Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		try {
			Field[] field = obj.getClass().getDeclaredFields();
			StringBuffer data = new StringBuffer();
			for (int i = 0; i < field.length; i++) {
				String name = field[i].getName(); // 获取属性的名字
				String tempName = name.substring(0, 1).toUpperCase() + name.substring(1);
				Method m = obj.getClass().getMethod("get" + tempName);
				String value = (String) m.invoke(obj);
				if (value != null && value.length() > 0) {
					data.append(name + ":\"");
					data.append(value);
					data.append("\",");
				}
			}
			sb.append(data.toString().substring(0, data.toString().lastIndexOf(",")));
		} catch (Exception ex) {
			return "{is_success:\"F\",error_code:\"OUT_SYSTEM_ERROR\"}";
		}

		sb.append("}");
		return sb.toString();
	}

	/**
	 *
	 * 方法用途: MD5加密，字符集编码为GBK<br>
	 * 实现步骤: <br>
	 * @param str
	 * @return
	 */
	public static String getMD54GBK(String str) {
		String gbkMD5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(str.getBytes("GBK"));
			gbkMD5 = byteArrayToHexString(results);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return gbkMD5;
	}

	public static String getMD5ForUTF8(String str) throws Exception{
		String utf8MD5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(str.getBytes("UTF-8"));
			utf8MD5 = byteArrayToHexString(results);
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		}

		return utf8MD5;
	}

	/**
	 *
	 * 方法用途: 将一个字节转化成十六进制形式的字符串<br>
	 * 实现步骤: <br>
	 * @param b
	 * @return
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 *
	 * 方法用途: 将一个字节转化成十六进制形式的字符串<br>
	 * 实现步骤: <br>
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 *
	 * 方法用途: 生成随机字符串<br>
	 * @param length 表示生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());
	        sb.append(base.charAt(number));
	    }
	    return sb.toString();
	 }

	/**
	 * 校验为空
	 * @param order
	 */
	public static void validateOrder(Order order){
		if(order.getOrderMoney() == null){
			throw new PayException("PayMoney为空");
		}
		if(StringUtils.isBlank(String.valueOf(order.getOrderMoney()),order.getAuthCode(),order.getOrderNo())){
			throw new PayException("OrderMoney,AuthCode,OrderNo为空");
		}
		if(order.getOrderMoney() <= 0){
			throw new PayException("支付金额错误PayMoney");
		}
		if(!"1".equals(order.getOrderStatus())){//1未支付
			throw new PayException("订单状态不正确");
		}
		if(order.getUndiscountMoney() != null && order.getUndiscountMoney() <0){//不打折金额错误
			throw new PayException("不打折金额错误");
		}

	}
	public static void validateRefund(Order order){

		if(StringUtils.isBlank(String.valueOf(order.getPayMoney()),order.getOrderNo())){
			throw new PayException("OrderMoney,OrderNo为空");
		}
		if(order.getPayMoney() <= 0){
			throw new PayException("退款金额错误PayMoney");
		}
		if(!"2".equals(order.getPayStatus())){//1未支付
			throw new PayException("支付状态不正确");
		}

	}
	/**
	 *
	 * @content  二维码内容
	 * @param savePath 保存到服务器的路径
	 * @throws PayException
	 */
	public static void createRQ(String content, String savePath,String picPif) throws PayException {
		log.info("paramters:[content="+content+",savePath="+savePath+"]");

		try {
			content = new String(content.getBytes("GBK"), "iso-8859-1");
			String imagePath = savePath + File.separator + picPif + ".bmp";
			File file = new File(imagePath);

			QRCodeWriter writer = new QRCodeWriter();
			// 生成二维码
			BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200);
			MatrixToImageWriter.writeToPath(matrix, "bmp", file.toPath());
			// 读取二维码
//			QRCodeReader reader = new QRCodeReader();
			/*BufferedImage image = ImageIO.read(file);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);*/
//			com.google.zxing.Result result = reader.decode(imageBinaryBitmap);
//			log.info("解析的二维码内容为:" + result.getText());
		} catch (Exception e) {
			log.error("创建二维码异常:", e);
			throw new PayException("创建二维码失败");
		}
	}
	/**
	 * JsonToMap
	 * @param str
	 */
	public static Map<String,String> JsonToMap(String str){
		 Map<String,String> map = new HashMap<String,String>();

		 if(StringUtils.isBlank(str)){
			 log.error("JsonToMap str为空...");
			 return map;
		 }
		 Map mapTypes = JSON.parseObject(str);
	     for (Object obj : mapTypes.keySet()){
	         map.put(obj.toString(), mapTypes.get(obj).toString());
	     }

	     return map;

	}

	public static String signData(Map<String, String> map,String key) throws UnsupportedEncodingException{
			String[] params = { "orgCode=" + map.get("orgCode"),
					"merCode=" + map.get("merCode"),"storeId=" + map.get("storeId"),
					"orderMoney=" + map.get("orderMoney"), "orderNo=" + map.get("orderNo"),
					"payType=" + map.get("payType")};
			String param = PayUtils.genSortParams(params);
			String sign = DigestUtils.md5Hex((param+"&key="+key).getBytes("utf-8")).toUpperCase();
			log.info("sign=" + sign + "");
			return sign;
		}

	public static void main(String[] args) {


		 String[] parameters = { "service=alipay.mobile.qrcode.create", "partner=2088101103495019",
		 "_input_charset=utf-8", "timestamp=2012-12-21 17:11:16", "method=stop",
		 "qrcode=https://qr.alipay.com/2664646145424884", "clientCode=lb=1", "ccode=90", "sku_name=" };

		 System.out.println(genSortParams(parameters));

//		Map<String, String> tmpMap = genSortMapParams(parameters);
//		Collection<String> c = tmpMap.values();
//		Iterator it = c.iterator();
//		for (; it.hasNext();) {
//			System.out.println(it.next());
//		}


//		AlipayResult a = new AlipayResult();
//		a.setIs_success("T");
//		System.out.println(composeResult(a));


		String str ="<xml>"
				+ "<appid><![CDATA[wxa63c789948d1cb22]]></appid>"
				+ "<openid><![CDATA[oH6E0wxwEJbVnFDJsFVXUP2d1Ll8]]></openid>"
				+ "<mch_id><![CDATA[1253006001]]></mch_id>"
				+ "<is_subscribe><![CDATA[N]]></is_subscribe>"
				+ "<nonce_str><![CDATA[B2BgQl0BbIcs9vGO]]></nonce_str>"
				+ "<product_id><![CDATA[2015081300000045]]></product_id>"
				+ "<sign><![CDATA[A78125A3C7AFD6E0197338F082116733]]></sign>"
				+ "</xml>";
//
//		XmlPrepayV3 xml = (XmlPrepayV3)formatXmlToObject(XmlPrepayV3.class,str);
//		String xmlStr = formatObjectToXml(xml,XmlPrepayV3.class);
//
//		System.out.println(xml.getAppid());
//		System.out.println(xmlStr);
	}
}
