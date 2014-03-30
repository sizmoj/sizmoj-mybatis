package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sizmoj.sizmoj.modules.sys.entity.User;

/**
 * 评论Comment
 * @author sizmoj
 * @version 2013-12-11
 */
public class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2475909158185924117L;
	/**文章ID**/
	private Long id;
	/**文章作者**/
	private User author;
	/**发布时间**/
	private Date publicDate;
	/**文章内容**/
	private String content;
	/**文章标题**/
	private String postTitle;
	/**摘录**/
	private String excerpt;
	/**文章状态**/
	private String status;
	/**文章密码**/
	private String password;
	/**修改时间**/
	private Date postModified;
	/**上级文章**/
	private Post parentPost;
	/**排序ID**/
	private int menuOrder;
	/**留言总数**/
	private Long commentCount;
	/**所属分类**/
	private Term term;
	/**标签**/
	private List<Tag> tags = new ArrayList<Tag>();
	
	private List<Comment> comments = new ArrayList<Comment>();
	
	private String tagsString;
	
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public String getTagsString() {
		return tagsToString(getTags());
	}
	public String getTagsStringByOr() {
		return tagsString;
	}
	public void setTagsString(String tagsString) {
		this.tagsString = tagsString;
	}
	public Post() {
		super();
	}
	public Post(Long id, User author, Date publicDate, String content,
			String postTitle, String excerpt, String status, String password,
			Date postModified, Post parentPost, int menuOrder,
			Long commentCount, Term term, List<Tag> tags) {
		super();
		this.id = id;
		this.author = author;
		this.publicDate = publicDate;
		this.content = content;
		this.postTitle = postTitle;
		this.excerpt = excerpt;
		this.status = status;
		this.password = password;
		this.postModified = postModified;
		this.parentPost = parentPost;
		this.menuOrder = menuOrder;
		this.commentCount = commentCount;
		this.term = term;
		this.tags = tags;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getPostModified() {
		return postModified;
	}
	public void setPostModified(Date postModified) {
		this.postModified = postModified;
	}
	public Post getParentPost() {
		return parentPost;
	}
	public void setParentPost(Post parentPost) {
		this.parentPost = parentPost;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
		
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public String tagsToString(List<Tag> tags) {
		StringBuilder sb = new StringBuilder();
		if(tags.size() == 0) {
			return null;
		} else {
			for(Tag tag: tags) {
				sb.append(tag.getName() + ";");
			}
			return sb.toString();
		}
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 
	
	
}
