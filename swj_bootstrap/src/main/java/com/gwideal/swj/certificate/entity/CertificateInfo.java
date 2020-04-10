package com.gwideal.swj.certificate.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.EntityDao;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.User;
import com.gwideal.swj.work.entity.PublicAdjust;
import com.gwideal.swj.work.entity.PublicBusiness;

//${comments}
@Entity
@Table(name = "T_CERTIFICATE_INFO")
public class CertificateInfo extends GenericEntityNow implements EntityDao,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4029721539127040459L;
	//业务类型（因公/因私）
	@Transient
	private String businessType;
	//三种证件类型（因私普通护照,港澳通行证,台湾通行证）
	@Column(name = "CERTIFICATE_TYPE")
	private String certificateType;
	//证件号码或者护照号
	@Column(name = "ZJHM")
	private String zjhm;
	//姓名
	@Column(name = "NAME")
	private String name;
	//性别
	@Column(name = "SEX")
	private String sex;
	//出生日期
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	//出生地点
	@Column(name = "BIRTH_PLACE")
	private String birthPlace;
	//有效期限
	@Column(name = "EFFECTIVE_DATE")
	private Date effectiveDate;
	//签发机关
	@Column(name = "ISSUANCE_AUTHORITY")
	private String issuanceAuthority;
	//签发地点
	@Column(name = "ISSUANCE_PLACE")
	private String issuancePlace;
	//签发日期
	@Column(name = "ISSUANCE_DATE")
	private Date issuanceDate;
	//国家码
	@Column(name = "NATION_CODE")
	private String nationCode;
	//通行证种类或者护照类型
	@Column(name = "TYPE")
	private String type;
	//身份证号码
	@Column(name="SFZHM")
	private String sfzhm;
	//证照状态
	@Column(name="STATUS")
	private String status;
	//是否有使用记录
	@Column(name="HAS_RECORD")
	private Integer hasRecord;
	//二维码路径
	@Column(name="QRCODE_URL")
	private String qrCodeUrl;
	//注销人
	@ManyToOne
    @JoinColumn(name = "CANCEL_USER")
    @JsonIgnore
	private User cancelUser;
	//注销时间
	@Column(name="CANCEL_TIME")
	private Date cancelTime;
	
	//是否点击过打印完成
	@Column(name="HAS_PRINTED")
	private String hasPrinted;
	
	//注销原因
	@Column(name="CANCEL_REMARK")
	private String cancelRemark;
	
	public String getHasPrinted() {
		return hasPrinted;
	}

	public void setHasPrinted(String hasPrinted) {
		this.hasPrinted = hasPrinted;
	}
	
	@ManyToMany(targetEntity = CertificateInfoApply.class,mappedBy = "certificates",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CertificateInfoApply> applys;
	
	public List<CertificateInfoApply> getApplys() {
		return applys;
	}

	public void setApplys(List<CertificateInfoApply> applys) {
		this.applys = applys;
	}
	
	public void addApply(CertificateInfoApply apply) {
		this.getApplys().add(apply);
		apply.getCertificates().add(this);
	}

	public void removeApply(CertificateInfoApply apply) {
		this.getApplys().remove(apply);
		apply.getCertificates().remove(this);
	}
	
	//所在单位
	@ManyToOne
    @JoinColumn(name = "DEPART_ID")
    @JsonIgnore
    @NotFound(action=NotFoundAction.IGNORE)
	private Depart belongDepart;
	
	
	public Depart getBelongDepart() {
		return belongDepart;
	}

	public void setBelongDepart(Depart belongDepart) {
		this.belongDepart = belongDepart;
	}

	@ManyToMany(targetEntity = PublicBusiness.class,mappedBy = "certificateInfos",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<PublicBusiness> publicBusinessList;
	
	
	public List<PublicBusiness> getPublicBusinessList() {
		return publicBusinessList;
	}

	public void setPublicBusinessList(List<PublicBusiness> publicBusinessList) {
		this.publicBusinessList = publicBusinessList;
	}

	public void addPublicBusiness(PublicBusiness business) {
		this.getPublicBusinessList().add(business);
		business.getCertificateInfos().add(this);
	}

	public void removePublicBusiness(PublicBusiness business) {
		this.getPublicBusinessList().remove(business);
		business.getCertificateInfos().remove(this);
	}
	
	public String getQrCodeUrl() {
		if(StringUtil.isEmpty(qrCodeUrl)){
			return getId();
		}
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public Integer getHasRecord() {
		return hasRecord;
	}

	public void setHasRecord(Integer hasRecord) {
		this.hasRecord = hasRecord;
	}

	public String getSfzhm() {
		return sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	
	public String getCertificateType() {
		return certificateType;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	
	public String getZjhm() {
		return zjhm;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setIssuanceAuthority(String issuanceAuthority) {
		this.issuanceAuthority = issuanceAuthority;
	}
	
	public String getIssuanceAuthority() {
		return issuanceAuthority;
	}
	public void setIssuancePlace(String issuancePlace) {
		this.issuancePlace = issuancePlace;
	}
	
	public String getIssuancePlace() {
		return issuancePlace;
	}
	public void setIssuanceDate(Date issuanceDate) {
		this.issuanceDate = issuanceDate;
	}
	
	public Date getIssuanceDate() {
		return issuanceDate;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	
	public String getNationCode() {
		return nationCode;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public User getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(User cancelUser) {
		this.cancelUser = cancelUser;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	
	@Transient
	private String showStatus;

	public String getShowStatus() {
		if(!StringUtil.isEmpty(status)){
			return CertificationStatus.getItemNameByItemValue(status);
		}
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}
	
	@Transient
	private String belongDepartId;


	public String getBelongDepartId() {
		return belongDepartId;
	}

	public void setBelongDepartId(String belongDepartId) {
		this.belongDepartId = belongDepartId;
	}
	
	@ManyToMany(targetEntity = PublicAdjust.class,mappedBy = "certificateList",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<PublicAdjust> publicAdjustList;
	
	
	public List<PublicAdjust> getPublicAdjustList() {
		return publicAdjustList;
	}

	public void setPublicAdjustList(List<PublicAdjust> publicAdjustList) {
		this.publicAdjustList = publicAdjustList;
	}

	public void addPublicAdjust(PublicAdjust adjust) {
		this.getPublicAdjustList().add(adjust);
		adjust.getCertificateList().add(this);
	}

	public void removePublicAdjust(PublicAdjust adjust) {
		this.getPublicAdjustList().remove(adjust);
		adjust.getCertificateList().remove(this);
	}
	
	
	//证照拥有人
	@ManyToOne
	@JoinColumn(name = "OWN_ID")
	@JsonIgnore
	private User owner;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	//实际领用日期
	@Column(name="REAL_USE_DATE")
	private Date realUseTime;
	
	public Date getRealUseTime() {
		return realUseTime;
	}

	public void setRealUseTime(Date realUseTime) {
		this.realUseTime = realUseTime;
	}

	//实际归还日期
	@Column(name="REAL_BACK_DATE")
	private Date realBackTime;
	
	public Date getRealBackTime() {
		return realBackTime;
	}

	public void setRealBackTime(Date realBackTime) {
		this.realBackTime = realBackTime;
	}

	@Override
	public String getJoinTable() {
		// TODO Auto-generated method stub
		return "T_CERTIFICATE_INFO";
	}

	@Override
	public String getEntryId() {
		// TODO Auto-generated method stub
		return this.getId();
	}

	@Override
	public String getEntryCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transient
	public Boolean isBeyondSixMonth;


	public Boolean getIsBeyondSixMonth() {
		if(effectiveDate!=null){
			if(effectiveDate.compareTo(DateUtils.add(new Date(),Calendar.MONTH, 6))>=0){
				return true;
			}
			return false;
		}
		return isBeyondSixMonth;
	}

	public void setIsBeyondSixMonth(Boolean isBeyondSixMonth) {
		this.isBeyondSixMonth = isBeyondSixMonth;
	}
	
	@Transient
	private String certificateTypeShow;


	public String getCertificateTypeShow() {
		if(!StringUtil.isEmpty(certificateType)){
			return CertificationTypeEnum.getItemNameByItemValue(certificateType);
		}
		return certificateTypeShow;
	}

	public void setCertificateTypeShow(String certificateTypeShow) {
		this.certificateTypeShow = certificateTypeShow;
	}
	
	@Transient
	private String briefType;


	public String getBriefType() {
		if(!StringUtil.isEmpty(certificateType)){
			return CertificationTypeEnum.getItemBriefNameByItemValue(certificateType);
		}
		return briefType;
	}

	public void setBriefType(String briefType) {
		this.briefType = briefType;
	}
	
	
	public String getBelongDepartName(){
		if(belongDepart!=null){
			return belongDepart.getName();
		}
		return null;
	}
	
	@Transient
	private String isNotBack;


	public String getIsNotBack() {
		return isNotBack;
	}

	public void setIsNotBack(String isNotBack) {
		this.isNotBack = isNotBack;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}
	
	
}
