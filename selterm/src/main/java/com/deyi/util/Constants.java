package com.deyi.util;

public class Constants {
	public final static String MENU_LIST="menuList";
	
	public final static String SESSION_USERINFO="userInfo";
	/**用户有权限的url**/
	public final static String SESSION_USER_URLS="userUrls";

	/**系统用户**/
	public final static String USER_SYS="0";
	/**经销商**/
	public final static String USER_DEALER="1";

	/**机构用户**/
	public final static String USER_OPERATOR="1";
	
	/**业务员**/
	public final static String USER_AGENT="2";
	
	/**商户**/
	public final static String USER_MERCHANT="3";
	
	/**店员**/
	public final static String USER_STORE="4";
	
	/**运营商用户**/
	public final static String SYS_USER_OPERATOR="5";
	
	/**门店编号 序列号**/
	public final static String STORE_CODE = "store_code";
	
	/**订单序列号**/
	public final static String ORDER_ID = "order_id";
	
	
	
	/**
	 * 运营商，
	 */
	public final static String ORG_LEVEL_ROOT = "1";
	
	
	/**
	 * 一级代理商
	 */
	public final static String ORG_LEVEL_ONE = "2";
	
	/**
	 * 机构有子节点
	 */
	public final static String ORG_HAS_NODE = "1";
			
	
	/**
	 * 操作日志类型1，机构类型
	 */
	public final static String TYPE_ORG = "机构";
	
	
	/**
	 * 操作日志类型2，商户类型
	 */
	public static final String TYPE_MERCHANT = "商户";
	
	/**
	 * 操作日志类型3，门店类型
	 */
	public static final String TYPE_STORE = "门店";
	
	/**
	 *  操作日志类型3 店员
	 */
	public static final String TYPE_CLEAK ="店员";
	
	public static final String TYPE_USER = "用户";
	
	
	public static final String SUBTYPE_ADD = "添加";
	
	public static final String SUBTYPE_EDIT = "编辑";
	
	public static final String SUBTYPE_DELETE = "删除";
	
	public static final String SUBTYPE_ENABLE = "启用";
	
	public static final String SUBTYPE_DISABLE = "禁用";
	
	public static final String SUBTYPE_ENABLEMAC = "启用Mac";
	
	public static final String SUBTYPE_DISABLEMAC = "禁用Mac";

	public static final String SUBTYPE_CLEAR = "清除Mac";

	public static final String SUBTYPE_RESETPASSWD = "重置密码";

	public static final String SUBTYPE_AUDIT = "审核";

	public static final String TYPE_ROLE = "角色";

	public static final String TYPE_PAYSET = "支付设置";

	public static final String TYPE_AUDIT = "审核通道";

	public static final String TYPE_BULLET = "公告";

	public static final String SUBTYPE_REVOKE = "撤销";

	public static final String TYPE_ADV = "广告";
	
	
	
	
	public static final String PC = "PC";
	
	public static final String ANDROID = "ANDROID";
	
	public static final String IOS = "IOS";
	
	public static final String POS = "POS";
	
}
