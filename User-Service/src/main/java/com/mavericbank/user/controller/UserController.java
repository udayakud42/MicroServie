package com.mavericbank.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mavericbank.user.model.User;
import com.mavericbank.user.service.UserServiceIMPL;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceIMPL service;
	// Create 
	@PostMapping("/create")
	public ResponseEntity<User> createUser (@RequestBody User user) {
		return service.createUser(user);
	}
	// Find One using ID
	@GetMapping("/find/{id}")
	public User retrieveUser(@PathVariable long id) {
		return service.getUser(id);
	}
	
	// Find All
	@GetMapping("/find")
	public List<User> retrieveAllUser() {
		return service.getUsers();
	}
	// Update
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable long id, @RequestBody User user) {
		return service.updateUser(id, user);	
	}
	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteEmployee (@PathVariable long id) {	
		return service.deleteUser(id);
	}
}
