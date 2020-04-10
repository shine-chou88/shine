package com.gwideal.activiti.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.core.model.User;
/**
 * 任务分配受理人-任务定义key必须和角色对应
 * @author zhou_liang
 *
 */
@Service
@Transactional
public class AssigneeListener extends BaseManagerImpl<User> implements TaskListener{
	private static final long serialVersionUID = 3003416263415225688L;

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		//任务Id为角色代码,任务Id包含“--”则取“--”后面的内容作为角色代码(解决两个任务节点是同一个角色的人,任务Id不能重复)
		String[] roles=task.getTaskDefinitionKey().split("--");
		String roleCode=roles[0];
		if(roles.length>1){
			roleCode=roles[1];
		}
		List<String> list=super.find("Select u.id from User u join u.roles r Where u.flag=1 and r.code=?",roleCode);
		if(null!=list && list.size()>0){
			for(String uId:list){
				task.addCandidateUser(uId);
			}
		}
	}
}
