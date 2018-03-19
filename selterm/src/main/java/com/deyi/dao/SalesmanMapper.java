package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.Salesman;
import com.deyi.util.Page;
import com.deyi.vo.BusinessInSalemanVo;
import com.deyi.vo.SaleManCollectVo;
@Repository
public interface SalesmanMapper {
    int deleteByPrimaryKey(Long saleid);

    int insert(Salesman record);

    int insertSelective(Salesman record);

    Salesman selectByPrimaryKey(Long saleid);

    Salesman getSalesmanByAccountName(String accountName);

    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
    
    List<Salesman> selectByOrgId(String orgId);

	List<Salesman> getSalesmansByPage(Page<Salesman> page);
	
	List<BusinessInSalemanVo> queryBusinessCollect(Page<BusinessInSalemanVo> page);
	
	/**
	 * 得到用户的商户数，当日的消费流水，昨日的消费流水，当月的消费流水
	 * @param page
	 * @return
	 */
	List<SaleManCollectVo> SaleManCollect(Page<SaleManCollectVo> page);

	SaleManCollectVo SaleManUserCollect(SaleManCollectVo vo);
}