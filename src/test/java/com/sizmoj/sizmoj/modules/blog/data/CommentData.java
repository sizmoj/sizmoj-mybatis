package com.sizmoj.sizmoj.modules.blog.data;

import java.util.Date;

import org.springside.modules.test.data.RandomData;

import com.sizmoj.sizmoj.modules.blog.entity.Comment;
import com.sizmoj.sizmoj.modules.blog.entity.Post;

public class CommentData {
	public static Comment randomComment() {
		Comment comment = new Comment();
		Post post = new Post();
		post.setId(1L);
		comment.setPost(post);
		comment.setAuthor(randomAuthor());
		comment.setEmail(randomEmail());
		comment.setUrl(randomUrl());
		comment.setIp(randomIp());
		comment.setDateTime(new Date());
		comment.setText(randomText());
		comment.setAgent(randomAgent());
		Comment pcomment = new Comment();
		pcomment.setId(1L);;
		comment.setParentComment(pcomment);
		return comment;
	}
	private static String randomAgent() {
		return RandomData.randomName("Agent");
	}
	private static String randomText() {
		return RandomData.randomName("Text");
	}
	private static String randomIp() {
		return RandomData.randomName("ip");
	}
	private static String randomUrl() {
		return RandomData.randomName("url");
	}
	public static String randomAuthor() {
		return RandomData.randomName("author");
	}
	public static String randomEmail() {
		return RandomData.randomName("email");
	}
}
