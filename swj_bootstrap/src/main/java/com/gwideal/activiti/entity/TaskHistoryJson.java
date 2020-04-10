package com.gwideal.activiti.entity;

import java.io.Serializable;
import java.util.Date;

import com.gwideal.attachment.entity.Attachment;
/**
 * 任务历史信息相关属性
 * @author zhou_liang
 *
 */
public class TaskHistoryJson implements Serializable{
	
	private static final long serialVersionUID = 3526463486211813975L;
	
	private String taskId;//任务Id
	private String taskDefKey;//任务定义key
	private String taskName;//任务名称
	private String taskType;//任务类型
	private String assigneeName;//任务完成人名称
	private String comment;//任务意见
	private Date startTime;//任务开始时间
	private Date endTime;//任务结束时间
	
	private Attachment assigneeAtt;//任务完成人的电子签名
	
	public Attachment getAssigneeAtt() {
		return assigneeAtt;
	}
	public void setAssigneeAtt(Attachment assigneeAtt) {
		this.assigneeAtt = assigneeAtt;
	}
	public String getTaskDefKey() {
		return taskDefKey;
	}
	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
