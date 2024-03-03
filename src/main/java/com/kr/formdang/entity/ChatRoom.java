package com.kr.formdang.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "chatRoom")
public class ChatRoom {

    @Id
    private long id;
    private Long userId;
    private String name;

    public ChatRoom(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

}
