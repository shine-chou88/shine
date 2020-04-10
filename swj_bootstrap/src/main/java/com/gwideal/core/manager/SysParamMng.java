package com.gwideal.core.manager;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.core.model.SysParam;

public interface SysParamMng extends BaseManager<SysParam>{
	public SysParam findByKey(String key);
}
