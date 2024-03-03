package com.kr.formdang.controller;

import com.kr.formdang.entity.ChatRoom;
import com.kr.formdang.jwt.JwtService;
import com.kr.formdang.model.common.GlobalCode;
import com.kr.formdang.model.net.req.CreateChatRoomRequest;
import com.kr.formdang.model.net.res.FindChatRoomResponse;
import com.kr.formdang.model.root.DefaultResponse;
import com.kr.formdang.model.root.RootResponse;
import com.kr.formdang.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final JwtService jwtService;

    @PostMapping(value = "/create/chat/room")
    public ResponseEntity<RootResponse> createChatRoom(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody CreateChatRoomRequest request
            ) {
        try {
            log.info("■ 1. 채팅방 생성 요청 성공");
            final Long userId = jwtService.getId(token); // 유저 아이디 세팅
            log.info("■ 2. 채팅방 등록 쿼리 시작");
            chatRoomRepository.save(new ChatRoom(userId, request.getName()));
            log.info("■ 3. 채팅방 생성 응답 성공");
            return ResponseEntity.ok().body(new DefaultResponse());
        } catch (Exception e) {
            log.error("■ 채탕방 생성 응답 오류", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }


    @GetMapping(value = "/find/chat/room")
    public ResponseEntity<RootResponse> findChatRoom(
            @RequestHeader("Authorization") String token
    ) {
        try {
            log.info("■ 1. 채팅방 조회 요청 성공");
            final Long userId = jwtService.getId(token); // 유저 아이디 세팅
            log.info("■ 2. 채팅방 조회 쿼리 시작");
            List<ChatRoom> rooms = chatRoomRepository.findByUserId(userId);
            log.info("■ 3. 채팅방 조회 응답 성공");
            return ResponseEntity.ok().body(new FindChatRoomResponse(rooms));
        } catch (Exception e) {
            log.error("■ 채탕방 조회 응답 오류", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }

}
