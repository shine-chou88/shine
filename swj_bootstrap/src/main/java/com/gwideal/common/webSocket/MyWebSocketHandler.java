package com.gwideal.common.webSocket;

import java.util.ArrayList;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 
* @Description: 消息处理
* @author li_chong
* @date 2017-11-3 下午5:02:34
* @version V1.0
 */
public class MyWebSocketHandler implements WebSocketHandler{

	    // 保存所有的用户session
	    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

	    // 连接 就绪时 
	    @Override
	    public void afterConnectionEstablished(WebSocketSession session)
	            throws Exception {
	        users.add(session);
	    }
	    // 处理信息
	    @Override
	    public void handleMessage(WebSocketSession session,
	            WebSocketMessage<?> message) throws Exception {
//	      session.sendMessage(message);
	        // 处理消息 msgContent消息内容
	        TextMessage textMessage = new TextMessage(message.getPayload().toString(), true);
	        // 调用方法（发送消息给所有人）
	        sendMsgToAllUsers(textMessage);
	    }
	    // 处理传输时异常
	    @Override
	    public void handleTransportError(WebSocketSession session,
	            Throwable exception) throws Exception {
	    }

	    // 关闭 连接时
	    @Override
	    public void afterConnectionClosed(WebSocketSession session,
	            CloseStatus closeStatus) throws Exception {
	        users.remove(session);
	    }

	    @Override
	    public boolean supportsPartialMessages() {
	        return false;
	    }

	    // 给所有用户发送 信息
	    public void sendMsgToAllUsers(WebSocketMessage<?> message) throws Exception{
	        for (WebSocketSession user : users) {
	            user.sendMessage(message);
	        }
	    }

}
