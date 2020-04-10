package com.gwideal.activiti.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDiagramCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwideal.activiti.entity.ProcessButton;
import com.gwideal.activiti.entity.ProcessDefinitionJson;
import com.gwideal.activiti.entity.ProcessInstanceDiagram;
import com.gwideal.activiti.entity.TaskHistoryJson;
import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.activiti.manager.ProcessMng;
import com.gwideal.activiti.manager.TaskMng;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;
import com.gwideal.common.page.ComboboxJson;
import com.gwideal.common.page.JsonPagination;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.page.Result;
import com.gwideal.common.util.StringUtil;
import com.gwideal.common.web.BaseController;
import com.gwideal.core.manager.DepartMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
import com.gwideal.swj.certificate.manager.CertificateInfoApplyMng;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/activiti/process")
public class ProcessController extends BaseController{
	protected static final Logger logger = LoggerFactory.getLogger(ProcessController.class);
	private static final ResourceBundle resource=ResourceBundle.getBundle("activiti");
	
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private  RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskMng taskMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	private DepartMng departMng;
	@Autowired
	private CertificateInfoApplyMng certificateInfoApplyMng;
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private ProcessMng processMng;
	@Autowired
	private AttachmentMng attachmentMng;
	
	/**
	 * 查询得到所有已部署的流程
	 * @return
	 */
	@RequestMapping("/list")
	public String list(){
		return "/WEB-INF/view/activiti/list";
	}
	
	/**
	 * 查询得到所有已部署的流程
	 * @return
	 */
	@RequestMapping("/jsonPagination")
	@ResponseBody
	public JsonPagination jsonPagination(){
		List<ProcessDefinition> listProcessDefinition = repositoryService.createProcessDefinitionQuery().list();
		List<ProcessDefinitionJson> listJson=new ArrayList<ProcessDefinitionJson>();
		if(null!=listProcessDefinition && listProcessDefinition.size()>0){
			for(ProcessDefinition processDefinition:listProcessDefinition){
				ProcessDefinitionJson processDefinitionJson=new ProcessDefinitionJson();
				processDefinitionJson.setId(processDefinition.getId());
				processDefinitionJson.setKey(processDefinition.getKey());
				processDefinitionJson.setName(processDefinition.getName());
				processDefinitionJson.setCategory(processDefinition.getCategory());
				processDefinitionJson.setVersion(processDefinition.getVersion());
				processDefinitionJson.setDeploymentId(processDefinition.getDeploymentId());
				processDefinitionJson.setDiagramResourceName(processDefinition.getDiagramResourceName());
				processDefinitionJson.setDescription(processDefinition.getDescription());
				processDefinitionJson.setTenantId(processDefinition.getTenantId());
				listJson.add(processDefinitionJson);
			}
		}
		Pagination p=new Pagination(1,listJson.size(),listJson.size(),listJson);
		return getJsonPagination(p,1);
	}
	
	/**
	 * 部署单个流程
	 * @param id
	 * @return
	 */
	@RequestMapping("/deploy")
	@ResponseBody
	public Result deploy(String id){
		try {
			if (!StringUtil.isEmpty(id)){
				ProcessDefinition processDefinition=repositoryService.getProcessDefinition(id);
				String path=request.getServletContext().getRealPath(processDefinition.getResourceName());
				File file=new File(path);
				if(null!=file && file.exists() && file.isFile()){
		        	repositoryService.createDeployment().addInputStream(processDefinition.getResourceName(),new FileInputStream(file)).deploy();
				}
	        }
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("", e);
			return getJsonResult(false,"部署失败，请联系管理员！");
		}
		return getJsonResult(true,"部署成功！");
	}
	
