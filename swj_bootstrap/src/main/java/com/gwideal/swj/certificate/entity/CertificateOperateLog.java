package com.gwideal.swj.certificate.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;

import java.util.Date;

//${comments}
@Entity
@Table(name = "T_CERTIFICATE_OPERATE_LOG")
public class CertificateOperateLog extends GenericEntityNow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7499655976462004278L;
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
	//证照信息ID
	@ManyToOne
	@JoinColumn(name = "CERTIFICATE_ID")
    @JsonIgnore
    private CertificateInfo certificateInfo;
	
	public CertificateInfo getCertificateInfo() {
		return certificateInfo;
	}

	public void setCertificateInfo(CertificateInfo certificateInfo) {
		this.certificateInfo = certificateInfo;
	}

	@Transient
	private String certificateId;
	
	@Transient
	private String operateUserName;
	@Transient
	private String businessType;
	@Transient
	private Date startTime;
	@Transient
	private Date endTime;
	@Transient
	private String certificateTypeShow;
	@Transient
	private String certificateNoShow;
	@Transient
	private String departNameShow;
	@Transient
	private String certificateName;//证照姓名
	
	public String getCertificateName() {
		if(certificateInfo!=null){
			return certificateInfo.getName();
		}
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getOperateUserDepartName(){
		if(operateUser!=null&&operateUser.getDepart()!=null){
			return operateUser.getDepartName();
		}
		return null;
	}
	

	public String getCertificateTypeShow() {
		if(certificateInfo!=null){
			if(!StringUtil.isEmpty(certificateInfo.getCertificateType())){
				return CertificationTypeEnum.getItemNameByItemValue(certificateInfo.getCertificateType());
			} 
		}
		return certificateTypeShow;
	}

	public String getCertificateNoShow() {
		if(certificateInfo!=null){
			return certificateInfo.getZjhm();
		}
		return certificateNoShow;
	}

	public String getDepartNameShow() {
		if(certificateInfo!=null&&null!=certificateInfo.getBelongDepart()){
			return certificateInfo.getBelongDepart().getName();
		}
		return departNameShow;
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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOperateUserName() {
		if(operateUser!=null){
			return operateUser.getName();
		}
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public CertificateOperateLog(){
		
	}
	
	public CertificateOperateLog(String operateType, String operateContent, User operateUser, Date operateTime,
			CertificateInfo certificateInfo) {
		super();
		this.operateType = operateType;
		this.operateContent = operateContent;
		this.operateUser = operateUser;
		this.operateTime = operateTime;
		this.certificateInfo = certificateInfo;
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
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	
	public String getCertificateId() {
		if(certificateInfo!=null){
			return certificateInfo.getId();
		}
		return certificateId;
	}
}
