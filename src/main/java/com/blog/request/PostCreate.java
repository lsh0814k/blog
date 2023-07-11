package com.blog.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostCreate {
    @NotBlank
    private String title;
    @NotBlank
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
