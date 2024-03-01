package com.demo.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.blog.Repository.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {

	@Autowired
	UserRepo uRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest()
	{
		String className = uRepo.getClass().getName();
		String packName= uRepo.getClass().getPackageName();
		
		System.out.println(className);
		System.out.println(packName);
	}
}
