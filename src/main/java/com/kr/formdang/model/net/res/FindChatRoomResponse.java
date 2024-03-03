package com.kr.formdang.model.net.res;

import com.kr.formdang.entity.ChatRoom;
import com.kr.formdang.model.root.DefaultResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindChatRoomResponse extends DefaultResponse {

    private List<ChatRoom> chatRooms;

}
