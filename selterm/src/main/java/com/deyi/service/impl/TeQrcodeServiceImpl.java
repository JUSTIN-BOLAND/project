package com.deyi.service.impl;

import com.deyi.dao.teQrcodeMapper;
import com.deyi.entity.TeQrcode;
import com.deyi.service.TeQrcodeService;
import com.deyi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeQrcodeServiceImpl implements TeQrcodeService {

	@Autowired
	private teQrcodeMapper teQrcodeDao;
	
	@Override
	public void deleteByPrimaryKey(Long id) {
		 teQrcodeDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TeQrcode record) {
		return teQrcodeDao.insert(record);
	}

	@Override
	public int insertSelective(TeQrcode record) {
		return teQrcodeDao.insertSelective(record);
	}
	
	@Override
	public int insertSelectiveBatch(List<TeQrcode> records) {
		return teQrcodeDao.insertSelectiveBatch(records);
	}

	@Override
	public TeQrcode selectByPrimaryKey(Long id) {
		return teQrcodeDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TeQrcode record) {
		return teQrcodeDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TeQrcode record) {
		return teQrcodeDao.updateByPrimaryKey(record);
	}

	@Override
	public List<TeQrcode> pageTeQrcode(Page<TeQrcode> teQrcode) {
		return teQrcodeDao.pageTeQrcode(teQrcode);
	}

	@Override
	public TeQrcode getQrByCode(String code){
		return teQrcodeDao.getQrByCode(code);
	}

}
