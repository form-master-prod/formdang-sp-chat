package com.kr.formdang.service;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatRoomService {

    <T> void sendMessage(String channel, T message);

    void createRoom(String channel);

    void deleteRoom(String channel);

    List<String> findRooms();

   void entranceRoom(String channel, WebSocketSession session);

   void exitRoom(String channel, WebSocketSession session);
}
