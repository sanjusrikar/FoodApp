package com.cl.FoodApp.dto;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	// list of foods
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
	List<Food> foods;
	@JsonIgnore
	@OneToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

}
