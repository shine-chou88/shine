package com.gwideal.swj.certificate.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.activiti.entity.TaskJson;
import com.gwideal.activiti.manager.ProcessMng;
import com.gwideal.activiti.manager.TaskMng;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.WcodeMng;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificateInfoApply;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.entity.CertificationStatus;
import com.gwideal.swj.certificate.entity.PrivateExportEntity;
import com.gwideal.swj.certificate.manager.CertificateInfoApplyMng;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;

@Transactional
@Service
public class CertificateInfoApplyMngImpl extends BaseManagerImpl<CertificateInfoApply> implements CertificateInfoApplyMng {
	
	private static final ResourceBundle resource=ResourceBundle.getBundle("activiti");
	@Autowired
	private ProcessMng processMng;
	@Autowired
	private WcodeMng wcodeMng;
	@Autowired
	private RuntimeService runTimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private CertificateOperateLogMng certificateOperateLogMng;
	@Autowired
	private TaskMng taskMng;
	
	@Override
	public Pagination list(CertificateInfoApply bean, String sort, String order, int pageIndex, int pageSize, boolean isOrganizationStaff, User user) {
		Finder f = Finder.create("from CertificateInfoApply where flag=1 ");
		if(!isOrganizationStaff){
			f.append(" and (creator.id='"+user.getId()+"' or processInstanceId in ");
			f.append(" (Select procInstId from ActHiTaskinst Where assignee='"+user.getId()+"'))");
		}
		if (bean != null) {
			if (!StringUtil.isEmpty(bean.getWcode())) {
				f.append(" and wcode = :wcode").setParam("wcode", bean.getWcode());
			}
			if (null != bean.getStartDate()) {
				f.append(" and startDate = :startDate").setParam("startDate", bean.getStartDate());
			}
			if (null != bean.getEndDate()) {
				f.append(" and endDate = :endDate").setParam("endDate", bean.getEndDate());
			}
			if (!StringUtil.isEmpty(bean.getReason())) {
				f.append(" and reason like :reason").setParam("reason", "%" + bean.getReason() + "%");
			}
			if (!StringUtil.isEmpty(bean.getDestination())) {
				f.append(" and destination like :destination").setParam("destination","%"+bean.getDestination()+"%");
			}
			if (!StringUtil.isEmpty(bean.getItinerary())) {
				f.append(" and itinerary like :itinerary").setParam("itinerary","%"+bean.getItinerary()+"%");
			}
			if (!StringUtil.isEmpty(bean.getDays())) {
				f.append(" and days = :days").setParam("days",bean.getDays());
			}
			if (!StringUtil.isEmpty(bean.getApplyHandleType())) {
				f.append(" and applyHandleType = :applyHandleType").setParam("applyHandleType",bean.getApplyHandleType());
			}
			if (!StringUtil.isEmpty(bean.getApplyUseType())) {
				f.append(" and applyUseType = :applyUseType").setParam("applyUseType",bean.getApplyUseType());
			}
			if (!StringUtil.isEmpty(bean.getAuditStatus())) {
				f.append(" and auditStatus = :auditStatus").setParam("auditStatus",bean.getAuditStatus());
			}
			if (null != bean.getHasParty()) {
				f.append(" and hasParty = :hasParty").setParam("hasParty", bean.getHasParty());
			}
			if (null != bean.getApplyStartTime()) {
				f.append(" and applyTime >= :applyStartTime").setParam("applyStartTime", bean.getApplyStartTime());
			}
			if (null != bean.getApplyEndTime()) {
				f.append(" and applyTime <= :applyEndTime").setParam("applyEndTime", bean.getApplyEndTime());
			}
			List<String> processInstanceIdList = bean.getProcessInstanceIdList();
			if(processInstanceIdList!=null&&processInstanceIdList.size()>0){
				f.append(" and processInstanceId in :idList").setParamList("idList", Arrays.asList(processInstanceIdList.toArray(new String[processInstanceIdList.size()])));
			}else{
				if(!StringUtil.isEmpty(bean.getScanType())){
					return null;
				}
			}
		}
		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			f.append(" order by " + sort + " " + order);
		} else {
			f.append(" order by updateTime desc");
		}
		return super.find(f, pageIndex, pageSize);
	}

	@Override
	public void save(CertificateInfoApply bean, User user) {
		if (StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
		} else {
			bean.setUpdator(user);
		}
		//领用需要绑定证照
		if(!StringUtil.isEmpty(bean.getApplyUseType())){
			//设置证照
			bean.setCertificates(new ArrayList<CertificateInfo>());
			String hql="from CertificateInfo Where flag=1 and certificateType in ("+StringUtil.getSplitString(bean.getApplyUseType(),",")+") and owner.id=? and status='checkIn'";
			List<CertificateInfo> list=super.find(hql,new Object[]{user.getId()});
			for(CertificateInfo c:list){
				if(null==c.getApplys()){
					c.setApplys(new ArrayList<CertificateInfoApply>());
				}
				bean.addCertificateInfo(c);
			}
		}else{
			bean.setCertificates(null);
		}
		bean=(CertificateInfoApply)super.merge(bean);
		if(bean.getAuditStatus().equals("toApprove")){
			if(null==bean.getApplyTime()){
				bean.setApplyTime(new Date());
			}
			//生成流水号
			if(StringUtil.isEmpty(bean.getWcode())){
				String year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
				String wCode=wcodeMng.getWcode(CertificateInfoApply.class.getName(),year);
				bean.setWcode("YSCGJ-"+year+"-"+wCode);
			}
			//开启工作流
			if(StringUtil.isEmpty(bean.getProcessInstanceId())){
				//正职处级干部走正职流程，副职走副职流程
				String processInstanceByKey=null;
				if(user.hasRole("OFFICAL_CADRES")){
					processInstanceByKey=resource.getString("OFFICAL_CADRES_PASSPORT_APPLY_PROCESSDEFINITIONKEY");
				}else{
					processInstanceByKey=resource.getString("DEPUTY_CADRES_PASSPORT_APPLY_PROCESSDEFINITIONKEY");
				}
				String processInstanceId=processMng.startProcessInstance(processInstanceByKey,bean.getId(),user.getId(),StringUtil.NULLSTR);
				bean.setProcessDefinitionKey(processInstanceByKey);
				bean.setProcessInstanceId(processInstanceId);
			}else{
				processMng.completeDraftTask(bean.getProcessInstanceId(),user.getId(),StringUtil.NULLSTR);
			}
			
			setLockCertificateInfo(bean, 1, user);
		}
	}

	@Override
	public void delete(String id, User user) {
		if (!StringUtil.isEmpty(id)) {
			CertificateInfoApply bean = super.findById(id);
			bean.setFlag(0);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			super.save(bean);
			//解锁
			setLockCertificateInfo(bean, 0, bean.getCreator());
		}
	}

	@Override
	public boolean isExist(CertificateInfoApply bean, User user) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer("Select count(*) from CertificateInfoApply Where flag=1 and creator.id = ?");
		if(!StringUtil.isEmpty(bean.getApplyUseType())){
			getIsExistHql("applyUseType",bean.getApplyUseType(),hql);
		}else{
			getIsExistHql("applyHandleType",bean.getApplyHandleType(),hql);
		}
		hql.append(" and auditStatus in ('draft','toApprove','returned')");
		if(!StringUtil.isEmpty(bean.getId())){
			hql.append(" and id <> '"+bean.getId()+"'");
		}
		List list=super.find(hql.toString(), new Object[]{user.getId()});
		if(null!=list && null!=list.get(0) && Integer.valueOf(list.get(0).toString())>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 拼接hql
	 * @param applyType
	 * @param applyTypeValue
	 * @param hql
	 */
	public void getIsExistHql(String applyType,String applyTypeValue,StringBuffer hql){
		hql.append(" and (");
		String[] arr=applyTypeValue.split(",");
		int t=arr.length;
		for (int i = 0; i < t; i++) {
			if(i==t-1){
				hql.append(" "+applyType+" like '%"+arr[i]+"%'");
			}else{
				hql.append(" "+applyType+" like '%"+arr[i]+"%' or");
			}
		}
		hql.append(")");
	}

	@Override
	public void cancel(String id, String reason, User user) {
		// TODO Auto-generated method stub
		CertificateInfoApply bean=super.findById(id);
		bean.setUpdateTime(new Date());
		bean.setUpdator(user);
		bean.setAuditStatus("canceled");
		bean.setReasonOfCancel(reason);
		processMng.cancel(bean.getProcessInstanceId(), reason, user);
		super.save(bean);
		//解锁
		setLockCertificateInfo(bean, 0, bean.getCreator());
		
	}

	@Override
	public CertificateInfoApply findByCertificateIdAndScanType(String id, String type) {
		StringBuilder sb=new StringBuilder();
		sb.append("select A.PID from T_CERTIFICATE_INFO_APPLY A INNER JOIN T_CERTIFICATE_INFO B on A.CREATOR=B.OWN_ID ");
		if("use".equals(type)||"back".equals(type)){
			sb.append(" and ('' ||A.APPLY_USE_TYPE|| '' like '%'||B.CERTIFICATE_TYPE||'%' ) ");
		}else if("receive".equals(type)){
			sb.append(" and ('' ||A.APPLY_HANDLE_TYPE|| '' like '%'||B.CERTIFICATE_TYPE||'%' ) ");
		}
		sb.append("where a.PFLAG='1' and B.PFLAG='1' ");
		String activeNode="";
		if("use".equals(type)){
			sb.append(" and B.status='"+CertificationStatus.CHECKIN.getItemValue()+"' ");
			activeNode="RECEIVE_PASSPORT";
		}else if("back".equals(type)){
			sb.append(" and B.status='"+CertificationStatus.USING.getItemValue()+"' ");
			activeNode="RETURN_PASSPORT";
		}else if("receive".equals(type)){
			sb.append(" and B.status='"+CertificationStatus.CHECKIN.getItemValue()+"' ");
			activeNode="RECEIVE_OLD_PASSPORT";
		}else{
			return null;
		}
		sb.append(" and B.PID='"+id+"' ");
		sb.append(" and A.AUDIT_STATUS='approved' ");
		sb.append("ORDER BY a.PUPDATETIME DESC");
		List<String> idList = super.getSession().createSQLQuery(sb.toString()).list();
		if(idList!=null&&idList.size()>0){
			String applyId = idList.get(0);
			 CertificateInfoApply apply = this.findById(applyId);
			 if(apply!=null&&!StringUtil.isEmpty(apply.getProcessInstanceId())){
				 Execution execution = runTimeService.createExecutionQuery().processInstanceId(apply.getProcessInstanceId()).singleResult();
				 if(!StringUtil.isEmpty(activeNode)&&execution!=null&&activeNode.equals(execution.getActivityId())){
					 Task task = taskService.createTaskQuery().processInstanceId(apply.getProcessInstanceId()).singleResult();
					 if(task!=null){
						 apply.setCurTaskId(task.getId());
						 if("use".equals(type)||"receive".equals(type)){
							 apply.setNextAssignee(apply.getCreator().getId());
						 }
						 return apply;
					 }
				 }
			 }
		}
				
		return null;
	}

	@Override
	public void saveBackScan(String applyId, TaskJson taskJson, User user) {
		CertificateInfoApply apply = this.findById(applyId);
		if(apply!=null&&!StringUtil.isEmpty(apply.getApplyUseType())){
			List<CertificateInfo> infoList = certificateInfoMng.findPrivateByTypeAndOwnId(apply.getApplyUseType().split(","), apply.getCreator().getId());
			if(infoList!=null&&infoList.size()>0){
				for (CertificateInfo certificateInfo : infoList) {
					certificateInfo.setStatus(CertificationStatus.CHECKIN.getItemValue());
					certificateInfo.setRealBackTime(new Date());
					certificateInfoMng.save(certificateInfo);
					CertificateOperateLog operateLog=new CertificateOperateLog("back","归还",user,new Date(),certificateInfo);
	            	certificateOperateLogMng.save(operateLog);
				}
				apply.setRealBackDate(new Date());
				this.merge(apply);
				taskMng.complete(taskJson,apply.getCreator());
			}
		}
		
	}

	@Override
	public void saveUseScan(String applyId, TaskJson taskJson, User user) {
		CertificateInfoApply apply = this.findById(applyId);
		if(apply!=null&&!StringUtil.isEmpty(apply.getApplyUseType())){
			List<CertificateInfo> infoList = certificateInfoMng.findPrivateByTypeAndOwnId(apply.getApplyUseType().split(","), apply.getCreator().getId());
			if(infoList!=null&&infoList.size()>0){
				for (CertificateInfo certificateInfo : infoList) {
					certificateInfo.setStatus(CertificationStatus.USING.getItemValue());
					certificateInfo.setRealUseTime(new Date());
					certificateInfoMng.save(certificateInfo);
					CertificateOperateLog operateLog=new CertificateOperateLog("use","领用",user,new Date(),certificateInfo);
	            	certificateOperateLogMng.save(operateLog);
				}
				apply.setRealUseDate(new Date());
				this.merge(apply);
				taskMng.complete(taskJson,apply.getCreator());
			}
		}
		
	}

	@Override
	public List<PrivateExportEntity> listExportData(String startTime, String endTime, String certificateType,String exportType) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<PrivateExportEntity> returnList=new ArrayList<>();
		Finder f = Finder.create("from CertificateInfoApply where flag=1 ");
		if (!StringUtil.isEmpty(startTime)) {
			f.append(" and applyTime >= :applyStartTime").setParam("applyStartTime", sdf.parse(startTime));
		}
		if (!StringUtil.isEmpty(endTime)) {
			f.append(" and applyTime <= :applyEndTime").setParam("applyEndTime",sdf.parse(endTime));
		}
		if (!StringUtil.isEmpty(certificateType)) {
			
			f.append(" and (applyHandleType like :applyHandleType").setParam("applyHandleType","%"+certificateType+"%");
			
			f.append(" or applyUseType like :applyUseType )").setParam("applyUseType","%"+certificateType+"%");
		}
		f.append(" order by applyTime desc ");
		List<CertificateInfoApply> applyList = super.find(f);
		if(applyList!=null&&applyList.size()>0){
		for (CertificateInfoApply apply : applyList) {
			if(!StringUtil.isEmpty(exportType)){
				if("已归档".equals(exportType)&&StringUtil.isEmpty(apply.getFileSummary())){
					continue;
				}
				if("未归档".equals(exportType)&&!StringUtil.isEmpty(apply.getFileSummary())){
					continue;
				}
			}
			//User creator = apply.getCreator();
			if(!StringUtil.isEmpty(apply.getApplyUseType())||!StringUtil.isEmpty(apply.getApplyHandleType())){
				String[] typeArr = null;
				if(!StringUtil.isEmpty(apply.getApplyUseType())){
					typeArr=apply.getApplyUseType().split(",");
				}
				if(!StringUtil.isEmpty(apply.getApplyHandleType())){
					typeArr=apply.getApplyHandleType().split(",");
				}
				List<CertificateInfo> applyInfoList = apply.getCertificates();
				if(applyInfoList!=null&&applyInfoList.size()>0){
					for (CertificateInfo certificateInfo : applyInfoList) {
						PrivateExportEntity entity=new PrivateExportEntity();
						entity.setName(certificateInfo.getName());
						entity.setZjhm(certificateInfo.getZjhm());
						entity.setDestination(apply.getDestination());
						entity.setStartDate(sdf.format(apply.getStartDate()));
						entity.setEndDate(sdf.format(apply.getEndDate()));
						entity.setRealUseTime(apply.getRealUseDate()!=null?sdf.format(apply.getRealUseDate()):"");
						entity.setRealBackTime(apply.getRealBackDate()!=null?sdf.format(apply.getRealBackDate()):"");
						entity.setReason(apply.getReason());
						entity.setRemark(apply.getFileSummary());
						returnList.add(entity);
					}
					
				}
				int shouldAddNum = typeArr.length;
				int queryNum=applyInfoList==null?0:applyInfoList.size();
				if(shouldAddNum>queryNum){
					for (int i = 0; i < (shouldAddNum-queryNum); i++) {
						PrivateExportEntity entity=new PrivateExportEntity();
						entity.setName(apply.getCreatorName());
						entity.setZjhm("");
						entity.setDestination(apply.getDestination());
						entity.setStartDate(sdf.format(apply.getStartDate()));
						entity.setEndDate(sdf.format(apply.getEndDate()));
						entity.setRealUseTime(apply.getRealUseDate()!=null?sdf.format(apply.getRealUseDate()):"");
						entity.setRealBackTime(apply.getRealBackDate()!=null?sdf.format(apply.getRealBackDate()):"");
						entity.setReason(apply.getReason());
						entity.setRemark(apply.getFileSummary());
						returnList.add(entity);
					}
				}
			 }
		 	}
		}
		return returnList;
	}

	@Override
	public void setLockCertificateInfo(CertificateInfoApply bean,Integer isLock,User user){
		//锁定或解锁证照 1 锁定 0 解锁
		if(!StringUtil.isEmpty(bean.getApplyUseType())){
			String[] typeArr = bean.getApplyUseType().split(",");
			List<CertificateInfo> infos = certificateInfoMng.findPrivateByTypeAndOwnId(typeArr, user.getId());
			if(infos!=null&&infos.size()>0){
				for (CertificateInfo certificateInfo : infos) {
					certificateInfo.setHasRecord(isLock);
					certificateInfoMng.merge(certificateInfo);
				}
			}
			
		}
	}

	@Override
	public CertificateInfoApply findByStatusAndUser(String[] statusArr, User user) {
		Finder f = Finder.create("from CertificateInfoApply where flag=1 ");
		f.append(" and creator.id='"+user.getId()+"'");
		f.append(" and auditStatus in :auditStatusList").setParamList("auditStatusList",Arrays.asList(statusArr));
		f.append(" order by applyTime desc");
		List list = super.find(f);
		if(list!=null&&list.size()>0){
			return (CertificateInfoApply) list.get(0);
		}
		return null;
	}

	@Override
	public void saveReceiveScan(String id, TaskJson taskJson, User user) {
		CertificateInfoApply apply = this.findById(id);
		if(apply!=null&&!StringUtil.isEmpty(apply.getApplyHandleType())){
			List<CertificateInfo> infoList = certificateInfoMng.findPrivateByTypeAndOwnId(apply.getApplyHandleType().split(","), apply.getCreator().getId());
			if(infoList!=null&&infoList.size()>0){
				for (CertificateInfo certificateInfo : infoList) {
					certificateInfo.setStatus(CertificationStatus.CANCEL.getItemValue());
					certificateInfo.setCancelTime(new Date());
					certificateInfo.setCancelUser(user);
					certificateInfo.setCancelRemark("申请领回旧护照");
					certificateInfoMng.save(certificateInfo);
					CertificateOperateLog operateLog=new CertificateOperateLog("receive","申办领回",user,new Date(),certificateInfo);
	            	certificateOperateLogMng.save(operateLog);
				}
				taskMng.complete(taskJson,apply.getCreator());
			}
		}
		
	}
}
