package com.gwideal.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.activiti.manager.ProcessMng;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.IndexMng;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	private static final long serialVersionUID = -5083788326183027148L;
	protected static final Logger log=LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private IndexMng indexMng;
	@Autowired
	private ProcessMng processMng;
	
	@RequestMapping("/listUnReadMsg")
	public String list(){
		return "/WEB-INF/view/unReadMsg_list";
	}
	
	@RequestMapping("/unReadMsgJsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(){
		Pagination p=indexMng.getUnReadMessagePagination(getUser());
		return getJsonPagination(p,1);
	}
	
	@RequestMapping(value = "/indexPart", method = RequestMethod.GET)
    public String indexPart(ModelMap model) {
        return "/WEB-INF/view/index_part";
    }
	
	@RequestMapping(value = "/currentTask", method = RequestMethod.GET)
    public String currentTask(ModelMap model) {
        model.addAttribute("listCurrentTask",processMng.listCurrentTask(getUser()));
        return "/WEB-INF/view/index_task";
    }
	
	/**过期提醒
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/xqtx", method = RequestMethod.GET)
    public String xqtx(ModelMap model) {
//		Message message=new Message();
//		message.setType("expire");
        model.addAttribute("xqtxList",indexMng.expireRemindList(getUser()));
        return "/WEB-INF/view/index_xqtx";
    }
	
	/**
	 * 归还提醒
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ghtx", method = RequestMethod.GET)
    public String ghtx(ModelMap model) {
//		Message message=new Message();
//		message.setType("not_back");
        model.addAttribute("ghtxList",indexMng.notBackReminList(getUser()));
        return "/WEB-INF/view/index_ghtx";
    }
	
	@RequestMapping("/getUnReadMsg")
	@ResponseBody
	public Result getUnReadMsg(){
		try {
			int t=indexMng.getUnReadMessage(getUser());
			return getJsonResult(true,String.valueOf(t));
		} catch (Exception e) {
			// TODO: handle exception
			return getJsonResult(false,"出现错误，请联系管理员！");
		}
	}
}
