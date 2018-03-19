/**
 * 
 */
package com.deyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.UnionPublicMapper;
import com.deyi.entity.UnionPublic;
import com.deyi.service.UnionPublicService;

/**
 * 
 * pure
 * @author hejx
 * @2017年4月27日
 */
@Service
public class UnionPublicServiceImpl implements UnionPublicService{

	@Autowired
	private UnionPublicMapper unionPublicMapper;
	
	@Override
	public UnionPublic queryEntityByMechId(String merchId) {
		return unionPublicMapper.selectByMechId(merchId);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return unionPublicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UnionPublic record) {
		return unionPublicMapper.insert(record);
	}

	@Override
	public int insertSelective(UnionPublic record) {
		return unionPublicMapper.insertSelective(record);
	}

	@Override
	public UnionPublic selectByPrimaryKey(String id) {
		return unionPublicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UnionPublic record) {
		return unionPublicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UnionPublic record) {
		return unionPublicMapper.updateByPrimaryKey(record);
	}

	@Override
	public UnionPublic selectByMechId(String merchId) {
		return unionPublicMapper.selectByMechId(merchId);
	}

	@Override
	public UnionPublic getUnionPublicByMerchId(String string) {
		return unionPublicMapper.getUnionPublicByMerchId(string);
	}

}
