package com.sizmoj.sizmoj.modules.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.nosql.redis.JedisTemplate;

import com.sizmoj.sizmoj.common.entity.RedisObjectType;
import com.sizmoj.sizmoj.common.persistence.FrontPage;
import com.sizmoj.sizmoj.common.persistence.Page;
import com.sizmoj.sizmoj.common.persistence.RedisManager;
import com.sizmoj.sizmoj.common.utils.StringUtils;
import com.sizmoj.sizmoj.modules.blog.entity.Comment;
import com.sizmoj.sizmoj.modules.blog.entity.Post;
import com.sizmoj.sizmoj.modules.blog.entity.Tag;
import com.sizmoj.sizmoj.modules.blog.entity.Term;
import com.sizmoj.sizmoj.modules.blog.mapper.CommentMapper;
import com.sizmoj.sizmoj.modules.blog.mapper.PostMapper;
import com.sizmoj.sizmoj.modules.blog.mapper.TagMapper;
import com.sizmoj.sizmoj.modules.blog.mapper.TermMapper;
import com.sizmoj.sizmoj.modules.sys.mapper.UserMapper;

@Service
@Transactional(readOnly = true)
public class BlogService {
	private CommentMapper commentMapper;
	private PostMapper postMapper;
	private TagMapper tagMapper;
	private TermMapper termMapper;
	private UserMapper userMapper;
	private JedisTemplate jedisTemplate = RedisManager.getJedisTemplate();
	private JsonMapper jsonMapper = new JsonMapper();

	@Transactional(readOnly = false)
	public void addPost(Post post) {
		if(post.getId() == null || post.getId() == 0L) {
			post.setPostModified(new Date());
			post.setPublicDate(new Date());
			Post parentPost = new Post();
			parentPost.setId(1L);
			post.setParentPost(parentPost);
			post.setCommentCount(0L);
			postMapper.add(post);
		} else {
			Post posttemp = postMapper.get(post.getId());
			post.setPostModified(new Date());
			post.setPublicDate(posttemp.getPublicDate());
			post.setParentPost(posttemp.getParentPost());
			if(posttemp.getCommentCount() == null) {
				post.setCommentCount(0L);
			} else {
				post.setCommentCount(posttemp.getCommentCount());
			}
			postMapper.update(post);
		}
		postMapper.deletePostTagByPostId(post.getId());		
		String[] tagsString = post.getTagsStringByOr().split(";");
		//排除字符串结尾为null的情况
		for(int i = 0; i < tagsString.length; i++ ) {
			if(StringUtils.isBlank(tagsString[i])) {
				continue;
			} else {
				List<Tag> tags = tagMapper.findByCondition(tagsString[i]);
				if(tags.size() > 0) {
					postMapper.addPostTag(post.getId(), tags.get(0).getId());
				} else {
					Tag tag = new Tag();
					tag.setName(tagsString[i]);
					tagMapper.add(tag);
					postMapper.addPostTag(post.getId(), tag.getId());
				}
			}
			
		}
		updatePostToRedis(post.getId());
	}
	
	@Transactional(readOnly = false)
	public void deletePost(long postId) {
		postMapper.delete(postId);
		commentMapper.deleteCommentByPostId(postId);
		postMapper.deletePostTagByPostId(postId);
		updatePostToRedis(postId);
	}
	
	@Transactional(readOnly = false)
	public Post getPost(final long postId) {		
		Post post = jsonMapper.fromJson(jedisTemplate.get(RedisObjectType.POST.getPrefix() + postId), Post.class);
		if(post == null) {
			post = postMapper.get(postId);
			if(post != null) {
				post.setTags(tagMapper.findByPostId(postId));
				post.setComments(commentMapper.findByCondition(postId, null, null, 0, 10000));
				post.setTerm(termMapper.get(post.getTerm().getId()));
				post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
				getAllPostToRedis();
			}
		}
		return post;
	}
	
	@Transactional(readOnly = false)
	public List<Post> getNewsPost() {		
		return postMapper.getNewsPost();
	}
	
	@Transactional(readOnly = false)
	public FrontPage<Post> getPostByTag(FrontPage<Post> page,long tagId) {
		page.setList(postMapper.findByCondition(tagId, null, null, null, null, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize()));
		for(Post post : page.getList()) {
			post.setTags(tagMapper.findByPostId(post.getId()));
			post.setTerm(termMapper.get(post.getTerm().getId()));
			post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
		}
		page.setCount(postMapper.countByCondition(tagId, null, null, null, null));
		return page;
	}
	
