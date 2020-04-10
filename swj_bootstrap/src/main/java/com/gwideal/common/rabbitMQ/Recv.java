package com.gwideal.common.rabbitMQ;

import java.io.IOException;

import com.gwideal.common.util.DBHelper;
import com.rabbitmq.client.*;

public class Recv {
	 private final static String QUEUE_NAME = "hello"; 
	 public static void main(String[] argv) throws Exception { 
		 //建立连接和通道 
		 Connection connection = DBHelper.getMqCon();  
		 final Channel channel = connection.createChannel(); 
		 //声明要消费的队列 
		 channel.queueDeclare(QUEUE_NAME, false, false, false, null); 
		 System.out.println(" [*] Waiting for messages. To exit press CTRL+C"); 
		 channel.basicQos(1);
		 //回调消费消息 
		 Consumer consumer = new DefaultConsumer(channel) { 
			 @Override 
			 public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, 
					 byte[] body) throws IOException { 
				 try {
					 String message = new String(body, "UTF-8"); 
					 System.out.println(" [x] Received '" + message + "'"); 
					 Thread.sleep(2000);
					 System.out.println(" [x] Done");
				     channel.basicAck(envelope.getDeliveryTag(), false);
				    } catch (Exception e) {
						e.printStackTrace();
					} 
			} 
		}; 
		channel.basicConsume(QUEUE_NAME, false, consumer); 
	}


}
