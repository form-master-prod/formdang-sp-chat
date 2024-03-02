package com.kr.formdang.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "chatRoom")
public class ChatRoom {

    @Id
    private String id;
    private String userId;
    private String name;

    public ChatRoom(String id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

}
