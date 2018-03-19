package com.deyi.service;

import java.util.List;

import com.deyi.entity.IndustryType;
import com.deyi.entity.ManagerCat;
import com.deyi.vo.TypeVo;

public interface IndustryTypeService {

	List<TypeVo> getonetype();

    List<com.deyi.vo.TypeVo> twoindustrytype(String id);

    List<com.deyi.vo.TypeVo> threeindustrytype(String id);

	IndustryType getbyid(String business_code);

	IndustryType querybytypecode(String business_code);

	List<com.deyi.vo.TypeVo> getManagerCat(Integer parentId);
    ManagerCat getManagerCatById(Integer id);
	ManagerCat getManagerCatByCode(String  catCode);

}
