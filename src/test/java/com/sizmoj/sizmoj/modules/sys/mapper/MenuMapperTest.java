package com.sizmoj.sizmoj.modules.sys.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.Menu;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class MenuMapperTest extends SpringTransactionalTestCase{
	@Autowired
	private MenuMapper menuMapper;
	
	//@Test
	public void testGetMenuById() {
		Menu menu = menuMapper.getMenuById(1L);
		assertNotNull(menu.getName());
		System.out.println(11);
		
	}

	//@Test
	public void testGetMenuByRoles() {
		List<Menu> menus =  menuMapper.getMenuByRoles("1");
		assertTrue(menus.size() >= 2);
		
	}

	//@Test
	public void testGetAllMenu() {
		List<Menu> menus =  menuMapper.getAllMenu();
		assertTrue(menus.size() >= 2);
	}


	//@Test
	public void testAddMenu() {
		Menu menu = new Menu();
		Menu parent = new Menu();
		parent.setId(1L);
		menu.setParent(parent);
		menu.setParentIds("1,2");
		menu.setName("asd");
		menu.setSort(1);
		menu.setIsShow("0");
		menuMapper.addMenu(menu);
		System.out.println(menu.getId());
		assertNotNull(menu.getId());
		
	}

	//@Test
	//@Rollback(value = false)
	public void testUpdateMenu() {
		Menu menu = new Menu();
		Menu parent = new Menu();
		parent.setId(71L);
		menu.setParent(parent);
		menu.setParentIds("1,2");
		menu.setName("dsa");
		menu.setSort(1);
		menu.setIsShow("0");
		menuMapper.updateMenu(menu);
	}
	//@Test
	@Rollback(value = false)
	public void testdeleteById() {
		menuMapper.deleteById(1L, "%,1,%");
	}

}
