package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.Merlogo;
import com.deyi.util.Page;


@Repository
public interface MerlogoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Merlogo record);

    int insertSelective(Merlogo record);

    Merlogo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Merlogo record);

    int updateByPrimaryKey(Merlogo record);

	List<Merlogo> queryByPage(Page<Merlogo> page);

	Merlogo queryByMerId(Integer id);
	
	Merlogo queryLogoByMerId(Integer id);
}