package com.sizmoj.sizmoj.modules.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.sizmoj.sizmoj.common.persistence.Page;
import com.sizmoj.sizmoj.modules.sys.entity.Menu;
import com.sizmoj.sizmoj.modules.sys.entity.Role;
import com.sizmoj.sizmoj.modules.sys.entity.User;
import com.sizmoj.sizmoj.modules.sys.mapper.MenuMapper;
import com.sizmoj.sizmoj.modules.sys.mapper.RoleMapper;
import com.sizmoj.sizmoj.modules.sys.mapper.UserMapper;
import com.sizmoj.sizmoj.modules.sys.security.SystemAuthorizingRealm;
import com.sizmoj.sizmoj.modules.sys.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class SystemService{

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired(required = false)
	private SystemAuthorizingRealm systemRealm;
	
	public User getUser(Long id) {
		User user = userMapper.findUserById(id);
		user.setRoleList(roleMapper.findRoleByUser(user));
		return user;
	}
	
	public Page<User> findUser(Page<User> page, User user) {
		page.setList(userMapper.findByPage((page.getPageNo()-1) 
				* page.getPageSize(), page.getPageSize()));
		page.setCount(userMapper.count());
		return page;
	}
	
	public User getUserByLoginName(String loginName) {
		List<User> users = userMapper.findByLoginName(loginName);
		return users.size() > 0 ? users.get(0) : null;
	}
	
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		if(user.getId() == null || user.getId() == 0L) {
			userMapper.insertUser(user);
		} else {
			userMapper.updateUser(user);
		}
	}
	
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		userMapper.deleteUser(id);
	}
	
	@Transactional(readOnly = false)
	public void updatePasswordById(Long id, String loginName, String newPassword) {
		userMapper.updatePasswordById(id, loginName, newPassword);
	}
	
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(Long id) {
		userMapper.updateLoginInfo(SecurityUtils.getSubject().getSession().getHost(), new Date(), id);
	}
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}

	//-- Menu Service --//
	
	public Menu getMenu(Long id) {
		return menuMapper.getMenuById(id);
	}

	public List<Menu> findAllMenu(){
		return menuMapper.getAllMenu();
	}
	
	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {
		menu.setParent(this.getMenu(menu.getParent().getId()));
		String oldParentIds = menu.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");
		if(menu.getId() == null) {
			menuMapper.addMenu(menu);			
		} else {
			menuMapper.updateMenu(menu);
		}
		// 更新子节点 parentIds
		List<Menu> list = menuMapper.findByParentIdsLike("%,"+menu.getId()+",%");
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuMapper.updateMenu(e);
		}
		systemRealm.clearAllCachedAuthorizationInfo();
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}

	@Transactional(readOnly = false)
	public void deleteMenu(Long id) {
		menuMapper.deleteById(id, "%,"+id+",%");
		systemRealm.clearAllCachedAuthorizationInfo();
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}
	
	//-- Role Service --//
	
	public Role getRole(Long id) {
		return roleMapper.findRoleByRoleId(id);
	}

	public Role findRoleByName(String name) {
		return roleMapper.findByName(name);
	}
	
	public List<Role> findAllRole(){
		return roleMapper.findAllRole();
	}
	
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if(role.getId() == null) {
			roleMapper.insertRole(role);
			for(String id : role.getMenuIds().split(",")) {			
				roleMapper.addRoleMenu(role.getId(), Long.parseLong(id));
			}				
		} else {
			roleMapper.updateRole(role);
			roleMapper.deleteRoleMenu(role.getId());
			for(String id : role.getMenuIds().split(",")) {			
				roleMapper.addRoleMenu(role.getId(), Long.parseLong(id));
			}
		}
		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteRole(Long id) {
		roleMapper.deleteRole(id);
		systemRealm.clearAllCachedAuthorizationInfo();

	}
	
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, Long userId) {
		User user = userMapper.findUserById(userId);
		List<Role> userroles = roleMapper.findRoleByUser(user);
		List<Long> roleIds = new ArrayList<Long>();
		for(Role temprole : userroles){
			roleIds.add(temprole.getId());
		}
		List<Role> roles = userroles;
		// 
		if (roleIds.contains(role.getId())) {
			roles.remove(role);
			userMapper.deleteUserRole(userId, role.getId());
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, Long userId) {
		userMapper.addUserRole(userId, role.getId());		
		return getUser(userId);
	}
}
