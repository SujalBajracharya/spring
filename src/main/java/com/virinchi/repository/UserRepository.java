package com.virinchi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData,Integer> {
	
	UserData findByUsernameAndPassword(String username, String password);
	
	boolean existsByUsernameAndPassword(String username, String password);

	Optional<UserData> findByUsername(String username);

	
}
