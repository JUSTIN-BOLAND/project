package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.Merchant;
import com.deyi.util.Page;
import com.deyi.vo.bank.BankMerchantVo;

@Repository("merchantDao")
public interface MerchantDao {
	int getCntByName(String name);
	int updateByCode (Merchant record);

	Merchant getMerchantByCode(String merCode);
	Merchant selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insert(Merchant record);

	int insertSelective(Merchant record) throws Exception;

	int insertMerchant(Merchant record) throws Exception;

	int updateByPrimaryKeySelective(Merchant record);

	int updateByPrimaryKey(Merchant record);

	List<Merchant> getMerchants(Merchant merchant);

	Merchant getMerchantByMerAccount(String meraccount);

	List<Merchant> getMerchantList1(Page<Merchant> page);

	List<Merchant> getMerchantList2(Page<Merchant> page);

	List<Merchant> getMerchantList3(@Param("orgIds")String orgIds);

	List<Merchant> getMerchantList4(Merchant merchant);

	List<Merchant> selectByOrg(String orgId);//根据代理商id查商户

	List<Merchant> getMerchantListTwo(Page<Merchant> page);//机构用户二清商户列表

	List<Merchant> getMerchantListTwo2(Page<Merchant> page);//业务员二清商户列表

	List<Merchant> getMerchantListByCreatorIds(@Param("usesrids")String userids);

	List<Merchant> getmerChantBysaleman(Page<Merchant> page);


	int querymerChantCount(Merchant merchant);

	Merchant selectByCode(String code);

	List<String> queryMerchantTwoids(@Param("mertype")String mertype);

	BankMerchantVo bankQueryMerDetail(String merCode);
}
