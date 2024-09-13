package com.virinchi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.virinchi.model.ShoeData;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeData, Integer> {

	// Custom query to search by shoe name or brand (case-insensitive)
//	List<ShoeData> findByShoeNameContainingIgnoreCaseOrBrandContainingIgnoreCase(String shoeName, String brand);
	Page<ShoeData> findByShoeNameContainingIgnoreCaseOrBrandContainingIgnoreCase(String shoeName, String brand, Pageable pageable);

	
	@Query("SELECT s FROM ShoeData s WHERE s.price >= :minPrice AND s.price <= :maxPrice")
    List<ShoeData> findShoesByPriceRange(@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice);

	List<ShoeData> findByBrandContainingIgnoreCase(String brand);
	
	List<ShoeData> findBySizesSizeIn(List<String> sizes);
//	Sizes: Refers to the sizes collection in ShoeData.
//	Size: Refers to the size field in ShoeSize.
//	In: Allows you to pass a list of sizes, and the query will return shoes that match any of the sizes in the list.

}
