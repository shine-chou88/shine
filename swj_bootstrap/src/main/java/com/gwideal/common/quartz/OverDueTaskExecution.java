package com.gwideal.common.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwideal.core.manager.impl.IndexMngImpl;


public class OverDueTaskExecution implements Job{
	protected static final Logger log=LoggerFactory.getLogger(OverDueTaskExecution.class);
	@Autowired
	private IndexMngImpl indexMngImpl;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("发送逾期短信内容任务开始");
		try{
			Integer num = indexMngImpl.sendOverDueMsg();
			log.info("发送了"+num+"短信");
		}catch(Exception e){
			log.error("发送逾期短信内容任务失败",e);
		}
		log.info("发送逾期短信内容任务结束");
	}

}
