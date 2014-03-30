package com.sizmoj.sizmoj.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sizmoj.sizmoj.common.utils.SpringContextHolder;
import com.sizmoj.sizmoj.modules.sys.entity.Dict;
import com.sizmoj.sizmoj.modules.sys.mapper.DictMapper;


/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);

	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		Map<String, List<Dict>> dictMap = Maps.newHashMap();
		for (Dict dict : dictMapper.findAllList()){
			List<Dict> dictList = dictMap.get(dict.getType());
			if (dictList != null){
				dictList.add(dict);
			}else{
				dictMap.put(dict.getType(), Lists.newArrayList(dict));
			}
		}
		List<Dict> dictList = Lists.newArrayList();
		if (dictList != null){
			dictList = dictMap.get(type);
		}
		return dictList;
	}
	
}
