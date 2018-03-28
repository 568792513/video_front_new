package com.hui.vedio.userservice;

import com.hui.user_service.UserServiceApplication;
import com.hui.user_service.entity.User;
import com.hui.user_service.service.UserService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceApplicationTests {

	@Resource
	private UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getMyInfo() {
		User user = userService.findUserById(3L);
		System.out.println(user);
	}

}
