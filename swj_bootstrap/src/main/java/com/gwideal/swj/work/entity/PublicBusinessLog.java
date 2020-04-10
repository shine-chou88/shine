package com.gwideal.swj.work.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;

import java.util.Date;

//${comments}
@Entity
@Table(name = "T_PUBLIC_BUSINESS_LOG")
public class PublicBusinessLog extends GenericEntityNow implements Serializable {

	//操作类型
	@Column(name = "OPERATE_TYPE")
	private String operateType;
	//操作内容
	@Column(name = "OPERATE_CONTENT")
	private String operateContent;
	//操作人
	@ManyToOne
	@JoinColumn(name = "OPERATE_USER")
	@JsonIgnore
	private User operateUser;
	//操作时间
	@Column(name = "OPERATE_TIME")
	private Date operateTime;
	//申请表ID
	@ManyToOne
	@JoinColumn(name = "BUSINESS_ID")
	@JsonIgnore
	private PublicBusiness publicBusiness;
	
	@Transient
	private String businessId;
	

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	public String getOperateType() {
		return operateType;
	}
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	
	public String getOperateContent() {
		return operateContent;
	}
	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}
	
	public User getOperateUser() {
		return operateUser;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	public Date getOperateTime() {
		return operateTime;
	}
	
	public PublicBusinessLog(){
		
	}
	
	public PublicBusinessLog(String operateType, String operateContent, User operateUser, Date operateTime,
			PublicBusiness publicBusiness) {
		super();
		this.operateType = operateType;
		this.operateContent = operateContent;
		this.operateUser = operateUser;
		this.operateTime = operateTime;
		this.publicBusiness = publicBusiness;
	}

	public PublicBusiness getPublicBusiness() {
		return publicBusiness;
	}

	public void setPublicBusiness(PublicBusiness publicBusiness) {
		this.publicBusiness = publicBusiness;
	}
	@Transient
	private String operateUserName;
	


	public String getOperateUserName() {
		if(operateUser!=null){
			return operateUser.getName();
		}
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public String getOperateUserDepartName(){
		if(operateUser!=null&&operateUser.getDepart()!=null){
			return operateUser.getDepartName();
		}
		return null;
	}
	
	
	
	
}
