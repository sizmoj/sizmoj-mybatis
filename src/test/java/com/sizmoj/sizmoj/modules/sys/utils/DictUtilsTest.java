package com.sizmoj.sizmoj.modules.sys.utils;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.entity.Dict;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class DictUtilsTest extends SpringTransactionalTestCase{
	
	//@Test
	public void testGetDictLabel() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetDictValue() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetDictList() {
		List<Dict> lists = DictUtils.getDictList("show_hide");
		Assert.isTrue(lists.size() == 2);
	}

}
