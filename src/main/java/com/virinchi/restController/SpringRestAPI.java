//package com.virinchi.restController;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.virinchi.repository.UserRepository;
//
//@RestController
//public class SpringRestAPI {
//	
//	@Autowired
//	private UserRepository uRepo;
//	
//	@GetMapping("/api/getAll")
//	public List<User> getAllData() {
//		return uRepo.findAll(null);
//	}
//	
//	@GetMapping("/api/getAll/{id}")
//	public Optional<User> getOneData(@PathVariable int id) {
//		return uRepo.findById(id);
//	}
//}
