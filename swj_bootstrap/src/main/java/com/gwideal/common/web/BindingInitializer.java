package com.gwideal.common.web;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

/**
 * 数据绑定初始化类
 */
public class BindingInitializer implements WebBindingInitializer {
	
	/**
	 * 初始化数据绑定
	 */
	@Override
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateTypeEditor());
	}
}
