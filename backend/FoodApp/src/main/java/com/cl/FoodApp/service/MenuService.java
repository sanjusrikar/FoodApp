package com.cl.FoodApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.FoodDAO;
import com.cl.FoodApp.dao.MenuDAO;
import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.dto.FoodOrder.Status;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class MenuService {
	
	@Autowired
	MenuDAO dao;
	@Autowired
	FoodDAO fooddao;
	
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu Menu) {
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		structure.setMessage("Menu successfully saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setR(dao.saveMenu(Menu));
		return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Menu>> addItemsToMenu(int id ,List<Food> foods){
		Menu createdMenu = dao.findById(id);
		for(Food food : foods) {
			food.setMenu(createdMenu);
			fooddao.updateFood(food, food.getId());
			
		}
		createdMenu.setFoods(foods);
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setR(dao.updateMenu(createdMenu, id));
		return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int id){
		Menu old_menu = dao.updateMenu(menu, id);
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		if(old_menu != null) {
			structure.setMessage("User Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setR(old_menu);
			return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.OK);
		}else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setR(null);
			return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}
	
	public void deleteMenu(int id) {
		dao.deleteMenu(id);
	}
	
	public Menu findById(int id) {
		return dao.findById(id);
	}
	
	public List<Menu> findAll(){
		return dao.findAll();
	}

}
