package com.gwideal.core.manager.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.SysLogMng;
import com.gwideal.core.model.SysLog;

@Transactional
@Service
public class SysLogMngImpl extends BaseManagerImpl<SysLog> implements SysLogMng{

	@Override
	public Pagination list(SysLog log, String sort, String order,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from SysLog Where flag=1");
		if(null!=log){
			if(!StringUtil.isEmpty(log.getCurrentlogName())){
				f.append(" and creator.street.streetCode = :streetCode");
				f.setParam("streetCode",log.getCurrentlogName());
			}
			if(!StringUtil.isEmpty(log.getLogName())){
				f.append(" and creator.name like :name");
				f.setParam("name","%"+log.getLogName()+"%");
			}
			if(!StringUtil.isEmpty(log.getOperateContent())){
				f.append(" and operateContent like :operateContent");
				f.setParam("operateContent", "%"+log.getOperateContent()+"%");
			}
			if(!StringUtil.isEmpty(log.getType())){
				f.append(" and type = :type");
				f.setParam("type",log.getType());
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				if(!StringUtil.isEmpty(log.getStartTime())){
					f.append(" and createTime >=:startTime").setParam("startTime",sdf.parse(log.getStartTime()));
				}
				if(!StringUtil.isEmpty(log.getEndTime())){
					f.append(" and createTime <=:endTime").setParam("endTime",sdf.parse(log.getEndTime()));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		f.append(" order by createTime desc");
		return super.find(f, pageIndex, pageSize);
	}

	@Override
	public void archive(String archiveDate) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("insert into SYS_OPERATE_LOG_HIS");
		sql.append("(pid,pflag,creator,createtime,pupdater,pupdatetime,operate_url,operate_content,archive_time,type,wx_id,data_id,ip)");
		sql.append(" select sys_guid(),pflag,creator,createtime,pupdater,pupdatetime,operate_url,operate_content,'"+archiveDate+"',type,wx_id,data_id,ip from SYS_OPERATE_LOG");
		sql.append(" Where createtime<to_date('"+archiveDate+"','yyyy-MM-dd')");
		super.getSession().createSQLQuery(sql.toString()).executeUpdate();
		super.getSession().createSQLQuery(" delete from SYS_OPERATE_LOG Where createtime<to_date('"+archiveDate+"','yyyy-MM-dd')").executeUpdate();
	}

	@Override
	public List<String> findCurrentUser() {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("Select creator from Sys_operate_log s Where pflag=1");
		sql.append(" and createtime>=DATE_SUB(NOW(),INTERVAL 1 HOUR) and operate_url<>'/logout.do'");
		sql.append(" and not exists (Select 1 from Sys_operate_log sl ");
		sql.append(" Where pflag=1 and createtime>=DATE_SUB(NOW(),INTERVAL 1 HOUR)");
		sql.append(" and s.creator=sl.creator and s.createtime<sl.createtime");
		sql.append(" and operate_url='/logout.do') group by creator");
		return super.getSession().createSQLQuery(sql.toString()).list();
	}

	@Override
	public List<SysLog> findByAttIds(List<String> attIds) {
		if (null != attIds && attIds.size() > 0) {
			Finder sql=Finder.create("from SysLog Where flag=1");
			sql.append(" and( operateUrl like '%"+attIds.get(0)+"'");
			for (int i = 1; i < attIds.size(); i++) {
				sql.append(" or operateUrl like '%"+attIds.get(i)+"'");
			}
			sql.append(")  order by createTime desc");
			Pagination p = super.find(sql, 1, 30);
			return (List<SysLog>) p.getList();
		}
		return null;
	}

	@Override
	public List listUserLastLog(boolean isQuRole, int size) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("Select s.creator,(select name from sys_user Where pid=s.creator),s.createtime,s.operate_url,s.operate_content from sys_operate_log s");
		sql.append(",(Select creator,max(createtime) createtime from sys_operate_log Where operate_url not in ('/logout.do','/login.do') group by creator) l ");
		sql.append(" Where s.creator=l.creator and s.createtime=l.createtime");
		if(isQuRole){
			sql.append(" and  s.creator in (Select userid from sys_user_role Where roleid=(Select pid from sys_role Where pflag=1 and rolecode='QU_ROLE'))");
		}else{
			sql.append(" and  s.creator in (Select userid from sys_user_role Where roleid=(Select pid from sys_role Where pflag=1 and rolecode='STREET_ROLE'))");
		}
		sql.append(" group by s.creator,(select name from sys_user Where pid=s.creator),s.createtime,s.operate_url,s.operate_content");
		sql.append(" order by s.createtime desc");
		sql.append(" limit "+size);
		return super.getSession().createSQLQuery(sql.toString()).list();
	}

	@Override
	public List<String[]> listAPPUserLastLog(boolean isQuRole, int size) {
		StringBuffer sql=new StringBuffer("Select s.creator,(select name from sys_user Where pid=s.creator),s.createtime,s.operate_url,s.operate_content from sys_operate_log s");
		sql.append(",(Select creator,max(createtime) createtime from sys_operate_log Where operate_url not in ('/app') group by creator) l ");
		sql.append(" Where s.creator=l.creator and s.createtime=l.createtime and type='app'");
		if(isQuRole){
			sql.append(" and  s.creator in (Select userid from sys_user_role Where roleid=(Select pid from sys_role Where pflag=1 and rolecode='QU_ROLE'))");
		}else{
			sql.append(" and  s.creator in (Select userid from sys_user_role Where roleid=(Select pid from sys_role Where pflag=1 and rolecode='STREET_ROLE'))");
		}
		sql.append(" group by s.creator,(select name from sys_user Where pid=s.creator),s.createtime,s.operate_url,s.operate_content");
		sql.append(" order by s.createtime desc");
		sql.append(" limit "+size);
		return super.getSession().createSQLQuery(sql.toString()).list();
	}
	
	@Override
	public List<SysLog> exportList(SysLog log) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from SysLog Where flag=1");
		if(null!=log){
			if(!StringUtil.isEmpty(log.getCurrentlogName())){
				f.append(" and creator.street.streetCode = :streetCode");
				f.setParam("streetCode",log.getCurrentlogName());
			}
			if(!StringUtil.isEmpty(log.getLogName())){
				f.append(" and creator.name like :name");
				f.setParam("name","%"+log.getLogName()+"%");
			}
			if(!StringUtil.isEmpty(log.getOperateContent())){
				f.append(" and operateContent like :operateContent");
				f.setParam("operateContent", "%"+log.getOperateContent()+"%");
			}
			if(!StringUtil.isEmpty(log.getType())){
				f.append(" and type = :type");
				f.setParam("type",log.getType());
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				if(!StringUtil.isEmpty(log.getStartTime())){
					f.append(" and createTime >=:startTime").setParam("startTime",sdf.parse(log.getStartTime()));
				}
				if(!StringUtil.isEmpty(log.getEndTime())){
					f.append(" and createTime <=:endTime").setParam("endTime",sdf.parse(log.getEndTime()));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		f.append(" order by createTime desc");
		return super.find(f);
	}

}
