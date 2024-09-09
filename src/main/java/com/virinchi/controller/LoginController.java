package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.virinchi.model.ShoeData;
import com.virinchi.model.UserData;
import com.virinchi.repository.ShoeRepository;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShoeRepository shoeRepository;

	@GetMapping("/")
	public String getIndex() {
		return "redirect:/home";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String getLoginDetails(@ModelAttribute UserData u, Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
//			UserData udata =userRepository.findByUsernameAndPassword(u.getUsername(),u.getPassword());
//			if(udata!=null) {
//				m.addAttribute("userData", udata);
//				return "home";
//			}else {
//				return "home";	
//			}
		
		if (userRepository.existsByUsernameAndPassword(u.getUsername(), u.getPassword())) {
//		List<UserData> uList = userRepository.findAll();
//		m.addAttribute("allData", uList);

			session.setAttribute("user", u.getUsername());
			session.setAttribute("id", u.getId());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("pass", u.getPassword());
			if ("admin".equals(session.getAttribute("user"))) {
				return "addShoe";
			} else if (session.getAttribute("user") == null) {
				return "login";
			} else {
				return "redirect:/home";
			}
		} else {
			return "login";
		}
	}

	@GetMapping("/logout")
	public String getLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "login";
	}

	@PostMapping("/testsignup")
	public String testlogin(@ModelAttribute UserData u) {
		return "sucess";
	}

	@GetMapping("/home")
	public String getMethodName(@ModelAttribute ShoeData s, Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if ("admin".equals(session.getAttribute("user"))) {
			return "addShoe";
		} else if (session.getAttribute("user") == null) {
			return "login";
		} else {
			List<ShoeData> sList = shoeRepository.findAll();
			m.addAttribute("allShoeData", sList);
			return "home";
		}
	}

}
