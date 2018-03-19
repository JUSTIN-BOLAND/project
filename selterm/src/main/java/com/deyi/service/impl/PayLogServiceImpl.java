package com.deyi.service.impl;

import com.deyi.dao.AliPublicMapper;
import com.deyi.dao.PayLogMapper;
import com.deyi.entity.AliPublic;
import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.entity.PayLog;
import com.deyi.service.AliPubilcService;
import com.deyi.service.PayLogService;
import com.deyi.util.Page;
import com.deyi.vo.AliWxPublicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayLogServiceImpl implements PayLogService {
	@Autowired
	private PayLogMapper payLogMapper;


	@Override
	public int deleteByPrimaryKey(Integer id) {
		return payLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PayLog record) {
		return payLogMapper.insert(record);
	}

	@Override
	public int insertSelective(PayLog record) {
		return payLogMapper.insertSelective(record);
	}

	@Override
	public PayLog selectByPrimaryKey(Integer id) {
		return payLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PayLog record) {
		return payLogMapper.updateByPrimaryKeySelective(record);
	}



	@Override
	public int updateByPrimaryKey(PayLog record) {
		return payLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PayLog> getPayLog(Integer workType, Integer subWorkType){
       return payLogMapper.getPayLog(workType,subWorkType);
	}

}
