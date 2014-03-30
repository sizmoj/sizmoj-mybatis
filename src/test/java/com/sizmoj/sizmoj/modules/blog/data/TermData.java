package com.sizmoj.sizmoj.modules.blog.data;

import org.springside.modules.test.data.RandomData;

import com.sizmoj.sizmoj.modules.blog.entity.Term;

public class TermData {
	public static Term RandomTerm() {
		Term term = new Term();
		term.setName(randomName());
		term.setSlug(randomSlug());
		term.setIsPage("1");
		term.setIsShow("0");
		return term;
	}

	private static String randomSlug() {
		
		return RandomData.randomName("slug");
	}

	private static String randomName() {
		return RandomData.randomName("name");
	}
}
