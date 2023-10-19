package com.mavericbank.account.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mavericbank.account.model.Account;

public interface iAccountService {
	
	ResponseEntity<Account> createAccount (Account account);
	
	Account updateAccount (long accountId, Account account);
	
	Account getAccount (long accountId);
	
	List<Account> getAccountByCustID (String CusID);
	
	List<Account> getAccounts();
	
	ResponseEntity<Account> deleteAccount (long accountId);

}
