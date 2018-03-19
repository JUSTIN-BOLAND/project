package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.deyi.entity.Mercollectday;
import com.deyi.util.Page;
import com.deyi.vo.MercollectdayVo;

@Repository("mercollectdayDao")
public interface MercollectdayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Mercollectday record);

    int insertSelective(Mercollectday record);

    Mercollectday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mercollectday record);

    int updateByPrimaryKey(Mercollectday record);
    /**
     * 查询昨天的数据条数
     * @return
     */
    int queryYesterdayDataCount(@Param(value="mer_id")Integer mer_id,@Param(value="day")Integer day);

	List<MercollectdayVo> getMerchantPaymentStatistic(Page<MercollectdayVo> page);

	List<Mercollectday> getExcelMerStatisticList(MercollectdayVo mercollectdayVo);
	/**
	 *根据商户id查询
	 */
	List<Mercollectday> getMerCollertdays(@Param("merId")String merId,@Param("querystarttime")String querystarttime, @Param("queryendtime")String queryendtime);
	
	
	List<Mercollectday> getMercollertCondtion(MercollectdayVo vo);
	
	
	List<MercollectdayVo> querymerbymonth(Page<MercollectdayVo> page);
	
	
	
	List<Mercollectday> queryCollectDay(int i);
	
	
	/**
	 * 查询商户每日的流水
	 * @param page
	 * @return
	 */
	List<MercollectdayVo> querymerserialbyday(Page<MercollectdayVo> page);
	
	
	/**
	 * 统计所时间的所有统计
	 * @param vo
	 * @return
	 */
	MercollectdayVo querymerchantsum(MercollectdayVo vo);
	
	
	/**
	 * 按月统计所有的统计
	 * 
	 */
	MercollectdayVo querymerbymonthsum(MercollectdayVo vo);

	@Select("select * from sta_mer_collectday where  endtime >= #{starttime} and endtime <= #{endtime}")
	List<Mercollectday> queryYesterData(@Param("starttime")String starttime,@Param("endtime")String endtime);
	
}