package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.sizmoj.sizmoj.modules.sys.entity.User;

/**
 * 友情链接 Link
 * @author sizmoj
 * @version 2013-05-11
 */
public class Link implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**主键**/
	private String linkID;
	/**链接URL**/
	private String url;
	/**链接标题**/
	private String name;
	/**链接图片**/
	private String image;
	/**链接打开方式**/
	private String target;
	/**链接描述**/
	private String description;
	/**是否可见**/
	private Boolean visible;
	/**添加者**/
	private User user;
	/**评分等级**/
	private Byte rating;
	/**更新时间**/
	private Date updateTime;
	/**XFN关系**/
	private String rel;
	/**XFN注释**/
	private String note;
	/**RSS链接**/
	private String rss;
	public Link() {
		super();
	}
	public String getLinkID() {
		return linkID;
	}
	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Byte getRating() {
		return rating;
	}
	public void setRating(Byte rating) {
		this.rating = rating;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRss() {
		return rss;
	}
	public void setRss(String rss) {
		this.rss = rss;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linkID == null) ? 0 : linkID.hashCode());
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
		Link other = (Link) obj;
		if (linkID == null) {
			if (other.linkID != null)
				return false;
		} else if (!linkID.equals(other.linkID))
			return false;
		return true;
	}	
}
