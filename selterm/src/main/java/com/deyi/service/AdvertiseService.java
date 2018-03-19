package com.deyi.service;

import java.util.List;

import com.deyi.entity.Advertise;
import com.deyi.util.Page;

public interface AdvertiseService {

    int deleteByPrimaryKey(Long id);

    int insert(Advertise record);

    int insertSelective(Advertise record);

    Advertise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Advertise record);

    int updateByPrimaryKey(Advertise record);
    
    List<Advertise> getAdvertisesList(Page<Advertise> page);
    
//    List<Advertise> getAdvertisesByOrg(String orgId);
    
    List<Advertise> getAdvertisesByOrg(String orgId);//根据orgid查询所有广告
    
    List<Advertise> getAdvByOrg(String orgId);//根据orgid查询所有广告且过滤数据
	
}
