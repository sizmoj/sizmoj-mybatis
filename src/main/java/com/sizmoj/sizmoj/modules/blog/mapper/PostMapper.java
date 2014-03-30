package com.sizmoj.sizmoj.modules.blog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.blog.entity.Post;

@MybatisRepository
public interface PostMapper {
	public long add(Post post);
	public long delete(long id);
	public void update(Post post);
	public Post get(Long id);
	public List<Post> findAll();
	public long count();
	public List<Post> findByCondition(@Param("tagId") Long tagId, @Param("postTitle") String postTitle,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
			@Param("termId") Long termId, @Param("startPage") int startPage, @Param("pageSize") int pageSize);
	/**防止前台展示冻结的文章,设计BUG**/
	public List<Post> findByConditionFront(@Param("tagId") Long tagId, @Param("postTitle") String postTitle,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
			@Param("termId") Long termId, @Param("startPage") int startPage, @Param("pageSize") int pageSize);
	public long countByCondition(@Param("tagId") Long tagId, @Param("postTitle") String postTitle,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
			@Param("termId") Long termId);
	public long countByConditionFront(@Param("tagId") Long tagId, @Param("postTitle") String postTitle,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
			@Param("termId") Long termId);
	public void deletePostTagByPostId(long postId);
	public void addPostTag(@Param("postId") long postId, @Param("tagId") long tagId);
	public List<Post> getNewsPost();

	
	
}
