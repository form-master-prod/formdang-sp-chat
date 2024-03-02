package com.kr.formdang.controller;

import com.kr.formdang.entity.ChatRoom;
import com.kr.formdang.model.common.GlobalCode;
import com.kr.formdang.model.root.DefaultResponse;
import com.kr.formdang.model.root.RootResponse;
import com.kr.formdang.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping(value = "/sp/public/create/chat/room")
    public ResponseEntity<RootResponse> createChatRoom(@RequestParam("name") String name, @RequestParam("userId") String userId) {
        try {
            String id = UUID.randomUUID().toString().replace("-", "");
            chatRoomRepository.save(new ChatRoom(id, userId, name));
            return ResponseEntity.ok().body(new DefaultResponse());
        } catch (Exception e) {
            log.error("{}", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }


    @GetMapping(value = "/sp/public/find/chat/room")
    public ResponseEntity<RootResponse> findChatRoom(@RequestParam("userId") String userId) {
        try {
            List<ChatRoom> chatRoomEntities = chatRoomRepository.findByUserId(userId);
            log.info("{}", chatRoomEntities);
            return ResponseEntity.ok().body(new DefaultResponse());
        } catch (Exception e) {
            log.error("{}", e);
            return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
        }
    }

}
