/**  
* @Title: ISysDic.java
* @Package com.yiqi.service.system
* @Description: 深圳市得壹科技有限公司
*/ 
package com.deyi.service;

import java.io.Serializable;

import com.deyi.entity.Description;


/**
 * @Description: app升级
 * @author gongz
 * @date 2015年10月24日 
 *
 */
public interface IDescriptionService extends Serializable {
	/**
	 *  根据主键查询
	 * @param dicKey
	 * @return
	 */
	public Description querryBydicKey();
}
