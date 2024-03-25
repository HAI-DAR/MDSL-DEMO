package com.mdsl.institution.service.user;

import java.util.Optional;

import com.mdsl.institution.domain.user.User;

/**
 * Service interface for user operations.
 */
public interface UserService
{
    /**
     * Finds a user by email.
     *
     * @param email The email of the user.
     * @return An optional containing the user if found, empty otherwise.
     */
    Optional<User> findByEmail(String email);
}
