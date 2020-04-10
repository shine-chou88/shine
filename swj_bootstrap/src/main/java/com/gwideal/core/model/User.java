package com.gwideal.core.model;

import java.sql.Timestamp;
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

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.Combobox;
import com.gwideal.common.entity.EntityDao;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;

@Entity
@Table(name = "sys_user")
public class User extends GenericEntityNow implements EntityDao,Combobox{
	private static final long serialVersionUID = 1971979413156827034L;

	@Column(name="wrong_num")
	private Integer wrongNum;
	
	@Column(name = "is_locked")
	private String islocked = "FALSE";
	
	@Column(name = "accountNo")
	private String accountNo;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "post")
	private String post;

	@Column(name = "postLevel")
	private String postLevel;

	@Column(name = "mobileNo")
	private String mobileNo;

	@Column(name = "telNo")
	private String telNo;

	@Column(name = "faxNo")
	private String faxNo;

	@Column(name = "address")
	private String address;

	@Column(name = "postalCode")
	private String postalCode;
	
	@Column(name = "keyCode")
	private String keyCode;
	
	@Column(name = "invalidTime")
	private Timestamp invalidTime;
	
	@Column(name="CERT_UNIQUE_ID")
	private String certUniqueId;//ukey唯一标示
	
	@Column(name="LOGIN_COUNT")
	private Integer loginCount;//登录次数
	
	@Column(name="LAST_LOGIN_IP")
	private String lastLoginIp;//最后登录IP
	
	@Column(name="LAST_LOGIN_TIME")
	private Date lastLoginTime;//最后登录时间
	
	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "departid")
	@JsonIgnore
	private Depart depart;

	@ManyToMany(targetEntity = Role.class, mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<Role> roles;
	
	@ManyToMany(targetEntity = Function.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinTable(name="SYS_USER_FUNCTION",joinColumns=@JoinColumn(name="USER_ID"),inverseJoinColumns=@JoinColumn(name="FUNCTION_ID"))
	@Where(clause = "pflag = '1'")
	@JsonIgnore
	private List<Function> functions;
	
	@Transient
	private String isKey;
	
	@Transient
	private String checked;
	
	@Column(name="ID_CARD")
	private String idCard;
	
	@Column(name="DEPART_APPROVER")
	private String departApprover;//部门审批人，工会主席（正处）在“局组织人事处（老干部处）”为副处长，需要特殊处理
	
	public String getDepartApprover() {
		return departApprover;
	}

	public void setDepartApprover(String departApprover) {
		this.departApprover = departApprover;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getWrongNum() {
		return wrongNum;
	}

	public void setWrongNum(Integer wrongNum) {
		this.wrongNum = wrongNum;
	}
	
	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginTimeStr(){
		if(null!=lastLoginTime){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(lastLoginTime);
		}else{
			return "";
		}
	}
	
	public void addFunction(Function function){
		this.getFunctions().add(function);
		function.getUsers().add(this);
	}
	
	public void removeFunction(Function function){
        this.getFunctions().remove(function);
        function.getUsers().add(this);
    }
	
	public void removeAllFunction(){
		this.functions=new ArrayList<Function>();
	}
	
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	/**
	 * User和Role是多对多关系，主控方在Role 为User添加Role时，要在两端都添加，由Role相关系表中添加记录	 */
	public void addRole(Role role) {
		this.getRoles().add(role);
		role.getUsers().add(this);
	}

	/**
	 * User和Role是多对多关系，主控方在Role 为User删除Role时，要在两端都删除，由Role相关系表中添加记录	 */
	public void removeRole(Role role) {
		this.getRoles().remove(role);
		role.getUsers().remove(this);
	}
	
	public String getCertUniqueId() {
		return certUniqueId;
	}

	public void setCertUniqueId(String certUniqueId) {
		this.certUniqueId = certUniqueId;
	}

	public Depart getDepart() {
		return depart;
	}
	
	/**
	 * 判断用户是否有该角色
	 * @param roleCode 角色代码
	 * @return
	 */
	public boolean hasRole(String roleCode){
		boolean flag=false;
		if(!StringUtil.isEmpty(roleCode) && null!=roles && roles.size()>0){
			for(Role r:roles){
				if(roleCode.equals(r.getCode())){
					flag=true;
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 是否锁定
	 * @return
	 */
	public String getLocked(){
		if(!StringUtil.isEmpty(islocked) && "TRUE".equals(islocked)){
			return "是";
		}
		return "否";
	}
	
	public String getDepartId(){
		String departId="";
		if(null!=depart){
			departId=depart.getId();
		}
		return departId;
	}
	
	public String getDepartName(){
		String departName="";
		if(null!=depart){
			departName=depart.getName();
		}
		return departName;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	// 实现Authentication接口的方法
	public String getAuthAccount() {
		// TODO Auto-generated method stub
		return accountNo;
	}

	public String getAuthPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getIslocked() {
		return islocked;
	}

	public void setIslocked(String islocked) {
		this.islocked = islocked;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return getName();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public Timestamp getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Timestamp invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getGridCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSftjCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJoinTable() {
		// TODO Auto-generated method stub
		return "SYS_USER";
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
}
