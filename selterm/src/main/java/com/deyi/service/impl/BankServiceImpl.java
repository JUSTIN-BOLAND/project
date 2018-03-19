package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.BankMapper;
import com.deyi.dao.BankParentMapper;
import com.deyi.entity.Bank;
import com.deyi.entity.BankParent;
import com.deyi.service.IBankService;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private BankParentMapper bankParentMapper;


	@Override
	public List<BankParent> getBankList() {
		return bankParentMapper.getBankList();
	}

	@Override
	public List<Bank> getBankByCity(String bankid, String cityid) {
		return bankMapper.getBankByCity(bankid, cityid);
	}

	@Override
	public Bank getByid(String id) {
		return bankMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public Bank queryBybankno(String bank_no) {
		return bankMapper.queryBybankno(bank_no);
	}

	public Bank getBankByCode(String unionBankNo){
		return bankMapper.getBankByCode(unionBankNo);
	}

}
