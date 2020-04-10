package com.gwideal.core.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.gwideal.core.manager.CategoryMng;
import com.gwideal.core.model.Category;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryMng categoryMng;
	
	@RequestMapping(value="/tree")
	@ResponseBody
	public List<TreeEntity> tree(String id,ModelMap model) {
		// 内容。取所有列表，找出父菜单。
		List<Category> listCategory=null;
		if(null==id){
			listCategory = categoryMng.getRoots();
		}else{
			listCategory = categoryMng.getChild(id);
		}
		List<TreeEntity> list=new ArrayList<TreeEntity>();
		if(null!=listCategory && listCategory.size()>0){
			for(Category d:listCategory){
				TreeEntity ft=new TreeEntity();
				ft.setId(d.getId());
				ft.setText(d.getName());
				ft.setCode(d.getCode());
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
	
	@RequestMapping(value="/treeGrid")
	@ResponseBody
	public List<TreeEntity> treeGrid(String id,String name,String parentId,ModelMap model) {
		// 内容。取所有列表，找出父菜单。
		List<Category> listCategory=null;
		if(null==id){
			listCategory = categoryMng.getCategoryRoots(name,parentId);
		}else{
			listCategory = categoryMng.getCategoryChild(id,name,parentId);
		}
		List<TreeEntity> list=new ArrayList<TreeEntity>();
		if(null!=listCategory && listCategory.size()>0){
			for(Category d:listCategory){
				TreeEntity ft=new TreeEntity();
				ft.setId(d.getId());
				ft.setText(d.getName());
				ft.setCode(d.getCode());
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
	
	@RequestMapping("/list")
	public String list() {
		return "/WEB-INF/gwideal_core/category/list";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap model,String code) {
		if(!StringUtil.isEmpty(code)) { model.addAttribute("category",categoryMng.findUniqueByProperty("code",code)); }
		return "/WEB-INF/gwideal_core/category/edit";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id,ModelMap model) {
		model.addAttribute("bean",categoryMng.findById(id));
		return "/WEB-INF/gwideal_core/category/edit";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(Category bean,String sort,String order,Integer page,Integer rows,ModelMap model){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=categoryMng.list(bean,sort,order,page,rows);
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(Category bean,ModelMap model) {
		try {
			if(categoryMng.isExist(bean)){
				return getJsonResult(false,"字典类型代码已存在，请重新填写！");
			}
			if(null==bean.getParent() || StringUtil.isEmpty(bean.getParent().getId())){
				bean.setParent(null);
			}
			if(StringUtil.isEmpty(bean.getId())){
				bean.setCreator(getUser());
			}else{
				bean.setUpdator(getUser());
			}
			categoryMng.save(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	@RequestMapping("/refCategoryGrid/{selectType}")
	public String refUserDepart(@PathVariable String selectType, ModelMap model){
		model.addAttribute("selectType", selectType);
		return "/WEB-INF/gwideal_core/category/refCategoryGrid";
	}

	/**
	 * 根据parentCode获取子项
	 * @param parentCode 父节点编码
	 * @param selected 选中值
	 * @return
	 */
	@RequestMapping("/childByCode")
	@ResponseBody
	public List<ComboboxJson> childByCode(String parentCode,String selected){
		List<Category> list=null;
		if(!StringUtil.isEmpty(parentCode)){
			list=categoryMng.getChildByParentCode(parentCode);
		}
		
		return getComboboxJson(list,selected);
	}

	/**
	 * 根据parentId获取子项
	 * @param parentId 父节点编码
	 * @param selected 选中值
	 * @return
	 */
	@RequestMapping("/childById")
	@ResponseBody
	public List<ComboboxJson> childById(String parentId,String selected){
		List<Category> list=null;
		if(!StringUtil.isEmpty(parentId)){
			list=categoryMng.getChild(parentId);
		}
		return getComboboxJson(list,selected);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			categoryMng.delete(id, getUser());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
}
