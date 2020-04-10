package com.gwideal.sms.entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;

/**
 * @author zhou_liang
 */
@Entity
@Table(name="comm_sms_pool")
public class SmsPool extends GenericEntityNow{
	
	private static final long serialVersionUID = -2612153361224522234L;
	
	@Column(name="sms_content")
	private String content;//发送内容
	
	@Column(name="receive_mobile")
	private String receiveMobile;//接收号码

	@Column(name="sender")
	private String sender;//发送人名称

	@Column(name="plan_send_time")
	private Date planSendTime;//计划发送时间
	
	@Transient
	private Date planSendTimes;
	
	@Transient
	private Date planSendTimee;

	@Column(name="send_time")
	private Date sendTime;//发送时间

	@Column(name="receive_person")
	private String receivePerson;//新添加的字段，短信接收人
	
	@Column(name="sms_state")
	private String smsState;//发送状态 10 = 未发送, 12 = 发送成功, 0 = 发送失败 14 = 取消发送

	@Column(name="is_resend")
	private Integer isResend;//是否重复发送

	@Column(name="resend_max")
	private Integer resendMax;//最大发送次数

	@Column(name="RESEND_TIMES")
	private Integer resendTimes;//重复发送次数

	@Column(name="error_info")
	private String errorInfo;
	
	@Transient
	private String beginDate;//开始时间

	@Transient
	private String endDate;//结束时间

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setReceiveMobile(String receiveMobile){
		this.receiveMobile = receiveMobile;
	}

	public String getReceiveMobile(){
		return receiveMobile;
	}

	public void setSender(String sender){
		this.sender = sender;
	}

	public String getSender(){
		return sender;
	}

	public void setPlanSendTime(Date planSendTime){
		this.planSendTime = planSendTime;
	}

	public Date getPlanSendTime(){
		return planSendTime;
	}
	
	public String getPlanSendTimeName(){
		String str = "";
		if (planSendTime!=null) {
			str = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(planSendTime);
		}
		return str;
	}

	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}

	public Date getSendTime(){
		return sendTime;
	}

	public void setSmsState(String smsState){
		this.smsState = smsState;
	}

	public String getSmsState(){
		return smsState;
	}
	
	public String getSmsStateName(){
		String str = "";
		if (!StringUtil.isEmpty(smsState)) {
			if ("0".equals(smsState)) {
				str = "发送失败";
			} else if ("10".equals(smsState)) {
				str = "未发送";
			} else if ("12".equals(smsState)) {
				str = "发送成功";
			} else if ("14".equals(smsState)) {
				str = "取消发送";
			}
		}
		return str;
	}

	public void setIsResend(Integer isResend){
		this.isResend = isResend;
	}

	public Integer getIsResend(){
		return isResend;
	}

	public void setResendMax(Integer resendMax){
		this.resendMax = resendMax;
	}

	public Integer getResendMax(){
		return resendMax;
	}

	public Integer getResendTimes() {
		return resendTimes;
	}

	public void setResendTimes(Integer resendTimes) {
		this.resendTimes = resendTimes;
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Date getPlanSendTimes() {
		return planSendTimes;
	}

	public void setPlanSendTimes(Date planSendTimes) {
		this.planSendTimes = planSendTimes;
	}

	public Date getPlanSendTimee() {
		return planSendTimee;
	}

	public void setPlanSendTimee(Date planSendTimee) {
		this.planSendTimee = planSendTimee;
	}
	
}