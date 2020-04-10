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
 * 因私出国(境)申请-“退回”监听
 * @author zhou_liang
 *
 */
@Service
@Transactional
public class CertificateInfoApplyReturnedListener extends BaseManagerImpl<CertificateInfoApply> implements TaskListener{
	private static final long serialVersionUID = -7224474860420674412L;
	
	private RuntimeService runtimeService;
	
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		Object rejected=task.getVariable("rejected");
		if(null!=rejected && Boolean.valueOf(rejected.toString()).booleanValue()){
			ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			CertificateInfoApply bean=super.findById(instance.getBusinessKey());
			bean.setAuditStatus("rejected");
			super.save(bean);
		}else{
			Object pass=task.getVariable("pass");
			ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			CertificateInfoApply bean=super.findById(instance.getBusinessKey());
			if(null!=pass && Boolean.valueOf(pass.toString()).booleanValue()){
				bean.setAuditStatus("toApprove");
			}else{
				bean.setAuditStatus("returned");
			}
			super.save(bean);
		}
	}
}
