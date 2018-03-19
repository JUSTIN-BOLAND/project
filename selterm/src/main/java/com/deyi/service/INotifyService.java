/**
 *
 */
package com.deyi.service;

import javax.servlet.http.HttpServletRequest;

/**
 * pure
 * @author hejx
 * @2016年4月11日
 */
public interface INotifyService {


	public String notify(String payType,String notifyUrl,Object param) throws Exception;
}
