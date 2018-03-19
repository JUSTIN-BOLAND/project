package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.Bank;
@Repository
public interface BankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    List<Bank> getBankByCity(@Param("bankid")String bankid,@Param("cityid")String cityid);

	Bank queryBybankno(@Param("bankno")String bank_no);
    Bank getBankByCode(String unionBankNo);
}