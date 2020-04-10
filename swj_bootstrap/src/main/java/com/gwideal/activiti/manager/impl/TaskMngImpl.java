package com.gwideal.activiti.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.activiti.manager.TaskMng;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.User;
import com.gwideal.sms.manager.SmsPoolMng;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
import com.gwideal.swj.certificate.manager.CertificateInfoApplyMng;
@Service
@Transactional
public class TaskMngImpl extends BaseManagerImpl<TaskJson> implements TaskMng{

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private CertificateInfoApplyMng certificateInfoApplyMng;
	@Autowired
	private SmsPoolMng smsPoolMng;
	@Autowired
	private UserMng userMng;
	
	@Override
	public void complete(TaskJson bean,User user) {
		// TODO Auto-generated method stub
		taskService.claim(bean.getId(),user.getId());
		Task task=taskService.createTaskQuery().taskId(bean.getId()).singleResult();
		taskService.addComment(bean.getId(),task.getProcessInstanceId(),bean.getComment());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pass",bean.isPass());
		map.put("hasPassport", bean.isHasPassport());
		map.put("hasOldPassport", bean.isHasOldPassport());
		map.put("rejected",bean.isRejected());
		map.put("comment",bean.getComment());
		if("ORGANIZATION_STAFF".equals(task.getTaskDefinitionKey())&&user.hasRole("ORGANIZATION_STAFF")){
			ProcessInstance processIns=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			CertificateInfoApply apply=certificateInfoApplyMng.findById(processIns.getBusinessKey());
			if(apply!=null){
				apply.setPrivateDestination(bean.getPrivateDestination());
				apply.setPrivateDateDesc(bean.getPrivateDateDesc());
				apply.setPrivateReason(bean.getPrivateReason());
				apply.setPublicDestination(bean.getPublicDestination());
				apply.setPublicDateDesc(bean.getPublicDateDesc());
				apply.setPublicReason(bean.getPublicReason());
				certificateInfoApplyMng.save(apply);
			}
		}
		//不通过删除流程
		if(bean.isRejected()){
			ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			if("DEPUTY_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey()) 
					|| "OFFICAL_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey())){
				CertificateInfoApply apply=certificateInfoApplyMng.findById(instance.getBusinessKey());
				apply.setAuditStatus("rejected");
				certificateInfoApplyMng.save(apply);
			}
			runtimeService.deleteProcessInstance(task.getProcessInstanceId(),"审批不通过！");
		}else{
			if(bean.isPass()){
				if(!StringUtil.isEmpty(bean.getNextAssignee())){
					map.put("assignee",bean.getNextAssignee());
				}
			}else{
				//退回同时完成并行任务
				List<Task> listTask=taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).active().list();
				if(null!=listTask && listTask.size()>=2){
					for(Task t:listTask){
						if(!t.getId().equals(bean.getId())){
							taskService.complete(t.getId(),map);
						}
					}
				}
				//退回发送短信
				CertificateInfoApply apply = certificateInfoApplyMng.findUniqueByProperty("processInstanceId", task.getProcessInstanceId());
				if(apply!=null&&"ORGANIZATION_STAFF".equals(task.getTaskDefinitionKey())){
					User creator = apply.getCreator();
					if(creator!=null&&!StringUtil.isEmpty(creator.getMobileNo())){
						smsPoolMng.saveSendSms(creator.getMobileNo(), creator.getName(), "您的"+apply.getWcode()+"因私出国（境）申请已被退回，请及时处理！【出国（境）管理】", user);
					}
				}
				if(apply!=null&&!"ORGANIZATION_STAFF".equals(task.getTaskDefinitionKey())){
					User creator = apply.getCreator();
					//发给组织工作人员
					List<User> listAuditor = userMng.listByRole("ORGANIZATION_STAFF");
					if(listAuditor!=null&&listAuditor.size()>0){
						for (User sendUser : listAuditor) {
							if(sendUser!=null&&!StringUtil.isEmpty(sendUser.getMobileNo())){
								smsPoolMng.saveSendSms(sendUser.getMobileNo(), sendUser.getName(), creator.getName()+"的"+apply.getWcode()+"因私出国（境）申请已被退回，请及时处理！【出国（境）管理】", sendUser);
							}
						}
					}
					
				}
			}
			//通知领用或者领取文件函
			if("BUREAU_MAIN_LEADER".equals(task.getTaskDefinitionKey())&&bean.isPass()){
				CertificateInfoApply apply = certificateInfoApplyMng.findUniqueByProperty("processInstanceId", task.getProcessInstanceId());
				if(apply!=null){
					User creator = apply.getCreator();
					if(creator!=null&&!StringUtil.isEmpty(creator.getMobileNo())){
						if(!StringUtil.isEmpty(apply.getApplyHandleType())){
							smsPoolMng.saveSendSms(creator.getMobileNo(), creator.getName(), "您的"+apply.getWcode()+"出国（境）申请已审批通过，请联系局组织人事处领取证件（如是涉密人员请办理完涉密审批手续后领取），归国（境）后十日内归还证件！【出国（境）管理】", user);
						}else{
							smsPoolMng.saveSendSms(creator.getMobileNo(), creator.getName(), "您的"+apply.getWcode()+"出国（境）申请已审批通过，请联系局组织人事处领取证件（如是涉密人员请办理完涉密审批手续后领取），归国（境）后十日内归还证件！ 【出国（境）管理】", user);
						}
					}
					//审批通过还需要发送给组织处工作人员/2019-09-03
					List<User> listAuditor = userMng.listByRole("ORGANIZATION_STAFF");
					if(listAuditor!=null && listAuditor.size()>0){
						for (User sendUser : listAuditor) {
							if(sendUser!=null && !StringUtil.isEmpty(sendUser.getMobileNo())){
								smsPoolMng.saveSendSms(sendUser.getMobileNo(), sendUser.getName(), creator.getName()+"的"+apply.getWcode()+"出国（境）申请已审批通过，请及时处理！【出国（境）管理】", user);
							}
						}
					}
				}
			}
			
			if("RECEIVE_FILE".equals(task.getTaskDefinitionKey())||"RETURN_PASSPORT_BYHAND".equals(task.getTaskDefinitionKey())){
				CertificateInfoApply apply = certificateInfoApplyMng.findUniqueByProperty("processInstanceId", task.getProcessInstanceId());
				if(apply!=null){
					if("RECEIVE_FILE".equals(task.getTaskDefinitionKey())){
						apply.setRealUseDate(new Date());
					}else if("RETURN_PASSPORT_BYHAND".equals(task.getTaskDefinitionKey())){
						apply.setRealBackDate(new Date());
					}
					certificateInfoApplyMng.merge(apply);
				}
			}
			//解锁证照
			if("OVER_SETUP--ORGANIZATION_STAFF".equals(task.getTaskDefinitionKey())){
				CertificateInfoApply apply = certificateInfoApplyMng.findUniqueByProperty("processInstanceId", task.getProcessInstanceId());
				if(apply!=null){
					certificateInfoApplyMng.setLockCertificateInfo(apply, 0, apply.getCreator());
				}
			}
			taskService.complete(bean.getId(),map);
		}
	}
	
}
