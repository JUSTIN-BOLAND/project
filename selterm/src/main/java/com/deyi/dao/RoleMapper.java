package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.Role;
import com.deyi.util.Page;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Role record);
    int insertSelective(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectByOrgId(Long orgid);
    int updateByPrimaryKeySelective(Role record);
    int updateByPrimaryKey(Role record);
    List<Role> getRoleList(Page<Role> page);
    Role selectManerchatRoleIdByOrgRoleid(String orgid,int usertype);
	List<Role> selectRoleByroleids(@Param("ids")String ids);
	
	
	Role selectById(Long id);
}