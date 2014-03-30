package com.sizmoj.sizmoj.modules.sys.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.Log;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class LogMapperTest extends SpringTransactionalTestCase{
	@Autowired
	private LogMapper logMapper;
	//@Test
	public void testFindByPage() {
		
	}

	//@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	//@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	//@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	//@Test
	public void testFindByCondition() {
		List<Log> lists = logMapper.findByCondition(null, null, null, null, null, 0, 10);
		System.out.println(lists.size());
	}

}
