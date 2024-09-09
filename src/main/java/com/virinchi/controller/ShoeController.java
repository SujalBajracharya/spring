package com.virinchi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.virinchi.model.ShoeColor;
import com.virinchi.model.ShoeData;
import com.virinchi.model.ShoeSize;
import com.virinchi.repository.ShoeRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ShoeController {

	@Autowired
	private ShoeRepository shoeRepository;

	@GetMapping("/admin")
	public String getAddShoe(HttpServletRequest req) {
		HttpSession session = req.getSession();

		if ("admin".equals(session.getAttribute("user")) ) {
			return "addShoe";
		} else {
			return "login";
		}
	}

	@PostMapping("/admin")
	public String addShoe(@ModelAttribute ShoeData shoeData, @RequestParam("size") List<String> sizes,
			@RequestParam("color") List<String> colors, @RequestParam("file") MultipartFile file) {
		List<ShoeSize> sizeEntities = new ArrayList<>();
		for (String size : sizes) {
			ShoeSize shoeSize = new ShoeSize();
			shoeSize.setSize(size);
			shoeSize.setShoeData(shoeData);
			sizeEntities.add(shoeSize);
		}

		List<ShoeColor> colorEntities = new ArrayList<>();
		for (String color : colors) {
			ShoeColor shoeColor = new ShoeColor();
			shoeColor.setColor(color);
			shoeColor.setShoeData(shoeData);
			colorEntities.add(shoeColor);
		}

		byte[] imgByte;
		try {
			imgByte = file.getBytes();
			String imgString = Base64.getEncoder().encodeToString(imgByte);
			shoeData.setImage(imgString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		shoeData.setSizes(sizeEntities);
		shoeData.setColors(colorEntities);
		shoeRepository.save(shoeData);
//	    return "addShoe";
		return "redirect:/admin"; // Redirect to a list or confirmation page
	}

	@GetMapping("/inventory")
	public String getInventory(@ModelAttribute ShoeData s,Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("user").equals("admin")) {
			List<ShoeData> sList = shoeRepository.findAll();
			m.addAttribute("shoeData", sList);
			return "inventory";
		} else {
			return "login";
		}
	}
	
	@PostMapping("/deleteShoe")
	public String deleteShoeData(@RequestParam("id") int id, Model m) {

		shoeRepository.deleteById(id);
		m.addAttribute("shoeData", shoeRepository.findAll());
		return "inventory";
	}
	
	@GetMapping("/updateShoe")
	public String showShoeData(@RequestParam("id") int id, Model m) {
		Optional<ShoeData> shoeDataOptional = shoeRepository.findById(id);
	    // Check if the ShoeData is present
	    if (shoeDataOptional.isPresent()) {
	        // Pass the ShoeData to the model
	        m.addAttribute("ShoeDetail", shoeDataOptional.get());
	    } else {
	        // Handle the case where the shoe data is not found (you could redirect to an error page or display a message)
	        return "inventory"; // or you can return some other view
	    }
		return "updateShoe";
	}
	
	
	@PostMapping("/updateShoe")
	public String updateShoeData(@RequestParam("id") int id, Model m) {
		return "inventory";
	}
	
}
