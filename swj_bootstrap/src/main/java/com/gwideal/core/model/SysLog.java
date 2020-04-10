package com.gwideal.core.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;

/**
 * 系统操作日志
 * @author zhou_liang
 *
 */
@Entity
@Table(name="SYS_OPERATE_LOG")
public class SysLog extends GenericEntityNow implements Serializable{
	private static final long serialVersionUID = 2005335674290610420L;
	
	@Column(name="operate_url")
	private String operateUrl;//操作路径
	
	@Column(name="operate_content")
	private String operateContent;//操作内容
	
	@Column(name="type")
	private String type;//类型  出国境：passport
	
	@Column(name="wx_id")
	private String wxId;//微信用户id
	
	@Column(name="data_id")
	private String dataId;//操作数据id
	
	@Column(name="IP")
	private String ip;//操作IP

	@Transient
	private String userName;
	
	@Transient
	private String createDateStr;
	@Transient
	private String logName;
	@Transient
	private String startTime;
	@Transient
	private String endTime;
	@Transient
	private String currentlogName;
	@Transient
	private String creatorId;
	@Transient
	private String createTimeStr;
	@Transient
	private boolean hasQuRole=false;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isHasQuRole() {
		return hasQuRole;
	}

	public void setHasQuRole(boolean hasQuRole) {
		this.hasQuRole = hasQuRole;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getTypeHz() {
		String typeHz = "";
		if (!StringUtil.isEmpty(type)) {
			if ("passport".equals(type)) {
				typeHz = "出国（境）管理";
			}
		}
		return typeHz;
	}
	
	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOperateUrl() {
		return operateUrl;
	}

	public void setOperateUrl(String operateUrl) {
		this.operateUrl = operateUrl;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getCreateDateStr() {
		createDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.getCreateTime());
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getUserName() {
		if (null != this.getCreator()) {
			userName = this.getCreator().getName();
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentlogName() {
		return currentlogName;
	}

	public void setCurrentlogName(String currentlogName) {
		this.currentlogName = currentlogName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	
	
	
}
