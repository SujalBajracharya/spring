/*
 * package com.virinchi.model;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
 * 
 * @Entity public class Color {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="shoeId") private ShoeData shoe;
 * 
 * private String color;
 * 
 * public int getId() { return id; }
 * 
 * public void setId(int id) { this.id = id; }
 * 
 * public ShoeData getShoe() { return shoe; }
 * 
 * public void setShoe(ShoeData shoe) { this.shoe = shoe; }
 * 
 * public String getColor() { return color; }
 * 
 * public void setColor(String color) { this.color = color; }
 * 
 * 
 * 
 * }
 */