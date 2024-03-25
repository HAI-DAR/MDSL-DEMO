package com.mdsl.institution.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdsl.institution.dto.auth.AuthenticationRequest;
import com.mdsl.institution.dto.auth.AuthenticationResponse;
import com.mdsl.institution.dto.auth.RegisterRequest;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.service.auth.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller class for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController
{

    private final AuthenticationService authService;

    /**
     * Endpoint for user registration.
     *
     * @param request The registration request body.
     * @return ResponseEntity containing the authentication response.
     * @throws BaseException If an error occurs during registration.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request)
	    throws BaseException
    {
	return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Endpoint for user login.
     *
     * @param request The login request body.
     * @return ResponseEntity containing the authentication response.
     * @throws BaseException If an error occurs during login.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request)
	    throws BaseException
    {
	return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Endpoint for refreshing authentication token.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws BaseException If an error occurs during token refresh.
     */
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
	authService.refreshToken(request, response);
    }

}
