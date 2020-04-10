package com.gwideal.core.manager.impl;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.util.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.core.model.Wcode;
import com.gwideal.core.manager.WcodeMng;

@Transactional
@Service
public class WcodeMngImpl extends BaseManagerImpl<Wcode> implements WcodeMng {

	@Override
	public synchronized String getWcode(String className, String wrule) {
		// TODO Auto-generated method stub
		Wcode bean=(Wcode)super.findUnique("from Wcode Where className=? and wrule=?",new Object[]{className,wrule});
		if(null!=bean && !StringUtil.isEmpty(bean.getId())){
			bean.setWcode(bean.getWcode()+1);
		}else{
			bean=new Wcode();
			bean.setClassName(className);
			bean.setWrule(wrule);
			bean.setWcode(1);
		}
		return super.save(bean).getWcodeStr();
	}
	
}
