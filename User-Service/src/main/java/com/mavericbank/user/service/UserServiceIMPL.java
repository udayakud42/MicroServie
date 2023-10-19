package com.mavericbank.user.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mavericbank.user.exception.ResourceNotFoundException;
import com.mavericbank.user.model.User;
import com.mavericbank.user.repository.UserRepo;
@Service
public class UserServiceIMPL implements iUserService{
	@Autowired
	private UserRepo repo;
	// Create Customer
	@Override
	public ResponseEntity<User> createUser(User user) {
		User save = repo.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(save.getCustomerId())
				.toUri();   

		return ResponseEntity.created(location).build();		
	}
	// Update User
	@Override
	public User updateUser(long customerId, User user) {
		return this.repo.findById(customerId)
				.map(cust ->{
//					if (null == user.getCustomerId()) {
//					} else {
//						cust.setCustomerId(user.getCustomerId());
//					}
					if (null == user.getFirstName()) {
					} else {
						cust.setFirstName(user.getFirstName());
					}
					if (null == user.getMiddleName()) {
					} else {
						cust.setMiddleName(user.getMiddleName());
					}
					if (null == user.getLastName()) {
					} else {
						cust.setLastName(user.getLastName());
					}
					if (null == user.getAddress()) {
					} else {
						cust.setAddress(user.getAddress());
					}
					if (null == user.getDateOfBirth()) {
					} else {
						cust.setDateOfBirth(user.getDateOfBirth());
					}
					if (null == user.getEmail()) {
					} else {
						cust.setEmail(user.getEmail());
					}
					if (null == user.getPassword()) {
					} else {
						cust.setPassword(user.getPassword());
					}
					if (null == user.getPhoneNumber()) {
					} else {
						cust.setPhoneNumber(user.getPhoneNumber());
					}
					if (null == user.getRole()) {
					} else {
						cust.setRole(user.getRole());
					}
					if (null == user.getCreatedAt()) {
					} else {
						cust.setCreatedAt(user.getCreatedAt());
					}
					cust.setUpdatedAt(LocalDate.now());
										
					return repo.save(cust);
				})
				.orElseThrow(()->new ResourceNotFoundException("User not found with id : " + customerId));
		}
	
	// get one user
	@Override
	public User getUser(long id) {
		 User findById = repo.findById(id)
				 .orElseThrow(()->new ResourceNotFoundException("User not found with id : " + id));
		 return findById;
	}
	// get all users
	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}
	// delete one user
	@Override
	public ResponseEntity<User> deleteUser(long customerId) {
		User findById = this.repo.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id : " + customerId));
		this.repo.deleteById(customerId);
			
				return ResponseEntity.ok().build();
	}

}
