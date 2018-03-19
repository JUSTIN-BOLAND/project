package com.deyi.dao;

import org.springframework.stereotype.Repository;

import com.deyi.entity.OthMerchant;

@Repository
public interface OthMerchantMapper {
    int deleteByPrimaryKey(Integer merid);

    int insert(OthMerchant record);

    int insertSelective(OthMerchant record);

    OthMerchant selectByPrimaryKey(Integer merid);

    int updateByPrimaryKeySelective(OthMerchant record);

    int updateByPrimaryKey(OthMerchant record);
}