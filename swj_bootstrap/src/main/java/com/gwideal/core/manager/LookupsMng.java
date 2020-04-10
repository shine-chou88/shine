package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.User;

public interface LookupsMng extends BaseManager<Lookups>{
	public boolean isExist(Lookups bean);
	public Pagination list(Lookups bean,String sort,String order,int pageIndex,int pageSize);
	/**
	 * 根据类别code拿字典想
	 * @param categoryCode 类别code
	 * @return
	 */
	public List<Lookups> getLookupsByCategoryCode(String categoryCode);
	
	/**
	 * 根据类别code拿字典想
	 * @param categoryCode 类别code
	 * @return
	 */
	public List<Lookups> getCmsLookupsByCategoryCode(String categoryCode);
	
	/**
	 * 判断是否是维稳工作通知公告
	 * @param typeId
	 * @param categoryCode
	 * @return
	 */
	public boolean isWwgzTzgg(String typeId,String categoryCode);
	
	public Lookups findByLookId(String id);
	
	public Lookups findByLookCode(String code);
	
	public Lookups getByParentAndName(String parentCode, String name);
	
	/**
	 * 获得同级字典的最大排序
	 * @param pCode 父字典代码
	 * @return 数据库已有最大顺序+1
	 */
	public int getMaxOrderByParent(String pCode);
	/**
	 * 创建新字典
	 * @param name 字典名称
	 * @param pCode 父字典代码
	 * @param user
	 * @return
	 */
	public Lookups createNewLookups(String name, String pCode, User user);
}
