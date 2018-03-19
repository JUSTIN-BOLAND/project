package com.deyi.dao;

import com.deyi.entity.CityStd;
import com.deyi.entity.ManagerCat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageCatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManagerCat record);

    int insertSelective(ManagerCat record);

    ManagerCat getManagerCatById(Integer id);

    int updateByPrimaryKeySelective(ManagerCat record);

    int updateByPrimaryKey(ManagerCat record);

	List<com.deyi.vo.TypeVo> getManagerCat(Integer parentId);
    ManagerCat getManagerCatByCode(String  catCode);


}