	@Transactional(readOnly = false)
	public FrontPage<Post> getPostByTerm(FrontPage<Post> page,long termId) {
		page.setList(postMapper.findByConditionFront(null, null, null, null, termId, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize()));
		for(Post post : page.getList()) {
			post.setTags(tagMapper.findByPostId(post.getId()));
			post.setTerm(termMapper.get(post.getTerm().getId()));
			post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
		}
		page.setCount(postMapper.countByConditionFront(null, null, null, null, termId));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void updatePost(Post post) {
		postMapper.update(post);
		if(post.getTags() != null) {
			List<Tag> tags = tagMapper.findByCondition(post.getTags().get(0).getName());
			if(tags.size() > 0) {
				postMapper.deletePostTagByPostId(post.getId());
				postMapper.addPostTag(post.getId(), tags.get(0).getId());
			} else {
				tagMapper.add(post.getTags().get(0));
				postMapper.addPostTag(post.getId(), tags.get(0).getId());
			}
		}
		updatePostToRedis(post.getId());
	}
	
	@Transactional(readOnly = false)
	public Page<Post> findPostPage(Page<Post> page, Map<String, Object> paramMap) {
		Long termId = StringUtils.toLong(paramMap.get("termId")) == 
				0L ? null : StringUtils.toLong(paramMap.get("termId"));
		String postTitle = ObjectUtils.toString(paramMap.get("postTitle"));
		if(StringUtils.isBlank(postTitle)) {
			postTitle = null;
		} else {
			postTitle = "%" +postTitle+ "%";
		}
		page.setList(postMapper.findByCondition(null, postTitle, null, null, termId, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize()));
		for(Post post : page.getList()) {
			post.setTags(tagMapper.findByPostId(post.getId()));
			post.setTerm(termMapper.get(post.getTerm().getId()));
			post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
		}
		page.setCount(postMapper.countByCondition(null, postTitle, null, null, termId));
		return page;
	}
	
	public FrontPage<Post> findPostPage(FrontPage<Post> page, Map<String, Object> paramMap) {
		Long termId = StringUtils.toLong(paramMap.get("termId")) == 
				0L ? null : StringUtils.toLong(paramMap.get("termId"));
		String postTitle = ObjectUtils.toString(paramMap.get("postTitle"));
		if(StringUtils.isBlank(postTitle)) {
			postTitle = null;
		} else {
			postTitle = "%" +postTitle+ "%";
		}
		page.setList(postMapper.findByConditionFront(null, postTitle, null, null, termId, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize()));
		for(Post post : page.getList()) {
			post.setTags(tagMapper.findByPostId(post.getId()));
			post.setTerm(termMapper.get(post.getTerm().getId()));
			post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
		}
		page.setCount(postMapper.countByConditionFront(null, postTitle, null, null, termId));
		return page;
	}
	
	public List<Comment> findComment(long postId) {		
		return commentMapper.findByCondition(postId, null, null, 0, 100000);
	}
	
	public List<Comment> getNewComments() {		
		List<Comment> comments = commentMapper.findByCondition(null, null, null, 0, 5);
		for(Comment comment : comments) {
			comment.setPost(postMapper.get(comment.getPost().getId()));
		}
		return comments;
	}
	
	public void addComment(Comment comment) {
		Post post = postMapper.get(comment.getPost().getId());
		if(post.getCommentCount() == null) {
			post.setCommentCount(0L);
		}
		post.setCommentCount(1L);
		postMapper.update(post);
		commentMapper.add(comment);
		updatePostToRedis(comment.getPost().getId());
	}
	
	public Comment getCommentById(long commentId) {	
		return commentMapper.get(commentId);
	}
	
	public void addTerm(Term term) {
		if(term.getId() == null || term.getId() == 0L){
			term.setIsShow("0");
			term.setIsPage("0");
			termMapper.add(term);
		} else {
			termMapper.update(term);			
		}
	}
	
	public List<Term> getAllTerm() {
		List<Term> terms = termMapper.findAll();
		for(Term term : terms) {
			term.setPostCount(postMapper.countByCondition(null, null, null, null, term.getId()));
		}
		return terms;
	}
	
	@Transactional(readOnly = false)
	public void deleteTerm(long id) {
		termMapper.delete(id);
	}
	
	public Page<Comment> findCommentPageByDate(Page<Comment> page) {
		page.setList(commentMapper.findByCondition(null, null, null, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize()));
		page.setCount(commentMapper.countByCondition(null, null, null));
		return page;
	}
	
	public void deleteComment(long commentId) {
		Comment comment = commentMapper.get(commentId);
		commentMapper.delete(commentId);
		Post post = postMapper.get(comment.getPost().getId());
		post.setCommentCount(post.getCommentCount() - 1);
		postMapper.update(post);
		updatePostToRedis(post.getId());
	}
	
	public List<Tag> getAllTag() {
		List<Tag> tags = tagMapper.findAll();
		for(Tag tag : tags) {
			tag.setPost(postMapper.findByCondition(tag.getId(), null, null, null, null, 0, 100000));
		}
		return tags;
	}
	
	private void getAllPostToRedis() {
		jedisTemplate.flushDB();
		List<Post> posts = postMapper.findByConditionFront(null, null, null, null, null, 0, 100000);
		for(Post post : posts) {
			post.setTags(tagMapper.findByPostId(post.getId()));
			post.setTerm(termMapper.get(post.getTerm().getId()));
			post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
			post.setComments(commentMapper.findByCondition(post.getId(), null, null, 0, 10000));
			jedisTemplate.setex(RedisObjectType.POST.getPrefix() + post.getId(), jsonMapper.toJson(post), RedisObjectType.POST.getExpiredTime());
		}
	}
	
	private void updatePostToRedis(Long postId) {
		Post post = postMapper.get(postId);
		jedisTemplate.del(RedisObjectType.POST.getPrefix() + postId);
		if(post != null) {
			post.setTags(tagMapper.findByPostId(post.getId()));
			post.setTerm(termMapper.get(post.getTerm().getId()));
			post.setAuthor(userMapper.findUserById(post.getAuthor().getId()));
			post.setComments(commentMapper.findByCondition(postId, null, null, 0, 10000));
			jedisTemplate.setex(RedisObjectType.POST.getPrefix() + post.getId(), 
					jsonMapper.toJson(post), RedisObjectType.POST.getExpiredTime());
		}
	}
	
	@Transactional(readOnly = false)
	public Term getTerm(long termId) {
		Term term = termMapper.get(termId);
		return term;
	}
	@Autowired
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	@Autowired
	public void setPostMapper(PostMapper postMapper) {
		this.postMapper = postMapper;
	}
	@Autowired
	public void setTagMapper(TagMapper tagMapper) {
		this.tagMapper = tagMapper;
	}
	@Autowired
	public void setTermMapper(TermMapper termMapper) {
		this.termMapper = termMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	
}
