package com.deyi.service;

import java.util.List;

import com.deyi.entity.Bulletin;
import com.deyi.util.Page;

public interface BulletinService {

    int deleteByPrimaryKey(Long id);

    int insert(Bulletin record);

    int insertSelective(Bulletin record);

    Bulletin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bulletin record);

    int updateByPrimaryKey(Bulletin record);
    
    List<Bulletin> getBulletinList(Page<Bulletin> page);
    
    List<Bulletin> getBulletinByOrg(String orgId);//根据orgid查询所有公告
    
    List<Bulletin> getBullByOrg(String orgId);//根据orgid查询所有公告并过滤
    
    List<Bulletin> getBullList(Page<Bulletin> page);//查询运营商和代理商下的公告且分页
}
