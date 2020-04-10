package com.gwideal.swj.work.manager.impl;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.swj.work.entity.PublicBusinessLog;
import com.gwideal.swj.work.manager.PublicBusinessLogMng;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
import java.util.Date;

@Transactional
@Service
public class PublicBusinessLogMngImpl extends BaseManagerImpl<PublicBusinessLog> implements PublicBusinessLogMng {

	@Override
	public Pagination list(PublicBusinessLog bean, String sort, String order,
			int pageIndex, int pageSize, boolean isQuRole,
			boolean isStreetRole, User user) {
		Finder f = Finder.create("from PublicBusinessLog where flag=1 ");
		if (bean!=null) {
			if (!StringUtil.isEmpty(bean.getOperateType())) {
				f.append(" and operateType like :operateType").setParam("operateType","%"+bean.getOperateType()+"%");
			}
			if (!StringUtil.isEmpty(bean.getOperateContent())) {
				f.append(" and operateContent like :operateContent").setParam("operateContent","%"+bean.getOperateContent()+"%");
			}
			if (!StringUtil.isEmpty(bean.getBusinessId())) {
				f.append(" and publicBusiness.id like :businessId").setParam("businessId","%"+bean.getBusinessId()+"%");
			}
		}
		if(!StringUtil.isEmpty(sort)&&!StringUtil.isEmpty(order)){
			f.append(" order by "+sort+" "+order);
		}else{
			f.append(" order by updateTime desc");
		}
		return super.find(f,pageIndex, pageSize);
	}
	
	@Override
	public void save(PublicBusinessLog bean, User user) {
		if(StringUtil.isEmpty(bean.getId())) {
			bean.setCreator(user);
		}else{
			bean.setUpdator(user);			
		}
		super.save(bean);
	}
	
	@Override
	public void delete(String id, User user) {
		if(!StringUtil.isEmpty(id)) {
			PublicBusinessLog bean=super.findById(id);
			bean.setFlag(0);
			bean.setUpdator(user);
			bean.setUpdateTime(new Date());
			super.save(bean);
		}
	}
}
