package com.gwideal.core.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;
import com.gwideal.common.page.ComboboxJson;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.security.CodeHelper;
import com.gwideal.common.util.RSAUtils;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.RoleMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.User;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private static final ResourceBundle res=ResourceBundle.getBundle("upload");
	
	@Autowired
	private UserMng userMng;
	
	@Autowired
	private RoleMng roleMng;
	
	@Autowired
	private FunctionMng functionMng;
	
	@Autowired
	private AttachmentMng attachmentMng;
	
	@RequestMapping(value="/functionTree")
	public void functionTree(String userId,HttpServletResponse res) {
		try {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter pw=res.getWriter();
			pw.write(functionMng.getUserTree(userId));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/refInspectorList")
	public String refInspectorList(ModelMap model){
		return "/WEB-INF/gwideal_core/user/ref_inspector_list";
	}
	
	@RequestMapping(value="/refInspectorJsonPagination")
	@ResponseBody
	public JsonPagination refInspectorJsonPagination(User bean,String sort,String order,Integer page,Integer rows){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=userMng.refInspectorList(bean,sort,order,page,rows,hasRole("QU_ROLE"),isStreetRole(),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/list")
	public String list(ModelMap model) {
		return "/WEB-INF/gwideal_core/user/user_list";
	}
	
	@RequestMapping(value="/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(User bean,String departId,String jddm,String jwdm,String sort,String order,Integer page,Integer rows,ModelMap model){
		if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=userMng.list(bean,departId,jddm,jwdm,sort,order,page,rows);
		model.addAttribute("jddm",jddm);
		model.addAttribute("jwdm",jwdm);
		model.addAttribute("departId",departId);
		return getJsonPagination(p,page);
	}
	
	/**
	 * 短信接收人选择列表
	 */
	@RequestMapping(value="/jsonPaginationMsgReceiver")
	@ResponseBody
	public JsonPagination jsonPaginationMsgReceiver(User bean,String departId,String jddm,String jwdm,String sort,String order,Integer page,Integer rows,ModelMap model){
		if(page==null){page=1;}
		if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=userMng.list(bean,departId,jddm,jwdm,sort,order,page,rows);
		//剔除没有手机号的用户数据
		for(int i=0,k=p.getList().size(); i<k; i++){
			User user = (User) p.getList().get(i);
			if(StringUtil.isEmpty(user.getMobileNo())){
				 p.getList().remove(i);
				 i--;
				 k--;
			}
		}
		model.addAttribute("jddm",jddm);
		model.addAttribute("jwdm",jwdm);
		model.addAttribute("departId",departId);
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id,ModelMap model) {
		model.addAttribute("bean",userMng.findById(id));
		model.addAttribute("listRole",roleMng.getAll());
		return "/WEB-INF/gwideal_core/user/user_edit";
	}
	
	@RequestMapping("/add")
	public String add(String departId,ModelMap model) {
		model.addAttribute("departId",departId);
		model.addAttribute("listRole",roleMng.getAll());
		return "/WEB-INF/gwideal_core/user/user_edit";
	}
	
	@RequestMapping("/editPwd")
	public String editPwd() {
		return "/WEB-INF/gwideal_core/user/user_editPwd";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(User bean,String roleIds,String funcIds,ModelMap model) {
		try {
			bean.setAccountNo(RSAUtils.decrypt(bean.getAccountNo()));
			if(StringUtil.isEmpty(bean.getId())){
				bean.setPassword(RSAUtils.decrypt(bean.getPassword()));
			}
			if(userMng.isExist(bean)){
				return getJsonResult(false,"该帐号已存在，请重新填写！");
			}
			userMng.save(bean,roleIds,funcIds,getUser());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			User bean=userMng.findById(id);
			bean.setFlag(0);
			bean.setUpdateTime(new Date());
			bean.setUpdator(getUser());
			userMng.updateDefault(bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	/**
	 * 修改密码
	 * @param userId
	 * @param originalPwd
	 * @param nowPwd
	 * @param confirmPwd
	 * @return
	 */
	@RequestMapping("/saveEditPwd")
	@ResponseBody
	public Result saveEditPwd(String userId,String originalPwd,String newPwd,String confirmNewPwd) {
		try {
			originalPwd=RSAUtils.decrypt(originalPwd);
			newPwd=RSAUtils.decrypt(newPwd);
			confirmNewPwd=RSAUtils.decrypt(confirmNewPwd);
			User bean=userMng.findById(userId);
			if(!CodeHelper.encryptPassword(originalPwd).equals(bean.getPassword())){
				return getJsonResult(false,"原始密码填写不正确，请重新填写！");
			}
			if(!newPwd.equals(confirmNewPwd)){
				return getJsonResult(false,"两次密码填写不一致，请重新填写！");
			}
			bean.setPassword(CodeHelper.encryptPassword(newPwd));
			bean.setUpdateTime(new Date());
			bean.setUpdator(getUser());
			userMng.updateDefault(bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	/**
	 * 锁定/解锁 用户
	 * @param userId
	 * @param originalPwd
	 * @param nowPwd
	 * @param confirmPwd
	 * @return
	 */
	@RequestMapping("/lock/{userId}")
	@ResponseBody
	public Result lock(@PathVariable String userId) {
		User bean=userMng.findById(userId);
		try {
			if(!StringUtil.isEmpty(bean.getIslocked()) && "TRUE".equals(bean.getIslocked())){
				bean.setIslocked("FALSE");
			}else{
				bean.setIslocked("TRUE");
			}
			bean.setUpdateTime(new Date());
			bean.setUpdator(getUser());
			userMng.updateDefault(bean);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	/**
	 * 重置密码
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("/reSetPwd/{userId}")
	@ResponseBody
	public Result reSetPwd(@PathVariable String userId,ModelMap model) {
		try {
			userMng.reSetPwd(userId,getUser());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable String id,ModelMap model){
		model.addAttribute("user", userMng.findById(id));
		return "/WEB-INF/gwideal_core/user/user_view";
	}

	/**
	 * 获取有某个角色的人员
	 * @param roleCode
	 * @return
	 */
	@RequestMapping("/usersJson")
	@ResponseBody
	public List<ComboboxJson> usersJson(String roleCode,String selected){
		List<User> list=userMng.listAuditor(roleCode);
		return getComboboxJson(list,selected);
	}
	
	/**
	 * 部门审批人，工会主席（正处）在“局组织人事处（老干部处）”为副处长，需要特殊处理
	 * @return
	 */
	@RequestMapping("/listAll")
	@ResponseBody
	public List<ComboboxJson> listAll(String selected){
		List<User> list=userMng.getALL();
		return getComboboxJson(list,selected);
	}
	
	@RequestMapping("/personLiableForCheckbox")
	@ResponseBody
	public List<User> personLiableForCheckbox(String jwhId,String selected){
		List<User> list=userMng.getChildByJwhCode(jwhId);
		return list;
	}
	
	/**
	 * 检查手机号是否唯一
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/mobileCheck")
	@ResponseBody
	public Result mobileCheck(String mobileNo,String id){
		try {
			boolean flag=userMng.checkMobileNo(mobileNo,id);
			if(flag){
				return getJsonResult(true, "手机号在系统中已存在，请更换手机号！");
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return getJsonResult(false, null);
	}
	
	@RequestMapping(value="/refCertificateOwnerList")
	public String refCertificateOwnerList(ModelMap model,String businessType){
		model.addAttribute("businessType", businessType);
		return "/WEB-INF/gwideal_core/user/ref_certificateOwner_list";
	}
	
	@RequestMapping(value="/innerUsePage")
	public String innerUsePage(ModelMap model){
		return "/WEB-INF/gwideal_core/user/user_innerUse_list";
	}
	
	@RequestMapping(value="/uploadSign/{id}")
	public String uploadSign(@PathVariable String id,ModelMap model){
		User bean = userMng.findById(id);
		model.addAttribute("bean", bean);
		List<Attachment> list = attachmentMng.list(bean);
		if(list!=null&&list.size()>0){
			model.addAttribute("signAtt", list.get(0));
		}
		return "/WEB-INF/gwideal_core/user/user_edit_sign";
	}
	
	@RequestMapping("/saveUploadSign")
	@ResponseBody
	public Result saveUploadSign(String userId,@RequestParam(value="signFile",required=false) MultipartFile signFile) {
		try {
			if(!StringUtil.isEmpty(userId)){
				User user = userMng.findById(userId);
				String fileType = signFile.getOriginalFilename().substring(signFile.getOriginalFilename().lastIndexOf(".")+1);
				if(!fileType.equalsIgnoreCase("jpg")&&!fileType.equalsIgnoreCase("png")&&!fileType.equalsIgnoreCase("jpeg")){
					return getJsonResult(false,"请选择图片！");
				}
				if(user!=null){
					//保存上传附件
					attachmentMng.upload(user,null,signFile,getFileSavePath(),user);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	private String getFileSavePath(){
		String filePath = res.getString("upload.save_path");
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		return filePath;
	}
}
