package com.virinchi.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.Cart;
import com.virinchi.model.ShoeData;
import com.virinchi.model.UserData;
import com.virinchi.repository.CartRepository;
import com.virinchi.repository.ShoeRepository;
import com.virinchi.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class CartController {

	@Autowired
	ShoeRepository shoeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;

	
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("id") int shoeId, @RequestParam("quantityCopy") int quantity,
	        HttpServletRequest req, Model model) {
	    
	    HttpSession session = req.getSession();
	    String username = (String) session.getAttribute("user");
	    
	    // Fetch the logged-in user
	    UserData user = userRepository.findByUsername(username);
	    
	    if (user == null) {
	        // Handle case where user is not found (maybe redirect to login or show an error)
	        return "redirect:/login"; // Adjust as needed
	    }

	    // Fetch the shoe data
	    Optional<ShoeData> shoeDataOptional = shoeRepository.findById(shoeId);
	    
	    if (!shoeDataOptional.isPresent()) {
	        // Handle case where shoe is not found
	        return "redirect:/productDetail"; // Adjust as needed
	    }
	    
	    ShoeData shoe = shoeDataOptional.get();
	    
	    // Check if the item is already in the cart
	    Cart existingCartItem = cartRepository.findByUserAndShoe(user, shoe);
	    
	    if (existingCartItem != null) {
	        // Update quantity if it already exists
	        existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
	        cartRepository.save(existingCartItem);
	    } else {
	        // Add a new cart item
	        Cart cartItem = new Cart();
	        cartItem.setUser(user);
	        cartItem.setShoe(shoe);
	        cartItem.setQuantity(quantity);
	        cartRepository.save(cartItem);
	    }

	    // Fetch updated cart
	    List<Cart> cartItems = cartRepository.findByUser(user);
	    model.addAttribute("cartItems", cartItems);
	    model.addAttribute("totalItems", cartItems.size());

	    return "cart"; // Redirect to the cart page
	}

	
	
//	@PostMapping("/addToCart")
//	public String addToCart(@RequestParam("id") int shoeId, @RequestParam("quantityCopy") int quantity,
//			HttpServletRequest req, Model model) {
//		
//		HttpSession session = req.getSession();
//		String username=(String)session.getAttribute("user");
//		
//		UserData user = userRepository.findByUsername(username);
//
//		Optional<ShoeData> shoeDataOptional = shoeRepository.findById(shoeId);
//
//		if (shoeDataOptional.isPresent()) {
//			ShoeData shoe = shoeDataOptional.get();
//
//			// Check if the item is already in the cart
//			Cart existingCartItem = cartRepository.findByUserAndShoe(user, shoe);
//			if (existingCartItem != null) {
//				// Update quantity if it already exists
//				existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
//				cartRepository.save(existingCartItem);
//			} else {
//				// Add a new cart item
//				Cart cartItem = new Cart();
//				cartItem.setUser(user);
//				cartItem.setShoe(shoe);
//				cartItem.setQuantity(quantity);
//				cartRepository.save(cartItem);
//			}
//
//			// Fetch updated cart
//			List<Cart> cartItems = cartRepository.findByUser(user);
//			model.addAttribute("cartItems", cartItems);
//			model.addAttribute("totalItems", cartItems.size());
//
//			return "cart"; // Redirect to the cart page
//		}
//
//		return "productDetail";
//	}

	@GetMapping("/cart")
	public String viewCart(HttpServletRequest req, Model model) {
	    HttpSession session = req.getSession();
	    String username = (String) session.getAttribute("user");
	    
	    if (username == null) {
	        // If no user is found in the session, redirect to login page
	        return "redirect:/login";
	    }

	    // Get the logged-in user
	    UserData user = userRepository.findByUsername(username);

	    if (user == null) {
	        // If the user is not found in the database, redirect to login
	        return "redirect:/login";
	    }

	    // Fetch the user's cart items
	    List<Cart> cartItems = cartRepository.findByUser(user);

	    // Add the cart items and total item count to the model
	    model.addAttribute("cartItems", cartItems);
	    model.addAttribute("totalItems", cartItems.size());

	    return "cart"; // Return the cart page
	}


	@PostMapping("/deleteCartItem")
	@Transactional
	public String deleteItem(@RequestParam("cartId") int cartId, HttpServletRequest req) {
	    HttpSession session = req.getSession();
	    String username = (String) session.getAttribute("user");

	    UserData user = userRepository.findByUsername(username);

	    // Directly delete by cartId if it uniquely identifies the cart item
	    cartRepository.deleteById(cartId);

	    return "redirect:/cart"; // Redirect back to the cart page
	}


}
