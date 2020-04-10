package com.gwideal.core.manager.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.CategoryMng;
import com.gwideal.core.model.Category;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.User;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class CategoryMngImpl extends BaseManagerImpl<Category> implements CategoryMng{

    @Override
	public synchronized boolean isExist(Category bean) {
		List<Category> list = null;
		if (StringUtil.isEmpty(bean.getId())) {
			if (null != bean.getParent() && null != bean.getParent().getId() && !"".equals(bean.getParent().getId())) {
				list=super.find("from Category where flag=1 and code=? and parent.id=?",new Object[]{bean.getCode(),bean.getParent().getId()});
			} else {
				list=super.find("from Category where flag=1 and code=?",new Object[]{bean.getCode()});
			}
		} else {
			if (null != bean.getParent() && null != bean.getParent().getId() && !"".equals(bean.getParent().getId())) {
				String hql ="from Category where code=? and id<>? and flag=1 and parent.id=?";
				list=super.find(hql,new Object[]{bean.getCode(),bean.getId(),bean.getParent().getId()});
			} else {
				String hql ="from Category where code=? and id<>? and flag=1";
				list=super.find(hql,new Object[]{bean.getCode(),bean.getId()});
			}
		}
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}
    
	@Override
	public Pagination list(Category bean, String sort, String order,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Category Where flag=1");
		if(null!=bean){
			if(!StringUtil.isEmpty(bean.getName())){
				f.append(" and name like :name");
				f.setParam("name","%"+bean.getName()+"%");
			}
			if(!StringUtil.isEmpty(bean.getCode())){
				f.append(" and code = :code");
				f.setParam("code",bean.getCode());
			}
			if(!StringUtil.isEmpty(bean.getParentCode())){
				f.append(" and parent.code = :parentcode");
				f.setParam("parentcode",bean.getParentCode());
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
	public List<Category> getParent(String id) {
		// TODO Auto-generated method stub
		List<Category> list = null;
		if(StringUtil.isEmpty(id)){
			list=super.find("from Category Where flag=1 order by orderNo,name",new Object[]{});
		}else{
			list=super.find("from Category Where flag=1 and id<>? order by orderNo,name",new Object[]{id});
		}
		return list;
	}

	@Override
	public List<Category> getRoots() {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Category Where flag=1 and parent.id is null order by orderNo,name");
		return super.find(f);
	}

	@Override
	public List<Category> getChild(String pid) {
		// TODO Auto-generated method stub
		String hql = "from Category where flag=1 and parent.id = ? order by orderNo,name";
		return find(hql, pid);
	}

	@Override
	public List<Category> getChildByParentCode(String parentCode) {
		// TODO Auto-generated method stub
		String hql = "from Category where flag=1 and parent.code = ? order by orderNo,name";
		return find(hql, parentCode);
	}
	
	@Override
	public List<Category> getCategoryRoots(String name,String parentId){
		Finder f=Finder.create("from Category Where flag=1 and parent.id is null ");
		if( !StringUtil.isEmpty( parentId ) && !"zdlxbm".equals(parentId) ){  f.append(" and id<>:parentId ").setParam("parentId", parentId); }
		if( !StringUtil.isEmpty( name ) ){  f.append(" and name like :name ").setParam("name", "%"+name+"%"); }
		f.append(" order by orderNo,name");
		return super.find(f);
	}
	
	@Override
	public List<Category> getCategoryChild(String pid,String name,String parentId){
		Finder f = Finder.create("from Category where flag=1  ");
		if( !StringUtil.isEmpty( parentId ) && !"zdlxbm".equals(parentId) ){  f.append(" and id<>:parentId ").setParam("parentId", parentId); }
		if( !StringUtil.isEmpty( pid ) ){  f.append(" and parent.id = :pid ").setParam("pid", pid); }
		if( !StringUtil.isEmpty( name ) ){  f.append(" and name like :name ").setParam("name", "%"+name+"%"); }
		f.append(" order by orderNo,name");
		return super.find(f);
	}

	@Override
	public Category getCateByParentAndSelf(String parentCode, String selfCode) {
		Finder f = Finder.create(" from Category where flag=1 ");
		if (!StringUtil.isEmpty(parentCode)) {
			f.append(" and parent.code =:parentCode").setParam("parentCode", parentCode);
		}
		if (!StringUtil.isEmpty(selfCode)) {
			f.append(" and code =:selfCode").setParam("selfCode", selfCode);
		}else{
			return null;
		}
		List<Category> list = super.find(f);
		if (list != null && 0 != list.size() ) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public boolean isExist(String pCode, String code) {
		if (!StringUtil.isEmpty(code)) {
			List<Lookups> list = null;
			StringBuffer hql=new StringBuffer("from Category where flag=1 and code='"+code+"' ");
			if (!StringUtil.isEmpty(pCode)) {
				hql.append(" and parent.code='"+pCode+"' ");
			}
			list=super.find(hql.toString());
			if(null!=list && list.size()>0){
				return true;
			}
		}
		return false;
	}

	@Override
	public int delete(String id, User user) {
		// TODO Auto-generated method stub
		String sqlCategory="Update sys_lookup set pflag=0,pupdatetime=sysdate,pupdater=? Where pid=?";
		String sqlLookups="Update sys_lookups set pflag=0,pupdatetime=sysdate,pupdater=? Where lookup_id=?";
		super.getSession().createSQLQuery(sqlCategory).setParameter(0,user.getId()).setParameter(1,id).executeUpdate();
		return super.getSession().createSQLQuery(sqlLookups).setParameter(0,user.getId()).setParameter(1,id).executeUpdate();
	}

}
