package com.gwideal.swj.certificate.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;

//因私出国(境)申请
@Entity
@Table(name = "T_CERTIFICATE_INFO_APPLY")
public class CertificateInfoApply extends GenericEntityNow implements Serializable {
	private static final long serialVersionUID = 3733134492539410L;
	
	//申请流水号
	@Column(name = "WCODE")
	private String wcode;
	//出国开始时间
	@Column(name = "START_DATE")
	private Date startDate;
	//出国结束时间
	@Column(name = "END_DATE")
	private Date endDate;
	//出国原因
	@Column(name = "REASON")
	private String reason;
	//出国目的地
	@Column(name = "DESTINATION")
	private String destination;
	//行程路线
	@Column(name = "ITINERARY")
	private String itinerary;
	//出国天数
	@Column(name = "DAYS")
	private String days;
	//申请办理证件
	@Column(name = "APPLY_HANDLE_TYPE")
	private String applyHandleType;
	//申请领用证件
	@Column(name = "APPLY_USE_TYPE")
	private String applyUseType;
	//是否是党员
	@Column(name = "HAS_PARTY")
	private String hasParty;
	//申请时间
	@Column(name = "APPLY_TIME")
	private Date applyTime;
	//审核状态
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;//draft-草稿;toApprove-待审批;approved-已通过;rejected-不通过;returned-已退回;canceled-取消;archived-已归档
	//流程实例ID
	@Column(name = "PROCESS_INSTANCE_ID")
	private String processInstanceId;
	//流程取消原因
	@Column(name = "REASON_OF_CANCEL")
	private String reasonOfCancel;
	
	@Column(name = "REAL_USE_DATE")
	private Date realUseDate;
	
	@Column(name = "REAL_BACK_DATE")
	private Date realBackDate;
	
	@Column(name="FILE_SUMMARY")
	private String fileSummary;//归档总结
	
	@Column(name="PROCESS_DEFINITION_KEY")
	private String processDefinitionKey;//流程定义key：DEPUTY_CADRES_PASSPORT_APPLY=副处；OFFICAL_CADRES_PASSPORT_APPLY=正处
	
