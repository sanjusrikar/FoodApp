package com.cl.FoodApp.dto;
import java.util.*;

import javax.persistence.*;
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String Name;
	private Role role;
	public enum Role {
		STAFF , BRANCH_MANAGER , ADMIN;
	};

	private String Email;
	private String Password;
	private String Branch;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy="user")
	private List<FoodOrder> orders;
	@OneToOne(cascade = CascadeType.ALL, mappedBy="user")
	private Menu menu;

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public List<FoodOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<FoodOrder> orders) {
		this.orders = orders;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}
