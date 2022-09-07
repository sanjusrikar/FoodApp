package com.cl.FoodApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.repository.FoodRepo;

@Repository
public class FoodDAO {

	@Autowired
	FoodRepo repository;
	
	public Food saveFood(Food food) {
		return repository.save(food);
	}
	
	public void deleteFood(int id) {
		Food Food = repository.findById(id).orElse(null);
		repository.delete(Food);
		return;
	}
	public Food updateFood(Food food , int id) {
		Food old_food = repository.findById(id).orElse(null);
		if(old_food == null) {
			throw new IdNotFoundException();
		}else {
			old_food.setId(food.getId());
			old_food.setImageURL(food.getImageURL());
			if(food.getFoodOrder() != null) {
				old_food.setFoodOrder(food.getFoodOrder());
			}
			old_food.setIsAvailable(food.getIsAvailable());
			if(food.getMenu() != null) {
				old_food.setMenu(food.getMenu());
			}
			old_food.setName(food.getName());
			old_food.setPrice(food.getPrice());
			old_food.setQuantity(food.getQuantity());
			old_food.setType(food.getType());
			return repository.save(old_food);
		}
		
		
	}
	
	public List<Food> findAll() {
		return repository.findAll();
	}
	
	public Food findById(int id) {
		return repository.findById(id).orElse(null);
	}
}
