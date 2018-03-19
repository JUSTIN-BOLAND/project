package com.deyi.service;

import java.util.List;

import com.deyi.entity.Bank;
import com.deyi.entity.BankParent;

public interface IBankService {

	List<BankParent> getBankList();


	List<Bank> getBankByCity(String bankid, String cityid);


	Bank getByid(String id);


	Bank queryBybankno(String bank_no);

	Bank getBankByCode(String unionBankNo);

}
