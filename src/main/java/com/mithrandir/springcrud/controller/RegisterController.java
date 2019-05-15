package com.mithrandir.springcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mithrandir.springcrud.entity.User;
import com.mithrandir.springcrud.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		
		User user = new User();
		
		model.addAttribute("user", user);
		return "register";
	}
	
	
	@PostMapping("/processRegistration")
	public String processRegistration(@ModelAttribute("user") User user, Model model) {
		
		userService.createUser(user);
		
		return "/login";
	}
}
