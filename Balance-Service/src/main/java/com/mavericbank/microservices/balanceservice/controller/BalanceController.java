package com.mavericbank.microservices.balanceservice.controller;

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

import com.mavericbank.microservices.balanceservice.model.Balance;
import com.mavericbank.microservices.balanceservice.service.BalanceServiceIMPL;

@RestController
@RequestMapping("/balance-service")
public class BalanceController {
	@Autowired
	private BalanceServiceIMPL service;

	// Create
	@PostMapping("/create")
	public ResponseEntity<Balance> createBalance(@RequestBody Balance balance) throws Exception {
		return service.createBalance(balance);
	}

	// Find One
	@GetMapping("/find/{id}")
	public Balance retrieveBalance(@PathVariable long id) {
		return service.getBalance(id);
	}
	
	// Find One By Account
	@GetMapping("/findacc/{id}")
	public Balance retrieveBalanceByAcc(@PathVariable String id) {
		return service.getBalanceByAccount(id);
	}

	// Find All
	@GetMapping("/find")
	public List<Balance> retrieveAllBalance() {
		return service.getBalances();
	}

	// Update
	@PutMapping("/update/{id}")
	public Balance updateBalance(@PathVariable long id, @RequestBody Balance balance) {
		return service.updateBalance(id, balance);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Balance> deleteBalance(@PathVariable long id) {
		return service.deleteBalance(id);
	}
}
