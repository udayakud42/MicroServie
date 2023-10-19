package com.mavericbank.microservices.transactionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mavericbank.microservices.transactionservice.model.Balance;
//Before Naming Server implementation
//@FeignClient(name = "balance-service",url = "http://localhost:3015")
//After Naming Server Implementation
@FeignClient(name = "balance-service")
public interface BalanceServiceProxy {
	
	@GetMapping("/balance-service/find/{id}")
	public Balance retrieveBalance(@PathVariable Long id);
	
	@GetMapping("/balance-service/findacc/{id}")
	public Balance retrieveBalanceByAcc(@PathVariable Long id);
	
	@PutMapping("/balance-service/update/{id}")
	public Balance updateBalance(@PathVariable long id, @RequestBody Balance balance);

}
