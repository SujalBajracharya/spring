package com.virinchi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData,Integer> {
	
	UserData findByUsernameAndPassword(String username, String password);
	
	boolean existsByUsernameAndPassword(String username, String password);

	UserData findByUsername(String username);

	
}
