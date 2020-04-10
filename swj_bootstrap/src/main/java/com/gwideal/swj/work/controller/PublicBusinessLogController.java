package com.gwideal.swj.work.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gwideal.common.page.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gwideal.swj.work.entity.PublicBusinessLog;
import com.gwideal.swj.work.manager.PublicBusinessLogMng;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.web.BaseController;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/publicBusinessLog")
public class PublicBusinessLogController extends BaseController{
	protected static final Logger log=LoggerFactory.getLogger(PublicBusinessLog.class);
	@Autowired
	private PublicBusinessLogMng publicBusinessLogMng;
	
	@RequestMapping("/list")
	public String list(Model model){
		return "/WEB-INF/view/swj/work/publicBusinessLog/list";
	}
	
	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(PublicBusinessLog bean,String sort,String order,Integer page,Integer rows,ModelMap model){
    	if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=publicBusinessLogMng.list(bean,sort,order,page,rows,hasRole("QU_ROLE"),isStreetRole(),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/add")
	public String add(){
	    return "/WEB-INF/view/swj/work/publicBusinessLog/edit";
	}

	@RequestMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("bean",publicBusinessLogMng.get(id));
	    return "/WEB-INF/view/swj/work/publicBusinessLog/edit";
	}
	
	@RequestMapping("/view/{id}")
	String view(@PathVariable("id") String id,Model model){
		model.addAttribute("bean",publicBusinessLogMng.get(id));
	    return "/WEB-INF/view/swj/work/publicBusinessLog/view";
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Result save(PublicBusinessLog bean){
		try {
			publicBusinessLogMng.save(bean,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			publicBusinessLogMng.delete(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
}
