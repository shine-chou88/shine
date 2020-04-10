package com.gwideal.swj.work.controller;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificationStatus;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.work.entity.PublicBusiness;
import com.gwideal.swj.work.entity.PublicBusinessLog;
import com.gwideal.swj.work.manager.PublicBusinessLogMng;
import com.gwideal.swj.work.manager.PublicBusinessMng;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 因公业务
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/publicBusiness")
public class PublicBusinessController extends BaseController{
	protected static final Logger log=LoggerFactory.getLogger(PublicBusiness.class);
	@Autowired
	private PublicBusinessMng publicBusinessMng;
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private PublicBusinessLogMng publicBusinessLogMng;
	
	/**
	 * 领用信息列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/useRegisterList")
	public String useRegisterList(Model model){
		model.addAttribute("isConfirmer", getUser().hasRole("PUBLIC_JBGSGZRY"));
		model.addAttribute("businessType", "use");
		return "/WEB-INF/view/swj/work/publicBusiness/useRegisterList";
	}
	
	/**
	 * 归还信息列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/backRegisterList")
	public String backRegisterList(Model model){
		model.addAttribute("isConfirmer", getUser().hasRole("PUBLIC_JBGSGZRY"));
		model.addAttribute("businessType", "back");
		return "/WEB-INF/view/swj/work/publicBusiness/backRegisterList";
	}
	
	/**
	 * 领用扫描列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/scanUseRegisterList")
	public String scanUseRegisterList(Model model){
		return "/WEB-INF/view/swj/work/publicBusiness/scanUseRegisterList";
	}
	
	/**
	 * 归还扫描列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/scanBackRegisterList")
	public String scanBackRegisterList(Model model){
		return "/WEB-INF/view/swj/work/publicBusiness/scanBackRegisterList";
	}
	
	/**
	 * 
	 * @param bean
	 * @param sort
	 * @param order
	 * @param page
	 * @param rows
	 * @param model
	 * @return
	 */
	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(PublicBusiness bean,String sort,String order,Integer page,Integer rows,ModelMap model){
    	if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=publicBusinessMng.list(bean,sort,order,page,rows,hasRole("PUBLIC_JBGSGZRY"),isStreetRole(),getUser());
		return getJsonPagination(p,page);
	}
	
	/**
	 * 借用登记页面
	 * @return
	 */
	@RequestMapping("/useRegister")
	public String add(Model model){
		model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    return "/WEB-INF/view/swj/work/publicBusiness/useRegister";
	}
	
