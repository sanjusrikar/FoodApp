package com.cl.FoodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.FoodApp.dto.FoodOrder;

public interface FoodOrderRepo extends JpaRepository<FoodOrder,Integer>{

}
