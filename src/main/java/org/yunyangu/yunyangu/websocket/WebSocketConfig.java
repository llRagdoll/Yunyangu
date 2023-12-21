package org.yunyangu.yunyangu.websocket;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebScoket配置处理器
 */
@Component
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    MyWebSocketHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/ws").addInterceptors(new HandShake());

        registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
    }

}