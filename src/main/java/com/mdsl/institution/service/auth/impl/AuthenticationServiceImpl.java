package com.mdsl.institution.service.auth.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdsl.institution.domain.businessmessage.MessageCodes;
import com.mdsl.institution.domain.common.RecordStatus;
import com.mdsl.institution.domain.token.Token;
import com.mdsl.institution.domain.token.TokenType;
import com.mdsl.institution.domain.user.User;
import com.mdsl.institution.dto.auth.AuthenticationRequest;
import com.mdsl.institution.dto.auth.AuthenticationResponse;
import com.mdsl.institution.dto.auth.RegisterRequest;
import com.mdsl.institution.exception.BaseException;
import com.mdsl.institution.exception.BusinessException;
import com.mdsl.institution.repository.token.TokenRepository;
import com.mdsl.institution.repository.user.UserRepository;
import com.mdsl.institution.service.auth.AuthenticationService;
import com.mdsl.institution.service.token.JwtService;
import com.mdsl.institution.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuthenticationServiceImpl implements AuthenticationService 
{
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
 
    @Override
    public AuthenticationResponse register(RegisterRequest request) throws BusinessException
    {
	if(!isUniqueUserName(request))
	{
	    throw new BusinessException(MessageCodes.INVALID_LOGIN.msgCode());
	}
	
	var user = User.builder()
		.firstName(request.getFirstname())
		.lastName(request.getLastname())
		.email(request.getEmail())
		.password(passwordEncoder.encode(request.getPassword()))
		.role(request.getRole())
		.build();
	
	user.setCreatedDate(DateUtil.returnCurrentDate());
	user.setRecordStatus(RecordStatus.ACTIVE);
	
	var savedUser = userRepository.save(user);
	var jwtToken = jwtService.generateToken(user);
	var refreshToken = jwtService.generateRefreshToken(user);
	saveUserToken(savedUser, jwtToken);
	
	return AuthenticationResponse.builder()
		.accessToken(jwtToken)
		.refreshToken(refreshToken)
		.build();
    }

    private boolean isUniqueUserName(RegisterRequest request)
    {
	Optional<User> user = userRepository.findByEmail(request.getEmail());
	
	if(user.isEmpty())
	    return true;
	
	return false;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) throws BusinessException
    {
	authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
	
	Optional<User> user = userRepository.findByEmail(request.getEmail());
	
	if(user.isEmpty())
	    throw new BusinessException(MessageCodes.INVALID_LOGIN.msgCode());
	
	
	var jwtToken = jwtService.generateToken(user.get());
	var refreshToken = jwtService.generateRefreshToken(user.get());
	
	revokeAllUserTokens(user.get());
	saveUserToken(user.get(), jwtToken);
	
	return AuthenticationResponse.builder()
		.accessToken(jwtToken)
		.refreshToken(refreshToken)
		.build();
    }

    private void saveUserToken(User user, String jwtToken)
    {
	var token = Token.builder()
		.user(user)
		.token(jwtToken)
		.tokenType(TokenType.BEARER)
		.expired(false)
		.revoked(false)
		.build();
	
	token.setCreatedDate(DateUtil.returnCurrentDate());
	token.setRecordStatus(RecordStatus.ACTIVE);
	
	tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user)
    {
	var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	if(validUserTokens.isEmpty())
	    return;
	validUserTokens.forEach(token -> {
	    token.setExpired(true);
	    token.setRevoked(true);
	});
	tokenRepository.saveAll(validUserTokens);
    }
    
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
	final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	final String refreshToken;
	final String userEmail;
	if(authHeader == null || !authHeader.startsWith("Bearer "))
	{
	    return;
	}
	refreshToken = authHeader.substring(7);
	userEmail = jwtService.extractUsername(refreshToken);
	if(userEmail != null)
	{
	    var user = this.userRepository.findByEmail(userEmail).orElseThrow();
	    if(jwtService.isTokenValid(refreshToken, user))
	    {
		var accessToken = jwtService.generateToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, accessToken);
		var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
			.build();
		
		try
		{
		    new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
		}
		catch(IOException e)
		{
		    throw new BaseException("Error occured while processing your request");
		}
		
	    }
	}
    }
}
