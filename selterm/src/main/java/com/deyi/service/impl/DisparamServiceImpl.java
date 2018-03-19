package com.deyi.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.DisparamMapper;
import com.deyi.entity.Disparam;
import com.deyi.service.DisparamService;

@Service
public class DisparamServiceImpl implements DisparamService{
	@Autowired
	private DisparamMapper disparamMapper;

	@Override
	public List<Disparam> getDisparams(String storeId) {
		List<Disparam> list = disparamMapper.getDisparams(storeId);
		if(list.isEmpty()){
			return save(storeId);
		}else{
			return list;
		}
	}
	private List<Disparam> save(String storeId){
		List<Disparam> list = new ArrayList<Disparam>();
		list.add(new Disparam("当班结算", "D", "DANG_BAN_JIE_SUAN", storeId));
		list.add(new Disparam("交易查询", "Q", "JIAO_YI_CHA_XUN", storeId));
		list.add(new Disparam("收款设置", "F1", "SHOU_KUAN_SHE_ZHI", storeId));
		
		list.add(new Disparam("启用打印机", "0", "QI_YONG_DAYIN_JI", storeId));
		list.add(new Disparam("交易成功后订单", "1", "JIAOYI_CG_ORDER", storeId));
		list.add(new Disparam("当班结算打印", "1", "DANG_BAN_DAYIN", storeId));
		
		for (Disparam disparam : list) {
			disparamMapper.insertSelective(disparam);
		}
		return list;
	}
	@Override
	public void update(Disparam sysparam) {
		disparamMapper.updateByPrimaryKeySelective(sysparam);
	}

	
	
}
