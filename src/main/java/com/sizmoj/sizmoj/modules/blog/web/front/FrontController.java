package com.sizmoj.sizmoj.modules.blog.web.front;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sizmoj.sizmoj.common.markdowm.MarkdownProcessor;
import com.sizmoj.sizmoj.common.persistence.FrontPage;
import com.sizmoj.sizmoj.modules.blog.entity.Comment;
import com.sizmoj.sizmoj.modules.blog.entity.Post;
import com.sizmoj.sizmoj.modules.blog.service.BlogService;

/** 
 * FreeMarker 前端控制器 
 * @author
 * @date 
 */  
@Controller  
@RequestMapping("/f")  
public class FrontController {  
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
	
    @RequestMapping("/index")  
    public String index(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, Model model) {  
    	 FrontPage<Post> page = blogService.findPostPage(new FrontPage<Post>(request, response), paramMap);
    	 model.addAttribute("page", page);
    	 model.addAttribute("terms",blogService.getAllTerm());
    	 model.addAttribute("newPost",blogService.getNewsPost());
    	 model.addAttribute("newComment",blogService.getNewComments());
    	 model.addAttribute("pageurl", "f/index");
        return "main"; 
    }  
      
    @RequestMapping("/detail/{postId}")  
    public String detail(@PathVariable Long postId,HttpServletRequest request, HttpServletResponse response, Model model) {  
    	Post post = blogService.getPost(postId);
    	MarkdownProcessor markup = new MarkdownProcessor();
    	post.setContent(markup.markdown(post.getContent()));
    	for(Comment comment : post.getComments()) {
    		comment.setText(markup.markdown(comment.getText()));
    	}
    	model.addAttribute("terms",blogService.getAllTerm());
    	model.addAttribute("newPost",blogService.getNewsPost());
    	model.addAttribute("newComment",blogService.getNewComments());
    	model.addAttribute("post", post);
        return "postdetail";  
    }  
    
    @RequestMapping("/tag/{tagId}")  
    public String tagPage(@PathVariable Long tagId,HttpServletRequest request, HttpServletResponse response, Model model) {  
    	FrontPage<Post> page = blogService.getPostByTag(new FrontPage<Post>(request, response), tagId);
    	model.addAttribute("page", page);
    	model.addAttribute("terms",blogService.getAllTerm());
    	model.addAttribute("newPost",blogService.getNewsPost());
    	model.addAttribute("newComment",blogService.getNewComments());
    	model.addAttribute("pageurl", "f/tag/"+tagId);
        return "main";  
    }
    
    @RequestMapping("/term/{termId}")  
    public String termPage(@PathVariable Long termId,HttpServletRequest request, HttpServletResponse response, Model model) {  
    	FrontPage<Post> page = blogService.getPostByTerm(new FrontPage<Post>(request, response), termId);
    	model.addAttribute("page", page);
    	model.addAttribute("terms",blogService.getAllTerm());
    	model.addAttribute("newPost",blogService.getNewsPost());
    	model.addAttribute("newComment",blogService.getNewComments());
    	model.addAttribute("pageurl", "f/term/"+termId);
        return "main";  
    }
    
    @RequestMapping("/addComment")  
    public String addComment(Long postId, String text, String author, String email, String url, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {  
    	Comment comment = new Comment();
    	if(StringUtils.isNotBlank(text))
    		comment.setText(text);
    	if(StringUtils.isNotBlank(author))
    		comment.setAuthor(author);
    	if(StringUtils.isNotBlank(email))
    		comment.setEmail(email);
    	if(StringUtils.isNotBlank(url))
    		comment.setUrl(url);
    	Post post = new Post();
    	post.setId(postId);
    	comment.setPost(post);
    	comment.setDateTime(new Date());
    	blogService.addComment(comment);
    	model.addAttribute("terms",blogService.getAllTerm());
    	model.addAttribute("newPost",blogService.getNewsPost());
    	model.addAttribute("newComment",blogService.getNewComments());
        return "redirect:detail/"+postId+"?repage";
    }
    
    @RequestMapping("/tagPage")
    public String tagPage(HttpServletRequest request, HttpServletResponse response, Model model) {
    	model.addAttribute("tags", blogService.getAllTag());
    	model.addAttribute("terms",blogService.getAllTerm());
    	model.addAttribute("newPost",blogService.getNewsPost());
    	model.addAttribute("newComment",blogService.getNewComments());
   	 	return "tagIndex";
    }
    @RequestMapping("postmd/{postId}")
    public String postmd(@PathVariable Long postId,HttpServletRequest request, HttpServletResponse response, Model model) {
    	Post post = blogService.getPost(postId);
    	model.addAttribute("post", post);
   	 	return "postmd";
    }
}  