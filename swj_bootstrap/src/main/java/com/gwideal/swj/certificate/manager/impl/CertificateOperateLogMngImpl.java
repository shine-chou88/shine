package com.gwideal.swj.certificate.manager.impl;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.swj.certificate.entity.CertificationTypeEnum;
import com.gwideal.swj.certificate.manager.CertificateOperateLogMng;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;

import java.util.Arrays;
import java.util.Date;

@Transactional
@Service
public class CertificateOperateLogMngImpl extends BaseManagerImpl<CertificateOperateLog>
		implements CertificateOperateLogMng {

	@Override
	public Pagination list(CertificateOperateLog bean, String sort, String order, int pageIndex, int pageSize,
			boolean isQuRole, boolean isStreetRole, User user) {
		Finder f = Finder.create("from CertificateOperateLog where flag=1 ");
		if (bean != null) {
			if (!StringUtil.isEmpty(bean.getOperateType())) {
				f.append(" and operateType like :operateType").setParam("operateType",
						"%" + bean.getOperateType() + "%");
			}
			if (!StringUtil.isEmpty(bean.getOperateContent())) {
				f.append(" and operateContent like :operateContent").setParam("operateContent",
						"%" + bean.getOperateContent() + "%");
			}
			if (null != bean.getOperateUser() && !StringUtil.isEmpty(bean.getOperateUser().getId())) {
				f.append(" and operateUser.id = :operateUserId").setParam("operateUserId",
						bean.getOperateUser().getId());
			}
			if (null != bean.getOperateTime()) {
				f.append(" and operateTime = :operateTime").setParam("operateTime", bean.getOperateTime());
			}
			if (!StringUtil.isEmpty(bean.getCertificateId())) {
				f.append(" and certificateInfo.id = :certificateId").setParam("certificateId", bean.getCertificateId());
			}
			if("private".equals(bean.getBusinessType())){
				f.append(" and certificateInfo.certificateType  in :typeList").setParamList("typeList",Arrays.asList(new String[]{CertificationTypeEnum.privatePassport.getItemValue(),CertificationTypeEnum.privateHKAndMacaoPass.getItemValue(),CertificationTypeEnum.privateTaiwanPass.getItemValue()}));
			}
			if(!StringUtil.isEmpty(bean.getCertificateName())){
				f.append(" and certificateInfo.name like :certificateName").setParam("certificateName", "%"+bean.getCertificateName()+"%");
			}
			if(null!=bean.getStartTime()){
				f.append(" and operateTime >= :startTime").setParam("startTime", bean.getStartTime());
			}
			
			if(null!=bean.getEndTime()){
				f.append(" and operateTime <= :endTime").setParam("endTime", bean.getEndTime());
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
	public void save(CertificateOperateLog bean, User user) {
		if (StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
		} else {
			bean.setUpdator(user);
		}
		super.save(bean);
	}

	@Override
	public void delete(String id, User user) {
		if (!StringUtil.isEmpty(id)) {
			CertificateOperateLog bean = super.findById(id);
			bean.setFlag(0);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			super.save(bean);
		}
	}
}
