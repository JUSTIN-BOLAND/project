package com.deyi.service;

import com.deyi.entity.Org;
import com.deyi.util.Page;

import java.util.List;

public interface OrgService {
	Org getOrgById(String id);
	/**
	 * 根据机构用户查询其对应的机构
	 * @param orgAccount
	 * @return
	 */
	Org getOrgByorgAccount(String orgAccount);
	
	/**
	 * 获取业务员所在的机构
	 * @param loginName
	 * @return
	 */
	Org selectByOrgSalesman(String loginName);
	
	
	/**
	 * 获取机构用户有权限的机构
	 */
	List<Org> getOrgsByOrgAccount(String loginName);
	
	/**
	 * 获取业务员用户有权限的机构（创建的以及创建的机构的子机构，当前所在机构看不了）
	 */
	List<Org> getOrgsByOrgSalesman(String loginName);
	
	/**
	 * 获取业务员用户有权限的机构和自身的机构
	 */
	List<Org> getOrgsByOrgSalesmanAndCurrentOrg(String loginName);
	
	
	/**
	 * 获取机构管理列表的数据
	 * @param page
	 * @return
	 */
	List<Org> getOrgsByPage(Page<Org> page);

	/**
	 * 新增机构
	 * @param org
	 */
	void orgAdd(Org org);

	void orgUpdate(Org org);
	
	void orgUpdateNotNull(Org org);

	void orgDelete(String id);
	
	Org selectByOrgName(String name);
	
	List<Org> getExportOrg();//导出数据
	
	Org selectByPrimaryKey(Long id);
	
	List<Org> getChildOrgs(String parentid);//获取运营商下的所有代理商

	Org getOrgWxApp(String storeCode);
	
	
	
}
