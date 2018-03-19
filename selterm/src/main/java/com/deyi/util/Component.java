package com.deyi.util;

import com.deyi.entity.CodeName;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class Component<T> extends Constants {
//	@Autowired
//	protected LoggerUtil loggerUtil;

   protected List<CodeName> buildStatisList(String name, boolean zeroType, boolean sorted){
	   List<CodeName> list =  new ArrayList<CodeName>();
	   CodeName  codeName ;
	   String[] names = name.split(",");
	   int begin=0;
	   if(!zeroType) begin=1;
	   if(sorted) {
		   for (int i = names.length-1;i>=0 ;i--) {
			   codeName = new CodeName();
			   codeName.setCode(i + begin+"");
			   codeName.setName(names[i]);
			   list.add(codeName);

		   }
	   }
	   else{
		   for (int i = 0; i < names.length; i++) {
			   codeName = new CodeName();
			   codeName.setCode(i + begin+"");
			   codeName.setName(names[i]);
			   list.add(codeName);
		   }
	   }
	   return list;
   }
	protected  String upperFirstLetter(String src){
		if(src==null || src.trim().length() ==0 ) return src;
		return src.substring(0,1).toUpperCase() + src.substring(1);
	}
	protected Object getMethodValue(T obj,String field){
		if(field!=null && field.trim().length() >0 ){
			try {
				Class<? extends Object> bean = obj.getClass();
				String methodName = "get"+upperFirstLetter(field);
				Method method = bean.getDeclaredMethod(methodName);
				Object fieldValue = method.invoke(obj);
				return fieldValue;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return null;

	}
	/**
	 *
	 * 方法用途: <br>
	 * @param request
	 * @param params 实体类
	 * @param page  分页类
	 */
	protected void setParams(HttpServletRequest request,T params,Page<T> page){
		page.setUrl(request.getRequestURI());
		Class<? extends Object> bean = params.getClass();
		Field[] fields = bean.getDeclaredFields();
		for(Field field:fields){
			try {
				if(!"serialVersionUID".equals(field.getName())){
					String methodName = "get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1, field.getName().length());
					Method method = bean.getDeclaredMethod(methodName);
					Object fieldValue = method.invoke(params);
				//	this.log("setParams","name="+field.getName()+",methodName="+methodName+",fieldValue="+fieldValue);
					page.getParams().put(field.getName(),fieldValue);
					//this.log("setParams","name="+field.getName()+",fieldValue="+page.getParams().get(field.getName()));
				}
			} catch (Exception e) {
               this.log("setParams->error",e.getMessage());
			}
		}
	}
	public   String getUrl(String webUrl){
		String webUrlNoHttp = webUrl.replace("http://","").replace("https://","");
		String[] urls = webUrlNoHttp.split("/");
		webUrl = "http://"+urls[0]+"/"+urls[1];

		return webUrl;
	}
	protected String getWebRoot(HttpServletRequest request){
		String webRoot = request.getSession().getServletContext().getRealPath("/");
		String uploadFilePath = webRoot.substring(0, webRoot.indexOf("webapps") + 7) ;
		//uploadFilePath = request.getHeader("X-Forwarded-Scheme")+"://"
		System.out.println("[getWebRoot] :"+uploadFilePath);
		return uploadFilePath;
	}
	public   String getUrl(HttpServletRequest request){
		String webUrl = request.getRequestURL().toString();
		String webUrlNoHttp = webUrl.replace(request.getScheme()+"://","").replace("https://","");
		String[] urls = webUrlNoHttp.split("/");
		webUrl = request.getScheme()+"://"+urls[0]+"/"+urls[1];
		this.log("getUrl",webUrl);
		//this.log("getUrl",request.getScheme()+" -> "+ request.getServerName()+" -> "+request.getRequestURI());
		return webUrl;
	}
	public   String getBaseDomain(HttpServletRequest request){
		String webUrl = request.getRequestURL().toString();
		String webUrlNoHttp = webUrl.replace(request.getScheme()+"://","").replace("https://","");
		String[] urls = webUrlNoHttp.split("/");
		webUrl = request.getScheme()+"://"+urls[0];
		this.log("getBaseDomain",webUrl);
		//this.log("getUrl",request.getScheme()+" -> "+ request.getServerName()+" -> "+request.getRequestURI());
		return webUrl;
	}

	protected String genDealerCode(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

		return "S"+df.format(c.getTime()) + RandomUtil.randomNum(4);
	}
	protected String genLogoImgName(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyMMddMMddHHmm");

		return "lg"+df.format(c.getTime()) + RandomUtil.randomNum(4);
	}
	protected String genRpImgName(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyMMddMMddHHmm");

		return "rp"+df.format(c.getTime()) + RandomUtil.randomNum(4);
	}

	protected String geQrCode(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmm");

		return "QR"+df.format(c.getTime()) + RandomUtil.randomNum(6);
	}
	protected  String sign(String[] params, String key) throws UnsupportedEncodingException {
/*		String[] params = { "appid=" + microEntity.getAppid(), "sub_appid=" + microEntity.getSub_appid(),
				"mch_id=" + microEntity.getMch_id(),"sub_mch_id=" + microEntity.getSub_mch_id(), "device_info=" + microEntity.getDevice_info(),
				"nonce_str=" + microEntity.getNonce_str(), "body=" + microEntity.getBody(),"detail=" + microEntity.getDetail(),"attach=" + microEntity.getAttach(),
				"out_trade_no=" + microEntity.getOut_trade_no(), "total_fee=" + microEntity.getTotal_fee(),"fee_type=" + microEntity.getFee_type(),
				"spbill_create_ip=" + microEntity.getSpbill_create_ip(), "goods_tag=" + microEntity.getGoods_tag(),
				"limit_pay=" + microEntity.getLimit_pay(),"auth_code=" + microEntity.getAuth_code()};*/
		String param = PayUtils.genSortParams(params);
		System.out.println("[sign] : param=" + param + ",key="+key+"");
		String sign = DigestUtils.md5Hex((param+"&key="+key).getBytes("utf-8")).toUpperCase();
		System.out.println("[sign] : sign=" + sign + "");
		return sign;
	}
	protected  void log(String method,String msg){
		System.out.println("【"+method+"】 : "+ msg);
	}
	public String genNo(String prefix){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		return prefix+df.format(c.getTime()) + RandomUtil.randomNum(6);
	}

	public   String getDomain(String webUrl){
		String webUrlNoHttp = webUrl.replace("http://","").replace("https://","");
		String[] urls = webUrlNoHttp.split("/");

		webUrl = urls[0];

		return webUrl;
	}
	public   String getWebRoot(String webUrl){
		String webUrlNoHttp = webUrl.replace("http://","").replace("https://","");
		String[] urls = webUrlNoHttp.split("/");
		webUrl = "http://"+urls[0]+"/"+urls[1];

		return webUrl;
	}

	public String getIpAddress()  {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(address != null ) return address.getHostAddress();
		else return "";
	}

}
