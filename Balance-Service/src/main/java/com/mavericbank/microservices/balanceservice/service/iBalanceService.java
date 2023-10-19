package com.mavericbank.microservices.balanceservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mavericbank.microservices.balanceservice.model.Balance;

public interface iBalanceService {
	
	ResponseEntity<Balance> createBalance (Balance balance) throws Exception;
	
	Balance updateBalance (long balanceId, Balance balance);
	
	Balance getBalance (long balanceId);
	
	Balance getBalanceByAccount (String accountId);
	
	List<Balance> getBalances();
	
	ResponseEntity<Balance> deleteBalance(long balanceId);

}
