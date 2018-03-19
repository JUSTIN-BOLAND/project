package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.AliPublicMapper;
import com.deyi.entity.AliPublic;
import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.service.AliPubilcService;
import com.deyi.util.Page;
import com.deyi.vo.AliWxPublicVo;

@Service
public class AliPublicServiceImpl implements AliPubilcService {
	@Autowired
	private AliPublicMapper aliPublicMapper;
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return aliPublicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AliPublicWithBLOBs record) {
		return aliPublicMapper.insert(record);
	}

	@Override
	public int insertSelective(AliPublicWithBLOBs record) {
		return aliPublicMapper.insertSelective(record);
	}

	@Override
	public AliPublicWithBLOBs selectByPrimaryKey(String id) {
		return aliPublicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AliPublicWithBLOBs record) {
		return aliPublicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(AliPublicWithBLOBs record) {
		return aliPublicMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(AliPublic record) {
		return aliPublicMapper.updateByPrimaryKey(record);
	}

	@Override
	public AliPublicWithBLOBs selectByMechId(String mechId) {
		return aliPublicMapper.selectByMechId(mechId);
	}

	@Override
	public AliPublic selectByMechantId(String mechId) {
		return aliPublicMapper.selectByMechantId(mechId);
	}

	@Override
	public List<AliWxPublicVo> getAliWxList(Page<AliWxPublicVo> page) {
		return aliPublicMapper.getAliWxList(page);
	}

}
