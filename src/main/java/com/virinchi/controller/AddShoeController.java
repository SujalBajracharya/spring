package com.virinchi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.ShoeData;
import com.virinchi.model.ShoeSize;
import com.virinchi.repository.ShoeRepository;


@Controller
public class AddShoeController {

	@Autowired
	private ShoeRepository shoeRepository;
	
	@GetMapping("/admin")
	public String getAddShoe() {
		return "addShoe";
	}
	
	@PostMapping("/admin")
	public String addShoe(@ModelAttribute ShoeData shoeData, @RequestParam("size") List<String> sizes) {
	    List<ShoeSize> sizeEntities = new ArrayList<>();
	    for (String size : sizes) {
	        ShoeSize shoeSize = new ShoeSize();
	        shoeSize.setSize(size);
	        shoeSize.setSize(size);
	        shoeSize.setShoeData(shoeData);
	        sizeEntities.add(shoeSize);
	    }
	    shoeData.setSizes(sizeEntities);
	    shoeRepository.save(shoeData);
	    return "redirect:/admin";  // Redirect to a list or confirmation page
	}
}

	
