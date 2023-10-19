package com.mavericbank.account.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mavericbank.account.exception.ResourceNotFoundException;
import com.mavericbank.account.model.Account;
import com.mavericbank.account.repository.AccountRepo;
@Service
public class AccountServiceIMPL implements iAccountService{
	@Autowired
	private AccountRepo repo;
	// Create Customer
	@Override
	public ResponseEntity<Account> createAccount(Account account) {
		Account save = repo.save(account);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(save.getAccountId())
				.toUri();   

		return ResponseEntity.created(location).build();		
	}
	// Update Account
	@Override
	public Account updateAccount(long accountId, Account account) {
		return this.repo.findById(accountId)
				.map(acc ->{
					if (null == account.getCustomerId()) {
					} else {
						acc.setCustomerId(account.getCustomerId());
					}
					if (null == account.getType()) {
					} else {
						acc.setType(account.getType());
					}				
										
					return repo.save(acc);
				})
				.orElseThrow(()->new ResourceNotFoundException("Account not found with id : " + accountId));
		}
	
	// get one account
	@Override
	public Account getAccount(long accountId) {
		 Account findById = repo.findById(accountId)
				 .orElseThrow(()->new ResourceNotFoundException("Account not found with id : " + accountId));
		 return findById;
	}
	// get accounts by Customer ID
	@Override
	public List<Account> getAccountByCustID(String CusID) {
		List<Account> findByCustomerId = repo.findByCustomerId(CusID);
		if (null == findByCustomerId) {
				new ResourceNotFoundException("Account not found for Customer id : " + CusID);
		}
		return findByCustomerId;
	}
	
	// get all accounts
	@Override
	public List<Account> getAccounts() {
		return repo.findAll();
	}
	// delete one account
	@Override
	public ResponseEntity<Account> deleteAccount(long accountId) {
		Account findById = this.repo.findById(accountId)
				.orElseThrow(()-> new ResourceNotFoundException("Account not found with id : " + accountId));
		this.repo.deleteById(accountId);
			
				return ResponseEntity.ok().build();
	}


}
