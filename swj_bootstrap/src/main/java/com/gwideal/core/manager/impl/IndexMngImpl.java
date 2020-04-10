package com.gwideal.core.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.activiti.manager.ProcessMng;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.IndexMng;
import com.gwideal.core.manager.UserMng;
import com.gwideal.core.model.UnReadMsg;
import com.gwideal.core.model.User;
import com.gwideal.sms.manager.SmsPoolMng;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.Message;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.work.entity.PublicBusiness;
import com.gwideal.swj.work.manager.PublicBusinessMng;

@Transactional
@Service
public class IndexMngImpl extends BaseManagerImpl<User> implements IndexMng{

	@Autowired
	private ProcessMng processMng;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private PublicBusinessMng publicBusinessMng;
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	private SmsPoolMng smsPoolMng;
	
	@Override
	public int getUnReadMessage(User user) {
		int activeTaskCount = processMng.getActiveTaskCount(user);
		List unReadMsg =null;
		if(user.hasRole("PUBLIC_JBGSGZRY")){
			unReadMsg=publicBusinessMng.getUnReadMsg();
		}
		activeTaskCount=activeTaskCount+(unReadMsg!=null?unReadMsg.size():0);
		return activeTaskCount;
	}

	@Override
	public Pagination getUnReadMessagePagination(User user) {
		// TODO Auto-generated method stub
		List<UnReadMsg> list=new ArrayList<UnReadMsg>();
		//activity 任务
		List<Task> listActivityTask=taskService.createTaskQuery().taskCandidateOrAssigned(user.getId()).active().list();
		if(null!=listActivityTask && listActivityTask.size()>0){
			for(Task task:listActivityTask){
				UnReadMsg msg=new UnReadMsg();
				ProcessInstance instance=runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
				if("DEPUTY_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey())
						|| "OFFICAL_CADRES_PASSPORT_APPLY".equals(instance.getProcessDefinitionKey())){
    				msg.setId(task.getId());
    				msg.setTitle("因私出国(境)申请");
    				msg.setFunctionType("DEPUTY_CADRES_PASSPORT_APPLY");
    			}
				list.add(msg);
			}
		}
		return new Pagination(1, list.size(), list.size(), list);
	}

	@Override
	public List<Message> expireRemindList(User user) {
		List<Message> returnList=null;
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT * FROM ");
		sb.append(" ( ");
		sb.append(" (  SELECT A.*, ROWNUM RN from ");
		sb.append(" ( ");
		sb.append("SELECT NAME || '的' || ( CASE CERTIFICATE_TYPE WHEN 'publicPassport' THEN '因公普通护照' WHEN 'publicHKAndMacaoPass' THEN ");
		sb.append(" '港澳通行证(因公)' WHEN 'publicTaiwanPass' THEN '台湾通行证(因公)' WHEN 'privatePassport' THEN ");
		sb.append(" '因私普通护照' WHEN 'privateHKAndMacaoPass' THEN '港澳通行证(因私)' WHEN 'privateTaiwanPass' THEN '台湾通行证(因私)' ELSE '' END ");
		sb.append(" ) || '(' || ZJHM || '，有效期：'||to_char( EFFECTIVE_DATE, 'yyyy-MM-dd' )||')，已过期' AS TITLE,'viewCertificateInfo' as objectType,PID as objectId  FROM T_CERTIFICATE_INFO WHERE ");
		sb.append(" PFLAG = '1' and status!='cancelled' AND EFFECTIVE_DATE IS NOT NULL AND to_date( to_char( EFFECTIVE_DATE, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )<to_date( to_char( SYSDATE, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )");
		if(!user.hasRole("ORGANIZATION_STAFF")&&!user.hasRole("PUBLIC_JBGSGZRY")){
			sb.append(" and OWN_ID = '"+user.getId()+"'");
		}else if(!user.hasRole("ORGANIZATION_STAFF")&&user.hasRole("PUBLIC_JBGSGZRY")){
			sb.append(" and CERTIFICATE_TYPE like '%public%' ");
		}else if(user.hasRole("ORGANIZATION_STAFF")&&!user.hasRole("PUBLIC_JBGSGZRY")){
			sb.append(" and CERTIFICATE_TYPE like '%private%' ");
		}
		sb.append(" order by PUPDATETIME desc ");
		sb.append(" ) A WHERE ROWNUM <= 30  )  ");
		sb.append(" ) where RN>=1 ");
		List list = super.getSession().createSQLQuery(sb.toString()).list();
		if(list!=null&&list.size()>0){
			returnList=new ArrayList<Message>();
			for (int i = 0; i < list.size(); i++) {
				Message msg=new Message();
				Object[] arr=(Object[])list.get(i);
				msg.setTitle(arr[0]==null?"":arr[0].toString());
				msg.setReleaseTime(new Date());
				msg.setObjectType(arr[1]==null?"":arr[1].toString());
				msg.setObjectId(arr[2]==null?"":arr[2].toString());
				returnList.add(msg);
			}
		}
		return returnList;
	}

