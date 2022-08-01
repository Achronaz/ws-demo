package com.example.demo.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"t1"}, groupId = "g1", autoStartup = "true")
    public void processMessage(String message) {
        log.info("got message: {}", message);
        
    }

}
