package com.gwideal.activiti.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
/**
 * 任务分配受理人-任务Id必须和角色对应，有些任务需要和部门关联
 * @author zhou_liang
 *
 */
@Service
@Transactional
public class AssigneeWithDepartListener extends BaseManagerImpl<User> implements TaskListener{
	private static final long serialVersionUID = -5723172632850145807L;

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		//任务Id为角色代码,任务Id包含“--”则取“--”后面的内容作为角色代码(解决两个任务节点是同一个角色的人,任务Id不能重复)
		String[] roles=task.getTaskDefinitionKey().split("--");
		String roleCode=roles[0];
		if(roles.length>1){
			roleCode=roles[1];
		}
		String applyer=task.getVariable("creatorId").toString();//申请人
		User u=super.findById(applyer);
		if(!StringUtil.isEmpty(u.getDepartApprover())){
			//部门审批人，工会主席（正处）在“局组织人事处（老干部处）”为副处长，需要特殊处理
			task.addCandidateUser(u.getDepartApprover());
		}else{
			String hql="Select u.id from User u join u.roles r Where u.flag=1 and r.code=? and u.depart.id=?";
			List<String> list=super.find(hql,new Object[]{roleCode,u.getDepartId()});
			if(null!=list && list.size()>0){
				for(String uId:list){
					task.addCandidateUser(uId);
				}
			}
		}
	}
}
