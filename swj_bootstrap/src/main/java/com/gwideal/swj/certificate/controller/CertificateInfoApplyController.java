package com.gwideal.swj.certificate.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.activiti.entity.TaskHistoryJson;
import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.activiti.manager.ProcessMng;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.page.SimplePage;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
import com.gwideal.swj.certificate.entity.CertificationTypeEnum;
import com.gwideal.swj.certificate.manager.CertificateInfoApplyMng;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/certificateInfoApply")
public class CertificateInfoApplyController extends BaseController{
	private static final ResourceBundle res=ResourceBundle.getBundle("upload");
	private static final long serialVersionUID = -722463441120773872L;
	protected static final Logger log=LoggerFactory.getLogger(CertificateInfoApplyController.class);
	
	@Autowired
	private CertificateInfoApplyMng certificateInfoApplyMng;
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ProcessMng processMng;
	@Autowired
	private AttachmentMng attachmentMng;
	
	@RequestMapping("/list")
	public String list(Model model){
		return "/WEB-INF/view/swj/certificate/apply/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model){
	    return "/WEB-INF/view/swj/certificate/apply/edit";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id,Model model){
		CertificateInfoApply bean=certificateInfoApplyMng.get(id);
		List<Attachment> list = attachmentMng.list(bean.getCreator());
		if(list!=null&&list.size()>0){
			bean.setCreatorAssigneeAtt(list.get(0));
		}
		model.addAttribute("bean",bean);
		if(!StringUtil.isEmpty(bean.getProcessInstanceId())){
			model.addAttribute("listHisTask",processMng.listHistoryTask(bean.getProcessInstanceId()));
		}
	    return "/WEB-INF/view/swj/certificate/apply/edit";
	}
	
	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(CertificateInfoApply bean,String sort,String order,Integer page,Integer rows,ModelMap model){
    	if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
		Pagination p=certificateInfoApplyMng.list(bean,sort,order,page,rows,hasRole("ORGANIZATION_STAFF"),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") String id,Model model){
		CertificateInfoApply bean=certificateInfoApplyMng.get(id);
		List<Attachment> list = attachmentMng.list(bean.getCreator());
		if(list!=null&&list.size()>0){
			bean.setCreatorAssigneeAtt(list.get(0));
		}
		model.addAttribute("bean",bean);
		if(!StringUtil.isEmpty(bean.getProcessInstanceId())){
			model.addAttribute("listHisTask",processMng.listHistoryTask(bean.getProcessInstanceId()));
		}
	    return "/WEB-INF/view/swj/certificate/apply/view";
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Result save(CertificateInfoApply bean){
		try {
			if(certificateInfoApplyMng.isExist(bean, getUser())){
				return getJsonResult(false, "您已存在“正在申请”的信息，暂不能申请！");
			}
			//提交申请领用证照需要验证证照是否有效
			if(!StringUtil.isEmpty(bean.getApplyUseType()) && "toApprove".equals(bean.getAuditStatus())){
				Result invalid=certificateInfoMng.isValidEffectiveDate(bean.getApplyUseType(),bean.getStartDate(),getUser());
				if(!invalid.isSuccess()){
					return invalid;
				}
			}
			certificateInfoApplyMng.save(bean,getUser());
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
			certificateInfoApplyMng.delete(id,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**生成文件函
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/genDocLetter/{id}")
	public String genDoc(@PathVariable String id, HttpServletResponse response,HttpServletRequest request){
		CertificateInfoApply apply = certificateInfoApplyMng.findById(id);
		if(apply!=null){
			this.genDocLetter(apply, response, request);
		}
		return null;
	}
	
	@RequestMapping("/genApprovedDetailInfo/{id}")
	public String genApproveDoc(@PathVariable String id, HttpServletResponse response,HttpServletRequest request){
		CertificateInfoApply apply = certificateInfoApplyMng.findById(id);
		if(apply!=null){
			this.genApprovedDetailInfo(apply, response, request);
		}
		return null;
	}
	
	private String genApprovedDetailInfo(CertificateInfoApply apply, HttpServletResponse response,HttpServletRequest request) {
		try {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("wcode", apply.getWcode());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = apply.getStartDate();
				Date endDate =apply.getEndDate();
				Date applyTime = apply.getApplyTime();
				String startStrDate = sdf.format(startDate);
				String endStrDate = sdf.format(endDate);
				String applyStrTime=sdf.format(applyTime);
				dataMap.put("startDateYear",startStrDate.substring(0,4));
				dataMap.put("startDateMonth",startStrDate.substring(5,7));	
				dataMap.put("startDateDay",startStrDate.substring(8,10));	
				dataMap.put("endDateYear",endStrDate.substring(0,4));	
				dataMap.put("endDateMonth",endStrDate.substring(5,7));	
				dataMap.put("endDateDay",endStrDate.substring(8,10));	
				dataMap.put("reason",apply.getReason());	
				dataMap.put("destination",apply.getDestination());	
				dataMap.put("itinerary",apply.getItinerary());	
				dataMap.put("days",apply.getDays());
				dataMap.put("applyYear", applyStrTime.substring(0,4));
				dataMap.put("applyMonth", applyStrTime.substring(5,7));
				dataMap.put("applyDay", applyStrTime.substring(8,10));
				dataMap.put("applyHandleOne",!StringUtil.isEmpty(apply.getApplyHandleType())&&apply.getApplyHandleType().contains(CertificationTypeEnum.privatePassport.getItemValue())?"√":"");	
				dataMap.put("applyHandleTwo",!StringUtil.isEmpty(apply.getApplyHandleType())&&apply.getApplyHandleType().contains(CertificationTypeEnum.privateHKAndMacaoPass.getItemValue())?"√":"");	
				dataMap.put("applyHandleThree",!StringUtil.isEmpty(apply.getApplyHandleType())&&apply.getApplyHandleType().contains(CertificationTypeEnum.privateTaiwanPass.getItemValue())?"√":"");	
				dataMap.put("applyUseOne",!StringUtil.isEmpty(apply.getApplyUseType())&&apply.getApplyUseType().contains(CertificationTypeEnum.privatePassport.getItemValue())?"√":"");	
				dataMap.put("applyUseTwo",!StringUtil.isEmpty(apply.getApplyUseType())&&apply.getApplyUseType().contains(CertificationTypeEnum.privateHKAndMacaoPass.getItemValue())?"√":"");	
				dataMap.put("applyUseThree",!StringUtil.isEmpty(apply.getApplyUseType())&&apply.getApplyUseType().contains(CertificationTypeEnum.privateTaiwanPass.getItemValue())?"√":"");		
				dataMap.put("hasParty","T".equals(apply.getHasParty())?"√":"");	
				String departOpinion="",jzzrscOpinion="",fgjldOpinion="",jldOpinion="",remark=apply.getFileSummary()==null?"":apply.getFileSummary();
				String departAssign="",departYear="",departMonth="",departDay="";
				String jzzrscAssign="",jzzrscYear="",jzzrscMonth="",jzzrscDay="";
				String fgjldAssign="",fgjldYear="",fgjldMonth="",fgjldDay="";
				String jldAssign="",jldYear="",jldMonth="",jldDay="";
				String jbrAssign="",jbrYear="",jbrMonth="",jbrDay="";
				String attachSrc=null;
				if(!StringUtil.isEmpty(apply.getProcessInstanceId())){
					List<TaskHistoryJson> historyTasks = processMng.listHistoryTask(apply.getProcessInstanceId());
					if(historyTasks!=null&&historyTasks.size()>0){
						for (TaskHistoryJson taskHistoryJson : historyTasks) {
							if(StringUtil.isEmpty(jldOpinion)&&"BUREAU_MAIN_LEADER".equals(taskHistoryJson.getTaskDefKey())){
								jldOpinion=taskHistoryJson.getComment();
								jldAssign=taskHistoryJson.getAssigneeName();
								Date signTime = taskHistoryJson.getEndTime();
								if(signTime!=null){
									jldYear=sdf.format(signTime).substring(0, 4);
									jldMonth=sdf.format(signTime).substring(5, 7);
									jldDay=sdf.format(signTime).substring(8, 10);
								}
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("jldUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
							if(StringUtil.isEmpty(fgjldOpinion)&&"BUREAU_DIRECTOR".equals(taskHistoryJson.getTaskDefKey())){
								fgjldOpinion=taskHistoryJson.getComment();
								fgjldAssign=taskHistoryJson.getAssigneeName();
								Date signTime = taskHistoryJson.getEndTime();
								if(signTime!=null){
									fgjldYear=sdf.format(signTime).substring(0, 4);
									fgjldMonth=sdf.format(signTime).substring(5, 7);
									fgjldDay=sdf.format(signTime).substring(8, 10);
								}
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("fgjldUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
							if(StringUtil.isEmpty(jzzrscOpinion)&&"ORGANIZATION_DIRECTOR".equals(taskHistoryJson.getTaskDefKey())){
								jzzrscOpinion=taskHistoryJson.getComment();
								jzzrscAssign=taskHistoryJson.getAssigneeName();
								Date signTime = taskHistoryJson.getEndTime();
								if(signTime!=null){
									jzzrscYear=sdf.format(signTime).substring(0, 4);
									jzzrscMonth=sdf.format(signTime).substring(5, 7);
									jzzrscDay=sdf.format(signTime).substring(8, 10);
								}
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("jzrscUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
							if(StringUtil.isEmpty(departOpinion)&&"OFFICAL_CADRES".equals(taskHistoryJson.getTaskDefKey())){
								departOpinion=taskHistoryJson.getComment();
								departAssign=taskHistoryJson.getAssigneeName();
								Date signTime = taskHistoryJson.getEndTime();
								if(signTime!=null){
									departYear=sdf.format(signTime).substring(0, 4);
									departMonth=sdf.format(signTime).substring(5, 7);
									departDay=sdf.format(signTime).substring(8, 10);
								}
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("departUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
							if(StringUtil.isEmpty(jbrAssign)&&"ORGANIZATION_STAFF".equals(taskHistoryJson.getTaskDefKey())){
								jbrAssign=taskHistoryJson.getAssigneeName();
								Date signTime = taskHistoryJson.getEndTime();
								if(signTime!=null){
									jbrYear=sdf.format(signTime).substring(0, 4);
									jbrMonth=sdf.format(signTime).substring(5, 7);
									jbrDay=sdf.format(signTime).substring(8, 10);
								}
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("jbUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
						}
					}
				}
				dataMap.put("jbUserName",jbrAssign );
				dataMap.put("jbrYear",jbrYear );
				dataMap.put("jbrMonth",jbrMonth );
				dataMap.put("jbrDay",jbrDay );
				dataMap.put("departOpinion",departOpinion);	
				dataMap.put("departUserName",departAssign );
				dataMap.put("departYear",departYear );
				dataMap.put("departMonth",departMonth );
				dataMap.put("departDay",departDay );
				dataMap.put("jzzrscOpinion",jzzrscOpinion);	
				dataMap.put("jzrscUserName",jzzrscAssign );
				dataMap.put("jzzrscYear",jzzrscYear );
				dataMap.put("jzzrscMonth",jzzrscMonth );
				dataMap.put("jzzrscDay",jzzrscDay );
				dataMap.put("fgjldOpinion",fgjldOpinion);	
				dataMap.put("fgjldUserName",fgjldAssign);	
				dataMap.put("fgjldYear",fgjldYear);	
				dataMap.put("fgjldMonth",fgjldMonth);	
				dataMap.put("fgjldDay",fgjldDay);	
				dataMap.put("jldOpinion",jldOpinion);
				dataMap.put("jldUserName",jldAssign );
				dataMap.put("jldYear",jldYear );
				dataMap.put("jldMonth",jldMonth );
				dataMap.put("jldDay",jldDay );
				dataMap.put("remark",remark);
				dataMap.put("privateRecord", apply.getPrivateRecord());
				dataMap.put("publicRecord", apply.getPublicRecord());
				dataMap.put("applyUserName",apply.getCreatorName());
				List<Attachment> list = attachmentMng.list(apply.getCreator());
				if(list!=null&&list.size()>0){
					attachSrc=getSavePath()+list.get(0).getFileUrl();
				}
				dataMap.put("applyUserImage",getImageBase(attachSrc));
				attachSrc=null;
				
				OutputStream os = response.getOutputStream();
				// 设置头部文件生成格式为微软word
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename=" + getFileName("上海市水务局（上海市海洋局）现职处级干部因私事出国（境）审批表")+".doc");
				response.setContentType("application/msword");
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				configuration.setClassForTemplateLoading(this.getClass(),"/freeMarker");
				// 获取模板
				Template template=null;
				if("DEPUTY_CADRES_PASSPORT_APPLY".equals(apply.getProcessDefinitionKey())){
					template= configuration.getTemplate("approveDetailInfo_deputy.ftl");
				}else{
					template= configuration.getTemplate("approveDetailInfo.ftl");
				}
				// 将模板和数据模型合并生成文件
				Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
				template.process(dataMap, out);
				// 关闭流
				out.flush();
				out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private String genDocLetter(CertificateInfoApply apply, HttpServletResponse response,HttpServletRequest request) {
		try {
				String types=apply.getApplyHandleType();
				if(StringUtil.isEmpty(types)){
					types=apply.getApplyUseType();
				}
				Map<String, Object> dataMap = new HashMap<String, Object>();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				dataMap.put("name", apply.getCreatorName());
				dataMap.put("idCard",apply.getCreator().getIdCard());
				dataMap.put("departName",apply.getCreator().getDepartName().length()<10?("     "+apply.getCreator().getDepartName()+"    "):apply.getCreator().getDepartName());	
				dataMap.put("position",apply.getCreator().getPostLevel()==null?"":apply.getCreator().getPostLevel());	
				dataMap.put("typeOne",types.contains(CertificationTypeEnum.privatePassport.getItemValue())?"☑":"□");	
				dataMap.put("typeThree",types.contains(CertificationTypeEnum.privateHKAndMacaoPass.getItemValue())?"☑":"□");	
				dataMap.put("typeTwo",types.contains(CertificationTypeEnum.privateTaiwanPass.getItemValue())?"☑":"□");
				dataMap.put("typeOneRemove",!types.contains(CertificationTypeEnum.privatePassport.getItemValue())?"1":null);	
				dataMap.put("typeThreeRemove",!types.contains(CertificationTypeEnum.privateHKAndMacaoPass.getItemValue())?"1":null);	
				dataMap.put("typeTwoRemove",!types.contains(CertificationTypeEnum.privateTaiwanPass.getItemValue())?"1":null);
				dataMap.put("hongkongReason",types.contains(CertificationTypeEnum.privateHKAndMacaoPass.getItemValue())?apply.getReason():"");	
				dataMap.put("hongkongNum","");	
				dataMap.put("macaoReason",types.contains(CertificationTypeEnum.privateHKAndMacaoPass.getItemValue())?apply.getReason():"");	
				dataMap.put("macaoNum","");	
				String jbrAssign="",zzrscAssgin="",zzrscYear="",zzrscMonth="",zzrscDay="";
				if(!StringUtil.isEmpty(apply.getProcessInstanceId())){
					List<TaskHistoryJson> historyTasks = processMng.listHistoryTask(apply.getProcessInstanceId());
					if(historyTasks!=null&&historyTasks.size()>0){
						String attachSrc=null;
						for (TaskHistoryJson taskHistoryJson : historyTasks) {
							if(StringUtil.isEmpty(zzrscAssgin)&&"BUREAU_MAIN_LEADER".equals(taskHistoryJson.getTaskDefKey())){
								zzrscAssgin=taskHistoryJson.getAssigneeName();
								Date signTime = taskHistoryJson.getEndTime();
								if(signTime!=null){
									zzrscYear=sdf.format(signTime).substring(0, 4);
									zzrscMonth=sdf.format(signTime).substring(5, 7);
									zzrscDay=sdf.format(signTime).substring(8, 10);
								}
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("jldUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
							if(StringUtil.isEmpty(jbrAssign)&&"ORGANIZATION_STAFF".equals(taskHistoryJson.getTaskDefKey())){
								jbrAssign=taskHistoryJson.getAssigneeName();
								if(taskHistoryJson.getAssigneeAtt()!=null){
									attachSrc=getSavePath()+taskHistoryJson.getAssigneeAtt().getFileUrl();
								}
								dataMap.put("jbrUserImage",getImageBase(attachSrc));
								attachSrc=null;
							}
						}
					}
				}
				dataMap.put("jbrUserName", jbrAssign);
				dataMap.put("jldUserName", zzrscAssgin);
				dataMap.put("bossYear", zzrscYear);
				dataMap.put("bossMonth", zzrscMonth);
				dataMap.put("bossDay", zzrscDay);
				OutputStream os = response.getOutputStream();
				// 设置头部文件生成格式为微软word
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename=" + getFileName("申办出入境证件的函")+".doc");
				response.setContentType("application/msword");
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				configuration.setClassForTemplateLoading(this.getClass(),"/freeMarker");
				// 获取模板
				Template template = configuration.getTemplate("docLetter.ftl");
				// 将模板和数据模型合并生成文件
				Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
				template.process(dataMap, out);
				// 关闭流
				out.flush();
				out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳转到取消流程页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/toCancel/{id}")
	public String toCancel(@PathVariable String id,ModelMap model){
		model.addAttribute("id", id);
		return "/WEB-INF/view/swj/certificate/apply/cancel";
	}
	
	/**
	 * 验证申请能不能取消或者修改，有些流程退回是先退回到“组织人事处工作人员初审”
	 * @param id
	 * @return
	 */
	@RequestMapping("/canCancelOrEdit/{id}")
	@ResponseBody
	public Result canCancelOrEdit(@PathVariable String id,ModelMap model){
		try {
			CertificateInfoApply bean = certificateInfoApplyMng.findById(id);
			int activeTaskCount=processMng.getActiveTaskCount(bean.getProcessInstanceId(), getUser());
			if(activeTaskCount==1){
				return getJsonResult(true, Result.SUCCESSMSG);
			}else{
				return getJsonResult(false,"只有退回到“在线填报申请”才可以操作！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 取消流程
	 * @param id
	 * @param reason 原因
	 * @return
	 */
	@RequestMapping("/cancel/{id}")
	@ResponseBody
	public Result cancel(@PathVariable String id,String reason,ModelMap model){
		try {
			certificateInfoApplyMng.cancel(id, reason, getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 证照待领用列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/useScanList")
	public String useScanList(Model model){
		return "/WEB-INF/view/swj/certificate/apply/useScanList";
	}
	
	/**
	 * 证照待归还列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/backScanList")
	public String backScanList(Model model){
		return "/WEB-INF/view/swj/certificate/apply/backScanList";
	}
	
	/**
	 * 证照待领回列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/receiveScanList")
	public String receiveScanList(Model model){
		return "/WEB-INF/view/swj/certificate/apply/receiveScanList";
	}
	
	@RequestMapping("/scanJsonPagination")
	@ResponseBody
	public JsonPagination scanJsonPagination(CertificateInfoApply bean,String sort,String order,Integer page,Integer rows,ModelMap model){
    	if(page==null){page=1;}
    	if(rows==null){rows=SimplePage.DEF_COUNT;}
    	if(bean==null){
    		bean=new CertificateInfoApply();
    	}
    	String taskName="use".equals(bean.getScanType())?"RECEIVE_PASSPORT":("back".equals(bean.getScanType())?"RETURN_PASSPORT":"RECEIVE_OLD_PASSPORT");
    	//获取流程节点为申请人扫码待领用的节点
    	List<String> processInstanceIdList=new ArrayList<String>();
    	List<Task> taskList = taskService.createTaskQuery().taskDefinitionKey(taskName).list();
    	if(taskList!=null&&taskList.size()>0){
	    	for (Task task : taskList) {
	    		String processInstanceId = task.getProcessInstanceId();
	    		processInstanceIdList.add(processInstanceId);
			}
    	}
    	bean.setProcessInstanceIdList(processInstanceIdList);
		Pagination p=certificateInfoApplyMng.list(bean,sort,order,page,rows,hasRole("ORGANIZATION_STAFF"),getUser());
		return getJsonPagination(p,page);
	}
	
	@RequestMapping("/useScan")
	public String useScan(Model model,String todoType){
		model.addAttribute("todoType", todoType);
		return "/WEB-INF/view/swj/certificate/apply/useScan";
	}
	
	@RequestMapping("/backScan")
	public String backScan(Model model,String todoType){
		model.addAttribute("todoType", todoType);
		return "/WEB-INF/view/swj/certificate/apply/backScan";
	}
	
	@RequestMapping("/receiveScan")
	public String receiveScan(Model model,String todoType){
		model.addAttribute("todoType", todoType);
		return "/WEB-INF/view/swj/certificate/apply/receiveScan";
	}
	
	@RequestMapping("/scanSearch/{id}")
	@ResponseBody
	public Map<String,Object> scanSearch(@PathVariable String id,String type){
		Map<String,Object> returnMap=new HashMap<String,Object>();
		String msg="";
		if(!StringUtil.isEmpty(id)){
			CertificateInfo certificateInfo = certificateInfoMng.findById(id);
			CertificateInfoApply apply= certificateInfoApplyMng.findByCertificateIdAndScanType(id,type);
			if(apply!=null){
				if("use".equals(type)||"back".equals(type)){
					String applyUseType = apply.getApplyUseType();
					if (!StringUtil.isEmpty(applyUseType)) {
						String[] arrs = applyUseType.split(",");
							List<CertificateInfo> infos = certificateInfoMng.findPrivateByTypeAndOwnId(arrs, apply.getCreator().getId());
							if(infos!=null&&infos.size()>0){
								for (CertificateInfo info : infos) {
									String certificateType = info.getCertificateType();
									if(certificateType.contains("Passport")){
										apply.setUseTypeOne("<span id='"+info.getId()+"_scanSpan'/>√");
									}else if(certificateType.contains("HK")){
										apply.setUseTypeTwo("<span id='"+info.getId()+"_scanSpan'/>√");
									}else if(certificateType.contains("Taiwan")){
										apply.setUseTypeThree("<span id='"+info.getId()+"_scanSpan'/>√");
									}
								}
							}
					}
				}else if("receive".equals(type)){
					String applyHandleType = apply.getApplyHandleType();
					if (!StringUtil.isEmpty(applyHandleType)) {
						String[] arrs = applyHandleType.split(",");
							List<CertificateInfo> infos = certificateInfoMng.findPrivateByTypeAndOwnId(arrs, apply.getCreator().getId());
							int shouldScanNum=0;
							if(infos!=null&&infos.size()>0){
								for (CertificateInfo info : infos) {
									String certificateType = info.getCertificateType();
									if(certificateType.contains("Passport")){
										apply.setReceiveTypeOne("<span id='"+info.getId()+"_scanSpan'/>√");
										shouldScanNum+=1;
									}else if(certificateType.contains("HK")){
										apply.setReceiveTypeTwo("<span id='"+info.getId()+"_scanSpan'/>√");
										shouldScanNum+=1;
									}else if(certificateType.contains("Taiwan")){
										apply.setReceiveTypeThree("<span id='"+info.getId()+"_scanSpan'/>√");
										shouldScanNum+=1;
									}
								}
							}
							//不存在的护照直接渲染钩
							if(StringUtil.isEmpty(apply.getReceiveTypeOne())&&applyHandleType.contains("Passport")){
								apply.setReceiveTypeOne("√");
							}
							if(StringUtil.isEmpty(apply.getReceiveTypeTwo())&&applyHandleType.contains("HK")){
								apply.setReceiveTypeTwo("√");
							}
							if(StringUtil.isEmpty(apply.getReceiveTypeThree())&&applyHandleType.contains("Taiwan")){
								apply.setReceiveTypeThree("√");
							}
							apply.setShouldScanNum(shouldScanNum+"");
					}
				}
				returnMap.put("comment", processMng.listHistoryTask(apply.getProcessInstanceId()));
				String creatorAttId=null;
				List<Attachment> list = attachmentMng.list(apply.getCreator());
				if(list!=null&&list.size()>0){
					creatorAttId=list.get(0).getId();
				}
				returnMap.put("signAttId", creatorAttId);
			}
			//仅仅提示而已
			if(certificateInfo==null){
				msg="对不起，该证照信息不存在";
			}else{
				if(certificateInfo.getEffectiveDate()!=null){
					if(certificateInfo.getEffectiveDate().compareTo(new Date())<0){
						msg="该证照信息已过期";
					}
				}
				if(apply==null){
					msg="对不起，没有匹配上申请单据";
				}
			}
			returnMap.put("apply", apply);
			
		}
		returnMap.put("msg", msg);
		return returnMap;
	}
	
	
	/**
	 * 领用扫描保存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/useScanSave/{applyId}")
	@ResponseBody
	public Result useScanSave(@PathVariable String applyId,TaskJson taskJson){
		try {
			certificateInfoApplyMng.saveUseScan(applyId,taskJson,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 归还扫描保存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backScanSave/{applyId}")
	@ResponseBody
	public Result backScanSave(@PathVariable String applyId,TaskJson taskJson){
		try {
			certificateInfoApplyMng.saveBackScan(applyId,taskJson,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	/**
	 * 领回扫描保存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/receiveScanSave/{applyId}")
	@ResponseBody
	public Result receiveScanSave(@PathVariable String applyId,TaskJson taskJson){
		try {
			certificateInfoApplyMng.saveReceiveScan(applyId,taskJson,getUser());
			return getJsonResult(true, Result.SUCCESSMSG);
		} catch (Exception e) {
			log.error("",e);
			return getJsonResult(false, Result.ERRORMSG);
		}
	}
	
	public String getImageBase(String src) {
	       if(src==null||src==""){
	           return null;
	       }
	        File file = new File(src);
	        if(!file.exists()) {
	            return null;
	       }
	        InputStream in = null;
	        byte[] data = null;  
	        try {
	            in = new FileInputStream(file);
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	        }
	        try {  
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
	        } catch (IOException e) {  
	          e.printStackTrace();  
	         } 
	        BASE64Encoder encoder = new BASE64Encoder();
	        return encoder.encode(data);
	  }
	private String getSavePath(){
		return res.getString("upload.save_path");
		//return request.getServletContext().getRealPath(FileUpLoadUtil.getSavePath());
	}
}
