package com.deyi.dao;

import org.springframework.stereotype.Repository;

@Repository("seqDao")
public interface SeqDao {
	Integer getSeq(String seqName);
}
