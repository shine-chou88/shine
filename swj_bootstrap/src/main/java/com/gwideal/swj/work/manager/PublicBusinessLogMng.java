package com.gwideal.swj.work.manager;

import com.gwideal.swj.work.entity.PublicBusinessLog;
import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;

public interface PublicBusinessLogMng extends BaseManager<PublicBusinessLog>{
	
	public Pagination list(PublicBusinessLog bean, String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole,boolean isStreetRole, User user);
	
	public void save(PublicBusinessLog bean, User user);
	
	public void delete(String id, User user);
}
