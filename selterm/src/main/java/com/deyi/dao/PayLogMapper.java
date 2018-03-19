package com.deyi.dao;

import com.deyi.entity.Mercollectday;
import com.deyi.entity.PayLog;
import com.deyi.util.Page;
import com.deyi.vo.MercollectdayVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);



	List<PayLog> getPayLog(Integer workType, Integer subWorkType);




}