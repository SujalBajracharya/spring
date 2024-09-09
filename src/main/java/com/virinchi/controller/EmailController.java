package com.virinchi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class EmailController {
	
	@Autowired
	private JavaMailSender jms;

	@GetMapping("/contact")
	public String getContact(HttpServletRequest req) {
		HttpSession session= req.getSession();
		if(session.getAttribute("user")==null) {
			return "redirect:/login";
		}else {
			return "contact";
		}
	}
	
	@PostMapping("/contact")
	public String sendMail(HttpServletRequest req) {
		String to= req.getParameter("to");
		String subject= req.getParameter("subject");
		String message= req.getParameter("message");
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		
		System.out.println("###################################");
		System.out.println(simpleMailMessage);
		
		jms.send(simpleMailMessage);
		
		return "redirect:/contact";
	}
	
}

	
