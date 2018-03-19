package com.deyi.dao;

import com.deyi.entity.TeQrcode;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface teQrcodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeQrcode record);

    int insertSelective(TeQrcode record);

    TeQrcode selectByPrimaryKey(Long id);
    
    List<TeQrcode> pageTeQrcode(Page<TeQrcode> teQrcode);//分页查询

    int updateByPrimaryKeySelective(TeQrcode record);

    int updateByPrimaryKey(TeQrcode record);

	int insertSelectiveBatch(List<TeQrcode> records);
    TeQrcode getQrByCode(String code);
    
    
}