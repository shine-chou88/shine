package com.gwideal.swj.work.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;
import com.gwideal.swj.work.entity.PublicBusiness;

public interface PublicBusinessMng extends BaseManager<PublicBusiness>{
	
	public Pagination list(PublicBusiness bean, String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole,boolean isStreetRole, User user);
	
	public void save(PublicBusiness bean, User user);
	
	public void delete(String id, User user);

	public void saveOrSubmitUseRegister(PublicBusiness bean, User user);

	public PublicBusiness findByCertificateIdAndStatus(String certificateId,String bussinessStatus,String certificateStatus);

	public void saveUseRegisterScan(String id,User user);

	public void saveBackRegisterScan(String id, User user);
	
	public List getUnReadMsg();
}
