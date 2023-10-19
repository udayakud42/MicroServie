package com.mavericbank.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mavericbank.user.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
