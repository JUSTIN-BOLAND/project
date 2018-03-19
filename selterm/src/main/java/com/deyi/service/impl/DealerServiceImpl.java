package com.deyi.service.impl;

import com.deyi.dao.DealerMapper;
import com.deyi.entity.Dealer;
import com.deyi.service.DealerService;
import com.deyi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 2018/1/27 0027.
 */
@Service
public class DealerServiceImpl implements DealerService {
    @Autowired
    private DealerMapper dealerDao;
    @Override
    public List<Dealer> getDealers(Page<Dealer> page) {
        return dealerDao.getDealers(page);
    }

    @Override
    public Dealer getDealer(Integer id) {
        return dealerDao.getDealer(id);
    }

    @Override
    public int insert(Dealer dealer) {
        return dealerDao.insert(dealer);
    }

    @Override
    public int update(Dealer dealer) {
        return dealerDao.update(dealer);
    }

    @Override
    public int delete(Integer id) {
        return dealerDao.delete(id);
    }

    @Override
    public Dealer getDealerByCode(Dealer dealer) {
        return dealerDao.getDealerByCode(dealer);
    }
}
