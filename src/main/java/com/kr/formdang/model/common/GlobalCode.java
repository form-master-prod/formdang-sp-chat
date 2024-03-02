package com.kr.formdang.model.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GlobalCode {

    SUCCESS("0", "성공"),
    SYSTEM_ERROR("9999", "미지정 오류"),
    ;

    private final String code;
    private final String msg;

}
