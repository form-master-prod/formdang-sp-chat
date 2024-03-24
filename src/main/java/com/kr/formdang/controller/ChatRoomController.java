package com.kr.formdang.controller;

import com.kr.formdang.model.common.GlobalCode;
import com.kr.formdang.model.net.req.CreateSocketRoomRequest;
import com.kr.formdang.model.net.res.FindSocketRoomResponse;
import com.kr.formdang.model.root.DefaultResponse;
import com.kr.formdang.model.root.RootResponse;
import com.kr.formdang.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatRoomService socketChatRoomService;

    @GetMapping(value = "/socket/find/room")
    public ResponseEntity<RootResponse> findSocketRoom() {
        try {
            log.info("■ 1. 채팅방 조회 요청 성공");
            log.info("■ 2. 채팅방 조회 쿼리 시작");
            List<String> rooms = socketChatRoomService.findRooms();
            log.info("■ 3. 채팅방 조회 응답 성공");
            return ResponseEntity.ok().body(new FindSocketRoomResponse(rooms));
        } catch (Exception e) {
            log.error("■ 채탕방 조회 응답 오류", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }

    @PostMapping(value = "/socket/create/room")
    public ResponseEntity<RootResponse> createSocketRoom(
            @Valid @RequestBody CreateSocketRoomRequest request
    ) {
        try {
            log.info("■ 1. 채팅방 생성 요청 성공");
            log.info("■ 2. 채팅방 등록 시작");
            socketChatRoomService.createRoom(request.getChannel());
            log.info("■ 3. 채팅방 생성 응답 성공");
            return ResponseEntity.ok().body(new DefaultResponse());
        } catch (Exception e) {
            log.error("■ 채탕방 생성 응답 오류", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }

}
