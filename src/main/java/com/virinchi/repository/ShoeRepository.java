package com.virinchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.ShoeData;


@Repository
public interface ShoeRepository extends JpaRepository<ShoeData,Integer> {

}
