package com.gwideal.core.manager;

import java.util.List;
import java.util.Set;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.Role;

public interface FunctionMng extends BaseManager<Function> {

	/**
	 * 获得管理员权限
	 * 
	 * @param adminId
	 * @return
	 */
	public List<Function> getFunctions(String userId);

	/**
	 * 获得管理员权限项集合
	 * 
	 * @param adminId
	 * @return
	 */
	public Set<String> getFunctionItems(String userId);

	/**
	 * 获得所有根节点
	 * 
	 * @return
	 */
	public List<Function> getRoots();

	/**
	 * 获得子节点
	 * 
	 * @param pid
	 * @return
	 */
	public List<Function> getChild(Long pid);
	
	/**
	 * 更新
	 * @param bean
	 */
	public void update(Function bean);
	
	/**
	 * 返回一个全部节点的json格式的字符串
	 * @param roleId 角色id
	 * @return
	 */
	public String getTree(String roleId);
	
	/**
	 * 返回一个全部节点的json格式的字符串
	 * @param roleId 角色id
	 * @return
	 */
	public String getUserTree(String userId);
	
	public Function findById(Long id);
	/**
	 * 获得管理员权限项集合
	 * 
	 * @param adminId
	 * @return
	 */
	public void getFunctionItems(Set<String> funcItemSet,Role role);
	
	/**
	 * 是否有维稳工作权限
	 * @param userId
	 * @return
	 */
	public boolean hasWwgzFunction(String userId);
}