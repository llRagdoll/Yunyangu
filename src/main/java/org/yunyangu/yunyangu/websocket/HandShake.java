
package org.yunyangu.yunyangu.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;
import java.util.Map;


/**
 * Socket建立连接（握手）和断开
 */
public class HandShake implements HandshakeInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		logger.info("========beforeHandshake======uid:{}",((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("uid"));
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			Integer uid = (Integer) session.getAttribute("uid");
			if(uid!=null){
				attributes.put("uid", uid);
			}else{
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	logger.info("========afterHandshake======uid:{}",((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("uid"));
	}

}
