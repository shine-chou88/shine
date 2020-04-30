package com.gwideal.core.manager.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Updater;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.RoleMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.Function;
import com.gwideal.core.model.Role;
import com.gwideal.core.model.User;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class FunctionMngImpl extends BaseManagerImpl<Function> implements
		FunctionMng {

	@Autowired
	private RoleMng roleMng;
	@Autowired
	private UserMng userMng;
	
	public List<Function> getFunctions(String userId) {
		String hql = "select func from Function func where func.id in"
				+ " (select f1.id from User user join user.roles role join role.functions f1 where user.id = ?) "
				+ " order by func.priority asc";
		return find(hql,userId);
	}

	public Set<String> getFunctionItems(String userId) {
		List<Function> funcList = getFunctions(userId);
		Set<String> funcItemSet = new HashSet<String>();
		String f = null;
		String fs = null;
		String[] fa = null;
		for (Function function : funcList) {
			f = function.getUrl();
			if (!StringUtil.isEmpty(f)) {
				funcItemSet.add(f);
			}
			fs = function.getFuncs();
			if (!StringUtil.isEmpty(fs)) {
				fa = fs.split(Function.FUNC_SPLIT);
				for (String fas : fa) {
					funcItemSet.add(fas);
				}
			}
		}
		User user=userMng.findById(userId);
		if(null!=user && null!=user.getFunctions() && user.getFunctions().size()>0){
			List<Function> listUserFunction=user.getFunctions();
			for (Function function : listUserFunction) {
				f = function.getUrl();
				if (!StringUtil.isEmpty(f)) {
					funcItemSet.add(f);
				}
				fs = function.getFuncs();
				if (!StringUtil.isEmpty(fs)) {
					fa = fs.split(Function.FUNC_SPLIT);
					for (String fas : fa) {
						funcItemSet.add(fas);
					}
				}
			}
		}
		return funcItemSet;
	}

	public List<Function> getRoots() {
		String hql = "select func from Function func where func.flag=1 and func.parent.id is null order by func.priority asc";
		return find(hql);
	}

	public List<Function> getChild(Long pid) {
		String hql = "select func from Function func where func.flag=1 and func.parent.id = ? order by func.priority asc";
		return find(hql, pid);
	}

	@Override
	public void update(Function bean) {
		Function f = findById(bean.getId());
		Function pf = f.getParent();
		Function pbean = bean.getParent();
		// pbean!=null代表需要更新父节点。父节点不能等于自身。
		if (pbean != null && !f.getId().equals(pbean.getId())) {
			// pf!=null原父节点存在，处理原父节点的child
			if (pf != null && !pf.getId().equals(pbean.getId())) {
				pf.getChild().remove(f);
			}
			// pbean.getId()!=null新父节点存在，处理新父节点child
			if (pbean.getId() != null && !pbean.getId().equals(pf.getId())) {
				//Function np = findById(pbean.getId());
				//np.addTochild(f);
			}
		}
		Updater<Function> updater=new Updater<Function>(bean);
		super.updateByUpdater(updater);
	}

	@Override
	public Function save(Function func) {
		if(null==func.getParent() || null==func.getParent().getId()){
			func.setParent(null);
		}
		return super.save(func);
	}

	@Override
	public Function findById(Serializable id) {
		Function func = super.findById(id);
		return func;
	}

	@Override
	public Function deleteById(Serializable id) {
		Function entity = findById(id);
		entity.setFlag(0);
		return (Function)super.updateDefault(entity);
	}
	
	/**
	 * 根据角色id得到该角色拥有功能id集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getFuncsIdsByRoleId(String roleId){
		List<Long> listFuncsIds=new ArrayList<Long>();
		if(!StringUtil.isEmpty(roleId)){
			Role role=roleMng.findById(roleId);
			if(null!=role && null!=role.getFunctions() && role.getFunctions().size()>0){
				List<Function> listFuncs=role.getFunctions(); 
				for(Function f:listFuncs){
					listFuncsIds.add(f.getId());
				}
			}
		}
		return listFuncsIds;
	}
	
	/**
	 * 根据用户id得到该用户拥有功能id集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getFuncsIdsByUserId(String userId){
		List<Long> listFuncsIds=new ArrayList<Long>();
		if(!StringUtil.isEmpty(userId)){
			User user=userMng.findById(userId);
			if(null!=user && null!=user.getFunctions() && user.getFunctions().size()>0){
				List<Function> listFuncs=user.getFunctions(); 
				for(Function f:listFuncs){
					listFuncsIds.add(f.getId());
				}
			}
		}
		return listFuncsIds;
	}
	
	/**
	 * 返回一个全部节点的json格式的字符串
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public String getTree(String roleId) {
		// TODO Auto-generated method stub
		StringBuffer tree=new StringBuffer("[");
		List<Long> listFuncsIds=getFuncsIdsByRoleId(roleId);
		List<Function> list=getRoots();
		if(null!=list && list.size()>0){
			int t=list.size();
			for (int i = 0; i < t; i++) {
				Function f=list.get(i);
				tree.append("{");
				tree.append("\"id\":"+f.getId()+",");
				if(listFuncsIds.contains(f.getId())){
					tree.append("\"checked\":true,");
				}
				tree.append("\"text\":\""+f.getName()+"\"");
				addChild(f,listFuncsIds,tree,false);
				tree.append("}");
				if(i!=t-1){
					tree.append(",");
				}
			}
		}
		tree.append("]");
		return tree.toString();
	}
	
	/**
	 * 返回一个全部节点的json格式的字符串
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public String getUserTree(String userId) {
		// TODO Auto-generated method stub
		StringBuffer tree=new StringBuffer("[");
		List<Long> listFuncsIds=getFuncsIdsByUserId(userId);
		List<Function> list=getRoots();
		if(null!=list && list.size()>0){
			int t=list.size();
			for (int i = 0; i < t; i++) {
				Function f=list.get(i);
				tree.append("{");
				tree.append("\"id\":"+f.getId()+",");
				if(listFuncsIds.contains(f.getId())){
					tree.append("\"state\":{\"checked\":true},");
				}
				tree.append("\"text\":\""+f.getName()+"\"");
				addChild(f,listFuncsIds,tree,true);
				tree.append("}");
				if(i!=t-1){
					tree.append(",");
				}
			}
		}
		tree.append("]");
		return tree.toString();
	}
	
	/**
	 * 递归添加子节点的json格式的字符串
	 * @param f
	 * @param tree
	 */
	public void addChild(Function f,List<Long> listFuncsIds,StringBuffer tree,boolean isUserTree){
		List<Function> listChild=super.find("from Function func where func.flag=1 and func.parent.id=? order by priority",f.getId());
		if(null!=listChild && listChild.size()>0){
			tree.append(",\"nodes\":[");
			int t=listChild.size();
			for (int i = 0; i < t; i++) {
				Function child=listChild.get(i);
				boolean append=true;
				if(isUserTree){
					if(child.getName().equals("区级首页") || child.getName().equals("街镇级首页")){
						append=false;
					}
				}
				if(append){
					tree.append("{");
					tree.append("\"id\":"+child.getId()+",");
					if(listFuncsIds.contains(child.getId())){
						tree.append("\"state\":{\"checked\":true},");
					}
					tree.append("\"text\":\""+child.getName()+"\"");
					addChild(child,listFuncsIds,tree,isUserTree);
					tree.append("}");
					if(i!=t-1){
						tree.append(",");
					}
				}
			}
			tree.append("]");
		}
	}

	@Override
	public Function findById(Long id) {
		// TODO Auto-generated method stub
		Function f=null;
		List<Function> list=super.find("from Function func where func.flag=1 and func.id=?",id);
		if(null!=list && list.size()>0){
			f=list.get(0);
		}
		return f;
	}

	@Override
	public void getFunctionItems(Set<String> funcItemSet,Role role) {
		String f = null;
		String fs = null;
		String[] fa = null;
		for (Function function : role.getFunctions()) {
			f = function.getUrl();
			if (!StringUtil.isEmpty(f)) {
				funcItemSet.add(f);
			}
			fs = function.getFuncs();
			if (!StringUtil.isEmpty(fs)) {
				fa = fs.split(Function.FUNC_SPLIT);
				for (String fas : fa) {
					funcItemSet.add(fas);
				}
			}
		}
	}

	@Override
	public boolean hasWwgzFunction(String userId) {
		// TODO Auto-generated method stub
		String hql = "select func from Function func where func.id in"
				+ " (select f1.id from User user join user.roles role join role.functions f1 where user.id = ?) "
				+ " and url = '/wwgz'";
		List<Function> list=super.find(hql, userId);
		if(null!=list && list.size()>0){
			return true;
		}else{
			list=super.find("Select f from User u join u.functions f Where u.id=? and f.url='/wwgz'", userId);
			if(null!=list && list.size()>0){
				return true;
			}
		}
		return false;
	}

}
