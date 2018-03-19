package com.deyi.dao;

import com.deyi.entity.Recharge;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RechargeMapper {
    List<Recharge> getPageRecharges(Page<Recharge> page);
    List<Recharge> getRecharges(Recharge recharge);
    Recharge getRechargeByCode(Recharge recharge);
    Recharge getRecharge(Integer id);
    int insert(Recharge recharge);
    int update(Recharge recharge);
    int delete(Integer id);
}
