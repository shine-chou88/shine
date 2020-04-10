package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.Role;
import com.gwideal.core.model.User;

public interface RoleMng extends BaseManager<Role>{
	public Pagination list(Role bean,String sort,String order,int pageIndex,int pageSize);
	public void save(Role bean,String funcIds,User user);
	/**
	 * 拿到所有的角色
	 * @return
	 */
	public List<Role> getAll();
	
	/**
	 * 验证角色是否已存在
	 * @param role
	 * @return
	 */
	public boolean isExist(Role role);
	
	public Role findByCode(String code);
}
