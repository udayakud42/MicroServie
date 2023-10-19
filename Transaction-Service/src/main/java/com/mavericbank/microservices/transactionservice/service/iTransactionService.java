package com.mavericbank.microservices.transactionservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mavericbank.microservices.transactionservice.model.Transaction;

public interface iTransactionService {
	
	ResponseEntity<Transaction> create (Transaction transaction) throws Throwable;
	
	Transaction updateTransaction (long transactionId, Transaction transaction);
	
	Transaction getTransaction (long transactionId);
	
	List<Transaction> getTransactions();
	
	ResponseEntity<Transaction> deleteTransaction(long transactionId);
	
//	ResponseEntity<Transaction> createTransaction(@PathVariable Long accId, @RequestBody Transaction transaction) throws Throwable;
	
//	ResponseEntity<Transaction> createTransaction(@PathVariable String accId, @RequestBody Transaction transaction) throws Throwable;

//	List<Transaction> getTransactionsByAcc(String accId);

	List<Transaction> getTransactionsByAcc(Long accId);

	ResponseEntity<Transaction> createTransaction(Long accId, Transaction transaction) throws Throwable;
}
