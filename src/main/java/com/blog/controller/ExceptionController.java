package com.blog.controller;

import com.blog.response.ErrorResponse;
import com.blog.response.FieldErrorDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(BindingResult bindingResult, Locale locale) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .fieldErrorDetails(
                        bindingResult.getFieldErrors()
                                .stream()
                                .map(fieldError -> new FieldErrorDetail(fieldError.getField(), messageSource.getMessage(fieldError, locale), fieldError.getRejectedValue()))
                                .collect(Collectors.toList())
                )
                .build();

        return response;
    }

    // TODO errorMessage 정의해서 처리하자.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentHandler(IllegalArgumentException e) {
        Map<String, String> message = new HashMap<>();
        message.put("message", e.getMessage());
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
}
