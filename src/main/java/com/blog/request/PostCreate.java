package com.blog.request;

import java.util.Objects;

public class PostCreate {
    private String title;
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
