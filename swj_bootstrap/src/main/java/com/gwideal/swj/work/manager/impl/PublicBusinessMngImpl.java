package com.gwideal.swj.work.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.entity.CertificationStatus;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;
import com.gwideal.swj.work.entity.PublicBusiness;
import com.gwideal.swj.work.entity.PublicBusinessLog;
import com.gwideal.swj.work.manager.PublicBusinessLogMng;
import com.gwideal.swj.work.manager.PublicBusinessMng;

@Transactional
@Service
public class PublicBusinessMngImpl extends BaseManagerImpl<PublicBusiness> implements PublicBusinessMng {

	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
	private CertificateOperateLogMng certificateOperateLogMng;
	@Autowired
	private PublicBusinessLogMng publicBusinessLogMng;

	@Override
	public Pagination list(PublicBusiness bean, String sort, String order, int pageIndex, int pageSize,
			boolean isJBGSGZRYRole, boolean isStreetRole, User user) {
		Finder f = Finder.create("from PublicBusiness  where flag=1 ");
		if (bean != null) {
			if (!StringUtil.isEmpty(bean.getGroupDepartId())) {
				f.append(" and groupDepart.id like :groupDepartId").setParam("groupDepartId",
						"%" + bean.getGroupDepartId() + "%");
			}
			if (!StringUtil.isEmpty(bean.getApprovalNo())) {
				f.append(" and approvalNo like :approvalNo").setParam("approvalNo", "%" + bean.getApprovalNo() + "%");
			}
			if (null != bean.getPlanExitTime()) {
				f.append(" and planExitTime = :planExitTime").setParam("planExitTime", bean.getPlanExitTime());
			}
			if (null != bean.getPlanEnterTime()) {
				f.append(" and planEnterTime = :planEnterTime").setParam("planEnterTime", bean.getPlanEnterTime());
			}
			if (!StringUtil.isEmpty(bean.getApplyUserName())) {
				f.append(" and applyUserName like :applyUserName").setParam("applyUserName",
						"%" + bean.getApplyUserName() + "%");
			}
			if (!StringUtil.isEmpty(bean.getApplyUserDepartName())) {
				f.append(" and applyUserDepartName like :applyUserDepartName").setParam("applyUserDepartName",
						"%" + bean.getApplyUserDepartName() + "%");
			}
			if (!StringUtil.isEmpty(bean.getApplyUserPhone())) {
				f.append(" and applyUserPhone like :applyUserPhone").setParam("applyUserPhone",
						"%" + bean.getApplyUserPhone() + "%");
			}
			if (null != bean.getApplyDate()) {
				f.append(" and applyDate = :applyDate").setParam("applyDate", bean.getApplyDate());
			}
			if (null != bean.getRealExitTime()) {
				f.append(" and realExitTime = :realExitTime").setParam("realExitTime", bean.getRealExitTime());
			}
			if (null != bean.getRealEnterTime()) {
				f.append(" and realEnterTime = :realEnterTime").setParam("realEnterTime", bean.getRealEnterTime());
			}
			if (!StringUtil.isEmpty(bean.getRealVisitCountry())) {
				f.append(" and realVisitCountry like :realVisitCountry").setParam("realVisitCountry",
						"%" + bean.getRealVisitCountry() + "%");
			}
			if (!StringUtil.isEmpty(bean.getHasViolationSituation())) {
				f.append(" and hasViolationSituation like :hasViolationSituation").setParam("hasViolationSituation",
						"%" + bean.getHasViolationSituation() + "%");
			}
			if (!StringUtil.isEmpty(bean.getBackUserName())) {
				f.append(" and backUserName like :backUserName").setParam("backUserName",
						"%" + bean.getBackUserName() + "%");
			}
			if (!StringUtil.isEmpty(bean.getBackUserDepartName())) {
				f.append(" and backUserDepartName like :backUserDepartName").setParam("backUserDepartName",
						"%" + bean.getBackUserDepartName() + "%");
			}
			if (!StringUtil.isEmpty(bean.getBackUserPhone())) {
				f.append(" and backUserPhone like :backUserPhone").setParam("backUserPhone",
						"%" + bean.getBackUserPhone() + "%");
			}
			if (null != bean.getBackDate()) {
				f.append(" and backDate = :backDate").setParam("backDate", bean.getBackDate());
			}
			if (!StringUtil.isEmpty(bean.getUseStatus())) {
				f.append(" and useStatus = :useStatus").setParam("useStatus",bean.getUseStatus() );
			}
			if (!StringUtil.isEmpty(bean.getBackStatus())) {
				f.append(" and backStatus = :backStatus").setParam("backStatus",bean.getBackStatus() );
			}
		}

		if (!isJBGSGZRYRole) {
			f.append(" and creator.id = :cId");
			f.setParam("cId", user.getId());
			if(!StringUtil.isEmpty(bean.getBusinessType())){
				if("use".equals(bean.getBusinessType())){
					f.append(" and useStatus in :queryUseStatus").setParamList("queryUseStatus", Arrays.asList(new String[]{PublicBusiness.USE_REGISTER_TODO,PublicBusiness.USE_REGISTER,PublicBusiness.USE_CONFRIM,PublicBusiness.USE_COMPLETE}));
				}else if("back".equals(bean.getBusinessType())){
					f.append(" and backStatus in :queryBackStatus").setParamList("queryBackStatus", Arrays.asList(new String[]{PublicBusiness.BACK_REGISTER_TODO,PublicBusiness.BACK_REGISTER,PublicBusiness.BACK_CONFRIM,PublicBusiness.BACK_COMPLETE}));
				}
			}
		}else{
			if(!StringUtil.isEmpty(bean.getBusinessType())){
				if("use".equals(bean.getBusinessType())){
					f.append(" and useStatus in :queryUseStatus").setParamList("queryUseStatus", Arrays.asList(new String[]{PublicBusiness.USE_REGISTER,PublicBusiness.USE_CONFRIM,PublicBusiness.USE_COMPLETE}));
				}else if("back".equals(bean.getBusinessType())){
					f.append(" and backStatus in :queryBackStatus").setParamList("queryBackStatus", Arrays.asList(new String[]{PublicBusiness.BACK_REGISTER,PublicBusiness.BACK_CONFRIM,PublicBusiness.BACK_COMPLETE}));
				}
			}
		}
		if(!StringUtil.isEmpty(bean.getScanType())){
			if("use".equals(bean.getScanType())){
				f.append(" and useStatus =:queryStatus").setParam("queryStatus", PublicBusiness.USE_CONFRIM);
			}else if("back".equals(bean.getScanType())){
				f.append(" and backStatus =:queryStatus").setParam("queryStatus", PublicBusiness.BACK_CONFRIM);
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
	public void save(PublicBusiness bean, User user) {
		if (StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
			if (!StringUtil.isEmpty(bean.getCertificateIds())) {
				bean.setCertificateInfos(new ArrayList<CertificateInfo>());
				String[] cids = bean.getCertificateIds().split(",");
				for (String cid : cids) {
					bean.addCertificateInfo(certificateInfoMng.findById(cid));
				}
			}
		} else {
			bean.setUpdator(user);
			PublicBusiness business = super.findById(bean.getId());
			bean.setCertificateInfos(business.getCertificateInfos());
			if (!StringUtil.isEmpty(bean.getCertificateIds())) {
				String[] CInfoArr = bean.getCertificateIds().split(",");
				List<String> cids = new ArrayList<String>();
				for (String id : CInfoArr) {
					cids.add(id);
				}
				List<CertificateInfo> listRemoveCertificate = new ArrayList<CertificateInfo>();
				if (null == bean.getCertificateInfos()) {
					bean.setCertificateInfos(new ArrayList<CertificateInfo>());
				}
				for (CertificateInfo info : bean.getCertificateInfos()) {
					if (!cids.contains(info.getId())) {
						listRemoveCertificate.add(info);
					}
				}
				for (CertificateInfo info : listRemoveCertificate) {
					bean.removeCertificateInfo(info);
				}
				for (String cid : cids) {
					CertificateInfo certificateInfo = certificateInfoMng.findById(cid);
					if (null == certificateInfo.getPublicBusinessList()) {
						certificateInfo.setPublicBusinessList(new ArrayList<PublicBusiness>());
					}
					if (!bean.getCertificateInfos().contains(certificateInfo)) {
						bean.addCertificateInfo(certificateInfo);
					}
				}
			} else {
				bean.setCertificateInfos(null);
			}
		}
		PublicBusiness business = (PublicBusiness) super.merge(bean);
		//日志
		if(bean.getHasSubmit()){
			PublicBusinessLog  log=new PublicBusinessLog("use", "提交证照领用信息表", user, new Date(),business);
			publicBusinessLogMng.save(log);
		}
	}

	@Override
	public void delete(String id, User user) {
		if (!StringUtil.isEmpty(id)) {
			PublicBusiness bean = super.findById(id);
			bean.setFlag(0);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			super.save(bean);
			List<CertificateInfo> certificateInfos = bean.getCertificateInfos();
			if(certificateInfos!=null&&certificateInfos.size()>0){
				for (CertificateInfo certificateInfo : certificateInfos) {
					certificateInfo.setHasRecord(0);//解锁
					certificateInfoMng.merge(certificateInfo);
				}
			}
		}
	}


	@Override
	public void saveOrSubmitUseRegister(PublicBusiness bean, User user) {
		Boolean isSubmit = bean.getHasSubmit();
		if(isSubmit){
			bean.setUseStatus(PublicBusiness.USE_REGISTER);
			bean.setApplySubmitTime(new Date());
		}else if(!isSubmit){
			bean.setUseStatus(PublicBusiness.USE_REGISTER_TODO);
		}
		this.save(bean, user);
		List<CertificateInfo> certificateInfos = bean.getCertificateInfos();
		if(certificateInfos!=null&&certificateInfos.size()>0){
			for (CertificateInfo certificateInfo : certificateInfos) {
				certificateInfo.setHasRecord(1);
				certificateInfoMng.merge(certificateInfo);
			}
		}
		
	}

	@Override
	public PublicBusiness findByCertificateIdAndStatus(String certificateId,String bussinessStatus,String certificateStatus) {
		StringBuffer sql=new StringBuffer("Select A.pid from T_PUBLIC_BUSINESS A INNER JOIN T_PUBLIC_BUSINESS_CERTIFICATE B ON A.pid=B.PUBLIC_BUSINESS_ID INNER JOIN T_CERTIFICATE_INFO C on B.CERTIFICATE_ID=C.pid Where A.pflag=1");
		sql.append(" AND b.CERTIFICATE_ID = '"+certificateId+"' ");
		if(PublicBusiness.USE_CONFRIM.equals(bussinessStatus)){
			sql.append(" and A.USE_STATUS= '"+bussinessStatus+"' ");
		}else if(PublicBusiness.BACK_CONFRIM.equals(bussinessStatus)){
			sql.append(" and A.BACK_STATUS= '"+bussinessStatus+"' ");
		}
		sql.append(" AND C.STATUS= '"+certificateStatus+"' ");
		sql.append("ORDER BY a.PUPDATETIME DESC");
		List<String> idList = super.getSession().createSQLQuery(sql.toString()).list();
		if(idList!=null&&idList.size()>0){
			String businessId = idList.get(0);
			return this.findById(businessId);
		}
		return null;
		
	}

	@Override
	public void saveUseRegisterScan(String id,User user) {
		PublicBusiness business = this.findById(id);
		if(business!=null){
			business.setUpdator(user);
			business.setUseStatus(PublicBusiness.USE_COMPLETE);
			business.setRealApplyDate(new Date());
			this.save(business);
			List<CertificateInfo> certificateInfos = business.getCertificateInfos();
			if(certificateInfos!=null&&certificateInfos.size()>0){
				for (CertificateInfo certificateInfo : certificateInfos) {
					certificateInfo.setStatus(CertificationStatus.USING.getItemValue());
					certificateInfo.setRealUseTime(new Date());
					certificateInfoMng.save(certificateInfo);
					CertificateOperateLog operateLog=new CertificateOperateLog("use","领用",user,new Date(),certificateInfo);
	            	certificateOperateLogMng.save(operateLog);
				}
			}
			PublicBusinessLog  log=new PublicBusinessLog("use", "证照领用信息表领用扫描", user, new Date(),business);
			publicBusinessLogMng.save(log);
		}
		
	}

	@Override
	public void saveBackRegisterScan(String id, User user) {
		PublicBusiness business = this.findById(id);
		if(business!=null){
			business.setUpdator(user);
			business.setBackStatus(PublicBusiness.BACK_COMPLETE);
			business.setRealBackDate(new Date());
			this.save(business);
			List<CertificateInfo> certificateInfos = business.getCertificateInfos();
			if(certificateInfos!=null&&certificateInfos.size()>0){
				for (CertificateInfo certificateInfo : certificateInfos) {
					certificateInfo.setStatus(CertificationStatus.CHECKIN.getItemValue());
					certificateInfo.setRealBackTime(new Date());
					certificateInfo.setHasRecord(0);//解锁
					certificateInfoMng.save(certificateInfo);
					CertificateOperateLog operateLog=new CertificateOperateLog("back","归还",user,new Date(),certificateInfo);
	            	certificateOperateLogMng.save(operateLog);
				}
			}
			PublicBusinessLog  log=new PublicBusinessLog("back", "证照归还信息表归还扫描", user, new Date(),business);
			publicBusinessLogMng.save(log);
			
		}
		
	}

	@Override
	public List getUnReadMsg() {
		StringBuilder sb=new StringBuilder();
		sb.append(" select A.pid ,'因公出国（境）证照领用信息表' as name,'证照领用待确认' as curNodeName ,'领用登记提交' as lastNodeName, B.name as lastAssigneeName ,to_char(A.apply_submit_time,'YYYY-MM-DD hh24:mi:ss ') as lastTime,B.name||'申请领用证照' from T_PUBLIC_BUSINESS  A  inner join sys_user B on A.creator=B.pid where A.pflag=1 and A.use_status='领用待确认' ");
		sb.append(" union all ");
		sb.append(" select C.pid ,'因公出国（境）证照归还信息表' as name,'证照归还待确认' as curNodeName, '归还登记提交' as lastNodeName, D.name as lastAssigneeName ,to_char(C.back_submit_time,'YYYY-MM-DD hh24:mi:ss ') as lastTime,D.name||'申请归还证照' from T_PUBLIC_BUSINESS C inner join sys_user D on C.creator=D.pid where C.pflag=1 and C.back_status='归还待确认' ");
		return super.getSession().createSQLQuery(sb.toString()).list();
	}
}
