package com.blog.controller;

import com.blog.request.PostCreate;
import com.blog.response.PostResponse;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void write(@RequestBody @Valid PostCreate request) {
        postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse findPost(@PathVariable(name = "postId") Long id) {
        // 서비스 정책이 추가 된다면 서비스 정책에 맞는 응답 클래스를 만들어서 반환을 하자!!
        return postService.findPost(id);
    }
}
