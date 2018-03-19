package com.deyi.service;

import com.deyi.entity.Dealer;
import com.deyi.util.Page;

import java.util.List;

public interface DealerService {

	List<Dealer> getDealers(Page<Dealer> page);

	Dealer getDealer(Integer id);

	int insert(Dealer dealer);

	int update(Dealer dealer);

	int delete(Integer id);


	Dealer getDealerByCode(Dealer dealer);

}
