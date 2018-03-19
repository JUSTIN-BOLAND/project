package com.deyi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.PayAdvertMapper;
import com.deyi.entity.PayAdvert;
import com.deyi.service.PayAdvertService;
import com.deyi.util.Page;
import com.deyi.vo.AdvertCollect;

@Service
public class PayAdvertServiceImpl  implements PayAdvertService{

	
	@Autowired
	private PayAdvertMapper payAdvertMapper;
	
	
	
	
	@Override
	public List<PayAdvert> queryByPage(Page<PayAdvert> page) {
		return payAdvertMapper.queryByPage(page);
	}

	@Override
	public PayAdvert queryByid(Integer id) {
		return payAdvertMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(PayAdvert entity) {
		entity.setCreatetime(new Date());
		entity.setAuditstatus("0");
		entity.setDeletestatus("0");
		entity.setClicknum(0);
		entity.setPushstatus("0");
		payAdvertMapper.insertSelective(entity);
		
	}

	@Override
	public void update(PayAdvert entity) {
		payAdvertMapper.updateByPrimaryKeySelective(entity);
		
	}

	@Override
	public void deleteAdvById(String id) {
		PayAdvert payAdvert = new PayAdvert();
		payAdvert.setId(Integer.parseInt(id));
		payAdvert.setDeletestatus("1");
		payAdvertMapper.updateByPrimaryKeySelective(payAdvert);
		
	}

	@Override
	public PayAdvert queryPaySussessByMerid(Integer merId) {
		return payAdvertMapper.queryPayCompleteAdvByMerId(merId);
	}

	@Override
	public PayAdvert queryPayAdvByMerId(Integer merId) {
		return payAdvertMapper.queryPayAdvByMerId(merId);
	}

	@Override
	public int clickTheAdv(Integer id) {
		return payAdvertMapper.clickTheAdv(id);
	}

	@Override
	public List<AdvertCollect> queryAdvertCollect(Page<AdvertCollect> page) {
		return payAdvertMapper.queryAdvertCollect(page);
	}

}
