package com.example.demo.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class KafkaConfig {

    private ConcurrentHashMap<String,String> result = new ConcurrentHashMap<>();
    public static final String TOPIC_P1 = "p1";

    @KafkaListener(topics = {TOPIC_P1}, groupId = "g1", autoStartup = "true")
    public void processMessage(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, @Payload String payload) {
        result.put(key,payload);
        log.info("Got message: key={}, payload={}", key, payload);
    }

}
