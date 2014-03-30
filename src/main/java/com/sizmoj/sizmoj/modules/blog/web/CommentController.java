package com.sizmoj.sizmoj.modules.blog.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sizmoj.sizmoj.common.persistence.Page;
import com.sizmoj.sizmoj.common.web.BaseController;
import com.sizmoj.sizmoj.modules.blog.entity.Comment;
import com.sizmoj.sizmoj.modules.blog.service.BlogService;

@Controller
@RequestMapping(value = "/blog/comment")
public class CommentController extends BaseController{
	@Autowired
	private BlogService blogService;
	
	@ModelAttribute
	public Comment get(@RequestParam(required=false) Long id) {
		if (id != null){
			return blogService.getCommentById(id);
		}else{
			return new Comment();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(id == null) {
			Page<Comment> page = blogService.findCommentPageByDate(new Page<Comment>(request, response));
			model.addAttribute("page", page);
			return "modules/blog/commentListPage";
		} else {
			List<Comment> comments = blogService.findComment(id);
			model.addAttribute("comments", comments);
		}
		return "modules/blog/commentList";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
		blogService.deletePost(comment.getId());
		return "redirect:/blog/post/?repage";
	}
}
