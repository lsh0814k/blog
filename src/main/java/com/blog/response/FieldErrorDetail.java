package com.blog.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FieldErrorDetail {
    private final String field;
    private final String message;
    private final Object rejectValue;

}
