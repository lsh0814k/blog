package com.blog.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * {
 *     "code": "400"
 *     "message": "잘못된 요청입니다."
 *     "fieldErrorDetails": [{
 *          "field": "title"
 *          "message": "타이틀을 입력하세요."
 *          "rejectValue": ""
 *
 *     }]
 * }
 */
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final List<FieldErrorDetail> fieldErrorDetails;

    @Builder
    public ErrorResponse(String code, String message, List<FieldErrorDetail> fieldErrorDetails) {
        this.code = code;
        this.message = message;
        this.fieldErrorDetails = fieldErrorDetails;
    }
}
