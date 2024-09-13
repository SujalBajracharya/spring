package com.virinchi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.ShoeData;
import com.virinchi.model.UserData;
import com.virinchi.repository.ShoeRepository;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


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

		
		if (userRepository.existsByUsernameAndPassword(u.getUsername(), u.getPassword())) {

			UserData user = userRepository.findByUsername(u.getUsername());

			session.setAttribute("user", u.getUsername());
			session.setAttribute("id", user.getId());
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
	public String getMethodName(@ModelAttribute ShoeData s, Model m, HttpServletRequest req,@RequestParam(defaultValue = "0") int page ) {
		HttpSession session = req.getSession();
		if ("admin".equals(session.getAttribute("user"))) {
			return "addShoe";
		} else if (session.getAttribute("user") == null) {
			return "login";
		} else {
			
			
			// Create a Pageable object with the page number and size
			Pageable pageable = PageRequest.of(page, 8);  // Fixed page size = 8
	        
	        // Get the paginated list of ShoeData
	        Page<ShoeData> shoePage = shoeRepository.findAll(pageable);
	        
	        // Add attributes for pagination controls and data
	        m.addAttribute("allShoeData", shoePage.getContent());  // List of shoes on the current page
	        m.addAttribute("currentPage", page);
	        m.addAttribute("totalPages", shoePage.getTotalPages());
	        m.addAttribute("totalItems", shoePage.getTotalElements());
			return "home";
		}
	}
}
