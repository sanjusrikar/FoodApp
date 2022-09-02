package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.Food;

public interface FoodRepo extends JpaRepository<Food,Integer> {

}
