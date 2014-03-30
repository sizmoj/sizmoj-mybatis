package com.sizmoj.sizmoj.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.sys.entity.Dict;


@MybatisRepository
public interface DictMapper {
	public void deleteById(Long id);
	public List<Dict> findAllList();
	public List<String> findTypeList();
	public List<Dict> findByPage(@Param("prefix") long prefix, @Param("suffix") long suffix);
	public void update(Dict dict);
	public long count();
	public long add(Dict dict);
	public Dict findById(long id);
	public List<Dict> findByCondition(@Param("type") String type, @Param("description") String description, @Param("prefix") long prefix, @Param("suffix") long suffix);
	public long findByConditionCount(@Param("type") String type, @Param("description") String description);
}
