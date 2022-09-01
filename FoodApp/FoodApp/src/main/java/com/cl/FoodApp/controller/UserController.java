package com.cl.FoodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cl.FoodApp.dto.EmailMessage;
import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.service.EmailSenderService;
import com.cl.FoodApp.service.FoodOrderService;
import com.cl.FoodApp.service.MenuService;
import com.cl.FoodApp.service.UserService;
import com.cl.FoodApp.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	UserService service;
	@Autowired
	FoodOrderService orderservice;
	@Autowired
	MenuService menuservice;
	


	@PostMapping("/saveUser")
	public ResponseEntity<ResponseStructure<User>>  saveUser(@RequestBody User user) {
		return service.saveUser(user);

	}
    
    @PostMapping("/sendMail/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable int id) {
        service.sendBill(id);
        return ResponseEntity.ok("Success");
    }

	@PostMapping("/placeOrder/{id}")
	public String placeOrder(@RequestBody FoodOrder foodOrder,@PathVariable int id) {
		// calculate total_price

		int order_id = service.placeOrder(id, foodOrder);
		if (order_id != -1) {
			return "Order " + order_id + " Placed !!";
		}
		return "Order not Placed !!";

	}
	
	@PostMapping("/addItemsToOrder/{id}")
	public FoodOrder addItemsToOrder(@RequestBody List<Food> foods , @PathVariable int id) {
		return orderservice.addItems(id, foods);
	}

	@PostMapping("/readyOrder/{id}")
	public String readyOrder(@PathVariable int id) {
		// calculate total_price
		int order_id = service.readyOrder(id);
		if (order_id != -1) {
			return "Order" + order_id + " is Ready !!";
		}
		return "Order " + order_id + " is Not Ready !!";

		
	}
	@PostMapping("/createMenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> createMenu(@RequestBody Menu menu ,@PathVariable int id ){
		return service.createMenu(id, menu);
	}
	
	@PostMapping("/addItemsToMenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> addItemsToMenu(@RequestBody List<Food> foods , @PathVariable int id){
		return menuservice.addItemsToMenu(id, foods);
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		return "User removed " + id;

	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int id) {
		return service.findById(id);
	}
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@PathVariable int id){
		return service.updateUser(user, id);
	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return service.findAll();

	}

}
