package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;

/**
 * 评论源数据 CommentMeta
 * @author sizmoj
 * @version 2013-12-11
 */
public class CommentMeta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1242539363488274484L;
	/**主键**/
	private Long CommentMetaId;
	/**评论**/
	private Comment comment;
	/**键名**/
	private String metaKey;
	/**键值**/
	private String metaValue;
		
	public CommentMeta() {
		super();
	}
	public CommentMeta(Comment comment, String metaKey, String metaValue) {
		super();
		this.comment = comment;
		this.metaKey = metaKey;
		this.metaValue = metaValue;
	}
	public Long getCommentMetaId() {
		return CommentMetaId;
	}
	public void setCommentMetaId(Long commentMetaId) {
		CommentMetaId = commentMetaId;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
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
				+ ((CommentMetaId == null) ? 0 : CommentMetaId.hashCode());
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
		CommentMeta other = (CommentMeta) obj;
		if (CommentMetaId == null) {
			if (other.CommentMetaId != null)
				return false;
		} else if (!CommentMetaId.equals(other.CommentMetaId))
			return false;
		return true;
	}
	
	
	
}
