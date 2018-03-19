package com.deyi.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.UnionPublic;
@Repository
public interface UnionPublicMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionPublic record);

    int insertSelective(UnionPublic record);

    UnionPublic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionPublic record);

    int updateByPrimaryKey(UnionPublic record);

	public UnionPublic selectByMechId(@Param("merchid")String merchId);

	UnionPublic getUnionPublicByMerchId(String string);
	
	
	UnionPublic getUnionPublicByOrgId(String string);
	
}