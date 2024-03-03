package com.kr.formdang.model.net.req;

import com.kr.formdang.model.root.DefaultRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRoomRequest extends DefaultRequest {

    @NotNull(message = "채팅방 제목이 누락되었습니다.")
    private String name; // 채팅방 제목
}
