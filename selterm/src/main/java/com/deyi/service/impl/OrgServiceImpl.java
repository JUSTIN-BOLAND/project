package com.deyi.service.impl;

import com.deyi.dao.OrgDao;
import com.deyi.entity.Org;
import com.deyi.service.OrgService;
import com.deyi.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService{
	private Logger LOGGER =  LoggerFactory.getLogger(OrgServiceImpl.class);
	
	@Autowired
	private OrgDao orgDao;
	
	@Override
	public Org getOrgByorgAccount(String orgAccount) {
		return orgDao.selectByOrgAccount(orgAccount);
	}

	@Override
	public Org selectByOrgSalesman(String loginName) {
		return orgDao.selectByOrgSalesman(loginName);
	}

	@Override
	public List<Org> getOrgsByOrgAccount(String loginName) {
		List<Org> list = new ArrayList<Org>();
		Org org = orgDao.selectByOrgAccount(loginName);//当前机构
		list.add(org);
		if("1".equals(org.getNode())){
			List<Org> childs1 = orgDao.getChildOrgs(org.getId());
			list.addAll(childs1);
			for (Org org2 : childs1) {
				if("1".equals(org2.getNode())){
					List<Org> childs2 = orgDao.getChildOrgs(org2.getId());
					list.addAll(childs2);
				}
				
			}
		}
		return list;
	}
	
	@Override
	public List<Org> getOrgsByOrgSalesman(String loginName) {
		List<Org> list = new ArrayList<Org>();
		Org org = orgDao.selectByOrgSalesman(loginName);//当前机构  业务员在
//		list.add(org);
		if(org == null){
			return null;
		}
		if("1".equals(org.getNode())){
			List<Org> childs1 = orgDao.getChildOrgs(org.getId());
			list.addAll(childs1);
		}
		return list;
	}
	
	@Override
	public List<Org> getOrgsByOrgSalesmanAndCurrentOrg(String loginName) {
		List<Org> list = new ArrayList<Org>();
		Org org = orgDao.selectByOrgSalesman(loginName);//当前机构  业务员在
		list.add(org);
		if(org == null){
			return null;
		}
		if("1".equals(org.getNode())){
			List<Org> childs1 = orgDao.getChildOrgs(org.getId());
			list.addAll(childs1);
		}
		return list;
	}

	@Override
	public List<Org> getOrgsByPage(Page<Org> page) {
		List<Org> orgs = orgDao.getOrgsByPage(page);
		return orgs;
	}

	@Override
	public void orgAdd(Org org) {
		orgDao.insertSelective(org);
	}

	@Override
	public void orgUpdate(Org org) {
		orgDao.updateByPrimaryKeySelective(org);
	}

 
	
	@Override
	public Org getOrgById(String id) {
		return orgDao.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public void orgDelete(String id) {
		orgDao.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public void orgUpdateNotNull(Org org) {
		orgDao.updateByPrimaryKeySelective(org);
	}

	/**
	 *  根据name验证org是否有数据
	 *  @author 
	 *  @param
	 * 
	 */
	@Override
	public Org selectByOrgName(String name) {
		return orgDao.selectByOrgName(name);
	}

	@Override
	public List<Org> getExportOrg() {		
		return orgDao.getExportOrg();
	}

	@Override
	public Org selectByPrimaryKey(Long id) {
		return orgDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Org> getChildOrgs(String parentid) {
		return orgDao.getChildOrgs(parentid);
	}

	@Override
	public Org getOrgWxApp(String storeCode){
		return orgDao.getOrgWxApp(storeCode);
	}
	
}
