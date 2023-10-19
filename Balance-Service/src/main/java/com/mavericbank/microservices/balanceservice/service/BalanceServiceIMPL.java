package com.mavericbank.microservices.balanceservice.service;

import java.net.URI;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mavericbank.microservices.balanceservice.exception.ResourceNotFoundException;
import com.mavericbank.microservices.balanceservice.model.Balance;
import com.mavericbank.microservices.balanceservice.repository.BalanceRepo;

@Service
public class BalanceServiceIMPL implements iBalanceService {
	@Autowired
	private BalanceRepo repo;

	// Create Customer
	@Override
	public ResponseEntity<Balance> createBalance(Balance balance) throws Exception {
		try {
			balance.setCreatedAt(LocalDate.now());
			Balance save = repo.save(balance);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(save.getBalanceId()).toUri();

			return ResponseEntity.created(location).build();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(	e.getMessage().contains("[Duplicate entry")) {
				System.out.println(e.getMessage());
				throw new Exception("Account ID Already Exist");
			}
		}
		return null;
		
		
	}

	// Update Balance
	@Override
	public Balance updateBalance(long balanceId, Balance balance) {
		return this.repo.findById(balanceId).map(bal -> {
//			if (null == balance.getAccountId()) {
//			} else {
//				bal.setAccountId(balance.getAccountId());
//			}
			if (null == balance.getAmount()) {
			} else {
				bal.setAmount(balance.getAmount());
			}
/*			if (balance.getAmount() == null) {				
				throw new ResourceNotFoundException("Balance Amount is Null or Blank");
			} else {
				bal.setAmount(balance.getAmount());
			}*/
//			if (null == balance.getCurrency()) {
//			} else {
//				bal.setCurrency(balance.getCurrency());
//			}
//			if (null == balance.getCreatedAt()) {
//			} else {
//				bal.setCreatedAt(balance.getCreatedAt());
//			}

				bal.setUpdatedAt(LocalDate.now());			
	
			return repo.save(bal);
		}).orElseThrow(() -> new ResourceNotFoundException("Balance not found with id : " + balanceId));
//				.orElse(createBalance(balance));
	}

	// get one balance
	@Override
	public Balance getBalance(long balanceId) {
		Balance findById = repo.findById(balanceId)
				.orElseThrow(() -> new ResourceNotFoundException("Balance not found with id : " + balanceId));
		return findById;
	}

	// get all balances
	@Override
	public List<Balance> getBalances() {
		return repo.findAll();
	}

	// delete one balance
	@Override
	public ResponseEntity<Balance> deleteBalance(long balanceId) {
		Balance findById = this.repo.findById(balanceId)
				.orElseThrow(() -> new ResourceNotFoundException("Balance not found with id : " + balanceId));
		this.repo.deleteById(balanceId);

		return ResponseEntity.ok().build();
	}

	@Override
	public Balance getBalanceByAccount(String accountId) {
		Balance findByAccountId = repo.findByAccountId(accountId);
		return findByAccountId;
	}

}
