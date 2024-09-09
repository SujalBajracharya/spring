package com.virinchi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.ShoeData;
import com.virinchi.repository.ShoeRepository;




@Controller
public class productController {

	@Autowired
	ShoeRepository shoeRepository;
	
	@PostMapping("/productDetail")
	public String getProduct(@RequestParam("id") int id, Model m){
		System.out.println(id);
		Optional<ShoeData> shoeDataOptional = shoeRepository.findById(id);
	    // Check if the ShoeData is present
	    if (shoeDataOptional.isPresent()) {
	        // Pass the ShoeData to the model
	        m.addAttribute("ShoeDetail", shoeDataOptional.get());
	        return "productDetail";
	    } else {
	        // Handle the case where the shoe data is not found (you could redirect to an error page or display a message)
	        return "redirect:/home"; // or you can return some other view
	    }
	}
	
}