	/**
	 * 保存或提交借用登记
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/saveUseRegister")
	@ResponseBody
	public Result saveUseRegister(PublicBusiness bean){
		try {
			publicBusinessMng.saveOrSubmitUseRegister(bean,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}

	/**
	 * 修改借用登记
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editUseRegister/{id}")
	public String edit(@PathVariable("id") String id,Model model){
		PublicBusiness bean = publicBusinessMng.get(id);
		model.addAttribute("bean",bean);
		return "/WEB-INF/view/swj/work/publicBusiness/useRegister";
	}
	
	/**查看
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/view/{id}")
	String view(@PathVariable("id") String id,Model model,String type){
		model.addAttribute("bean",publicBusinessMng.get(id));
		if("use".equals(type)){
			return "/WEB-INF/view/swj/work/publicBusiness/view_use";
		}else if("back".equals(type)){
			return "/WEB-INF/view/swj/work/publicBusiness/view_back";
		}
		return "500";
	    
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Result save(PublicBusiness bean){
		try {
			publicBusinessMng.save(bean,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable String id) {
		try {
			publicBusinessMng.delete(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	
	/**
	 * 归还页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/backRegister/{id}")
	public String backRegister(@PathVariable("id") String id,Model model){
		PublicBusiness bean = publicBusinessMng.get(id);
		model.addAttribute("bean",bean);
		return "/WEB-INF/view/swj/work/publicBusiness/backRegister";
	}
	
	/**
	 * 归还修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editBackRegister/{id}")
	public String editBackRegister(@PathVariable("id") String id,Model model){
		PublicBusiness bean = publicBusinessMng.get(id);
		model.addAttribute("bean",bean);
		model.addAttribute("closeBack", "true");
		return "/WEB-INF/view/swj/work/publicBusiness/backRegister";
	}
	
	/**
	 * 保存或提交归还登记
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/saveBackRegister")
	@ResponseBody
	public Result saveBackRegister(PublicBusiness bean){
		try {
			PublicBusiness business = publicBusinessMng.get(bean.getId());
			List<CertificateInfo> orgCertificateInfos = business.getCertificateInfos();
			for (CertificateInfo certificateInfo : orgCertificateInfos) {
				if(!bean.getCertificateIds().contains(certificateInfo.getId())){
					return getJsonResult(false, "证照相关人员信息与领用登记时不一致，无法进行归还登记");
				}
			}
			Boolean submit = bean.getHasSubmit();
			if(submit){
				bean.setBackStatus(PublicBusiness.BACK_REGISTER);
				bean.setBackSubmitTime(new Date());
				bean.setBackRegisterUser(getUser());
				PublicBusinessLog  log=new PublicBusinessLog("back", "提交证照归还信息表", getUser(), new Date(),bean);
				publicBusinessLogMng.save(log);
			}else{
				bean.setBackStatus(PublicBusiness.BACK_REGISTER_TODO);
			}
			publicBusinessMng.save(bean,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	@RequestMapping(value="/confirmUseRegister/{id}")
	public String confirmUseRegister(@PathVariable String id,Model model,String todoType){
		model.addAttribute("bean",publicBusinessMng.get(id));
		model.addAttribute("todoType", todoType);
		return "/WEB-INF/view/swj/work/publicBusiness/useRegisterConfirm";
		
	}
	
	@RequestMapping(value="/confirmBackRegister/{id}")
	public String confirmBackRegister(@PathVariable String id,Model model,String todoType){
		model.addAttribute("bean",publicBusinessMng.get(id));
		model.addAttribute("todoType", todoType);
		return "/WEB-INF/view/swj/work/publicBusiness/backRegisterConfirm";
		
	}
	
	/**
	 * 领用确认
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveConfirmUseRegister/{id}")
	@ResponseBody
	public Result saveConfirmUseRegister(@PathVariable String id,Date applyDate){
		try {
			PublicBusiness business = publicBusinessMng.findById(id);
			if(business!=null){
				business.setUseStatus(PublicBusiness.USE_CONFRIM);
				business.setUpdator(getUser());
				if (null != applyDate) {
					business.setApplyDate(applyDate);
				}
				publicBusinessMng.save(business);
				PublicBusinessLog  log=new PublicBusinessLog("use", "确认证照领用信息表", getUser(), new Date(),business);
				publicBusinessLogMng.save(log);
			}
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	
	/**
	 * 归还确认
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveConfirmBackRegister/{id}")
	@ResponseBody
	public Result saveConfirmBackRegister(@PathVariable String id,Date backDate){
		try {
			PublicBusiness business = publicBusinessMng.findById(id);
			if(business!=null){
				business.setBackStatus(PublicBusiness.BACK_CONFRIM);
				business.setUpdator(getUser());
				if (null != backDate) {
					business.setBackDate(backDate);
				}
				publicBusinessMng.save(business);
				PublicBusinessLog  log=new PublicBusinessLog("back", "确认证照归还信息表", getUser(), new Date(),business);
				publicBusinessLogMng.save(log);
			}
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	
	/**
	 * 导出领用
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/exportUseRegister/{id}")
	public String exportUseRegister(@PathVariable String id, HttpServletResponse response,HttpServletRequest request) {
		try {
			PublicBusiness info = publicBusinessMng.findById(id);
			if(info!=null){
				StringBuilder sbCertis=new StringBuilder();
				List<CertificateInfo> certificateInfos = info.getCertificateInfos();
				if(certificateInfos!=null&&certificateInfos.size()>0){
					for (CertificateInfo certificateInfo : certificateInfos) {
						sbCertis.append(certificateInfo.getZjhm()+"-"+certificateInfo.getName());
					}
				}
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("groupDepartName", info.getGroupDepart().getName());
				dataMap.put("certificateNames", sbCertis.toString());
				dataMap.put("approvalNo", info.getApprovalNo());
				dataMap.put("planExitTime",sdf.format(info.getPlanExitTime()) );
				dataMap.put("planEnterTime", sdf.format(info.getPlanEnterTime()));
				dataMap.put("applyUserName", StringUtil.isEmpty(info.getApplyUserName())?"":info.getApplyUserName());
				dataMap.put("applyUserDepartName",  StringUtil.isEmpty(info.getApplyUserDepartName())?"":info.getApplyUserDepartName());
				dataMap.put("applyUserPhone", StringUtil.isEmpty(info.getApplyUserPhone())?"":info.getApplyUserPhone());
				dataMap.put("applyDate", info.getApplyDate()!=null?sdf.format(info.getApplyDate()):"");
			OutputStream os = response.getOutputStream();
			// 设置头部文件生成格式为微软word
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=\"" + getFileName("证照领用信息表")+".doc\"");
			response.setContentType("application/msword");
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(this.getClass(),"/freeMarker");
			// 获取模板
			Template template = configuration.getTemplate("applyUse.ftl");
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			template.process(dataMap, out);
			// 关闭流
			out.flush();
			out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 导出归还
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/exportBackRegister/{id}")
	public String exportBackRegister(@PathVariable String id, HttpServletResponse response,HttpServletRequest request) {
		try {
			PublicBusiness info = publicBusinessMng.findById(id);
			if(info!=null){
				StringBuilder sbCertis=new StringBuilder();
				List<CertificateInfo> certificateInfos = info.getCertificateInfos();
				if(certificateInfos!=null&&certificateInfos.size()>0){
					for (CertificateInfo certificateInfo : certificateInfos) {
						sbCertis.append(certificateInfo.getZjhm()+"-"+certificateInfo.getName()+" ");
					}
				}
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("groupDepartName", info.getGroupDepart().getName());
				dataMap.put("certificateNames", sbCertis.toString());
				dataMap.put("realExitTime",sdf.format(info.getRealExitTime()) );
				dataMap.put("realEnterTime", sdf.format(info.getRealEnterTime()));
				dataMap.put("realVisitCountry", StringUtil.isEmpty(info.getRealVisitCountry())?"":info.getRealVisitCountry());
				dataMap.put("hasViolationSituation", StringUtil.isEmpty(info.getHasViolationSituation())?"":info.getHasViolationSituation());
				dataMap.put("applyUserName", StringUtil.isEmpty(info.getApplyUserName())?"":info.getApplyUserName());
				dataMap.put("backUserName", StringUtil.isEmpty(info.getBackUserName())?"":info.getBackUserName());
				dataMap.put("backUserDepartName", StringUtil.isEmpty(info.getBackUserDepartName())?"":info.getBackUserDepartName());
				dataMap.put("backUserPhone", StringUtil.isEmpty(info.getBackUserPhone())?"":info.getBackUserPhone());
				dataMap.put("backDate", info.getBackDate()!=null?sdf.format(info.getBackDate()):"");
			OutputStream os = response.getOutputStream();
			// 设置头部文件生成格式为微软word
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=\"" + getFileName("证照归还信息表")+".doc\"");
			response.setContentType("application/msword");
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(this.getClass(),"/freeMarker");
			// 获取模板
			Template template = configuration.getTemplate("applyBack.ftl");
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			template.process(dataMap, out);
			// 关闭流
			out.flush();
			out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 领用扫描
	 * @param id
	 * @return
	 */
	@RequestMapping("/useScan")
	public String useScan() {
		return "/WEB-INF/view/swj/work/publicBusiness/useScan";
	}
	
