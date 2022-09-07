package com.cl.FoodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.service.FoodOrderService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
public class FoodOrderController {
	
	@Autowired
	FoodOrderService service;
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/saveFoodOrder")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(@RequestBody FoodOrder FoodOrder) {
		return service.saveFoodOrder(FoodOrder);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteFoodOrder/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFood(@PathVariable int id) {
		return service.deleteFoodOrder(id);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getFoodOrderById/{id}")
	public FoodOrder getFoodOrderById(@PathVariable int id) {
		System.out.println(service.findById(id));
		return service.findById(id);	
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllFoodOrders")
	public List<FoodOrder> getAllFoodOrders() {
		return service.findAll();
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getItemsInOrder/{id}")
	public List<Food> getItemsInOrder(@PathVariable int id) {
		return service.findById(id).getFoods();
		
	}


}
