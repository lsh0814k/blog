package com.blog.controller;

import com.blog.request.PostCreate;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * MediaType.APPLICATION_JSON 요청
     * @param request
     * @return
     */
    @PostMapping("/posts")
    public Map<String, String> post2(@RequestBody @Valid PostCreate request) {
        postService.write(request);
        return Map.of();
    }
}
