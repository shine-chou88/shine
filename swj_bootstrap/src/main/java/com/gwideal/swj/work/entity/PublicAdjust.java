package com.gwideal.swj.work.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.core.model.Depart;
import com.gwideal.swj.certificate.entity.CertificateInfo;

//${comments}
@Entity
@Table(name = "T_PUBLIC_ADJUST")
public class PublicAdjust extends GenericEntityNow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269063943594812981L;
	//单位
	@ManyToOne
    @JoinColumn(name = "DEPART_ID")
    @JsonIgnore
    @NotFound(action=NotFoundAction.IGNORE)
	private Depart depart;
	//填写人姓名
	@Column(name = "WRITE_USER_NAME")
	private String writeUserName;
	//系统内调动后单位名称
	@ManyToOne
	@JoinColumn(name = "AFTER_DEPART_INNER")
	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	private Depart afterDepartInner;
	//系统外调动后单位名称
	@Column(name = "AFTER_DEPART_OUTER")
	private String afterDepartOuter;
	//是否退休
	@Column(name = "HAS_RETIRED")
	private String hasRetired;
	//填写人电话
	@Column(name = "WRITE_USER_PHONE")
	private String writeUserPhone;
	//填写日期
	@Column(name = "WRITE_DATE")
	private Date writeDate;
	//状态
	@Column(name = "STATUS")
	private String status;
	//其他
	@Column(name = "OTHER")
	private String other;
	
	@ManyToMany(targetEntity = CertificateInfo.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinTable(name="T_PUBLIC_ADJUST_CERTIFICATE",joinColumns=@JoinColumn(name="PUBLIC_ADJUST_ID"),inverseJoinColumns=@JoinColumn(name="CERTIFICATE_ID"))
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<CertificateInfo> certificateList;
	
	public List<CertificateInfo> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<CertificateInfo> certificateList) {
		this.certificateList = certificateList;
	}

	public void addCertificate(CertificateInfo info){
		this.getCertificateList().add(info);
		info.getPublicAdjustList().add(this);
	}
	
	public void removeCertificate(CertificateInfo info){
        this.getCertificateList().remove(info);
        info.getPublicAdjustList().add(this);
    }
	
	public void removeAllCertificate(){
		this.certificateList=new ArrayList<CertificateInfo>();
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public Depart getAfterDepartInner() {
		return afterDepartInner;
	}

	public void setAfterDepartInner(Depart afterDepartInner) {
		this.afterDepartInner = afterDepartInner;
	}

	public void setWriteUserName(String writeUserName) {
		this.writeUserName = writeUserName;
	}
	
	public String getWriteUserName() {
		return writeUserName;
	}
	public void setAfterDepartOuter(String afterDepartOuter) {
		this.afterDepartOuter = afterDepartOuter;
	}
	
	public String getAfterDepartOuter() {
		return afterDepartOuter;
	}
	public void setHasRetired(String hasRetired) {
		this.hasRetired = hasRetired;
	}
	
	public String getHasRetired() {
		return hasRetired;
	}
	public void setWriteUserPhone(String writeUserPhone) {
		this.writeUserPhone = writeUserPhone;
	}
	
	public String getWriteUserPhone() {
		return writeUserPhone;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	public Date getWriteDate() {
		return writeDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	public String getOther() {
		return other;
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
	private String departName;

	public String getDepartName() {
		if(depart!=null){
			return depart.getName();
		}
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
	@Transient
	private String afterDepartInnerName;

	public String getAfterDepartInnerName() {
		if(afterDepartInner!=null){
			return afterDepartInner.getName();
		}
		return afterDepartInnerName;
	}

	public void setAfterDepartInnerName(String afterDepartInnerName) {
		this.afterDepartInnerName = afterDepartInnerName;
	}
	
	@Transient
	private String departId;

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
	
	
	
	
}
