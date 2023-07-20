package com.blog.request;

import com.blog.domain.QPost;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import lombok.Builder;
import lombok.Getter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Builder // class 레벨에 @Builder가 있어야 @Builder.Default가 적용된다.
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;
    @Builder.Default
    private String sort = "DESC";
    @Builder.Default
    private String sortField = "id";

    public long getOffset() {
        return (long) (max(page, 1) - 1) * min(size,  MAX_SIZE);
    }

    public OrderSpecifier getOrderBy() {
        Order order = sort.equals("DESC") ? Order.DESC : Order.ASC;
        if(sortField.equals("id")) {
            return new OrderSpecifier(order, QPost.post.id);
        }

        if(sortField.equals("title")) {
            return new OrderSpecifier(order, QPost.post.title);
        }

        if(sortField.equals("content")) {
            return new OrderSpecifier(order, QPost.post.content);
        }

        return new OrderSpecifier(order, NullExpression.DEFAULT);
    }
}
