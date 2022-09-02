package com.cl.FoodApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.dto.FoodOrder.Status;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.repository.FoodOrderRepo;

@Repository
public class FoodOrderDAO {

	@Autowired
	FoodOrderRepo repository;

	public FoodOrder saveFoodOrder(FoodOrder FoodOrder) {
		return repository.save(FoodOrder);
	}

	public void deleteFoodOrder(int id) {
		FoodOrder FoodOrder = repository.findById(id).orElse(null);
		repository.delete(FoodOrder);
		return;
	}
	public FoodOrder updateFoodOrder(FoodOrder FoodOrder) {
		FoodOrder existingOrder = repository.findById(FoodOrder.getOrder_Id()).orElse(null);
		if(existingOrder == null) {
			throw new IdNotFoundException();
		}
		existingOrder.setEmail(FoodOrder.getEmail());
		existingOrder.setName(FoodOrder.getName());
		existingOrder.setTotal_price(FoodOrder.getTotal_price());
		existingOrder.setUser(FoodOrder.getUser());
		existingOrder.setFoods(FoodOrder.getFoods());
		existingOrder.setStatus(FoodOrder.getStatus());
		return repository.save(existingOrder);
	}

	public List<FoodOrder> findAll() {
		return repository.findAll();
	}

	public FoodOrder findById(int id) {
		return repository.findById(id).orElse(null);
	}

}
