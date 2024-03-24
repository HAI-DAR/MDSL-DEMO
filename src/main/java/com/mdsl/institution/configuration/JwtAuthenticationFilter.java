package com.mdsl.institution.configuration;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mdsl.institution.domain.common.LoginLanguage;
import com.mdsl.institution.domain.user.User;
import com.mdsl.institution.repository.token.TokenRepository;
import com.mdsl.institution.service.token.JwtService;
import com.mdsl.institution.service.user.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    private final JwtService jwtService;
    // private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
	    @NonNull HttpServletRequest request,
	    @NonNull HttpServletResponse response,
	    @NonNull FilterChain filterChain)
	throws ServletException,
	IOException
    {

	final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	final String jwt;
	final String userEmail;

	if(authHeader == null || !authHeader.startsWith("Bearer "))
	{
	    filterChain.doFilter(request, response);
	    return;
	}

	jwt = authHeader.substring(7);
	userEmail = jwtService.extractUsername(jwt);
	if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
	{
	    Optional<User> optUser = userService.findByEmail(userEmail);

	    if(optUser.isEmpty())
		filterChain.doFilter(request, response);

	    User user = optUser.get();
	    user = setLoginLanguageFromHeader(user,request);
	    
	    var isTokenValid = tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked())
		    .orElse(false);

	    if(jwtService.isTokenValid(jwt, user) && isTokenValid)
	    {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
			user.getAuthorities());

		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	    }

	}
	filterChain.doFilter(request, response);
    }

    private User setLoginLanguageFromHeader(User user, HttpServletRequest request)
    {
	String loginLanguage = request.getHeader("loginLanguage");
	LoginLanguage loginLangEnum = LoginLanguage.getByCode(loginLanguage);
	user.setLoginLang(null == loginLangEnum ? LoginLanguage.ENGLISH : loginLangEnum);
	return user;
    }
}
