package com.cl.FoodApp.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Food {
	
	public enum food_type {
		VEG , NON_VEG;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String ImageURL;
	public String name;
	private int Quantity;
	private Boolean isAvailable;
	public int Price;
	public String description;
	public food_type type;
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
    @JoinColumn
    @JsonIgnore
	private Menu menu;
	@ManyToOne
    @JoinColumn
    @JsonIgnore
    private FoodOrder foodOrder;
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}
	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	
	public int getId() {
		return Id;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getImageURL() {
		return ImageURL;
	}
	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public food_type getType() {
		return type;
	}
	public void setType(food_type type) {
		this.type = type;
	}
	
	

	

}
