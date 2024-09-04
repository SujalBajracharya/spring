package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.UserData;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String getIndex() {
		return "login";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String getLoginDetails(@ModelAttribute UserData u, Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
//			UserData udata=userRepository.findByUsernameAndPassword(u.getUsername(),u.getPassword());
//			if(udata!=null) {
//				m.addAttribute("userData", udata);
//				return "home";
//			}else {
//				return "home";	
//			}
		if(userRepository.existsByUsernameAndPassword(u.getUsername(), u.getPassword())) {
		List<UserData> uList = userRepository.findAll();
		m.addAttribute("allData", uList);
		session.setAttribute("user", u.getUsername());
		return "home";	
		}else {
			return"login";
		}
		}
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest req) {
		HttpSession session= req.getSession();
		session.invalidate();
		return "login";
	}

	@PostMapping("/delete")
	public String deleteData(@RequestParam("id") int id, Model m) {

		userRepository.deleteById(id);
		m.addAttribute("userData", userRepository.findAll());
		return "home";
	}
	
	@PostMapping("/testsignup")
	public String testlogin(@ModelAttribute UserData u) {
		return "sucess";
	}
	
	@GetMapping("/home")
	public String getMethodName(Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null) {
		return "login";
		}else {
			return "home";
		}
		
	}
	
}
