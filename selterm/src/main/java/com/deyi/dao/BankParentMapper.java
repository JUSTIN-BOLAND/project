package com.deyi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deyi.entity.BankParent;
@Repository
public interface BankParentMapper {
    int insert(BankParent record);

    int insertSelective(BankParent record);
    
    List<BankParent> getBankList();
}