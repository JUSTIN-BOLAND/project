package com.deyi.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.WxPublic;
@Repository
public interface WxPublicMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxPublic record);

    int insertSelective(WxPublic record);

    WxPublic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxPublic record);

    int updateByPrimaryKey(WxPublic record);

	public WxPublic selectByMechId(@Param("merchId")String merchId);

	WxPublic getWxPublicByMerchId(String string);
	
	
	WxPublic getWxPublicByOrgId(String string);
	
}