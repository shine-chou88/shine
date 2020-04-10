package com.gwideal.activiti.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * activiti 任务相关属性表
 * @author zhou_liang
 *
 */
public class TaskJson implements Serializable{

	private static final long serialVersionUID = -4334255425746016756L;
	
	private String id;
	private String title;//流程标题
	private String type;//流程类型
	private String name;//流程名称
	private String assignee;//当前流程受理人
	private String comment;//评论
	private String nextAssignee;//下一流程受理人
	private boolean pass=true;//审批是否通过
	private boolean rejected=false;//审批不通过
	private boolean hasSpecificCondition=false;//符合特定条件可以跳过其他流程往下执行
	private boolean hasPassport=false;//是否有证件
	private boolean hasOldPassport=false;//新办是否需要领回旧护照
	private String lastName;//上一流程名称
	private String lastAssigneeName;//上一流程受理人
	private String lastEndTime;//上一流程结束时间
	private String actId;//流程图上的userTask-id
	private String actName;//流程图上的userTask-name
	private String businessKey;//业务id
	private String processDefinitionKey;//流程定义key
	private String processDefinitionName;//流程定义名称
	private String processInstanceId;//流程实例id
	private String targetTaskDefinitionKey;//目标任务定义key
	
	private Date privateStart;
	private Date privateEnd;
	//因私出国记录原因
	private String privateReason;
	//因私出国记录目的地
	private String privateDestination;
	
	//因公出国记录开始时间
	private Date publicStart;
	//因公出国记录结束时间
	private Date publicEnd;
	//因公出国记录原因
	private String publicReason;
	//因公出国记录目的地
	private String publicDestination;
	
	private String privateDateDesc;
	private String publicDateDesc;
	
	public boolean isHasOldPassport() {
		return hasOldPassport;
	}
	public void setHasOldPassport(boolean hasOldPassport) {
		this.hasOldPassport = hasOldPassport;
	}
	public String getPrivateDateDesc() {
		return privateDateDesc;
	}
	public void setPrivateDateDesc(String privateDateDesc) {
		this.privateDateDesc = privateDateDesc;
	}
	public String getPublicDateDesc() {
		return publicDateDesc;
	}
	public void setPublicDateDesc(String publicDateDesc) {
		this.publicDateDesc = publicDateDesc;
	}
	public Date getPrivateStart() {
		return privateStart;
	}
	public void setPrivateStart(Date privateStart) {
		this.privateStart = privateStart;
	}
	public Date getPrivateEnd() {
		return privateEnd;
	}
	public void setPrivateEnd(Date privateEnd) {
		this.privateEnd = privateEnd;
	}
	public String getPrivateReason() {
		return privateReason;
	}
	public void setPrivateReason(String privateReason) {
		this.privateReason = privateReason;
	}
	public String getPrivateDestination() {
		return privateDestination;
	}
	public void setPrivateDestination(String privateDestination) {
		this.privateDestination = privateDestination;
	}
	public Date getPublicStart() {
		return publicStart;
	}
	public void setPublicStart(Date publicStart) {
		this.publicStart = publicStart;
	}
	public Date getPublicEnd() {
		return publicEnd;
	}
	public void setPublicEnd(Date publicEnd) {
		this.publicEnd = publicEnd;
	}
	public String getPublicReason() {
		return publicReason;
	}
	public void setPublicReason(String publicReason) {
		this.publicReason = publicReason;
	}
	public String getPublicDestination() {
		return publicDestination;
	}
	public void setPublicDestination(String publicDestination) {
		this.publicDestination = publicDestination;
	}
	public String getTargetTaskDefinitionKey() {
		return targetTaskDefinitionKey;
	}
	public void setTargetTaskDefinitionKey(String targetTaskDefinitionKey) {
		this.targetTaskDefinitionKey = targetTaskDefinitionKey;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public boolean isHasPassport() {
		return hasPassport;
	}
	public void setHasPassport(boolean hasPassport) {
		this.hasPassport = hasPassport;
	}
	public boolean isRejected() {
		return rejected;
	}
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
	public boolean isHasSpecificCondition() {
		return hasSpecificCondition;
	}
	public void setHasSpecificCondition(boolean hasSpecificCondition) {
		this.hasSpecificCondition = hasSpecificCondition;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastAssigneeName() {
		return lastAssigneeName;
	}
	public void setLastAssigneeName(String lastAssigneeName) {
		this.lastAssigneeName = lastAssigneeName;
	}
	public String getLastEndTime() {
		return lastEndTime;
	}
	public void setLastEndTime(String lastEndTime) {
		this.lastEndTime = lastEndTime;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public String getNextAssignee() {
		return nextAssignee;
	}
	public void setNextAssignee(String nextAssignee) {
		this.nextAssignee = nextAssignee;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
}
