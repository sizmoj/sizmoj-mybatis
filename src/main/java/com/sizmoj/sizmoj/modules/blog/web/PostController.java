package com.sizmoj.sizmoj.modules.blog.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sizmoj.sizmoj.common.persistence.Page;
import com.sizmoj.sizmoj.common.web.BaseController;
import com.sizmoj.sizmoj.modules.blog.entity.Post;
import com.sizmoj.sizmoj.modules.blog.service.BlogService;
import com.sizmoj.sizmoj.modules.sys.utils.UserUtils;
/**
 *
 * @author 
 * @version
 */
@Controller
@RequestMapping(value = "/blog/post")
public class PostController extends BaseController{
	@Autowired
	private BlogService blogService;
	
	@ModelAttribute(value="post")
	public Post get(@RequestParam(required=false) Long id, @RequestParam(required=false) String tagsString) {
		if (id != null){
			return blogService.getPost(id);
		}else{
			return new Post();
		}
	}

	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Post> page = blogService.findPostPage(new Page<Post>(request, response), paramMap); 
        model.addAttribute("page", page);
        model.addAllAttributes(paramMap);
        model.addAttribute("terms", blogService.getAllTerm());
		return "modules/blog/postList";
	}
	
	@RequestMapping(value = "form")
	public String form(Post post, Model model) {
		model.addAttribute("post", post);
		model.addAttribute("terms", blogService.getAllTerm());
		return "modules/blog/postForm";
	}
	
	@RequestMapping(value = "save")
	public String save(Post post, String oldLoginName, RedirectAttributes redirectAttributes) {
		post.setAuthor(UserUtils.getUser());
		post.setPassword("nopassword");
		post.setExcerpt("noExcerpt");
		// 保存用户信息
		blogService.addPost(post);
		return "redirect:/blog/post/?repage";
	}
	@RequestMapping(value = "delete")
	public String delete(Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
		blogService.deletePost(id);
		return "redirect:/blog/post/?repage";
	}

}
