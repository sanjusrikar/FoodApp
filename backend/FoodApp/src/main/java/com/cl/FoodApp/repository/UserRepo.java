package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cl.FoodApp.dto.User;


public interface UserRepo extends JpaRepository<User,Integer> {
	
	User findByEmail(String email);

}
