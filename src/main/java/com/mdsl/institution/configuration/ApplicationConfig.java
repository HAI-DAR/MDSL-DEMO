package com.mdsl.institution.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mdsl.institution.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Configuration class for configuring application beans.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig
{

    private final UserRepository repository;

    /**
     * Bean definition for UserDetailsService.
     *
     * @return UserDetailsService instance.
     */
    @Bean
    UserDetailsService userDetailsService()
    {
	return username -> repository.findByEmail(username)
		.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Bean definition for AuthenticationProvider.
     *
     * @return AuthenticationProvider instance.
     */
    @Bean
    AuthenticationProvider authenticationProvider()
    {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService());
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
    }
    
    
    /**
     * Bean definition for AuthenticationManager.
     *
     * @param config AuthenticationConfiguration instance.
     * @return AuthenticationManager instance.
     * @throws Exception if an error occurs while retrieving AuthenticationManager.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
	return config.getAuthenticationManager();
    }
    
    /**
     * Bean definition for PasswordEncoder.
     *
     * @return PasswordEncoder instance.
     */
    @Bean
    PasswordEncoder passwordEncoder()
    {
	return new BCryptPasswordEncoder();
    }
    
    /**
     * Bean definition for JavaTimeModule.
     *
     * @return JavaTimeModule instance.
     */
    @Bean
    JavaTimeModule javaTimeModule()
    {
	return new JavaTimeModule();
    }
}
