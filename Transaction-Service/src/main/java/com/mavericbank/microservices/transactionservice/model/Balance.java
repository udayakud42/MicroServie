package com.mavericbank.microservices.transactionservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Maveric_Balance")
public class Balance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long balanceId;
	@Column(unique=true)
	private Long accountId;
	
	private BigDecimal amount;
	
	private String currency;
	
	private LocalDate createdAt;
	
	private LocalDate updatedAt;
}
