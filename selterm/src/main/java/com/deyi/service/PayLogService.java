package com.deyi.service;

import com.deyi.entity.AliPublic;
import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.entity.PayLog;
import com.deyi.util.Page;
import com.deyi.vo.AliWxPublicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayLogService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(PayLog record);

	    int insertSelective(PayLog record);

	    PayLog selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(PayLog record);



	    int updateByPrimaryKey(PayLog record);



		List<PayLog> getPayLog(Integer workType,Integer subWorkType);


}
