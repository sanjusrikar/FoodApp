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

import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.service.MenuService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
public class MenuController {
	
	@Autowired
	MenuService service;
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/saveMenu")
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu Menu) {
		return service.saveMenu(Menu);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteMenu/{id}")
	public String deleteMenu(@PathVariable int id) {
		service.deleteMenu(id);
		return "Menu removed "+id;
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateMenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(@RequestBody Menu menu,@PathVariable int id){
		return service.updateMenu(menu, id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getMenuById/{id}")
	public Menu getMenuById(@PathVariable int id) {	
		return service.findById(id);	
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllMenus")
	public List<Menu> getAllMenus() {
		return service.findAll();
		
	}

}
