package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.ActionLog;
import com.deyi.util.Page;
import com.deyi.vo.ActionLogVo;

@Repository
public interface ActionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActionLog record);

    int insertSelective(ActionLog record);

    ActionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActionLog record);

    int updateByPrimaryKey(ActionLog record);

	List<ActionLogVo> selectByPage(Page<ActionLogVo> page);
}