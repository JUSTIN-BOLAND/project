package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.deyi.entity.AliPublic;
import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.util.Page;
import com.deyi.vo.AliWxPublicVo;
@Repository
public interface AliPublicMapper {
    int deleteByPrimaryKey(String id);

    int insert(AliPublicWithBLOBs record);

    int insertSelective(AliPublicWithBLOBs record);

    AliPublicWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AliPublicWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AliPublicWithBLOBs record);

    int updateByPrimaryKey(AliPublic record);

	AliPublicWithBLOBs selectByMechId(@Param("mechid")String mechId);
	
	AliPublic selectByMechantId(@Param("mechid")String mechId);
	
	List<AliWxPublicVo> getAliWxList(Page<AliWxPublicVo> page);//
	
	int updateByPrimaryKeyWith(AliPublic record);

	AliPublicWithBLOBs queryorgAlipublic(String orgid);
}