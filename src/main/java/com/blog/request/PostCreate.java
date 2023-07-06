package com.blog.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;
    @NotBlank(message = "컨텐츠를 입력해주세요.")
    private String content;

    public PostCreate(String title, String conent) {
        this.title = title;
        this.content = conent;
    }

    @Override
    public String toString() {
        return "PostCreate{" +
                "title='" + title + '\'' +
                ", conent='" + content + '\'' +
                '}';
    }
}
