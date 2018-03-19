package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.deyi.entity.Payanother;
import com.deyi.util.Page;
@Repository("payanotherMapper")
public interface PayanotherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Payanother record);

    int insertSelective(Payanother record);

    Payanother selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Payanother record);

    int updateByPrimaryKey(Payanother record);

	List<Payanother> queryByPage(Page<Payanother> page);
	
	@Update("UPDATE sta_payanother SET `status` = #{status},reason =#{reason},whlseqno = #{whlseqno} WHERE bathno = #{batchno}")
	Integer updateReason(@Param("batchno") String batchno, @Param("status") Integer status, @Param("reason") String reason, @Param("whlseqno") String whlseqno);
	
	
	@Update("UPDATE sta_payanother SET `status` = #{status},reason =#{reason} WHERE seqno = #{batchno}")
	Integer updateReasonBySeq(@Param("batchno") String batchno, @Param("status") Integer status, @Param("reason") String reason);

	
	List<Payanother> querytoday();
}