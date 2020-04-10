package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.SysLog;

public interface SysLogMng extends BaseManager<SysLog>{
	
	public Pagination list(SysLog log,String sort,String order,int pageIndex,int pageSize);
	
	/**
	 * 日志归档，归档日期之前的记录都移动到日志历史表
	 * @param archiveTime 归档日期
	 */
	public void archive(String archiveDate);
	
	public List<String> findCurrentUser();
	
	public List<SysLog> findByAttIds(List<String> attIds);
	
	/**
	 * 获取用户最后操作
	 * @param isQuRole true=区级用户操作，false=街镇级用户操作
	 * @param size 获取多少个用户的最后操作
	 * @return
	 */
	public List listUserLastLog(boolean isQuRole,int size);
	
	/**
	 * 获取用户最后操作
	 * @param isQuRole true=区级用户操作，false=街镇级用户操作
	 * @param size 获取多少个用户的最后操作
	 * @return
	 */
	public List<String[]> listAPPUserLastLog(boolean isQuRole,int size);

	List<SysLog> exportList(SysLog log);
}
