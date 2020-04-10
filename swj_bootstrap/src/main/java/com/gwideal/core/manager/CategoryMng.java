package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.Category;
import com.gwideal.core.model.User;

public interface CategoryMng extends BaseManager<Category>{
	/**
	 * 获得所有根节点
	 * 
	 * @return
	 */
	public List<Category> getRoots();
	public List<Category> getCategoryRoots(String name,String parentId); 
	/**
	 * 获得子节点
	 * 
	 * @param pid
	 * @return
	 */
	public List<Category> getChild(String pid);
	public List<Category> getCategoryChild(String pid,String name,String parentId); 
	public boolean isExist(Category bean);
	public List<Category> getParent(String id);
	public Pagination list(Category bean,String sort,String order,int pageIndex,int pageSize);
	/**
	 * 通过父节点的编码获取子节点
	 * @param parentCode
	 * @return
	 */
	public List<Category> getChildByParentCode(String parentCode);
	
	/**
	 * 通过父节点和自身id获得节点
	 * @param parentCode
	 * @param selectedCode
	 * @return
	 */
	public Category getCateByParentAndSelf(String parentCode, String selfCode);
	
	/**
	 * 判断同级是否有当前代码
	 * @param pCode 父字典代码
	 * @param code 代码
	 * @return
	 */
	public boolean isExist(String pCode,String code);
	
	/**
	 * 删除字典需要同时删除字典对应的字典项
	 * @param id
	 * @param user
	 * @return
	 */
	public int delete(String id,User user);

}
