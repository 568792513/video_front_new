package com.hui.comment_service;

import com.hui.comment_service.entity.Comment;
import com.hui.comment_service.entity.User;
import com.hui.comment_service.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentApplicationTests {

	@Resource
	private CommentService commentService;

	@Test
	public void contextLoads() {
		Date time = new Date();
		String content = "ok";
		Comment comment = new Comment();
		User user = new User();
		user.setId(456489L);
		user.setName("zhaohui");
		comment.setUser(user);
		comment.setVideoId(2L);
		comment.setContent(content);
		comment.setTime(time);
		commentService.save(comment);


	}
	@Test
	public void test() {
		Pageable page = new PageRequest(0, 2);
//		List<Comment> commentList = commentService.getAllByVideoId(2L, page);
//		commentList.forEach(System.out::println);

	}

}
