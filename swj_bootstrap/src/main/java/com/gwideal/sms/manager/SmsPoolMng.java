package com.gwideal.sms.manager;

import java.util.List;


import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;
import com.gwideal.sms.entity.SmsPool;

public interface SmsPoolMng extends BaseManager<SmsPool>{
	/**
	 * 把短信放入短信池
	 * @param phone 手机号码
	 * @param userName 收信人
	 * @param content 短信内容
	 */
	public void saveSendSms(String phone,String userName,String content,User user);
	
	/**
	 * 批量取消
	 */
	public void cancel(List<String> ids,User user);
	
	/**
	 * 查询
	 * @return 分页
	 */
	public Pagination list(SmsPool bean,String sort,String order,int pageIndex,int pageSize,boolean isQuRole,boolean isStreetRole,User user);
	
	
	/**
	 * 批量删除
	 */
	public void delete(List<String> ids,User user);
	
	/**
	 * 删除
	 */
	public void delete(String id,User user);

}

