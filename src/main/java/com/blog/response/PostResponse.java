package com.blog.response;

import com.blog.domain.Post;
import lombok.Builder;
import lombok.Getter;


/**
 * 서비스 정책에 맞는 클래스
 * title은 10자 까지만!
 */

@Getter
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }

    /**
     * Post -> PostResponse
     * @param post
     */
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle().substring(0, Math.min(post.getTitle().length(), 10));
        this.content = post.getContent();
    }
}
