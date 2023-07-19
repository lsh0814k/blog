package com.blog.request;

import lombok.Builder;
import lombok.Getter;

import static java.lang.Math.*;

@Getter
@Builder // class 레벨에 @Builder가 있어야 @Builder.Default가 적용된다.
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

    public long getOffset() {
        return (long) (max(page, 1) - 1) * min(size,  MAX_SIZE);
    }
}
