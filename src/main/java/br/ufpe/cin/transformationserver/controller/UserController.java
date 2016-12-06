package br.ufpe.cin.transformationserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufpe.cin.transformationserver.domain.User;
import br.ufpe.cin.transformationserver.service.AuditingService;
import br.ufpe.cin.transformationserver.service.UserService;
import br.ufpe.cin.transformationserver.validation.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private AuditingService auditingService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}

	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String formForCreate(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("mode", "create");
		return "user/form";
	}

	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Long id ) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("mode", "update");
		return "user/form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String processUserForm( Model model, @Valid @ModelAttribute("user")   User userToBeAdded, BindingResult result, RedirectAttributes redirectAttributes){
		if(userToBeAdded.getId() == 0L){
			model.addAttribute("mode", "create");
		}else{
			model.addAttribute("mode", "update");
		}
		try {
			if(result.hasErrors()) {
				model.addAttribute("warning", "true");
				return "user/form";
			}
			userToBeAdded = userService.save(userToBeAdded);
		} catch(Exception e) {
			model.addAttribute("exception", getCause(e).getLocalizedMessage());
			return "user/form";
		}
		redirectAttributes.addFlashAttribute("success", "User saved");
		return "redirect:/user/form/"+userToBeAdded.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/history/{id}")
	public String history(Model model, @PathVariable("id") Long id ) {
		List<Object[]> revisions = auditingService.findRevisions(User.class, id);
		model.addAttribute("revisions", revisions);
		return "user/history";
	}
	
	Throwable getCause(Throwable e) {
	    Throwable cause = null; 
	    Throwable result = e;

	    while(null != (cause = result.getCause())  && (result != cause) ) {
	        result = cause;
	    }
	    return result;
	}
}
