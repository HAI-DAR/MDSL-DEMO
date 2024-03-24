package com.mdsl.institution.repository.user;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdsl.institution.domain.user.User;

public interface UserRepository extends JpaRepository<User, BigDecimal> {
	
	Optional<User> findByEmail(String email); 
	
}
