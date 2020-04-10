package com.gwideal.cms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.util.FileUpLoadUtil;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.LookupsMng;
import com.gwideal.core.manager.SysLogMng;
import com.gwideal.core.manager.SysParamMng;
import com.gwideal.core.model.User;
import com.gwideal.fckeditor.UploadRule;
import com.gwideal.cms.entity.Article;
import com.gwideal.cms.entity.ArticleReadRecord;
import com.gwideal.cms.manager.ArticleMng;
import com.gwideal.cms.manager.ArticleReadRecordMng;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/xxfb")
public class ArticleController extends BaseController{
	private static final ResourceBundle res=ResourceBundle.getBundle("fckeditor");
	
	private static final ResourceBundle resUpload=ResourceBundle.getBundle("upload");
	
	@Autowired
	private ArticleMng articleMng;
	@Autowired
	private ArticleReadRecordMng articleReadRecordMng;
	@Autowired
	private LookupsMng lookupsMng;
	@Autowired
	private AttachmentMng attachmentMng;
	@Autowired
	private SysLogMng sysLogMng;
	@Autowired
	private SysParamMng sysParamMng;
	
	@RequestMapping("/list")
	public String list(String comeFrom,ModelMap model) {
		model.addAttribute("comeFrom", comeFrom);
    	model.addAttribute("typeList",lookupsMng.getCmsLookupsByCategoryCode(comeFrom));
		return "/WEB-INF/view/xxfb/list";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(Article bean,String comeFrom,String sort,String order,Integer page,Integer rows){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=articleMng.list(bean,comeFrom,sort,order,page,rows,hasFunction("/viewAllXxfb"),isStreetRole(),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/add")
	public String add(String comeFrom,ModelMap model){
		addUploadRule();
		model.addAttribute("releaseTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		model.addAttribute("typeList",lookupsMng.getCmsLookupsByCategoryCode(comeFrom));
		model.addAttribute("comeFrom", comeFrom);
		return "/WEB-INF/view/xxfb/add";
	}
	
	/**
	 * fckeditor添加上传规则
	 */
	private void addUploadRule() {
		UploadRule rule = new UploadRule(res.getString("fckeditor.file.rootPath"),res.getString("fckeditor.file.pathPrefix"),true);
		request.getSession().setAttribute(UploadRule.KEY,rule);
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id,String comeFrom,ModelMap model){
		addUploadRule();
		model.addAttribute("typeList",lookupsMng.getCmsLookupsByCategoryCode(comeFrom));
		Article bean=articleMng.findById(id);
		List<String> sendList = articleMng.getArticleReceiverId(bean); //发布对象列表
		model.addAttribute("sendList",sendList.toString());
		model.addAttribute("listAtt",attachmentMng.list(bean));
		model.addAttribute("bean",bean);
		model.addAttribute("comeFrom", comeFrom);
		return "/WEB-INF/view/xxfb/edit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(Article bean,String departIds,@RequestParam(value="xxfbFiles",required=false) MultipartFile[] xxfbFiles,@RequestParam(value="xxfbPlayFiles",required=false) MultipartFile[] xxfbPlayFiles,String[] originalFileName,String attachmentId){
		try {
			//首页飘窗只能一个
			if(null!=bean && null!=bean.getIndexFloat() && bean.getIndexFloat().booleanValue()){
				if(articleMng.isExistIndexFloat(bean,hasRole("QU_ROLE"),isStreetRole(),getUser())){
					return getJsonResult(false,"首页飘窗只能选择一个,已存在首页飘窗的内容！");
				}
			}
			articleMng.save(bean,departIds,xxfbFiles,xxfbPlayFiles,originalFileName,getSavePath(),getUser(),attachmentId);
			request.getSession().removeAttribute(UploadRule.KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return getJsonResult(false,"保存失败,请联系管理员！");
		}
		return getJsonResult(true,"保存成功！");
	}
	
	@RequestMapping("/saveSuggestion")
	@ResponseBody
	public Result saveSuggestion(ArticleReadRecord record,@RequestParam(value="suggestionFiles",required=false) MultipartFile[] suggestionFiles,ModelMap model){
		if(null!=record && !StringUtil.isEmpty(record.getSuggestion())){
			articleReadRecordMng.save(record,suggestionFiles,getSavePath(),getUser());
		}else{
			return getJsonResult(true,"请输入反馈意见！");
		}
		return getJsonResult(true,"保存成功！");
	}
	
	private String getSavePath(){
		String filePath = res.getString("upload.save_path");
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		return filePath;
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable String id,ModelMap model) {
		//获得查看记录列表
		Article bean=articleMng.findById(id);
		String recList = articleMng.getArticleReceiverName(bean).toString();
		model.addAttribute("recList",recList.toString().substring(1,recList.length()-1));
		List<ArticleReadRecord> listRecord=articleReadRecordMng.list(bean.getId(),null);
		model.addAttribute("listRecord",listRecord);
		//如果该信息已发布
		if(null!=bean.getIsRelease() && bean.getIsRelease().booleanValue()){
			//增加已读次数
			bean.setIsRead(true);
			if(null==bean.getReadCount()){
				bean.setReadCount(1);
			}else{
				bean.setReadCount(bean.getReadCount().intValue()+1);
			}
			articleMng.updateDefault(bean);
			List<ArticleReadRecord> list=articleReadRecordMng.list(bean.getId(),getUser().getId());
			//如果该用户之前没有阅读记录 保存本次阅读记录
			if(null==list || 0==list.size()){
				ArticleReadRecord arr=new ArticleReadRecord();
				arr.setArticleId(bean.getId());
				arr.setCreator(getUser());
				articleReadRecordMng.save(arr);
			}
			//未读人员
			boolean isWwgzTzgg=lookupsMng.isWwgzTzgg(bean.getType().getId(),"CMS-WWGZ-TYPE");
			List<User> listUnReadUser=articleMng.listUnReadUser(bean.getId(),bean.getDeparts(),isWwgzTzgg);
			if(null!=listUnReadUser && listUnReadUser.size()>0){
				List<String> listUnReadDepartName=new ArrayList<String>();
				for(User u:listUnReadUser){
					if(!StringUtil.isEmpty(u.getDepartName()) && !listUnReadDepartName.contains(u.getDepartName())){
						listUnReadDepartName.add(u.getDepartName());
					}
				}
				model.addAttribute("listUnReadDepartName", listUnReadDepartName);
				model.addAttribute("listUnReadUser", listUnReadUser);
			}
		}
		List<Attachment> attList = attachmentMng.list(bean);
		if (null != attList && attList.size() >0) {
			List<String> attIds = new ArrayList<String>();
			for (Attachment attachment : attList) {
				attIds.add(attachment.getId());
			}
			model.addAttribute("listLog",sysLogMng.findByAttIds(attIds));
		}
		model.addAttribute("listAtt",attList);
		model.addAttribute("bean",bean);
		return "/WEB-INF/view/xxfb/view";
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			Article bean=articleMng.findById(id);
			bean.setFlag(0);
			bean.setUpdateTime(new Date());
			bean.setUpdator(getUser());
			articleMng.updateDefault(bean);
		}catch(Exception e) {
			e.printStackTrace();
			return getJsonResult(false,"删除失败,请联系管理员！");
		}
		return getJsonResult(true,"删除成功！");
	}
	
	@RequestMapping("/release/{id}")
	@ResponseBody
	public Result release(@PathVariable String id) {
		try {
			Article bean=articleMng.findById(id);
			bean.setUpdateTime(new Date());
			bean.setUpdator(getUser());
			if(null!=bean.getIsRelease()){
				if(bean.getIsRelease().booleanValue()){
					bean.setIsRelease(false);
				}else{
					bean.setIsRelease(true);
				}
			}else{
				bean.setIsRelease(true);
			}
			bean.setReleaseUser(getUser());
			articleMng.updateDefault(bean);
		}catch(Exception e) {
			e.printStackTrace();
			return getJsonResult(false,"操作失败,请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
}
