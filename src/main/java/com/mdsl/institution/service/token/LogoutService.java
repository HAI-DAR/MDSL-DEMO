package com.mdsl.institution.service.token;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdsl.institution.domain.token.Token;
import com.mdsl.institution.domain.user.User;
import com.mdsl.institution.repository.token.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LogoutService implements LogoutHandler
{

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
	final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	final String jwt;
	if(authHeader == null || !authHeader.startsWith("Bearer "))
	{
	    return;
	}
	jwt = authHeader.substring(7);
	Token storedToken = tokenRepository.findByToken(jwt).orElse(null);
	if(storedToken != null)
	{
	    
	    // Either revoke or delete all user token based on the needed business
	    //storedToken.setExpired(true);
	    //storedToken.setRevoked(true);
	    //tokenRepository.save(storedToken);
	    
	    User user = storedToken.getUser();
	    tokenRepository.deleteByUser(user);
	    SecurityContextHolder.clearContext();
	}
    }
}
