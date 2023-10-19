package com.mavericbank.microservices.transactionservice.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.mavericbank.microservices.transactionservice.exception.GlobalExceptionHandler;
import com.mavericbank.microservices.transactionservice.exception.InsufficientBalance;
import com.mavericbank.microservices.transactionservice.exception.InvalidTransactionType;
import com.mavericbank.microservices.transactionservice.feign.BalanceServiceProxy;
import com.mavericbank.microservices.transactionservice.model.Balance;
import com.mavericbank.microservices.transactionservice.model.Transaction;
import com.mavericbank.microservices.transactionservice.service.TransactionServiceIMPL;

@RestController
@RequestMapping("/transaction-service")
public class TransactionController {
	@Autowired
	private TransactionServiceIMPL service;
	@Autowired
	private BalanceServiceProxy proxy;

	// Create
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) throws Throwable {
		return service.create(transaction);
	}

	// Find One
	@GetMapping("/find/{id}")
	public Transaction retrieveTransaction(@PathVariable long id) {
		return service.getTransaction(id);
	}

	// Find All
	@GetMapping("/find")
	public List<Transaction> retrieveAllTransaction() {
		return service.getTransactions();
	}

	// Update
	@PutMapping("/update/{id}")
	public Transaction updateTransaction(@PathVariable long id, @RequestBody Transaction transaction) {
		return service.updateTransaction(id, transaction);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable long id) {
		return service.deleteTransaction(id);
	}
	// Feign - Get and sending info Balance Service
	@PostMapping("/balance-service/find/{accId}/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Transaction> createTransaction(@PathVariable Long accId, @RequestBody Transaction transaction)
			throws Throwable {
		return service.createTransaction(accId, transaction);
	}
	
	// Find All Transactions by Account ID
	@GetMapping("/findbyacc/{accID}")
	public List<Transaction> retrieveAllTransactionByAcc(@PathVariable Long accID) {
		return service.getTransactionsByAcc(accID);
	}
	
}
