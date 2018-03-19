/**
 * 
 */
package com.deyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.WxPublicMapper;
import com.deyi.entity.WxPublic;
import com.deyi.service.WxPublicService;

/**
 * pure
 * @author hejx
 * @2016年3月19日
 */
@Service
public class WxPublicServiceImpl implements WxPublicService{

	@Autowired
	private WxPublicMapper wxPublicMapper;
	
	@Override
	public WxPublic queryEntityByMechId(String merchId) {
		return wxPublicMapper.selectByMechId(merchId);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return wxPublicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxPublic record) {
		return wxPublicMapper.insert(record);
	}

	@Override
	public int insertSelective(WxPublic record) {
		return wxPublicMapper.insertSelective(record);
	}

	@Override
	public WxPublic selectByPrimaryKey(String id) {
		return wxPublicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxPublic record) {
		return wxPublicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxPublic record) {
		return wxPublicMapper.updateByPrimaryKey(record);
	}

	@Override
	public WxPublic selectByMechId(String merchId) {
		return wxPublicMapper.selectByMechId(merchId);
	}

	@Override
	public WxPublic getWxPublicByMerchId(String string) {
		return wxPublicMapper.getWxPublicByMerchId(string);
	}

}
