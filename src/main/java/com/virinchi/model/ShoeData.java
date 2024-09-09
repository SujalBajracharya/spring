package com.virinchi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class ShoeData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Lob //Binary Large objects
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image; // ASCII will be saved as image
	
	private String shoeName;
	
	private String brand;
	

    @OneToMany(mappedBy = "shoeData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoeSize> sizes = new ArrayList<>();


    @OneToMany(mappedBy = "shoeData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoeColor> colors;

    private int price;
    
    private String year;
	
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

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<ShoeColor> getColors() {
		return colors;
	}
	public void setColors(List<ShoeColor> colors) {
		this.colors = colors;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
