package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.Disparam;
@Repository
public interface DisparamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Disparam record);

    int insertSelective(Disparam record);

    Disparam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Disparam record);

    int updateByPrimaryKey(Disparam record);

	List<Disparam> getDisparams(String storeId);
}