	@ManyToMany(targetEntity = CertificateInfo.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinTable(name="T_CERTIFICATE_APPLY",joinColumns=@JoinColumn(name="APPLY_ID"),inverseJoinColumns=@JoinColumn(name="CERTIFICATE_ID"))
	@JsonIgnore
	private List<CertificateInfo> certificates;
	
	
	//因私出国记录开始时间
	@Column(name = "PRIVATE_START")
	private Date privateStart;
	//因私出国记录结束时间
	@Column(name = "PRIVATE_END")
	private Date privateEnd;
	//因私出国记录原因
	@Column(name = "PRIVATE_REASON")
	private String privateReason;
	//因私出国记录目的地
	@Column(name = "PRIVATE_DESTINATION")
	private String privateDestination;
	
	//因公出国记录开始时间
	@Column(name = "PUBLIC_START")
	private Date publicStart;
	//因公出国记录结束时间
	@Column(name = "PUBLIC_END")
	private Date publicEnd;
	//因公出国记录原因
	@Column(name = "PUBLIC_REASON")
	private String publicReason;
	//因公出国记录目的地
	@Column(name = "PUBLIC_DESTINATION")
	private String publicDestination;
	
	@Column(name = "PRIVATE_DATA_AUTO")
	private String auto;
	
	@Column(name = "PRIVATE_DATE_DESC")
	private String privateDateDesc;
	@Column(name = "PUBLIC_DATE_DESC")
	private String publicDateDesc;
	
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

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
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

	public void addCertificateInfo(CertificateInfo certificateInfo){
		this.getCertificates().add(certificateInfo);
		certificateInfo.getApplys().add(this);
	}
	
	public void removeCertificateInfo(CertificateInfo certificateInfo){
        this.getCertificates().remove(certificateInfo);
        certificateInfo.getApplys().add(this);
    }
	
	public void removeAllCertificate(){
		this.certificates=new ArrayList<CertificateInfo>();
	}
	
	public List<CertificateInfo> getCertificates() {
		return certificates;
	}
	public void setCertificates(List<CertificateInfo> certificates) {
		this.certificates = certificates;
	}
	
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getFileSummary() {
		return fileSummary;
	}

	public void setFileSummary(String fileSummary) {
		this.fileSummary = fileSummary;
	}

	public Date getRealUseDate() {
		return realUseDate;
	}

	public void setRealUseDate(Date realUseDate) {
		this.realUseDate = realUseDate;
	}

	public Date getRealBackDate() {
		return realBackDate;
	}

	public void setRealBackDate(Date realBackDate) {
		this.realBackDate = realBackDate;
	}

	@Transient
	private Date applyStartTime;
	@Transient
	private Date applyEndTime;
	
	public String getCreatorId(){
		String creatorId="";
		if(null!=getCreator() && !StringUtil.isEmpty(getCreator().getId())){
			creatorId=getCreator().getId();
		}
		return creatorId;
	}
	
	public String getReasonOfCancel() {
		return reasonOfCancel;
	}

	public void setReasonOfCancel(String reasonOfCancel) {
		this.reasonOfCancel = reasonOfCancel;
	}

	public Date getApplyStartTime() {
		return applyStartTime;
	}

	public void setApplyStartTime(Date applyStartTime) {
		this.applyStartTime = applyStartTime;
	}

	public Date getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public String getAuditStatusStr(){
		String auditStatusStr="";
		if("draft".equals(auditStatus)){
			auditStatusStr="草稿";
		}else if("toApprove".equals(auditStatus)){
			auditStatusStr="待审批";
		}else if("approved".equals(auditStatus)){
			auditStatusStr="已通过";
		}else if("rejected".equals(auditStatus)){
			auditStatusStr="不通过";
		}else if("returned".equals(auditStatus)){
			auditStatusStr="已退回";
		}else if("canceled".equals(auditStatus)){
			auditStatusStr="已取消";
		}else if("archived".equals(auditStatus)){
			auditStatusStr="已归档";
		}
		return auditStatusStr;
	}
	
	public String getCreatorName(){
		String creatorName="";
		if(null!=getCreator() && !StringUtil.isEmpty(getCreator().getName())){
			creatorName=getCreator().getName();
		}
		return creatorName;
	}

	public String getWcode() {
		return wcode;
	}

	public void setWcode(String wcode) {
		this.wcode = wcode;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
	
	public String getItinerary() {
		return itinerary;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getDays() {
		return days;
	}
	public void setApplyHandleType(String applyHandleType) {
		this.applyHandleType = applyHandleType;
	}
	
	public String getApplyHandleType() {
		return applyHandleType;
	}
	public void setApplyUseType(String applyUseType) {
		this.applyUseType = applyUseType;
	}
	
	public String getApplyUseType() {
		return applyUseType;
	}
	public void setHasParty(String hasParty) {
		this.hasParty = hasParty;
	}
	
	public String getHasParty() {
		return hasParty;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	
	@Transient
	private List<String> processInstanceIdList;

	public List<String> getProcessInstanceIdList() {
		return processInstanceIdList;
	}

	public void setProcessInstanceIdList(List<String> processInstanceIdList) {
		this.processInstanceIdList = processInstanceIdList;
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
	private String useTypeOne;//普通护照
	@Transient
	private String useTypeTwo;//港澳
	@Transient
	private String useTypeThree;//台湾
	
	@Transient
	private String receiveTypeOne;//普通护照
	@Transient
	private String receiveTypeTwo;//港澳
	@Transient
	private String receiveTypeThree;//台湾
	
	public String getReceiveTypeOne() {
		return receiveTypeOne;
	}

	public void setReceiveTypeOne(String receiveTypeOne) {
		this.receiveTypeOne = receiveTypeOne;
	}

	public String getReceiveTypeTwo() {
		return receiveTypeTwo;
	}

	public void setReceiveTypeTwo(String receiveTypeTwo) {
		this.receiveTypeTwo = receiveTypeTwo;
	}

	public String getReceiveTypeThree() {
		return receiveTypeThree;
	}

	public void setReceiveTypeThree(String receiveTypeThree) {
		this.receiveTypeThree = receiveTypeThree;
	}

	public String getUseTypeOne() {
		return useTypeOne;
	}

	public void setUseTypeOne(String useTypeOne) {
		this.useTypeOne = useTypeOne;
	}

	public String getUseTypeTwo() {
		return useTypeTwo;
	}

	public void setUseTypeTwo(String useTypeTwo) {
		this.useTypeTwo = useTypeTwo;
	}

	public String getUseTypeThree() {
		return useTypeThree;
	}

	public void setUseTypeThree(String useTypeThree) {
		this.useTypeThree = useTypeThree;
	}

	@Transient
	private String shouldScanNum;
	
	
	public String getShouldScanNum() {
		if(!StringUtil.isEmpty(applyUseType)){
			String[] arr = applyUseType.split(",");
			return arr.length+"";
		}
		return shouldScanNum;
	}

	public void setShouldScanNum(String shouldScanNum) {
		this.shouldScanNum = shouldScanNum;
	}
	
	@Transient
	private String curTaskId;
	

	public String getCurTaskId() {
		return curTaskId;
	}

	public void setCurTaskId(String curTaskId) {
		this.curTaskId = curTaskId;
	}

	
	@Transient
	private String nextAssignee;

	public String getNextAssignee() {
		return nextAssignee;
	}

	public void setNextAssignee(String nextAssignee) {
		this.nextAssignee = nextAssignee;
	}
	
	/**
	 * activiti title
	 * @return
	 */
	public String getTitle(){
		StringBuffer title=new StringBuffer(getCreatorName()+"申请");
		if(!StringUtil.isEmpty(applyUseType)){
			title.append("领用");
			appendTitle(title,applyUseType);
		}else{
			title.append("办理");
			appendTitle(title,applyHandleType);
		}
		String s=title.toString();
		s=s.substring(0, s.length()-1);
		return s;
	}
	
	public void appendTitle(StringBuffer title,String applyType){
		if(applyType.indexOf("privatePassport")>=0){
			title.append("普通护照、");
		}
		if(applyType.indexOf("privateHKAndMacaoPass")>=0){
			title.append("港澳通行证、");
		}
		if(applyType.indexOf("privateTaiwanPass")>=0){
			title.append("台湾通行证、");
		}
	}
	
	@Transient
	private Attachment creatorAssigneeAtt;//申请人的电子签名

	public Attachment getCreatorAssigneeAtt() {
		return creatorAssigneeAtt;
	}

	public void setCreatorAssigneeAtt(Attachment creatorAssigneeAtt) {
		this.creatorAssigneeAtt = creatorAssigneeAtt;
	}
	
	@Transient
	private String privateRecord;
	
	@Transient
	private String publicRecord;

	public String getPrivateRecord() {
		if(!StringUtil.isEmpty(privateDateDesc)&&!StringUtil.isEmpty(privateDestination)&&!StringUtil.isEmpty(privateReason)){
			return privateDateDesc+"赴"+privateDestination+"，"+privateReason;
		}else if("无".equals(publicDestination)){
			return privateDestination;
		}else{
			return " ";
		}
	}

	public void setPrivateRecord(String privateRecord) {
		this.privateRecord = privateRecord;
	}

	public String getPublicRecord() {
		if(!StringUtil.isEmpty(publicDateDesc)&&!StringUtil.isEmpty(publicDestination)&&!StringUtil.isEmpty(publicReason)){
			return publicDateDesc+"赴"+publicDestination+"，"+publicReason;
		}else if("无".equals(publicDestination)){
			return publicDestination;
		}else{
			return " ";
		}
	}

	public void setPublicRecord(String publicRecord) {
		this.publicRecord = publicRecord;
	}
	
	
}
