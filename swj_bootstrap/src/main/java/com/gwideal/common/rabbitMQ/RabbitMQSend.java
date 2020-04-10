package com.gwideal.common.rabbitMQ;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gwideal.common.util.DBHelper;
import com.gwideal.common.util.StringUtil;
import com.rabbitmq.client.*;
/**
 * 
* @Description 消息推送-生产者  
* @author li_chong  
* @date 2018-9-20  
* @version V1.0
 */
public class RabbitMQSend {
	private static final Logger log = LoggerFactory.getLogger(RabbitMQSend.class);
	
	public static final int QITA = 99;//其他
	
	public static final int TASKDISTRIBUTE = 1;//稳控任务派发
	
	/**
	 * 推送单条
	 * @param queue 队列：用户id
	 * @param message
	 */
	public static boolean sendOne(String queue,String message){
		if (StringUtil.isEmpty(queue) || StringUtil.isEmpty(message)) {
			return false;
		}
		Connection connection = null;
		Channel channel = null;
		try {
			//创建连接和通道 
			connection = DBHelper.getMqCon(); 
			channel = connection.createChannel(); 
			//为通道指明队列 
			channel.queueDeclare(queue, false, false, false, null); 
			//发布消息
			channel.basicPublish("", queue, null, message.getBytes("UTF-8")); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendOne:"+e.getMessage());
		} finally {
			//关闭连接
			if (null != channel) {
				try {
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return false;
	}
	/**
	 * 给多个用户推送相同信息
	 * @param queueList 用户id集合
	 * @param message
	 * @return
	 */
	public static boolean sendAll(List<String> queueList,String message){
		if (null == queueList || queueList.size() == 0 || StringUtil.isEmpty(message)) {
			return false;
		}
		Connection connection = null;
		Channel channel = null;
		try {
			//创建连接和通道 
			connection = DBHelper.getMqCon(); 
			channel = connection.createChannel(); 
			for (String queue : queueList) {
				if (!StringUtil.isEmpty(queue)) {
					//为通道指明队列 
					channel.queueDeclare(queue, false, false, false, null); 
					//发布消息
					channel.basicPublish("", queue, null, message.getBytes("UTF-8")); 
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendAll.List<String>:"+e.getMessage());
		} finally {
			//关闭连接
			if (null != channel) {
				try {
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return false;
	}
	
	/**
	 * 给多个用户推送相同信息
	 * @param queues 用户id数组
	 * @param message
	 * @return
	 */
	public static boolean sendAll(String[] queues,String message){
		if (null == queues || queues.length == 0 || StringUtil.isEmpty(message)) {
			return false;
		}
		Connection connection = null;
		Channel channel = null;
		try {
			//创建连接和通道 
			connection = DBHelper.getMqCon(); 
			channel = connection.createChannel(); 
			for (int i = 0; i < queues.length; i++) {
				if (!StringUtil.isEmpty(queues[i])) {
					//为通道指明队列 
					channel.queueDeclare(queues[i], false, false, false, null); 
					//发布消息
					channel.basicPublish("", queues[i], null, message.getBytes("UTF-8")); 
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendAll.String[]:"+e.getMessage());
		} finally {
			//关闭连接
			if (null != channel) {
				try {
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return false;
	}
}

