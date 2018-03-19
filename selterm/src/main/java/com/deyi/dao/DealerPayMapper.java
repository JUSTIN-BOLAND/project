package com.deyi.dao;

import com.deyi.entity.Dealer;
import com.deyi.entity.DealerPay;
import com.deyi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 2018/1/27 0027.
 */
@Repository
public interface DealerPayMapper {

    List<DealerPay> getDealerPays(Page<DealerPay> page);

    DealerPay getDealerPay(String dealerId);

    int insert(DealerPay dealerPay);

    int update(DealerPay dealerPay);

    int delete(String dealerId);

    DealerPay getDealerPayByUserId(String userId);

    @Select("SELECT a.* FROM t_dealer_pay a  JOIN t_device b ON a.dealerId = b.dealerId WHERE b.id=#{deviceId}")
    DealerPay getDealerPayByDeviceId(@Param("deviceId") int deviceId);
}
