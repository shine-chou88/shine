package com.gwideal.activiti.manager;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.core.model.User;

import java.util.List;

import com.gwideal.activiti.entity.ProcessDefinitionJson;
import com.gwideal.activiti.entity.TaskHistoryJson;
import com.gwideal.activiti.entity.TaskJson;

public interface ProcessMng extends BaseManager<ProcessDefinitionJson>{
	
	/**
	 * 用户提交表单同时完成“申请(Draft)”环节
	 * @param processInstanceId 流程实例Id
	 * @param userId 完成人
	 * @param nextAssignee 下一环节执行人
	 */
	public void completeDraftTask(String processInstanceId,String userId,String nextAssignee);
	
	/**
	 * 取消流程
	 * @param processInstanceId
	 * @param reason 取消原因
	 * @param user
	 */
	public void cancel(String processInstanceId,String reason,User user);
	
	/**
	 * 获取用户活动的任务数量
	 * @param user
	 * @return
	 */
	public int getActiveTaskCount(User user);
	
	/**
	 * 获取用户活动的任务数量
	 * @param user
	 * @return
	 */
	public int getActiveTaskCount(String processInstanceId,User user);
	
	/**
	 * 启动流程
	 * @param processInstanceByKey 流程实例key
	 * @param businessKey 业务id
	 * @param assignee 任务完成人
	 * @param nextAssignee 下一环节审批人
	 * @return 流程id
	 */
	public String startProcessInstance(String processInstanceByKey,String businessKey,String userId,String nextAssignee);
	
	/**
	 * 特送
	 * @param bean
	 * @param user
	 */
	public void specialApprove(TaskJson bean,User user);
	
	/**
	 * 通过流程实例ID获取历史任务
	 * @param processInstanceId
	 * @return
	 */
	public List<TaskHistoryJson> listHistoryTask(String processInstanceId);
	
	/**
	 * 查询用户当前待办任务
	 * @param user
	 * @return
	 */
	public List<TaskJson> listCurrentTask(User user);
}
