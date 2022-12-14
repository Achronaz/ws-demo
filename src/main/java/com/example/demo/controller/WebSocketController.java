package com.example.demo.controller;

import com.example.demo.config.websocket.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class WebSocketController {

    @PostMapping("/ws/send")
    public String wsSend(@RequestBody WsSendRequest request) throws IOException {
        WebSocketHandler.send(request);
        return "done";
    }

}
