package com.deyi.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.AliPublicMapper;
import com.deyi.dao.UnionPublicMapper;
import com.deyi.dao.WxPublicMapper;
import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.entity.UnionPublic;
import com.deyi.entity.WxPublic;
import com.deyi.service.PaySetService;

@Service
public class PaySetServiceImpl implements PaySetService {

	@Autowired
	private WxPublicMapper wxPublicMapper;
	
	@Autowired
	private AliPublicMapper aliPublicMapper;
	@Autowired
	private UnionPublicMapper unionPublicMapper;
	
	@Override
	public WxPublic getWxPublicById(String id) {
		return wxPublicMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateWxPublic(WxPublic wxPublic) {
		wxPublicMapper.updateByPrimaryKeySelective(wxPublic);
	}

	@Override
	public void saveWxPublic(WxPublic wxPublic) {
		wxPublicMapper.insertSelective(wxPublic);
	}

	@Override
	public WxPublic getWxPublicByMerchId(String string) {
		return wxPublicMapper.getWxPublicByMerchId(string);
	}

	@Override
	public AliPublicWithBLOBs getAliPublicByMerchId(String merchId) {
		return aliPublicMapper.selectByMechId(merchId);
	}

	@Override
	public void updateAliPublic(AliPublicWithBLOBs aliPublicWithBLOBs) {
		aliPublicMapper.updateByPrimaryKeySelective(aliPublicWithBLOBs);
	}

	@Override
	public void saveAliPublic(AliPublicWithBLOBs aliPublicWithBLOBs) {
		aliPublicMapper.insertSelective(aliPublicWithBLOBs);
	}

	@Override
	public AliPublicWithBLOBs queryorgAlipublic(String orgid) {
		return aliPublicMapper.queryorgAlipublic(orgid);
	}

	@Override
	public WxPublic getWxPublicByorgid(String id) {
		return wxPublicMapper.getWxPublicByOrgId(id);
	}

	@Override
	public void updateUnionPublic(UnionPublic unionPublic) {
		unionPublicMapper.updateByPrimaryKeySelective(unionPublic);
		
	}

	@Override
	public void saveUnionPublic(UnionPublic unionPublic) {
		unionPublicMapper.insertSelective(unionPublic);
		
	}

	@Override
	public UnionPublic getUnionPublicByorgid(String id) {
		return unionPublicMapper.getUnionPublicByOrgId(id);
	}

	@Override
	public UnionPublic getUnionPublicByMerchId(String string) {
		return unionPublicMapper.getUnionPublicByMerchId(string);
	}

	


	
}
