package com.mavericbank.account.controller;

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

import com.mavericbank.account.model.Account;
import com.mavericbank.account.service.AccountServiceIMPL;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountServiceIMPL service;
	// Create 
	@PostMapping("/create")
	public ResponseEntity<Account> createUser (@RequestBody Account account) {
		return service.createAccount(account);
	}
	// Find One
	@GetMapping("/find/{id}")
	public Account retrieveAccount(@PathVariable long id) {
		return service.getAccount(id);
	}
	
	// Find One By Customer ID
	@GetMapping("/findbycus/{id}")
	public List<Account> retrieveAccountByCustID(@PathVariable String id) {
		return service.getAccountByCustID(id);
	}
	// Find All
	@GetMapping("/find")
	public List<Account> retrieveAllAccount() {
		return service.getAccounts();
	}
	// Update
	@PutMapping("/update/{id}")
	public Account updateAccount(@PathVariable long id, @RequestBody Account account) {
		return service.updateAccount(id, account);	
	}
	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Account> deleteEmployee (@PathVariable long id) {	
		return service.deleteAccount(id);
	}
}
