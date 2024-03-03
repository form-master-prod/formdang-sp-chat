package com.kr.formdang.sub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageSubscriber implements MessageListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("String Message received: " + message.toString());
        try {
            Map map = new ObjectMapper().readValue(message.toString(), Map.class);
            messagingTemplate.convertAndSend("/sub/message/" + map.get("id"), map.get("content")); // 구독 메세지 전달
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
