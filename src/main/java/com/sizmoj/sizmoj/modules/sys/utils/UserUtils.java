package com.sizmoj.sizmoj.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Maps;
import com.sizmoj.sizmoj.common.utils.SpringContextHolder;
import com.sizmoj.sizmoj.modules.sys.entity.Menu;
import com.sizmoj.sizmoj.modules.sys.entity.Role;
import com.sizmoj.sizmoj.modules.sys.entity.User;
import com.sizmoj.sizmoj.modules.sys.mapper.MenuMapper;
import com.sizmoj.sizmoj.modules.sys.mapper.RoleMapper;
import com.sizmoj.sizmoj.modules.sys.mapper.UserMapper;
import com.sizmoj.sizmoj.modules.sys.security.SystemAuthorizingRealm.ShiroUser;



public class UserUtils {
	private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
	private static MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
	private static RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
	
	public static final String CACHE_USER = "user";
	public static final String CACHE_MENU_LIST = "menuList";

	

	public static User getUser(){
		User user = (User)getCache(CACHE_USER);
		if (user == null){
			ShiroUser principal = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = userMapper.findUserById(principal.getId());
				putCache(CACHE_USER, user);
			}
		}
		if (user == null){
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getUser();
	}

	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();			
			if (user.isAdmin()){
				menuList = menuMapper.getAllMenu();
			}else{
				List<Role> roles = roleMapper.findRoleByUser(user);
				StringBuilder sb = new StringBuilder();
				for(Role r : roles) {
					sb.append(r.getId());
					sb.append(",");
				}
				sb.deleteCharAt(sb.length()-1);
				menuList = menuMapper.getMenuByRoles(sb.toString());
			}
			//putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	public static List<Menu> getMenuListByRole(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();	
			List<Role> roles = roleMapper.findRoleByUser(user);
			StringBuilder sb = new StringBuilder();
			for(Role r : roles) {
				sb.append(r.getId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			menuList = menuMapper.getMenuByRoles(sb.toString());
		}
		return menuList;
	}
	
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	public static Map<String, Object> getCacheMap(){
		Map<String, Object> map = Maps.newHashMap();
		try{
			Subject subject = SecurityUtils.getSubject();
			ShiroUser principal = (ShiroUser)subject.getPrincipal();
			return principal!=null?principal.getCacheMap():map;
		}catch (UnavailableSecurityManagerException e) {
			return map;
		}
	}


}
