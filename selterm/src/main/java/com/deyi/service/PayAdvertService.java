package com.deyi.service;

import java.util.List;

import com.deyi.entity.PayAdvert;
import com.deyi.util.Page;
import com.deyi.vo.AdvertCollect;

public interface PayAdvertService {

	List<PayAdvert> queryByPage(Page<PayAdvert> page);

	PayAdvert queryByid(Integer id);

	void save(PayAdvert entity);

	void update(PayAdvert entity);

	void deleteAdvById(String id);

	PayAdvert queryPaySussessByMerid(Integer merId);
	
	
	PayAdvert queryPayAdvByMerId(Integer merId);
	
	
	int clickTheAdv(Integer id);
	

	/**
	 * 广告发布统计
	 * @param page
	 * @return   
	 * List<AdvertCollect> 
	 * @author Enzo
	 * @date 2017年2月24日
	 */
	List<AdvertCollect> queryAdvertCollect(Page<AdvertCollect> page);
}
