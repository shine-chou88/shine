package com.gwideal.swj.work.manager;

import com.gwideal.swj.work.entity.PublicAdjust;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;

public interface PublicAdjustMng extends BaseManager<PublicAdjust>{
	
	public Pagination list(PublicAdjust bean, String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole,boolean isStreetRole, User user);
	
	public void save(PublicAdjust bean, User user);
	
	public void delete(String id, User user);

	public void adjustConfirm(String id, User user);
}
