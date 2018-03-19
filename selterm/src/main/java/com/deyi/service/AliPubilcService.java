package com.deyi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.deyi.entity.AliPublic;
import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.util.Page;
import com.deyi.vo.AliWxPublicVo;

public interface AliPubilcService {
	 int deleteByPrimaryKey(String id);

	    int insert(AliPublicWithBLOBs record);

	    int insertSelective(AliPublicWithBLOBs record);

	    AliPublicWithBLOBs selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(AliPublicWithBLOBs record);

	    int updateByPrimaryKeyWithBLOBs(AliPublicWithBLOBs record);

	    int updateByPrimaryKey(AliPublic record);

		AliPublicWithBLOBs selectByMechId(@Param("mechid")String mechId);
		
		AliPublic selectByMechantId(@Param("mechid")String mechId);
		
		List<AliWxPublicVo> getAliWxList(Page<AliWxPublicVo> page);
		
		
}
