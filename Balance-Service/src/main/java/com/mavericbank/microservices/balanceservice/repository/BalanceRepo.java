package com.mavericbank.microservices.balanceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mavericbank.microservices.balanceservice.model.Balance;



@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long>{

	Balance findByAccountId(String accountId);
}
