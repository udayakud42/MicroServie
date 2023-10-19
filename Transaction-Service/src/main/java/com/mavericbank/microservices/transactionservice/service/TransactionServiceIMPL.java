package com.mavericbank.microservices.transactionservice.service;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mavericbank.microservices.transactionservice.exception.InsufficientBalance;
import com.mavericbank.microservices.transactionservice.exception.InvalidTransactionType;
import com.mavericbank.microservices.transactionservice.exception.ResourceNotFoundException;
import com.mavericbank.microservices.transactionservice.feign.BalanceServiceProxy;
import com.mavericbank.microservices.transactionservice.model.Balance;
import com.mavericbank.microservices.transactionservice.model.Transaction;
import com.mavericbank.microservices.transactionservice.repository.TransactionRepo;

@Service
public class TransactionServiceIMPL implements iTransactionService {
	@Autowired
	private TransactionRepo repo;
	@Autowired
	private BalanceServiceProxy proxy;

	// Create Customer
	@Override
	public ResponseEntity<Transaction> create(Transaction transaction) throws Throwable {
		try {
			transaction.setCreatedAt(LocalDate.now());
			Transaction save = repo.save(transaction);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(save.getTransactionId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	// Update Transaction
	@Override
	public Transaction updateTransaction(long transactionId, Transaction transaction) {
		return this.repo.findById(transactionId).map(trans -> {
			if (null == transaction.getTransactionType()) {
			} else {
				trans.setTransactionType(transaction.getTransactionType());
			}
			if (null == transaction.getAmount()) {
			} else {
				trans.setAmount(transaction.getAmount());
			}
			if (null == transaction.getAccountId()) {
			} else {
				trans.setAccountId(transaction.getAccountId());
			}
			if (null == transaction.getCreatedAt()) {
			} else {
				trans.setCreatedAt(transaction.getCreatedAt());
			}

			return repo.save(trans);
		}).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id : " + transactionId));
	}

	// get one transaction
	@Override
	public Transaction getTransaction(long transactionId) {
		Transaction findById = repo.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id : " + transactionId));
		return findById;
	}

	// get all transactions
	@Override
	public List<Transaction> getTransactions() {
		return repo.findAll();
	}

	// delete one transaction
	@Override
	public ResponseEntity<Transaction> deleteTransaction(long transactionId) {
		Transaction findById = this.repo.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id : " + transactionId));
		this.repo.deleteById(transactionId);

		return ResponseEntity.ok().build();
	}

	// Deposit & Withdrawal Service
	@Override
	public ResponseEntity<Transaction> createTransaction(@PathVariable Long accId,
			@RequestBody Transaction transaction) throws Throwable {
		if (transaction.getTransactionType().equalsIgnoreCase("withdrawal")) {
			try {
				Balance retrieveBalance = proxy.retrieveBalanceByAcc(accId);
				BigDecimal balanceAmount = retrieveBalance.getAmount();

				BigDecimal transactionAmount = transaction.getAmount();

				int compareTo = balanceAmount.compareTo(transactionAmount);

				if (compareTo >= 0) {
					BigDecimal newBalance = balanceAmount.subtract(transactionAmount);
					retrieveBalance.setAmount(newBalance);
					proxy.updateBalance(retrieveBalance.getBalanceId(), retrieveBalance);
					transaction.setAccountId(accId);
					return create(transaction);
				} else {
					throw new InsufficientBalance("Insufficient Balance");
				}

			} catch (Exception e) {
				throw new Exception(e);
			}
		} else if (transaction.getTransactionType().equalsIgnoreCase("deposit")) {
			try {
				if ((transaction.getAmount() != null) && (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0)) {
					Balance retrieveBalance = proxy.retrieveBalanceByAcc(accId);
					BigDecimal balanceAmount = retrieveBalance.getAmount();

					BigDecimal transactionAmount = transaction.getAmount();
					BigDecimal newBalance = balanceAmount.add(transactionAmount);
					retrieveBalance.setAmount(newBalance);
					proxy.updateBalance(retrieveBalance.getBalanceId(), retrieveBalance);
					transaction.setAccountId(accId);
					return create(transaction);
				} else {
					throw new Exception("Enter a valid Amount");
				}
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else
			throw new InvalidTransactionType("Transaction Type should be either Deposit or Withdrawal");
	}

	@Override
	public List<Transaction> getTransactionsByAcc(Long accId) {
		return repo.findByAccountId(accId);
	}





}
