package com.mdsl.institution.service.token;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdsl.institution.dto.token.JwtSettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService
{

    @Value("${JWT_CONFIG}")
    private String jwtSettingsString;
    
    private static JwtSettings jwtSettings;

    public String extractUsername(String token)
    {
	return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
	final Claims claims = extractAllClaims(token);
	return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails)
    {
	return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
    {
	return buildToken(extraClaims, userDetails, jwtSettings.getExpiration());
    }

    public String generateRefreshToken(UserDetails userDetails)
    {
	return buildToken(new HashMap<>(), userDetails, jwtSettings.getRefreshToken().getExpiration());
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long expiration)
    {
	return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + expiration))
		.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails)
    {
	final String username = extractUsername(token);
	return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token)
    {
	return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token)
    {
	return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token)
    {
	return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey()
    {
	byte[] keyBytes = Decoders.BASE64.decode(jwtSettings.getSecretKey());
	return Keys.hmacShaKeyFor(keyBytes);
    }

    @Autowired
    private void parseJwtConfig() throws JsonMappingException, JsonProcessingException
    {
	ObjectMapper objectMapper = new ObjectMapper();
	jwtSettings = objectMapper.readValue(jwtSettingsString, JwtSettings.class);
    }

}