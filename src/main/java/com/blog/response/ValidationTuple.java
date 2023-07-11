package com.blog.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationTuple {
    private final String fieldName;
    private final String errorMessage;
}
