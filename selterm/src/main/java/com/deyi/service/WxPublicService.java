/**
 * 
 */
package com.deyi.service;

import org.apache.ibatis.annotations.Param;

import com.deyi.entity.WxPublic;

/**
 * pure
 * @author hejx
 * @2016年3月19日
 */
public interface WxPublicService {

	
	public WxPublic queryEntityByMechId(String merchId);
	
	    int deleteByPrimaryKey(String id);

	    int insert(WxPublic record);

	    int insertSelective(WxPublic record);

	    WxPublic selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(WxPublic record);

	    int updateByPrimaryKey(WxPublic record);

		public WxPublic selectByMechId(@Param("merchId")String merchId);

		WxPublic getWxPublicByMerchId(String string);
	
}
