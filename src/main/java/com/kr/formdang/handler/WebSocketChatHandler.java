package com.kr.formdang.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.formdang.model.net.handler.SocketMessage;
import com.kr.formdang.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    private final ChatRoomService socketChatRoomService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            log.debug("[입장]");
            super.afterConnectionEstablished(session);
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(Objects.requireNonNull(session.getUri()).toString()).build();
            String channel = Objects.requireNonNull(uriComponents.getQueryParams().getFirst("channel"));
            String name = uriComponents.getQueryParams().getFirst("name");
            log.debug("{}", name);
            socketChatRoomService.entranceRoom(channel, session);
            socketChatRoomService.sendMessage(channel, new SocketMessage(channel, name, "안녕하세요. " + name + "님이 입장하셨습니다."));
        } catch (Exception e) {
            log.error("[오류 발생]", e);
            session.close();
            throw e;
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SocketMessage socketMessage = objectMapper.readValue(message.getPayload(), SocketMessage.class);
        log.debug("{}", socketMessage);
        if (StringUtils.isNoneEmpty(socketMessage.getMsg())) {
            socketChatRoomService.sendMessage(socketMessage.getChannel(), socketMessage);
        }
    }



    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.debug("[종료]");
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(Objects.requireNonNull(session.getUri()).toString()).build();
        String channel = uriComponents.getQueryParams().getFirst("channel");
        String name = uriComponents.getQueryParams().getFirst("name");
        socketChatRoomService.exitRoom(channel, session);
        socketChatRoomService.sendMessage(channel, new SocketMessage(channel, name, name + "님이 퇴장하셨습니다.."));
        session.close();
    }



}
