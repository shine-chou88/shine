package com.gwideal.core.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.RoleMng;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.Role;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	
	@Autowired
	private FunctionMng functionMng;
	
	@Autowired
	private RoleMng roleMng;

	@RequestMapping("/list")
	public String list() {
		return "/WEB-INF/gwideal_core/role/role_list";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(Role bean,String sort,String order,Integer page,Integer rows){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=roleMng.list(bean,sort,order,page,rows);
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id,ModelMap model) {
		model.addAttribute("bean",roleMng.findById(id));
		return "/WEB-INF/gwideal_core/role/role_edit";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "/WEB-INF/gwideal_core/role/role_edit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(Role bean,String funcIds,ModelMap model) {
		try {
			if(roleMng.isExist(bean)){
				return getJsonResult(false,"角色代码或角色名称已存在,请重新填写！");
			}
			roleMng.save(bean,funcIds,getUser());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return getJsonResult(false,"保存失败,请联系管理员！");
		}
		return getJsonResult(true,"保存成功！");
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable String id,ModelMap model){
		model.addAttribute("bean", roleMng.findById(id));
		return "/WEB-INF/gwideal_core/role/role_view";
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			Role bean=roleMng.findById(id);
			if(null!=bean.getUsers() && bean.getUsers().size()>0){
				return getJsonResult(false,"该角色下存在用户,不能删除！");
			}
			bean.setFlag(0);
			bean.setUpdator(getUser());
			bean.setUpdateTime(new Date());
			roleMng.updateDefault(bean);
		}catch(Exception e) {
			log.error("", e);
			return getErrorJsonResult();
		}
		return getJsonResult(true,"删除成功！");
	}
	
	@RequestMapping(value="/functionTree")
	public void functionTree(String roleId,HttpServletResponse res) {
		try {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter pw=res.getWriter();
			pw.write(functionMng.getTree(roleId));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
