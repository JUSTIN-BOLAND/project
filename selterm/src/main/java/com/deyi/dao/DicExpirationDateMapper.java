package com.deyi.dao;

import com.deyi.entity.CityStd;
import com.deyi.entity.DealerPay;
import com.deyi.entity.DicExpirationDate;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicExpirationDateMapper {
    List<DicExpirationDate> getDicExpirationDates(DicExpirationDate dicExpirationDate);

    DealerPay getDicExpirationDate(Integer dealerId);

    int insert(DicExpirationDate dicExpirationDate);

    int update(DicExpirationDate dicExpirationDate);

    int delete(Integer code);
}