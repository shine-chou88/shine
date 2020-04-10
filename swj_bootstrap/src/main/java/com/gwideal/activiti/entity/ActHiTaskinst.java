package com.gwideal.activiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * activiti历史任务表
 * @author zhou_liang
 */
@Entity
@Table(name = "ACT_HI_TASKINST")
public class ActHiTaskinst implements Serializable {
	private static final long serialVersionUID = -4679752435805002214L;
	
	@Id
    @Column(name = "ID_")
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "guid")
    private String id;
	
	//流程实例id
	@Column(name = "PROC_INST_ID_")
	private String procInstId;
	
	//任务定义key
	@Column(name = "TASK_DEF_KEY_")
	private String taskDefKey;
	
	//任务名称
	@Column(name = "NAME_")
	private String name;
	
	//任务执行人
	@Column(name = "ASSIGNEE_")
	private String assignee;
	
	//任务开始时间
	@Column(name = "START_TIME_")
	private Date startTime;
	
	//任务领用时间
	@Column(name = "CLAIM_TIME_")
	private Date claimTime;
	
	//任务结束时间
	@Column(name = "END_TIME_")
	private Date endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
