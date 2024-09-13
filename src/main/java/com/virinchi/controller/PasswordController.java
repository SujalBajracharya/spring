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
import org.springframework.transaction.annotation.Transactional;

@Controller
public class PasswordController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/changePassword")
    public String showPasswordChangeForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        Integer userId = (Integer) session.getAttribute("id");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(userId);
        if (userId == null) {
            return "redirect:/login"; // Redirect to login if userId is not present
        }

        Optional<UserData> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            model.addAttribute("userDetail", userData.get());
            return "changePassword"; // Return the HTML form for changing the password
        } else {
            return "redirect:/home"; // Handle case where user is not found
        }
    }

	
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("id") int id,
                                  @RequestParam("currentPassword") String currentPassword,
                                  @RequestParam("newPassword") String newPassword) {
        
        // Fetch user data based on ID
        Optional<UserData> optionalUser = userRepository.findById(id);
        
        // Check if user exists
        if (optionalUser.isPresent()) {
            UserData user = optionalUser.get();
            
            // Check if the current password matches the existing password
            if (user.getPassword().equals(currentPassword)) {
                // Update the password (In a real application, hash the newPassword)
                user.setPassword(newPassword);
                
                // Save the updated user data
                userRepository.save(user);
                
                return "redirect:/home"; // Redirect to a success page or display a success message
            } else {
                // Current password does not match
                return "redirect:/changePassword?error=invalid"; // Redirect to an error page with a message
            }
        } else {
            // User not found
            return "redirect:/changePassword?error=notfound"; // Redirect to an error page with a message
        }
    }


}
