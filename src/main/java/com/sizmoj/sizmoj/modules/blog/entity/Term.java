package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数配置 Option
 * @author biggernin
 * @version 2013-05-11
 */
public class Term implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3025035663944657410L;
	/**主键**/
	private Long id;
	/**名字**/
	private String name;
	/**缩略名**/
	private String slug;
	/**是否在导航栏中显示**/
	private String isShow;
	/**是否是一个页面**/
	private String isPage;
	/**所含有文章**/
	private List<Post> posts = new ArrayList<Post>();
	/**所含有文章**/
	private Long postCount;
	public Term() {
		super();
	}
	
	public Term(String name, String slug, String isShow, String isPage,
			List<Post> posts) {
		super();
		this.name = name;
		this.slug = slug;
		this.isShow = isShow;
		this.isPage = isPage;
		this.posts = posts;
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getIsPage() {
		return isPage;
	}
	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public Long getPostCount() {
		return postCount;
	}

	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
