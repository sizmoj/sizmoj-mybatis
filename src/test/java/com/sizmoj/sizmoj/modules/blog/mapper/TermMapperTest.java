package com.sizmoj.sizmoj.modules.blog.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.blog.data.TermData;
import com.sizmoj.sizmoj.modules.blog.entity.Term;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class TermMapperTest extends SpringTransactionalTestCase{
	
	@Autowired
	private TermMapper termMapper;
	
	//@Test
	public void testAdd() {
		int count = countRowsInTable("blog_term");
		termMapper.add(TermData.RandomTerm());
		assertEquals(count + 1, countRowsInTable("blog_term"));
	}

	//@Test
	public void testGet() {
		Term term = TermData.RandomTerm();
		termMapper.add(term);
		Term term1 = termMapper.get(term.getId());
		assertEquals(term1.getName(), term.getName());
	}
	
	//@Test
	public void testDelete() {
		Term term = TermData.RandomTerm();
		termMapper.add(term);
		int count = countRowsInTable("blog_term");
		termMapper.delete(term.getId());
		assertEquals(count - 1, countRowsInTable("blog_term"));
	}

	//@Test
	public void testUpdate() {
		Term term = TermData.RandomTerm();
		termMapper.add(term);
		Term termup = TermData.RandomTerm();
		termup.setId(term.getId());
		termMapper.update(termup);
		assertEquals(1, countRowsInTableWhere("blog_term", "name='" +termup.getName() + "'"));
	}

	//@Test
	public void testFindAll() {
		Term term = TermData.RandomTerm();
		termMapper.add(term);
		Term term2 = TermData.RandomTerm();
		termMapper.add(term2);
		int count = countRowsInTable("blog_term");
		List<Term> terms = termMapper.findAll();
		assertEquals(count, terms.size());
	}

	//@Test
	public void testCount() {
		Term term = TermData.RandomTerm();
		termMapper.add(term);
		Term term2 = TermData.RandomTerm();
		termMapper.add(term2);
		int count = countRowsInTable("blog_term");
		long terms = termMapper.count();
		assertEquals(count, terms);
	}

}
