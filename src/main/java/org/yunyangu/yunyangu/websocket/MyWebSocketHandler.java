package org.yunyangu.yunyangu.websocket;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.yunyangu.yunyangu.entity.ChatMessage;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.service.GroupService;
import org.yunyangu.yunyangu.service.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Socket处理器
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
	//用于保存HttpSession与WebSocketSession的映射关系
	public static final Map<Integer, WebSocketSession> userSocketSessionMap;
	private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
	@Autowired
	UserService userService;
	
	static {
		userSocketSessionMap = new ConcurrentHashMap<Integer, WebSocketSession>();
	}
	
	/**
	 * 建立连接后,把登录用户的id写入WebSocketSession
	 */
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		Integer uid = (Integer) session.getAttributes().get("uid");
		User u=userService.getUserById(uid);
		String username=u.getName();
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
			ChatMessage msg = new ChatMessage();
			msg.setFrom(0);//0表示上线消息
			msg.setText(username);
			this.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		}
		logger.info("========afterConnectionEstablished======uid:{}",uid);
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			if(message.getPayloadLength()==0)
				return;
			ChatMessage msg=new Gson().fromJson(message.getPayload().toString(),ChatMessage.class);
			msg.setDate(new Date());
			sendMessageToUser(msg.getTo(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前抛出异常用户的Socket会话
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				String username=userService.getUserById(entry.getKey()).getName();
				ChatMessage msg = new ChatMessage();
				msg.setFrom(-2);
				msg.setText(username);
				this.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");
		Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前用户的Socket会话
		while (it.hasNext()) {
			Entry<Integer, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				String username=userService.getUserById(entry.getKey()).getName();
				ChatMessage msg = new ChatMessage();
				msg.setFrom(-2);//下线消息，用-2表示
				msg.setText(username);
				this.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
				break;
			}
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	@Autowired
	GroupService groupService;

	public void broadcastGroup(String groupName,final TextMessage message) {
		Integer groupId=groupService.getGroupByName(groupName);
		List<Integer> groupUsers=groupService.getGroupMember(groupId);
		//多线程群发
		for (Entry<Integer, WebSocketSession> entry : userSocketSessionMap.entrySet()) {
			Integer userId = entry.getKey();

			// 检查群成员是否在线
			if (groupUsers.contains(userId) && entry.getValue().isOpen()) {
				new Thread(new Runnable() {
					public void run() {
						try {
							entry.getValue().sendMessage(message);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}
	/**
	 * 给所有在线用户发送消息
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {

        //多线程群发
        for (Entry<Integer, WebSocketSession> entry : userSocketSessionMap.entrySet()) {

            if (entry.getValue().isOpen()) {
                // entry.getValue().sendMessage(message);
                new Thread(new Runnable() {

                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }

        }
	}

	/**
	 * 给某个用户发送消息
	 * 
	 * @param uid
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(Integer uid, TextMessage message) throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
		logger.info("current--thread--name:【{}】",Thread.currentThread().getName());
	}

}
