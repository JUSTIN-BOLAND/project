package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.IndustryType;
@Repository
public interface IndustryTypeMapper {
    int insert(IndustryType record);

    int insertSelective(IndustryType record);
    
    List<com.deyi.vo.TypeVo> oneindustrytype();
    
    List<com.deyi.vo.TypeVo> twoindustrytype(String id);
    
    List<com.deyi.vo.TypeVo> threeindustrytype(String id);

	IndustryType getbyid(String id);

	IndustryType querybytypecode(String typecode);
}