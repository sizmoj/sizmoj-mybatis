package com.sizmoj.sizmoj.modules.blog.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.blog.data.PostData;
import com.sizmoj.sizmoj.modules.blog.entity.Post;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class PostMapperTest extends SpringTransactionalTestCase{
	
	@Autowired
	private PostMapper postMapper;
	
	//@Test
	public void testAdd() {
		int count = countRowsInTable("blog_post");
		postMapper.add(PostData.randomPost());
		assertEquals(count + 1, countRowsInTable("blog_post"));
	}

	//@Test
	public void testDelete() {
		int count = countRowsInTable("blog_post");
		postMapper.add(PostData.randomPost());
		assertEquals(count + 1, countRowsInTable("blog_post"));
		postMapper.delete(1L);
		assertEquals(0, countRowsInTableWhere("blog_post", "status = 1"));
	}

	//@Test
	public void testUpdate() {
		Post post = PostData.randomPost();
		System.out.println("post.getPassword():" + post.getPassword());
		Post updatepost = PostData.randomPost();
		updatepost.setId(post.getId());
		postMapper.update(updatepost);
		assertEquals(1, 
				countRowsInTableWhere("blog_post", "password='" + updatepost.getPassword() + "'"));
	}

	//@Test
	public void testFindAll() {
		postMapper.add(PostData.randomPost());
		int count = countRowsInTableWhere("blog_post", "status=0");
		assertEquals(count, postMapper.findAll().size());
	}

	//@Test
	public void testCount() {
		postMapper.add(PostData.randomPost());
		int count = countRowsInTableWhere("blog_post", "status=0");
		assertEquals(count, postMapper.count());
	}

	//@Test
	public void testFindByCondition() {
		Post post = PostData.randomPost();
		Post post1 = PostData.randomPost();
		Post post2 = PostData.randomPost();
		Post post3 = PostData.randomPost();
		postMapper.add(post);
		postMapper.add(post1);
		postMapper.add(post2);
		postMapper.add(post3);
		List<Post> lists =  postMapper.findByCondition(null, null, null, null,null, 0, 10);
		assertEquals(lists.size(), 4);
	}

	//@Test
	public void testCountByCondition() {
		Post post = PostData.randomPost();
		Post post1 = PostData.randomPost();
		Post post2 = PostData.randomPost();
		Post post3 = PostData.randomPost();
		postMapper.add(post);
		postMapper.add(post1);
		postMapper.add(post2);
		postMapper.add(post3);
		long count =  postMapper.countByCondition(null, null, null, null, null);
		assertEquals(count, 4);
	}



	//@Test
	public void testDeletePostTagByPostId() {
		postMapper.addPostTag(1, 1);
		postMapper.addPostTag(2, 2);
		postMapper.addPostTag(2, 3);
		int count = countRowsInTable("blog_post_tag");
		postMapper.deletePostTagByPostId(2);
		assertEquals(count, countRowsInTable("blog_post_tag") + 2 );
	}

	//@Test
	public void testAddPostTag() {
		int count = countRowsInTable("blog_post_tag");
		postMapper.addPostTag(2, 3);
		assertEquals(count, countRowsInTable("blog_post_tag") - 1);
	}

	//@Test
	public void testGet() {
		postMapper.add(PostData.randomPost());
		Post post = postMapper.get(1L);
		assertEquals(post.getId().longValue(), 1);
	}

}
