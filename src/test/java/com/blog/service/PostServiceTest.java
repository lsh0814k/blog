package com.blog.service;

import com.blog.domain.Post;
import com.blog.repository.PostRepository;
import com.blog.request.PostCreate;
import com.blog.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.data.domain.Sort.Direction.DESC;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void write() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // when
        postService.write(postCreate);

        // then
        assertThat(postRepository.count()).isSameAs(1L);

        Post findPost = postRepository.findAll().get(0);
        assertThat(findPost.getTitle()).isEqualTo("제목입니다.");
        assertThat(findPost.getContent()).isEqualTo("내용입니다.");
    }

    @Test
    @DisplayName("글 1개 조회")
    void findPost() {
        // given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        // when
        postRepository.save(post);

        // when
        PostResponse findPost = postService.findPost(post.getId());

        // then
        assertThat(findPost).isNotNull();
        assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(findPost.getContent()).isEqualTo(post.getContent());
    }

    /**
     * ExceptionHandler를 통해 예외를 처리하지 않으면 테스트가 실패한다.
     * 따라서 ExceptionHandler를 통해 예외를 처리하자.
     */
    @Test
    @DisplayName("존재하지 않는 글 1개 조회")
    void findNonExistPost() {
        // given
        Long postId = 1L;

        // expected
        assertThatThrownBy(() -> postService.findPost(postId)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("서비스 정책에 따라 title은 10자 까지만 표시 된다.(underLength)")
    void underLengthTitle() {
        // given
        Post post = Post.builder()
                .title("12345")
                .content("내용입니다.")
                .build();

        postRepository.save(post);

        // when
        PostResponse response = postService.findPost(post.getId());

        // then
        assertThat(response.getTitle().length()).isSameAs(post.getTitle().length());
    }

    @Test
    @DisplayName("서비스 정책에 따라 title은 10자 까지만 표시 된다.(overLength)")
    void overLengthTitle() {
        // given
        Post post = Post.builder()
                .title("1234567890123456")
                .content("내용입니다.")
                .build();

        postRepository.save(post);


        // when
        PostResponse response = postService.findPost(post.getId());

        // then
        assertThat(response.getTitle().length()).isSameAs(10);
    }


    @Test
    @DisplayName("글 여러개 조회")
    void findAll() {
        // given
        Post post1 = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("제목2")
                .content("내용2")
                .build();
        postRepository.save(post2);

        // when
        List<PostResponse> posts = postService.findAll(PageRequest.of(0, 5));

        // then
        assertThat(posts.size()).isSameAs(2);
    }

    @Test
    @DisplayName("글 1페이지 조회(페이징)")
    void firstPage() {
        // given
        List<Post> requestPosts = IntStream.range(1, 31)
                        .mapToObj(idx -> Post.builder()
                                .title("제목 " + idx)
                                .content("내용 " + idx)
                                .build()
                        )
                        .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        // when
        List<PostResponse> posts = postService.findAll(PageRequest.of(0, 5, DESC, "id"));

        // then
        assertThat(posts.size()).isSameAs(5);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 30");
        assertThat(posts.get(4).getTitle()).isEqualTo("제목 26");
    }
}