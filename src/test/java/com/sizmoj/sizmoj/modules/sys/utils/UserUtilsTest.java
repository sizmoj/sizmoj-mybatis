package com.sizmoj.sizmoj.modules.sys.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.security.shiro.ShiroTestUtils;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.Menu;
import com.sizmoj.sizmoj.modules.sys.entity.User;
import com.sizmoj.sizmoj.modules.sys.security.SystemAuthorizingRealm.ShiroUser;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class UserUtilsTest extends SpringTransactionalTestCase{
	
	@InjectMocks
	private UserUtils userUtils;
	
	//@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ShiroTestUtils.mockSubject(new ShiroUser(3L, "foo", "Foo"));
	}
	//@Test
	public void testGetUser() {
		User user = UserUtils.getUser();
		assertNotNull(user);
	}
	//@Test
	public void testGetMenuList() {
		List<Menu> menus = userUtils.getMenuList();
		for(int i=0; i<menus.size(); i++) {
			System.out.println(menus.get(i).getHref() +"name:"+menus.get(i).getName()+ "id: " + menus.get(i).getId());
		}		
		assertTrue(menus.size()>20);
		
	}

	//@Test
	public void testGetCacheString() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetCacheStringObject() {
		fail("Not yet implemented");
	}

	//@Test
	public void testPutCache() {
		fail("Not yet implemented");
	}

	//@Test
	public void testRemoveCache() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetCacheMap() {
		fail("Not yet implemented");
	}

}
