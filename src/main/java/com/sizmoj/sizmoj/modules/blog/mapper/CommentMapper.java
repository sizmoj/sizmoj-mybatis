package com.sizmoj.sizmoj.modules.blog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.blog.entity.Comment;
@MybatisRepository
public interface CommentMapper {
	public long add(Comment Comment);
	public long delete(long id);
	public void update(Comment Comment);
	public Comment get(long id);
	public List<Comment> findAll();
	public long count();
	public List<Comment> findByCondition(@Param("postId") Long postId, 
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
				@Param("startPage") int startPage, @Param("pageSize") int pageSize);
	public long countByCondition(@Param("postId") Long postId, 
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	public void deleteCommentByPostId(long postId);
}
