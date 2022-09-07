package com.cl.FoodApp.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.FoodApp.dao.FoodDAO;
import com.cl.FoodApp.dao.FoodOrderDAO;
import com.cl.FoodApp.dao.MenuDAO;
import com.cl.FoodApp.dao.UserDAO;
import com.cl.FoodApp.dto.Food;
import com.cl.FoodApp.dto.FoodOrder;
import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.dto.FoodOrder.Status;
import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.dto.User.Role;
import com.cl.FoodApp.exception.IdNotFoundException;
import com.cl.FoodApp.exception.NotAuthorizedException;
import com.cl.FoodApp.util.AES;
import com.cl.FoodApp.util.ResponseStructure;

@Service
public class UserService {

	// business logic
	// speaks with dao

	@Autowired
	UserDAO dao;
	@Autowired
	FoodOrderDAO orderdao;
	@Autowired
	FoodDAO fooddao;
	@Autowired
	MenuDAO menudao;
	@Autowired
	EmailSenderService emailSenderService;
	@Autowired
	AES aes;

	@SuppressWarnings("static-access")
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		
		
		User user1 = dao.findByEmail(user.getEmail());
		
		if(user1 != null) {
			String ePassword1 = aes.encrypt(user.getPassword(), "SECRET");
			if(user1.getPassword().contentEquals(ePassword1)) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setMessage("Successful login");
				structure.setStatus(HttpStatus.ALREADY_REPORTED.value());
				structure.setR(user1);
				return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ALREADY_REPORTED);
			}
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setMessage("Wrong Email or Password");
			structure.setStatus(HttpStatus.GONE .value());
			structure.setR(null);
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.GONE);
			

		}
		

		String ePassword = aes.encrypt(user.getPassword(), "SECRET");
		user.setPassword(ePassword);

		String domain = user.getEmail().split("@")[1];

		if (domain.contentEquals("admin.com")) {
			user.setRole(Role.ADMIN);
		} else if (domain.contentEquals("branchadmin.com")) {
			user.setRole(Role.BRANCH_MANAGER);

		} else {
			user.setRole(Role.STAFF);
		}
		ResponseStructure<User> structure = new ResponseStructure<User>();
		structure.setMessage("User successfully saved and Registered");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setR(dao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User user, int id){
		User old_user = dao.updateUser(user, id);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if(old_user != null) {
			structure.setMessage("User Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setR(old_user);
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setR(null);
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Menu>> createMenu(int id , Menu menu) {
		User user = dao.findById(id);
		if(user == null) {
			throw new IdNotFoundException();
		}else {
			if (user.getRole() == Role.BRANCH_MANAGER) {
				menu.setUser(user);
				Menu createdMenu = menudao.saveMenu(menu);
				ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
				structure.setMessage("Menu Created");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setR(createdMenu);
				return new ResponseEntity<ResponseStructure<Menu>>(structure,HttpStatus.CREATED);

			} else {
				throw new NotAuthorizedException();
			}
		}
	}
	

	public void deleteUser(int id) {

		dao.deleteUser(id);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		User user = dao.findById(id);
		if(user == null) {
			throw new IdNotFoundException();
		}else {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setMessage("User Found");
			structure.setStatus(HttpStatus.OK.value());
			structure.setR(user);
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
	}

	public List<User> findAll() {

		return dao.findAll();
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> placeOrder(int id, FoodOrder order ) {

		User user = dao.findById(id);
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		if (user.getRole() == Role.STAFF) {
			order.setUser(user);
			order.setStatus(Status.WAITING);
			order.setTimeStamp(timestamp);
			structure.setMessage("Order Created Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setR(orderdao.saveFoodOrder(order));
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);

		} else {
			throw new NotAuthorizedException();
		}

	}

	public int readyOrder(int id) {
		FoodOrder order = orderdao.findById(id);
		order.setStatus(Status.WAITING);
		FoodOrder newOrder = orderdao.updateFoodOrder(order);
		
		if(newOrder.getOrder_Id()!= -1) {
			return newOrder.getOrder_Id();
		}
		return -1;
	}
	
	public void sendBill(int id) {
		
		FoodOrder order = orderdao.findById(id);
		int total_price = order.getTotal_price();
		String email = order.getEmail();
		List<Food> foods = order.getFoods();
		double gst = 0.18;
		double price_after_tax =  (total_price - total_price*gst);
		String subject = "BILLING INFORMATION";
		String msg = "Hello ," +" Order placed " +
				"Total Amount is Rs." + price_after_tax + " Tax amount is Rs."+total_price*gst;
		// add items ordered later
		this.emailSenderService.sendEmail(email, subject, msg);

	}

}
