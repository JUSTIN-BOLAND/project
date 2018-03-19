package com.deyi.service.impl;

import java.util.List;

import com.deyi.dao.ManageCatMapper;
import com.deyi.entity.ManagerCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.IndustryTypeMapper;
import com.deyi.entity.IndustryType;
import com.deyi.service.IndustryTypeService;
import com.deyi.vo.TypeVo;

@Service
public class IndustryTypeServiceImpl implements IndustryTypeService{

	@Autowired
	private ManageCatMapper manageCatMapper;

	@Autowired
	private IndustryTypeMapper industryTypeMapper;


	@Override
	public List<com.deyi.vo.TypeVo> getManagerCat(Integer parentId) {
		return manageCatMapper.getManagerCat(parentId);
	}

	@Override
	public ManagerCat getManagerCatById(Integer id) {
		return manageCatMapper.getManagerCatById(id);
	}

	@Override
	public ManagerCat getManagerCatByCode(String catCode) {
		return manageCatMapper.getManagerCatByCode(catCode);
	}

	@Override
	public List<TypeVo> getonetype() {
		return industryTypeMapper.oneindustrytype();
	}


	@Override
	public List<TypeVo> twoindustrytype(String id) {
		return industryTypeMapper.twoindustrytype(id);
	}


	@Override
	public List<TypeVo> threeindustrytype(String id) {
		return industryTypeMapper.threeindustrytype(id);
	}


	@Override
	public IndustryType getbyid(String business_code) {
		return industryTypeMapper.getbyid(business_code);
	}


	@Override
	public IndustryType querybytypecode(String business_code) {
		return industryTypeMapper.querybytypecode(business_code);
	}

}
