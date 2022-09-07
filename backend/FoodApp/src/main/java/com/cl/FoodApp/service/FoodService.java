package com.cl.FoodApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.FoodDAO;
import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class FoodService {
	
	@Autowired
	FoodDAO dao;
	
	public ResponseEntity<ResponseStructure<Food>> saveFood(Food Food) {
		ResponseStructure<Food> structure = new ResponseStructure<Food>();
		structure.setMessage("Food successfully saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setR(dao.saveFood(Food));
		return new ResponseEntity<ResponseStructure<Food>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Food>> updateFood(Food Food , int id) {
		ResponseStructure<Food> structure = new ResponseStructure<Food>();
		structure.setMessage("Food successfully updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setR(dao.updateFood(Food,id));
		return new ResponseEntity<ResponseStructure<Food>>(structure,HttpStatus.OK);
	}
	
	public void deleteFood(int id) {
		dao.deleteFood(id);
	}
	
	public Food findById(int id) {
		return dao.findById(id);
	}
	
	public List<Food> findAll(){
		return dao.findAll();
	}

}
