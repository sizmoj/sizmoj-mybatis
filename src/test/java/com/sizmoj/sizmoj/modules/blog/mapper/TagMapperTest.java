package com.sizmoj.sizmoj.modules.blog.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.blog.data.TagData;
import com.sizmoj.sizmoj.modules.blog.entity.Tag;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class TagMapperTest extends SpringTransactionalTestCase{
	
	@Autowired
	private TagMapper tagMapper;
	
	//@Test
	public void testAdd() {
		int count = countRowsInTable("blog_tag");
		tagMapper.add(TagData.RandomTag());
		assertEquals(count + 1, countRowsInTable("blog_tag"));
	}

	//@Test
	public void testDelete() {
		Tag tag = TagData.RandomTag();
		tagMapper.add(tag);
		int count = countRowsInTable("blog_tag");
		tagMapper.delete(tag.getId());
		assertEquals(count - 1, countRowsInTable("blog_tag"));
	}

	//@Test
	public void testUpdate() {
		Tag tag = TagData.RandomTag();
		tagMapper.add(tag);
		Tag tagupdate = TagData.RandomTag();
		tagupdate.setId(tag.getId());
		tagMapper.update(tagupdate);
		assertEquals(1, countRowsInTableWhere(
				"blog_tag", "name ='" + tagupdate.getName() + "'"));
	}

	//@Test
	public void testFindAll() {
		tagMapper.add(TagData.RandomTag());
		tagMapper.add(TagData.RandomTag());
		List<Tag> tags = tagMapper.findAll();
		assertEquals(tags.size(), countRowsInTable("blog_tag"));
	}

	//@Test
	public void testCount() {
		tagMapper.add(TagData.RandomTag());
		tagMapper.add(TagData.RandomTag());
		long tags = tagMapper.count();
		assertEquals(tags, countRowsInTable("blog_tag"));
	}

	//@Test
	public void testDeletePostTagByTagId() {
		
	}
	//@Test
	public void testFindByConditionCount() {
		Tag tag = TagData.RandomTag();
		tagMapper.add(tag);
		long count = tagMapper.findByConditionCount(tag.getName());
		assertEquals(count, countRowsInTableWhere(
				"blog_tag", "name ='" + tag.getName() + "'"));
		List<Tag> tags = tagMapper.findByCondition(tag.getName());
		assertEquals(tags.size(), countRowsInTableWhere(
				"blog_tag", "name ='" + tag.getName() + "'"));
	}

}
