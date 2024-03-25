package com.mdsl.institution.configuration;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mdsl.institution.service.token.LogoutService;

import lombok.RequiredArgsConstructor;

/**
 * Security configuration class for setting up application security.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration
{

    private static final String[] WHITE_LIST_URL = { 
	    "/v1/auth/**",
	    "/v2/api-docs", 
	    "/v3/api-docs", 
	    "/v3/api-docs/**", 
	    "/api-docs/**", 
	    "/swagger-resources",
	    "/swagger-resources/**", 
	    "/configuration/ui", 
	    "/configuration/security", 
	    "/swagger-ui/**", 
	    "/webjars/**",
	    "/swagger-ui.html" };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutService logoutHandler;

    /**
     * Configures security filter chain.
     *
     * @param http The HTTP security object.
     * @return The configured security filter chain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

	http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
			req -> req.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated())
		.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.logout(logout -> logout.logoutUrl("/v1/auth/logout").addLogoutHandler(logoutHandler)
			.logoutSuccessHandler(
				(request, response, authentication) -> SecurityContextHolder.clearContext()));

	return http.build();
    }
}
