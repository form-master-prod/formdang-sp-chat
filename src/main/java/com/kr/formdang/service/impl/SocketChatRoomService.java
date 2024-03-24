package com.kr.formdang.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.formdang.model.dto.SocketChatRoom;
import com.kr.formdang.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketChatRoomService implements ChatRoomService {

    private Map<String, SocketChatRoom> chatRoomMap = Collections.synchronizedMap(new HashMap<>());
    private final ObjectMapper objectMapper;

    @Override
    public <T> void sendMessage(String channel, T message) {
        if (!chatRoomMap.containsKey(channel)) return;
        SocketChatRoom chatRoom = chatRoomMap.get(channel);
        // parallelStream 사용시 버그 발생 이유 확인 필요
        chatRoom.getSessions().forEach(it -> {
            try {
                if (it.isOpen()) {
                    it.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
                } else {
                    chatRoom.getSessions().remove(it);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void createRoom(String channel) {
        if (chatRoomMap.containsKey(channel)) return;
        chatRoomMap.put(channel, new SocketChatRoom(channel));
    }

    @Override
    public void deleteRoom(String channel) {
        if (!chatRoomMap.containsKey(channel)) return;
        chatRoomMap.remove(channel);
    }

    @Override
    public List<String> findRooms() {
        return new LinkedList<>(chatRoomMap.keySet());
    }

    @Override
    public void entranceRoom(String channel, WebSocketSession session) {
        if (!chatRoomMap.containsKey(channel)) return;
        SocketChatRoom chatRoom = chatRoomMap.get(channel);
        chatRoom.entranceRoom(session);
    }

    @Override
    public void exitRoom(String channel, WebSocketSession session) {
        if (!chatRoomMap.containsKey(channel)) return;
        SocketChatRoom chatRoom = chatRoomMap.get(channel);
        chatRoom.exitRoom(session);
    }

}
