package com.sizmoj.sizmoj.modules.sys.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.sys.entity.Log;

@MybatisRepository
public interface LogMapper {
	public List<Log> findAllList();
	public List<Log> findByPage(@Param("prefix") long prefix, @Param("suffix") long suffix);
	public long count();
	public long add(Log log);
	public Log findById(long id);
	public List<Log> findByCondition(@Param("id") Long id, @Param("requestUri") String requestUri, 
			@Param("exception") String exception, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("prefix") long prefix, @Param("suffix") long suffix);
	public long findByConditionCount(@Param("id") Long id, @Param("requestUri") String requestUri, 
			@Param("exception") String exception, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

}
