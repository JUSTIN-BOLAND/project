/**  
* @Title: ISysDic.java
* @Package com.yiqi.service.system
* @Description: 深圳市得壹科技有限公司
*/ 
package com.deyi.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.DescriptionMapper;
import com.deyi.entity.Description;
import com.deyi.service.IDescriptionService;

/**
 * @Description: app升级
 * @author gong
 * @date 2015年10月24日 
 *
 */
@Service
public class DescriptionServiceImpl implements IDescriptionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DescriptionMapper descriptionMapper;
	@Override
	public Description querryBydicKey() {
		return descriptionMapper.selectByPrimaryId();
	}

}
