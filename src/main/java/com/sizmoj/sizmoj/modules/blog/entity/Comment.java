package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.sizmoj.sizmoj.modules.sys.entity.User;


/**
 * 评论Comment
 * @author sizmoj
 * @version 2013-12-11
 */
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5649344663390643625L;
	/**评论ID**/
	private Long id;
	/**文章**/
	private Post post;
	/**作者**/
	private String author;
	/**评论者邮箱**/
	private String email;
	/**评论者主页**/
	private String url;
	/**评论者IP**/
	private String ip;
	/**评论时间**/
	private Date dateTime;
	/**评论者内容**/
	private String text;
	/**是否被允许**/
	private String approved;
	/**评论客户端信息**/
	private String agent;
	/**上一条评论**/
	private Comment parentComment;
	
	
	
	public Comment() {
		super();
	}
	public Comment(Long id, Post post, String author, String email,
			String url, String ip, Date dateTime, String text, String approved,
			String agent, Comment parentComment, User user) {
		super();
		this.id = id;
		this.post = post;
		this.author = author;
		this.email = email;
		this.url = url;
		this.ip = ip;
		this.dateTime = dateTime;
		this.text = text;
		this.approved = approved;
		this.agent = agent;
		this.parentComment = parentComment;

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public Comment getParentComment() {
		return parentComment;
	}
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	
}
