package com.kr.formdang.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Slf4j
public class SocketChatRoom {

    private String channel;
    /**
     * https://taes-k.github.io/2021/12/26/synchronizedlist-copyonwritelist/
     */
    private CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public SocketChatRoom(String channel) {
        this.channel = channel;
    }

    public void entranceRoom(WebSocketSession session) {
        sessions.addIfAbsent(session);
        log.debug("세션 수: {}", sessions.size());
    }

    public void exitRoom(WebSocketSession session) {
        sessions.remove(session);
        log.debug("세션 수: {}", sessions.size());
    }
}
