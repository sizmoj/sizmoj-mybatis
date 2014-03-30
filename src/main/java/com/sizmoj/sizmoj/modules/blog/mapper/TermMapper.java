package com.sizmoj.sizmoj.modules.blog.mapper;

import java.util.List;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.blog.entity.Term;
@MybatisRepository
public interface TermMapper {
	public long add(Term term);
	public long delete(long id);
	public void update(Term term);
	public Term get(long id);
	public List<Term> findAll();
	public long count();	
}
