package com.mdsl.institution.service.user.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdsl.institution.domain.user.User;
import com.mdsl.institution.repository.user.UserRepository;
import com.mdsl.institution.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    
    @Override
    public Optional<User> findByEmail(String email)
    {
	return userRepository.findByEmail(email);
    }

}
