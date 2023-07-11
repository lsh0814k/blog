package com.blog.controller;

import com.blog.domain.Post;
import com.blog.request.PostCreate;
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
    public Post findPost(@PathVariable(name = "postId") Long id) {
        return postService.findById(id);
    }
}
