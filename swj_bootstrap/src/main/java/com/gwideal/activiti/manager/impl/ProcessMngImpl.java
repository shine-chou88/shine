package com.gwideal.activiti.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
import com.gwideal.swj.certificate.manager.CertificateInfoApplyMng;
import com.gwideal.swj.work.manager.PublicBusinessMng;
import com.gwideal.activiti.entity.ProcessDefinitionJson;
import com.gwideal.activiti.entity.TaskHistoryJson;
import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.activiti.manager.ProcessMng;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;

@Service
@Transactional
public class ProcessMngImpl extends BaseManagerImpl<ProcessDefinitionJson> implements ProcessMng {

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private UserMng userMng;
	@Autowired
	private CertificateInfoApplyMng certificateInfoApplyMng;
	@Autowired
	private PublicBusinessMng publicBusinessMng;
	@Autowired
	private AttachmentMng attachmentMng;

	@Override
	public int getActiveTaskCount(User user) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Task> listTask = taskService.createTaskQuery().taskCandidateOrAssigned(user.getId()).active().list();
		if (null != listTask && listTask.size() > 0) {
			count = listTask.size();
		}
		return count;
	}
	
	@Override
	public int getActiveTaskCount(String processInstanceId,User user) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Task> listTask = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(user.getId()).active().list();
		if (null != listTask && listTask.size() > 0) {
			count = listTask.size();
		}
		return count;
	}

	@Override
	public void completeDraftTask(String processInstanceId, String userId, String nextAssignee) {
		// TODO Auto-generated method stub
		// 获取该流程实例下的当前用户的活动的任务
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId)
				.active().singleResult();
		if (null != task && !StringUtil.isEmpty(task.getId())) {
			Map<String, Object> nextMap = new HashMap<String, Object>();
			nextMap.put("assignee", nextAssignee);
			taskService.complete(task.getId(), nextMap);
		}
	}

	@Override
	public String startProcessInstance(String processInstanceByKey, String businessKey, String userId,
			String nextAssignee) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("creatorId", userId);
		// 启动流程实例
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInstanceByKey,businessKey,map);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskAssignee(userId).singleResult();
		Map<String, Object> nextMap = new HashMap<String, Object>();
		nextMap.put("assignee", nextAssignee);
		nextMap.put("pass", true);
		// 完成“第一个”任务
		taskService.complete(task.getId(), nextMap);
		return processInstance.getId();
	}

	@Override
	public void cancel(String processInstanceId, String reason, User user) {
		// TODO Auto-generated method stub
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(user.getId()).active().singleResult();
		taskService.addComment(task.getId(), task.getProcessInstanceId(), reason);
		runtimeService.deleteProcessInstance(processInstanceId, reason);
	}

	@Override
	public void specialApprove(TaskJson bean, User user) {
		// TODO Auto-generated method stub
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("pass", bean.isPass());
		variables.put("hasPassport", bean.isHasPassport());
		variables.put("rejected", bean.isRejected());
		ExecutionEntity entity = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(bean.getProcessInstanceId()).singleResult();
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(entity.getProcessDefinitionId());
		// 当前活动环节
		ActivityImpl currentActivityImpl = definition.findActivity(entity.getActivityId());
		// 目标活动节点
		ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
				.findActivity(bean.getTargetTaskDefinitionKey());
		if (currentActivityImpl != null) {
			// 所有的出口集合
			List<PvmTransition> currentPvmTransitions = currentActivityImpl.getOutgoingTransitions();
			List<PvmTransition> oriPvmTransitions = new ArrayList<PvmTransition>();
			for (PvmTransition transition : currentPvmTransitions) {
				oriPvmTransitions.add(transition);
			}
			// 清除所有出口
			currentPvmTransitions.clear();
			// 建立新的出口
			List<TransitionImpl> newTransitionImpls = new ArrayList<TransitionImpl>();
			TransitionImpl nextTransitionImpl = currentActivityImpl.createOutgoingTransition();
			nextTransitionImpl.setDestination(nextActivityImpl);
			newTransitionImpls.add(nextTransitionImpl);
			List<Task> list = taskService.createTaskQuery().processInstanceId(entity.getProcessInstanceId())
					.taskDefinitionKey(entity.getActivityId()).list();
			for (Task task : list) {
				taskService.addComment(task.getId(),task.getProcessInstanceId(),user.getName()+"特送："+bean.getComment());
				taskService.complete(task.getId(), variables);
			}
			for (TransitionImpl transitionImpl : newTransitionImpls) {
				currentActivityImpl.getOutgoingTransitions().remove(transitionImpl);
			}
			for (PvmTransition pvmTransition : oriPvmTransitions) {
				currentPvmTransitions.add(pvmTransition);
			}
		}
	}

	@Override
	public List<TaskHistoryJson> listHistoryTask(String processInstanceId) {
		// TODO Auto-generated method stub
		List<HistoricActivityInstance> listActIns=historyService.createHistoricActivityInstanceQuery().activityType("userTask").finished().processInstanceId(processInstanceId).orderByHistoricActivityInstanceEndTime().desc().list();
	 	List<TaskHistoryJson> listTaskHis=new ArrayList<TaskHistoryJson>();
	 	if(null!=listActIns && listActIns.size()>0){
	 		int t=listActIns.size();
	 		HistoricActivityInstance draf=listActIns.get(0);
	 		//如果是申请人刚提交，则原来的流程信息不显示
	 		if(!"DRAF".equals(draf.getActivityId())){
	 			for (int i = 0; i < t; i++) {
		 			HistoricActivityInstance actIns=listActIns.get(i);
		 			addHistoryTask(actIns,listTaskHis);
		 			//只显示当前的流程信息以及最后一次的退回流程信息，不然会出现当前流程还没结束后面流程会显示上次退回的信息
		 			if("ORGANIZATION_STAFF".equals(actIns.getActivityId())){
		 				HistoricActivityInstance officalCadres=listActIns.get(i+1);
		 				if(null!=officalCadres && "OFFICAL_CADRES".equals(officalCadres.getActivityId())){
		 					addHistoryTask(officalCadres,listTaskHis);
		 				}
		 				break;
		 			}
				}
	 		}
	 	}
	 	return listTaskHis;
	}
	
	private void addHistoryTask(HistoricActivityInstance actIns,List<TaskHistoryJson> listTaskHis){
		TaskHistoryJson his=new TaskHistoryJson();
		his.setTaskId(actIns.getTaskId());
		his.setTaskDefKey(actIns.getActivityId());
		his.setTaskName(actIns.getActivityName());
		his.setTaskType(actIns.getActivityType());
		if(!StringUtil.isEmpty(actIns.getAssignee())){
			User user=userMng.findById(actIns.getAssignee());
		if(null!=user && !StringUtil.isEmpty(user.getName())){
			his.setAssigneeName(user.getName());
			List<Attachment> signList = attachmentMng.list(user);
			if(signList!=null&&signList.size()>0){
				his.setAssigneeAtt(signList.get(0));
			}
		}		 
		}
		List<Comment> listComment=taskService.getTaskComments(actIns.getTaskId());
		if(null!=listComment && listComment.size()>0){
		his.setComment(listComment.get(0).getFullMessage());
		}
		his.setStartTime(actIns.getStartTime());
		his.setEndTime(actIns.getEndTime());
		listTaskHis.add(his);
	}

	@Override
	public List<TaskJson> listCurrentTask(User user) {
		// TODO Auto-generated method stub
		List<TaskJson> listJson=new ArrayList<TaskJson>();
    	List<Task> listTask=taskService.createTaskQuery().taskCandidateOrAssigned(user.getId()).active().orderByTaskCreateTime().desc().list();
    	if(null!=listTask && listTask.size()>0){
    		ProcessInstance instance=null;
    		TaskJson taskJson=null;
    		for(Task task:listTask){
    			instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
    			taskJson=new TaskJson();
    			taskJson.setId(task.getId());
    			taskJson.setName(task.getName());
    			taskJson.setAssignee(task.getAssignee());
    			taskJson.setProcessDefinitionKey(instance.getProcessDefinitionKey());
    			if("DEPUTY_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey()) || 
    					"OFFICAL_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey())){
    				CertificateInfoApply bean=certificateInfoApplyMng.findById(instance.getBusinessKey());
    				taskJson.setTitle(bean.getTitle());
    				taskJson.setProcessDefinitionName("因私出国（境）审批");
				}
    			taskJson.setActId(task.getTaskDefinitionKey());
    			taskJson.setBusinessKey(instance.getBusinessKey());
				setLastTask(task.getProcessInstanceId(),taskJson);
    			listJson.add(taskJson);
    		}
    	}
    	//领用待确认 归还待确认任务
		if(user.hasRole("PUBLIC_JBGSGZRY")){
			List listMsg=publicBusinessMng.getUnReadMsg();
			if(listMsg!=null&&listMsg.size()>0){
				int t=listMsg.size();
				for (int i = 0; i < t; i++) {
					TaskJson json=new TaskJson();
					Object[] arr=(Object[])listMsg.get(i);
					json.setTitle(arr[6]==null?"":arr[6].toString());
					json.setId(arr[0]==null?"":arr[0].toString());
					json.setName(arr[2]==null?"":arr[2].toString());
	    			json.setProcessDefinitionName(arr[1]==null?"":arr[1].toString());
	    			json.setLastName(arr[3]==null?"":arr[3].toString());
    				json.setLastEndTime(arr[5]==null?"":arr[5].toString());
    				json.setLastAssigneeName(arr[4]==null?"":arr[4].toString());
					listJson.add(json);
				}
			}
		}
		return listJson;
	}

	/**
	 * 设置当前节点的上一个节点
	 * @param processInstanceId
	 * @param taskJson
	 * @return
	 */
	private void setLastTask(String processInstanceId,TaskJson taskJson){
		List<HistoricActivityInstance> listActIns=historyService.createHistoricActivityInstanceQuery().activityType("userTask").processInstanceId(processInstanceId).finished().orderByHistoricActivityInstanceEndTime().desc().list();
		if(null!=listActIns && listActIns.size()>0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			HistoricActivityInstance lastTask=listActIns.get(0);
			taskJson.setLastName(lastTask.getActivityName());
			taskJson.setLastEndTime(sdf.format(lastTask.getEndTime()));
			if(!StringUtil.isEmpty(lastTask.getAssignee())){
				User user=userMng.findById(lastTask.getAssignee());
				if(null!=user && !StringUtil.isEmpty(user.getName())){
					taskJson.setLastAssigneeName(user.getName());
				}
			}
		}
	}
	
}
