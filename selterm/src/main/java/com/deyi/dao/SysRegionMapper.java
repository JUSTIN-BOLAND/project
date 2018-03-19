package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.SysRegion;
@Repository
public interface SysRegionMapper {
    int deleteByPrimaryKey(Double regionId);

    int insert(SysRegion record);

    int insertSelective(SysRegion record);

    SysRegion selectByPrimaryKey(Double regionId);

    int updateByPrimaryKeySelective(SysRegion record);

    int updateByPrimaryKey(SysRegion record);
    /**
     * 根据parentId查询列表
     * @param parentId
     * @return
     */
    public List<SysRegion> querySysRegionByParentId(String parentId);
    /**
     * 根据regionId查询列表
     * @param regionId
     * @return
     */
    public SysRegion querySysRegionByRegionId(String regionId);
}