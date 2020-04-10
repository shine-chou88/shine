package com.gwideal.sms.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.User;
import com.gwideal.sms.entity.SmsPool;
import com.gwideal.sms.manager.SmsPoolMng;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class SmsPoolMngImpl extends BaseManagerImpl<SmsPool> implements
		SmsPoolMng {

	@Override
	public void saveSendSms(String phone, String userName, String content,
			User user) {
		SmsPool smsPool = new SmsPool();
		smsPool.setCreator(user);
		smsPool.setSmsState("10");
		smsPool.setPlanSendTime(new Date());
		smsPool.setReceiveMobile(phone);
		smsPool.setContent(content);
		smsPool.setReceivePerson(userName);
		super.save(smsPool);
	}

	/**
	 * 找出所有的收信人
	 * 
	 * @return
	 */
	public List<String> getListReceivePerson() {
		return super
				.find("Select receivePerson from SmsPool Where flag=1 and receivePerson is not null group by receivePerson order by receivePerson");
	}

	@Override
	public void delete(List<String> ids, User user) {
		try {
			if (null != ids && 0 != ids.size()) {
				for (String id : ids) {
					SmsPool smsPool = super.findById(id);
					smsPool.setUpdator(user);
					smsPool.setUpdateTime(new Date());
					smsPool.setFlag(0);
					super.update(smsPool);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancel(List<String> ids, User user) {
		try {
			if (null != ids && 0 != ids.size()) {
				for (String id : ids) {
					SmsPool smsPool = super.findById(id);
					smsPool.setUpdator(user);
					smsPool.setUpdateTime(new Date());
					smsPool.setSmsState("14");
					super.update(smsPool);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id, User user) {

		SmsPool sms = findById(id);
		sms.setFlag(0);
		sms.setUpdator(user);
		sms.setUpdateTime(new Date());

		super.update(sms);
	}

	@SuppressWarnings("static-access")
	@Override
	public Pagination list(SmsPool bean, String sort, String order,
			int pageIndex, int pageSize, boolean isQuRole,
			boolean isStreetRole, User user) {

		Finder f = Finder.create(" from SmsPool where flag=1");

		if (bean != null) {
			if (!StringUtil.isEmpty(bean.getReceiveMobile())) {
				f.append(" and receiveMobile =:receiveMobile").setParam(
						"receiveMobile", bean.getReceiveMobile());
			}
			if (!StringUtil.isEmpty(bean.getSmsState())) {
				f.append(" and smsState=:smsState").setParam("smsState",
						bean.getSmsState());
			}
			if (!StringUtil.isEmpty(bean.getContent())) {
				f.append(" and content like '%" + bean.getContent() + "%'");
			}
			if (bean.getPlanSendTimes() != null) {
				f.append(" and planSendTime>=:planSendTimes").setParam(
						"planSendTimes", bean.getPlanSendTimes());
			}
			if (bean.getPlanSendTimee() != null) {
				Calendar cal = new GregorianCalendar();
				cal.setTime(bean.getPlanSendTimee());
				cal.add(cal.DATE, 1);
				bean.setPlanSendTimee(cal.getTime());
				f.append(" and planSendTime<=:planSendTimee").setParam(
						"planSendTimee", bean.getPlanSendTimee());
			}

		}

		if (StringUtil.isEmpty(order)) {
			f.append(" order by createTime");
		} else {
			f.append(" order by " + order);
		}
		if (StringUtil.isEmpty(sort)) {
			f.append(" desc");
		} else {
			f.append(" " + sort);
		}
		return super.find(f, pageIndex, pageSize);
	}

}
