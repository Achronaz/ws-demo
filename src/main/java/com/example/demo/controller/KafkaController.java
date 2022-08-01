package com.example.demo.controller;

import com.example.demo.config.kafka.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/push/{msg}")
    public String sendReq(@PathVariable("msg") String msg){
        String key = "key"+System.currentTimeMillis();
        kafkaTemplate.send(KafkaConfig.TOPIC_P1, key, msg);
        return "sent, key="+key;
    }

}
