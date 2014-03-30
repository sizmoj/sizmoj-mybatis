package com.sizmoj.sizmoj.modules.sys.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.sys.entity.User;

@MybatisRepository
public interface UserMapper {
	public long insertUser(User user);
	public User findUserById(long id);
	public List<User> findAll();
	public List<User> findByPage(@Param("prefix") long prefix, @Param("suffix") long suffix);
	public User findUserByUserNamePassword(User user);
	public void deleteUser(long userId);
	public void updateUser(User user);
	public long updatePasswordById(@Param("id") Long id, @Param("loginName") String loginName, @Param("newPassword") String newPassword);
	public List<User> findByLoginName(String loginName);
	public void deleteUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
	public void addUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
	public int updateLoginInfo(@Param("loginIp") String loginIp, @Param("loginDate") Date loginDate, @Param("id") Long id);
	public long count();
}
