package com.minwoo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minwoo.domain.post.PostMapper;
import com.minwoo.domain.post.PostRequest;
import com.minwoo.domain.post.PostResponse;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest
class MinwooworldApplicationTests {
	@Autowired
	PostMapper postMapper;

	@Test
	void save() {
		PostRequest params = new PostRequest();
		params.setTitle("1번 게시글");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		params.setNoticeYn(false);
		postMapper.save(params);

		List<PostResponse> posts = postMapper.findAll();
		System.out.println("전체 게시글 수 : " + posts.size() + "개입니다.");
	}

	@Test
	void findById() {
		PostResponse post = postMapper.findById(1L);
		try {
			String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
			System.out.println(postJson);

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
