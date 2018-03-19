package com.deyi.dao;

import com.deyi.entity.Org;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orgDao")
public interface OrgDao {
    int deleteByPrimaryKey(Long id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
    
    Org selectByOrgAccount(String orgAccount);
    
    Org selectByOrgSalesman(String accountName);

    List<Org> getChildOrgs(String parentid);//获取运营商下的所有代理商

	List<Org> getOrgsByPage(Page<Org> page);
	
	Org selectByOrgName(String name);//根据name判断有没有数据
	
	List<Org> getExportOrg();//导出数据
    Org getOrgWxApp(String storeCode);
	
}