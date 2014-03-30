/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sizmoj.sizmoj.common.config;

import org.apache.commons.io.IOUtils;
import org.springside.modules.utils.PropertiesLoader;



/**
 * 全局配置类
 * @author ThinkGem
 * @version 2013-03-23
 */
public class Global {
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader;
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader("classpath:/application.properties");
		}
		return propertiesLoader.getProperty(key);
	}

	/////////////////////////////////////////////////////////
	
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	public static void main(String[] args) {
		 char a = IOUtils.DIR_SEPARATOR;
		String ss = Global.getConfig("urlSuffix");
	}
}
