package com.kr.formdang.pub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.formdang.config.RedisConfig;
import com.kr.formdang.model.common.GlobalCode;
import com.kr.formdang.model.net.req.SendChatRequest;
import com.kr.formdang.model.root.DefaultResponse;
import com.kr.formdang.model.root.RootResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessagePublisher {

    private final RedisTemplate<String, String> redisTemplate;

    @PostMapping(value = "/send/chat/{id}")
    public ResponseEntity<RootResponse> sendChat(
            @RequestBody SendChatRequest request,
            @NotNull @PathVariable("id") String id
    ) {
        try {
            log.error("■ 1. 메세지 전송 요청 성공");
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("content", request.getContent());
            redisTemplate.convertAndSend(RedisConfig.CHANNEL_TOPIC, new ObjectMapper().writeValueAsString(map)); // 레디스에 메세지 pub
            return ResponseEntity.ok().body(new DefaultResponse());
        } catch (Exception e) {
            log.error("■ 메세지 전송 오류", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }

}
