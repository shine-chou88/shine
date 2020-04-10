package com.gwideal.swj.work.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;

//${comments}
@Entity
@Table(name = "T_PUBLIC_BUSINESS")
public class PublicBusiness extends GenericEntityNow implements Serializable {
	
	private static final long serialVersionUID = -6534619104160961826L;
	
	public static final String USE_REGISTER_TODO="领用草稿";
	public static final String USE_REGISTER="领用待确认";
	public static final String USE_CONFRIM="待领用";
	public static final String USE_COMPLETE="领用完成";
	public static final String BACK_REGISTER_TODO="归还草稿";
	public static final String BACK_REGISTER="归还待确认";
	public static final String BACK_CONFRIM="待归还";
	public static final String BACK_COMPLETE="归还完成";
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

	//组团单位
	@ManyToOne
    @JoinColumn(name = "GROUP_DEPART_ID")
    @JsonIgnore
    @NotFound(action=NotFoundAction.IGNORE)
	private Depart groupDepart;
	//批件号
	@Column(name = "APPROVAL_NO")
	private String approvalNo;
	//计划出境日期
	@Column(name = "PLAN_EXIT_TIME")
	private Date planExitTime;
	//计划入境日期
	@Column(name = "PLAN_ENTER_TIME")
	private Date planEnterTime;
	//领用人姓名
	@Column(name = "APPLY_USER_NAME")
	private String applyUserName;
	//领用人单位
	@Column(name = "APPLY_USER_DEPART_NAME")
	private String applyUserDepartName;
	//领用人电话
	@Column(name = "APPLY_USER_PHONE")
	private String applyUserPhone;
	//领用日期
	@Column(name = "APPLY_DATE")
	private Date applyDate;
	//实际出境日期
	@Column(name = "REAL_EXIT_TIME")
	private Date realExitTime;
	//实际入境日期
	@Column(name = "REAL_ENTER_TIME")
	private Date realEnterTime;
	//实际出访国家地区(含经停城市)
	@Column(name = "REAL_VISIT_COUNTRY")
	private String realVisitCountry;
	//有无超天数超国家等违规情况
	@Column(name = "HAS_VIOLATION_SITUATION")
	private String hasViolationSituation;
	//归还人姓名
	@Column(name = "BACK_USER_NAME")
	private String backUserName;
	//归还人单位
	@Column(name = "BACK_USER_DEPART_NAME")
	private String backUserDepartName;
	//归还人电话
	@Column(name = "BACK_USER_PHONE")
	private String backUserPhone;
	//归还日期
	@Column(name = "BACK_DATE")
	private Date backDate;
	//状态（证照登记，证照领用，归还等等）
	@Column(name = "USE_STATUS")
	private String useStatus;
	
	@Column(name="BACK_STATUS")
	private String backStatus;
	
	//实际领用日期
	@Column(name = "REAL_APPLY_DATE")
	private Date realApplyDate;
	
	//实际归还日期
	@Column(name = "REAL_BACK_DATE")
	private Date realBackDate;
	
	@Column(name="APPLY_SUBMIT_TIME")
	private Date applySubmitTime;
	
	@Column(name="BACK_SUBMIT_TIME")
	private Date backSubmitTime;
	
	public Date getApplySubmitTime() {
		return applySubmitTime;
	}
	
	@ManyToOne
    @JoinColumn(name = "BACK_REGISTER_USER")
    @JsonIgnore
	private User backRegisterUser;
	
	public User getBackRegisterUser() {
		return backRegisterUser;
	}

	public void setBackRegisterUser(User backRegisterUser) {
		this.backRegisterUser = backRegisterUser;
	}

	public void setApplySubmitTime(Date applySubmitTime) {
		this.applySubmitTime = applySubmitTime;
	}

	public Date getBackSubmitTime() {
		return backSubmitTime;
	}

	public void setBackSubmitTime(Date backSubmitTime) {
		this.backSubmitTime = backSubmitTime;
	}

	public Date getRealApplyDate() {
		return realApplyDate;
	}

	public void setRealApplyDate(Date realApplyDate) {
		this.realApplyDate = realApplyDate;
	}

	public Date getRealBackDate() {
		return realBackDate;
	}

	public void setRealBackDate(Date realBackDate) {
		this.realBackDate = realBackDate;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}


