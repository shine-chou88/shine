package com.gwideal.core.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.SysParamMng;
import com.gwideal.core.model.SysParam;
@SuppressWarnings("unchecked")
@Transactional
@Service
public class SysParamMngImpl extends BaseManagerImpl<SysParam> implements SysParamMng{
	
	@Override
	public SysParam findByKey(String key){
		Finder hql=Finder.create(" from SysParam where paramKey='"+key+"'");
		List<SysParam> sp=super.find(hql);
		if(sp!=null&&sp.size()>0){
			return sp.get(0);
		}
		return null;
	}
	
}