	@RequestMapping("/useRegisterScan/{id}")
	@ResponseBody
	public Map<String,Object> useRegisterScan(@PathVariable String id){
		Map<String,Object> returnMap=new HashMap<String,Object>();
		String msg="";
		if(!StringUtil.isEmpty(id)){
			CertificateInfo certificateInfo = certificateInfoMng.findById(id);
			PublicBusiness business= publicBusinessMng.findByCertificateIdAndStatus(id,PublicBusiness.USE_CONFRIM,CertificationStatus.CHECKIN.getItemValue());
			returnMap.put("business", business);
			//仅仅提示而已
			if(certificateInfo==null){
				msg="对不起，该证照信息不存在";
			}else{
				if(certificateInfo.getEffectiveDate()!=null){
					if(certificateInfo.getEffectiveDate().compareTo(new Date())<0){
						msg="该证照信息已过期";
					}
				}
				if(business==null){
					msg="对不起，没有相关联该证照的申请记录";
				}
			}
		}
		returnMap.put("msg", msg);
		return returnMap;
	}
	
	/**
	 * 领用扫描保存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveUseRegisterScan/{id}")
	@ResponseBody
	public Result saveUseRegisterScan(@PathVariable String id){
		try {
			publicBusinessMng.saveUseRegisterScan(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	
	/**
	 * 归还扫描
	 * @param id
	 * @return
	 */
	@RequestMapping("/backScan")
	public String backScan() {
		return "/WEB-INF/view/swj/work/publicBusiness/backScan";
	}
	
