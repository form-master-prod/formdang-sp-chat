package com.kr.formdang.service.impl;

import com.kr.formdang.service.ChatRoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Service
public class StompChatRoomService implements ChatRoomService {


    @Override
    public <T> void sendMessage(String channel, T message) {

    }

    @Override
    public void createRoom(String channel) {

    }

    @Override
    public void deleteRoom(String channel) {

    }

    @Override
    public List<String> findRooms() {
        return null;
    }

    @Override
    public void entranceRoom(String channel, WebSocketSession session) {

    }

    @Override
    public void exitRoom(String channel, WebSocketSession session) {

    }
}
