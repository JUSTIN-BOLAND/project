package com.deyi.service;

import java.util.List;

import com.deyi.entity.CityStd;
import com.deyi.entity.DistrictStd;
import com.deyi.entity.ProvinceStd;

public interface IProvinceService {

	List<ProvinceStd> getprovicelist();

	List<CityStd> getCity(String proviceid);

	ProvinceStd getByid(String merchant_province);

	CityStd getcitybyid(String merchant_city);

	List<DistrictStd> getDistrict(String cityid);
	public DistrictStd getDistrictById(String id);


	ProvinceStd getProvinceByCode(String provinceCode);
	CityStd getCityByCode(String cityCode );
	DistrictStd getDistrictByCode(String districtCode);
	List<DistrictStd> getDistrictByCityCode(String cityCode);
}
