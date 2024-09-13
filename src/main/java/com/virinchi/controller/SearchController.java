package com.virinchi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.ShoeData;
import com.virinchi.repository.ShoeRepository;

@Controller
public class SearchController {

	@Autowired
	private ShoeRepository shoeRepository;

	@GetMapping("/searchShoes")
	public String searchShoes(@RequestParam("query") String query, Model model,
	                          @RequestParam(defaultValue = "0") int page) {
	    // Create a Pageable object with the page number and size
	    Pageable pageable = PageRequest.of(page, 3); // Fixed page size = 3

	    // Get the paginated list of ShoeData
	    Page<ShoeData> searchResults = shoeRepository
	            .findByShoeNameContainingIgnoreCaseOrBrandContainingIgnoreCase(query, query, pageable);

	    // Add attributes for pagination controls and data
	    model.addAttribute("allShoeData", searchResults.getContent()); // List of shoes on the current page
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", searchResults.getTotalPages());
	    model.addAttribute("totalItems", searchResults.getTotalElements());
	    model.addAttribute("searchResults", searchResults);
	    model.addAttribute("query", query); // Show what the user searched for
	    return "searchResult";  // Thymeleaf template to display results
	}

	@PostMapping("/searchByPrice")
	public String searchByPrice(@RequestParam("minPrice") int min, @RequestParam("maxPrice") int max, Model model) {

		int minPrice = min;
		int maxPrice = max;

		List<ShoeData> searchResults = shoeRepository.findShoesByPriceRange(minPrice, maxPrice);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(searchResults);
		model.addAttribute("searchResults", searchResults);

		return "searchResult";
	}

	@PostMapping("/searchByBrand")
	public String searchByBrand(@RequestParam("brand") String brand, Model m) {

		List<ShoeData> searchResults = shoeRepository.findByBrandContainingIgnoreCase(brand);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(searchResults);
		m.addAttribute("searchResults", searchResults);
		return "searchResult";
	}

	@PostMapping("/searchBySize")
	public String searchBySizes(@RequestParam("size") List<String> size, Model m) {

		List<ShoeData> searchResults = shoeRepository.findBySizesSizeIn(size);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(searchResults);
		m.addAttribute("searchResults", searchResults);
		return "searchResult";
	}

}
