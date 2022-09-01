package com.cl.FoodApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.FoodDAO;
import com.cl.FoodApp.dao.FoodOrderDAO;
import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.dto.FoodOrder.Status;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class FoodOrderService {
	
	@Autowired
	FoodOrderDAO dao;
	@Autowired
	FoodDAO fooddao;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder FoodOrder) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		structure.setMessage("Order successfully saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setR(dao.saveFoodOrder(FoodOrder));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.CREATED);
	}
	
	public void deleteFoodOrder(int id) {
		dao.deleteFoodOrder(id);
	}
	
	public FoodOrder addItems(int id , List<Food> foods) {
		FoodOrder placedOrder = dao.findById(id);
		int cost = 0;
		for(Food food : foods) {
			cost += food.Price;
//			System.out.println(food.getName());
			food.setFoodOrder(placedOrder);
			fooddao.updateFood(food, food.getId());
			
		}
		placedOrder.setTotal_price(cost);
		placedOrder.setFoods(foods);
		placedOrder.setStatus(Status.PREPARING);
		dao.updateFoodOrder(placedOrder);
		return placedOrder;
		
	}
	
	public FoodOrder findById(int id) {
		return dao.findById(id);
	}
	
	public List<FoodOrder> findAll(){
		return dao.findAll();
	}

}
