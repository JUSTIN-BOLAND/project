package com.deyi.dao;

import com.deyi.entity.CityStd;
import com.deyi.entity.DistrictStd;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictStdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DistrictStd record);

    int insertSelective(DistrictStd record);

    DistrictStd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DistrictStd record);

    int updateByPrimaryKey(DistrictStd record);

	List<DistrictStd> getDistrict(String cityId);
    DistrictStd getDistrictByCode(String districtCode);
    List<DistrictStd> getDistrictByCityCode(String cityCode);
}