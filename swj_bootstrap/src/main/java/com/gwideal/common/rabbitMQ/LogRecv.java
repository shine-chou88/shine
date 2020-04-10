package com.gwideal.common.rabbitMQ;

import java.io.IOException;

import com.gwideal.common.util.DBHelper;
import com.rabbitmq.client.*;

public class LogRecv {
	private final static String QUEUE_NAME = "appLog"; 
	private static Connection connection = DBHelper.getMqCon(); 
	private static LogRecv logRecv = new LogRecv();
	private LogRecv(){}
	
	public static LogRecv getInstance(){
		return logRecv;
	}
	
	private static void appLog() throws Exception{
		if (null == connection) {
			connection = DBHelper.getMqCon();  
		}
		final Channel channel = connection.createChannel(); 
		//声明要消费的队列 
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		 //回调消费消息 
		 Consumer consumer = new DefaultConsumer(channel) { 
			 @Override 
			 public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, 
					 byte[] body) throws IOException { 
				 try {
					 String message = new String(body, "UTF-8"); 
					 
				     channel.basicAck(envelope.getDeliveryTag(), false);
			    } catch (Exception e) {
					e.printStackTrace();
				} 
			} 
		}; 
		channel.basicConsume(QUEUE_NAME, false, consumer); 
	}
	static {
		 try {
			 appLog();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}	

}
