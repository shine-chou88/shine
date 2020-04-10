package com.gwideal.sms.controller;

import java.util.Arrays;
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
import com.gwideal.sms.entity.SmsPool;
import com.gwideal.sms.manager.SmsPoolMng;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/smsPool")
public class SmsPoolController extends BaseController {
	
	@Autowired
	private SmsPoolMng smsPoolMng;

	//列表
	@RequestMapping("/list")
	public String list(SmsPool bean,String sort,String order,Integer page,Integer rows,ModelMap model){
		
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
    	
    	Pagination p = smsPoolMng.list(bean,sort,order,page,rows,hasRole("QU_ROLE"),isStreetRole(),getUser());
    	
    	model.addAttribute("page", page);
    	model.addAttribute("pagination",p);
    	model.addAttribute("bean",bean);
    	
		return "/WEB-INF/view/sms/list";
	}
	
	@RequestMapping(value="/smsPoolJson")
	@ResponseBody
	public JsonPagination listData(SmsPool bean, Integer page ,Integer rows,String sort,String order){
		Pagination p = smsPoolMng.list(bean,sort,order,page,rows,hasRole("QU_ROLE"),isStreetRole(),getUser());
		return paginationJson(p, page);
	}
	
	//批量删除
	@RequestMapping("/batchDelete/{ids}")
	@ResponseBody
	public Result batchDelete(ModelMap model, @PathVariable String ids){
		try {
			smsPoolMng.delete(Arrays.asList(ids.substring(0, ids.length()-1).split(",")), getUser());
			return getJsonResult(true, Result.deleteSuccessMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return getJsonResult(false, Result.deleteFailureMessage);
		}
	}
	
	
	//批量取消
	@RequestMapping("/batchCancel/{ids}")
	@ResponseBody
	public Result deletion(ModelMap model, @PathVariable String ids){
		try {
			smsPoolMng.cancel(Arrays.asList(ids.substring(0, ids.length()-1).split(",")), getUser());
			return getJsonResult(true, "取消成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return getJsonResult(true, "取消发送失败，请联系管理员！");
		}
		
	}
}
