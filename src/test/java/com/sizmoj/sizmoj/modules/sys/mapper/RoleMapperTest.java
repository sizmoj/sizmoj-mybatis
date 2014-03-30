package com.sizmoj.sizmoj.modules.sys.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.Role;
import com.sizmoj.sizmoj.modules.sys.entity.User;

@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class RoleMapperTest extends SpringTransactionalTestCase{
	
	@Autowired
	private RoleMapper roleMapper;
	
	//@Test
	public void testFindAllRole() {
		List<Role> users = roleMapper.findAllRole();
		assertTrue(users.size() > 5);
	}

	//@Test
	@Rollback(value = false)
	public void testDeleteRole() {
		roleMapper.deleteRole(2L);		
	}

	//@Test
	public void testAddRole() {
		Role role = new Role();
		role.setName("aa");
		roleMapper.insertRole(role);
		assertNotNull(role.getId());
	}

	//@Test
	@Rollback(value = false)
	public void testUpdateRole() {
		Role role = new Role();
		role.setName("kkk");
		role.setId(7L);
		roleMapper.updateRole(role);
		
	}

	//@Test
	public void testFindRoleByUser() {
		User user = new User();
		user.setId(1L);
		List<Role> roles = roleMapper.findRoleByUser(user);
		for(Role r :roles) {
			System.out.println(r.getName());
		}
	}

	//@Test
	public void testFindRoleByRoleId() {
		Role role = roleMapper.findRoleByRoleId(1L);
		assertNotNull(role.getId());
	}

}
