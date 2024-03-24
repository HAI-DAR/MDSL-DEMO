package com.mdsl.institution.service.user;

import java.util.Optional;

import com.mdsl.institution.domain.user.User;

public interface UserService
{
    Optional<User> findByEmail(String email);
}
