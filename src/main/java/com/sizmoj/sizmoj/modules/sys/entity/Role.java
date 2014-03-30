package com.sizmoj.sizmoj.modules.sys.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.sizmoj.sizmoj.common.persistence.DataEntity;

/**
 * 角色Entity
 * @author Biggernin
 * @version 2013-05-15
 */
public class Role extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	private Long id;	 	// 编号
	private String name; 	// 角色名称
	private String enname;	//英文名称

	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表

	
	public Role() {
		super();
	}

	public Role(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public List<Long> getUserIdList() {
		List<Long> nameIdList = Lists.newArrayList();
		for (User user : userList) {
			nameIdList.add(user.getId());
		}
		return nameIdList;
	}

	public String getUserIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (User user : userList) {
			nameIdList.add(user.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Long> getMenuIdList() {
		List<Long> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		menuList = Lists.newArrayList();
		for (Long menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	public String getMenuIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			nameIdList.add(menu.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}
	
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			for (String menuId : ids) {
				Menu menu = new Menu();
				menu.setId(new Long(menuId));
				menuList.add(menu);
			}
		}
	}
	
	


	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission()!=null && !"".equals(menu.getPermission())){
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}
	
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(Long id){
		return id != null && id.equals(1L);
	}
	
}
