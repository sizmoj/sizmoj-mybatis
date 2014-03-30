package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;

/**
 * 友情链接 Link
 * @author sizmoj
 * @version 2013-12-11
 */
public class PostMeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1360004042947175821L;
	/**主键**/
	private Long PostMetaId;
	/**文章**/
	private Post post;
	/**键名**/
	private String metaKey;
	/**键值**/
	private String metaValue;
	
	
	public PostMeta() {
		super();
	}
	public PostMeta(Long postMetaId, Post post, String metaKey, String metaValue) {
		super();
		PostMetaId = postMetaId;
		this.post = post;
		this.metaKey = metaKey;
		this.metaValue = metaValue;
	}
	public Long getPostMetaId() {
		return PostMetaId;
	}
	public void setPostMataId(Long postMataId) {
		PostMetaId = postMataId;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getMetaKey() {
		return metaKey;
	}
	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}
	public String getMetaValue() {
		return metaValue;
	}
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((PostMetaId == null) ? 0 : PostMetaId.hashCode());
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
		PostMeta other = (PostMeta) obj;
		if (PostMetaId == null) {
			if (other.PostMetaId != null)
				return false;
		} else if (!PostMetaId.equals(other.PostMetaId))
			return false;
		return true;
	}
	
	
	

}
