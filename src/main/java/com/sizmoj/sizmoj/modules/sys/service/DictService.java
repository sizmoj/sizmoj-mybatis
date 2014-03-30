package com.sizmoj.sizmoj.modules.sys.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sizmoj.sizmoj.common.persistence.Page;
import com.sizmoj.sizmoj.modules.sys.entity.Dict;
import com.sizmoj.sizmoj.modules.sys.mapper.DictMapper;



/**
 * 字典Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class DictService {

	@Autowired
	private DictMapper dictMapper;
	
	public Dict get(Long id) {
		return dictMapper.findById(id);
	}
	
	public Page<Dict> find(Page<Dict> page, Dict dict) {
		if(StringUtils.isEmpty(dict.getType())) {
			dict.setType(null);
		}
		if(StringUtils.isEmpty(dict.getDescription())) {
			dict.setDescription(null);
		}
		page.setList(dictMapper.findByCondition(dict.getType(), dict.getDescription(), (page.getPageNo()-1) * page.getPageSize(), page.getPageSize()));
		page.setCount(dictMapper.findByConditionCount(dict.getType(), dict.getDescription()));
		return page;
	}
	public List<String> findTypeList(){
		return dictMapper.findTypeList();
	}
	
	@Transactional(readOnly = false)
	public void save(Dict dict) {
		dictMapper.add(dict);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		dictMapper.deleteById(id);
	}
	
}
