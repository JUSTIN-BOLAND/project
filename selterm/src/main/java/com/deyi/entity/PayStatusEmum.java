/**
 * 
 */
package com.deyi.entity;

/**
 * pure
 * @author hejx
 * @2016年3月23日
 */
public enum PayStatusEmum {

	//支付成功
	SUCCESS(1,"支付成功"),
	//转入退款
	REFUND(2,"转入退款"),
	//未支付
	NOTPAY(3,"未支付"),
	//已关闭
	CLOSED(4,"已关闭"),
	//已撤销(刷卡支付)
	REVOKED(5,"已撤销"),
	//-用户支付中
	USERPAYING(6,"用户支付中"),
	//支付失败
	PAYERROR(7,"支付失败");
	
	private Integer value;
	private String msg;
	
	PayStatusEmum(Integer value,String msg){
		this.value = value;
		this.msg = msg;
	}
	
	public static String getMsgByval(int value){
		 for (PayStatusEmum aLight : PayStatusEmum.values()) {
			 if(aLight.getValue() == value){
				 return aLight.getMsg();
			 }
	     }
		 
		 return "";
	}
	public static int getValBMsg(String msg){
		 int val = 0;
		 if("SUCCESS".equals(msg)){
			 val = 1;
		 }else if("REFUND".equals(msg)){
			 val = 2;
		 }else if("NOTPAY".equals(msg)){
			 val = 3;
		 }else if("CLOSED".equals(msg)){
			 val = 4;
		 }else if("REVOKED".equals(msg)){
			 val = 5;
		 }else if("USERPAYING".equals(msg)){
			 val = 6;
		 }else if("PAYERROR".equals(msg)){
			 val = 7;
		 }
		return val;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
