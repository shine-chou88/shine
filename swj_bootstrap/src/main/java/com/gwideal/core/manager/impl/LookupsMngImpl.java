package com.gwideal.core.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.CategoryMng;
import com.gwideal.core.manager.LookupsMng;
import com.gwideal.core.model.Category;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.User;
@SuppressWarnings("unchecked")
@Transactional
@Service
public class LookupsMngImpl extends BaseManagerImpl<Lookups> implements LookupsMng{
	
	@Autowired
	private CategoryMng categoryMng;
	
	@Override
	public Pagination list(Lookups bean, String sort, String order,
			int pageIndex, int pageSize) {
		Finder f=Finder.create("from Lookups Where flag=1");
		if(null!=bean){
			if(!StringUtil.isEmpty(bean.getName())){
				f.append(" and name like :name");
				f.setParam("name","%"+bean.getName()+"%");
			}
			if(null!=bean.getCategory() && !StringUtil.isEmpty(bean.getCategory().getCode())){
				f.append(" and category.code = :categoryCode");
				f.setParam("categoryCode",bean.getCategory().getCode());
			}
		}
		if(!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order) && (order.equals("asc") || order.equals("desc"))){
			f.append(" order by "+sort+" "+order);
		}else{
			f.append(" order by orderNo,name");
		}
		return super.find(f, pageIndex, pageSize);
	}

	@Override
	public boolean isExist(Lookups bean) {
		List<Lookups> list = null;
		StringBuffer hql=new StringBuffer("from Lookups where flag=1 ");
		hql.append(" and code='"+bean.getCode()+"' ");
		hql.append(" and category.id='"+bean.getCategory().getId()+"' ");
		if (!StringUtil.isEmpty(bean.getId())) {
			hql.append(" and id<>'"+bean.getId()+"' ");
		}
		
		list=super.find(hql.toString());
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Lookups> getLookupsByCategoryCode(String categoryCode) {
		return super.find("from Lookups Where flag=1 and category.code = ? order by orderNo",new Object[]{categoryCode});
	}

	@Override
	public List<Lookups> getCmsLookupsByCategoryCode(String categoryCode) {
		if(!StringUtil.isEmpty(categoryCode)){
			return super.find("from Lookups Where flag=1 and category.code = ? order by orderNo",new Object[]{categoryCode});
		}else{
			return super.find("from Lookups Where flag=1 and category.code in ('CMS-TYPE','CMS-WWGZ-TYPE') order by orderNo",new Object[]{});
		}
	}
	
	@Override
	public boolean isWwgzTzgg(String typeId,String categoryCode){
		List<Lookups> list=super.find("from Lookups Where flag=1 and id = ? and category.code = ?",new Object[]{typeId,categoryCode});
		if(null!=list && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public Lookups findByLookId(String id) {
		Finder hql=Finder.create(" from Lookups where flag=1 and id=:id").setParam("id", id);
		List<Lookups> list=super.find(hql);
		return list!=null&&list.size()>0?list.get(0):null;
	}

	@Override
	public Lookups findByLookCode(String code) {
		Finder hql=Finder.create(" from Lookups where flag=1 and code=:code").setParam("code", code);
		List<Lookups> list=super.find(hql);
		return list!=null&&list.size()>0?list.get(0):null;
	}

	@Override
	public Lookups getByParentAndName(String parentCode, String name) {
		List<Lookups> list = super.find("from Lookups Where flag=1 and category.code = ? and name like ?" +
				"order by orderNo",new Object[]{parentCode,"%"+name+"%"});
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getMaxOrderByParent(String pCode) {
		int version=1;
		List<?> list=super.find("Select max(orderNo) from Lookups Where flag=1 and category.code=?",pCode);	
		if(null!=list && list.get(0)!=null && list.get(0).toString()!=null){
			version=Integer.valueOf(list.get(0).toString())+1;
		}    
		return version;
	}

	@Override
	public Lookups createNewLookups(String name, String pCode, User user) {
		int order = getMaxOrderByParent(pCode);//排序
		String code = pCode + "-" + String.valueOf(order);//代码
		Category cate = categoryMng.getCateByParentAndSelf(null, pCode);//父字典
		Lookups look = new Lookups(name, order, code, cate, user, new Date());
		look = (Lookups) saveOrUpdate(look);
		return look;
	}
	
}