	/**
	 * 部署所有流程
	 * @param id
	 * @return
	 */
	@RequestMapping("/deployAll")
	@ResponseBody
	public Result deployAll(){
		try {
			String diagramPath=resource.getString("DIAGRAM_PATH");
			String path=request.getServletContext().getRealPath(diagramPath);
			File file=new File(path);
			if(null!=file && file.isDirectory()){
				File[] files=file.listFiles();
				if(null!=files && files.length>0){
					for(File f:files){
						repositoryService.createDeployment().addInputStream(diagramPath+f.getName(),new FileInputStream(f)).deploy();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("", e);
			return getJsonResult(false,"部署所有流程失败，请联系管理员！");
		}
		return getJsonResult(true,"部署所有流程成功！");
	}
	
	/**
	 * 我的任务列表
	 * @return
	 */
	@RequestMapping("/taskList")
	public String tasklist(){
		return "/WEB-INF/view/activiti/task_list";
	}
	
	/**
	 * 我的任务列表
	 * @return
	 */
	@RequestMapping("/taskListJsonPagination")
	@ResponseBody
	public JsonPagination taskListJsonPagination(){
		List<TaskJson> listJson=processMng.listCurrentTask(getUser());
		Pagination p=new Pagination(1,listJson.size(),listJson.size(),listJson);
		return getJsonPagination(p,1);
	}
	
	/**
	 * 获取当前环节的下一个环节
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @param executionId
	 * @return
	 */
	public List<TaskJson> getNextUserTask(String processDefinitionId,String processInstanceId,String executionId,ProcessButton processButton){
		List<TaskJson> listTaskJson=new ArrayList<TaskJson>();
		ProcessDefinitionEntity procDef = (ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);  
		List<ActivityImpl> activitiList =procDef.getActivities();
		ExecutionEntity execution =(ExecutionEntity)runtimeService.createExecutionQuery().executionId(executionId).singleResult();
		//找到当前任务的流程变量
		List<HistoricVariableInstance> listVar=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
		ExpressionFactory factory = new ExpressionFactoryImpl();
		SimpleContext context = new SimpleContext();
		if(null!=listVar && listVar.size()>0){
			for(HistoricVariableInstance var :listVar){
				if(null!=var.getValue()){
					context.setVariable(var.getVariableName(),factory.createValueExpression(var.getValue(),var.getValue().getClass()));
				}
 		    }
		}
		String activitiId =execution.getActivityId();//正在执行环节的id 
		for(ActivityImpl activityImpl:activitiList){  
			if(activitiId.equals(activityImpl.getId())){
				nextTaskDefinition(activityImpl,activityImpl.getId(),listTaskJson,processButton,factory,context,false);  
			}  
		}
		//符合特定条件的流程优先
		List<TaskJson> listSpecificTaskJson=new ArrayList<TaskJson>();
		if(null!=listTaskJson && listTaskJson.size()>0){
			for(TaskJson taskJson:listTaskJson){
				if(taskJson.isHasSpecificCondition()){
					listSpecificTaskJson.add(taskJson);
					break;
				}
			}
		}
		if(listSpecificTaskJson.size()>0){
			return listSpecificTaskJson;
		}
		return listTaskJson;
	}
	
	/**
	 * 下一个任务定义
	 * @param activityImpl
	 * @param activityId
	 * @param listTaskJson
	 * @param hasSpecificCondition 符合特定条件可以跳过其他流程往下执行
	 */
	public void nextTaskDefinition(ActivityImpl activityImpl,String activityId,List<TaskJson> listTaskJson,ProcessButton processButton,ExpressionFactory factory,SimpleContext context,boolean hasSpecificCondition){  
        if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){  
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition(); 
            TaskJson taskJson=new TaskJson();
            taskJson.setActId(taskDefinition.getKey());
            taskJson.setActName(taskDefinition.getNameExpression().getExpressionText());
            taskJson.setHasSpecificCondition(hasSpecificCondition);
            listTaskJson.add(taskJson);
        }else{  
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();  
            List<PvmTransition> outTransitionsTemp = null;  
            for(PvmTransition tr:outTransitions){    
                PvmActivity ac = tr.getDestination();//获取线路的终点节点    
                if("exclusiveGateway".equals(ac.getProperty("type")) || "inclusiveGateway".equals(ac.getProperty("type"))){  
                    outTransitionsTemp = ac.getOutgoingTransitions();  
                    for(PvmTransition trGateWay:outTransitionsTemp){
                    	selectUsefulTaskDefinition(trGateWay,activityId,listTaskJson,processButton,factory,context,hasSpecificCondition);
                    }  
                }else if("userTask".equals(ac.getProperty("type"))){ 
                	selectUsefulTaskDefinition(tr,activityId,listTaskJson,processButton,factory,context,hasSpecificCondition);
                }
            }   
        }  
	}  
	
	/**
	 * 选择执行条件为“true”的任务定义
	 */
	public void selectUsefulTaskDefinition(PvmTransition tr,String activityId,List<TaskJson> listTaskJson,ProcessButton processButton,ExpressionFactory factory,SimpleContext context,boolean hasSpecificCondition){
		Object conditionText = tr.getProperty("conditionText");//获取执行环节的条件
    	if(null==conditionText || StringUtil.isEmpty(conditionText.toString())){
    		nextTaskDefinition((ActivityImpl)tr.getDestination(),activityId,listTaskJson,processButton,factory,context,false);
    	}else{
    		//有“退回”操作的流程需要“不通过”按钮
    		if("${pass==false}".equals(conditionText.toString())){
    			processButton.setNeedNoPassButton(true);
    		}else{
    			//符合特定条件可以跳过其他流程往下执行
        		ValueExpression e = factory.createValueExpression(context,conditionText.toString(),boolean.class);
        		if((Boolean)(e.getValue(context))){
        			nextTaskDefinition((ActivityImpl)tr.getDestination(),activityId,listTaskJson,processButton,factory,context,true);
        		}
    		}
    	}
	}
	
	/**
	 * 跳转到任务完成页面
	 * @param taskId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toComplete/{taskId}")
	public String toComplete(@PathVariable String taskId,ModelMap model){
		Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
		model.addAttribute("taskId",taskId);
		model.addAttribute("taskDefinitionKey",task.getTaskDefinitionKey());
		ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		model.addAttribute("processInstanceId",instance.getProcessInstanceId());
		model.addAttribute("bussinessKey",instance.getBusinessKey());
		model.addAttribute("processDefinitionKey",instance.getProcessDefinitionKey());
		ProcessButton processButton=new ProcessButton();//是否需要“不通过”按钮,没有“退回”操作的流程不需要此按钮
		model.addAttribute("listUserTask",getNextUserTask(task.getProcessDefinitionId(),task.getProcessInstanceId(),task.getExecutionId(),processButton));
		model.addAttribute("processButton",processButton);
		initBean(instance, model);
		model.addAttribute("isOrgStaff", getUser().hasRole("ORGANIZATION_STAFF"));
		return "/WEB-INF/view/activiti/task_complete";
	}
	
	/**
	 * 初始化业务数据
	 * @param instance
	 * @param model
	 */
	private void initBean(ProcessInstance instance,ModelMap model){
		//因私出国(境)申请
		if("DEPUTY_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey()) 
			|| "OFFICAL_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey())){
			CertificateInfoApply bean=certificateInfoApplyMng.findById(instance.getBusinessKey());
			if(null!=bean && !StringUtil.isEmpty(bean.getApplyUseType())){
				model.addAttribute("hasPassport",true);
				model.addAttribute("hasOldPassport", false);//20200122 领用流程默认走扫码领用
			}else{
				model.addAttribute("hasPassport",false);
				model.addAttribute("hasOldPassport",certificateInfoMng.isExistOldPassport(bean.getApplyHandleType(), bean.getCreator()));
			}
			List<Attachment> list = attachmentMng.list(bean.getCreator());
			if(list!=null&&list.size()>0){
				bean.setCreatorAssigneeAtt(list.get(0));
			}
			CertificateInfoApply lastApply = certificateInfoApplyMng.findByStatusAndUser(new String[]{"approved","archived"}, bean.getCreator());
			if(lastApply!=null&&StringUtil.isEmpty(bean.getPrivateDestination())&&StringUtil.isEmpty(bean.getPrivateDateDesc())&&StringUtil.isEmpty(bean.getPrivateReason())){
				bean.setPrivateDateDesc(DateFormatUtils.format(lastApply.getStartDate(), "yyyy年MM月dd日")+"至"+DateFormatUtils.format(lastApply.getEndDate(), "yyyy年MM月dd日"));
				bean.setPrivateDestination(lastApply.getDestination());
				bean.setPrivateReason(lastApply.getReason());
			}
			model.addAttribute("bean",bean);
			model.addAttribute("listHisTask",processMng.listHistoryTask(bean.getProcessInstanceId()));
		}
	}
	
	/**
	 * 流程特送
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toSpecialApprove/{processInstanceId}")
	public String toSpecialApprove(@PathVariable("processInstanceId") String processInstanceId,ModelMap model){
		ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		model.addAttribute("bussinessKey",instance.getBusinessKey());
		model.addAttribute("processDefinitionKey",instance.getProcessDefinitionKey());
		List<TaskJson> listTask=new ArrayList<TaskJson>();
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService)
		          .getProcessDefinition(instance.getProcessDefinitionId());
		TaskJson taskJson=null;
		for (Iterator iterator = definition.getActivities().listIterator(); iterator.hasNext();) {
			ActivityImpl activityImpl = (ActivityImpl) iterator.next();
			if("userTask".equals(activityImpl.getProperties().get("type"))){
				taskJson=new TaskJson();
				taskJson.setActId(activityImpl.getId());
				taskJson.setActName(activityImpl.getProperties().get("name").toString());
				listTask.add(taskJson);
			}
		}
		model.addAttribute("listTask",listTask);
		initBean(instance, model);
		return "/WEB-INF/view/activiti/special_approve";
	}
	
	/**
	 * 流程特送
	 * @param bean
	 * @return
	 */
	@RequestMapping("/specialApprove")
	@ResponseBody
	public Result specialApprove(TaskJson bean){
		try {
			processMng.specialApprove(bean, getUser());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	/**
	 * 下一环节审批人
	 * @return
	 */
	@RequestMapping("/approveUser")
	@ResponseBody
	public List<ComboboxJson> approveUser(String actId){
		List<User> listApproveUser=userMng.listByRole(actId);
		return getComboboxJson(listApproveUser);
	}
	
	/**
	 * 完成任务
	 * @param bean
	 * @return
	 */
	@RequestMapping("/complete")
	@ResponseBody
	public Result complete(TaskJson bean){
		try {
			taskMng.complete(bean,getUser());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("", e);
			return getJsonResult(false,"操作失败，请联系管理员！");
		}
		return getJsonResult(true,"操作成功！");
	}
	
	/**
	 * 查看已经和业务关联的流程
	 * @param businessKey
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewByProcessInstanceId/{processInstanceId}")
	public String viewByProcessInstanceId(@PathVariable String processInstanceId,ModelMap model){
	 	if(!StringUtil.isEmpty(processInstanceId)){
	 		List<HistoricActivityInstance> listActIns=historyService.createHistoricActivityInstanceQuery().activityType("userTask").processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();
		 	List<TaskHistoryJson> listTaskHis=new ArrayList<TaskHistoryJson>();
		 	if(null!=listActIns && listActIns.size()>0){
		 		for(HistoricActivityInstance actIns:listActIns){
		 			TaskHistoryJson his=new TaskHistoryJson();
		 			his.setTaskId(actIns.getTaskId());
		 			his.setTaskDefKey(actIns.getActivityId());
		 			his.setTaskName(actIns.getActivityName());
		 			his.setTaskType(actIns.getActivityType());
		 			if(!StringUtil.isEmpty(actIns.getAssignee())){
		 				User user=userMng.findById(actIns.getAssignee());
			 			if(null!=user && !StringUtil.isEmpty(user.getName())){
			 				his.setAssigneeName(user.getName());	
			 			}		 
		 			}
		 			List<Comment> listComment=taskService.getTaskComments(actIns.getTaskId());
		 			if(null!=listComment && listComment.size()>0){
			 			his.setComment(listComment.get(0).getMessage());
		 			}
		 			his.setStartTime(actIns.getStartTime());
		 			his.setEndTime(actIns.getEndTime());
		 			listTaskHis.add(his);
		 		}
		 	}
		 	model.addAttribute("listTaskHis",listTaskHis);
	 	}
	 	model.addAttribute("processInstanceId",processInstanceId);
	 	model.addAttribute("random",Math.random());
		return "/WEB-INF/view/activiti/view_by_businesskey";
	}
	
	/**
	 * 查看已经和业务关联的流程图
	 * @param processInstanceId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/viewDiagramByProcessInstanceId")
	public void viewDiagramByProcessInstanceId(String processInstanceId,HttpServletResponse response) throws IOException{
		ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(null==instance || instance.isEnded()){
			List<HistoricActivityInstance> listActIns=historyService.createHistoricActivityInstanceQuery().activityType("userTask").processInstanceId(processInstanceId).list();
			outPutDiagram(new GetDeploymentProcessDiagramCmd(listActIns.get(0).getProcessDefinitionId()),response);
		}else{
			outPutDiagram(new ProcessInstanceDiagram(processEngine,processInstanceId),response);
		}
	}
	
	/**
	 * 查看已部署流程的流程图
	 * @param processDefinitionId
	 * @param model
	 * @return
	 */
	@RequestMapping("/view/{processDefinitionId}")
	public String view(@PathVariable String processDefinitionId,ModelMap model){
		model.addAttribute("processDefinitionId",processDefinitionId);
		return "/WEB-INF/view/activiti/view";
	}
	
	/**
	 * 查看已部署流程的流程图
	 * @param businessKey
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/viewDiagram/{processDefinitionId}")
	public void viewDiagram(@PathVariable String processDefinitionId,HttpServletResponse response)throws IOException{
		outPutDiagram(new GetDeploymentProcessDiagramCmd(processDefinitionId),response);
	}
	
	/**
	 * 输出流程图
	 * @param cmd
	 * @param response
	 * @throws IOException
	 */
	public void outPutDiagram(Command<InputStream>  cmd,HttpServletResponse response)throws IOException{
		OutputStream out=null;
		InputStream in=null;
		try {		
        	in=processEngine.getManagementService().executeCommand(cmd);
        	response.setContentType("image/png");
        	response.setContentLength(in.available());
	        out = response.getOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = in.read(b, 0, 1024))>0) {
                out.write(b, 0, len);
                out.flush();
            }
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}finally{
			if(null!=out){out.close();}
			if(null!=in){in.close();}
		}	
	}
}
