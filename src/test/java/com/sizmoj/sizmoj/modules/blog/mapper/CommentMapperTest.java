package com.sizmoj.sizmoj.modules.blog.mapper;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.blog.data.CommentData;
import com.sizmoj.sizmoj.modules.blog.entity.Comment;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class CommentMapperTest extends SpringTransactionalTestCase{
	@Autowired
	private CommentMapper commentMapper;
	
	//@Test
	public void testAdd() {
		int count = countRowsInTable("blog_comment");
		commentMapper.add(CommentData.randomComment());
		assertEquals(count + 1, countRowsInTable("blog_comment"));
	}

	//@Test
	public void testDelete() {
		commentMapper.add(CommentData.randomComment());
		int count = countRowsInTable("blog_comment");
		commentMapper.delete(1L);
		assertEquals(count - 1, countRowsInTable("blog_comment"));
	}

	//@Test
	public void testUpdate() {
		Comment comment = CommentData.randomComment();
		commentMapper.add(comment);
		Comment updatecomment = CommentData.randomComment();
		updatecomment.setId(comment.getId());
		commentMapper.update(updatecomment);
		assertEquals(1, countRowsInTableWhere("blog_comment", "Agent='" + updatecomment.getAgent() + "'"));
	}

	//@Test
	public void testFindAll() {
		commentMapper.add(CommentData.randomComment());
		commentMapper.add(CommentData.randomComment());
		List<Comment> list = commentMapper.findAll();
		int count = countRowsInTable("blog_comment");
		assertEquals(count, list.size());
	}

	//@Test
	//@Rollback(value = false)
	public void testGet() {
		commentMapper.add(CommentData.randomComment());
		Comment comment = commentMapper.get(1L);
		assertEquals(1L, comment.getId().longValue());
	}
	
	//@Test
	public void testCount() {
		commentMapper.add(CommentData.randomComment());
		commentMapper.add(CommentData.randomComment());
		long size = commentMapper.count();
		int count = countRowsInTable("blog_comment");
		assertEquals(count, size);
	}

	//@Test
	public void testFindByCondition() {
		commentMapper.add(CommentData.randomComment());
		commentMapper.add(CommentData.randomComment());
		//List<Comment> lists = commentMapper.findByCondition(1L, null, null, 0L, 10L);
		int count = countRowsInTable("blog_comment");
		//assertEquals(count, lists.size());
		
	}

	//@Test
	public void testCountByCondition() {
		commentMapper.add(CommentData.randomComment());
		commentMapper.add(CommentData.randomComment());
		long count =commentMapper.countByCondition(1L, null, null);
		assertEquals(count, countRowsInTable("blog_comment"));
	}

	//@Test
	public void testDeleteCommentByPostId() {
		commentMapper.add(CommentData.randomComment());
		commentMapper.add(CommentData.randomComment());
		commentMapper.deleteCommentByPostId(1L);
		assertEquals(0, countRowsInTable("blog_comment"));
	}

}
