package com.mdsl.institution.service.auth;

import com.mdsl.institution.dto.auth.AuthenticationRequest;
import com.mdsl.institution.dto.auth.AuthenticationResponse;
import com.mdsl.institution.dto.auth.RegisterRequest;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Service interface for authentication operations.
 */
public interface AuthenticationService
{
    /**
     * Registers a user.
     *
     * @param request The registration request.
     * @return The authentication response.
     * @throws BusinessException If an error occurs during registration.
     */
    AuthenticationResponse register(RegisterRequest request) throws BusinessException;

    /**
     * Logs in a user.
     *
     * @param request The authentication request.
     * @return The authentication response.
     * @throws BusinessException If an error occurs during login.
     */
    AuthenticationResponse login(AuthenticationRequest request) throws BusinessException;

    /**
     * Refreshes the authentication token.
     *
     * @param request  The HTTP servlet request.
     * @param response The HTTP servlet response.
     * @throws BaseException If an error occurs during token refresh.
     */
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws BaseException;

}
