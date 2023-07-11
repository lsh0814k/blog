package com.blog.response;

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
@RequiredArgsConstructor
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final List<FieldErrorDetail> fieldErrorDetails;
}
