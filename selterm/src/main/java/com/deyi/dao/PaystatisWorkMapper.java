package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.PaystatisWork;
import com.deyi.util.Page;

@Repository
public interface PaystatisWorkMapper {
    int insertSelective(PaystatisWork record);

    PaystatisWork selectByPrimaryKey(Long id);

	List<PaystatisWork> getPaystatisWorks(Page<PaystatisWork> page);
	
	
	PaystatisWork getPaystatisWorksSum(PaystatisWork vo);
}