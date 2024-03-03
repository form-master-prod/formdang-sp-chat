package com.kr.formdang.model.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GlobalCode {

    SUCCESS("0", "성공"),
    SYSTEM_ERROR("9999", "미지정 오류"),
    NOT_EXIST_TOKEN("9998", "토큰 누락 오류"),
    PARAMETER_ERROR("9997", "요청 파라미터 오류"),
    BIND_ERROR("9996", "파라미터 검증 오류"),
    HTTP_MESSAGE_NOT_READABLE_ERROR("9996","잘못된 자료형 요청 오류"),
    NOT_EXIST_HEADER("9995", "헤더 누락 오류"),
    NOT_EXIST_PARAM("9994", "파라미터 누락 오류"),
    IO_EXCEPTION("9993", "입출력 오류"),
    NOT_FOUND_PAGE("9992", "잘못된 API PATH 요청 오류"),

    ;

    private final String code;
    private final String msg;

}
