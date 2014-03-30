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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sizmoj.sizmoj.common.web.BaseController;
import com.sizmoj.sizmoj.modules.blog.entity.Post;
import com.sizmoj.sizmoj.modules.blog.entity.Term;
import com.sizmoj.sizmoj.modules.blog.service.BlogService;
import com.sizmoj.sizmoj.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "/blog/term")
public class TermController  extends BaseController{

	@Autowired
	private BlogService blogService;
	
	@ModelAttribute(value="term")
	public Term get(@RequestParam(required=false) Long id, @RequestParam(required=false) String tagsString) {
		if (id != null){
			return blogService.getTerm(id);
		}else{
			return new Term();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Term> terms = blogService.getAllTerm();
        model.addAttribute("terms", terms);
		return "modules/blog/termList";
	}
	
	@RequestMapping(value = "form")
	public String form(Term term, Model model) {
		model.addAttribute("term", term);
		return "modules/blog/termForm";
	}
	
	@RequestMapping(value = "save")
	public String save(Term term, RedirectAttributes redirectAttributes) {
		blogService.addTerm(term);
		return "redirect:/blog/term/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Term term, RedirectAttributes redirectAttributes) {
		blogService.deleteTerm(term.getId());
		return "redirect:/blog/term/?repage";
	}
}
