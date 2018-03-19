package com.deyi.dao;

import org.springframework.stereotype.Repository;

import com.deyi.entity.Facilor;
@Repository
public interface FacilorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Facilor record);

    int insertSelective(Facilor record);

    Facilor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Facilor record);

    int updateByPrimaryKey(Facilor record);
}