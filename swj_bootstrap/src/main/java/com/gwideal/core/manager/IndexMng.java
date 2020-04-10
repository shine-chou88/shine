package com.gwideal.core.manager;

import java.util.List;

import com.gwideal.common.hibernate.BaseManager;
import com.gwideal.common.page.Pagination;
import com.gwideal.core.model.User;
import com.gwideal.swj.certificate.entity.Message;

public interface IndexMng extends BaseManager<User>{
	/**
	 * 获取未读信息条数
	 * @param user
	 * @return
	 */
	public int getUnReadMessage(User user);
	
	/**
	 * 获取未读信息列表
	 * @param isQuRole
	 * @param isStreetRole
	 * @param user
	 * @return
	 */
	public Pagination getUnReadMessagePagination(User user);

	public List<Message> expireRemindList(User user);

	public List<Message> notBackReminList(User user);
	
	public Integer sendOverDueMsg();
	
}
