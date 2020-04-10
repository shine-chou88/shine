package com.gwideal.swj.work.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gwideal.common.page.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gwideal.swj.work.entity.PublicAdjust;
import com.gwideal.swj.work.manager.PublicAdjustMng;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.web.BaseController;
import org.springframework.ui.ModelMap;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/publicAdjust")
public class PublicAdjustController extends BaseController{
	protected static final Logger log=LoggerFactory.getLogger(PublicAdjust.class);
	@Autowired
	private PublicAdjustMng publicAdjustMng;
	
	@RequestMapping("/list")
	public String list(Model model){
		return "/WEB-INF/view/swj/work/publicAdjust/list";
	}
	
	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(PublicAdjust bean,String sort,String order,Integer page,Integer rows,ModelMap model){
    	if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=publicAdjustMng.list(bean,sort,order,page,rows,hasRole("QU_ROLE"),isStreetRole(),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    return "/WEB-INF/view/swj/work/publicAdjust/edit";
	}

	@RequestMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("bean",publicAdjustMng.get(id));
	    return "/WEB-INF/view/swj/work/publicAdjust/edit";
	}
	
	@RequestMapping("/view/{id}")
	String view(@PathVariable("id") String id,Model model){
		model.addAttribute("bean",publicAdjustMng.get(id));
	    return "/WEB-INF/view/swj/work/publicAdjust/view";
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Result save(PublicAdjust bean){
		try {
			publicAdjustMng.save(bean,getUser());
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
			publicAdjustMng.delete(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	@RequestMapping(value="/adjustConfirm/{id}")
	@ResponseBody
	public Result adjustConfirm(@PathVariable String id){
		try {
			publicAdjustMng.adjustConfirm(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
}
