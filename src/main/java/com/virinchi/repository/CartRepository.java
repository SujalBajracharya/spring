package com.virinchi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.Cart;
import com.virinchi.model.ShoeData;
import com.virinchi.model.UserData;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(UserData user); // Fetch all cart items for a user

    Cart findByUserAndShoe(UserData user, ShoeData shoe);

    void deleteById(int cartId); 
}
