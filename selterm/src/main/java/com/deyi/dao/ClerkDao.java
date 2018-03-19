package com.deyi.dao;

import com.deyi.entity.Clerk;
import com.deyi.entity.ClerkRel;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("clerkDao")
public interface ClerkDao {
    int deleteByPrimaryKey(String id);

    int insert(Clerk record);

    int insertSelective(Clerk record);

    Clerk selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clerk record);

    int updateByPrimaryKey(Clerk record);
    
    List<Clerk> getClerks(Page<Clerk> page);
    
    Clerk getClerkByAccountName(String accountName);
    
    List<Clerk> selectByPrimaryStore(String storeid);


    List<com.deyi.vo.TypeVo> getBindClerks(String storeId);
    String getClerkByQrCode(String qrCode);
    int updateQrCodeId(String qrCode);
    ClerkRel getClerkRelByCode(String clerkCode);
    int updateByMerId(Clerk record);

}