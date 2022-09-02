package com.cl.FoodApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.repository.UserRepo;

@Repository
public class UserDAO {
	
	@Autowired
	public UserRepo repository;

	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public User updateUser(User user, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}else {
			User oldUser = repository.findById(id).orElse(null);
			oldUser.setId(user.getId());
			oldUser.setBranch(user.getBranch());
			oldUser.setEmail(user.getEmail());
			oldUser.setMenu(user.getMenu());
			oldUser.setName(user.getName());
			oldUser.setOrders(user.getOrders());
			oldUser.setPassword(user.getPassword());
			oldUser.setRole(user.getRole());
			return repository.save(oldUser);
		}
	}
	
	public void deleteUser(int id) {
		User user = repository.findById(id).orElse(null);
		repository.delete(user);
		return;
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	
	
	

}
