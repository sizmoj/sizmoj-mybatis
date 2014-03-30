package com.sizmoj.sizmoj.modules.sys.service;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sizmoj.sizmoj.common.persistence.Page;
import com.sizmoj.sizmoj.common.utils.StringUtils;
import com.sizmoj.sizmoj.modules.sys.entity.Log;
import com.sizmoj.sizmoj.modules.sys.mapper.LogMapper;



@Service
@Transactional(readOnly = true)
public class LogService{

	@Autowired
	private LogMapper logMapper;
	
	public Log get(Long id) {
		return logMapper.findById(id);
	}
	
	public Page<Log> find(Page<Log> page, Map<String, Object> paramMap) {
		Long id = StringUtils.toLong(paramMap.get("createById")) == 
				0L ? null : StringUtils.toLong(paramMap.get("createById"));
		String requestUri = ObjectUtils.toString(paramMap.get("requestUri"));
		if(StringUtils.isBlank(requestUri)) {
			requestUri = null;
		}
		String exception = ObjectUtils.toString(paramMap.get("exception"));
		if(StringUtils.isBlank(exception)) {
			exception = null;
		}	
		page.setList(logMapper.findByCondition(id, requestUri, exception, null, null, 
				(page.getPageNo() - 1) * page.getPageSize(),  page.getPageSize()));
		page.setCount(logMapper.findByConditionCount(id, requestUri, exception, null, null));
		return page;
	}
	
}
