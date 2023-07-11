package com.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PUBLIC;

@Entity
@NoArgsConstructor(access = PUBLIC)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
