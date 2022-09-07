package com.cl.FoodApp.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_Id;

	private int total_price;
	private Timestamp timeStamp;
	private Status status;

	public enum Status {
		WAITING, PREPARING, READY;
	};
	
	@OneToMany( mappedBy = "foodOrder")
	@JsonIgnore
	private List<Food> foods;
	


	private String name;
	private String email;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private User user;

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	
	public int getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(int order_Id) {
		this.order_Id = order_Id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
