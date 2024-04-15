package com.mdsl.institution.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class SecurityConfigurationIT {

    @Bean
    SecurityFilterChain  disableSecurityFilterChain(HttpSecurity http) throws Exception {
	return http.csrf(AbstractHttpConfigurer::disable)
	.authorizeHttpRequests(
		req -> req.requestMatchers("/**").permitAll()
		).build();
	
    }

}