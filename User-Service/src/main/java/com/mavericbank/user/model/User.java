package com.mavericbank.user.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "Maveric_User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;

	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private LocalDate dateOfBirth;
	
	private LocalDate createdAt;
	
	private LocalDate updatedAt;
	
	private String password;
	
	private String role;
	
}
