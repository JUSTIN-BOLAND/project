package com.deyi.service;

import com.deyi.entity.TeQrcode;
import com.deyi.util.Page;

import java.util.List;

public interface TeQrcodeService {

	
	void deleteByPrimaryKey(Long id);

    int insert(TeQrcode record);

    int insertSelective(TeQrcode record);

    TeQrcode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeQrcode record);

    int updateByPrimaryKey(TeQrcode record);
    
    List<TeQrcode> pageTeQrcode(Page<TeQrcode> teQrcode);//分页查询

	int insertSelectiveBatch(List<TeQrcode> records);

    TeQrcode getQrByCode(String code);
}
