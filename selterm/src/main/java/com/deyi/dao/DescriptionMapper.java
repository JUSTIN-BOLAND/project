package com.deyi.dao;

import org.springframework.stereotype.Repository;

import com.deyi.entity.Description;

@Repository
public interface DescriptionMapper {
    int deleteByPrimaryKey(Integer versioncode);

    int insert(Description record);

    int insertSelective(Description record);

    Description selectByPrimaryKey(Integer versioncode);
    Description selectByPrimaryId();

    int updateByPrimaryKeySelective(Description record);

    int updateByPrimaryKey(Description record);
}