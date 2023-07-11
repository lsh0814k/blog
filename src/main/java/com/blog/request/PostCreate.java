package com.blog.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostCreate {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostCreate{" +
                "title='" + title + '\'' +
                ", conent='" + content + '\'' +
                '}';
    }
}
