package com.kr.formdang.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ChatRoomService {

    void addUserToRoom(String roomId, String sessionId);
    Set<String> getUsersInRoom(String roomId);
    void removeUserFromRoom(String roomId, String sessionId);

}
