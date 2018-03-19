package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.StoreCollectDay;
import com.deyi.util.Page;
import com.deyi.vo.StoreCollectDayVo;


@Repository
public interface StoreCollectDayMapper {
    int insert(StoreCollectDay record);

    int insertSelective(StoreCollectDay record);
    
    /**
     * 查询昨天订单  门店的统计数据
     * @return
     */
    List<StoreCollectDay> queryYesterdayData(int i);
    
    int queryYesterdayCount(@Param("storeid")Long storeid,@Param("day")Long day);

	List<StoreCollectDayVo> querystoreConllectlist(Page<StoreCollectDayVo> page);

	List<StoreCollectDayVo> getExcelStoreStatisticList(StoreCollectDayVo store);

	List<StoreCollectDayVo> querylistBymonth(Page<StoreCollectDayVo> page);
	
	
	StoreCollectDayVo querystoresum(StoreCollectDayVo vo);
	StoreCollectDayVo querylistBymonthsum(StoreCollectDayVo vo);
}