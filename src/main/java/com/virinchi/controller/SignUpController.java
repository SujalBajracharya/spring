package com.virinchi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.virinchi.model.UserData;
import com.virinchi.repository.UserRepository;


@Controller
public class SignUpController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/signup")
	public String getSignUp() {
		return "register";
	}
	
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute("userData") UserData userData,Model model) {	
		System.out.println("---------------------------------------");
		System.out.println(userData);
		System.out.println("---------------------------------------");
		userRepository.save(userData);
		return "redirect:/login";
	}
	
}
