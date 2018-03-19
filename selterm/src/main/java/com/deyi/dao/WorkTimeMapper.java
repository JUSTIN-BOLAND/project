package com.deyi.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.WorkTime;

@Repository
public interface WorkTimeMapper {
    int deleteByPrimaryKey(String id);
    int insertSelective(WorkTime record);

    WorkTime selectByPrimaryKey(String id);

    WorkTime getWorkTimeByLoginname(String loginname);
    
    WorkTime getWorkTimeByStoreId(@Param("storeId")String storeId);
    
    int update(WorkTime record);
}