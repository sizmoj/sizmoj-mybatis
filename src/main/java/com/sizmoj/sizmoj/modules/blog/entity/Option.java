package com.sizmoj.sizmoj.modules.blog.entity;

import java.io.Serializable;

/**
 * 参数配置 Option
 * @author biggernin
 * @version 2013-05-11
 */
public class Option implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3833182463178565283L;
	/**主键**/
	private Long optionId;
	/**BlogId**/
	private Long blogId;
	/**建名**/
	private String optionName;
	/**键值**/
	private String optionValue;
	/**是否自动载入**/
	private Boolean autoload;
	
	
	public Option() {
		super();
	}
	
	public Option(Long blogId, String optionName, String optionValue,
			Boolean autoload) {
		super();
		this.blogId = blogId;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.autoload = autoload;
	}

	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public Boolean getAutoload() {
		return autoload;
	}
	public void setAutoload(Boolean autoload) {
		this.autoload = autoload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autoload == null) ? 0 : autoload.hashCode());
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
		Option other = (Option) obj;
		if (autoload == null) {
			if (other.autoload != null)
				return false;
		} else if (!autoload.equals(other.autoload))
			return false;
		return true;
	}
	
}
