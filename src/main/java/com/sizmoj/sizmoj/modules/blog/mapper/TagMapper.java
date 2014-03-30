package com.sizmoj.sizmoj.modules.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.blog.entity.Post;
import com.sizmoj.sizmoj.modules.blog.entity.Tag;
@MybatisRepository
public interface TagMapper {
	public long add(Tag tag);
	public long delete(long id);
	public void update(Tag tag);
	public List<Tag> findAll();
	public List<Tag> findByCondition(@Param("name") String name);
	public List<Tag> findByPostId(long postId);
	public long findByConditionCount(@Param("name") String name);
	public long count();
	public void deletePostTagByTagId(long tagId);
}
