package com.kr.formdang.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService{

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void addUserToRoom(String roomId, String sessionId) {
        this.redisTemplate.opsForSet().add("chat:rooms:" + roomId + ":users", sessionId);
    }

    @Override
    public Set<String> getUsersInRoom(String roomId) {
        return redisTemplate.opsForSet().members("chat:room:" + roomId + ":users");
    }

    @Override
    public void removeUserFromRoom(String roomId, String sessionId) {
        redisTemplate.opsForSet().remove("chat:rooms:" + roomId + ":users", sessionId);
    }
}
