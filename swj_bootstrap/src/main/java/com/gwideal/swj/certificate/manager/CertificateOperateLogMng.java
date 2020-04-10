package com.gwideal.swj.certificate.manager;

import com.gwideal.swj.certificate.entity.CertificateOperateLog;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;

public interface CertificateOperateLogMng extends BaseManager<CertificateOperateLog>{
	
	public Pagination list(CertificateOperateLog bean, String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole,boolean isStreetRole, User user);
	
	public void save(CertificateOperateLog bean, User user);
	
	public void delete(String id, User user);
}
