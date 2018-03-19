package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.ProvinceStd;

@Repository
public interface ProvinceStdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvinceStd record);

    int insertSelective(ProvinceStd record);

    ProvinceStd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvinceStd record);

    int updateByPrimaryKey(ProvinceStd record);

	List<ProvinceStd> getprovicelist();
    ProvinceStd getProvinceByCode(String provinceCode);
}