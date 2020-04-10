package com.gwideal.common.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/**
 * 
* @Description: WebSocket全局变量
* @author li_chong
* @date 2017-11-3 下午4:54:13
* @version V1.0
 */
@Configuration
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //前台 可以使用websocket环境
        registry.addHandler(new MyWebSocketHandler(),"/socket").addInterceptors(new HandshakeInterceptor());
      //前台 不可以使用websocket环境，则使用sockjs进行模拟连接
        registry.addHandler(new MyWebSocketHandler(), "/socket").addInterceptors(new HandshakeInterceptor())
                .withSockJS();
    }

    
}
