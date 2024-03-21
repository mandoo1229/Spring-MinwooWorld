package com.minwoo;

import com.minwoo.domain.post.PostRequest;
import com.minwoo.domain.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;
    @Test
    void saveByForeach() {
        for (int i = 1; i < 1000; i++) {
            PostRequest params = new PostRequest();
            params.setTitle(i + "번 게시글 제목");
            params.setContent(i + "번 게시글 내용");
            params.setWriter("테스트" + i);
            params.setNoticeYn(false);
            postService.savePost(params);
        }
    }
}