	@RequestMapping("/backRegisterScan/{id}")
	@ResponseBody
	public Map<String,Object> backRegisterScan(@PathVariable String id){
		Map<String,Object> returnMap=new HashMap<String,Object>();
		String msg="";
		if(!StringUtil.isEmpty(id)){
			CertificateInfo certificateInfo = certificateInfoMng.findById(id);
			PublicBusiness business= publicBusinessMng.findByCertificateIdAndStatus(id,PublicBusiness.BACK_CONFRIM,CertificationStatus.USING.getItemValue());
			returnMap.put("business", business);
			//仅仅提示而已
			if(certificateInfo==null){
				msg="对不起，该证照信息不存在";
			}else{
				if(certificateInfo.getEffectiveDate()!=null){
					if(certificateInfo.getEffectiveDate().compareTo(new Date())<0){
						msg="该证照信息已过期";
					}
				}
				if(business==null){
					msg="对不起，没有匹配上申请单据";
				}
			}
		}
		returnMap.put("msg", msg);
		return returnMap;
	}
	
	/**
	 * 归还扫描保存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveBackRegisterScan/{id}")
	@ResponseBody
	public Result saveBackRegisterScan(@PathVariable String id){
		try {
			publicBusinessMng.saveBackRegisterScan(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 归还扫描-新增证照
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addBackRegisterScan/{id}")
	@ResponseBody
	public Result addBackRegisterScan(@PathVariable String id,String certificateIds){
		try {
			if (!StringUtil.isEmpty(id) && !StringUtil.isEmpty(certificateIds)) {
				PublicBusiness publicBusiness = publicBusinessMng.findById(id);
				StringBuilder addInfos=new StringBuilder();
				String[] cids = certificateIds.split(",");
				for (String cid : cids) {
					CertificateInfo info = certificateInfoMng.findById(cid);
					publicBusiness.addCertificateInfo(info);
					if(!StringUtil.isEmpty(addInfos.toString())){
						addInfos.append("、");
					}
					addInfos.append(info.getName()+"("+info.getZjhm()+")");
				}
				publicBusiness.setUpdator(getUser());
				publicBusinessMng.saveOrUpdate(publicBusiness);
				if(!StringUtil.isEmpty(addInfos.toString())){
					PublicBusinessLog  log=new PublicBusinessLog("use", "新增"+addInfos.toString(), getUser(), new Date(),publicBusiness);
					publicBusinessLogMng.save(log);
				}
			}
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 领用-添加新证照
	 * @param id
	 * @return
	 */
	@RequestMapping("/editUse/{id}")
	public String editUse(@PathVariable String id,Model model) {
		model.addAttribute("bean",publicBusinessMng.get(id));
		return "/WEB-INF/view/swj/work/publicBusiness/edit_use";
	}
}
