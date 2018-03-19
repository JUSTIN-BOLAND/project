package com.deyi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.entity.UserOperator;
import com.deyi.util.Page;
@Repository
public interface UserOperatorMapper {
    int insert(UserOperator record);

    int insertSelective(UserOperator record);
    
    UserOperator selectByPrimaryKey(String id);
    
    List<UserOperator> getUserOperatorMapperList(Page<UserOperator> page);
    
    int updateByPrimaryKeySelective(UserOperator record);
    
    int deleteByPrimaryKey(String id);
    int deleteByLoginName(String loginName);
    
    UserOperator selectByPrimaryAccountName(String accountName);

	List<UserOperator> selectByOrgid(String id);
	
	/**
	 * 此方法可以得到用户的上级用户和下级用户
	 * @param id
	 * @return
	 */
	UserOperator selectbyid(Long id);

	List<UserOperator> selectbyuserids(@Param("ids")String ids);
	
	 
}