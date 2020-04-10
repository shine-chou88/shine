package com.gwideal.activiti.manager;

import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.core.model.User;

public interface TaskMng extends BaseManager<TaskJson>{
	/**
	 * 完成任务
	 * @param bean
	 */
	public void complete(TaskJson bean,User user);
}
