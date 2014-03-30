package com.sizmoj.sizmoj.modules.sys.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.User;

@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserMapperTest extends SpringTransactionalTestCase{
	
	@Autowired
	private UserMapper userMapper;
	
	//@Test
	public void findUserByIdTest() {
		User user = userMapper.findUserById(1L);
		assertTrue(user.getCreateBy().getId() == 1L);
	}
	
	//@Test
	public void insertUser() {
		User user = new User();
		user.setName("dsadsa");
		user.setLoginName("yoning");
		user.setPassword("123456");
		user.setDelFlag("0");
		User createBy = new User();
		createBy.setId(3L);
		user.setCreateBy(createBy);
		userMapper.insertUser(user);
		Assert.notNull(user.getId());
		
	}
	//@Test
	public void findUserByUserNamePassword() {
		User user = new User();
		user.setLoginName("thinkgem");
		user.setPassword("02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032");
		user = userMapper.findUserByUserNamePassword(user);
		Assert.notNull(user.getId());
		
	}
	
	//@Test
	public void findAllUserTest() {
		List<User> users = userMapper.findAll();
		assertNotNull(users);
	}
	//@Test
	public void deleteUserTest() {
		userMapper.deleteUser(43L);
		
	}
	
	//@Test
	public void updateUserTest() {
		User user = new User();
		user.setId(42L);
		user.setLoginName("yoning");
		user.setName("dsadsa");
		user.setPassword("123456");
		user.setEmail("dsadsadas@dsa");
		userMapper.updateUser(user);
	}
	
	//@Test
	//@Rollback(value = false)
	public void  TestupdatePasswordById() {
		long id = userMapper.updatePasswordById(14L, "sdjnlx_zhb", "1234567");
	}
	//@Test
	public void  TestfindByLoginName() {
		List<User> users = userMapper.findByLoginName("admin");
		assertTrue(users.get(0).getLoginName().equals("admin"));
	}
	//@Test
	public void  TestupdateLoginInfo() {
		userMapper.updateLoginInfo("128.0.0.1", new Date(), 1L);
		
	}
}
