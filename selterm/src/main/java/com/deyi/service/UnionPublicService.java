/**
 * 
 */
package com.deyi.service;

import org.apache.ibatis.annotations.Param;

import com.deyi.entity.UnionPublic;
/**
 * 
 * pure
 * @author hejx
 * @2017年4月27日
 */
public interface UnionPublicService {

	
	public UnionPublic queryEntityByMechId(String merchId);
	
	    int deleteByPrimaryKey(String id);

	    int insert(UnionPublic record);

	    int insertSelective(UnionPublic record);

	    UnionPublic selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(UnionPublic record);

	    int updateByPrimaryKey(UnionPublic record);

		public UnionPublic selectByMechId(@Param("merchId")String merchId);

		UnionPublic getUnionPublicByMerchId(String string);
	
}
