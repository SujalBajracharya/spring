package com.virinchi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.UserData;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class PasswordController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/changePassword")
    public String showPasswordChangeForm(Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		String userEmail = (String) session.getAttribute("email");
		System.out.println("########################");
		System.out.println(session.getAttribute("userId"));
		System.out.println(userId);
		System.out.println(userEmail);
		System.out.println(session.getAttribute("user"));
		System.out.println(session.getAttribute("pass"));
		System.out.println(session.getAttribute("email"));
		System.out.println("########################");
		Optional<UserData> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            m.addAttribute("userDetail", userData.get());
            return "changePassword"; // Return the HTML form for changing the password
        } else {
            return "home"; // Handle case where user is not found
        }
    }

    // Process the password change form submission
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("username") String username, @RequestParam("newPassword") String newPassword) {
        Optional<UserData> userDataOpt = userRepository.findByUsername(username);
        if (userDataOpt.isPresent()) {
            UserData userData = userDataOpt.get();
            userData.setPassword(newPassword); // Set the new password
            userRepository.save(userData); // Save the updated user data
            return "success"; // Redirect to success page
        } else {
            return "error"; // Handle case where user is not found
        }
    }
}
