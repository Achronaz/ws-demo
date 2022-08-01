package com.example.demo.config.websocket;


import com.example.demo.controller.WsSendRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    public static ConcurrentHashMap<String,WebSocketSession> SESSIONS = new ConcurrentHashMap<>();
    public static Gson GSON = new Gson();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        WsRequest req;
        WsResponse res = null;
        try{
            req = GSON.fromJson(message.getPayload(), WsRequest.class);
            res = WsResponse.builder()
                    .id(session.getId()).status("0").msg("").type(req.getType())
                    .data(req.getData()).build();
            log.info("req={}, res={}", req, res);
        } catch(Exception ex){
            res = WsResponse.builder().id(session.getId()).status("2").msg(ex.getMessage()).data(null).build();
        } finally {
            session.sendMessage(new TextMessage(GSON.toJson(res)));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId() ,session);
        WsResponse response = WsResponse.builder().id(session.getId()).status("0").msg("connected").data(null).build();
        session.sendMessage(new TextMessage(GSON.toJson(response)));
        log.info("connected, sessionId={}, size={}",session.getId(), SESSIONS.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        SESSIONS.remove(session.getId());
        log.info("disconnected, sessionId={}, size={}",session.getId(), SESSIONS.size());
    }

    public static void send(WsSendRequest req) throws IOException {
        String sessionId = req.getSessionId();
        Object payLoad = req.getData();
        WebSocketHandler.send(sessionId,payLoad);
    }
    public static void send(String sessionId, Object payload) throws IOException {
        SESSIONS.get(sessionId).sendMessage(new TextMessage(GSON.toJson(payload)));
    }

}
