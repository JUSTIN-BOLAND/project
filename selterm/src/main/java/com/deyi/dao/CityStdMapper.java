package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.CityStd;

@Repository
public interface CityStdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CityStd record);

    int insertSelective(CityStd record);

    CityStd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CityStd record);

    int updateByPrimaryKey(CityStd record);

	List<CityStd> getCity(String provinceid);
    CityStd getCityByCode(String cityCode );
}