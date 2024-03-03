package com.kr.formdang.model.net.req;


import com.kr.formdang.model.root.DefaultRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SendChatRequest extends DefaultRequest {

    @NotNull(message = "내용이 누락되었습니다.")
    private String content; // 내용
}
