package com.gwideal.activiti.listener;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
/**
 * 因私出国(境)申请-“局主要领导终审”监听
 * @author zhou_liang
 *
 */
@Service
@Transactional
public class CertificateInfoApplyApprovedListener extends BaseManagerImpl<CertificateInfoApply> implements TaskListener{
	private static final long serialVersionUID = -4722698507134650437L;

	private RuntimeService runtimeService;
	
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		CertificateInfoApply bean=super.findById(instance.getBusinessKey());
		Object rejected=task.getVariable("rejected");
		if(null!=rejected && Boolean.valueOf(rejected.toString()).booleanValue()){
			bean.setAuditStatus("rejected");
		}else{
			Object pass=task.getVariable("pass");
			if(null!=pass && Boolean.valueOf(pass.toString()).booleanValue()){
				bean.setAuditStatus("approved");
			}else{
				bean.setAuditStatus("returned");
			}
		}
		super.save(bean);
	}
}
