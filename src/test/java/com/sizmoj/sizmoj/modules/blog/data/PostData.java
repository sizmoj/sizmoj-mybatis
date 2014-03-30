package com.sizmoj.sizmoj.modules.blog.data;

import java.util.Date;

import org.springside.modules.test.data.RandomData;

import com.sizmoj.sizmoj.modules.blog.entity.Post;
import com.sizmoj.sizmoj.modules.blog.entity.Term;
import com.sizmoj.sizmoj.modules.sys.entity.User;

/**
 * Post相关实体测试数据生成.
 * 
 * @author biggernin
 */
public class PostData {
	public static Post randomPost() {
		Post post = new Post();
		post.setPublicDate(new Date());
		User user = new User();
		user.setId(1L);
		post.setAuthor(user);
		post.setContent(randomContent());
		post.setPostTitle(randomPostTitle());
		post.setPassword(randomPassword());
		post.setPostModified(new Date());
		Post parentPost = new Post();
		parentPost.setId(1L);
		post.setParentPost(parentPost);
		Term term = new Term();
		post.setMenuOrder(new Long(randomMenuOrder()).intValue());
		post.setCommentCount(randomCommentCount());
		term.setId(1L);
		post.setTerm(term);
		return post;
	}
	public static String randomContent() {
		return RandomData.randomName("文章内容");
	}
	public static String randomPostTitle() {
		return RandomData.randomName("文章标题");
	}
	public static Long randomMenuOrder() {
		return RandomData.randomId();
	}
	public static String randomPassword() {
		return RandomData.randomName("password");
	}
	public static Long randomCommentCount() {
		return RandomData.randomId();
	}
}
