package com.gwideal.core.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gwideal.common.entity.TreeEntity;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.DBHelper;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.DepartMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.User;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class DepartMngImpl extends BaseManagerImpl<Depart> implements DepartMng{
	
	@Autowired
	private UserMng userMng;
	
	@Override
	public List<Depart> getRoots() {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Depart Where flag=1 and parent.id is null order by orderNo");
		return super.find(f);
	}

	@Override
	public List<Depart> getChild(String pid) {
		// TODO Auto-generated method stub
		String hql = "from Depart where flag=1 and parent.id = ? order by orderNo";
		return find(hql, pid);
	}

	@Override
	public String getTree(String checkIds) {
		// TODO Auto-generated method stub
		StringBuffer treeJson=new StringBuffer("[");
		//所有父节点
		List<Depart> roots=this.getRoots();
		if(roots!=null && roots.size()>0){
			for (int i = 0; i < roots.size(); i++) {
				String rootId=roots.get(i).getId();
				treeJson.append("{\"id\":\""+rootId+"\",\"text\":\""+roots.get(i).getName()+"\"");
				if(!StringUtil.isEmpty(checkIds) && checkIds.contains(rootId)){
					treeJson.append(",\"checked\":true");
				}
				if(null!=roots.get(i).getChildren() && roots.get(i).getChildren().size()>0){
					treeJson.append(",\"state\":\"closed\"");
				}else{
					treeJson.append(",\"isLeaf\":true");
				}
				childTreeJson(treeJson, rootId,checkIds);
				treeJson.append("}");
				if(i!=(roots.size()-1)){
					treeJson.append(",");
				}
			}
		}
		treeJson.append("]");
		return treeJson.toString();
	}

	public void childTreeJson(StringBuffer treeJson, String rootId,String checkIds) {
		//当前父节点
		List<Depart> childs=this.getChild(rootId);
		if(childs!=null&&childs.size()>0){
			treeJson.append(",\"children\":[");
			for (int j = 0; j < childs.size(); j++) {
				treeJson.append("{\"id\":\""+childs.get(j).getId()+"\",\"text\":\""+childs.get(j).getName()+"\"");
				if(!StringUtil.isEmpty(checkIds) && checkIds.contains(childs.get(j).getId())){
					treeJson.append(",\"checked\":true");
				}
				if(null!=childs.get(j).getChildren() && childs.get(j).getChildren().size()>0){
					treeJson.append(",\"state\":\"closed\"");
				}else{
					treeJson.append(",\"isLeaf\":true");
				}
				this.childTreeJson(treeJson, childs.get(j).getId(),checkIds);
				treeJson.append("}");
				if(j!=(childs.size()-1)){
					treeJson.append(",");
				}
			}
			treeJson.append("]");
		}
	}

	@Override
	public Pagination list(Depart bean, String sort, String order,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Depart Where flag=1");
		if(null!=bean){
			if(!StringUtil.isEmpty(bean.getName())){
				f.append(" and name like :name");
				f.setParam("name","%"+bean.getName()+"%");
			}
			if(!StringUtil.isEmpty(bean.getCode())){
				f.append(" and code like :code");
				f.setParam("code","%"+bean.getCode()+"%");
			}
			if(!StringUtil.isEmpty(bean.getId())){
				f.append(" and id = :id");
				f.setParam("id",bean.getId());
			}
		}
		if(!StringUtil.isEmpty(sort)){
			f.append(" order by " + sort );
		}else{
			f.append(" order by code");
		}
		if(!StringUtil.isEmpty(order)){
			
		}else{
			f.append("  asc");
		}
		return super.find(f, pageIndex, pageSize);
	}

	@Override
	public synchronized boolean isExist(Depart bean) {
		// TODO Auto-generated method stub
		List<Depart> list=null;
    	if(StringUtil.isEmpty(bean.getId())){
    		if (null != bean.getParent() && null != bean.getParent().getId() && !"".equals(bean.getParent().getId())) {
				list=super.find("from Depart where (code = ? or name = ?) and flag=1 and parent.id=?",new Object[]{bean.getCode(),bean.getName(),bean.getParent().getId()});
			} else {
				list=super.find("from Depart where (code = ? or name = ?) and flag=1 ",new Object[]{bean.getCode(),bean.getName()});
			}
    	}else{
    		if (null != bean.getParent() && null != bean.getParent().getId() && !"".equals(bean.getParent().getId())) {
				list=super.find(" from Depart where (code = ? or name = ?) and id <> ? and flag=1 and parent.id=?",new Object[]{bean.getCode(),bean.getName(),bean.getId(),bean.getParent().getId()});
			} else {
				list=super.find(" from Depart where (code = ? or name = ?) and id <> ? and flag=1 ",new Object[]{bean.getCode(),bean.getName(),bean.getId()});
			}
    	}
    	if(null!=list && list.size()>0){
    		return true;
    	}
		return false;
	}

	@Override
	public Depart findByCode(String code) {
		// TODO Auto-generated method stub
		Depart d=null;
		List<Depart> list=super.find("from Depart Where flag=1 and code = ?", new Object[]{code});
		if(null!=list && list.size()>0){
			d=list.get(0);
		}
		return d;
	}

	@Override
	public boolean haveChild(Depart depart) {
		List<Depart> list=super.find("from Depart Where flag=1 and parent.id = ?", new Object[]{depart.getId()});
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
	public boolean haveChild(String parentId) {
		List<Depart> list=super.find("from Depart Where flag=1 and parent.id = ?", new Object[]{parentId});
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<TreeEntity> getQueryTree(Depart depart) {
		List<Depart> departList = new ArrayList<Depart>();
		//根据姓名、代码模糊查询
		StringBuffer hql = new StringBuffer("select depart from Depart depart where depart.flag=1 ");
		if(depart!=null){
			if(!StringUtil.isEmpty(depart.getName())){
				hql.append(" and depart.name like '%" + depart.getName() + "%'");
			}
			if(!StringUtil.isEmpty(depart.getCode())){
				hql.append(" and depart.code = '" + depart.getCode() + "'");
			}
			hql.append(" order by depart.orderNo asc");
		}
		
		departList = find(hql.toString());
		
		List<TreeEntity> list = new ArrayList<TreeEntity>();
		for(Depart depart1: departList ){
			TreeEntity tree = new TreeEntity();
			tree.setId(depart1.getId());
			tree.setText(depart1.getName());
			tree.setCode(depart1.getCode());
			tree.setParentId(depart1.getParent()!=null? depart1.getParent().getId():"");
			if(haveChild(depart1)){
				tree.setHaveChild("1");
				tree.setState("closed");
			}else{
				tree.setHaveChild("0");
			}
			list.add(tree);
		}
		
		//将子节点放入父节点下
		List<TreeEntity> seniorList = new ArrayList<TreeEntity>();
		List<TreeEntity> juniorList = new ArrayList<TreeEntity>();
		for (TreeEntity tree: list) {
			if (haveChild(tree.getId())) {
				seniorList.add(tree);
			} else {
				juniorList.add(tree);
			}
		}
		List<TreeEntity> deleteList = new ArrayList<TreeEntity>();
		for (TreeEntity stree: seniorList) {
			for (TreeEntity jtree: juniorList) {
				if (stree.getId().equals(jtree.getParentId())) {
					deleteList.add(jtree);
					List<TreeEntity> list1 = stree.getChildren();
					if (list1 == null || list1.size() == 0) {
						list1 = new ArrayList<TreeEntity>();
						stree.setChildren(list1);
					}
					list1.add(jtree);
					stree.setState("opened");
				}
			}
			if (null == stree.getChildren()) {
				addchildDepart(stree,getChild(stree.getId()));
			}
		}
		for (TreeEntity tree: deleteList) {
			juniorList.remove(tree);
		}

		List<TreeEntity> finalList = new ArrayList<TreeEntity>();
		finalList.addAll(seniorList);
		finalList.addAll(juniorList);
		return finalList;
	}
	private void addchildDepart(TreeEntity ptree,List<Depart> childDepartList) {
		if (null == ptree.getChildren()) {
			ptree.setChildren(new ArrayList<TreeEntity>());
		}
		for (Depart depart : childDepartList) {
			TreeEntity tree = new TreeEntity();
			tree.setId(depart.getId());
			tree.setText(depart.getName());
			tree.setCode(depart.getCode());
			tree.setParentId(depart.getParent()!=null? depart.getParent().getId():"");
			if(haveChild(depart)){
				tree.setHaveChild("1");
				tree.setState("closed");
			}else{
				tree.setHaveChild("0");
			}
			ptree.getChildren().add(tree);
			if ("1".equals(tree.getHaveChild())) {
				addchildDepart(tree,getChild(tree.getId()));
			}
		}
	}
	public String generateOrgCode(String id, String pid) {
        int orgCodeLength = 2; // 默认编码长度
        if ("3".equals(DBHelper.getOrgCodeLengthType())) { // 类型2-编码长度为3，如001
            orgCodeLength = 3;
        }
        String  newOrgCode = "";
        if(!StringUtils.hasText(pid)) { // 第一级编码
            String sql = "select max(t.org_code) orgCode from wx_t_s_depart t where t.parentdepartid is null";
            List<String> pOrgCode = super.getSession().createSQLQuery(sql).list();
            if(pOrgCode != null && pOrgCode.size() > 0) {
                newOrgCode = String.format("%0" + orgCodeLength + "d", Integer.valueOf(pOrgCode.get(0)) + 1);
            } else {
                newOrgCode = String.format("%0" + orgCodeLength + "d", 1);
            }
        } else { // 下级编码
            String sql = "select max(t.org_code) orgCode from wx_t_s_depart t where t.parentdepartid = '"+pid+"'";
            List<String> orgCode = super.getSession().createSQLQuery(sql).list();
            if(orgCode != null && orgCode.size() > 0) { // 当前基本有编码时
                String curOrgCode = orgCode.get(0);
                String pOrgCode = curOrgCode.substring(0, curOrgCode.length() - orgCodeLength);
                String subOrgCode = curOrgCode.substring(curOrgCode.length() - orgCodeLength, curOrgCode.length());
                newOrgCode = pOrgCode + String.format("%0" + orgCodeLength + "d", Integer.valueOf(subOrgCode) + 1);
            } else { // 当前级别没有编码时
                String pOrgCodeSql = "select max(t.org_code) orgCode from wx_t_s_depart t where t.id = '"+pid+"'";
                List<String> pOrgCode = super.getSession().createSQLQuery(pOrgCodeSql).list();
                if (pOrgCode != null && pOrgCode.size() > 0) {
                	newOrgCode = pOrgCode.get(0) + String.format("%0" + orgCodeLength + "d", 1);
                } else {
                	newOrgCode = String.format("%0" + orgCodeLength + "d", 1);
                }
            }
        }

        return newOrgCode;
    }

	@Override
	public List<Depart> getDefaultJcxx() {
		// TODO Auto-generated method stub
		return super.find("from Depart where flag=1 and code in ('QZFWWWS','QGAFJ','QXFB') order by orderNo");
	}

	@Override
	public List<TreeEntity> getQuerySupervisionTree(Depart depart) {
		List<Depart> departList = new ArrayList<Depart>();
		//根据姓名、代码模糊查询
		StringBuffer hql = new StringBuffer("select depart from Depart depart where depart.flag=1 ");
		if(depart!=null){
			if(!StringUtil.isEmpty(depart.getName())){
				hql.append(" and depart.name like '%" + depart.getName() + "%'");
			}
			if(!StringUtil.isEmpty(depart.getCode())){
				hql.append(" and depart.code = '" + depart.getCode() + "'");
			}
			hql.append(" order by depart.orderNo asc");
		}
		
		departList = find(hql.toString());
		
		List<TreeEntity> list = new ArrayList<TreeEntity>();
		for(Depart depart1: departList ){
			TreeEntity tree = new TreeEntity();
			tree.setId(depart1.getId());
			tree.setText(depart1.getName());
			tree.setCode(depart1.getCode());
			tree.setDepartStatus(true);
			tree.setParentId(depart1.getParent()!=null? depart1.getParent().getId():"");
			if(haveChild(depart1)){
				tree.setHaveChild("1");
				tree.setState("closed");
			}else{
				tree.setHaveChild("0");
			}
			list.add(tree);
			if(tree.isDepartStatus()&&haveChild(depart1)){
				List<User> usersByDepart = userMng.getUserByDepartCode(tree.getCode());
				if(usersByDepart!=null&&usersByDepart.size()>0){
					for (User user : usersByDepart) {
						TreeEntity userTree = new TreeEntity();
						userTree.setId(user.getId());
						userTree.setText(user.getName());
						userTree.setCode(tree.getCode());
						userTree.setDepartStatus(false);
						userTree.setParentId(depart1.getParent()!=null? depart1.getParent().getId():"");
						userTree.setHaveChild("0");
						list.add(userTree);
					}
				}
			}
		}
		
		//将子节点放入父节点下
		List<TreeEntity> seniorList = new ArrayList<TreeEntity>();
		List<TreeEntity> juniorList = new ArrayList<TreeEntity>();
		for (TreeEntity tree: list) {
			if (haveChild(tree.getId())) {
				seniorList.add(tree);
			} else {
				juniorList.add(tree);
			}
		}
		List<TreeEntity> deleteList = new ArrayList<TreeEntity>();
		for (TreeEntity stree: seniorList) {
			for (TreeEntity jtree: juniorList) {
				if (stree.getId().equals(jtree.getParentId())) {
					deleteList.add(jtree);
					List<TreeEntity> list1 = stree.getChildren();
					if (list1 == null || list1.size() == 0) {
						list1 = new ArrayList<TreeEntity>();
						stree.setChildren(list1);
					}
					list1.add(jtree);
					if(jtree.isDepartStatus()){
						List<User> usersByDepart = userMng.getUserByDepartCode(jtree.getCode());
						if(usersByDepart!=null&&usersByDepart.size()>0){
							for (User user : usersByDepart) {
								TreeEntity userTree = new TreeEntity();
								userTree.setId(user.getId());
								userTree.setText(user.getName());
								userTree.setCode(jtree.getCode());
								userTree.setDepartStatus(false);
								userTree.setHaveChild("0");
								list1.add(userTree);
							}
						}
					}
					stree.setState("opened");
				}
				
			}
			if (null == stree.getChildren()) {
				addSupervisionChildDepart(stree,getChild(stree.getId()));
			}
			
		}
		for (TreeEntity tree: deleteList) {
			juniorList.remove(tree);
		}

		List<TreeEntity> finalList = new ArrayList<TreeEntity>();
		finalList.addAll(seniorList);
		finalList.addAll(juniorList);
		return finalList;
	}
	private void addSupervisionChildDepart(TreeEntity ptree,List<Depart> childDepartList) {
		if (null == ptree.getChildren()) {
			ptree.setChildren(new ArrayList<TreeEntity>());
		}
		for (Depart depart : childDepartList) {
			TreeEntity tree = new TreeEntity();
			tree.setId(depart.getId());
			tree.setText(depart.getName());
			tree.setCode(depart.getCode());
			tree.setDepartStatus(true);
			tree.setParentId(depart.getParent()!=null? depart.getParent().getId():"");
			if(haveChild(depart)){
				tree.setHaveChild("1");
				tree.setState("closed");
			}else{
				tree.setHaveChild("0");
			}
			ptree.getChildren().add(tree);
			if(tree.isDepartStatus()&&haveChild(depart)){
				List<User> usersByDepart = userMng.getUserByDepartCode(tree.getCode());
				if(usersByDepart!=null&&usersByDepart.size()>0){
					for (User user : usersByDepart) {
						TreeEntity userTree = new TreeEntity();
						userTree.setId(user.getId());
						userTree.setText(user.getName());
						userTree.setCode(tree.getCode());
						userTree.setDepartStatus(false);
						userTree.setHaveChild("0");
						userTree.setParentId(tree.getId());
						ptree.getChildren().add(userTree);
					}
				}
			}
			if ("1".equals(tree.getHaveChild())) {
				addSupervisionChildDepart(tree,getChild(tree.getId()));
			}
		}
	}

	@Override
	public List<Depart> getCertificateDepartList() {
		Finder f=Finder.create("from Depart Where flag=1");
		f.append(" and ");
		f.append(" code like :secondCode and parent.code is not null ").setParam("secondCode", "SWJ_%");
		f.append(" order by parent.code desc,orderNo ASC");
		List list = super.find(f);
		if(list!=null&&list.size()>0){
			list.add(0, this.findByCode("SHSSWJ"));
		}
		
		return  list;
	}
}