	@Override
	public List<Message> notBackReminList(User user) {
		List<Message> returnList=new ArrayList<Message>();;
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT * FROM ");
		sb.append(" ( ");
		sb.append(" (  SELECT D.*, ROWNUM RN from ");
		sb.append(" ( ");
		sb.append(" SELECT C.name || '的' || ( CASE C.CERTIFICATE_TYPE WHEN 'publicPassport' THEN '因公普通护照' WHEN 'publicHKAndMacaoPass' THEN '港澳通行证(因公)' WHEN 'publicTaiwanPass' THEN '台湾通行证(因公)' ELSE '' END ) || '(' || ZJHM || '，应在' || to_char( A.PLAN_ENTER_TIME+7, 'yyyy-MM-dd' ) ||'前归还)，已逾期' AS TITLE,");
		sb.append(" 'viewPublicBusiness' as objectType,A.pid as objectId FROM T_PUBLIC_BUSINESS A INNER JOIN T_PUBLIC_BUSINESS_CERTIFICATE B ON A.pid = B.PUBLIC_BUSINESS_ID ");
		sb.append(" INNER JOIN T_CERTIFICATE_INFO C ON B.CERTIFICATE_ID = C.pid ");
		sb.append(" WHERE A.PFLAG = '1' AND C.PFLAG = '1' AND to_date( to_char( A.PLAN_ENTER_TIME, 'yyyy-MM-dd' ), 'yyyy-mm-dd' ) < to_date( to_char( SYSDATE - 7, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )  ");
		sb.append(" AND ( A.BACK_STATUS IS NULL OR A.BACK_STATUS != '归还完成' ) AND C.STATUS = 'using'");
		if(!user.hasRole("PUBLIC_JBGSGZRY")){
			sb.append(" and C.OWN_ID = '"+user.getId()+"'");
		}
		sb.append(" order by C.PUPDATETIME desc ");
		sb.append(" ) D WHERE ROWNUM <= 10  )  ");
		sb.append(" ) where RN>=1 ");
		List list = super.getSession().createSQLQuery(sb.toString()).list();
		//因私归还提醒
		String sql="SELECT applyInfo.APPLY_USE_TYPE,applyInfo.APPLY_HANDLE_TYPE ,applyInfo.CREATOR,to_char( applyInfo.END_DATE+10, 'yyyy-MM-dd' ),'viewPrivateApply' as objectType,applyInfo.pid as objectId FROM  T_CERTIFICATE_INFO_APPLY applyInfo WHERE applyInfo.PFLAG='1' AND applyInfo.AUDIT_STATUS='approved' AND to_date( to_char( applyInfo.END_DATE, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )<to_date( to_char( SYSDATE - 10, 'yyyy-MM-dd' ), 'yyyy-mm-dd' ) AND REAL_USE_DATE IS NOT NULL AND REAL_BACK_DATE IS NULL";
		if(!user.hasRole("ORGANIZATION_STAFF")){
			sql+=" and applyInfo.CREATOR='"+user.getId()+"' ";
		}
		List privateList = super.getSession().createSQLQuery(sql).list();
		if(privateList!=null&&privateList.size()>0){
			for (int j = 0; j < privateList.size()&&returnList.size()<=10; j++) {
				Object[] arr=(Object[])privateList.get(j);
				String useTypeStr=arr[0]==null?"":arr[0].toString();
				String handleTypeStr=arr[1]==null?"":arr[1].toString();
				String ownUserId=arr[2].toString();
				String backDate=arr[3].toString();
				String objectType=arr[4]==null?"":arr[4].toString();
				String objectId=arr[5]==null?"":arr[5].toString();
				if(!StringUtil.isEmpty(useTypeStr)){
					List<CertificateInfo> useList = certificateInfoMng.findPrivateByTypeAndOwnId(useTypeStr.split(","), ownUserId);
					if(useList!=null&&useList.size()>0){
						for (CertificateInfo certificateInfo : useList) {
							Message msg=new Message();
							String typeName="";
							if(certificateInfo.getCertificateType().contains("privatePassport")){
								typeName="因私普通护照";
							}else if(certificateInfo.getCertificateType().contains("HK")){
								typeName="港澳通行证(因私)";
							}else if(certificateInfo.getCertificateType().contains("Taiwan")){
								typeName="台湾通行证(因私)";
							}
							msg.setTitle(certificateInfo.getName()+"的"+typeName+"("+certificateInfo.getZjhm()+"，应在"+backDate+"前归还)，已逾期");
							msg.setObjectId(objectId);
							msg.setObjectType(objectType);
							returnList.add(msg);
						}
					}
				}
				if(!StringUtil.isEmpty(handleTypeStr)){
					String[] typeArr = handleTypeStr.split(",");
					User owner = userMng.findById(ownUserId);
					if(owner!=null){
						for (int i = 0; i < typeArr.length; i++) {
							Message msg=new Message();
							String typeName="";
							if(typeArr[i].contains("privatePassport")){
								typeName="因私普通护照";
							}else if(typeArr[i].contains("HK")){
								typeName="港澳通行证(因私)";
							}else if(typeArr[i].contains("Taiwan")){
								typeName="台湾通行证(因私)";
							}
							msg.setTitle(owner.getName()+"申请办理的"+typeName+"(应在"+backDate+"前归还)，已逾期");
							msg.setObjectId(objectId);
							msg.setObjectType(objectType);
							returnList.add(msg);
						}
					}
				}
					
				
			}
		}
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size()&&returnList.size()<=10; i++) {
				Message msg=new Message();
				Object[] arr=(Object[])list.get(i);
				msg.setTitle(arr[0]==null?"":arr[0].toString());
				msg.setReleaseTime(new Date());
				msg.setObjectType(arr[1]==null?"":arr[1].toString());
				msg.setObjectId(arr[2]==null?"":arr[2].toString());
				returnList.add(msg);
			}
		}
		return returnList;
	}

	@Override
	public Integer sendOverDueMsg() {
		//处理因私逾期 提前一天 原本10天 现在9天  暂时只发给申请者
		Integer sendNum=0;
		String sql="SELECT applyInfo.APPLY_USE_TYPE,applyInfo.APPLY_HANDLE_TYPE ,applyInfo.CREATOR FROM  T_CERTIFICATE_INFO_APPLY applyInfo WHERE applyInfo.PFLAG='1' AND applyInfo.AUDIT_STATUS='approved' AND to_date( to_char( applyInfo.END_DATE, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )=to_date( to_char( SYSDATE - 9, 'yyyy-MM-dd' ), 'yyyy-mm-dd' ) AND REAL_USE_DATE IS NOT NULL AND REAL_BACK_DATE IS NULL";
		List privateList = super.getSession().createSQLQuery(sql).list();
		if(privateList!=null&&privateList.size()>0){
			for (int j = 0; j < privateList.size(); j++) {
				Object[] arr=(Object[])privateList.get(j);
				String useTypeStr=arr[0]==null?"":arr[0].toString();
				String handleTypeStr=arr[1]==null?"":arr[1].toString();
				String ownUserId=arr[2].toString();
				if(!StringUtil.isEmpty(useTypeStr)){
					User owner = userMng.findById(ownUserId);
					List<CertificateInfo> useList = certificateInfoMng.findPrivateByTypeAndOwnId(useTypeStr.split(","), ownUserId);
					String ownerContent="";
					if(useList!=null&&useList.size()>0){
						for (CertificateInfo certificateInfo : useList) {
							if(!StringUtil.isEmpty(certificateInfo.getZjhm())){
								if(!StringUtil.isEmpty(ownerContent)){
									ownerContent+="、";
								}
								ownerContent+= certificateInfo.getZjhm();
							}
						}
					}
					if(!StringUtil.isEmpty(ownerContent)&&owner!=null&&!StringUtil.isEmpty(owner.getMobileNo())){
						smsPoolMng.saveSendSms(owner.getMobileNo(), owner.getName(), "您的"+ownerContent+"护照明日将被列入逾期未归还名单，逾期15天系统将限制后续的护照签证申报功能,请及时归还！【出国（境）管理】", null);
						sendNum++;
					}
					
				}
				if(!StringUtil.isEmpty(handleTypeStr)){
					String[] typeArr = handleTypeStr.split(",");
					User owner = userMng.findById(ownUserId);
					if(owner!=null){
						String typeName="";
						for (int i = 0; i < typeArr.length; i++) {
							if(!StringUtil.isEmpty(typeName)){
								typeName+="、";
							}
							if(typeArr[i].contains("privatePassport")){
								typeName+="因私普通护照";
							}else if(typeArr[i].contains("HK")){
								typeName+="港澳通行证(因私)";
							}else if(typeArr[i].contains("Taiwan")){
								typeName+="台湾通行证";
							}
						}
						if(!StringUtil.isEmpty(typeName)&&!StringUtil.isEmpty(owner.getMobileNo())){
							smsPoolMng.saveSendSms(owner.getMobileNo(), owner.getName(), "您的"+typeName+"护照明日将被列入逾期未归还名单，逾期15天系统将限制后续的护照签证申报功能,请及时归还！【出国（境）管理】", null);
						}
					}
				}
			}
		}
		//因公逾期提醒  提前一天 发给申请的局主任和局工作人员
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT A.pid FROM T_PUBLIC_BUSINESS A INNER JOIN T_PUBLIC_BUSINESS_CERTIFICATE B ON A.pid = B.PUBLIC_BUSINESS_ID ");
		sb.append(" INNER JOIN T_CERTIFICATE_INFO C ON B.CERTIFICATE_ID = C.pid ");
		sb.append(" WHERE A.PFLAG = '1' AND C.PFLAG = '1' AND to_date( to_char( A.PLAN_ENTER_TIME, 'yyyy-MM-dd' ), 'yyyy-mm-dd' ) = to_date( to_char( SYSDATE - 6, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )  ");
		sb.append(" AND ( A.BACK_STATUS IS NULL OR A.BACK_STATUS != '归还完成' ) AND C.STATUS = 'using' ");
		List publicList = super.getSession().createSQLQuery(sb.toString()).list();
		String jgzContent="";//局工作人员短信内容
		if(publicList!=null&&publicList.size()>0){
			for (int i = 0; i < publicList.size(); i++) {
				Object[] arr=(Object[])publicList.get(i);
				String businessId=arr[0]==null?"":arr[0].toString();
				if(!StringUtil.isEmpty(businessId)){
					PublicBusiness publicBusiness = publicBusinessMng.findById(businessId);
					if(publicBusiness!=null){
						User jzr = publicBusiness.getCreator();
						String jzrContent="";
						List<CertificateInfo> certificateInfos = publicBusiness.getCertificateInfos();
						if(certificateInfos!=null&&certificateInfos.size()>0){
							for (CertificateInfo certificateInfo : certificateInfos) {
								if(!StringUtil.isEmpty(certificateInfo.getName())&&!StringUtil.isEmpty(certificateInfo.getZjhm())){
									if(!StringUtil.isEmpty(jzrContent)){
										jzrContent+="、";
									}
									jzrContent+=certificateInfo.getName()+ certificateInfo.getZjhm();
									if(!StringUtil.isEmpty(jgzContent)){
										jgzContent+="、";
									}
									jgzContent+=certificateInfo.getName()+ certificateInfo.getZjhm();
								}
							}
							if(jzr!=null&&!StringUtil.isEmpty(jzr.getMobileNo())&&!StringUtil.isEmpty(jzrContent)){
								smsPoolMng.saveSendSms(jzr.getMobileNo(), jzr.getName(), "以下护照明日将被列入逾期未归还名单，逾期15天系统将限制后续的护照签证申报功能："+jzrContent+" 【出国（境）管理】", null);
								sendNum++;
							}
						}
					}
				}
			}
			if(!StringUtil.isEmpty(jgzContent)){
				//局工作人员名单
				List<User> listAuditor = userMng.listByRole("PUBLIC_JBGSGZRY");
				if(listAuditor!=null&&listAuditor.size()>0){
					for (User user : listAuditor) {
						if(!StringUtil.isEmpty(user.getMobileNo())){
							smsPoolMng.saveSendSms(user.getMobileNo(), user.getName(), "以下护照明日将被列入逾期未归还名单，逾期15天系统将限制后续的护照签证申报功能："+jgzContent+" 【出国（境）管理】", null);
							sendNum++;
						}
					}
				}
			}
		}
		return sendNum;
	}

	public Integer sendExpireMsg() {
		Integer sendNum=0;
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT '您的' || ( CASE CERTIFICATE_TYPE WHEN   ");
		sb.append(" 'privatePassport' THEN ");
		sb.append(" '因私普通护照' WHEN 'privateHKAndMacaoPass' THEN '港澳通行证（因私）' WHEN 'privateTaiwanPass' THEN '台湾通行证（因私）' ELSE '' END ");
		sb.append(" ) || '（' || ZJHM || '，有效期：'||to_char( EFFECTIVE_DATE, 'yyyy-MM-dd' )||'），已过期' AS TITLE,'viewCertificateInfo' as objectType,PID as objectId  FROM T_CERTIFICATE_INFO WHERE ");
		sb.append(" PFLAG = '1' and status!='cancelled' AND EFFECTIVE_DATE IS NOT NULL AND to_date( to_char( EFFECTIVE_DATE, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )=to_date( to_char( SYSDATE, 'yyyy-MM-dd' ), 'yyyy-mm-dd' )");
		sb.append(" AND CERTIFICATE_TYPE LIKE '%private%' ");
		sb.append(" order by PUPDATETIME desc ");
		List list = super.getSession().createSQLQuery(sb.toString()).list();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Object[] arr=(Object[])list.get(i);
				String msg=arr[0]==null?"":arr[0].toString();
				String objectId=arr[2]==null?"":arr[2].toString();
				if(!StringUtil.isEmpty(objectId)){
					CertificateInfo info = certificateInfoMng.findById(objectId);
					User owner = info.getOwner();
					if(owner!=null&&!StringUtil.isEmpty(owner.getMobileNo())){
						smsPoolMng.saveSendSms(owner.getMobileNo(), owner.getName(), msg+"，请联系组织人事处工作人员及时领回！【出国（境）管理】", null);
						sendNum++;
					}
				}
			}
		}
		return sendNum;
	}
	

}
