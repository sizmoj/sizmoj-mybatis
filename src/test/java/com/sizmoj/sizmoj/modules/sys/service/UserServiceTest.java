package com.sizmoj.sizmoj.modules.sys.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.User;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserServiceTest extends SpringTransactionalTestCase{
	@Autowired
	private UserService userService;

	//@Test
	public void testAddUser() {
		User user = new User();
		user.setLoginName("aaaa");
		user.setName("bbb");
		user.setPassword("cccc");
		user.setDelFlag("0");
		userService.addUser(user);
		long id = user.getId();
		System.out.println(id);
		assertNotNull(id);		
	}

	public void testSelectUser() {
		User user = userService.selectUser(1L);
		assertNotNull(user);
	}	
}
