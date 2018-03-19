package com.deyi.service.impl;

import java.util.List;

import com.deyi.dao.DistrictStdMapper;
import com.deyi.entity.DistrictStd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.CityStdMapper;
import com.deyi.dao.ProvinceStdMapper;
import com.deyi.entity.CityStd;
import com.deyi.entity.ProvinceStd;
import com.deyi.service.IProvinceService;

@Service
public class ProvinceServiceImpl implements IProvinceService {

	@Autowired
	private ProvinceStdMapper provinceStdMapper;

	@Autowired
	private CityStdMapper cityStdMapper;

	@Autowired
	private DistrictStdMapper districtStdMapper;

	@Override
	public List<ProvinceStd> getprovicelist() {
		return provinceStdMapper.getprovicelist();
	}

	@Override
	public List<CityStd> getCity(String proviceid) {
		return cityStdMapper.getCity(proviceid);
	}

	@Override
	public ProvinceStd getByid(String merchant_province) {
		return provinceStdMapper.selectByPrimaryKey(Integer.parseInt(merchant_province));
	}

	@Override
	public CityStd getcitybyid(String merchant_city) {
		return cityStdMapper.selectByPrimaryKey(Integer.parseInt(merchant_city));
	}

	@Override
	public List<DistrictStd> getDistrict(String cityid) {
		return this.districtStdMapper.getDistrict(cityid);
	}

	@Override
	public DistrictStd getDistrictById(String id) {
		return this.districtStdMapper.selectByPrimaryKey(Integer.parseInt(id));
	}
	@Override
	public List<DistrictStd> getDistrictByCityCode(String cityCode){
		return districtStdMapper.getDistrictByCityCode(cityCode);
	}

	@Override
	public ProvinceStd getProvinceByCode(String provinceCode){
		return provinceStdMapper.getProvinceByCode(provinceCode);
	}
	@Override
	public CityStd  getCityByCode(String cityCode ){
		return cityStdMapper.getCityByCode(cityCode);
	}
	@Override
	public DistrictStd getDistrictByCode(String districtCode){
		return districtStdMapper.getDistrictByCode(districtCode);
	}


}
