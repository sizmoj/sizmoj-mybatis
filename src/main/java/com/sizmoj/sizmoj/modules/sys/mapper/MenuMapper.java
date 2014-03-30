package com.sizmoj.sizmoj.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sizmoj.sizmoj.common.persistence.MybatisRepository;
import com.sizmoj.sizmoj.modules.sys.entity.Menu;

@MybatisRepository
public interface MenuMapper {
	public Menu getMenuById(long id);
	public List<Menu> getMenuByRoles(String RoleIds);
	public List<Menu> getAllMenu();
	public long addMenu(Menu menu);
	public long updateMenu(Menu menu);
	public List<Menu> findByParentIdsLike(String string);
	public int deleteById(@Param("id")Long id, @Param("likeParentIds") String likeParentIds);
}
