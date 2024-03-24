package com.mdsl.institution.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mdsl.institution.domain.common.LoginLanguage;
import com.mdsl.institution.domain.user.User;

public class SecurityUtil
{

    public static Optional<String> getLoginUserName()
    {
	SecurityContext securityContext = SecurityContextHolder.getContext();
	return Optional.ofNullable(extractUserName(securityContext.getAuthentication()));
    }
    
    public static LoginLanguage getLoginLanguage()
    {
	SecurityContext securityContext = SecurityContextHolder.getContext();
	return extractLoginLang(securityContext.getAuthentication());
    }

    private static String extractUserName(Authentication authentication)
    {
	if(authentication == null)
	{
	    return null;
	}
	else if(authentication.getPrincipal() instanceof User)
	{
	    User springSecurityUser = (User) authentication.getPrincipal();
	    return springSecurityUser.getUsername();
	}
	else if(authentication.getPrincipal() instanceof String)
	{
	    return (String) authentication.getPrincipal();
	}
	return null;
    }
    
    private static LoginLanguage extractLoginLang(Authentication authentication)
    {
	if(authentication == null)
	{
	    return LoginLanguage.ENGLISH;
	}
	else if(authentication.getPrincipal() instanceof User)
	{
	    User springSecurityUser = (User) authentication.getPrincipal();
	    return springSecurityUser.getLoginLang();
	}	
	return LoginLanguage.ENGLISH;
    }
}
