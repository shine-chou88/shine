package com.gwideal.swj.work.manager.impl;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.swj.certificate.entity.CertificateInfo;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.manager.CertificateInfoMng;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;
import com.gwideal.swj.work.entity.PublicAdjust;
import com.gwideal.swj.work.entity.PublicBusiness;
import com.gwideal.swj.work.manager.PublicAdjustMng;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PublicAdjustMngImpl extends BaseManagerImpl<PublicAdjust> implements PublicAdjustMng {
	
	@Autowired
	private CertificateInfoMng certificateInfoMng;
	@Autowired
    private CertificateOperateLogMng certificateOperateLogMng;

	@Override
	public Pagination list(PublicAdjust bean, String sort, String order, int pageIndex, int pageSize, boolean isQuRole,
			boolean isStreetRole, User user) {
		Finder f = Finder.create("from PublicAdjust where flag=1 ");
		if (bean != null) {
			if(!StringUtil.isEmpty(bean.getDepartId())){
				f.append(" and depart.id  =:departId").setParam("departId", bean.getDepartId());
			}
			if (!StringUtil.isEmpty(bean.getWriteUserName())) {
				f.append(" and writeUserName like :writeUserName").setParam("writeUserName",
						"%" + bean.getWriteUserName() + "%");
			}
			if (!StringUtil.isEmpty(bean.getAfterDepartOuter())) {
				f.append(" and afterDepartOuter like :afterDepartOuter").setParam("afterDepartOuter",
						"%" + bean.getAfterDepartOuter() + "%");
			}
			if (!StringUtil.isEmpty(bean.getHasRetired())) {
				f.append(" and hasRetired like :hasRetired").setParam("hasRetired", "%" + bean.getHasRetired() + "%");
			}
			if (!StringUtil.isEmpty(bean.getWriteUserPhone())) {
				f.append(" and writeUserPhone like :writeUserPhone").setParam("writeUserPhone",
						"%" + bean.getWriteUserPhone() + "%");
			}
			if (null != bean.getWriteDate()) {
				f.append(" and writeDate = :writeDate").setParam("writeDate", bean.getWriteDate());
			}
			if (!StringUtil.isEmpty(bean.getStatus())) {
				f.append(" and status like :status").setParam("status", "%" + bean.getStatus() + "%");
			}
			if (!StringUtil.isEmpty(bean.getOther())) {
				f.append(" and other like :other").setParam("other", "%" + bean.getOther() + "%");
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
	public void save(PublicAdjust bean, User user) {
		if (StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
			if (!StringUtil.isEmpty(bean.getCertificateIds())) {
				bean.setCertificateList(new ArrayList<CertificateInfo>());
				String[] cids = bean.getCertificateIds().split(",");
				for (String cid : cids) {
					bean.addCertificate(certificateInfoMng.findById(cid));
				}
			}
		} else {
			bean.setUpdator(user);
			PublicAdjust adjust = super.findById(bean.getId());
			bean.setCertificateList(adjust.getCertificateList());
			if (!StringUtil.isEmpty(bean.getCertificateIds())) {
				String[] CInfoArr = bean.getCertificateIds().split(",");
				List<String> cids = new ArrayList<String>();
				for (String id : CInfoArr) {
					cids.add(id);
				}
				List<CertificateInfo> listRemoveCertificate = new ArrayList<CertificateInfo>();
				if (null == bean.getCertificateList()) {
					bean.setCertificateList(new ArrayList<CertificateInfo>());
				}
				for (CertificateInfo info : bean.getCertificateList()) {
					if (!cids.contains(info.getId())) {
						listRemoveCertificate.add(info);
					}
				}
				for (CertificateInfo info : listRemoveCertificate) {
					bean.removeCertificate(info);
				}
				for (String cid : cids) {
					CertificateInfo certificateInfo = certificateInfoMng.findById(cid);
					if (null == certificateInfo.getPublicAdjustList()) {
						certificateInfo.setPublicAdjustList(new ArrayList<PublicAdjust>());
					}
					if (!bean.getCertificateList().contains(certificateInfo)) {
						bean.addCertificate(certificateInfo);
					}
				}
			} else {
				bean.setCertificateList(null);
			}
		}
		super.save(bean);
	}

	@Override
	public void delete(String id, User user) {
		if (!StringUtil.isEmpty(id)) {
			PublicAdjust bean = super.findById(id);
			bean.setFlag(0);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			super.save(bean);
		}
	}

	@Override
	public void adjustConfirm(String id, User user) {
		if (!StringUtil.isEmpty(id)) {
			PublicAdjust bean = super.findById(id);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			bean.setStatus("已通过");
			List<CertificateInfo> certificateList = bean.getCertificateList();
			if(certificateList!=null&&certificateList.size()>0){
				for (CertificateInfo certificateInfo : certificateList) {
					if (null != bean.getAfterDepartInner() && !StringUtil.isEmpty(bean.getAfterDepartInner().getId())) {
						certificateInfo.setBelongDepart(bean.getAfterDepartInner());
						certificateInfo.setUpdator(user);
						certificateInfoMng.saveOrUpdate(certificateInfo);
						CertificateOperateLog operateLog = new CertificateOperateLog("adjust","证照信息调整",user,new Date(),certificateInfo);
		            	certificateOperateLogMng.save(operateLog);
					}
				}
			}
			super.save(bean);
		}
		
	}
}
