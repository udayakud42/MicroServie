package com.mavericbank.user.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="account-service", url="localhost:3010")
public interface AccountProxy {
//	
//	@GetMapping("/account/findbycus/{id}")
//	public Account retrieveAccountByCustID {
//		@PathVariable String id
//	} 

}
