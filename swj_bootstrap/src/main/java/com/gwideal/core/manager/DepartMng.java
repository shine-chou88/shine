package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.entity.TreeEntity;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.Depart;

public interface DepartMng extends BaseManager<Depart>{
	/**
	 * 获得所有根节点
	 * 
	 * @return
	 */
	public List<Depart> getRoots();
	
	/**
	 * 获得子节点
	 * 
	 * @param pid
	 * @return
	 */
	public List<Depart> getChild(String pid);
	
	public String getTree(String checkIds);
	
	public Pagination list(Depart bean,String sort,String order,int pageIndex,int pageSize);
	
	/**
	 * 验证部门代码是否已存在
	 * @param role
	 * @return
	 */
	public boolean isExist(Depart bean);
	
	/**
	 * 通过部门代码查找部门
	 * @param code
	 * @return
	 */
	public Depart findByCode(String code);

	/**
	 * 查询部门是否有下属部门
	 * @param depart
	 * @return
	 */
	public boolean haveChild(Depart depart);

	/**
	 * 根据查询条件获得treegrid
	 * @param depart
	 * @return
	 */
	public List<TreeEntity> getQueryTree(Depart depart);
	
	/**
     * 生成组织机构编码
     * @param id 组织机构主键
     * @param pid 组织机构的父级主键
     * @return 组织机构编码
     */
	public String generateOrgCode(String id, String pid);
	
	/**
	 * 获取矛盾默认的推送相关单位（区维稳室、区公安分局、区信访办）
	 * @return
	 */
	public List<Depart> getDefaultJcxx();

	public List<TreeEntity> getQuerySupervisionTree(Depart depart);
	
	/**
	 * 
	 * @return
	 */
	public List<Depart> getCertificateDepartList();
}
