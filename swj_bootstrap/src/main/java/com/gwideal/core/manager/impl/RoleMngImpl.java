package com.gwideal.core.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.RoleMng;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.Role;
import com.gwideal.core.model.User;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class RoleMngImpl extends BaseManagerImpl<Role> implements RoleMng{
	
	@Autowired
	private FunctionMng functionMng;
	
	/**
     * 验证角色是否已存在
     * @author ren_wei
     * @param user
     * @return
     */
	@Override
    public synchronized boolean isExist(Role role)
    {
    	List<Role> list=null;
    	if(StringUtil.isEmpty(role.getId())){
    		String condition = " from Role where (code = ? or name = ?) and flag=1 ";
    		list = this.find(condition, new Object[]{role.getCode(),role.getName()});
    	}else{
			String condition = " from Role where (code = ? or name = ?) and id <> ? and flag=1 ";
			list = this.find(condition, new Object[]{role.getCode(),role.getName(),role.getId()});
    	}
    	if(null!=list && list.size()>0){
    		return true;
    	}
		return false;
    }

	@Override
	public Pagination list(Role bean, String sort, String order, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Role Where flag=1");
		if(null!=bean){
			if(!StringUtil.isEmpty(bean.getName())){
				f.append(" and name like :name");
				f.setParam("name","%"+bean.getName()+"%");
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
	public void save(Role bean,String funcIds,User user) {
		// TODO Auto-generated method stub
		if(null!=bean && !StringUtil.isEmpty(bean.getId())){
			Role role=super.findById(bean.getId());
			bean.setUpdator(user);
			bean.setFunctions(role.getFunctions());
			bean.setUsers(role.getUsers());
		}else{
			bean.setCreator(user);
		}
		bean=(Role)super.merge(bean);
		//设置多对多关系
		if(!StringUtil.isEmpty(funcIds)){
			String[] functionArr=funcIds.split(",");
			List<Long> funcs=new ArrayList<Long>();
			for(String id:functionArr){
				funcs.add(Long.valueOf(id));
			}
			List<Function> listRemoveFunction=new ArrayList<Function>();
			if(null==bean.getFunctions()){
				bean.setFunctions(new ArrayList<Function>());
			}
			for(Function function:bean.getFunctions()){
				if(!funcs.contains(function.getId())){
					listRemoveFunction.add(function);
				}
			}
			for(Function function:listRemoveFunction){
				bean.removeFunction(function);
			}
			for(Long fid:funcs){
				Function function=functionMng.findById(fid);
				if(null==function.getRoles()){
					function.setRoles(new ArrayList<Role>());
				}
				if(!bean.getFunctions().contains(function)){
					bean.addFunction(function);
				}
			}
		}else{
			bean.setFunctions(null);
		}
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Role Where flag=1 order by orderNo,name");
		return super.find(f);
	}
	
	@Override
	public Role findByCode(String code) {
		List<Role> list = super.find("from Role Where flag=1 and code=?", code);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}
}
