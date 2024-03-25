package com.mdsl.institution.repository.user;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdsl.institution.domain.user.User;

/**
 * Repository interface for accessing users.
 */
public interface UserRepository extends JpaRepository<User, BigDecimal>
{
    /**
     * Finds a user by email.
     *
     * @param email The email of the user.
     * @return An optional containing the user if found, empty otherwise.
     */
    Optional<User> findByEmail(String email);

}
