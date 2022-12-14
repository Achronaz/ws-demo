package com.example.demo.config.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WsResponse {
    private String status;
    private String type;
    private String id;
    private String msg;
    private Map<String,Object> data;

}
