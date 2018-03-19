package com.deyi.service;

import java.util.List;

import com.deyi.entity.InheritRecordSheet;
import com.deyi.util.Page;

public interface InheritRecordSheetService {

	    int deleteByPrimaryKey(Long id);

	    int insert(InheritRecordSheet record);

	    int insertSelective(InheritRecordSheet record);

	    InheritRecordSheet selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(InheritRecordSheet record);

	    int updateByPrimaryKey(InheritRecordSheet record);
	    
	    List<InheritRecordSheet> getInheritRecordSheets(Page<InheritRecordSheet> page);
	    
	    List<InheritRecordSheet> queryInheritRecordSheet();//查询所有
	    
	    InheritRecordSheet selectByRowId(String id);//根据原账号id查找数据

		List<String> queryinheritDataByuserids(String idsByList2);
}
