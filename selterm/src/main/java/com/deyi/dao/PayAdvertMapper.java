package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.PayAdvert;
import com.deyi.util.Page;
import com.deyi.vo.AdvertCollect;


@Repository
public interface PayAdvertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayAdvert record);

    int insertSelective(PayAdvert record);

    PayAdvert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayAdvert record);

    int updateByPrimaryKey(PayAdvert record);

	List<PayAdvert> queryByPage(Page<PayAdvert> page);
	
	/**
	 * 查询付款页面广告
	 * @param id
	 * @return   
	 * PayAdvert 
	 * @author Enzo
	 * @date 2017年2月22日
	 */
	PayAdvert queryPayAdvByMerId(Integer id);
	
	/**
	 * 查询支付成功的页面
	 * @param id
	 * @return   
	 * PayAdvert 
	 * @author Enzo
	 * @date 2017年2月22日
	 */
	PayAdvert queryPayCompleteAdvByMerId(Integer id);
	
	
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