package com.deyi.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.SeqDao;
import com.deyi.service.SeqService;

@Service
public class SeqServiceImpl implements SeqService{
	@Autowired
	private SeqDao seqDao;

	@Override
	public String getSeq(String seqName,int length) {
		String seq = seqDao.getSeq(seqName)+"";
		if(seq.length() >= length){
			return seq;
		}
		for (int i = seq.length(); i < length; i++) {
			seq = "0"+seq;
		}
		return seq;
	}

	@Override
	public String getSeq(String seqName) {
		String seq = seqDao.getSeq(seqName)+"";
		return seq;
	}
}
