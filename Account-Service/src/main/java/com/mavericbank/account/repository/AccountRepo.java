package com.mavericbank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mavericbank.account.model.Account;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
	List<Account> findByCustomerId(String customerId);
}
