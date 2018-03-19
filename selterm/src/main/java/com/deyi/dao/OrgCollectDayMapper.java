package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.OrgCollectDay;
import com.deyi.util.Page;
import com.deyi.vo.OrgCollectDayVo;


@Repository
public interface OrgCollectDayMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(OrgCollectDay record);

	int insertSelective(OrgCollectDay record);

	OrgCollectDay selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(OrgCollectDay record);

	int updateByPrimaryKey(OrgCollectDay record);
	
	
	List<OrgCollectDay> queryYesterdayData(int i);

	List<OrgCollectDayVo> queryOrgCollectListBypage(Page<OrgCollectDayVo> page);

	List<OrgCollectDayVo> getExcelStatisticList(OrgCollectDayVo orgvo);

	List<OrgCollectDayVo> getExcelOrgStatisticList(OrgCollectDayVo orgvo);
	
	
	List<OrgCollectDayVo> queryorgbymonth(Page<OrgCollectDayVo> page);
	
	
	int queryYesterdayCount(@Param("orgid")String orgid,@Param("day") String day);
	
	/**
	 * 查询条件的汇总
	 * @param vo
	 * @return
	 */
	OrgCollectDayVo queryOrgCollectSum(OrgCollectDayVo vo);
	OrgCollectDayVo queryorgbymonthsum(OrgCollectDayVo vo);
}