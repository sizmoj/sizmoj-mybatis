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

import com.sizmoj.sizmoj.modules.sys.entity.Dict;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class DictMapperTest extends SpringTransactionalTestCase{
	@Autowired
	private DictMapper dictMapper;
	
	//@Test
	public void testDeleteById() {
		dictMapper.deleteById(63L);
	}

	//@Test
	public void testFindAllList() {
		List<Dict> dicts = dictMapper.findAllList();
		assertTrue(dicts.size() > 20);
	}

	//@Test
	public void testFindTypeList() {
		List<String> dicts = dictMapper.findTypeList();
		assertTrue(dicts.size() > 4);
	}

	//@Test
	public void testFindByPage() {
		List<Dict> dicts = dictMapper.findByPage(2, 3);
		assertTrue(dicts.size() > 2);
	}

	//@Test
	public void testUpdate() {
		Dict dict = new Dict();
		dict.setId(65L);
		dict.setLabel("dsadas");
		dict.setValue("Dict");
		dict.setType("dsa");
		dict.setDescription("dsa");
		dict.setSort(1);
		dictMapper.update(dict);
		
		
	}

	//@Test
	public void testCount() {
		long count = dictMapper.count();
		assertTrue(count>5);
	}

	//@Test
	//@Rollback(value=false)
	public void testAdd() {
		Dict dict = new Dict();
		dict.setLabel("dsadas");
		dict.setValue("Dict");
		dict.setType("dsa");
		dict.setDescription("dsa");
		dict.setSort(1);
		dictMapper.add(dict);
		assertNotNull(dict.getId());
	}
	public void findByConditionTest() {
		Dict dict = new Dict();
		dict.setType("cms_del_flag");
		dictMapper.findByCondition(dict.getType(), null, 0, 10);
	}
}
