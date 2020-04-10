package com.gwideal.swj.certificate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.web.BaseController;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/certificateOperateLog")
public class CertificateOperateLogController extends BaseController{
	protected static final Logger log=LoggerFactory.getLogger(CertificateOperateLog.class);
	@Autowired
	private CertificateOperateLogMng certificateOperateLogMng;
	
	@RequestMapping("/list")
	public String list(Model model){
		return "/WEB-INF/view/swj/certificate/certificateOperateLog/list";
	}
	
	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(CertificateOperateLog bean,String sort,String order,Integer page,Integer rows,ModelMap model){
    	if(page==null){page=1;}
    	if(rows==null){rows=100;}
    	//目前只查因私
    	//bean.setBusinessType("private");
		Pagination p=certificateOperateLogMng.list(bean,sort,order,page,rows,hasRole("QU_ROLE"),isStreetRole(),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/add")
	public String add(){
	    return "/WEB-INF/view/swj/certificate/certificateOperateLog/edit";
	}

	@RequestMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("bean",certificateOperateLogMng.get(id));
	    return "/WEB-INF/view/swj/certificate/certificateOperateLog/edit";
	}
	
	@RequestMapping("/view/{id}")
	String view(@PathVariable("id") String id,Model model){
		model.addAttribute("bean",certificateOperateLogMng.get(id));
	    return "/WEB-INF/view/swj/certificate/certificateOperateLog/view";
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Result save(CertificateOperateLog bean){
		try {
			certificateOperateLogMng.save(bean,getUser());
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
			certificateOperateLogMng.delete(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
}