	@ManyToMany(targetEntity = CertificateInfo.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinTable(name="T_PUBLIC_BUSINESS_CERTIFICATE",joinColumns=@JoinColumn(name="PUBLIC_BUSINESS_ID"),inverseJoinColumns=@JoinColumn(name="CERTIFICATE_ID"))
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<CertificateInfo> certificateInfos;
	
	public List<CertificateInfo> getCertificateInfos() {
		return certificateInfos;
	}

	public void setCertificateInfos(List<CertificateInfo> certificateInfos) {
		this.certificateInfos = certificateInfos;
	}

	public void addCertificateInfo(CertificateInfo info){
		this.getCertificateInfos().add(info);
		info.getPublicBusinessList().add(this);
	}
	
	public void removeCertificateInfo(CertificateInfo info){
        this.getCertificateInfos().remove(info);
        info.getPublicBusinessList().add(this);
    }
	
	public void removeAllCertificateInfo(){
		this.certificateInfos=new ArrayList<CertificateInfo>();
	}
	
	
	public Depart getGroupDepart() {
		return groupDepart;
	}

	public void setGroupDepart(Depart groupDepart) {
		this.groupDepart = groupDepart;
	}


	@Transient
	private String groupDepartId;
	public void setGroupDepartId(String groupDepartId) {
		this.groupDepartId = groupDepartId;
	}
	
	public String getGroupDepartId() {
		return groupDepartId;
	}
	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}
	
	public String getApprovalNo() {
		return approvalNo;
	}
	public void setPlanExitTime(Date planExitTime) {
		this.planExitTime = planExitTime;
	}
	
	public Date getPlanExitTime() {
		return planExitTime;
	}
	public void setPlanEnterTime(Date planEnterTime) {
		this.planEnterTime = planEnterTime;
	}
	
	public Date getPlanEnterTime() {
		return planEnterTime;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserDepartName(String applyUserDepartName) {
		this.applyUserDepartName = applyUserDepartName;
	}
	
	public String getApplyUserDepartName() {
		return applyUserDepartName;
	}
	public void setApplyUserPhone(String applyUserPhone) {
		this.applyUserPhone = applyUserPhone;
	}
	
	public String getApplyUserPhone() {
		return applyUserPhone;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public Date getApplyDate() {
		return applyDate;
	}
	public void setRealExitTime(Date realExitTime) {
		this.realExitTime = realExitTime;
	}
	
	public Date getRealExitTime() {
		return realExitTime;
	}
	public void setRealEnterTime(Date realEnterTime) {
		this.realEnterTime = realEnterTime;
	}
	
	public Date getRealEnterTime() {
		return realEnterTime;
	}
	public void setRealVisitCountry(String realVisitCountry) {
		this.realVisitCountry = realVisitCountry;
	}
	
	public String getRealVisitCountry() {
		return realVisitCountry;
	}
	public void setHasViolationSituation(String hasViolationSituation) {
		this.hasViolationSituation = hasViolationSituation;
	}
	
	public String getHasViolationSituation() {
		return hasViolationSituation;
	}
	public void setBackUserName(String backUserName) {
		this.backUserName = backUserName;
	}
	
	public String getBackUserName() {
		return backUserName;
	}
	public void setBackUserDepartName(String backUserDepartName) {
		this.backUserDepartName = backUserDepartName;
	}
	
	public String getBackUserDepartName() {
		return backUserDepartName;
	}
	public void setBackUserPhone(String backUserPhone) {
		this.backUserPhone = backUserPhone;
	}
	
	public String getBackUserPhone() {
		return backUserPhone;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	
	public Date getBackDate() {
		return backDate;
	}
	@Transient
	private String certificateIds;

	public String getCertificateIds() {
		return certificateIds;
	}

	public void setCertificateIds(String certificateIds) {
		this.certificateIds = certificateIds;
	}
	@Transient
	private String groupDepartName;

	public String getGroupDepartName() {
		if(groupDepart!=null){
			return groupDepart.getName();
		}
		return groupDepartName;
	}

	public void setGroupDepartName(String groupDepartName) {
		this.groupDepartName = groupDepartName;
	}
	
	@Transient
	private Boolean hasSubmit;

	public Boolean getHasSubmit() {
		return hasSubmit;
	}

	public void setHasSubmit(Boolean hasSubmit) {
		this.hasSubmit = hasSubmit;
	}
	
	@Transient
	private String certificateNames;
	public String getCertificateNames(){
		StringBuilder sbCertis=new StringBuilder();
		if(certificateInfos!=null&&certificateInfos.size()>0){
			for (CertificateInfo certificateInfo : certificateInfos) {
				sbCertis.append("<span id='"+certificateInfo.getId()+"_scanSpan'/>"+certificateInfo.getZjhm()+"-"+certificateInfo.getName()+"  ");
			}
			return sbCertis.toString();
		}
		return "";
	}
	
	@Transient
	private String scanType;

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}
	
	@Transient
	private String shouldScanNum;

	public String getShouldScanNum() {
		if(certificateInfos!=null&&certificateInfos.size()>0){
			return certificateInfos.size()+"";
		}
		return shouldScanNum;
	}

	public void setShouldScanNum(String shouldScanNum) {
		this.shouldScanNum = shouldScanNum;
	}
	
	@Transient
	private String businessType;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	
	
	
}
