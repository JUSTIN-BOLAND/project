package com.deyi.service;

import java.util.List;

import com.deyi.entity.SysRegion;


/**
 * 
* @Description: 地区信息
* @author gongz
* @date 2015年11月2日 
*
 */
public interface ISysRegionService{
	/**
	 * 根据parent_id地区列表
	 * @param userId
	 * @return
	 */
	public List<SysRegion> querySysRegionByParentId(String parentId);
	/**
	 * 根据省市区id查询省市区名称
	 * @param regionId
	 * @return
	 */
	public SysRegion querySysRegionByRegionId(String regionId);
}
