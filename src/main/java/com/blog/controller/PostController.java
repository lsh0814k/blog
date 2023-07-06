package com.blog.controller;

import com.blog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostController {

    /**
     * MediaType.APPLICATION_FORM_URLENCODED 요청
     * @param params
     * @return
     */
    // @PostMapping("/posts")
    public String post(@ModelAttribute PostCreate params) {
        log.info("params={}", params.toString());
        return "Hello World";
    }

    @PostMapping("/posts")
    public String post2(@RequestBody PostCreate params) {
        log.info("params={}", params.toString());
        return "Hello World";
    }
}
