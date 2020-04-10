package com.gwideal.core.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.common.entity.TreeEntity;
import com.gwideal.common.page.ComboboxJson;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.DepartMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.User;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/depart")
public class DepartController extends BaseController{
	
	@Autowired
	private DepartMng departMng;
	@Autowired
	private UserMng userMng;
	
	@RequestMapping(value="/tree")
	@ResponseBody
	public List<TreeEntity> tree(String id,ModelMap model) {
		// 内容。取所有列表，找出父菜单。
		List<Depart> listDepart=null;
		if(null==id){
			listDepart = departMng.getRoots();
		}else{
			listDepart = departMng.getChild(id);
		}
		List<TreeEntity> list=new ArrayList<TreeEntity>();
		if(null!=listDepart && listDepart.size()>0){
			for(Depart d:listDepart){
				TreeEntity ft=new TreeEntity();
				ft.setId(d.getId());
				ft.setText(d.getName());
				if(null!=d.getChildren() && d.getChildren().size()>0){
					ft.setState("closed");
				}else{
					ft.setLeaf(true);
				}
				list.add(ft);
			}
		}
		return list;
	}
	
	@RequestMapping(value="/jsonTree")
	public void jsonTree(String checkIds,HttpServletResponse res){
		try {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter pw=res.getWriter();
			pw.write(departMng.getTree(checkIds));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/list")
	public String list() {
		return "/WEB-INF/gwideal_core/depart/list";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(Depart bean,String sort,String order,Integer page,Integer rows){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=departMng.list(bean,sort,order,page,rows);
		return getJsonPagination(p,page);
	}
	
	/**
	 * 部门选择treegrid数据
	 */
	@RequestMapping(value="/treeGrid")
	@ResponseBody
	public List<TreeEntity> treeGrid(String name,String code,String id,ModelMap model){
		
		List<Depart> departList = null;//部门集合
		List<TreeEntity> treeList = new ArrayList<TreeEntity>();//节点集合
		
		
		//输入了查询条件 输入查询时候id为null
		if(id==null && (!StringUtil.isEmpty(name) || !StringUtil.isEmpty(code))){
			Depart depart = new Depart();
			depart.setName(name);
			depart.setCode(code);
			treeList = departMng.getQueryTree(depart);
		//获取根节点
		}else if(id==null){
			departList = departMng.getRoots();
			for(Depart depart: departList){
				TreeEntity tree = new TreeEntity();
				tree.setId(depart.getId());
				tree.setText(depart.getName());
				tree.setCode(depart.getCode());
				if(departMng.haveChild(depart)){
					tree.setState("closed");
					tree.setHaveChild("1");
				}else{
					tree.setHaveChild("0");
				}
				treeList.add(tree);
				if ("1".equals(tree.getHaveChild())) {
					addchildDepart(tree,departMng.getChild(tree.getId()));
				}
			}
		//获取子节点
		}else if(id!=null){
			departList = departMng.getChild(id);
			for(Depart depart: departList){
				TreeEntity tree = new TreeEntity();
				tree.setId(depart.getId());
				tree.setText(depart.getName());
				tree.setCode(depart.getCode());
				tree.setLeaf(true);
				if(departMng.haveChild(depart)){
					tree.setState("closed");
					tree.setLeaf(false);
					tree.setHaveChild("1");
				}else{
					tree.setHaveChild("0");
				}
				treeList.add(tree);
				if ("1".equals(tree.getHaveChild())) {
					addchildDepart(tree,departMng.getChild(tree.getId()));
				}
			}
		}
		return treeList;
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
			tree.setParentId(ptree.getId());
			if(departMng.haveChild(depart)){
				tree.setHaveChild("1");
				tree.setState("closed");
			}else{
				tree.setHaveChild("0");
			}
			ptree.getChildren().add(tree);
			if ("1".equals(tree.getHaveChild())) {
				addchildDepart(tree,departMng.getChild(tree.getId()));
			}
		}
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id,ModelMap model) {
		model.addAttribute("bean",departMng.findById(id));
		return "/WEB-INF/gwideal_core/depart/edit";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap model,String id) {
		if(!StringUtil.isEmpty(id)) { model.addAttribute("depart",departMng.findById(id)); }
		return "/WEB-INF/gwideal_core/depart/edit";
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			Depart bean=departMng.findById(id);
			if(null!=bean.getChildren() && bean.getChildren().size()>0){
				return getJsonResult(false,"该部门下还有下级部门,不能删除！");
			}
			bean.setFlag(0);
			bean.setUpdator(getUser());
			bean.setUpdateTime(new Date());
			departMng.updateDefault(bean);
		}catch(Exception e) {
			log.error("", e);
			return getErrorJsonResult();
		}
		return getJsonResult(true,"删除成功！");
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(Depart bean){
		try {
			if(departMng.isExist(bean)){
				return getJsonResult(false,"部门名称或代码已存在，请重新填写！");
			}
			if(StringUtil.isEmpty(bean.getId())){
				bean.setCreator(getUser());
			}else{
				bean.setUpdator(getUser());
			}
			if(null==bean.getParent() || StringUtil.isEmpty(bean.getParent().getId())){
				bean.setParent(null);
			}
			if(!StringUtil.isEmpty(bean.getId()) && null!=bean.getParent() && 
			!StringUtil.isEmpty(bean.getParent().getId()) && bean.getId().equals(bean.getParent().getId())){
				return getJsonResult(false,"不能选择自己作为父部门，请重新选择！");
			}
			departMng.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	/*
	 * 跳转到部门选择页面
	 */
	@RequestMapping("/refList/{selectType}")
	public String refList(@PathVariable String selectType, ModelMap model){
		model.addAttribute("selectType", selectType);
		return "/WEB-INF/gwideal_core/depart/refTreeGrid";
	}
	
	/*
	 * 跳转到部门选择页面
	 */
	@RequestMapping("/refUserDepart/{selectType}")
	public String refUserDepart(@PathVariable String selectType, ModelMap model){
		model.addAttribute("selectType", selectType);
		return "/WEB-INF/gwideal_core/depart/refUserDepartGrid";
	}
	
	/*
	 * 跳转到部门选择页面
	 */
	@RequestMapping("/refList2")
	public String refList2(String selectType,String depart_id,String num, ModelMap model){
		model.addAttribute("selectType", selectType);
		model.addAttribute("depart_id", depart_id);
		model.addAttribute("num", num);
		return "/WEB-INF/gwideal_core/depart/refTreeGrid";
	}
	
	/*
	 * 跳转到部门选择页面
	 */
	@RequestMapping("/refOneselfDepart/{selectType}")
	public String refOneselfDepart(@PathVariable String selectType, ModelMap model){
		model.addAttribute("selectType", selectType);
		return "/WEB-INF/gwideal_core/depart/refDepartOneselfTree";
	}
	
	@RequestMapping("/refSupervision")
	public String refSupervision(String selectType,ModelMap model){
		model.addAttribute("selectType", selectType);
		return "/WEB-INF/gwideal_core/depart/refSupervisionTreeGrid";
	}
	
	@RequestMapping(value="/treeSupervisionGrid")
	@ResponseBody
	public List<TreeEntity> treeSupervisionGrid(String name,String code,String id,ModelMap model){
		
		List<Depart> departList = null;//部门集合
		List<TreeEntity> treeList = new ArrayList<TreeEntity>();//节点集合
		//输入了查询条件 输入查询时候id为null
		if(id==null && (!StringUtil.isEmpty(name) || !StringUtil.isEmpty(code))){
			Depart depart = new Depart();
			depart.setName(name);
			depart.setCode(code);
			treeList = departMng.getQuerySupervisionTree(depart);
		//获取根节点
		}else if(id==null){
			departList = departMng.getRoots();
			for(Depart depart: departList){
				TreeEntity tree = new TreeEntity();
				tree.setId(depart.getId());
				tree.setText(depart.getName());
				tree.setCode(depart.getCode());
				tree.setDepartStatus(true);
				if(departMng.haveChild(depart)){
					tree.setState("closed");
					tree.setHaveChild("1");
				}else{
					tree.setHaveChild("0");
				}
				treeList.add(tree);
				//加载直属于该部门的人员；不含子机构的
				List<User> usersByDepart = userMng.getUserByDepartCode(depart.getCode());
				if(usersByDepart!=null&&usersByDepart.size()>0){
					for (User user : usersByDepart) {
						TreeEntity userTree = new TreeEntity();
						userTree.setId(user.getId());
						userTree.setText(user.getName());
						userTree.setCode(depart.getCode());
						userTree.setDepartStatus(false);
						userTree.setHaveChild("0");
						userTree.setLeaf(true);
						treeList.add(userTree);
					}
				}
				if ("1".equals(tree.getHaveChild())) {
					addSupervisionChildDepart(tree,departMng.getChild(tree.getId()));
				}
			}
		//获取子节点
		}else if(id!=null){
			departList = departMng.getChild(id);
			for(Depart depart: departList){
				TreeEntity tree = new TreeEntity();
				tree.setId(depart.getId());
				tree.setText(depart.getName());
				tree.setCode(depart.getCode());
				tree.setLeaf(true);
				tree.setDepartStatus(true);
				if(departMng.haveChild(depart)){
					tree.setState("closed");
					tree.setLeaf(false);
					tree.setHaveChild("1");
				}else{
					tree.setHaveChild("0");
				}
				treeList.add(tree);
				//加载直属于该部门的人员；不含子机构的
				List<User> usersByDepart = userMng.getUserByDepartCode(depart.getCode());
				if(usersByDepart!=null&&usersByDepart.size()>0){
					for (User user : usersByDepart) {
						TreeEntity userTree = new TreeEntity();
						userTree.setId(user.getId());
						userTree.setText(user.getName());
						userTree.setCode(depart.getCode());
						userTree.setDepartStatus(false);
						userTree.setLeaf(true);
						userTree.setHaveChild("0");
						treeList.add(userTree);
					}
				}
				if ("1".equals(tree.getHaveChild())) {
					addSupervisionChildDepart(tree,departMng.getChild(tree.getId()));
				}
			}
		}
		
		return treeList;
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
			tree.setParentId(ptree.getId());
			if(departMng.haveChild(depart)){
				tree.setHaveChild("1");
				tree.setState("closed");
			}else{
				tree.setHaveChild("0");
			}
			ptree.getChildren().add(tree);
			//加载直属于该部门的人员；不含子机构的
			if(tree.isDepartStatus()){
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
				addSupervisionChildDepart(tree,departMng.getChild(tree.getId()));
			}
		}
	}
	
	@RequestMapping("/certificateDepartJson")
	@ResponseBody
	public List<ComboboxJson> certificateDepartJson(String selected){
		List<Depart> list = departMng.getCertificateDepartList();
		return this.getComboboxJson(list,selected);
	}
	@Override
	public List<ComboboxJson> getComboboxJson(List<?> list, String selected) {
		List<ComboboxJson> listCombobox = new ArrayList<ComboboxJson>();
		ComboboxJson comboboxJson = null;
		ComboboxJson comboboxJsonDefault = new ComboboxJson();
		comboboxJsonDefault.setId("");
		comboboxJsonDefault.setCode("");
		comboboxJsonDefault.setText("--请选择--");
		if (StringUtil.isEmpty(selected)) {
			comboboxJsonDefault.setSelected(true);
		}
		listCombobox.add(comboboxJsonDefault);
		if (null != list && list.size() > 0) {
			int t = list.size();
			for (int i = 0; i < t; i++) {
				comboboxJson = new ComboboxJson();
				Depart combobox = (Depart) list.get(i);
				comboboxJson.setId(combobox.getId());
				comboboxJson.setCode(combobox.getCode());
				comboboxJson.setText(combobox.getName());
				if (!StringUtil.isEmpty(selected)) {
					if (selected.equals(combobox.getId())
							|| selected.equals(combobox.getCode())
							|| selected.equals(combobox.getName())) {
						comboboxJson.setSelected(true);
					}
				}
				listCombobox.add(comboboxJson);
			}
		}
		return listCombobox;
	}
}
