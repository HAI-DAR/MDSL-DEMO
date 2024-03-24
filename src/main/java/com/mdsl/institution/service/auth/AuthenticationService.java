package com.mdsl.institution.service.auth;

import com.mdsl.institution.dto.auth.AuthenticationRequest;
import com.mdsl.institution.dto.auth.AuthenticationResponse;
import com.mdsl.institution.dto.auth.RegisterRequest;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService
{

    AuthenticationResponse register(RegisterRequest request) throws BusinessException;

    AuthenticationResponse login(AuthenticationRequest request) throws BusinessException;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws BaseException;

}
