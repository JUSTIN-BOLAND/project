package com.deyi.service;

import java.util.List;


import com.deyi.entity.Role;
import com.deyi.util.Page;

public interface RoleService {
	void deleteRole(Long id);
	void updateRole(Role role);
	List<Role> getRoleList(Page<Role> page);
	List<Role> selectByOrgId(Long orgid);
	
	void saveRole(Role role);
    
	
	/**
	 * 使用这个方法 ，将会得到上级角色和他所有的孩子角色
	 * @param id 
	 * @return
	 */
	Role selectByPrimaryKey(Long id);
	
	/**
	 * 根据用户登陆的角色ID得到可以看到的具有权限的角色ID集合
	 * @param roleid   用户登陆的角色ID
	 * @return
	 */
	public List<String> selectGetChildrenRoleAndSelf(Long roleid);
	
	
	/**
	 * 在新增代理商的时候，向代理商生成默认的菜单
	 * <p> 生成的角色有：代理商角色，经理，组长，业务员，商户
	 * <p> 当生成角色之后，再给每个角色进行分配菜单列表
	 * @author xiongqq
	 * @param creator		创建人
	 * @param creatorName	创建人姓名
	 * @param Rolestatus	是否开通  1.开通，2未开通
	 * @return	返回代理商角色ID
	 */
	public Role addorgrole(String parentroleid,String creator,String creatorName,String Rolestatus,String orgId);
	
	
	/**
	 * 根据机构ID得到商户的角色ID,1.
	 * @param orgid 机构ID
	 * @param type 1. 一清商户2. 二清商户
	 * @return
	 */
	Role selectManerchatRoleIdOrgId(String orgid,String type);
	List<Role> selectRoleByroleids(String ids);
	
	/**
	 * 得到单个的角色
	 * @param id
	 * @return
	 */
	Role selectById(Long id);
}
