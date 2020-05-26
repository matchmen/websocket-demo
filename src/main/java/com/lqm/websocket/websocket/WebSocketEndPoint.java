package com.lqm.websocket.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;

import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint("/net/websocket/{key}/{name}")
public class WebSocketEndPoint {

    @OnOpen
    public void onOpen(@PathParam("key") String key, @PathParam("name") String name, Session session){
        log.info("有新的连接：{}", session);
        add(createKey(key, name), session);
        WebSocketHandler.sendMessage(session, key + name);
        log.info("在线人数：{}",count());
        sessionMap().keySet().forEach(item -> log.info("在线用户：", item));
        for (Map.Entry<String, Session> item : sessionMap().entrySet()){
            log.info("12: {}", item.getKey());
        }
    }



}
