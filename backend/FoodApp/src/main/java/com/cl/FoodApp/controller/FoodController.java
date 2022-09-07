package com.cl.FoodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.service.FoodService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
public class FoodController {

	@Autowired
	FoodService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/saveFood")
	public ResponseEntity<ResponseStructure<Food>> saveFood(@RequestBody Food Food) {
		return service.saveFood(Food);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateFood/{id}")
	public ResponseEntity<ResponseStructure<Food>> updateFood(@RequestBody Food food , @PathVariable int id){
		return service.updateFood(food, id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteFood/{id}")
	public String deleteFood(@PathVariable int id) {
		service.deleteFood(id);
		return "Food removed "+id;
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getFoodById/{id}")
	public Food getFoodById(@PathVariable int id) {	
		return service.findById(id);	
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllFoods")
	public List<Food> getAllFoods() {
		return service.findAll();
		
	}

}
