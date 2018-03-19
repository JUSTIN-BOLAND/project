package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.SysRegionMapper;
import com.deyi.entity.SysRegion;
import com.deyi.service.ISysRegionService;

/**
 * 
* @Description: 地区信息
* @author gongz
* @date 2015年11月2日 
*
 */
@Service
public class SysRegionServiceImpl implements ISysRegionService{
	
	@Autowired
	private SysRegionMapper sysRegionMapper;
	@Override
	public List<SysRegion> querySysRegionByParentId(String parentId) {
		return sysRegionMapper.querySysRegionByParentId(parentId);
	}
	@Override
	public SysRegion querySysRegionByRegionId(String regionId) {
		return sysRegionMapper.querySysRegionByRegionId(regionId);
	}
}
