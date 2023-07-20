package com.blog.controller;

import com.blog.request.PostCreate;
import com.blog.request.PostSearch;
import com.blog.response.PostResponse;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts-old")
    public List<PostResponse> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }


    @PostMapping("/posts")
    public void write(@RequestBody @Valid PostCreate request) {
        postService.write(request.createPost());
    }

    @GetMapping("/posts/{postId}")
    public PostResponse findPost(@PathVariable(name = "postId") Long id) {
        // 서비스 정책이 추가 된다면 서비스 정책에 맞는 응답 클래스를 만들어서 반환을 하자!!
        return postService.findPost(id);
    }
}
