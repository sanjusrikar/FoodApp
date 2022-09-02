package com.cl.FoodApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.FoodApp.dto.Menu;
import com.cl.FoodApp.dto.User;
import com.cl.FoodApp.repository.MenuRepo;

@Repository
public class MenuDAO {
	
	@Autowired
	MenuRepo repository;
	
	public Menu saveMenu(Menu Menu) {
		return repository.save(Menu);
	}
	
	public void deleteMenu(int id) {
		Menu Menu = repository.findById(id).orElse(null);
		repository.delete(Menu);
		return;
	}
	
	public Menu updateMenu(Menu menu, int id) {
		if(repository.findById(id).isEmpty()) {
			return null;
		}else {
			Menu old_menu = repository.findById(id).orElse(null);
			old_menu.setId(menu.getId());
			old_menu.setFoods(menu.getFoods());
			old_menu.setUser(menu.getUser());
			return repository.save(menu);
		}
	}
	
	
	public List<Menu> findAll() {
		return repository.findAll();
	}
	
	public Menu findById(int id) {
		return repository.findById(id).orElse(null);
	}

}
