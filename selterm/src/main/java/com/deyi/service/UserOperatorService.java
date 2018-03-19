package com.deyi.service;

import java.util.List;

import com.deyi.entity.UserInfo;
import com.deyi.entity.UserOperator;
import com.deyi.util.Page;

public interface UserOperatorService {
    int insert(UserOperator record);

    int insertSelective(UserOperator record);
    
    UserOperator selectByPrimaryKey(String id);
    
    List<UserOperator> getUserOperatorMapperList(Page<UserOperator> page);
    
    int updateByPrimaryKeySelective(UserOperator record);
    
    int deleteByPrimaryKey(String id);
    
    UserOperator selectByPrimaryAccountName(String accountName);
    
    /**
     * 根据登录用户获取用户信息
     * @param userInfo
     * @return 用户ID集合  '1','2','3','4'
     */
    String getUserIdsbyLoginname(UserInfo userInfo);
    
    /**
     * 根据机构得到ID
     * @param id 	机构 ID
     * @return
     */
	public List<UserOperator> selectByOrgid(String id);
	
	
	/**
	 * 此方法可以得到用户的上级用户和下级用户
	 * @param id
	 * @return
	 */
	UserOperator selectbyid(Long id);

	/**
	 * 根据用户得到自己和下属用户登陆名ID集合
	 * @param userOperator
	 * @return  list<String>  longmname
	 */
	List<String> getalluserLoginnid(UserOperator userOperator);

	/**
	 * 根据用户IDs得到所有的用户
	 * @param userIdsbyLoginname
	 * @return
	 */
	List<UserOperator> selectbyuserids(String ids);
	
	int deleteByLoginName(String loginName);
}
