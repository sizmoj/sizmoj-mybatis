package com.sizmoj.sizmoj.modules.blog.data;

import org.springside.modules.test.data.RandomData;

import com.sizmoj.sizmoj.modules.blog.entity.Tag;

public class TagData {
	public static Tag RandomTag() {
		Tag tag = new Tag();
		tag.setName(RandomName());
		return tag;
	}

	private static String RandomName() {
		return RandomData.randomName("tag");
	}
}
