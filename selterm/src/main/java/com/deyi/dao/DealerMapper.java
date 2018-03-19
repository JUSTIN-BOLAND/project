package com.deyi.dao;

import com.deyi.entity.Dealer;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DealerMapper {
    List<Dealer> getDealers(Page<Dealer> page);
    Dealer getDealer(Integer id);
    int insert(Dealer dealer);
    int update(Dealer dealer);
    int delete(Integer id);
    Dealer getDealerByCode(Dealer dealer);


}
