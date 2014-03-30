package com.sizmoj.sizmoj.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.sys.entity.Role;
import com.sizmoj.sizmoj.modules.sys.entity.User;

@MybatisRepository
public interface RoleMapper {
	public List<Role> findAllRole();
	public void deleteRole(long id);
	public long insertRole(Role role);
	public void updateRole(Role role);
	public List<Role> findRoleByUser(User user);
	public Role findRoleByRoleId(long id);
	public void deleteRoleMenu(@Param("roleId") long roleId);
	public void addRoleMenu(@Param("roleId") long roleId, @Param("menuId") long menuId);
	public Role findByName(@Param("name") String name);
}
