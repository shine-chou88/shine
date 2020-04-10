package com.gwideal.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.common.entity.TreeEntity;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.util.SelectTreeUtils;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.model.Function;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/function")
public class FunctionController extends BaseController {
	
	@RequestMapping("/main")
	public String main() {
		return "/WEB-INF/gwideal_core/function/main";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(Function bean,Long pid,ModelMap model){
		List<Function> list=new ArrayList<Function>();
		if (pid == null) {
			list = functionMng.getRoots();
		} else {
			Function parent=functionMng.findById(pid);
			if(null!=parent && null!=parent.getId()){
				if(null!=parent.getChild() && parent.getChild().size()>0){
					list = new ArrayList<Function>(parent.getChild());
				}else{
					list.add(parent);
				}
			}
		}
		model.addAttribute("pid",pid);
		Pagination p=new Pagination(1,50,list.size(),list);
		return getJsonPagination(p,1);
	}
	
	@RequestMapping("/list")
	public String list(Long pid,ModelMap model) {
		model.addAttribute("pid", pid);
		return "/WEB-INF/gwideal_core/function/list";
	}
	
	@RequestMapping(value="/tree")
	@ResponseBody
	public List<TreeEntity> tree(Long id,ModelMap model) {
		// 内容。取所有列表，找出父菜单。
		List<Function> listFunction=null;
		if(null==id){
			listFunction = functionMng.getRoots();
		}else{
			listFunction = functionMng.getChild(id);
		}
		List<TreeEntity> list=new ArrayList<TreeEntity>();
		if(null!=listFunction && listFunction.size()>0){
			for(Function f:listFunction){
				TreeEntity ft=new TreeEntity();
				ft.setId(f.getId().toString());
				ft.setText(f.getName());
				if(null!=f.getChild() && f.getChild().size()>0){
					ft.setState("closed");
				}else{
					ft.setLeaf(true);
				}
				list.add(ft);
			}
		}
		return list;
	}
	
	@RequestMapping("/add")
	public String add(Long pid,ModelMap model) {
		Function parent=null;
		if (pid != null) {
			parent = functionMng.findById(pid);
		}
		// 功能菜单
		model.addAttribute("pid",pid);
		model.addAttribute("parent",parent);
		return "/WEB-INF/gwideal_core/function/add";
	}
	@RequestMapping("/save")
	@ResponseBody
	public Result save(Function bean) {
		try {
			if(null==bean.getId()){
				bean.setCreator(getUser());
			}else{
				bean.setUpdator(getUser());
			}
			functionMng.save(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return getJsonResult(false,"保存失败,请联系管理员！");
		}
		return getJsonResult(true,"保存成功！");
	}
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id,ModelMap model) {
		model.addAttribute("bean",functionMng.findById(id));
		model.addAttribute("listParent",SelectTreeUtils.webTree(functionMng.getRoots()));
		return "/WEB-INF/gwideal_core/function/edit";
	}
	@RequestMapping("/update")
	@ResponseBody
	public Result update(Function bean) {
		try {
			if(null==bean.getId()){
				bean.setCreator(getUser());
			}else{
				bean.setUpdator(getUser());
			}
			functionMng.updateDefault(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return getJsonResult(false,"保存失败,请联系管理员！");
		}
		return getJsonResult(true,"保存成功！");
	}

	public String priorityUpdate(Long[] wids,Integer[] prioritys,Long pid,ModelMap model) {
		for (int i = 0; i < wids.length; i++) {
			Function f = functionMng.findById(wids[i]);
			f.setPriority(prioritys[i]);
			functionMng.update(f);
		}
		return list(pid,model);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id,Long ids[]) {
		try {
			if (id != null) {
				Function ft = functionMng.findById(id);
				if(null!=ft.getRoles() && ft.getRoles().size()>0){
					return getJsonResult(false,"记录已被角色引用,不能删除！");
				}else{
					ft.setFlag(0);
					ft.setUpdateTime(new Date());
					ft.setUpdator(getUser());
					functionMng.updateDefault(ft);
				}
			} else {
				boolean flag=true;
				for (int i = 0; i < ids.length; i++) {
					Function ft = functionMng.findById(ids[i]);
					if(null!=ft.getRoles() && ft.getRoles().size()>0){
						flag=false;
						break;
					}
				}
				if(flag){
					functionMng.deleteById(ids);
				}else{
					return getJsonResult(false,"记录已被角色引用,不能删除！");
				}
			}
		}catch(DataIntegrityViolationException e) {
			return getJsonResult(false,"记录已被角色引用,不能删除！");
		}catch(Exception e) {
			return getJsonResult(false,"删除失败,请联系管理员！");
		}
		return getJsonResult(true,"删除成功！");
	}

	@Autowired
	private FunctionMng functionMng;
}
