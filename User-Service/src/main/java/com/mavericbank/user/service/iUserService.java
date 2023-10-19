package com.mavericbank.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mavericbank.user.model.User;

public interface iUserService {
	
	ResponseEntity<User> createUser (User user);
	
	User updateUser (long customerId, User user);
	
	User getUser (long customerId);
	
	List<User> getUsers();
	
	ResponseEntity<User> deleteUser (long customerId);

}
