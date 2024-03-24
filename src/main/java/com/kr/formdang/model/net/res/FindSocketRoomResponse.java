package com.kr.formdang.model.net.res;

import com.kr.formdang.entity.ChatRoom;
import com.kr.formdang.model.root.DefaultResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindSocketRoomResponse extends DefaultResponse {

    private List<String> channels; // 채팅방 아이디 리스트

}
