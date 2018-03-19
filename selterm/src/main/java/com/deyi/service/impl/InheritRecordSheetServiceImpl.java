package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.InheritRecordSheetMapper;
import com.deyi.entity.InheritRecordSheet;
import com.deyi.service.InheritRecordSheetService;
import com.deyi.util.Page;

@Service
public class InheritRecordSheetServiceImpl implements InheritRecordSheetService {

	@Autowired
	private InheritRecordSheetMapper inheritRecordSheetMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return inheritRecordSheetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(InheritRecordSheet record) {
		return inheritRecordSheetMapper.insert(record);
	}

	@Override
	public int insertSelective(InheritRecordSheet record) {
		return inheritRecordSheetMapper.insertSelective(record);
	}

	@Override
	public InheritRecordSheet selectByPrimaryKey(Long id) {
		return inheritRecordSheetMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(InheritRecordSheet record) {
		return inheritRecordSheetMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(InheritRecordSheet record) {
		return inheritRecordSheetMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<InheritRecordSheet> getInheritRecordSheets(Page<InheritRecordSheet> page) {
		return inheritRecordSheetMapper.getInheritRecordSheets(page);
	}

	 

	@Override
	public List<InheritRecordSheet> queryInheritRecordSheet() {
		return inheritRecordSheetMapper.queryInheritRecordSheet();
	}

	@Override
	public InheritRecordSheet selectByRowId(String id) {
		return inheritRecordSheetMapper.selectByRowId(id);
	}

	@Override
	public List<String> queryinheritDataByuserids(String idsByList2) {
		return inheritRecordSheetMapper.queryinheritDataByuserids(idsByList2);
	}

}
