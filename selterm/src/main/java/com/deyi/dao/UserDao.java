package com.deyi.dao;

import com.deyi.entity.UserInfo;
import com.deyi.util.Page;
import com.deyi.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(String id);
    int deleteByRoleId(String roleId);

    int insert(UserInfo record);

    int updateByPrimaryKeySelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);


    UserInfo getUserPassword(String loginName, String passwd);
    UserInfo getAccountPassword(String loginName, String passwd);

    List<UserVo> getUserInfoList(Page<UserVo> page);


    List<String> getUseridByRoleList(@Param("roleids") String rolelist);
    UserInfo getUserById(String id);
}