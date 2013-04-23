package com.dmcliver.springvalidation;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dmcliver.springvalidation.dataaccess.CustomerDao;
import com.dmcliver.springvalidation.domain.Customer;
import com.dmcliver.springvalidation.services.UserDetailsImpl;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CustomerDao customerDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String webSiteHomeIndex(ModelMap model){
		return "menu";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model){
		return "login";
	}
	
	@RequestMapping(value = "/oops", method = RequestMethod.GET)
	public String loginFailed(ModelMap model){
		model.addAttribute("error", true);
		return "oops";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model){
		return "byebye";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Server started");
		
		model.addAttribute("customer", new Customer());
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		logger.info("Server started");
				
		if(result.hasErrors()){
			return "register";
		}	
		try{
			customerDao.save(customer);
		}
		catch(Exception ex){
			result.reject("gblErrMess",ex.getMessage());
			return "register";
		}
		model.addAttribute("userName",customer.getUserName());
		return "success";
	}
	
	@RequestMapping(value = "/success",method=RequestMethod.GET)
	public String success(Model model){
		UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userName",user.getUsername());
		return "success";
	}
}
