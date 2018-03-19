package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.BulletinMapper;
import com.deyi.entity.Bulletin;
import com.deyi.service.BulletinService;
import com.deyi.util.Page;

@Service
public class BulletinServiceImpl implements BulletinService {

	@Autowired
	private BulletinMapper bulletinDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return bulletinDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Bulletin record) {
		return bulletinDao.insert(record);
	}

	@Override
	public int insertSelective(Bulletin record) {
		return bulletinDao.insertSelective(record);
	}

	@Override
	public Bulletin selectByPrimaryKey(Long id) {
		return bulletinDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Bulletin record) {
		return bulletinDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Bulletin record) {
		return bulletinDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Bulletin> getBulletinList(Page<Bulletin> page) {
		return bulletinDao.getBulletinList(page);
	}

	@Override
	public List<Bulletin> getBulletinByOrg(String orgId) {
		return bulletinDao.getBulletinByOrg(orgId);
	}

	@Override
	public List<Bulletin> getBullByOrg(String orgId) {
		return bulletinDao.getBullByOrg(orgId);
	}

	@Override
	public List<Bulletin> getBullList(Page<Bulletin> page) {
		return bulletinDao.getBullList(page);
	}

}
