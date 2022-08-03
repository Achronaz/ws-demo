package com.example.demo.config.kafka;

import com.example.demo.config.websocket.WebSocketHandler;
import com.example.demo.config.websocket.WsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class KafkaConfig {

    private ConcurrentHashMap<String,String> result = new ConcurrentHashMap<>();
    public static final String TOPIC_P1 = "p1";

    @KafkaListener(topics = {TOPIC_P1}, groupId = "g1", autoStartup = "true")
    public void processMessage(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, @Payload String payload) throws IOException {
        //result.put(key,payload);
        log.info("Got message: key={}, payload={}", key, payload);
        WebSocketHandler.send(payload, new WsRequest());
    }

}
