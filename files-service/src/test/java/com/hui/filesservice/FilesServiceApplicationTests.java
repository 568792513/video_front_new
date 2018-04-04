package com.hui.filesservice;

import com.hui.filesservice.controller.FileController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilesServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilesServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Resource
	private FileController fileController;

	@Test
	public void deleteTest(){
		fileController.removeHeadImg("test.jpg");
	}

}
