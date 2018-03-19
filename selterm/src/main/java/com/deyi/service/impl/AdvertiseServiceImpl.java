package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.AdvertiseMapper;
import com.deyi.entity.Advertise;
import com.deyi.service.AdvertiseService;
import com.deyi.util.Page;

@Service
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	private AdvertiseMapper advertiseDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return advertiseDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Advertise record) {
		return advertiseDao.insert(record);
	}

	@Override
	public int insertSelective(Advertise record) {
		return advertiseDao.insertSelective(record);
	}

	@Override
	public Advertise selectByPrimaryKey(Long id) {
		return advertiseDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Advertise record) {
		return advertiseDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Advertise record) {
		return advertiseDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Advertise> getAdvertisesList(Page<Advertise> page) {
		return advertiseDao.getAdvertisesList(page);
	}

	@Override
	public List<Advertise> getAdvertisesByOrg(String orgId) {
		return advertiseDao.getAdvertisesByOrg(orgId);
	}

	@Override
	public List<Advertise> getAdvByOrg(String orgId) {
		return advertiseDao.getAdvByOrg(orgId);
	}


}
