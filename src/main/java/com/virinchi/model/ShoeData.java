package com.virinchi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ShoeData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String shoeName;
	
	private String brand;
	
	/* private int year; */

    @OneToMany(mappedBy = "shoeData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoeSize> sizes = new ArrayList<>();


//    @OneToMany(mappedBy = "shoe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Color> color;
//
//    private int price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getShoeName() {
		return shoeName;
	}
	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}
	
	public List<ShoeSize> getSizes() {
		return sizes;
	}
	public void setSizes(List<ShoeSize> sizes) {
		this.sizes = sizes;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	
//	public int getYear() {
//		return year;
//	}
//	public void setYear(int year) {
//		this.year = year;
//	}
//	
//	public List<Color> getColor() {
//		return color;
//	}
//	public void setColor(List<Color> color) {
//		this.color = color;
//	}
//	public int getPrice() {
//		return price;
//	}
//	public void setPrice(int price) {
//		this.price = price;
//	}
	
	
	
}
