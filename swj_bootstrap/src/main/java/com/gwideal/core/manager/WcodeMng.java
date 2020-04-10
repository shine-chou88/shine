package com.gwideal.core.manager;

import com.gwideal.core.model.Wcode;
import com.gwideal.common.hibernate.BaseManager;

public interface WcodeMng extends BaseManager<Wcode>{
	
	/**
	 * 获取流水号，当前规则不存在流水号则新增，存在则流水号+1
	 * @param className
	 * @param wrule
	 * @return
	 */
	public String getWcode(String className,String wrule);
}
